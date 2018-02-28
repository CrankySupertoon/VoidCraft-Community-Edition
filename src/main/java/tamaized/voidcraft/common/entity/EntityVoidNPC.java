package tamaized.voidcraft.common.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityVoidNPC extends EntityFlying implements IMob {

	private static final DataParameter<Float> LEFT_ARM_YAW = EntityDataManager.createKey(EntityVoidNPC.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> LEFT_ARM_PITCH = EntityDataManager.createKey(EntityVoidNPC.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> RIGHT_ARM_YAW = EntityDataManager.createKey(EntityVoidNPC.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> RIGHT_ARM_PITCH = EntityDataManager.createKey(EntityVoidNPC.class, DataSerializers.FLOAT);

	protected boolean canDie = true;
	protected boolean canPush = true;
	protected boolean canMove = true;
	private boolean invulnerable = false;

	private int animationID;

	public EntityVoidNPC(World world) {
		super(world);
		experienceValue = 10;
		ignoreFrustumCheck = true;
		enablePersistence();
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(LEFT_ARM_YAW, 0.0F);
		dataManager.register(LEFT_ARM_PITCH, 0.0F);
		dataManager.register(RIGHT_ARM_YAW, 0.0F);
		dataManager.register(RIGHT_ARM_PITCH, 0.0F);
	}

	public void setLimbRotations(float ly, float lp, float ry, float rp) {
		dataManager.set(LEFT_ARM_YAW, ly);
		dataManager.set(LEFT_ARM_PITCH, lp);
		dataManager.set(RIGHT_ARM_YAW, ry);
		dataManager.set(RIGHT_ARM_PITCH, rp);
	}

	public float getLeftArmYaw() {
		return dataManager.get(LEFT_ARM_YAW);
	}

	public float getLeftArmPitch() {
		return dataManager.get(LEFT_ARM_PITCH);
	}

	public float getRightArmYaw() {
		return dataManager.get(RIGHT_ARM_YAW);
	}

	public float getRightArmPitch() {
		return dataManager.get(RIGHT_ARM_PITCH);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("canDie", canDie);
		nbt.setBoolean("canPush", canPush);
		nbt.setBoolean("invulnerable", invulnerable);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		canDie = nbt.getBoolean("canDie");
		canPush = nbt.getBoolean("canPush");
		invulnerable = nbt.getBoolean("invulnerable");
	}

	@Override
	public void onLivingUpdate() {
		updateArmSwingProgress();
		float f = getBrightness();
		if (f > 0.5F) {
			idleTime += 2;
		}
		super.onLivingUpdate();
	}

	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setVelocity(double x, double y, double z) {
		if (canMove)
			super.setVelocity(x, y, z);
	}

	@Override
	public void addVelocity(double x, double y, double z) {
		if (canMove)
			super.addVelocity(x, y, z);
	}

	@Override
	public void move(MoverType type, double x, double y, double z) {
		if (canMove)
			super.move(type, x, y, z);
	}

	@Override
	public void moveRelative(float strafe, float up, float forward, float friction) {
		if (canMove)
			super.moveRelative(strafe, up, forward, friction);
	}

	@Override
	public void moveToBlockPosAndAngles(BlockPos pos, float rotationYawIn, float rotationPitchIn) {
		if (canMove)
			moveToBlockPosAndAngles(pos, rotationYawIn, rotationPitchIn);
	}

	@Override
	public boolean canBePushed() {
		return canPush;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote && world.getDifficulty() == EnumDifficulty.PEACEFUL) {
			setDead();
		}
	}

	@Override
	public boolean isEntityAlive() {
		return !canDie || !isDead && getHealth() > 0.0F;
	}

	/*@Override
	public void travel(float p_191986_1_, float p_191986_2_, float p_191986_3_) {
		prevLimbSwingAmount = limbSwingAmount;
		double d0 = posX - prevPosX;
		double d1 = posZ - prevPosZ;
		float f6 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;

		if (f6 > 1.0F) {
			f6 = 1.0F;
		}

		limbSwingAmount += (f6 - limbSwingAmount) * 0.4F;
		limbSwing += limbSwingAmount;
	}*/

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (isInvulnerable()) {
			return false;
		} else if (super.attackEntityFrom(source, amount)) {
			Entity entity = source.getTrueSource();
			if (entity != this && entity instanceof EntityLivingBase) {
				setAttackTarget((EntityLivingBase) entity);
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean isInvulnerable() {
		return invulnerable;
	}

	public void setInvulnerable(boolean b) {
		invulnerable = b;
	}

	@Override
	public SoundCategory getSoundCategory() {
		return SoundCategory.HOSTILE;
	}

	@Override
	protected SoundEvent getSwimSound() {
		return SoundEvents.ENTITY_HOSTILE_SWIM;
	}

	@Override
	protected SoundEvent getSplashSound() {
		return SoundEvents.ENTITY_HOSTILE_SPLASH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_HOSTILE_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_HOSTILE_DEATH;
	}

	@Override
	protected SoundEvent getFallSound(int heightIn) {
		return heightIn > 4 ? SoundEvents.ENTITY_HOSTILE_BIG_FALL : SoundEvents.ENTITY_HOSTILE_SMALL_FALL;
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		float f = (float) getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
		int i = 0;

		if (entityIn instanceof EntityLivingBase) {
			f += EnchantmentHelper.getModifierForCreature(getHeldItemMainhand(), ((EntityLivingBase) entityIn).getCreatureAttribute());
			i += EnchantmentHelper.getKnockbackModifier(this);
		}

		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

		if (flag) {

			if (i > 0) {
				entityIn.addVelocity((double) (-MathHelper.sin(rotationYaw * (float) Math.PI / 180.0F) * (float) i * 0.5F), 0.1D, (double) (MathHelper.cos(rotationYaw * (float) Math.PI / 180.0F) * (float) i * 0.5F));
				motionX *= 0.6D;
				motionZ *= 0.6D;
			}

			int j = EnchantmentHelper.getFireAspectModifier(this);

			if (j > 0) {
				entityIn.setFire(j * 4);
			}

			if (entityIn instanceof EntityPlayer) {
				EntityPlayer entityplayer = (EntityPlayer) entityIn;
				ItemStack itemstack = getHeldItemMainhand();
				ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

				if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem() instanceof ItemAxe && itemstack1.getItem() == Items.SHIELD) {
					float f1 = 0.25F + (float) EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

					if (rand.nextFloat() < f1) {
						entityplayer.getCooldownTracker().setCooldown(Items.SHIELD, 100);
						world.setEntityState(entityplayer, (byte) 30);
					}
				}
			}

			applyEnchantments(this, entityIn);
		}
		return flag;
	}

	protected boolean isValidLightLevel() {
		return false;
	}

	@Override
	public boolean getCanSpawnHere() {
		return world.getDifficulty() != EnumDifficulty.PEACEFUL && isValidLightLevel() && super.getCanSpawnHere();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	}

	@Override
	protected boolean canDropLoot() {
		return true;
	}

	@Override
	protected void despawnEntity() {

	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	protected static class AILookAround extends EntityAIBase {
		private final EntityVoidNPC parentEntity;

		public AILookAround(EntityVoidNPC entity) {
			this.parentEntity = entity;
			this.setMutexBits(2);
		}

		@Override
		public boolean shouldExecute() {
			return true;
		}

		@Override
		public void updateTask() {
			if (this.parentEntity.getAttackTarget() == null) {
				this.parentEntity.rotationYaw = -((float) MathHelper.atan2(this.parentEntity.motionX, this.parentEntity.motionZ)) * (180F / (float) Math.PI);
				this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
			} else {
				EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();
				double d0 = 64.0D;

				if (entitylivingbase.getDistance(this.parentEntity) < 4096.0D) {
					parentEntity.getLookHelper().setLookPositionWithEntity(parentEntity.getAttackTarget(), 30, 30);
					//					double d1 = entitylivingbase.posX - this.parentEntity.posX;
					//					double d2 = entitylivingbase.posZ - this.parentEntity.posZ;
					//					this.parentEntity.rotationYaw = -((float) MathHelper.atan2(d1, d2)) * (180F / (float) Math.PI);
					//					this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
				}
			}
		}
	}

	protected static class AIChaseTarget extends EntityAIBase {
		private final EntityVoidNPC parentEntity;
		private final boolean doDamage;

		public AIChaseTarget(EntityVoidNPC entity, boolean damage) {
			this.parentEntity = entity;
			doDamage = damage;
			setMutexBits(1);
		}

		@Override
		public boolean shouldExecute() {
			return parentEntity.getAttackTarget() != null;
		}

		@Override
		public void updateTask() {
			if (parentEntity.getAttackTarget() != null) {
				Entity target = parentEntity.getAttackTarget();
				parentEntity.moveHelper.setMoveTo(target.posX, target.posY, target.posZ, 1);
				if (doDamage && (parentEntity.getDistanceSq(target.posX, target.getEntityBoundingBox().minY, target.posZ)) <= getAttackReachSqr(parentEntity.getAttackTarget()))
					parentEntity.attackEntityAsMob(target);
				parentEntity.getLookHelper().setLookPositionWithEntity(parentEntity.getAttackTarget(), 30, 30);
			} else {
				parentEntity.moveHelper.setMoveTo(parentEntity.posX, parentEntity.posY, parentEntity.posZ, 0);
			}
		}

		private double getAttackReachSqr(EntityLivingBase attackTarget) {
			return (double) (this.parentEntity.width * 2.0F * this.parentEntity.width * 2.0F + attackTarget.width);
		}
	}

	public static class BossFlyNoclipMoveHelper extends EntityMoveHelper {
		private final EntityVoidNPC parentEntity;
		private int courseChangeCooldown;

		public BossFlyNoclipMoveHelper(EntityVoidNPC boss) {
			super(boss);
			this.parentEntity = boss;
		}

		@Override
		public void onUpdateMoveHelper() {
			if (this.action == EntityMoveHelper.Action.MOVE_TO) {
				double d0 = this.posX - this.parentEntity.posX;
				double d1 = this.posY - this.parentEntity.posY;
				double d2 = this.posZ - this.parentEntity.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				if (d3 <= 1)
					action = Action.WAIT;

				if (this.courseChangeCooldown-- <= 0) {
					this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
					d3 = (double) MathHelper.sqrt(d3);
					double movementSpeed = parentEntity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
					this.parentEntity.motionX += d0 / d3 * (movementSpeed * speed);
					this.parentEntity.motionY += d1 / d3 * (movementSpeed * speed);
					this.parentEntity.motionZ += d2 / d3 * (movementSpeed * speed);
				}
				this.parentEntity.rotationYaw = -((float) MathHelper.atan2(this.parentEntity.motionX, this.parentEntity.motionZ)) * (180F / (float) Math.PI);
				this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
			}
		}

	}

}