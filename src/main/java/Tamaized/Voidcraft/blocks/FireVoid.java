package Tamaized.Voidcraft.blocks;

import java.util.Random;

import Tamaized.TamModized.blocks.TamBlockFire;
import Tamaized.TamModized.blocks.TamBlockPortal;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.EntityVoidMob;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireVoid extends TamBlockFire {

	public FireVoid(CreativeTabs tab, String n) {
		super(tab, n);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if (world.getBlockState(pos.add(0, -1, 0)).getBlock() != VoidCraft.blocks.blockVoidcrystal || !((TamBlockPortal) VoidCraft.blocks.blockPortalVoid).tryToCreatePortal(world, pos)) {
			if (!world.isSideSolid(pos.down(), EnumFacing.UP) && !this.canNeighborCatchFire(world, pos)) {
				world.setBlockToAir(pos);
			} else {
				world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
			}
		} else {
			world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!worldIn.isRemote && entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityVoidMob || entityIn instanceof EntityWitherSkeleton)) {
			EntityLivingBase e = ((EntityLivingBase) entityIn);
			e.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 60, 1)); // nausea
			e.addPotionEffect(new PotionEffect(Potion.getPotionById(20), 60, 1)); // wither
			e.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 60, 1)); // blind
		}
	}

	@Override
	protected boolean canNeighborCatchFire(World worldIn, BlockPos pos) {
		return false;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		if (world.isAirBlock(pos.down())) world.setBlockToAir(pos);
	}

	@Override
	protected boolean canBeOnBlock(Block block) {
		return !(block instanceof BlockVoidcrystal);
	}
}
