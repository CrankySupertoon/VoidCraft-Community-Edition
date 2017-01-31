package Tamaized.Voidcraft.blocks;

import java.util.ArrayList;

import Tamaized.TamModized.blocks.TamBlockFarmland;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityFakeBedrockFarmland;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class BlockFakeBedrockFarmland extends TamBlockFarmland {

	public BlockFakeBedrockFarmland(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness);
	}

	@Override
	protected IBlockState getParentBlockState() {
		return VoidCraft.blocks.blockFakeBedrock.getDefaultState();
	}

	@Override
	protected Block getWaterBlock() {
		return VoidCraft.fluids.voidFluidBlock;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityFakeBedrockFarmland();
	}

	@Override
	protected void updateTiles(TileEntity oldTile, TileEntity newTile) {
		if (oldTile != null && newTile != null && oldTile instanceof TileEntityFakeBedrockFarmland && newTile instanceof TileEntityFakeBedrockFarmland) {
			((TileEntityFakeBedrockFarmland) newTile).setAlteration(((TileEntityFakeBedrockFarmland) oldTile).getAlteration());
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack stack = playerIn.getHeldItem(hand);
		if (!stack.isEmpty() && worldIn.isAirBlock(pos.up())) {
			TileEntity t = worldIn.getTileEntity(pos);
			if (!(t instanceof TileEntityFakeBedrockFarmland)) return false;
			TileEntityFakeBedrockFarmland te = (TileEntityFakeBedrockFarmland) t;
			if (te.getAlteration() != TileEntityFakeBedrockFarmland.Alteration.NORMAL) return false;
			Item item = stack.getItem();
			if (item == Items.REDSTONE) {
				setColor(te, TileEntityFakeBedrockFarmland.Alteration.REDSTONE, stack);
				return true;
			}
			if (item == VoidCraft.items.lapisDust) {
				setColor(te, TileEntityFakeBedrockFarmland.Alteration.LAPIS, stack);
				return true;
			}
			if (item == VoidCraft.items.diamondDust) {
				setColor(te, TileEntityFakeBedrockFarmland.Alteration.DIAMOND, stack);
				return true;
			}
			if (item == VoidCraft.items.emeraldDust) {
				setColor(te, TileEntityFakeBedrockFarmland.Alteration.EMERALD, stack);
				return true;
			}
			if (item == VoidCraft.items.goldDust) {
				setColor(te, TileEntityFakeBedrockFarmland.Alteration.GOLD, stack);
				return true;
			}
		}
		return false;
	}

	private static void setColor(TileEntityFakeBedrockFarmland tile, TileEntityFakeBedrockFarmland.Alteration alter, ItemStack stack) {
		tile.setAlteration(alter);
		stack.shrink(1);
	}

	@Override
	protected ArrayList<IPlantable> getPlantList() {
		ArrayList<IPlantable> list = new ArrayList<IPlantable>();
		list.add(VoidCraft.blocks.etherealPlant);
		list.add(VoidCraft.items.etherealSeed);
		return list;
	}

}
