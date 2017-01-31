package Tamaized.Voidcraft.machina;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import Tamaized.TamModized.blocks.TamBlockContainer;
import Tamaized.Voidcraft.api.voidicpower.IVoidicPower;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicPowerCable;

import com.google.common.collect.ImmutableList;

public class VoidicPowerCable extends TamBlockContainer {

	public static final float PIPE_MIN_POS = 0.25f;
	public static final float PIPE_MAX_POS = 0.75f;

	private static AxisAlignedBB bounds = new AxisAlignedBB(PIPE_MIN_POS, PIPE_MIN_POS, PIPE_MIN_POS, PIPE_MAX_POS, PIPE_MAX_POS, PIPE_MAX_POS);

	public static final ImmutableList<IProperty> CONNECTED_PROPERTIES = ImmutableList.copyOf(Stream.of(EnumFacing.VALUES).map(facing -> PropertyBool.create(facing.getName())).collect(Collectors.toList()));

	public static final ImmutableList<AxisAlignedBB> CONNECTED_BOUNDING_BOXES = ImmutableList.copyOf(Stream.of(EnumFacing.VALUES).map(facing -> {
		Vec3i directionVec = facing.getDirectionVec();
		return new AxisAlignedBB(getMinBound(directionVec.getX()), getMinBound(directionVec.getY()), getMinBound(directionVec.getZ()), getMaxBound(directionVec.getX()), getMaxBound(directionVec.getY()), getMaxBound(directionVec.getZ()));
	}).collect(Collectors.toList()));

	private static float getMinBound(int dir) {
		return dir == -1 ? 0 : PIPE_MIN_POS;
	}

	private static float getMaxBound(int dir) {
		return dir == 1 ? 1 : PIPE_MAX_POS;
	}

	public VoidicPowerCable(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, CONNECTED_PROPERTIES.toArray(new IProperty[CONNECTED_PROPERTIES.size()]));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	/**
	 * Is the neighbouring pipe a valid connection for this pipe?
	 *
	 * @param ownState
	 *            This pipe's state
	 * @param neighbourState
	 *            The neighbouring pipe's state
	 * @param world
	 *            The world
	 * @param ownPos
	 *            This pipe's position
	 * @param neighbourDirection
	 *            The direction of the neighbouring pipe
	 * @return Is the neighbouring pipe a valid connection?
	 */
	protected boolean isValidPipe(IBlockState ownState, IBlockState neighbourState, IBlockAccess world, BlockPos ownPos, EnumFacing neighbourDirection) {
		return neighbourState.getBlock() instanceof VoidicPowerCable || world.getTileEntity(ownPos.offset(neighbourDirection)) instanceof IVoidicPower && (((IVoidicPower) world.getTileEntity(ownPos.offset(neighbourDirection))).canInputPower(neighbourDirection.getOpposite()) || ((IVoidicPower) world.getTileEntity(ownPos.offset(neighbourDirection))).canOutputPower(neighbourDirection.getOpposite()));
	}

	/**
	 * Can this pipe connect to the neighbouring block?
	 *
	 * @param ownState
	 *            This pipe's state
	 * @param worldIn
	 *            The world
	 * @param ownPos
	 *            This pipe's position
	 * @param neighbourDirection
	 *            The direction of the neighbouring block
	 * @return Can this pipe connect?
	 */
	private boolean canConnectTo(IBlockState ownState, IBlockAccess worldIn, BlockPos ownPos, EnumFacing neighbourDirection) {
		BlockPos neighbourPos = ownPos.offset(neighbourDirection);
		IBlockState neighbourState = worldIn.getBlockState(neighbourPos);
		Block neighbourBlock = neighbourState.getBlock();

		boolean neighbourIsValidForThis = isValidPipe(ownState, neighbourState, worldIn, ownPos, neighbourDirection);
		boolean thisIsValidForNeighbour = neighbourBlock instanceof VoidicPowerCable && ((VoidicPowerCable) neighbourBlock).isValidPipe(neighbourState, ownState, worldIn, neighbourPos, neighbourDirection.getOpposite());

		return neighbourIsValidForThis && (thisIsValidForNeighbour || worldIn.getTileEntity(ownPos.offset(neighbourDirection)) instanceof IVoidicPower);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		for (EnumFacing facing : EnumFacing.VALUES) {
			state = state.withProperty(CONNECTED_PROPERTIES.get(facing.getIndex()), canConnectTo(state, world, pos, facing));
		}

		return state;
	}

	public final boolean isConnected(IBlockState state, EnumFacing facing) {
		return (boolean) state.getValue(CONNECTED_PROPERTIES.get(facing.getIndex()));
	}

	public void setBlockBounds(AxisAlignedBB bb) {
		bounds = bb;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return bounds;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {
		setBlockBounds(new AxisAlignedBB(PIPE_MIN_POS, PIPE_MIN_POS, PIPE_MIN_POS, PIPE_MAX_POS, PIPE_MAX_POS, PIPE_MAX_POS));
		super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn, p_185477_7_);

		state = getActualState(state, worldIn, pos);

		for (EnumFacing facing : EnumFacing.VALUES) {
			if (isConnected(state, facing)) {
				AxisAlignedBB axisAlignedBB = CONNECTED_BOUNDING_BOXES.get(facing.getIndex());
				setBlockBounds(axisAlignedBB);
				super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn, p_185477_7_);
			}
		}

		setBlockBounds(new AxisAlignedBB(0, 0, 0, 1, 1, 1));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityVoidicPowerCable();
	}

}
