package Tamaized.Voidcraft.machina;

import Tamaized.TamModized.blocks.TamBlockContainer;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicCharger;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

public class VoidicPowerCharger extends TamBlockContainer {

	public VoidicPowerCharger(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness, SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityVoidicCharger();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack s, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) FMLNetworkHandler.openGui(playerIn, VoidCraft.instance, GuiHandler.getTypeID(GuiHandler.Type.VoidicCharger), worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullyOpaque(IBlockState state) {
		return false;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof TileEntityVoidicCharger) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityVoidicCharger) tileentity);
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}

}
