package Tamaized.Voidcraft.entity;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.Voidcraft.network.IEntitySync;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class EntityVoidNPC extends EntityCreature implements IMob, IEntitySync {

	private boolean invulnerable = false;
	protected boolean canDie = true;
	protected boolean canPush = true;
	protected boolean isFlying = false;

	private int[] spawnLoc;
	private boolean firstSpawn = true;

	/**
	 * Degrees
	 */
	private float leftArmYaw = 0.0f;

	/**
	 * Degrees
	 */
	private float leftArmPitch = 0.0f;

	/**
	 * Degrees
	 */
	private float rightArmYaw = 0.0f;

	/**
	 * Degrees
	 */
	private float rightArmPitch = 0.0f;

	public enum ArmRotation {
		LeftYaw, LeftPitch, RightYaw, RightPitch
	}

	public EntityVoidNPC(World p_i1738_1_) {
		super(p_i1738_1_);
		experienceValue = 10;
		ignoreFrustumCheck = true;
		enablePersistence();
	}

	@Override
	public SoundCategory getSoundCategory() {
		return SoundCategory.HOSTILE;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
	}

	public final void setArmRotations(float leftArmPitch, float rightArmPitch, float leftArmYaw, float rightArmYaw, boolean sendUpdates) {
		this.leftArmYaw = leftArmYaw;
		this.leftArmPitch = leftArmPitch;
		this.rightArmYaw = rightArmYaw;
		this.rightArmPitch = rightArmPitch;
		if (sendUpdates) sendPacketUpdates();
	}

	public final float getArmRotation(ArmRotation arm) {
		switch (arm) {
			default:
			case LeftPitch:
				return leftArmPitch;
			case LeftYaw:
				return leftArmYaw;
			case RightPitch:
				return rightArmPitch;
			case RightYaw:
				return rightArmYaw;
		}
	}

	@Override
	public final void encodePacket(DataOutputStream stream) throws IOException {
		stream.writeFloat(leftArmPitch);
		stream.writeFloat(rightArmPitch);
		stream.writeFloat(leftArmYaw);
		stream.writeFloat(rightArmYaw);
		encodePacketData(stream);
	}

	protected abstract void encodePacketData(DataOutputStream stream) throws IOException;

	@Override
	public final void decodePacket(ByteBufInputStream stream) throws IOException {
		setArmRotations(stream.readFloat(), stream.readFloat(), stream.readFloat(), stream.readFloat(), false);
		decodePacketData(stream);
	}

	protected abstract void decodePacketData(ByteBufInputStream stream) throws IOException;

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons use this to react to sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate() {
		updateArmSwingProgress();
		float f = getBrightness(1.0F);
		if (f > 0.5F) {
			entityAge += 2;
		}
		super.onLivingUpdate();
	}

	public boolean isEntityFlying() {
		return isFlying;
	}

	@Override
	public boolean canBePushed() {
		return canPush;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote && world.getDifficulty() == EnumDifficulty.PEACEFUL) {
			setDead();
		}
	}

	/**
	 * Checks whether target entity is alive.
	 */
	@Override
	public boolean isEntityAlive() {
		return !canDie ? true : !isDead && getHealth() > 0.0F;
	}

	/**
	 * Moves the entity based on the specified heading. Args: strafe, forward
	 */
	@Override
	public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_) {
		/*
		 * if(isFlying){ jumpMovementFactor = 0.0F; double d3 = motionY; motionY = d3 * 0.6D; }else{ jumpMovementFactor = 0.2F; super.moveEntityWithHeading(p_70612_1_, p_70612_2_); }
		 */
		// super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
		prevLimbSwingAmount = limbSwingAmount;
		double d0 = posX - prevPosX;
		double d1 = posZ - prevPosZ;
		float f6 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;

		if (f6 > 1.0F) {
			f6 = 1.0F;
		}

		limbSwingAmount += (f6 - limbSwingAmount) * 0.4F;
		limbSwing += limbSwingAmount;
	}

	@Override
	protected SoundEvent getSwimSound() {
		return SoundEvents.ENTITY_HOSTILE_SWIM;
	}

	@Override
	protected SoundEvent getSplashSound() {
		return SoundEvents.ENTITY_HOSTILE_SPLASH;
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (isEntityInvulnerable()) {
			return false;
		} else if (super.attackEntityFrom(source, amount)) {
			Entity entity = source.getEntity();
			if (entity != this && entity instanceof EntityLivingBase) {
				setAttackTarget((EntityLivingBase) entity);
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * I'm lazy
	 * 
	 * @param b
	 */
	public void setInvul(boolean b) {
		invulnerable = b;
	}

	/**
	 * Return whether this entity is invulnerable to damage. Again, im lazy
	 */
	public boolean isEntityInvulnerable() {
		return invulnerable;
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected SoundEvent getHurtSound() {
		return SoundEvents.ENTITY_HOSTILE_HURT;
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
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
		// if(p_70652_1_ instanceof VoidChain) return false;

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

	/**
	 * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
	 *//*
		 * protected void attackEntity(Entity p_70785_1_, float p_70785_2_){ if (attackTime <= 0 && p_70785_2_ < 2.0F && p_70785_1_.boundingBox.maxY > boundingBox.minY && p_70785_1_.boundingBox.minY < boundingBox.maxY){ attackTime = 20; attackEntityAsMob(p_70785_1_); } }
		 */

	/**
	 * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block. Args: x, y, z
	 */
	@Override
	public float getBlockPathWeight(BlockPos pos) {
		return 0.5F - world.getLightBrightness(pos);
	}

	/**
	 * Checks to make sure the light is not too bright where the mob is spawning BUT! MY NPCS DONT CARE SO YEA; this always returns false bro
	 */
	protected boolean isValidLightLevel() {
		return false;
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere() {
		return world.getDifficulty() != EnumDifficulty.PEACEFUL && isValidLightLevel() && super.getCanSpawnHere();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	}

	/**
	 * Entity won't drop items or experience points if this returns false
	 */
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

	@Override
	public final Entity getEntity() {
		return this;
	}
}