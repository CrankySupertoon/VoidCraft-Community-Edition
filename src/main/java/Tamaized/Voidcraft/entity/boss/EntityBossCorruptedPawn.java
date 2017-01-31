package Tamaized.Voidcraft.entity.boss;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.boss.render.bossBar.IVoidBossData;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
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
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;


public class EntityBossCorruptedPawn extends EntityBossCorruptedPawnBase implements IVoidBossData{
	
	private boolean hasExploded = false;
	
	public EntityBossCorruptedPawn(World p_i1738_1_) {
		super(p_i1738_1_);
		
		this.isImmuneToFire = true;
		this.setSize(0.9F, 3.0F);
		this.experienceValue = 5000;
		
		//Begin Spawn Sequence
		this.setInvul(true);
		this.setHealth(1);
	}
	
	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound p_70014_1_){
		super.writeEntityToNBT(p_70014_1_);
		p_70014_1_.setFloat("Health", this.getHealth());
		p_70014_1_.setBoolean("HasExploded", this.hasExploded);
		p_70014_1_.setBoolean("Invul", this.isEntityInvulnerable());
	}
	
	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound p_70037_1_){
		super.readEntityFromNBT(p_70037_1_);
		
		this.setHealth(p_70037_1_.getFloat("Health"));
		this.setInvul(p_70037_1_.getBoolean("Invul"));
		this.hasExploded = p_70037_1_.getBoolean("HasExploded");
		
		if(hasExploded) BeginAI();
	}
	
	private void BeginAI(){
		//this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIBreakDoor(this));
        this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityGolem.class, true)); //IronGolem and Snowman extend this
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, true)); //Normal Minecraft mobs
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityAnimal.class, true)); //Passive animals
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySlime.class, true)); //Slime extends living so need to add it manually
	}
	
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1000.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.50000000298023224D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(100.0D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
	}
	
	@Override
	public void onLivingUpdate(){	
		//The Spawn Explosion stuff
		if(!hasExploded){
			if(this.getHealth() < this.getMaxHealth()){
				if (this.ticksExisted % 10 == 0){
					this.heal(30.0F);
				}
			}
			
			if(this.getHealth() >= this.getMaxHealth()){
				this.hasExploded = true;
				this.world.newExplosion(this, this.posX, this.posY + (double)this.getEyeHeight(), this.posZ, 7.0F, false, this.world.getGameRules().getBoolean("mobGriefing"));
				this.world.playBroadcastSound(1013, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 0);
				this.BeginAI();
				this.setInvul(false);
			}
		}/*else{
    		EntityLivingBase target = this.getAITarget();
        	
        	//If target is higher, begin flight
        	if(target != null && target.posY > this.posY){
        		if (this.motionY < 0.0D){
                    this.motionY = 0.0D;
                }
	
                this.motionY += (0.5D - this.motionY) * 0.4000000238418579D;
        	}
    	}
		 */
		
		super.onLivingUpdate();
	}
    
    @Override
    protected SoundEvent getAmbientSound(){
    	return VoidSoundEvents.EntityMobVoidBossSoundEvents.ambientSound;
    }
    
    @Override
    protected SoundEvent getHurtSound(){
    	return VoidSoundEvents.EntityMobVoidBossSoundEvents.hurtSound;
    }
    
    @Override
    protected SoundEvent getDeathSound(){
    	return VoidSoundEvents.EntityMobVoidBossSoundEvents.deathSound;
    }
	
    @Override
    protected float getSoundVolume(){
        return 0.4F;
    }

    @Override
    protected Item getDropItem() {
        return VoidCraft.items.voidStar;
    }

    @Override
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_){
    	this.dropItem(getDropItem(), 1);
    }
    
	@Override
	public float getPercentHPForBossBar() {
		return this.getHealth()/this.getMaxHealth();
	}

	@Override
	public ITextComponent getNameForBossBar() {
		return new TextComponentString("Corrupted Pawn");
	}

	@Override
	public float getMaxHealthForBossBar() {
		return getMaxHealth();
	}

	@Override
	public float getHealthForBossBar() {
		return getHealth();
	}
	
	
	
	
	
	
	
	
}