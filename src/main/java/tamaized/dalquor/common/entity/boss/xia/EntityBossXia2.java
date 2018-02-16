package tamaized.dalquor.common.entity.boss.xia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import tamaized.dalquor.VoidCraft;
import tamaized.dalquor.common.entity.EntityVoidBoss;
import tamaized.dalquor.common.entity.ghost.EntityGhostPlayerBase;
import tamaized.dalquor.common.sound.VoidSoundEvents;
import tamaized.dalquor.common.xiacastle.logic.battle.xia2.Xia2BattleHandler;
import tamaized.dalquor.common.xiacastle.logic.battle.xia2.phases.EntityAIXia2Phase1;
import tamaized.dalquor.common.xiacastle.logic.battle.xia2.phases.EntityAIXia2Phase2;
import tamaized.dalquor.common.xiacastle.logic.battle.xia2.phases.EntityAIXia2Phase3;
import tamaized.dalquor.network.IVoidBossAIPacket;
import tamaized.dalquor.network.client.ClientPacketHandlerSheathe;
import tamaized.dalquor.registry.VoidCraftPotions;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class EntityBossXia2 extends EntityVoidBoss<Xia2BattleHandler> {

	private static final DataParameter<Boolean> SPHERE_STATE = EntityDataManager.createKey(EntityBossXia2.class, DataSerializers.BOOLEAN);
	private final BossInfoServer bossInfo = new BossInfoServer(getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS);
	private List<EntityGhostPlayerBase> ghostList = new ArrayList<>();

	public EntityBossXia2(World par1World) {
		super(par1World);
		this.setInvulnerable(true);
		canMove = false;
	}

	public EntityBossXia2(World world, Xia2BattleHandler handler) {
		super(world, handler, false);
		this.setInvulnerable(true);
		canMove = false;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(SPHERE_STATE, false);
	}

	public boolean getSphereState() {
		return dataManager.get(SPHERE_STATE);
	}

	public void setSphereState(boolean state) {
		dataManager.set(SPHERE_STATE, state);
	}

	@Override
	public void setCustomNameTag(String name) {
		super.setCustomNameTag(name);
		this.bossInfo.setName(this.getDisplayName());
	}

	@Override
	public void addTrackingPlayer(EntityPlayerMP player) {
		super.addTrackingPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(EntityPlayerMP player) {
		super.removeTrackingPlayer(player);
		this.bossInfo.removePlayer(player);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		if (this.hasCustomName())
			this.bossInfo.setName(this.getDisplayName());
	}

	@Override
	public void onLivingUpdate() {
		if (!world.isRemote)
			bossInfo.setPercent(getHealth() / getMaxHealth());
		super.onLivingUpdate();
	}

	@Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {

	}

	@Override
	protected void triggerOnDamage(int phase, DamageSource source, float amount) {
		sendPacketToBus(new Xia2TookDamagePacket());
	}

	@Override
	protected void deathHook() {

	}

	@Override
	public void addPotionEffect(PotionEffect potioneffectIn) {
		Potion pot = potioneffectIn.getPotion();
		if (pot == VoidCraftPotions.fireSheathe || pot == VoidCraftPotions.frostSheathe || pot == VoidCraftPotions.litSheathe || pot == VoidCraftPotions.acidSheathe)
			super.addPotionEffect(potioneffectIn);
		if (!world.isRemote) {
			VoidCraft.network.sendToAllAround(new ClientPacketHandlerSheathe.Packet(getEntityId(), Potion.getIdFromPotion(pot), potioneffectIn.getDuration()), new TargetPoint(world.provider.getDimension(), posX, posY, posZ, 64));
		}
	}

	public void addGhost(EntityGhostPlayerBase ghost) {
		ghostList.add(ghost);
	}

	public List<EntityGhostPlayerBase> getGhostList() {
		return ghostList;
	}

	public void clearGhosts() {
		ghostList.clear();
	}

	@Override
	protected void initPhase(int phase) {
		setSphereState(false);
		clearGhosts();
		switch (phase) {
			case 1: {
				getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
				setHealth(getMaxHealth());
				// BossMusicManager.PlayTheSound(this.worldObj, this, new ItemStack(voidCraft.items.voidDiscs.get(10)), new int[]{(int) this.posX, (int) this.posY, (int) this.posZ}, true);
				setInvulnerable(false);
				addAI(new EntityAIXia2Phase1(this, getFilters()));
			}
			break;
			case 2: {
				getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
				setHealth(getMaxHealth());
				setInvulnerable(false);
				addAI(new EntityAIXia2Phase2(this, getFilters()));
			}
			break;
			case 3: {
				getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
				setHealth(getMaxHealth());
				setInvulnerable(false);
				addAI(new EntityAIXia2Phase3(this, getFilters()));
			}
			break;
			default:
				break;
		}

		setLimbRotations(0, 0, 0, 0);
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
		return new TextComponentString("Xia");
	}

	@Override
	protected void updatePhase(int phase) {
		for (EntityPlayer player : world.playerEntities) {
			if (player.getDistanceToEntity(this) >= 100) {
				player.sendMessage(new TextComponentTranslation("voidcraft.misc.xia.escape"));
				player.setPositionAndUpdate(this.posX, this.posY, this.posZ - 2);
			}
		}
	}

	@Override
	protected List<Class> getFilters() {
		List<Class> filter = new ArrayList<>();
		filter.add(EntityPlayer.class);
		return filter;
	}

	@Override
	protected boolean immuneToFire() {
		return true;
	}

	@Override
	protected float sizeWidth() {
		return 0.6F;
	}

	@Override
	protected float sizeHeight() {
		return 1.8F;
	}

	@Override
	protected int maxPhases() {
		return 3;
	}

	public class Xia2TookDamagePacket implements IVoidBossAIPacket {

	}

}