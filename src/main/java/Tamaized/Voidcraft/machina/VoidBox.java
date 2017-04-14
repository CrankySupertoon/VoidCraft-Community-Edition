package Tamaized.Voidcraft.machina;

import java.util.Random;

import Tamaized.TamModized.blocks.TamBlockContainer;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

public class VoidBox extends TamBlockContainer {

	private Random rand = new Random();

	public VoidBox(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness, SoundType.METAL);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack s, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			FMLNetworkHandler.openGui(playerIn, VoidCraft.instance, GuiHandler.getTypeID(GuiHandler.Type.MusicBox), worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

	private void setDefaultDirection(World world, BlockPos pos) {
		if (!world.isRemote) {
			Block l = world.getBlockState(pos.add(0, 0, -1)).getBlock();
			Block il = world.getBlockState(pos.add(0, 0, 1)).getBlock();
			Block jl = world.getBlockState(pos.add(-1, 0, 0)).getBlock();
			Block kl = world.getBlockState(pos.add(1, 0, 0)).getBlock();

			byte b0 = 3;

			world.setBlockState(pos, this.getStateFromMeta(b0), 2);
		}
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		int l = MathHelper.floor((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			world.setBlockState(pos, this.getStateFromMeta(2), 2);
		}

		if (l == 1) {
			world.setBlockState(pos, this.getStateFromMeta(5), 2);
		}

		if (l == 2) {
			world.setBlockState(pos, this.getStateFromMeta(3), 2);
		}

		if (l == 3) {
			world.setBlockState(pos, this.getStateFromMeta(4), 2);
		}

	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote) {
			TileEntityVoidBox tileentity = (TileEntityVoidBox) world.getTileEntity(pos);

			if (tileentity != null) {
				tileentity.StopTheSound();
				InventoryHelper.dropInventoryItems(world, pos, tileentity);
			}
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityVoidBox();
	}
}
