package Tamaized.Voidcraft.items;

import Tamaized.TamModized.items.TamItem;
import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class EmptyObsidianFlask extends TamItem {

	public EmptyObsidianFlask(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (worldIn.provider.getDimension() != VoidCraft.config.getDimensionIDvoid() && player.getPosition().getY() > 6) return ActionResult.newResult(EnumActionResult.FAIL, stack);
		stack.shrink(1);
		ItemStack newStack = new ItemStack(VoidCraft.items.obsidianFlask, 1);
		ItemHandlerHelper.giveItemToPlayer(player, newStack);
		return ActionResult.newResult(EnumActionResult.PASS, stack);
	}

}