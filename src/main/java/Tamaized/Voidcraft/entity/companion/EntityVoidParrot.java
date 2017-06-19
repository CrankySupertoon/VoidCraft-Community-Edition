package Tamaized.Voidcraft.entity.companion;

import Tamaized.Voidcraft.registry.VoidCraftBlocks;
import Tamaized.Voidcraft.registry.VoidCraftItems;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.passive.EntityShoulderRiding;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class EntityVoidParrot extends EntityShoulderRiding implements EntityFlying {

	private static final Set<Item> field_192016_bJ = Sets.newHashSet(VoidCraftItems.etherealSeed);
	private static final DataParameter<Integer> field_192013_bG = EntityDataManager.createKey(EntityVoidParrot.class, DataSerializers.VARINT);
	private static final java.util.Map<Class<? extends Entity>, SoundEvent> MIMIC_SOUNDS = Maps.newHashMapWithExpectedSize(32);
	private static final Predicate<EntityLiving> field_192014_bH = p_apply_1_ -> p_apply_1_ != null && MIMIC_SOUNDS.containsKey(p_apply_1_.getClass());

	static {
		registerMimicSound(EntityBlaze.class, SoundEvents.field_193791_eM);
		registerMimicSound(EntityCaveSpider.class, SoundEvents.field_193813_fc);
		registerMimicSound(EntityCreeper.class, SoundEvents.field_193792_eN);
		registerMimicSound(EntityElderGuardian.class, SoundEvents.field_193793_eO);
		registerMimicSound(EntityDragon.class, SoundEvents.field_193794_eP);
		registerMimicSound(EntityEnderman.class, SoundEvents.field_193795_eQ);
		registerMimicSound(EntityEndermite.class, SoundEvents.field_193796_eR);
		registerMimicSound(EntityEvoker.class, SoundEvents.field_193797_eS);
		registerMimicSound(EntityGhast.class, SoundEvents.field_193798_eT);
		registerMimicSound(EntityHusk.class, SoundEvents.field_193799_eU);
		registerMimicSound(EntityIllusionIllager.class, SoundEvents.field_193800_eV);
		registerMimicSound(EntityMagmaCube.class, SoundEvents.field_193801_eW);
		registerMimicSound(EntityPigZombie.class, SoundEvents.field_193822_fl);
		registerMimicSound(EntityPolarBear.class, SoundEvents.field_193802_eX);
		registerMimicSound(EntityShulker.class, SoundEvents.field_193803_eY);
		registerMimicSound(EntitySilverfish.class, SoundEvents.field_193804_eZ);
		registerMimicSound(EntitySkeleton.class, SoundEvents.field_193811_fa);
		registerMimicSound(EntitySlime.class, SoundEvents.field_193812_fb);
		registerMimicSound(EntitySpider.class, SoundEvents.field_193813_fc);
		registerMimicSound(EntityStray.class, SoundEvents.field_193814_fd);
		registerMimicSound(EntityVex.class, SoundEvents.field_193815_fe);
		registerMimicSound(EntityVindicator.class, SoundEvents.field_193816_ff);
		registerMimicSound(EntityWitch.class, SoundEvents.field_193817_fg);
		registerMimicSound(EntityWither.class, SoundEvents.field_193818_fh);
		registerMimicSound(EntityWitherSkeleton.class, SoundEvents.field_193819_fi);
		registerMimicSound(EntityWolf.class, SoundEvents.field_193820_fj);
		registerMimicSound(EntityZombie.class, SoundEvents.field_193821_fk);
		registerMimicSound(EntityZombieVillager.class, SoundEvents.field_193823_fm);
		// TODO: add in Void Mob sounds?
	}

	public float field_192008_bB;
	public float field_192009_bC;
	public float field_192010_bD;
	public float field_192011_bE;
	public float field_192012_bF = 1.0F;
	private boolean field_192018_bL;
	private BlockPos field_192019_bM;

	public EntityVoidParrot(World p_i47411_1_) {
		super(p_i47411_1_);
		this.setSize(0.5F, 0.9F);
		this.moveHelper = new EntityFlyHelper(this);
	}

	private static boolean func_192006_b(World p_192006_0_, Entity p_192006_1_) {
		if (!p_192006_1_.isSilent() && p_192006_0_.rand.nextInt(50) == 0) {
			List<EntityLiving> list = p_192006_0_.<EntityLiving>getEntitiesWithinAABB(EntityLiving.class, p_192006_1_.getEntityBoundingBox().expandXyz(20.0D), field_192014_bH);

			if (!list.isEmpty()) {
				EntityLiving entityliving = list.get(p_192006_0_.rand.nextInt(list.size()));

				if (!entityliving.isSilent()) {
					SoundEvent soundevent = MIMIC_SOUNDS.get(entityliving.getClass());
					p_192006_0_.playSound((EntityPlayer) null, p_192006_1_.posX, p_192006_1_.posY, p_192006_1_.posZ, soundevent, p_192006_1_.getSoundCategory(), 0.7F, func_192000_b(p_192006_0_.rand));
					return true;
				}
			}

			return false;
		} else {
			return false;
		}
	}

	public static void func_192005_a(World p_192005_0_, Entity p_192005_1_) {
		if (!p_192005_1_.isSilent() && !func_192006_b(p_192005_0_, p_192005_1_) && p_192005_0_.rand.nextInt(200) == 0) {
			p_192005_0_.playSound((EntityPlayer) null, p_192005_1_.posX, p_192005_1_.posY, p_192005_1_.posZ, func_192003_a(p_192005_0_.rand), p_192005_1_.getSoundCategory(), 1.0F, func_192000_b(p_192005_0_.rand));
		}
	}

	private static SoundEvent func_192003_a(Random p_192003_0_) {
		if (p_192003_0_.nextInt(1000) == 0) {
			List<SoundEvent> list = new ArrayList<SoundEvent>(MIMIC_SOUNDS.values());
			SoundEvent ret = list.get(p_192003_0_.nextInt(list.size()));
			return ret == null ? SoundEvents.field_192792_ep : ret;
		} else {
			return SoundEvents.field_192792_ep;
		}
	}

	private static float func_192000_b(Random p_192000_0_) {
		return (p_192000_0_.nextFloat() - p_192000_0_.nextFloat()) * 0.2F + 1.0F;
	}

	public static void registerMimicSound(Class<? extends Entity> cls, SoundEvent sound) {
		MIMIC_SOUNDS.put(cls, sound);
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack itemstack = player.getHeldItem(hand);

		if (!this.isTamed() && field_192016_bJ.contains(itemstack.getItem())) {
			if (!player.capabilities.isCreativeMode) {
				itemstack.shrink(1);
			}

			if (!this.isSilent()) {
				this.world.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.field_192797_eu, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
			}

			if (!this.world.isRemote) {
				if (this.rand.nextInt(10) == 0) {
					this.func_193101_c(player);
					this.playTameEffect(true);
					this.world.setEntityState(this, (byte) 7);
				} else {
					this.playTameEffect(false);
					this.world.setEntityState(this, (byte) 6);
				}
			}

			return true;
		} else {
			if (!this.world.isRemote && !this.func_192002_a() && this.isTamed() && this.isOwner(player)) {
				this.aiSit.setSitting(!this.isSitting());
			}

			return super.processInteract(player, hand);
		}
	}

	@Override
	public boolean getCanSpawnHere() {
		for (int x = -15; x <= 15; x++)
			for (int z = -15; z <= 15; z++)
				for (int y = -5; y <= 5; y++) {
					BlockPos pos = new BlockPos(MathHelper.floor(this.posX + x), MathHelper.floor(this.getEntityBoundingBox().minY + y), MathHelper.floor(this.posZ + z));
					IBlockState state = world.getBlockState(pos);
					if (state.getBlock() == VoidCraftBlocks.etherealPlant)
						return true;
				}
		return false;

	}

	/**
	 * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
	 * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
	 */
	@Nullable
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		this.func_191997_m(this.rand.nextInt(5));
		return super.onInitialSpawn(difficulty, livingdata);
	}

	@Override
	protected void initEntityAI() {
		this.aiSit = new EntityAISit(this);
		this.tasks.addTask(0, new EntityAIPanic(this, 1.25D));
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(2, new EntityAIFollowOwnerFlying(this, 1.0D, 5.0F, 1.0F));
		this.tasks.addTask(2, new EntityAIWanderAvoidWaterFlying(this, 1.0D));
		this.tasks.addTask(3, new EntityAILandOnOwnersShoulder(this));
		this.tasks.addTask(3, new EntityAIFollow(this, 1.0D, 3.0F, 7.0F));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.field_193334_e);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.field_193334_e).setBaseValue(0.4000000059604645D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
	}

	/**
	 * Returns new PathNavigateGround instance
	 */
	@Override
	protected PathNavigate createNavigator(World worldIn) {
		PathNavigateFlying pathnavigateflying = new PathNavigateFlying(this, worldIn);
		pathnavigateflying.func_192879_a(false);
		pathnavigateflying.func_192877_c(true);
		pathnavigateflying.func_192878_b(true);
		return pathnavigateflying;
	}

	@Override
	public float getEyeHeight() {
		return this.height * 0.6F;
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate() {
		func_192006_b(this.world, this);

		if (this.field_192019_bM == null || this.field_192019_bM.distanceSq(this.posX, this.posY, this.posZ) > 12.0D || this.world.getBlockState(this.field_192019_bM).getBlock() != Blocks.JUKEBOX) {
			this.field_192018_bL = false;
			this.field_192019_bM = null;
		}

		super.onLivingUpdate();
		this.func_192001_dv();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void func_191987_a(BlockPos p_191987_1_, boolean p_191987_2_) {
		this.field_192019_bM = p_191987_1_;
		this.field_192018_bL = p_191987_2_;
	}

	@SideOnly(Side.CLIENT)
	public boolean func_192004_dr() {
		return this.field_192018_bL;
	}

	private void func_192001_dv() {
		this.field_192011_bE = this.field_192008_bB;
		this.field_192010_bD = this.field_192009_bC;
		this.field_192009_bC = (float) ((double) this.field_192009_bC + (double) (this.onGround ? -1 : 4) * 0.3D);
		this.field_192009_bC = MathHelper.clamp(this.field_192009_bC, 0.0F, 1.0F);

		if (!this.onGround && this.field_192012_bF < 1.0F) {
			this.field_192012_bF = 1.0F;
		}

		this.field_192012_bF = (float) ((double) this.field_192012_bF * 0.9D);

		if (!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.6D;
		}

		this.field_192008_bB += this.field_192012_bF * 2.0F;
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
	 * the animal type)
	 */
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return false;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
	}

	/**
	 * Returns true if the mob is currently able to mate with the specified mob.
	 */
	@Override
	public boolean canMateWith(EntityAnimal otherAnimal) {
		return false;
	}

	@Nullable
	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
	}

	@Nullable
	@Override
	public SoundEvent getAmbientSound() {
		return func_192003_a(this.rand);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
		return SoundEvents.field_192794_er;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.field_192793_eq;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.field_192795_es, 0.15F, 1.0F);
	}

	@Override
	protected float func_191954_d(float p_191954_1_) {
		this.playSound(SoundEvents.field_192796_et, 0.15F, 1.0F);
		return p_191954_1_ + this.field_192009_bC / 2.0F;
	}

	@Override
	protected boolean func_191957_ae() {
		return true;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	@Override
	protected float getSoundPitch() {
		return func_192000_b(this.rand);
	}

	@Override
	public SoundCategory getSoundCategory() {
		return SoundCategory.NEUTRAL;
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	protected void collideWithEntity(Entity entityIn) {
		if (!(entityIn instanceof EntityPlayer)) {
			super.collideWithEntity(entityIn);
		}
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isEntityInvulnerable(source)) {
			return false;
		} else {
			if (this.aiSit != null) {
				this.aiSit.setSitting(false);
			}

			return super.attackEntityFrom(source, amount);
		}
	}

	public int func_191998_ds() {
		return MathHelper.clamp(((Integer) this.dataManager.get(field_192013_bG)).intValue(), 0, 4);
	}

	public void func_191997_m(int p_191997_1_) {
		this.dataManager.set(field_192013_bG, Integer.valueOf(p_191997_1_));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(field_192013_bG, Integer.valueOf(0));
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("Variant", this.func_191998_ds());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.func_191997_m(compound.getInteger("Variant"));
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootTableList.field_192561_ax;
	}

	public boolean func_192002_a() {
		return !this.onGround;
	}
}
