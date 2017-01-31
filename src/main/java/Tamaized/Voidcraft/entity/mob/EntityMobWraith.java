package Tamaized.Voidcraft.entity.mob;

import com.google.common.base.Predicate;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.EntityVoidMob;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import net.minecraft.entity.Entity;
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
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityMobWraith extends EntityVoidMob implements IMob{
	
	public EntityMobWraith(World par1World) {
		super(par1World);
		
		this.isImmuneToFire = true;
		this.setSize(0.9F, 3.0F);
		
		//this.getNavigator().setBreakDoors(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIBreakDoor(this));
		this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, false));
		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		
		Predicate ies = new Predicate(){
			/**
			 * Return whether the specified entity is applicable to this filter.
			 */
			public boolean apply(Entity p_82704_1_){
				if(p_82704_1_ instanceof EntityWitherSkeleton) return false;
				else if(p_82704_1_ instanceof EntityHerobrineCreeper || p_82704_1_ instanceof EntityShulker) return false;
				else return true;
			}
			public boolean apply(Object p_apply_1_){
				return p_apply_1_ instanceof Entity ? this.apply((Entity)p_apply_1_) : false;
			}
		};
		
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySnowman.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true, false, ies));
	}
	
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(45.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(25.0D);
	}
    
    @Override
    protected SoundEvent getAmbientSound(){
    	return VoidSoundEvents.EntityMobWraithSoundEvents.ambientSound;
    }
    
    @Override
    protected SoundEvent getHurtSound(){
    	return VoidSoundEvents.EntityMobWraithSoundEvents.hurtSound;
    }
    
    @Override
    protected SoundEvent getDeathSound(){
    	return VoidSoundEvents.EntityMobWraithSoundEvents.deathSound;
    }

    @Override
    protected float getSoundVolume(){
    	return 0.4F;
    }

    @Override
    protected Item getDropItem() {
    	return VoidCraft.items.ectoplasm;
    }
    
}