package tamaized.voidcraft.common.entity.mob;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.entity.EntityVoidMob;
import tamaized.voidcraft.common.entity.ai.ISpellAttackMob;
import tamaized.voidcraft.common.sound.VoidSoundEvents;
import tamaized.voidcraft.common.vademecum.progression.VadeMecumWordsOfPower;
import tamaized.voidcraft.registry.VoidCraftItems;

import java.util.ArrayList;
import java.util.List;

public class EntityMobLich extends EntityVoidMob implements ISpellAttackMob {

	public static final List<IVadeMecumCapability.Category> spells = new ArrayList<>();

	static {
		spells.add(IVadeMecumCapability.Category.Flame);
		spells.add(IVadeMecumCapability.Category.Fireball);
		spells.add(IVadeMecumCapability.Category.RingOfFire);
		spells.add(IVadeMecumCapability.Category.LitStrike);
		spells.add(IVadeMecumCapability.Category.RingOfLit);
		spells.add(IVadeMecumCapability.Category.IceSpike);
		spells.add(IVadeMecumCapability.Category.RingOfFrost);
		spells.add(IVadeMecumCapability.Category.AcidSpray);
		spells.add(IVadeMecumCapability.Category.Disint);
		spells.add(IVadeMecumCapability.Category.RingOfAcid);
	}

	public EntityMobLich(World par1World) {
		super(par1World);

		isImmuneToFire = true;
		setSize(0.9F, 3.0F);

		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(4, new EntityAIRestrictSun(this));
		tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
		tasks.addTask(2, new EntityAIWander(this, 1.0D));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(2, new EntityAILookIdle(this));
		tasks.addTask(2, new EntityAIAttackSpell(this, 1.0D, 20, 15.0F, 2));
		tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));

		Predicate ies = new Predicate() {
			public boolean apply(Entity p_82704_1_) {
				return !(p_82704_1_ instanceof EntityWitherSkeleton);
			}

			@Override
			public boolean apply(Object p_apply_1_) {
				return p_apply_1_ instanceof Entity && apply((Entity) p_apply_1_);
			}
		};

		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, ies));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, 0, true, false, ies));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySnowman.class, 0, true, false, ies));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true, false, ies));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityMobLich.class, 0, true, false, ies)); // Lich hate Lich
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, true, false, ies)); // Passive animals
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySlime.class, 0, true, false, ies)); // Slime extends EntityLiving so need to add it manually
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000000298023224D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(65.0D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobLichSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
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
		return VoidCraftItems.voidCloth;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	public void attackEntityWithSpellAttack(EntityLivingBase target, float par2) {
		if (!canAttack(target))
			return;
		int randAttk = world.rand.nextInt(spells.size());
		VadeMecumWordsOfPower.invoke(world, spells.get(randAttk), this, target);
	}

	// TODO: put this in TamModized
	private class EntityAIAttackSpell extends EntityAIBase {
		private final EntityLiving entity;
		private final ISpellAttackMob target;
		private final double moveSpeed;
		private final int attackIntervalMin;
		private final int maxRangedAttackTime;
		private final float attackRadius;
		private final float maxAttackDistance;
		private final float minAttackDistance;
		private EntityLivingBase attackTarget;
		private int rangedAttackTime;
		private int seeTime;

		public EntityAIAttackSpell(ISpellAttackMob attacker, double movespeed, int maxAttackTime, float maxAttackDistanceIn, float minAttackDistanceIn) {
			this(attacker, movespeed, maxAttackTime, maxAttackTime, maxAttackDistanceIn, minAttackDistanceIn);
		}

		public EntityAIAttackSpell(ISpellAttackMob attacker, double movespeed, int p_i1650_4_, int maxAttackTime, float maxAttackDistanceIn, float minAttackDistanceIn) {
			rangedAttackTime = -1;
			target = attacker;
			entity = (EntityLiving) attacker;
			moveSpeed = movespeed;
			attackIntervalMin = p_i1650_4_;
			maxRangedAttackTime = maxAttackTime;
			attackRadius = maxAttackDistanceIn;
			maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
			minAttackDistance = minAttackDistanceIn * minAttackDistanceIn;
			setMutexBits(3);
		}

		@Override
		public boolean shouldExecute() {
			EntityLivingBase entitylivingbase = entity.getAttackTarget();

			if (entitylivingbase == null) {
				return false;
			} else {
				attackTarget = entitylivingbase;
				return attackTarget.getDistance(entity) > minAttackDistance;
			}
		}

		@Override
		public boolean shouldContinueExecuting() {
			return shouldExecute() || !entity.getNavigator().noPath();
		}

		@Override
		public void resetTask() {
			attackTarget = null;
			seeTime = 0;
			rangedAttackTime = -1;
		}

		@Override
		public void updateTask() {
			double d0 = entity.getDistanceSq(attackTarget.posX, attackTarget.getEntityBoundingBox().minY, attackTarget.posZ);
			boolean flag = entity.getEntitySenses().canSee(attackTarget);

			if (flag) {
				++seeTime;
			} else {
				seeTime = 0;
			}

			if (d0 <= (double) maxAttackDistance && seeTime >= 20) {
				entity.getNavigator().clearPath();
			} else {
				entity.getNavigator().tryMoveToEntityLiving(attackTarget, moveSpeed);
			}

			entity.getLookHelper().setLookPositionWithEntity(attackTarget, 30.0F, 30.0F);

			if (--rangedAttackTime == 0) {
				if (!flag) {
					return;
				}

				float f = MathHelper.sqrt(d0) / attackRadius;
				float lvt_5_1_ = MathHelper.clamp(f, 0.1F, 1.0F);
				target.attackEntityWithSpellAttack(attackTarget, lvt_5_1_);
				rangedAttackTime = MathHelper.floor(f * (float) (maxRangedAttackTime - attackIntervalMin) + (float) attackIntervalMin);
			} else if (rangedAttackTime < 0) {
				float f2 = MathHelper.sqrt(d0) / attackRadius;
				rangedAttackTime = MathHelper.floor(f2 * (float) (maxRangedAttackTime - attackIntervalMin) + (float) attackIntervalMin);
			}
		}
	}
}