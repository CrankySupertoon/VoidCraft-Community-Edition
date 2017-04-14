package Tamaized.Voidcraft.blocks;

import Tamaized.TamModized.blocks.TamBlockContainer;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AIBlock extends TamBlockContainer {

	public static final PropertyInteger STATE = PropertyInteger.create("state", 0, 4);

	public AIBlock(String string) {
		super(null, Material.GLASS, string, -1, SoundType.GLASS);
		setBlockUnbreakable();
		setDefaultState(this.blockState.getBaseState().withProperty(STATE, 0));
	}

	public static TileEntityAIBlock getMyTileEntity(World world, BlockPos pos) {
		Block b = world.getBlockState(pos.add(0, -1, 0)).getBlock();
		if (b instanceof AIBlock) {
			return ((AIBlock) b).getMyTileEntity(world, pos.add(0, -1, 0));
		}
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TileEntityAIBlock) return (TileEntityAIBlock) te;
		else return null;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityAIBlock();
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		world.setBlockToAir(pos);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TileEntityAIBlock) {
			TileEntityAIBlock teAI = (TileEntityAIBlock) te;
		}

		super.breakBlock(world, pos, state);
	}

	/**
	 * Override this and return getDefaultState() if hasAxis is false
	 */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STATE, meta);
	}

	/**
	 * Override this and return 0 if hasAxis is false
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(STATE);
	}

	/**
	 * Override this and return 'new BlockState(this, new IProperty[0])' if hasAxis is false
	 */
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STATE });
	}

}
