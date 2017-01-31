package Tamaized.Voidcraft.entity.boss.herobrine.extra;

import javax.annotation.Nullable;

import Tamaized.Voidcraft.entity.boss.herobrine.extra.ai.EntityAIHerobrineCreeperSwell;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityHerobrineCreeper extends EntityMob {
	private static final DataParameter<Integer> STATE = EntityDataManager.<Integer> createKey(EntityCreeper.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> POWERED = EntityDataManager.<Boolean> createKey(EntityCreeper.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IGNITED = EntityDataManager.<Boolean> createKey(EntityCreeper.class, DataSerializers.BOOLEAN);
	/**
	 * Time when this creeper was last in an active state (Messed up code here, probably causes creeper animation to go weird)
	 */
	private int lastActiveTime;
	/** The amount of time since the creeper was close enough to the player to ignite */
	private int timeSinceIgnited;
	private int fuseTime = 30;
	/** Explosion radius for this creeper. */
	private int explosionRadius = 7;
	private int droppedSkulls;

	public EntityHerobrineCreeper(World worldIn) {
		super(worldIn);
		setSize(0.6F, 1.7F);
	}

	protected void initEntityAI() {
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIHerobrineCreeperSwell(this));
		tasks.addTask(3, new EntityAIAvoidEntity(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
		tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
		tasks.addTask(5, new EntityAIWander(this, 0.8D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.50D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
	}

	/**
	 * The maximum height from where the entity is alowed to jump (used in pathfinder)
	 */
	public int getMaxFallHeight() {
		return getAttackTarget() == null ? 3 : 3 + (int) (getHealth() - 1.0F);
	}

	public void fall(float distance, float damageMultiplier) {
		super.fall(distance, damageMultiplier);
		timeSinceIgnited = (int) ((float) timeSinceIgnited + distance * 1.5F);

		if (timeSinceIgnited > fuseTime - 5) {
			timeSinceIgnited = fuseTime - 5;
		}
	}

	protected void entityInit() {
		super.entityInit();
		dataManager.register(STATE, Integer.valueOf(-1));
		dataManager.register(POWERED, Boolean.valueOf(true));
		dataManager.register(IGNITED, Boolean.valueOf(false));
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		if (((Boolean) dataManager.get(POWERED)).booleanValue()) {
			compound.setBoolean("powered", true);
		}

		compound.setShort("Fuse", (short) fuseTime);
		compound.setByte("ExplosionRadius", (byte) explosionRadius);
		compound.setBoolean("ignited", hasIgnited());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		dataManager.set(POWERED, Boolean.valueOf(compound.getBoolean("powered")));

		if (compound.hasKey("Fuse", 99)) {
			fuseTime = compound.getShort("Fuse");
		}

		if (compound.hasKey("ExplosionRadius", 99)) {
			explosionRadius = compound.getByte("ExplosionRadius");
		}

		if (compound.getBoolean("ignited")) {
			ignite();
		}
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		if (isEntityAlive()) {
			lastActiveTime = timeSinceIgnited;

			if (hasIgnited()) {
				setCreeperState(1);
			}

			int i = getCreeperState();

			if (i > 0 && timeSinceIgnited == 0) {
				playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
			}

			timeSinceIgnited += i;

			if (timeSinceIgnited < 0) {
				timeSinceIgnited = 0;
			}

			if (timeSinceIgnited >= fuseTime) {
				timeSinceIgnited = fuseTime;
				explode();
			}
		}

		super.onUpdate();
	}

	protected SoundEvent getHurtSound() {
		return SoundEvents.ENTITY_CREEPER_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_CREEPER_DEATH;
	}

	/**
	 * Called when the mob's health reaches 0.
	 */
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
	}

	public boolean attackEntityAsMob(Entity entityIn) {
		return true;
	}

	/**
	 * Returns true if the creeper is powered by a lightning bolt.
	 */
	public boolean getPowered() {
		return ((Boolean) dataManager.get(POWERED)).booleanValue();
	}

	/**
	 * Params: (Float)Render tick. Returns the intensity of the creeper's flash when it is ignited.
	 */
	@SideOnly(Side.CLIENT)
	public float getCreeperFlashIntensity(float p_70831_1_) {
		return ((float) lastActiveTime + (float) (timeSinceIgnited - lastActiveTime) * p_70831_1_) / (float) (fuseTime - 2);
	}

	@Nullable
	protected ResourceLocation getLootTable() {
		return LootTableList.ENTITIES_CREEPER;
	}

	/**
	 * Returns the current state of creeper, -1 is idle, 1 is 'in fuse'
	 */
	public int getCreeperState() {
		return ((Integer) dataManager.get(STATE)).intValue();
	}

	/**
	 * Sets the state of creeper, -1 to idle and 1 to be 'in fuse'
	 */
	public void setCreeperState(int state) {
		dataManager.set(STATE, Integer.valueOf(state));
	}

	/**
	 * Called when a lightning bolt hits the entity.
	 */
	public void onStruckByLightning(EntityLightningBolt lightningBolt) {
		super.onStruckByLightning(lightningBolt);
		dataManager.set(POWERED, Boolean.valueOf(true));
	}

	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!stack.isEmpty() && stack.getItem() == Items.FLINT_AND_STEEL) {
			world.playSound(player, posX, posY, posZ, SoundEvents.ITEM_FLINTANDSTEEL_USE, getSoundCategory(), 1.0F, rand.nextFloat() * 0.4F + 0.8F);
			player.swingArm(hand);

			if (!world.isRemote) {
				ignite();
				stack.damageItem(1, player);
				return true;
			}
		}

		return super.processInteract(player, hand);
	}

	/**
	 * Creates an explosion as determined by this creeper's power and explosion radius.
	 */
	private void explode() {
		if (!world.isRemote) {
			dead = true;
			world.createExplosion(this, posX, posY, posZ, (float) explosionRadius, false);
			setDead();
		}
	}

	public boolean hasIgnited() {
		return ((Boolean) dataManager.get(IGNITED)).booleanValue();
	}

	public void ignite() {
		dataManager.set(IGNITED, Boolean.valueOf(true));
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled() {
		return droppedSkulls < 1 && world.getGameRules().getBoolean("doMobLoot");
	}

	public void incrementDroppedSkulls() {
		++droppedSkulls;
	}
}