package Tamaized.Voidcraft.world.dim.TheVoid;

import java.util.Random;

import com.google.common.base.Predicate;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class WorldGenEtherealPlants extends WorldGenMinable {

	private final static int numberOfBlocks = 1;
	private final Predicate<IBlockState> predicate;

	public WorldGenEtherealPlants() {
		super(VoidCraft.blocks.blockFakeBedrockFarmland.getDefaultState(), numberOfBlocks);
		predicate = BlockMatcher.forBlock(VoidCraft.blocks.blockFakeBedrock);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		float f = rand.nextFloat() * (float) Math.PI;
		int numBlock = 3;
		double d0 = (double) ((float) (position.getX() + 8) + MathHelper.sin(f) * (float) numBlock / 8.0F);
		double d1 = (double) ((float) (position.getX() + 8) - MathHelper.sin(f) * (float) numBlock / 8.0F);
		double d2 = (double) ((float) (position.getZ() + 8) + MathHelper.cos(f) * (float) numBlock / 8.0F);
		double d3 = (double) ((float) (position.getZ() + 8) - MathHelper.cos(f) * (float) numBlock / 8.0F);
		double d4 = (double) (position.getY() + rand.nextInt(3) - 2);
		double d5 = (double) (position.getY() + rand.nextInt(3) - 2);

		for (int i = 0; i < numBlock; ++i) {
			float f1 = (float) i / (float) numBlock;
			double d6 = d0 + (d1 - d0) * (double) f1;
			double d7 = d4 + (d5 - d4) * (double) f1;
			double d8 = d2 + (d3 - d2) * (double) f1;
			double d9 = rand.nextDouble() * (double) numBlock / 16.0D;
			double d10 = (double) (MathHelper.sin((float) Math.PI * f1) + 1.0F) * d9 + 1.0D;
			double d11 = (double) (MathHelper.sin((float) Math.PI * f1) + 1.0F) * d9 + 1.0D;
			int j = MathHelper.floor(d6 - d10 / 2.0D);
			int k = MathHelper.floor(d7 - d11 / 2.0D);
			int l = MathHelper.floor(d8 - d10 / 2.0D);
			int i1 = MathHelper.floor(d6 + d10 / 2.0D);
			int j1 = MathHelper.floor(d7 + d11 / 2.0D);
			int k1 = MathHelper.floor(d8 + d10 / 2.0D);

			for (int l1 = j; l1 <= i1; ++l1) {
				double d12 = ((double) l1 + 0.5D - d6) / (d10 / 2.0D);

				if (d12 * d12 < 1.0D) {
					for (int i2 = k; i2 <= j1; ++i2) {
						double d13 = ((double) i2 + 0.5D - d7) / (d11 / 2.0D);

						if (d12 * d12 + d13 * d13 < 1.0D) {
							for (int j2 = l; j2 <= k1; ++j2) {
								double d14 = ((double) j2 + 0.5D - d8) / (d10 / 2.0D);

								if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D) {
									BlockPos blockpos = new BlockPos(l1, i2, j2);
									IBlockState state = worldIn.getBlockState(blockpos);
									if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, this.predicate)) {
										//System.out.println("ding: "+blockpos);
										worldIn.setBlockState(blockpos, VoidCraft.fluids.voidFluidBlock.getDefaultState());
										if(worldIn.rand.nextInt(4) == 0){
											if (worldIn.isAirBlock(blockpos.north().up())) {
												worldIn.setBlockState(blockpos.north(), VoidCraft.blocks.blockFakeBedrockFarmland.getDefaultState(), 2);
												worldIn.setBlockState(blockpos.north().up(), VoidCraft.blocks.etherealPlant.withAge(VoidCraft.blocks.etherealPlant.getMaxAge()), 2);
												return true;
											}
											if (worldIn.isAirBlock(blockpos.south().up())) {
												worldIn.setBlockState(blockpos.south(), VoidCraft.blocks.blockFakeBedrockFarmland.getDefaultState(), 2);
												worldIn.setBlockState(blockpos.south().up(), VoidCraft.blocks.etherealPlant.withAge(VoidCraft.blocks.etherealPlant.getMaxAge()), 2);
												return true;
											}
											if (worldIn.isAirBlock(blockpos.east().up())) {
												worldIn.setBlockState(blockpos.east(), VoidCraft.blocks.blockFakeBedrockFarmland.getDefaultState(), 2);
												worldIn.setBlockState(blockpos.east().up(), VoidCraft.blocks.etherealPlant.withAge(VoidCraft.blocks.etherealPlant.getMaxAge()), 2);
												return true;
											}
											if (worldIn.isAirBlock(blockpos.west().up())) {
												worldIn.setBlockState(blockpos.west(), VoidCraft.blocks.blockFakeBedrockFarmland.getDefaultState(), 2);
												worldIn.setBlockState(blockpos.west().up(), VoidCraft.blocks.etherealPlant.withAge(VoidCraft.blocks.etherealPlant.getMaxAge()), 2);
												return true;
											}
											return true;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return true;
	}

}
