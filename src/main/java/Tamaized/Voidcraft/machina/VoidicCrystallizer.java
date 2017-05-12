package Tamaized.Voidcraft.machina;

import Tamaized.TamModized.blocks.TamBlockContainer;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicAnchor;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicCrystallizer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

public class VoidicCrystallizer extends TamBlockContainer {

	public VoidicCrystallizer(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness, SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityVoidicCrystallizer();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack facing, EnumFacing hitX, float hitY, float hitZ, float p_180639_10_) {
		if (!worldIn.isRemote) FMLNetworkHandler.openGui(playerIn, VoidCraft.instance, GuiHandler.getTypeID(GuiHandler.Type.VoidicCrystallizer), worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
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
}
