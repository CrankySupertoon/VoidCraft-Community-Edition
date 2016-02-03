package Tamaized.Voidcraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.common.voidCraft;

public class BlockPortalVoid extends BlockVoidTeleporter {

	public BlockPortalVoid(String string) {
		super(string, true);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		float f;
		float f1;
		int meta = this.getMetaFromState(worldIn.getBlockState(pos));
		if(meta == 2/*worldIn.getBlockState(pos.add(-1, 0, 0)).getBlock() != this && worldIn.getBlockState(pos.add(1, 0, 0)).getBlock() != this*/){
			f = 0.125F;
			f1 = 0.5F;
			this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
		}else{
			f = 0.5F;
			f1 = 0.125F;
			this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
		}
	}
	
	@Override
	public boolean tryToCreatePortal(World par1World, BlockPos pos) {
    /*
    	int x = pos.getX();
    	int y = pos.getY();
    	int z = pos.getZ();

    	byte b0 = 0;
        byte b1 = 1;

        if (par1World.getBlockState(pos.add(-1, 0, 0)).getBlock() == this || par1World.getBlockState(pos.add(1, 0, 0)).getBlock() == this)
        {
            b0 = 1;
            b1 = 0;
        }
    	

    	if (b0 == b1){
    		return false;
    	}else{
    		if (par1World.isAirBlock(new BlockPos(x - b0, y, z - b1))){
    			x -= b0;
    			y -= b1;
    		}

    		int l;
    		int i1;

    		for (l = -1; l <= 2; ++l){
    			for (i1 = -1; i1 <= 3; ++i1){
    				boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;

    				if (l != -1 && l != 2 || i1 != -1 && i1 != 3){
    					Block j1 = par1World.getBlockState(new BlockPos(x + b0 * l, y + i1, z + b1 * l)).getBlock();
    					boolean isAirBlock = par1World.isAirBlock(new BlockPos(x + b0 * l, y + i1, z + b1 * l));
    					


    					if (flag){
    						if (j1 != voidCraft.blocks.blockVoidcrystal){
    							return false;
    						}
    					}
    					else if (!isAirBlock && j1 != voidCraft.blocks.fireVoid){
    						return false;
    					}
    				}
    			}
    		}

    		for (l = 0; l < 2; ++l){
    			for (i1 = 0; i1 < 3; ++i1){
    				par1World.setBlockState(new BlockPos(x + b0 * l, y + i1, z + b1 * l), getStateFromMeta(3), 2);
    			}
    		}

    		return true;
    	}*/
		BlockPortalVoid.Size blockportal$size = new BlockPortalVoid.Size(par1World, pos, EnumFacing.Axis.X);
        if (blockportal$size.func_150860_b() && blockportal$size.field_150864_e == 0)
        {
            blockportal$size.func_150859_c();
            return true;
        }
        else
        {
            BlockPortalVoid.Size blockportal$size1 = new BlockPortalVoid.Size(par1World, pos, EnumFacing.Axis.Z);

            if (blockportal$size1.func_150860_b() && blockportal$size1.field_150864_e == 0)
            {
                blockportal$size1.func_150859_c();
                return true;
            }
            else
            {
                return false;
            }
        }
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock){
        /*byte b0 = 0;
        byte b1 = 1;

        if (world.getBlockState(pos.add(-1, 0, 0)).getBlock() == this || world.getBlockState(pos.add(1, 0, 0)).getBlock() == this)
        {
            b0 = 1;
            b1 = 0;
        }

        int i1;

        for (i1 = pos.getY(); world.getBlockState(pos.add(0, i1-1, 0)).getBlock() == this; --i1)
        {
            ;
        }

        if (world.getBlockState(pos.add(0, i1-1, 0)).getBlock() != voidCraft.blocks.blockVoidcrystal)
        {
            world.setBlockToAir(pos);
        }
        else
        {
            int j1;

            for (j1 = 1; j1 < 4 && world.getBlockState(pos.add(0, i1 + j1, 0)).getBlock() == this; ++j1)
            {
                ;
            }

            if (j1 == 3 && world.getBlockState(pos.add(0, i1 + j1, 0)).getBlock() == voidCraft.blocks.blockVoidcrystal)
            {
                boolean flag = world.getBlockState(pos.add(-1, 0, 0)).getBlock() == this || world.getBlockState(pos.add(1, 0, 0)).getBlock() == this;
                boolean flag1 = world.getBlockState(pos.add(0, 0, -1)).getBlock() == this || world.getBlockState(pos.add(0, 0, 1)).getBlock() == this;

                if (flag && flag1)
                {
                    world.setBlockToAir(pos);
                }
                else
                {
                    if ((world.getBlockState(pos.add(b0, 0, b1)).getBlock() != voidCraft.blocks.blockVoidcrystal || world.getBlockState(pos.add(-b0, 0, -b1)).getBlock() != this) && (world.getBlockState(pos.add(-b0, 0, -b1)).getBlock() != voidCraft.blocks.blockVoidcrystal || world.getBlockState(pos.add(b0, 0, b1)).getBlock() != this))
                    {
                        world.setBlockToAir(pos);
                    }
                }
            }
            else
            {
                world.setBlockToAir(pos);
            }
        }*/
		EnumFacing.Axis enumfacing$axis = (EnumFacing.Axis)state.getValue(AXIS);

        if (enumfacing$axis == EnumFacing.Axis.X)
        {
            BlockPortalVoid.Size blockportal$size = new BlockPortalVoid.Size(world, pos, EnumFacing.Axis.X);

            if (!blockportal$size.func_150860_b() || blockportal$size.field_150864_e < blockportal$size.field_150868_h * blockportal$size.field_150862_g)
            {
            	if(neighborBlock != this) world.setBlockState(pos, Blocks.air.getDefaultState());
            }
        }
        else if (enumfacing$axis == EnumFacing.Axis.Z)
        {
        	BlockPortalVoid.Size blockportal$size1 = new BlockPortalVoid.Size(world, pos, EnumFacing.Axis.Z);

            if (!blockportal$size1.func_150860_b() || blockportal$size1.field_150864_e < blockportal$size1.field_150868_h * blockportal$size1.field_150862_g)
            {
            	if(neighborBlock != this) world.setBlockState(pos, Blocks.air.getDefaultState());
            }
        }
    }

	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand){
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
        for (int l = 0; l < 4; ++l){
            double d0 = (double)((float)x + rand.nextFloat());
            double d1 = (double)((float)y + rand.nextFloat());
            double d2 = (double)((float)z + rand.nextFloat());
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int i1 = rand.nextInt(2) * 2 - 1;
            d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;

            if (world.getBlockState(pos.add(-1, 0, 0)).getBlock() != this && world.getBlockState(pos.add(1, 0, 0)).getBlock() != this)
            {
                d0 = (double)x + 0.5D + 0.25D * (double)i1;
                d3 = (double)(rand.nextFloat() * 2.0F * (float)i1);
            }
            else
            {
                d2 = (double)z + 0.5D + 0.25D * (double)i1;
                d5 = (double)(rand.nextFloat() * 2.0F * (float)i1);
            }

			// par1World.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
			// Minecraft.getMinecraft().effectRenderer.addEffect(new
			// TestFX(par1World, d0, d1, d2));
		}
	}

	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates. Args: blockAccess, x, y, z, side
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		if (worldIn.getBlockState(pos).getBlock() == this) {
			return false;
		} else {
			boolean flag = worldIn.getBlockState(pos.add(-1, 0, 0)).getBlock() == this && worldIn.getBlockState(pos.add(-2, 0, 0)).getBlock() != this;
			boolean flag1 = worldIn.getBlockState(pos.add(1, 0, 0)).getBlock() == this && worldIn.getBlockState(pos.add(2, 0, 0)).getBlock() != this;
			boolean flag2 = worldIn.getBlockState(pos.add(0, 0, -1)).getBlock() == this && worldIn.getBlockState(pos.add(0, 0, -2)).getBlock() != this;
			boolean flag3 = worldIn.getBlockState(pos.add(0, 0, 1)).getBlock() == this && worldIn.getBlockState(pos.add(0, 0, 2)).getBlock() != this;
			boolean flag4 = flag || flag1;
			boolean flag5 = flag2 || flag3;
			return flag4 && side == EnumFacing.WEST ? true : (flag4 && side == EnumFacing.EAST ? true : (flag5 && side == EnumFacing.NORTH ? true : flag5 && side == EnumFacing.SOUTH));
		}
	}
	
	public static class Size
    {
        private final World world;
        private final EnumFacing.Axis axis;
        private final EnumFacing field_150866_c;
        private final EnumFacing field_150863_d;
        private int field_150864_e = 0;
        private BlockPos field_150861_f;
        private int field_150862_g;
        private int field_150868_h;

        public Size(World worldIn, BlockPos p_i45694_2_, EnumFacing.Axis p_i45694_3_)
        {
            this.world = worldIn;
            this.axis = p_i45694_3_;

            if (p_i45694_3_ == EnumFacing.Axis.X)
            {
                this.field_150863_d = EnumFacing.EAST;
                this.field_150866_c = EnumFacing.WEST;
            }
            else
            {
                this.field_150863_d = EnumFacing.NORTH;
                this.field_150866_c = EnumFacing.SOUTH;
            }

            for (BlockPos blockpos = p_i45694_2_; p_i45694_2_.getY() > blockpos.getY() - 21 && p_i45694_2_.getY() > 0 && this.func_150857_a(worldIn.getBlockState(p_i45694_2_.down()).getBlock()); p_i45694_2_ = p_i45694_2_.down())
            {
                ;
            }

            int i = this.func_180120_a(p_i45694_2_, this.field_150863_d) - 1;

            if (i >= 0)
            {
                this.field_150861_f = p_i45694_2_.offset(this.field_150863_d, i);
                this.field_150868_h = this.func_180120_a(this.field_150861_f, this.field_150866_c);
                

                if (this.field_150868_h < 2 || this.field_150868_h > 21)
                {
                    this.field_150861_f = null;
                    this.field_150868_h = 0;
                }
            }

            if (this.field_150861_f != null)
            {
                this.field_150862_g = this.func_150858_a();
            }
        }

        protected int func_180120_a(BlockPos p_180120_1_, EnumFacing p_180120_2_)
        {
            int i;

            for (i = 0; i < 22; ++i)
            {
                BlockPos blockpos = p_180120_1_.offset(p_180120_2_, i);

                if (!this.func_150857_a(this.world.getBlockState(blockpos).getBlock()) || this.world.getBlockState(blockpos.down()).getBlock() != voidCraft.blocks.blockVoidcrystal)
                {
                    break;
                }
            }

            Block block = this.world.getBlockState(p_180120_1_.offset(p_180120_2_, i)).getBlock();
            return block == voidCraft.blocks.blockVoidcrystal ? i : 0;
        }

        public int func_181100_a()
        {
            return this.field_150862_g;
        }

        public int func_181101_b()
        {
            return this.field_150868_h;
        }

        protected int func_150858_a()
        {
            label24:

            for (this.field_150862_g = 0; this.field_150862_g < 21; ++this.field_150862_g)
            {
                for (int i = 0; i < this.field_150868_h; ++i)
                {
                    BlockPos blockpos = this.field_150861_f.offset(this.field_150866_c, i).up(this.field_150862_g);
                    Block block = this.world.getBlockState(blockpos).getBlock();

                    if (!this.func_150857_a(block))
                    {
                        break label24;
                    }

                    if (block == voidCraft.blocks.blockPortalVoid)
                    {
                        ++this.field_150864_e;
                    }

                    if (i == 0)
                    {
                        block = this.world.getBlockState(blockpos.offset(this.field_150863_d)).getBlock();

                        if (block != voidCraft.blocks.blockVoidcrystal)
                        {
                            break label24;
                        }
                    }
                    else if (i == this.field_150868_h - 1)
                    {
                        block = this.world.getBlockState(blockpos.offset(this.field_150866_c)).getBlock();

                        if (block != voidCraft.blocks.blockVoidcrystal)
                        {
                            break label24;
                        }
                    }
                }
            }

            for (int j = 0; j < this.field_150868_h; ++j)
            {
                if (this.world.getBlockState(this.field_150861_f.offset(this.field_150866_c, j).up(this.field_150862_g)).getBlock() != voidCraft.blocks.blockVoidcrystal)
                {
                    this.field_150862_g = 0;
                    break;
                }
            }

            if (this.field_150862_g <= 21 && this.field_150862_g >= 3)
            {
                return this.field_150862_g;
            }
            else
            {
                this.field_150861_f = null;
                this.field_150868_h = 0;
                this.field_150862_g = 0;
                return 0;
            }
        }

        protected boolean func_150857_a(Block p_150857_1_)
        {
            return p_150857_1_.getMaterial() == Material.air || p_150857_1_ == voidCraft.blocks.fireVoid || p_150857_1_ == voidCraft.blocks.blockPortalVoid.getDefaultState();
        }

        public boolean func_150860_b()
        {
            return this.field_150861_f != null && this.field_150868_h >= 2 && this.field_150868_h <= 21 && this.field_150862_g >= 3 && this.field_150862_g <= 21;
        }

        public void func_150859_c()
        {
            for (int i = 0; i < this.field_150868_h; ++i)
            {
                BlockPos blockpos = this.field_150861_f.offset(this.field_150866_c, i);

                for (int j = 0; j < this.field_150862_g; ++j)
                {
                    this.world.setBlockState(blockpos.up(j), voidCraft.blocks.blockPortalVoid.getDefaultState().withProperty(BlockPortalVoid.AXIS, this.axis), 2);
                }
            }
        }
    }

}
