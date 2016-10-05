package Tamaized.Voidcraft.machina;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import Tamaized.TamModized.blocks.TamBlockContainer;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicPowerGen;

public class VoidicPowerGen extends TamBlockContainer {

	public VoidicPowerGen(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityVoidicPowerGen();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) FMLNetworkHandler.openGui(playerIn, voidCraft.instance, GuiHandler.getTypeID(GuiHandler.Type.VoidicGenerator), worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

}
