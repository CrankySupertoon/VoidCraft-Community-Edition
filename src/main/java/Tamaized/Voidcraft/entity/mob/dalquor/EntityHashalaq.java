package Tamaized.Voidcraft.entity.mob.dalquor;

import com.google.common.base.Predicate;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityHashalaq extends EntityMob {

	public EntityHashalaq(World par1World) {
		super(par1World);

		this.isImmuneToFire = true;
		this.setSize(0.9F, 3.0F);

		// this.getNavigator().setBreakDoors(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, false));
		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLivingBase.class, 8.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, true, false));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(50.0D);
	}
	
	// TODO
	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobWraithSoundEvents.ambientSound;
	}

	// TODO
	@Override
	protected SoundEvent getHurtSound() {
		return VoidSoundEvents.EntityMobWraithSoundEvents.hurtSound;
	}

	// TODO
	@Override
	protected SoundEvent getDeathSound() {
		return VoidSoundEvents.EntityMobWraithSoundEvents.deathSound;
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	protected Item getDropItem() {
		return VoidCraft.items.quoriFragment;
	}

}
