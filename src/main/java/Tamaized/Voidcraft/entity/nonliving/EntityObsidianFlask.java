package Tamaized.Voidcraft.entity.nonliving;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityObsidianFlask extends EntityThrowable {

	public EntityObsidianFlask(World worldIn) {
		super(worldIn);
	}

	public EntityObsidianFlask(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	public EntityObsidianFlask(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public static void func_189662_a(DataFixer p_189662_0_) {
		// EntityThrowable.func_189661_a(p_189662_0_, "Snowball");
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	@Override
	protected void onImpact(RayTraceResult result) {

		for (int j = 0; j < 8; ++j) {
			boolean flag = (j % 2 == 0);
			this.world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, this.posX, this.posY, this.posZ, flag ? 0.0D : 1.0D, 0.0D, flag ? 1.0D : 0.0D, new int[0]);
		}

		if (result.entityHit != null) {
			result.entityHit.attackEntityFrom(DamageSource.OUT_OF_WORLD, 5);
		} else {
			implosion(result.getBlockPos());
		}

		if (!this.world.isRemote) {
			this.setDead();
		}
	}

	private void implosion(BlockPos pos) {
		pos = pos.add(0, 1, 0);
		world.newExplosion((Entity) null, this.posX, this.posY, this.posZ, 0, true, true);
		if (world.isAirBlock(pos)) world.setBlockState(pos, VoidCraft.blocks.fireVoid.getDefaultState());
	}

}
