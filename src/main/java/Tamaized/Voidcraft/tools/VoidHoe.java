package Tamaized.Voidcraft.tools;

import Tamaized.TamModized.tools.TamHoe;
import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VoidHoe extends TamHoe {

	public VoidHoe(CreativeTabs tab, ToolMaterial material, String n) {
		super(tab, material, n);
	}

	/**
	 * Called when a Block is right-clicked with this Item
	 */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = playerIn.getHeldItem(hand);
		if (!playerIn.canPlayerEdit(pos.offset(facing), facing, stack)) {
			return EnumActionResult.FAIL;
		} else {
			int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(stack, playerIn, worldIn, pos);
			if (hook != 0) return hook > 0 ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;

			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (block == VoidCraft.blocks.blockFakeBedrock && facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up())) {
				this.setBlock(stack, playerIn, worldIn, pos, VoidCraft.blocks.blockFakeBedrockFarmland.getDefaultState());
				return EnumActionResult.SUCCESS;
			}
			return super.onItemUse(playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		}
	}

}
