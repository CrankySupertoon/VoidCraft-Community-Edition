package Tamaized.Voidcraft.machina;

import java.util.Random;

import Tamaized.TamModized.blocks.TamBlockContainer;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBlastFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VoidBlastFurnace extends TamBlockContainer {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	private Random rand = new Random();

	public VoidBlastFurnace(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return getDefaultState().withProperty(FACING, enumfacing);
	}

	@SideOnly(Side.CLIENT)
	public IBlockState getStateForEntityRender(IBlockState state) {
		return getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (world.getTileEntity(pos) instanceof TileEntityVoidBlastFurnace && ((TileEntityVoidBlastFurnace) world.getTileEntity(pos)).isCooking()) {
			float x1 = (float) pos.getX() + 0.5F;
			float y1 = (float) pos.getY() + rand.nextFloat();
			float z1 = (float) pos.getZ() + 0.5F;

			float f = 0.52F;
			float f1 = rand.nextFloat() * 0.6F - 0.3F;

			switch (state.getValue(FACING)) {
				case NORTH:
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 - f), 0D, 0D, -1D);
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 - f), 0D, 0D, -1D);
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 - f), 0D, 0D, -1D);
					break;
				case SOUTH:
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 + f), 0D, 0D, 1D);
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 + f), 0D, 0D, 1D);
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 + f), 0D, 0D, 1D);
					break;
				case EAST:
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f), (double) (y1), (double) (z1 + f1), 1D, 0D, 0D);
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f), (double) (y1), (double) (z1 + f1), 1D, 0D, 0D);
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f), (double) (y1), (double) (z1 + f1), 1D, 0D, 0D);
					break;
				case WEST:
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 - f), (double) (y1), (double) (z1 + f1), -1D, 0D, 0D);
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 - f), (double) (y1), (double) (z1 + f1), -1D, 0D, 0D);
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 - f), (double) (y1), (double) (z1 + f1), -1D, 0D, 0D);
					break;
				default:
					break;
			}
		}
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote) {
			IBlockState iblockstate = world.getBlockState(pos.north());
			IBlockState iblockstate1 = world.getBlockState(pos.south());
			IBlockState iblockstate2 = world.getBlockState(pos.west());
			IBlockState iblockstate3 = world.getBlockState(pos.east());
			EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

			if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
				enumfacing = EnumFacing.SOUTH;
			} else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
				enumfacing = EnumFacing.NORTH;
			} else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
				enumfacing = EnumFacing.EAST;
			} else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
				enumfacing = EnumFacing.WEST;
			}
			world.setBlockState(pos, state.withProperty(FACING, enumfacing));
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack facing, EnumFacing hitX, float hitY, float hitZ, float p_180639_10_) {
		ItemStack heldItem = player.getHeldItem(hand);
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, VoidCraft.instance, GuiHandler.getTypeID(GuiHandler.Type.BlastFurnace), world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityVoidBlastFurnace();
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(VoidCraft.blocks.voidBlastFurnace);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof TileEntityVoidBlastFurnace) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityVoidBlastFurnace) tileentity);
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}
}