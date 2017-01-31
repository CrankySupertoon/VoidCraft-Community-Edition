package Tamaized.Voidcraft.entity.companion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class EntityCompanion extends EntityTameable {

	private static final DataParameter<Float> DATA_HEALTH_ID = EntityDataManager.<Float> createKey(EntityCompanion.class, DataSerializers.FLOAT);
	private static final DataParameter<Integer> COLOR = EntityDataManager.<Integer> createKey(EntityCompanion.class, DataSerializers.VARINT);

	public EntityCompanion(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
		tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		tasks.addTask(8, new EntityAIWanderAvoidWater(this, 1.0D));
		tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(10, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
	}

	protected abstract double getDefaultMoveSpeed(boolean tamed);

	protected abstract double getDefaultMaxHealth(boolean tamed);

	protected abstract double getDefaultDamage(boolean tamed);

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		applyAttributes();
	}

	private void applyAttributes() {
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getDefaultMoveSpeed(isTamed()));
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getDefaultMaxHealth(isTamed()));
		if (getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null) getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getDefaultDamage(isTamed()));
		else getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getDefaultDamage(isTamed()));
	}

	@Override
	protected void updateAITasks() {
		dataManager.set(DATA_HEALTH_ID, Float.valueOf(getHealth()));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(DATA_HEALTH_ID, Float.valueOf(getHealth()));
		dataManager.register(COLOR, Integer.valueOf(EnumDyeColor.ORANGE.getDyeDamage()));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setByte("DyeColor", (byte) getColor().getDyeDamage());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		if (compound.hasKey("Color", 99)) {
			setColor(EnumDyeColor.byDyeDamage(compound.getByte("DyeColor")));
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));
		if (flag) {
			applyEnchantments(this, entityIn);
		}
		return flag;
	}

	@Override
	public void setTamed(boolean tamed) {
		super.setTamed(tamed);
		applyAttributes();
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return false;
	}

	public EnumDyeColor getColor() {
		return EnumDyeColor.byDyeDamage(((Integer) dataManager.get(COLOR)).intValue() & 15);
	}

	public void setColor(EnumDyeColor collarcolor) {
		dataManager.set(COLOR, Integer.valueOf(collarcolor.getDyeDamage()));
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		System.out.println(isTamed() + " & " + itemstack.getItem());
		if (isTamed()) {
			if (!itemstack.isEmpty()) {
				if (itemstack.getItem() == Items.DYE) {
					EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(itemstack.getMetadata());
					if (enumdyecolor != getColor()) {
						setColor(enumdyecolor);
						if (!player.capabilities.isCreativeMode) {
							itemstack.shrink(1);
						}
						return true;
					}
				}
			}
			return true;
		}
		return super.processInteract(player, hand);
	}

	@Override
	public boolean canMateWith(EntityAnimal otherAnimal) {
		return false;
	}

	@Override
	public boolean shouldAttackEntity(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
		if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast)) {
			return p_142018_1_ instanceof EntityPlayer && p_142018_2_ instanceof EntityPlayer && !((EntityPlayer) p_142018_2_).canAttackPlayer((EntityPlayer) p_142018_1_) ? false : !(p_142018_1_ instanceof AbstractHorse) || !((AbstractHorse) p_142018_1_).isTame();
		} else {
			return false;
		}
	}

	public void tame(EntityPlayer player) {
		setOwnerId(player.getGameProfile().getId());
		setTamed(true);
	}

	@Override
	public boolean canBeLeashedTo(EntityPlayer player) {
		return false;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}
}