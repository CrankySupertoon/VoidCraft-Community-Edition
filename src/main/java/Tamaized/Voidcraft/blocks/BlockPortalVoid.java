package Tamaized.Voidcraft.blocks;

import java.util.Random;

import com.google.common.cache.LoadingCache;

import Tamaized.TamModized.blocks.TamBlockPortal;
import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPortalVoid extends TamBlockPortal {

	public BlockPortalVoid(CreativeTabs tab, String n) {
		super(tab, n, true);
	}

	@Override
	public boolean tryToCreatePortal(World par1World, BlockPos pos) {
		BlockPortalVoid.Size blockportal$size = new BlockPortalVoid.Size(par1World, pos, EnumFacing.Axis.X);
		if (blockportal$size.isValid() && blockportal$size.portalBlockCount == 0) {
			blockportal$size.placePortalBlocks();
			return true;
		} else {
			BlockPortalVoid.Size blockportal$size1 = new BlockPortalVoid.Size(par1World, pos, EnumFacing.Axis.Z);

			if (blockportal$size1.isValid() && blockportal$size1.portalBlockCount == 0) {
				blockportal$size1.placePortalBlocks();
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos p_189540_5_) {
		EnumFacing.Axis enumfacing$axis = (EnumFacing.Axis) state.getValue(AXIS);

		if (enumfacing$axis == EnumFacing.Axis.X) {
			BlockPortalVoid.Size blockportal$size = new BlockPortalVoid.Size(world, pos, EnumFacing.Axis.X);

			if (!blockportal$size.isValid() || blockportal$size.portalBlockCount < blockportal$size.width * blockportal$size.height) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		} else if (enumfacing$axis == EnumFacing.Axis.Z) {
			BlockPortalVoid.Size blockportal$size1 = new BlockPortalVoid.Size(world, pos, EnumFacing.Axis.Z);

			if (!blockportal$size1.isValid() || blockportal$size1.portalBlockCount < blockportal$size1.width * blockportal$size1.height) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		}
	}

	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		for (int l = 0; l < 4; ++l) {
			double d0 = (double) ((float) x + rand.nextFloat());
			double d1 = (double) ((float) y + rand.nextFloat());
			double d2 = (double) ((float) z + rand.nextFloat());
			double d3 = 0.0D;
			double d4 = 0.0D;
			double d5 = 0.0D;
			int i1 = rand.nextInt(2) * 2 - 1;
			d3 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			d4 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			d5 = ((double) rand.nextFloat() - 0.5D) * 0.5D;

			if (worldIn.getBlockState(pos.add(-1, 0, 0)).getBlock() != this && worldIn.getBlockState(pos.add(1, 0, 0)).getBlock() != this) {
				d0 = (double) x + 0.5D + 0.25D * (double) i1;
				d3 = (double) (rand.nextFloat() * 2.0F * (float) i1);
			} else {
				d2 = (double) z + 0.5D + 0.25D * (double) i1;
				d5 = (double) (rand.nextFloat() * 2.0F * (float) i1);
			}

			// par1World.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
			// Minecraft.getMinecraft().effectRenderer.addEffect(new
			// TestFX(par1World, d0, d1, d2));
		}
	}

	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given coordinates. Args: blockAccess, x, y, z, side
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		if (blockAccess.getBlockState(pos).getBlock() == this) {
			return false;
		} else {
			boolean flag = blockAccess.getBlockState(pos.add(-1, 0, 0)).getBlock() == this && blockAccess.getBlockState(pos.add(-2, 0, 0)).getBlock() != this;
			boolean flag1 = blockAccess.getBlockState(pos.add(1, 0, 0)).getBlock() == this && blockAccess.getBlockState(pos.add(2, 0, 0)).getBlock() != this;
			boolean flag2 = blockAccess.getBlockState(pos.add(0, 0, -1)).getBlock() == this && blockAccess.getBlockState(pos.add(0, 0, -2)).getBlock() != this;
			boolean flag3 = blockAccess.getBlockState(pos.add(0, 0, 1)).getBlock() == this && blockAccess.getBlockState(pos.add(0, 0, 2)).getBlock() != this;
			boolean flag4 = flag || flag1;
			boolean flag5 = flag2 || flag3;
			return flag4 && side == EnumFacing.WEST ? true : (flag4 && side == EnumFacing.EAST ? true : (flag5 && side == EnumFacing.NORTH ? true : flag5 && side == EnumFacing.SOUTH));
		}
	}

	public BlockPattern.PatternHelper createPatternHelper(World p_181089_1_, BlockPos p_181089_2_) {
		EnumFacing.Axis enumfacing$axis = EnumFacing.Axis.Z;
		BlockPortalVoid.Size blockportal$size = new BlockPortalVoid.Size(p_181089_1_, p_181089_2_, EnumFacing.Axis.X);
		LoadingCache<BlockPos, BlockWorldState> loadingcache = BlockPattern.createLoadingCache(p_181089_1_, true);

		if (!blockportal$size.isValid()) {
			enumfacing$axis = EnumFacing.Axis.X;
			blockportal$size = new BlockPortalVoid.Size(p_181089_1_, p_181089_2_, EnumFacing.Axis.Z);
		}

		if (!blockportal$size.isValid()) {
			return new BlockPattern.PatternHelper(p_181089_2_, EnumFacing.NORTH, EnumFacing.UP, loadingcache, 1, 1, 1);
		} else {
			int[] aint = new int[EnumFacing.AxisDirection.values().length];
			EnumFacing enumfacing = blockportal$size.rightDir.rotateYCCW();
			BlockPos blockpos = blockportal$size.bottomLeft.up(blockportal$size.func_181100_a() - 1);

			for (EnumFacing.AxisDirection enumfacing$axisdirection : EnumFacing.AxisDirection.values()) {
				BlockPattern.PatternHelper blockpattern$patternhelper = new BlockPattern.PatternHelper(enumfacing.getAxisDirection() == enumfacing$axisdirection ? blockpos : blockpos.offset(blockportal$size.rightDir, blockportal$size.func_181101_b() - 1), EnumFacing.getFacingFromAxis(enumfacing$axisdirection, enumfacing$axis), EnumFacing.UP, loadingcache, blockportal$size.func_181101_b(), blockportal$size.func_181100_a(), 1);

				for (int i = 0; i < blockportal$size.func_181101_b(); ++i) {
					for (int j = 0; j < blockportal$size.func_181100_a(); ++j) {
						BlockWorldState blockworldstate = blockpattern$patternhelper.translateOffset(i, j, 1);

						if (blockworldstate.getBlockState() != null && blockworldstate.getBlockState().getMaterial() != Material.AIR) {
							++aint[enumfacing$axisdirection.ordinal()];
						}
					}
				}
			}

			EnumFacing.AxisDirection enumfacing$axisdirection1 = EnumFacing.AxisDirection.POSITIVE;

			for (EnumFacing.AxisDirection enumfacing$axisdirection2 : EnumFacing.AxisDirection.values()) {
				if (aint[enumfacing$axisdirection2.ordinal()] < aint[enumfacing$axisdirection1.ordinal()]) {
					enumfacing$axisdirection1 = enumfacing$axisdirection2;
				}
			}

			return new BlockPattern.PatternHelper(enumfacing.getAxisDirection() == enumfacing$axisdirection1 ? blockpos : blockpos.offset(blockportal$size.rightDir, blockportal$size.func_181101_b() - 1), EnumFacing.getFacingFromAxis(enumfacing$axisdirection1, enumfacing$axis), EnumFacing.UP, loadingcache, blockportal$size.func_181101_b(), blockportal$size.func_181100_a(), 1);
		}
	}

	public static class Size {
		private final World world;
		private final EnumFacing.Axis axis;
		private final EnumFacing rightDir;
		private final EnumFacing leftDir;
		private int portalBlockCount = 0;
		private BlockPos bottomLeft;
		private int height;
		private int width;

		public Size(World worldIn, BlockPos p_i45694_2_, EnumFacing.Axis p_i45694_3_) {
			world = worldIn;
			axis = p_i45694_3_;

			if (p_i45694_3_ == EnumFacing.Axis.X) {
				leftDir = EnumFacing.EAST;
				rightDir = EnumFacing.WEST;
			} else {
				leftDir = EnumFacing.NORTH;
				rightDir = EnumFacing.SOUTH;
			}

			for (BlockPos blockpos = p_i45694_2_; p_i45694_2_.getY() > blockpos.getY() - 21 && p_i45694_2_.getY() > 0 && isEmptyBlock(worldIn.getBlockState(p_i45694_2_.down()).getBlock()); p_i45694_2_ = p_i45694_2_.down()) {
				;
			}

			int i = getDistanceUntilEdge(p_i45694_2_, leftDir) - 1;

			if (i >= 0) {
				bottomLeft = p_i45694_2_.offset(leftDir, i);
				width = getDistanceUntilEdge(bottomLeft, rightDir);

				if (width < 2 || width > 21) {
					bottomLeft = null;
					width = 0;
				}
			}

			if (bottomLeft != null) {
				height = func_150858_a();
			}
		}

		protected int getDistanceUntilEdge(BlockPos p_180120_1_, EnumFacing p_180120_2_) {
			int i;

			for (i = 0; i < 22; ++i) {
				BlockPos blockpos = p_180120_1_.offset(p_180120_2_, i);

				if (!isEmptyBlock(world.getBlockState(blockpos).getBlock()) || world.getBlockState(blockpos.down()).getBlock() != VoidCraft.blocks.blockVoidcrystal) {
					break;
				}
			}

			Block block = world.getBlockState(p_180120_1_.offset(p_180120_2_, i)).getBlock();
			return block == VoidCraft.blocks.blockVoidcrystal ? i : 0;
		}

		public int func_181100_a() {
			return height;
		}

		public int func_181101_b() {
			return width;
		}

		protected int func_150858_a() {
			label24:

			for (height = 0; height < 21; ++height) {
				for (int i = 0; i < width; ++i) {
					BlockPos blockpos = bottomLeft.offset(rightDir, i).up(height);
					Block block = world.getBlockState(blockpos).getBlock();

					if (!isEmptyBlock(block)) {
						break label24;
					}

					if (block == VoidCraft.blocks.blockPortalVoid) {
						++portalBlockCount;
					}

					if (i == 0) {
						block = world.getBlockState(blockpos.offset(leftDir)).getBlock();

						if (block != VoidCraft.blocks.blockVoidcrystal) {
							break label24;
						}
					} else if (i == width - 1) {
						block = world.getBlockState(blockpos.offset(rightDir)).getBlock();

						if (block != VoidCraft.blocks.blockVoidcrystal) {
							break label24;
						}
					}
				}
			}

			for (int j = 0; j < width; ++j) {
				if (world.getBlockState(bottomLeft.offset(rightDir, j).up(height)).getBlock() != VoidCraft.blocks.blockVoidcrystal) {
					height = 0;
					break;
				}
			}

			if (height <= 21 && height >= 3) {
				return height;
			} else {
				bottomLeft = null;
				width = 0;
				height = 0;
				return 0;
			}
		}

		protected boolean isEmptyBlock(Block blockIn) {
			return blockIn.getDefaultState().getMaterial() == Material.AIR || blockIn == VoidCraft.blocks.fireVoid || blockIn == VoidCraft.blocks.blockPortalVoid;
		}

		public boolean isValid() {
			return bottomLeft != null && width >= 2 && width <= 21 && height >= 3 && height <= 21;
		}

		public void placePortalBlocks() {
			for (int i = 0; i < width; ++i) {
				BlockPos blockpos = bottomLeft.offset(rightDir, i);

				for (int j = 0; j < height; ++j) {
					world.setBlockState(blockpos.up(j), VoidCraft.blocks.blockPortalVoid.getDefaultState().withProperty(BlockPortalVoid.AXIS, axis), 2);
				}
			}
		}
	}

}
