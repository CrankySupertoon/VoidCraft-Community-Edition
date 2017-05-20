package Tamaized.Voidcraft.entity;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.Voidcraft.entity.client.animation.IAnimation;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityVoidNPC extends EntityCreature implements IMob, IEntitySync {

	private boolean invulnerable = false;
	protected boolean canDie = true;
	protected boolean canPush = true;
	protected boolean isFlying = false;

	private int[] spawnLoc;
	private boolean firstSpawn = true;

	private IAnimation animation;

	/**
	 * Degrees
	 */

	@Deprecated
	private float leftArmYaw = 0.0f;

	/**
	 * Degrees
	 */

	@Deprecated
	private float leftArmPitch = 0.0f;

	/**
	 * Degrees
	 */

	@Deprecated
	private float rightArmYaw = 0.0f;

	/**
	 * Degrees
	 */

	@Deprecated
	private float rightArmPitch = 0.0f;

	@Deprecated
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
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
	}

	public void playAnimation(IAnimation a) {
		animation = a;
	}

	@SideOnly(Side.CLIENT)
	public void renderSpecials() {
		animation.render(this);
	}

	@Deprecated
	public final void setArmRotations(float leftArmPitch, float rightArmPitch, float leftArmYaw, float rightArmYaw, boolean sendUpdates) {
		this.leftArmYaw = leftArmYaw;
		this.leftArmPitch = leftArmPitch;
		this.rightArmYaw = rightArmYaw;
		this.rightArmPitch = rightArmPitch;
		if (sendUpdates) sendPacketUpdates(this);
	}

	@Deprecated
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

	@Override
	public void onLivingUpdate() {
		if (animation != null && animation.update(this)) animation = null;
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

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote && world.getDifficulty() == EnumDifficulty.PEACEFUL) {
			setDead();
		}
	}

	@Override
	public boolean isEntityAlive() {
		return !canDie ? true : !isDead && getHealth() > 0.0F;
	}

	@Override
	public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_) {
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
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (isInvulnerable()) {
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

	public void setInvulnerable(boolean b) {
		invulnerable = b;
	}

	public boolean isInvulnerable() {
		return invulnerable;
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
	protected SoundEvent getHurtSound() {
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
				ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : null;

				if (itemstack != null && itemstack1 != null && itemstack.getItem() instanceof ItemAxe && itemstack1.getItem() == Items.SHIELD) {
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

	@Override
	public float getBlockPathWeight(BlockPos pos) {
		return 0.5F - world.getLightBrightness(pos);
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

}