package Tamaized.Voidcraft.GUI.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotOnlyClass extends Slot {
	
	private final Class clazz;

	public SlotOnlyClass(Class validClass, IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		clazz = validClass;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return !stack.isEmpty() && clazz.isInstance(stack.getItem());
	}

}
