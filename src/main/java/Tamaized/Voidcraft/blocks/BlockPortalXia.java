package Tamaized.Voidcraft.blocks;

import java.util.Random;

import Tamaized.TamModized.blocks.TamBlockPortal;
import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPortalXia extends TamBlockPortal {

	public BlockPortalXia(CreativeTabs tab, String n) {
		super(tab, n, false);
	}

	/**
	 * Override this and return getDefaultState() if hasAxis is false
	 */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}

	/**
	 * Override this and return 0 if hasAxis is false
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	/**
	 * Override this and return 'new BlockState(this, new IProperty[0])' if hasAxis is false
	 */
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[0]);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		super.breakBlock(worldIn, pos, state);
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				for (int y = -1; y <= 0; y++) {
					BlockPos newPos = pos.add(x, y, z);
					if (worldIn.getBlockState(newPos).getBlock() == VoidCraft.blocks.blockNoBreak) {
						worldIn.setBlockState(newPos, VoidCraft.blocks.blockFakeBedrock.getDefaultState());
					}
				}
			}
		}
	}

	public boolean tryToCreatePortal(World par1World, BlockPos pos) {
		byte b0 = 0;
		byte b1 = 0;

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		if (par1World.getBlockState(pos.add(-1, 0, 0)).getBlock() == VoidCraft.blocks.blockFakeBedrock || par1World.getBlockState(pos.add(1, 0, 0)).getBlock() == VoidCraft.blocks.blockFakeBedrock) {
			b0 = 1;
		}

		if (par1World.getBlockState(pos.add(0, 0, -1)).getBlock() == VoidCraft.blocks.blockFakeBedrock || par1World.getBlockState(pos.add(0, 0, 1)).getBlock() == VoidCraft.blocks.blockFakeBedrock) {
			b1 = 1;
		}

		if (b0 == b1) {
			return false;
		} else {
			if (par1World.isAirBlock(new BlockPos(x - b0, y, z - b1))) {
				x -= b0;
				y -= b1;
			}

			int l;
			int i1;

			for (l = -1; l <= 2; ++l) {
				for (i1 = -1; i1 <= 3; ++i1) {
					boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;

					if (l != -1 && l != 2 || i1 != -1 && i1 != 3) {
						Block j1 = par1World.getBlockState(new BlockPos(x + b0 * l, y + i1, z + b1 * l)).getBlock();
						boolean isAirBlock = par1World.isAirBlock(new BlockPos(x + b0 * l, y + i1, z + b1 * l));

						if (flag) {
							if (j1 != VoidCraft.blocks.blockFakeBedrock) {
								return false;
							}
						} else if (!isAirBlock && j1 != VoidCraft.blocks.fireVoid) {
							return false;
						}
					}
				}
			}

			for (l = 0; l < 2; ++l) {
				for (i1 = 0; i1 < 3; ++i1) {
					par1World.setBlockState(new BlockPos(x + b0 * l, y + i1, z + b1 * l), VoidCraft.blocks.blockPortalXia.getDefaultState(), 2);
				}
			}

			return true;
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos p_189540_5_) {
		byte b0 = 0;
		byte b1 = 1;

		if (world.getBlockState(pos.add(-1, 0, 0)).getBlock() == this || world.getBlockState(pos.add(1, 0, 0)).getBlock() == this) {
			b0 = 1;
			b1 = 0;
		}

		int i1;

		for (i1 = pos.getY(); world.getBlockState(pos.add(0, i1 - 1, 0)).getBlock() == this; --i1) {
			;
		}

		if (world.getBlockState(pos.add(0, i1 - 1, 0)).getBlock() != VoidCraft.blocks.blockFakeBedrock) {
			world.setBlockToAir(pos);
		} else {
			int j1;

			for (j1 = 1; j1 < 4 && world.getBlockState(pos.add(0, i1 + j1, 0)).getBlock() == this; ++j1) {
				;
			}

			if (j1 == 3 && world.getBlockState(pos.add(0, i1 + j1, 0)).getBlock() == VoidCraft.blocks.blockFakeBedrock) {
				boolean flag = world.getBlockState(pos.add(-1, 0, 0)).getBlock() == this || world.getBlockState(pos.add(1, 0, 0)).getBlock() == this;
				boolean flag1 = world.getBlockState(pos.add(0, 0, -1)).getBlock() == this || world.getBlockState(pos.add(0, 0, 1)).getBlock() == this;

				if (flag && flag1) {
					world.setBlockToAir(pos);
				} else {
					if ((world.getBlockState(pos.add(b0, 0, b1)).getBlock() != VoidCraft.blocks.blockFakeBedrock || world.getBlockState(pos.add(-b0, 0, -b1)).getBlock() != this) && (world.getBlockState(pos.add(-b0, 0, -b1)).getBlock() != VoidCraft.blocks.blockFakeBedrock || world.getBlockState(pos.add(b0, 0, b1)).getBlock() != this)) {
						world.setBlockToAir(pos);
					}
				}
			} else {
				world.setBlockToAir(pos);
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

			// world.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
			// Minecraft.getMinecraft().effectRenderer.addEffect(new TestFX(par1World, d0, d1, d2));
		}
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}

}
