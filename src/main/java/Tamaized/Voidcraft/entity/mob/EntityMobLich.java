package Tamaized.Voidcraft.entity.mob;

import com.google.common.base.Predicate;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.EntityVoidMob;
import Tamaized.Voidcraft.entity.mob.lich.EntityLichInferno;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMobLich extends EntityVoidMob implements IRangedAttackMob {

	public EntityMobLich(World par1World) {
		super(par1World);

		this.isImmuneToFire = true;
		this.setSize(0.9F, 3.0F);

		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(4, new EntityAIRestrictSun(this));
		this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(2, new EntityAILookIdle(this));
		this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0D, 20, 20, 15.0F));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));

		Predicate ies = new Predicate() {
			/**
			 * Return whether the specified entity is applicable to this filter.
			 */
			public boolean apply(Entity p_82704_1_) {
				if (p_82704_1_ instanceof EntityWitherSkeleton) return false;
				else return true;
			}

			public boolean apply(Object p_apply_1_) {
				return p_apply_1_ instanceof Entity ? this.apply((Entity) p_apply_1_) : false;
			}
		};

		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySnowman.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true, false, ies));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityMobLich.class, 0, true, false, ies)); // Lich hate Lich
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, true, false, ies)); // Passive animals
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySlime.class, 0, true, false, ies)); // Slime extends EntityLiving so need to add it manually
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000000298023224D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(65.0D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobLichSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound() {
		return VoidSoundEvents.EntityMobLichSoundEvents.hurtSound;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VoidSoundEvents.EntityMobLichSoundEvents.deathSound;
	}

	@Override
	protected float getSoundVolume() {
		return 0.6F;
	}

	@Override
	protected Item getDropItem() {
		return VoidCraft.items.voidCloth;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	// TODO: overhaul this
	// TODO Potion like attacks using a new throwable entity.
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float par2) {
		if (!canAttack(target)) return;

		double randAttk = Math.random() * 5;
		randAttk = Math.round(randAttk);
		// randAttk = 0;

		if (randAttk == 5) { // Call the Inferno
			world.spawnEntity(new EntityLichInferno(world, getPosition(), 6));
		} else if (randAttk == 4) { // Call forth the Undead to my aid
			if (target instanceof EntityMobLich) return; // Do not summon the undead if i'm fighting another lich
			EntityMobWraith wraith;
			EntityMobSpectreChain chain;
			EntityMobVoidWrath wrath;
			EntityWitherSkeleton skelly;

			wraith = new EntityMobWraith(world);
			chain = new EntityMobSpectreChain(world);
			wrath = new EntityMobVoidWrath(world);
			skelly = new EntityWitherSkeleton(world);

			wraith.setPosition(this.posX - 2, this.posY, this.posZ - 2);
			wraith.setAttackTarget(target);

			chain.setPosition(this.posX - 2, this.posY, this.posZ + 2);
			chain.setAttackTarget(target);

			wrath.setPosition(this.posX + 2, this.posY, this.posZ - 2);
			wrath.setAttackTarget(target);

			skelly.setPosition(this.posX + 2, this.posY, this.posZ + 2);
			skelly.setAttackTarget(target);

			this.world.spawnEntity(wraith);
			this.world.spawnEntity(chain);
			this.world.spawnEntity(wrath);
			this.world.spawnEntity(skelly);

		} else if (randAttk == 3) { // Incase Target in Stone
			if (target instanceof EntityMobLich) return; // Don't bother if against a lich

			int j = (int) MathHelper.floor(target.posX);
			int k = (int) MathHelper.floor(target.posY);
			int l = (int) MathHelper.floor(target.posZ);

			for (int xj = -1; xj < 2; xj++) {
				for (int yj = -1; yj < 1; yj++) {
					for (int zj = -1; yj < 1; yj++) {
						if (this.world.isAirBlock(new BlockPos(xj, yj, zj))) this.world.setBlockState(new BlockPos(xj, yj, zj), Blocks.STONE.getDefaultState(), 3);
					}
				}
			}
		} else if (randAttk == 2) { // EntityLightningBolt at Target
			double x = target.posX;
			double y = target.posY;
			double z = target.posZ;

			EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, x, y, z, false);
			entitylightningbolt.setLocationAndAngles(x, y + 1 + entitylightningbolt.getYOffset(), z, target.rotationYaw, target.rotationPitch);
			world.addWeatherEffect(entitylightningbolt);

		} else if (randAttk == 1) { // EntityLargeFireball at Target
			double d5 = target.posX - this.posX;
			double d6 = target.getEntityBoundingBox().minY + (double) (target.height / 2.0F) - (this.posY + (double) (this.height / 2.0F));
			double d7 = target.posZ - this.posZ;
			this.world.playEvent((EntityPlayer) null, 1016, new BlockPos((int) this.posX, (int) this.posY, (int) this.posZ), 0);
			EntityLargeFireball entitylargefireball = new EntityLargeFireball(this.world, this, d5, d6, d7);
			double d8 = 4.0D;
			// Vec3d vec3 = this.getLook(1.0F);
			entitylargefireball.posX = this.posX;// + vec3.xCoord * d8;
			entitylargefireball.posY = this.posY + (double) (this.height / 2.0F) + 0.5D;
			entitylargefireball.posZ = this.posZ;// + vec3.zCoord * d8;
			this.world.spawnEntity(entitylargefireball);

		} else if (randAttk == 0) { // Speak TODO

		}
	}
}