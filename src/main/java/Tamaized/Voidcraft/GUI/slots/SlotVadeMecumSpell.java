package Tamaized.Voidcraft.GUI.slots;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotVadeMecumSpell extends SlotOnlyItem {

	private final IVadeMecumCapability capability;
	private final IVadeMecumCapability.Category category;

	public SlotVadeMecumSpell(IVadeMecumCapability cap, IVadeMecumCapability.Category cat, Item validItem, int index, int xPosition, int yPosition) {
		super(validItem, null, index, xPosition, yPosition);
		capability = cap;
		category = cat;
	}

	public IVadeMecumCapability getCapability() {
		return capability;
	}

	@Override
	public ItemStack getStack() {
		return capability.getStackInSlot(category);
	}

	@Override
	public void putStack(ItemStack stack) {
		capability.setStackSlot(category, stack);
		onSlotChanged();
	}

	@Override
	public void onSlotChanged() {

	}

	@Override
	public int getSlotStackLimit() {
		return 64;
	}

	@Override
	public ItemStack decrStackSize(int amount) {
		return capability.decrStackSize(category, amount);
	}

	@Override
	public boolean isHere(IInventory inv, int slotIn) {
		return super.isHere(inv, slotIn);
	}

	@Override
	public boolean isSameInventory(Slot slot) {
		return slot instanceof SlotVadeMecumSpell ? ((SlotVadeMecumSpell)slot).getCapability() == capability : false;
	}

}
