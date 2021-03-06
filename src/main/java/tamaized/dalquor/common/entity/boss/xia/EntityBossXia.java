package tamaized.dalquor.common.entity.boss.xia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.common.capabilities.CapabilityList;
import tamaized.dalquor.common.entity.EntityVoidNPC;
import tamaized.dalquor.common.entity.ai.EntityAIFindEntityNearestEntityNoSight;
import tamaized.dalquor.common.entity.boss.IVoidBossData;
import tamaized.dalquor.common.entity.boss.xia.ai.EntityAIXia1Phase1;
import tamaized.dalquor.common.entity.boss.xia.ai.EntityAIXia1Phase2;
import tamaized.dalquor.common.entity.boss.xia.ai.EntityAIXia1Phase3;
import tamaized.dalquor.common.sound.VoidSoundEvents;
import tamaized.dalquor.common.world.dim.xia.WorldProviderXia;
import tamaized.dalquor.network.client.ClientPacketHandlerSheathe;
import tamaized.dalquor.registry.ModArmors;
import tamaized.dalquor.registry.ModItems;
import tamaized.dalquor.registry.ModPotions;
import tamaized.dalquor.registry.ModTools;

import java.util.ArrayList;
import java.util.List;

public class EntityBossXia extends EntityVoidNPC implements IVoidBossData {

	private int phase = 0;
	private BlockPos initialPos;
	private boolean cantUpdatePos = false;
	private List<IDamageListener> aiList;

	public EntityBossXia(World par1World) {
		super(par1World);
		if (aiList == null)
			aiList = new ArrayList<>();
		setSize(0.6F, 1.8F);
		isImmuneToFire = true;
		noClip = true;
		moveHelper = new EntityVoidNPC.BossFlyNoclipMoveHelper(this);
		updateStats();
		setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModArmors.xiaHelmet));
		setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ModArmors.xiaChest));
		setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ModArmors.xiaLegs));
		setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ModArmors.xiaBoots));
		setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ModTools.demonSword));
		ItemStack vade = new ItemStack(ModItems.vadeMecum);
		if (vade.hasCapability(CapabilityList.VADEMECUMITEM, null))
			vade.getCapability(CapabilityList.VADEMECUMITEM, null).setBookState(true);
		setHeldItem(EnumHand.OFF_HAND, vade);
		if (!world.isRemote && world.provider instanceof WorldProviderXia && !((WorldProviderXia) world.provider).getXiaCastleHandler().getHandlerXia1().isRunning())
			setDead();
	}

	private <T extends EntityAIBase & IDamageListener> EntityAIBase addAI(T ai) {
		aiList.add(ai);
		return ai;
	}

	@Override
	protected void initEntityAI() {
		aiList = new ArrayList<>();
		tasks.addTask(1, addAI(new EntityAIXia1Phase1(this)));
		tasks.addTask(1, addAI(new EntityAIXia1Phase2(this)));
		tasks.addTask(1, addAI(new EntityAIXia1Phase3(this)));
		tasks.addTask(7, new EntityVoidNPC.AILookAround(this));

		targetTasks.addTask(1, new EntityAIFindEntityNearestEntityNoSight(this, EntityPlayer.class));
	}

	public final BlockPos getInitialPos() {
		return initialPos;
	}

	public final void updateInitialPos() {
		if (!cantUpdatePos)
			initialPos = getPosition();
		cantUpdatePos = true;
	}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {

	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("phase", phase);
		compound.setBoolean("cantUpdatePos", cantUpdatePos);
		if (initialPos != null)
			compound.setIntArray("initialPos", new int[]{initialPos.getX(), initialPos.getY(), initialPos.getZ()});
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		phase = compound.getInteger("phase");
		cantUpdatePos = compound.getBoolean("cantUpdatePos");
		int[] array = compound.getIntArray("initialPos");
		if (array.length == 3)
			initialPos = new BlockPos(array[0], array[1], array[2]);
		else
			cantUpdatePos = false;
		super.readFromNBT(compound);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		boolean flag = super.attackEntityFrom(source, amount);
		if (flag) {
			if (getHealth() <= 0) {
				phase++;
				updateStats();
				isDead = false;
				if (phase > 2) {
					world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(ModArmors.xiaHelmet)));
					world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(ModArmors.xiaChest)));
					world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(ModArmors.xiaLegs)));
					world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(ModArmors.xiaBoots)));
					if (world.provider instanceof WorldProviderXia) {
						for (EntityPlayer player : ((WorldProviderXia) world.provider).getXiaCastleHandler().getHandlerXia1().getPlayers()) {
							player.sendMessage(new TextComponentTranslation("dalquor.misc.xia.death"));
						}
					}
					setDead();
				}
			} else
				for (IDamageListener listener : aiList)
					if (listener.execute())
						listener.onTakeDamage();
		}
		return flag;
	}

	@Override
	protected void collideWithEntity(Entity entity) {
		entity.attackEntityFrom(DamageSource.causeMobDamage(this), 60);
		ItemStack stack = getHeldItem(EnumHand.MAIN_HAND);
		if (!stack.isEmpty() && entity instanceof EntityLivingBase)
			stack.getItem().hitEntity(stack, (EntityLivingBase) entity, this);
		swingArm(EnumHand.MAIN_HAND);
		for (IDamageListener listener : aiList)
			if (listener.execute())
				listener.onDoDamage(entity);
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).knockBack(this, 0.5F, (double) MathHelper.sin(this.rotationYaw * 0.017453292F), (double) (-MathHelper.cos(this.rotationYaw * 0.017453292F)));
		else
			entity.addVelocity((double) (-MathHelper.sin(this.rotationYaw * 0.017453292F) * 0.5F), 0.1D, (double) (MathHelper.cos(this.rotationYaw * 0.017453292F) * 0.5F));

	}

	@Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {

	}

	@Override
	public void addPotionEffect(PotionEffect potioneffectIn) {
		Potion pot = potioneffectIn.getPotion();
		if (pot == ModPotions.fireSheathe || pot == ModPotions.frostSheathe || pot == ModPotions.litSheathe || pot == ModPotions.acidSheathe)
			super.addPotionEffect(potioneffectIn);
		if (!world.isRemote) {
			DalQuor.network.sendToAllAround(new ClientPacketHandlerSheathe.Packet(getEntityId(), Potion.getIdFromPotion(pot), potioneffectIn.getDuration()), new TargetPoint(world.provider.getDimension(), posX, posY, posZ, 64));
		}
	}

	private void updateStats() {
		setInvulnerable(false);
		switch (phase) {
			default:
			case 0:
				getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
				break;
			case 1:
				getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20D);
				break;
			case 2:
				getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
				canMove = false;
				break;
		}
		setLimbRotations(0, 0, 0, 0);
		setHealth(getMaxHealth());
	}

	public int getPhase() {
		return phase;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobXiaSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return VoidSoundEvents.EntityMobXiaSoundEvents.hurtSound;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VoidSoundEvents.EntityMobXiaSoundEvents.deathSound;
	}

	@Override
	protected float getSoundVolume() {
		return 0.0F;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation("entity.dalquor.Xia.name");
	}

	@Override
	public float getMaxHealthForBossBar() {
		return getMaxHealth();
	}

	@Override
	public float getHealthForBossBar() {
		return getHealth();
	}

	@Override
	public float getPercentHPForBossBar() {
		return getHealth() / getMaxHealth();
	}

	@Override
	public ITextComponent getNameForBossBar() {
		return getDisplayName();
	}

	public interface IDamageListener {
		boolean execute();

		void onTakeDamage();

		void onDoDamage(Entity entity);
	}
}