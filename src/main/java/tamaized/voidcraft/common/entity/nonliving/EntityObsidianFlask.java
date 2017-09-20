package tamaized.voidcraft.common.entity.nonliving;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import tamaized.tammodized.common.helper.MotionHelper;
import tamaized.voidcraft.common.damagesources.DamageSourceVoidicInfusion;
import tamaized.voidcraft.registry.VoidCraftBlocks;
import tamaized.voidcraft.registry.VoidCraftFluids;

import java.util.List;

public class EntityObsidianFlask extends EntityThrowable implements IEntityAdditionalSpawnData {

	private Type type;

	public EntityObsidianFlask(World worldIn) {
		super(worldIn);
		type = Type.Normal;
	}

	public EntityObsidianFlask(Type t, World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
		type = t;
	}

	public EntityObsidianFlask(Type t, World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		type = t;
	}

	public Type getType() {
		return type;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (world.isRemote)
			return;
		if (result.entityHit != null) {
			result.entityHit.attackEntityFrom(type == Type.Fire ? DamageSource.ON_FIRE : (type == Type.Freeze || type == Type.Shock || type == Type.Acid) ? DamageSource.MAGIC : type == Type.Void ? new DamageSourceVoidicInfusion() : DamageSource.OUT_OF_WORLD, 5);
		} else {
			BlockPos pos = result.getBlockPos().add(0, 1, 0);
			switch (type) {
				default:
				case Normal: {
					world.newExplosion(null, this.posX, this.posY, this.posZ, 0, true, true);
					if (world.isAirBlock(pos))
						world.setBlockState(pos, VoidCraftBlocks.blockVoidFire.getDefaultState());
					break;
				}
				case Fire: {
					world.newExplosion(null, this.posX, this.posY, this.posZ, 0, true, true);
					for (int x = -2; x <= 2; x++) {
						for (int z = -2; z <= 2; z++) {
							if (!(x == 0 && z == 0) && world.rand.nextInt(4) != 0)
								continue;
							int y = -2;
							while (!world.isAirBlock(pos.add(x, y, z))) {
								y++;
								if (y > 2)
									break;
							}
							if (world.isAirBlock(pos.add(x, y, z))) {
								world.setBlockState(pos.add(x, y, z), Blocks.FIRE.getDefaultState());
							}
						}
					}
				}
				break;
				case Freeze: {
					world.playEvent(2002, pos, 0x00FFFF);
					List<EntityLivingBase> damageList = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.add(-5, -5, -5), pos.add(5, 5, 5)));
					for (EntityLivingBase e : damageList) {
						e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * 10, 5));
					}
				}
				break;
				case Shock: {
					world.playEvent(2002, pos, 0xFFFFFF);
					List<EntityLivingBase> damageList = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.add(-5, -5, -5), pos.add(5, 5, 5)));
					for (EntityLivingBase e : damageList) {
						MotionHelper.addMotion(e, new Vec3d(0, 5, 0));
					}
				}
				break;
				case Acid: {
					world.playEvent(2002, pos, 0x00FF00);
					if (world.isAirBlock(pos))
						world.setBlockState(pos, VoidCraftFluids.acidFluidBlock.getDefaultState());
				}
				break;
				case Void: {
					world.playEvent(2002, pos, 0x7700FF);
					List<EntityLivingBase> damageList = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.add(-5, -5, -5), pos.add(5, 5, 5)));
					for (EntityLivingBase e : damageList) {
						world.spawnEntity(new EntitySpellImplosion(world, e));
					}
				}
				break;
			}
		}

		if (!this.world.isRemote) {
			this.setDead();
		}
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeInt(type.ordinal());
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		type = Type.values()[additionalData.readInt()];
	}

	public enum Type {
		Normal, Fire, Freeze, Shock, Acid, Void
	}

}
