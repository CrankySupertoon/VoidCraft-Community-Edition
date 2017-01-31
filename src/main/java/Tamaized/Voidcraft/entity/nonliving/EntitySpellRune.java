package Tamaized.Voidcraft.entity.nonliving;

import java.util.List;

import com.google.common.base.Predicate;

import Tamaized.TamModized.TamModized;
import Tamaized.TamModized.particles.ParticleHelper;
import Tamaized.TamModized.particles.ParticlePacketHandlerRegistry;
import Tamaized.TamModized.particles.FX.network.ParticleFluffPacketHandler;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.damageSources.DamageSourceAcid;
import Tamaized.Voidcraft.damageSources.DamageSourceFrost;
import Tamaized.Voidcraft.damageSources.DamageSourceLit;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntitySpellRune extends Entity implements IEntityAdditionalSpawnData {

	public static enum DamageType {
		FIRE, FROST, ACID, SHOCK, VOID
	}

	public static int getTypeID(DamageType type) {
		return type.ordinal();
	}

	public static DamageType getTypeFromID(int id) {
		return id >= 0 && id < DamageType.values().length ? DamageType.values()[id] : DamageType.FIRE;
	}

	private int power = 1;
	private int radius = 1;
	private int color = 0xFFFFFF;
	private DamageType damageType = DamageType.FIRE;

	public EntitySpellRune(World worldIn) {
		super(worldIn);
	}

	public EntitySpellRune(World world, DamageType source, int damage, int size, int rgb) {
		super(world);
		power = damage;
		radius = size;
		color = rgb;
		damageType = source;
	}

	public int getColor() {
		return color;
	}

	@Override
	protected void entityInit() {

	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeInt(power);
		buffer.writeInt(radius);
		buffer.writeInt(color);
		buffer.writeInt(getTypeID(damageType));
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		power = additionalData.readInt();
		radius = additionalData.readInt();
		color = additionalData.readInt();
		damageType = getTypeFromID(additionalData.readInt());
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		power = nbt.getInteger("power");
		radius = nbt.getInteger("radius");
		color = nbt.getInteger("color");
		damageType = getTypeFromID(nbt.getInteger("damageType"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setInteger("power", power);
		nbt.setInteger("radius", radius);
		nbt.setInteger("color", color);
		nbt.setInteger("damageType", getTypeID(damageType));
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		// if(!world.isRemote){
		List<Entity> list = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(posX - 0.5F, posY, posZ - 0.5F, posX + 0.5F, posY + 1, posZ + 0.5F), new Predicate<Entity>() {
			@Override
			public boolean apply(Entity input) {
				return EntitySelectors.NOT_SPECTATING.apply(input) ? !(input instanceof EntitySpellRune) : false;
			}
		});
		if (!list.isEmpty()) {
			DamageSource source;
			boolean frost = false;
			switch (damageType) {
				case FIRE:
					for (int x = -1; x <= 1; x++)
						for (int z = -1; z <= 1; z++)
							if (world.isAirBlock(getPosition().add(x, 0, z))) world.setBlockState(getPosition().add(x, 0, z), Blocks.FIRE.getDefaultState());
				default:
					source = DamageSource.ON_FIRE;
					break;
				case FROST:
					frost = true;
					source = new DamageSourceFrost();
					break;
				case ACID:
					for (int x = -1; x <= 1; x++) {
						for (int z = -1; z <= 1; z++) {
							BlockPos pos = new BlockPos(getPosition().add(x, 0, z));
							if ((world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isReplaceable(world, pos)) && (!world.isAirBlock(pos.down()) && world.getBlockState(pos.down()).isFullCube())) {
								world.setBlockState(pos, VoidCraft.fluids.acidFluidBlock.getDefaultState());
							}
						}
					}
					source = new DamageSourceAcid();
					break;
				case SHOCK:
					EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, posX, posY, posZ, false);
					world.addWeatherEffect(entitylightningbolt);
					source = new DamageSourceLit();
					break;
			}
			List<EntityLivingBase> damageList = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius));
			for (EntityLivingBase e : damageList) {
				e.attackEntityFrom(source, power);
				if (frost) e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * 5, 5));
			}
			for (int index = 0; index < 1000; index++) {
				ParticleHelper.sendPacketToClients(world, TamModized.particles.fluff, getPositionVector().addVector(0.5, 0, 0.5), 64, new ParticleHelper.ParticlePacketHelper(TamModized.particles.fluff, ((ParticleFluffPacketHandler) ParticlePacketHandlerRegistry.getHandler(TamModized.particles.fluff)).new ParticleFluffData(new Vec3d(world.rand.nextDouble() * 0.8D - 0.4D, world.rand.nextDouble() * 0.8D - 0.4D, world.rand.nextDouble() * 0.8D - 0.4D), world.rand.nextInt(20 * 3), -0.10f, world.rand.nextFloat() * 0.95f + 0.05f, (color << 8) + (0xFF))));
			}
			setDead();
		}
		// }
	}

}
