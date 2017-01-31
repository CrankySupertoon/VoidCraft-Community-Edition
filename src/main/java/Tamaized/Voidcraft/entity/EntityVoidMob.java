package Tamaized.Voidcraft.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

//CLASS WAS MADE FOR TARGETTING PURPOSES (and less use of nbt)
public abstract class EntityVoidMob extends EntityCreature implements IMob {

	private boolean invulnerable = false;

	public EntityVoidMob(World p_i1738_1_) {
		super(p_i1738_1_);
		experienceValue = 10;
	}

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

	protected boolean canAttack(Entity entity) {
		if (entity instanceof EntityWitherSkeleton) return false;
		return true;
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

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_) {
		if (isEntityInvulnerable()) {
			return false;
		} else if (super.attackEntityFrom(p_70097_1_, p_70097_2_)) {
			Entity entity = p_70097_1_.getEntity();
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
			if (!getHeldItemMainhand().isEmpty() && entityIn instanceof EntityLivingBase) getHeldItemMainhand().getItem().hitEntity(getHeldItemMainhand(), (EntityLivingBase) entityIn, this);
		}

		return flag;
	}

	/**
	 * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
	 *//*
		 * protected void attackEntity(Entity p_70785_1_, float p_70785_2_) { if (attackTime <= 0 && p_70785_2_ < 2.0F && p_70785_1_.boundingBox.maxY > boundingBox.minY && p_70785_1_.boundingBox.minY < boundingBox.maxY) { attackTime = 20; if(!canAttack(p_70785_1_)) return; attackEntityAsMob(p_70785_1_); } }
		 */

	/**
	 * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block. Args: x, y, z
	 */
	@Override
	public float getBlockPathWeight(BlockPos pos) {
		return 0.5F - world.getLightBrightness(pos);
	}

	/**
	 * Checks to make sure the light is not too bright where the mob is spawning BUT! MY MOBS DONT CARE SO YEA; this always returns true bro
	 */
	protected boolean isValidLightLevel() {
		return true;
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
}