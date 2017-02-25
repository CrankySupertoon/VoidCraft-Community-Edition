package Tamaized.Voidcraft.GUI.server;

import Tamaized.Voidcraft.GUI.slots.SlotVadeMecumSpell;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.handlers.VadeMecumWordsOfPower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VadeMecumSpellsContainer extends Container {

	private final InventoryPlayer inventory;
	private final IVadeMecumCapability capability;
	private int index = 0;

	public VadeMecumSpellsContainer(InventoryPlayer inv, IVadeMecumCapability cap) {
		inventory = inv;
		capability = cap;
		initSlots(0, 0);
	}

	public void initSlots(int left, int top) {
		int xLoc = 140 - left;
		int yLoc = 30 - top;

		inventorySlots.clear();
		inventoryItemStacks.clear();

		index = 0;
		for (IVadeMecumCapability.Category cat : capability.getAvailableActivePowers()) {
			addSlotToContainer(new SlotVadeMecumSpell(capability, cat, VadeMecumWordsOfPower.getCategoryData(cat).getStack().getItem(), index, xLoc + (135 * ((int) Math.floor(index / 5) - 1)), yLoc + (25 * (index % 5) - 1)));
			index++;
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventory, j + (i * 9) + 9, 8 + j * 18, 8 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 66));
		}

		addSlotToContainer(new Slot(inventory, inventory.getSizeInventory() - 1, -20, 0) {
			@SideOnly(Side.CLIENT)
			@Override
			public String getSlotTexture() {
				return "minecraft:items/empty_armor_slot_shield";
			}
		});
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int hoverSlot) {
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(hoverSlot);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			final int maxSlots = index;
			if (hoverSlot < maxSlots) {
				if (!mergeItemStack(itemstack1, maxSlots, maxSlots + 36, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else {
				if (mergeItemStack(itemstack1, 0, maxSlots, false)) {
					
				} else if (hoverSlot >= maxSlots && hoverSlot < maxSlots + 27) {
					if (!mergeItemStack(itemstack1, maxSlots + 27, maxSlots + 36, false)) {
						return null;
					}
				} else if (hoverSlot >= maxSlots + 27 && hoverSlot < maxSlots + 36) {
					if (!mergeItemStack(itemstack1, maxSlots, maxSlots + 27, false)) {
						return null;
					}
				} else {
					if (!mergeItemStack(itemstack1, maxSlots, maxSlots + 36, false)) {
						return null;
					}
				}
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}
			slot.func_82870_a(player, itemstack1);
		}
		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
}