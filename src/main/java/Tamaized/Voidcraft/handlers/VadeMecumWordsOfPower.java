package Tamaized.Voidcraft.handlers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import Tamaized.TamModized.TamModized;
import Tamaized.TamModized.helper.RayTraceHelper;
import Tamaized.TamModized.particles.ParticleHelper;
import Tamaized.TamModized.particles.ParticlePacketHandlerRegistry;
import Tamaized.TamModized.particles.FX.network.ParticleFluffPacketHandler;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.blocks.spell.BlockSpellIceSpike;
import Tamaized.Voidcraft.blocks.spell.tileentity.TileEntitySpellIceSpike;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability.Category;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability.CategoryDataWrapper;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import Tamaized.Voidcraft.damageSources.DamageSourceAcid;
import Tamaized.Voidcraft.damageSources.DamageSourceLit;
import Tamaized.Voidcraft.damageSources.DamageSourceVoidicInfusion;
import Tamaized.Voidcraft.entity.nonliving.EntityCasterLightningBolt;
import Tamaized.Voidcraft.entity.nonliving.EntitySpellImplosion;
import Tamaized.Voidcraft.entity.nonliving.EntitySpellRune;
import Tamaized.Voidcraft.entity.nonliving.ProjectileDisintegration;
import Tamaized.Voidcraft.helper.ExplosionDamageHelper;
import Tamaized.Voidcraft.helper.SheatheHelper;
import Tamaized.Voidcraft.potion.PotionSheathe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class VadeMecumWordsOfPower {

	private static final Map<Category, CategoryDataWrapper> categoryMap = new HashMap<Category, CategoryDataWrapper>();
	private static final CategoryDataWrapper NullWrapper = new CategoryDataWrapper(CategoryDataWrapper.Element.NULL, "null", null);

	static {
		categoryMap.put(IVadeMecumCapability.Category.INTRO, new CategoryDataWrapper(CategoryDataWrapper.Element.NULL, "Rituals", null));
		categoryMap.put(IVadeMecumCapability.Category.TOME, new CategoryDataWrapper(CategoryDataWrapper.Element.NULL, "Words of Power", null));

		categoryMap.put(IVadeMecumCapability.Category.Flame, new CategoryDataWrapper(CategoryDataWrapper.Element.FIRE, "Word: Flame", new ItemStack(Items.FLINT)));
		categoryMap.put(IVadeMecumCapability.Category.FireSheathe, new CategoryDataWrapper(CategoryDataWrapper.Element.FIRE, "Word: Sheathe - Fire", new ItemStack(Items.MAGMA_CREAM)));
		categoryMap.put(IVadeMecumCapability.Category.Fireball, new CategoryDataWrapper(CategoryDataWrapper.Element.FIRE, "Word: Fireball", new ItemStack(Items.FIRE_CHARGE)));
		categoryMap.put(IVadeMecumCapability.Category.FireTrap, new CategoryDataWrapper(CategoryDataWrapper.Element.FIRE, "Word: Trap - Fire", new ItemStack(Blocks.MAGMA)));
		categoryMap.put(IVadeMecumCapability.Category.ExplosionFire, new CategoryDataWrapper(CategoryDataWrapper.Element.FIRE, "Word: Explosion - Fire", new ItemStack(Blocks.TNT)));
		categoryMap.put(IVadeMecumCapability.Category.RingOfFire, new CategoryDataWrapper(CategoryDataWrapper.Element.FIRE, "Word: Ring of Fire", new ItemStack(Items.GUNPOWDER)));

		categoryMap.put(IVadeMecumCapability.Category.Shock, new CategoryDataWrapper(CategoryDataWrapper.Element.AIR, "Word: Shock", new ItemStack(Items.FEATHER)));
		categoryMap.put(IVadeMecumCapability.Category.ShockSheathe, new CategoryDataWrapper(CategoryDataWrapper.Element.AIR, "Word: Sheathe - Shock", new ItemStack(Blocks.CARPET)));
		categoryMap.put(IVadeMecumCapability.Category.LitStrike, new CategoryDataWrapper(CategoryDataWrapper.Element.AIR, "Word: Lightning Strike", new ItemStack(Blocks.END_ROD)));
		categoryMap.put(IVadeMecumCapability.Category.LitTrap, new CategoryDataWrapper(CategoryDataWrapper.Element.AIR, "Word: Trap - Lightning", new ItemStack(Items.GHAST_TEAR)));
		categoryMap.put(IVadeMecumCapability.Category.ExplosionLit, new CategoryDataWrapper(CategoryDataWrapper.Element.AIR, "Word: Explosion - Shock", new ItemStack(Items.CHORUS_FRUIT_POPPED)));
		categoryMap.put(IVadeMecumCapability.Category.RingOfLit, new CategoryDataWrapper(CategoryDataWrapper.Element.AIR, "Word: Ring of Lightning", new ItemStack(Blocks.GLOWSTONE)));

		categoryMap.put(IVadeMecumCapability.Category.Freeze, new CategoryDataWrapper(CategoryDataWrapper.Element.WATER, "Word: Freeze", new ItemStack(Items.SNOWBALL)));
		categoryMap.put(IVadeMecumCapability.Category.FrostSheathe, new CategoryDataWrapper(CategoryDataWrapper.Element.WATER, "Word: Sheathe - Frost", new ItemStack(Blocks.SNOW)));
		categoryMap.put(IVadeMecumCapability.Category.IceSpike, new CategoryDataWrapper(CategoryDataWrapper.Element.WATER, "Word: Ice Spike", new ItemStack(Blocks.ICE)));
		categoryMap.put(IVadeMecumCapability.Category.FrostTrap, new CategoryDataWrapper(CategoryDataWrapper.Element.WATER, "Word: Trap - Frost", new ItemStack(Blocks.PRISMARINE)));
		categoryMap.put(IVadeMecumCapability.Category.ExplosionFrost, new CategoryDataWrapper(CategoryDataWrapper.Element.WATER, "Word: Explosion - Frost", new ItemStack(Items.SPLASH_POTION)));
		categoryMap.put(IVadeMecumCapability.Category.RingOfFrost, new CategoryDataWrapper(CategoryDataWrapper.Element.WATER, "Word: Ring of Frost", new ItemStack(Items.PRISMARINE_CRYSTALS)));

		categoryMap.put(IVadeMecumCapability.Category.AcidSpray, new CategoryDataWrapper(CategoryDataWrapper.Element.EARTH, "Word: Acid Spray", new ItemStack(Blocks.DIRT)));
		categoryMap.put(IVadeMecumCapability.Category.AcidSheathe, new CategoryDataWrapper(CategoryDataWrapper.Element.EARTH, "Word: Sheathe - Acid", new ItemStack(Items.SLIME_BALL)));
		categoryMap.put(IVadeMecumCapability.Category.Disint, new CategoryDataWrapper(CategoryDataWrapper.Element.EARTH, "Word: Disintegrate", new ItemStack(Blocks.MOSSY_COBBLESTONE)));
		categoryMap.put(IVadeMecumCapability.Category.AcidTrap, new CategoryDataWrapper(CategoryDataWrapper.Element.EARTH, "Word: Trap - Acid", new ItemStack(Blocks.SLIME_BLOCK)));
		categoryMap.put(IVadeMecumCapability.Category.ExplosionAcid, new CategoryDataWrapper(CategoryDataWrapper.Element.EARTH, "Word: Explosion - Acid", new ItemStack(Items.POISONOUS_POTATO)));
		categoryMap.put(IVadeMecumCapability.Category.RingOfAcid, new CategoryDataWrapper(CategoryDataWrapper.Element.EARTH, "Word: Ring of Acid", new ItemStack(Items.FISH, 1, 3)));

		categoryMap.put(IVadeMecumCapability.Category.VoidicTouch, new CategoryDataWrapper(CategoryDataWrapper.Element.VOID, "Word: Voidic Touch", new ItemStack(VoidCraft.items.voidcrystal)));
		categoryMap.put(IVadeMecumCapability.Category.VoidicSheathe, new CategoryDataWrapper(CategoryDataWrapper.Element.VOID, "Word: Sheathe - Voidic", new ItemStack(VoidCraft.blocks.blockVoidbrick)));
		categoryMap.put(IVadeMecumCapability.Category.Implosion, new CategoryDataWrapper(CategoryDataWrapper.Element.VOID, "Word: Implosion", new ItemStack(VoidCraft.blocks.realityHole)));
	}

	public static CategoryDataWrapper getCategoryData(Category c) {
		return c == null ? NullWrapper : categoryMap.containsKey(c) ? categoryMap.get(c) : NullWrapper;
	}

	public static void invoke(World world, EntityPlayer caster) { // TODO: clean all this up, make methods/classes/helpers and so on for all this junk
		IVadeMecumCapability cap = caster.getCapability(CapabilityList.VADEMECUM, null);
		if (cap == null || world.isRemote) return;
		IVadeMecumCapability.Category power = cap.getCurrentActive();
		if (cap.getStackInSlot(power) == null || cap.getStackInSlot(power).stackSize <= 0) {
			caster.sendMessage(new TextComponentString("Not enough spell material to cast " + getCategoryData(power).getName()));
			return;
		}
		boolean useCharge = false;
		power = cap.getCurrentActive();
		if (power != null && cap.hasCategory(power)) {
			boolean doCast = false;
			CategoryDataWrapper wrapper = getCategoryData(power);
			if (wrapper != null && wrapper != NullWrapper) {
				if (wrapper.getElement() != CategoryDataWrapper.Element.VOID) {
					int rand = world.rand.nextInt(100) + 1;
					if (rand <= cap.getFailureChance()) {
						ExplosionDamageHelper.explode(null, world, world.newExplosion(null, caster.posX, caster.posY, caster.posZ, 7.0F, true, true), 7.0F, caster.posX, caster.posY, caster.posZ);
						world.playBroadcastSound(1023, caster.getPosition(), 0);
						useCharge = true;
					} else {
						doCast = true;
					}
				}
			}
			if (doCast) {
				HashSet<Entity> exclude = new HashSet<Entity>();
				RayTraceResult result;
				switch (power) {
					case Flame: {
						exclude.add(caster);
						result = RayTraceHelper.tracePath(world, caster, 32, 1, exclude);
						if (result != null) {
							if (result.entityHit != null) {
								result.entityHit.setFire(10);
							} else {
								BlockPos pos = result.getBlockPos();
								switch (result.sideHit) {
									default:
									case UP: {
										pos = pos.add(0, 1, 0);
									}
										break;
									case DOWN: {
										pos = pos.add(0, -1, 0);
									}
										break;
									case NORTH: {
										pos = pos.add(0, 0, -1);
									}
										break;
									case SOUTH: {
										pos = pos.add(0, 0, 1);
									}
										break;
									case EAST: {
										pos = pos.add(1, 0, 0);
									}
										break;
									case WEST: {
										pos = pos.add(-1, 0, 0);
									}
										break;
								}
								if (world.isAirBlock(pos)) world.setBlockState(pos, Blocks.FIRE.getDefaultState());
							}
						}
						useCharge = true;
					}
						break;
					case FireSheathe: {
						SheatheHelper.castSheathe(caster, PotionSheathe.Type.Fire, 20 * 90);
						useCharge = true;
					}
						break;
					case Fireball: {
						Vec3d vec = caster.getLookVec();
						EntityFireball entity = new EntityLargeFireball(world, caster, vec.xCoord, vec.yCoord, vec.zCoord);
						entity.shootingEntity = caster;
						world.spawnEntity(entity);
						useCharge = true;
					}
						break;
					case FireTrap: {
						useCharge = castRune(world, caster, new EntitySpellRune(world, EntitySpellRune.DamageType.FIRE, 10, 5, 0xFF5733));
					}
						break;
					case ExplosionFire: {
						world.createExplosion(caster, caster.posX, caster.posY, caster.posZ, 10.0F, false);
						useCharge = true;
					}
						break;
					case RingOfFire: {
						for (BlockPos pos : createCircle(caster.getPosition()))
							if ((world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isReplaceable(world, pos)) && (!world.isAirBlock(pos.down()) && world.isBlockFullCube(pos.down()))) world.setBlockState(pos, Blocks.FIRE.getDefaultState());
						useCharge = true;
					}
						break;
					case Shock: {
						exclude.add(caster);
						RayTraceResult ray = RayTraceHelper.tracePath(world, caster, 2, 1, exclude);
						if (ray != null && ray.entityHit != null && ray.entityHit instanceof EntityLivingBase) {
							((EntityLivingBase) ray.entityHit).attackEntityFrom(new DamageSourceLit(), 5);
							useCharge = true;
						}
					}
						break;
					case ShockSheathe: {
						SheatheHelper.castSheathe(caster, PotionSheathe.Type.Lit, 20 * 90);
						useCharge = true;
					}
						break;
					case LitStrike: {
						exclude.add(caster);
						result = RayTraceHelper.tracePath(world, caster, 32, 1, exclude);
						if (result != null) {
							if (result.entityHit != null) {
								EntityCasterLightningBolt entitylightningbolt = new EntityCasterLightningBolt(world, caster, result.entityHit.posX, result.entityHit.posY, result.entityHit.posZ, false);
								world.addWeatherEffect(entitylightningbolt);
							} else {
								BlockPos bp = result.getBlockPos();
								if (bp != null) {
									EntityCasterLightningBolt entitylightningbolt = new EntityCasterLightningBolt(world, caster, bp.getX(), bp.getY() + 1, bp.getZ(), false);
									world.addWeatherEffect(entitylightningbolt);
								}
							}
							useCharge = true;
						}
					}
						break;
					case LitTrap: {
						useCharge = castRune(world, caster, new EntitySpellRune(world, EntitySpellRune.DamageType.SHOCK, 10, 5, 0xFFFFFF));
					}
						break;
					case ExplosionLit: {
						List<Entity> damageList = world.getEntitiesWithinAABBExcludingEntity(caster, new AxisAlignedBB(caster.posX - 5, caster.posY - 5, caster.posZ - 5, caster.posX + 5, caster.posY + 5, caster.posZ + 5));
						for (Entity e : damageList) {
							if (!(e instanceof EntityLivingBase)) continue;
							((EntityLivingBase) e).attackEntityFrom(new DamageSourceLit(), 10);
						}
						for (int index = 0; index < 1000; index++) {
							ParticleHelper.sendPacketToClients(world, TamModized.particles.fluff, caster.getPositionVector().addVector(0.5, 0, 0.5), 64, new ParticleHelper.ParticlePacketHelper(TamModized.particles.fluff, ((ParticleFluffPacketHandler) ParticlePacketHandlerRegistry.getHandler(TamModized.particles.fluff)).new ParticleFluffData(new Vec3d(world.rand.nextDouble() * 0.8D - 0.4D, world.rand.nextDouble() * 0.8D - 0.4D, world.rand.nextDouble() * 0.8D - 0.4D), world.rand.nextInt(20 * 3), -0.10f, world.rand.nextFloat() * 0.95f + 0.05f, 0xFFFFFFFF)));
						}
						useCharge = true;
					}
						break;
					case RingOfLit: {
						for (BlockPos pos : createCircle(caster.getPosition())) {
							EntityCasterLightningBolt entitylightningbolt = new EntityCasterLightningBolt(world, caster, pos.getX(), pos.getY(), pos.getZ(), false);
							world.addWeatherEffect(entitylightningbolt);
						}
						useCharge = true;
					}
						break;
					case Freeze: {
						exclude.add(caster);
						RayTraceResult ray = RayTraceHelper.tracePath(world, caster, 2, 1, exclude);
						if (ray != null && ray.entityHit != null && ray.entityHit instanceof EntityLivingBase) {
							((EntityLivingBase) ray.entityHit).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * 7, 5));
							useCharge = true;
						}
					}
						break;
					case FrostSheathe: {
						SheatheHelper.castSheathe(caster, PotionSheathe.Type.Frost, 20 * 90);
						useCharge = true;
					}
						break;
					case IceSpike: {
						exclude.add(caster);
						result = RayTraceHelper.tracePath(world, caster, 32, 1, exclude);
						if (result != null) {
							if (result.entityHit != null) {
								BlockPos pos = result.entityHit.getPosition();
								if ((world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isReplaceable(world, pos)) && (!world.isAirBlock(pos.down()) && world.getBlockState(pos.down()).isFullCube())) {
									world.setBlockState(pos, VoidCraft.blocks.iceSpike.getDefaultState().withProperty(BlockSpellIceSpike.FACING, EnumFacing.getHorizontal(world.rand.nextInt(4))));
									TileEntity te = world.getTileEntity(pos);
									if (te instanceof TileEntitySpellIceSpike) ((TileEntitySpellIceSpike) te).setCaster(caster);
								}
							} else {
								BlockPos bp = result.getBlockPos();
								if (bp != null) {
									if (world.getBlockState(bp).getBlock().isReplaceable(world, bp)) {
										world.setBlockState(bp, VoidCraft.blocks.iceSpike.getDefaultState().withProperty(BlockSpellIceSpike.FACING, EnumFacing.getHorizontal(world.rand.nextInt(4))));
										TileEntity te = world.getTileEntity(bp);
										if (te instanceof TileEntitySpellIceSpike) ((TileEntitySpellIceSpike) te).setCaster(caster);
									} else if ((world.isAirBlock(bp.up()) || world.getBlockState(bp.up()).getBlock().isReplaceable(world, bp.up())) && (!world.isAirBlock(bp) && world.getBlockState(bp).isFullCube())) {
										world.setBlockState(bp.up(), VoidCraft.blocks.iceSpike.getDefaultState().withProperty(BlockSpellIceSpike.FACING, EnumFacing.getHorizontal(world.rand.nextInt(4))));
										TileEntity te = world.getTileEntity(bp.up());
										if (te instanceof TileEntitySpellIceSpike) ((TileEntitySpellIceSpike) te).setCaster(caster);
									}
								}
							}
							useCharge = true;
						}
					}
						break;
					case FrostTrap: {
						useCharge = castRune(world, caster, new EntitySpellRune(world, EntitySpellRune.DamageType.FROST, 10, 5, 0x00FFFF));
					}
						break;
					case ExplosionFrost: {
						List<Entity> damageList = world.getEntitiesWithinAABBExcludingEntity(caster, new AxisAlignedBB(caster.posX - 5, caster.posY - 5, caster.posZ - 5, caster.posX + 5, caster.posY + 5, caster.posZ + 5));
						for (Entity e : damageList) {
							if (!(e instanceof EntityLivingBase)) continue;
							((EntityLivingBase) e).attackEntityFrom(new DamageSourceLit(), 10);
							((EntityLivingBase) e).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * 7, 5));
						}
						for (int index = 0; index < 1000; index++) {
							ParticleHelper.sendPacketToClients(world, TamModized.particles.fluff, caster.getPositionVector().addVector(0.5, 0, 0.5), 64, new ParticleHelper.ParticlePacketHelper(TamModized.particles.fluff, ((ParticleFluffPacketHandler) ParticlePacketHandlerRegistry.getHandler(TamModized.particles.fluff)).new ParticleFluffData(new Vec3d(world.rand.nextDouble() * 0.8D - 0.4D, world.rand.nextDouble() * 0.8D - 0.4D, world.rand.nextDouble() * 0.8D - 0.4D), world.rand.nextInt(20 * 3), -0.10f, world.rand.nextFloat() * 0.95f + 0.05f, 0x00FFFFFF)));
						}
						useCharge = true;
					}
						break;
					case RingOfFrost: {
						for (BlockPos pos : createCircle(caster.getPosition()))
							if ((world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isReplaceable(world, pos)) && (!world.isAirBlock(pos.down()) && world.getBlockState(pos.down()).isFullCube())) {
								world.setBlockState(pos, VoidCraft.blocks.iceSpike.getDefaultState().withProperty(BlockSpellIceSpike.FACING, EnumFacing.getHorizontal(world.rand.nextInt(4))));
								TileEntity te = world.getTileEntity(pos);
								if (te instanceof TileEntitySpellIceSpike) ((TileEntitySpellIceSpike) te).setCaster(caster);
							}
						useCharge = true;
					}
						break;
					case AcidSpray: {
						Vec3d vec = caster.getLook(1f);
						int damageRange = 5;
						List<Entity> damageList = world.getEntitiesWithinAABBExcludingEntity(caster, new AxisAlignedBB(caster.posX - (vec.xCoord * damageRange), caster.posY - (vec.yCoord * damageRange), caster.posZ - (vec.zCoord * damageRange), caster.posX + (vec.xCoord * damageRange), caster.posY + (vec.yCoord * damageRange), caster.posZ + (vec.zCoord * damageRange)));
						for (Entity e : damageList) {
							e.attackEntityFrom(new DamageSourceAcid(), 5);
						}
						for (int index = 0; index < 300; index++) {
							ParticleHelper.sendPacketToClients(world, TamModized.particles.fluff, caster.getPositionVector().addVector(0, 1.5f, 0), 64, new ParticleHelper.ParticlePacketHelper(TamModized.particles.fluff, ((ParticleFluffPacketHandler) ParticlePacketHandlerRegistry.getHandler(TamModized.particles.fluff)).new ParticleFluffData(new Vec3d(vec.xCoord * 0.15 + (world.rand.nextFloat() * 0.10) - 0.05, vec.yCoord * 0.15 + (world.rand.nextFloat() * 0.10) - 0.05, vec.zCoord * 0.15 + (world.rand.nextFloat() * 0.10) - 0.05), world.rand.nextInt(20 * 3), 0, world.rand.nextFloat() * 0.45f + 0.05f, 0x00FF00FF)));
						}
						useCharge = true;
					}
						break;
					case AcidSheathe: {
						SheatheHelper.castSheathe(caster, PotionSheathe.Type.Acid, 20 * 90);
						useCharge = true;
					}
						break;
					case Disint: {
						ProjectileDisintegration disint = new ProjectileDisintegration(world, caster, caster.posX, caster.posY, caster.posZ);
						disint.setDamageRangeSpeed(15, 0.0F, 0.5D);
						world.spawnEntity(disint);
						useCharge = true;
					}
						break;
					case AcidTrap: {
						useCharge = castRune(world, caster, new EntitySpellRune(world, EntitySpellRune.DamageType.ACID, 10, 5, 0x00FF00));
					}
						break;
					case ExplosionAcid: {
						List<Entity> damageList = world.getEntitiesWithinAABBExcludingEntity(caster, new AxisAlignedBB(caster.posX - 5, caster.posY - 5, caster.posZ - 5, caster.posX + 5, caster.posY + 5, caster.posZ + 5));
						for (Entity e : damageList) {
							if (!(e instanceof EntityLivingBase)) continue;
							((EntityLivingBase) e).attackEntityFrom(new DamageSourceLit(), 10);
						}
						for (int index = 0; index < 1000; index++) {
							ParticleHelper.sendPacketToClients(world, TamModized.particles.fluff, caster.getPositionVector().addVector(0.5, 0, 0.5), 64, new ParticleHelper.ParticlePacketHelper(TamModized.particles.fluff, ((ParticleFluffPacketHandler) ParticlePacketHandlerRegistry.getHandler(TamModized.particles.fluff)).new ParticleFluffData(new Vec3d(world.rand.nextDouble() * 0.8D - 0.4D, world.rand.nextDouble() * 0.8D - 0.4D, world.rand.nextDouble() * 0.8D - 0.4D), world.rand.nextInt(20 * 3), -0.10f, world.rand.nextFloat() * 0.95f + 0.05f, 0x00FF00FF)));
						}
						useCharge = true;
					}
						break;
					case RingOfAcid: {
						for (BlockPos pos : createCircle(caster.getPosition()))
							if ((world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isReplaceable(world, pos)) && (!world.isAirBlock(pos.down()) && world.getBlockState(pos.down()).isFullCube())) {
								world.setBlockState(pos, VoidCraft.fluids.acidFluidBlock.getDefaultState());
							}
						useCharge = true;
					}
						break;
					case VoidicTouch: {
						exclude.add(caster);
						RayTraceResult ray = RayTraceHelper.tracePath(world, caster, 2, 1, exclude);
						if (ray != null && ray.entityHit != null && ray.entityHit instanceof EntityLivingBase) {
							((EntityLivingBase) ray.entityHit).attackEntityFrom(new DamageSourceVoidicInfusion(), 5);
							IVoidicInfusionCapability inf = ((EntityLivingBase) ray.entityHit).getCapability(CapabilityList.VOIDICINFUSION, null);
							if (inf != null) inf.addInfusion(600);
							useCharge = true;
						}
					}
						break;
					case VoidicSheathe: {
						SheatheHelper.castSheathe(caster, PotionSheathe.Type.Void, 20 * 90);
						useCharge = true;
					}
						break;
					case Implosion: {
						List<Entity> damageList = world.getEntitiesWithinAABBExcludingEntity(caster, new AxisAlignedBB(caster.posX - 5, caster.posY - 5, caster.posZ - 5, caster.posX + 5, caster.posY + 5, caster.posZ + 5));
						for (Entity e : damageList) {
							if (!(e instanceof EntityLivingBase)) continue;
							world.spawnEntity(new EntitySpellImplosion(world, e));
						}
						useCharge = true;
					}
						break;
					default: {
					}
						break;
				}
			}
		}

		if (useCharge) {
			ItemStack stack = cap.getStackInSlot(power);
			stack.stackSize -= (1);
			if (stack.stackSize <= 0) cap.setStackSlot(power, null);
		}
	}

	public static void invoke(World world, IVadeMecumCapability.Category power, EntityLivingBase caster, EntityLivingBase target) {
		if (world.isRemote) return;
		if (power != null) {
			switch (power) {
				case Flame: {
					target.setFire(10);
					BlockPos pos = target.getPosition();
					if (world.isAirBlock(pos)) world.setBlockState(pos, Blocks.FIRE.getDefaultState());
				}
					break;
				case FireSheathe: {
					SheatheHelper.castSheathe(caster, PotionSheathe.Type.Fire, 20 * 90);
				}
					break;
				case Fireball: {
					double d5 = target.posX - caster.posX;
					double d6 = target.getEntityBoundingBox().minY + (double) (target.height / 2.0F) - (caster.posY + (double) (caster.height / 2.0F));
					double d7 = target.posZ - caster.posZ;
					EntityFireball entity = new EntityLargeFireball(world, caster, d5, d6, d7);
					entity.shootingEntity = caster;
					world.spawnEntity(entity);
				}
					break;
				case FireTrap: {
					// castRune(world, caster, new EntitySpellRune(world, EntitySpellRune.DamageType.FIRE, 10, 5, 0xFF5733));
				}
					break;
				case ExplosionFire: {
					world.createExplosion(caster, caster.posX, caster.posY, caster.posZ, 10.0F, false);
				}
					break;
				case RingOfFire: {
					for (BlockPos pos : createCircle(caster.getPosition()))
						if ((world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isReplaceable(world, pos)) && (!world.isAirBlock(pos.down()) && world.isBlockFullCube(pos.down()))) world.setBlockState(pos, Blocks.FIRE.getDefaultState());
				}
					break;
				case Shock: {
					target.attackEntityFrom(new DamageSourceLit(), 5);
				}
					break;
				case ShockSheathe: {
					SheatheHelper.castSheathe(caster, PotionSheathe.Type.Lit, 20 * 90);
				}
					break;
				case LitStrike: {
					EntityCasterLightningBolt entitylightningbolt = new EntityCasterLightningBolt(world, caster, target.posX, target.posY, target.posZ, false);
					world.addWeatherEffect(entitylightningbolt);
				}
					break;
				case LitTrap: {
					// useCharge = castRune(world, caster, new EntitySpellRune(world, EntitySpellRune.DamageType.SHOCK, 10, 5, 0xFFFFFF));
				}
					break;
				case ExplosionLit: {
					List<Entity> damageList = world.getEntitiesWithinAABBExcludingEntity(caster, new AxisAlignedBB(caster.posX - 5, caster.posY - 5, caster.posZ - 5, caster.posX + 5, caster.posY + 5, caster.posZ + 5));
					for (Entity e : damageList) {
						if (!(e instanceof EntityLivingBase)) continue;
						((EntityLivingBase) e).attackEntityFrom(new DamageSourceLit(), 10);
					}
					for (int index = 0; index < 1000; index++) {
						ParticleHelper.sendPacketToClients(world, TamModized.particles.fluff, caster.getPositionVector().addVector(0.5, 0, 0.5), 64, new ParticleHelper.ParticlePacketHelper(TamModized.particles.fluff, ((ParticleFluffPacketHandler) ParticlePacketHandlerRegistry.getHandler(TamModized.particles.fluff)).new ParticleFluffData(new Vec3d(world.rand.nextDouble() * 0.8D - 0.4D, world.rand.nextDouble() * 0.8D - 0.4D, world.rand.nextDouble() * 0.8D - 0.4D), world.rand.nextInt(20 * 3), -0.10f, world.rand.nextFloat() * 0.95f + 0.05f, 0xFFFFFFFF)));
					}
				}
					break;
				case RingOfLit: {
					for (BlockPos pos : createCircle(caster.getPosition())) {
						EntityCasterLightningBolt entitylightningbolt = new EntityCasterLightningBolt(world, caster, pos.getX(), pos.getY(), pos.getZ(), false);
						world.addWeatherEffect(entitylightningbolt);
					}
				}
					break;
				case Freeze: {
					target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * 7, 5));
				}
					break;
				case FrostSheathe: {
					SheatheHelper.castSheathe(caster, PotionSheathe.Type.Frost, 20 * 90);
				}
					break;
				case IceSpike: {
					BlockPos pos = target.getPosition();
					if ((world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isReplaceable(world, pos)) && (!world.isAirBlock(pos.down()) && world.getBlockState(pos.down()).isFullCube())) {
						world.setBlockState(pos, VoidCraft.blocks.iceSpike.getDefaultState().withProperty(BlockSpellIceSpike.FACING, EnumFacing.getHorizontal(world.rand.nextInt(4))));
						TileEntity te = world.getTileEntity(pos);
						if (te instanceof TileEntitySpellIceSpike) ((TileEntitySpellIceSpike) te).setCaster(caster);
					}
				}
					break;
				case FrostTrap: {
					// useCharge = castRune(world, caster, new EntitySpellRune(world, EntitySpellRune.DamageType.FROST, 10, 5, 0x00FFFF));
				}
					break;
				case ExplosionFrost: {
					List<Entity> damageList = world.getEntitiesWithinAABBExcludingEntity(caster, new AxisAlignedBB(caster.posX - 5, caster.posY - 5, caster.posZ - 5, caster.posX + 5, caster.posY + 5, caster.posZ + 5));
					for (Entity e : damageList) {
						if (!(e instanceof EntityLivingBase)) continue;
						((EntityLivingBase) e).attackEntityFrom(new DamageSourceLit(), 10);
						((EntityLivingBase) e).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * 7, 5));
					}
					for (int index = 0; index < 1000; index++) {
						ParticleHelper.sendPacketToClients(world, TamModized.particles.fluff, caster.getPositionVector().addVector(0.5, 0, 0.5), 64, new ParticleHelper.ParticlePacketHelper(TamModized.particles.fluff, ((ParticleFluffPacketHandler) ParticlePacketHandlerRegistry.getHandler(TamModized.particles.fluff)).new ParticleFluffData(new Vec3d(world.rand.nextDouble() * 0.8D - 0.4D, world.rand.nextDouble() * 0.8D - 0.4D, world.rand.nextDouble() * 0.8D - 0.4D), world.rand.nextInt(20 * 3), -0.10f, world.rand.nextFloat() * 0.95f + 0.05f, 0x00FFFFFF)));
					}
				}
					break;
				case RingOfFrost: {
					for (BlockPos pos : createCircle(caster.getPosition()))
						if ((world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isReplaceable(world, pos)) && (!world.isAirBlock(pos.down()) && world.getBlockState(pos.down()).isFullCube())) {
							world.setBlockState(pos, VoidCraft.blocks.iceSpike.getDefaultState().withProperty(BlockSpellIceSpike.FACING, EnumFacing.getHorizontal(world.rand.nextInt(4))));
							TileEntity te = world.getTileEntity(pos);
							if (te instanceof TileEntitySpellIceSpike) ((TileEntitySpellIceSpike) te).setCaster(caster);
						}
				}
					break;
				case AcidSpray: {
					Vec3d vec = caster.getLook(1f);
					int damageRange = 5;
					List<Entity> damageList = world.getEntitiesWithinAABBExcludingEntity(caster, new AxisAlignedBB(caster.posX - (vec.xCoord * damageRange), caster.posY - (vec.yCoord * damageRange), caster.posZ - (vec.zCoord * damageRange), caster.posX + (vec.xCoord * damageRange), caster.posY + (vec.yCoord * damageRange), caster.posZ + (vec.zCoord * damageRange)));
					for (Entity e : damageList) {
						e.attackEntityFrom(new DamageSourceAcid(), 5);
					}
					for (int index = 0; index < 300; index++) {
						ParticleHelper.sendPacketToClients(world, TamModized.particles.fluff, caster.getPositionVector().addVector(0, 1.5f, 0), 64, new ParticleHelper.ParticlePacketHelper(TamModized.particles.fluff, ((ParticleFluffPacketHandler) ParticlePacketHandlerRegistry.getHandler(TamModized.particles.fluff)).new ParticleFluffData(new Vec3d(vec.xCoord * 0.15 + (world.rand.nextFloat() * 0.10) - 0.05, vec.yCoord * 0.15 + (world.rand.nextFloat() * 0.10) - 0.05, vec.zCoord * 0.15 + (world.rand.nextFloat() * 0.10) - 0.05), world.rand.nextInt(20 * 3), 0, world.rand.nextFloat() * 0.45f + 0.05f, 0x00FF00FF)));
					}
				}
					break;
				case AcidSheathe: {
					SheatheHelper.castSheathe(caster, PotionSheathe.Type.Acid, 20 * 90);
				}
					break;
				case Disint: {
					ProjectileDisintegration disint = new ProjectileDisintegration(world, caster, caster.posX, caster.posY, caster.posZ);
					disint.setDamageRangeSpeed(15, 0.0F, 0.5D);
					world.spawnEntity(disint);
				}
					break;
				case AcidTrap: {
					// useCharge = castRune(world, caster, new EntitySpellRune(world, EntitySpellRune.DamageType.ACID, 10, 5, 0x00FF00));
				}
					break;
				case ExplosionAcid: {
					List<Entity> damageList = world.getEntitiesWithinAABBExcludingEntity(caster, new AxisAlignedBB(caster.posX - 5, caster.posY - 5, caster.posZ - 5, caster.posX + 5, caster.posY + 5, caster.posZ + 5));
					for (Entity e : damageList) {
						if (!(e instanceof EntityLivingBase)) continue;
						((EntityLivingBase) e).attackEntityFrom(new DamageSourceLit(), 10);
					}
					for (int index = 0; index < 1000; index++) {
						ParticleHelper.sendPacketToClients(world, TamModized.particles.fluff, caster.getPositionVector().addVector(0.5, 0, 0.5), 64, new ParticleHelper.ParticlePacketHelper(TamModized.particles.fluff, ((ParticleFluffPacketHandler) ParticlePacketHandlerRegistry.getHandler(TamModized.particles.fluff)).new ParticleFluffData(new Vec3d(world.rand.nextDouble() * 0.8D - 0.4D, world.rand.nextDouble() * 0.8D - 0.4D, world.rand.nextDouble() * 0.8D - 0.4D), world.rand.nextInt(20 * 3), -0.10f, world.rand.nextFloat() * 0.95f + 0.05f, 0x00FF00FF)));
					}
				}
					break;
				case RingOfAcid: {
					for (BlockPos pos : createCircle(caster.getPosition()))
						if ((world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isReplaceable(world, pos)) && (!world.isAirBlock(pos.down()) && world.getBlockState(pos.down()).isFullCube())) {
							world.setBlockState(pos, VoidCraft.fluids.acidFluidBlock.getDefaultState());
						}
				}
					break;
				case VoidicTouch: {
					target.attackEntityFrom(new DamageSourceVoidicInfusion(), 5);
					IVoidicInfusionCapability inf = target.getCapability(CapabilityList.VOIDICINFUSION, null);
					if (inf != null) inf.addInfusion(600);
				}
					break;
				case VoidicSheathe: {
					SheatheHelper.castSheathe(caster, PotionSheathe.Type.Void, 20 * 90);
				}
					break;
				case Implosion: {
					List<Entity> damageList = world.getEntitiesWithinAABBExcludingEntity(caster, new AxisAlignedBB(caster.posX - 5, caster.posY - 5, caster.posZ - 5, caster.posX + 5, caster.posY + 5, caster.posZ + 5));
					for (Entity e : damageList) {
						if (!(e instanceof EntityLivingBase)) continue;
						world.spawnEntity(new EntitySpellImplosion(world, e));
					}
				}
					break;
				default: {
				}
					break;
			}

		}

	}

	private static BlockPos[] createCircle(BlockPos pos) {
		return new BlockPos[] {

				pos.add(3, 0, 1), pos.add(3, 0, 0), pos.add(3, 0, -1),

				pos.add(-3, 0, 1), pos.add(-3, 0, 0), pos.add(-3, 0, -1),

				pos.add(1, 0, -3), pos.add(0, 0, -3), pos.add(-1, 0, -3),

				pos.add(1, 0, 3), pos.add(0, 0, 3), pos.add(-1, 0, 3),

				pos.add(2, 0, 2), pos.add(-2, 0, 2), pos.add(2, 0, -2), pos.add(-2, 0, -2)

		};
	}

	private static boolean castRune(World world, EntityPlayer player, EntitySpellRune rune) {
		if (!world.isRemote) {
			HashSet<Entity> exclude = new HashSet<Entity>();
			exclude.add(player);
			RayTraceResult result = RayTraceHelper.tracePath(world, player, 32, 1, exclude);
			if (result != null) {
				if (result.entityHit == null) {
					BlockPos bp = result.getBlockPos();
					BlockPos pos = bp;
					if (bp != null) {
						switch (result.sideHit) {
							case UP:
								pos = pos.add(0, 1, 0);
								rune.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
								world.spawnEntity(rune);
								return true;
							default:
								return false;
						}
					}
				}
			}
		}
		return false;
	}

}
