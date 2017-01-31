package Tamaized.Voidcraft.GUI.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotOnlyItem extends Slot {
	
	private final Item item;

	public SlotOnlyItem(Item validItem, IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		item = validItem;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return !stack.isEmpty() && stack.getItem() == item;
	}

}
