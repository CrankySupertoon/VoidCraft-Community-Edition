package Tamaized.Voidcraft.GUI.server;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.slots.SlotCantRemove;
import Tamaized.Voidcraft.GUI.slots.SlotOnlyItem;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicPower.IVoidicPowerCapability;
import Tamaized.Voidcraft.items.RealityTeleporter;
import Tamaized.Voidcraft.items.inventory.InventoryItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RealityTeleporterContainer extends ContainerBase {

	private final ItemStack parent;
	private final InventoryItem itemInventory;
	private final IVoidicPowerCapability cap;
	private int amount = 0;

	public RealityTeleporterContainer(InventoryPlayer inventory, ItemStack host) {
		parent = host;
		itemInventory = RealityTeleporter.createInventory(parent);
		cap = parent.hasCapability(CapabilityList.VOIDICPOWER, null) ? parent.getCapability(CapabilityList.VOIDICPOWER, null) : null;
		addSlotToContainer(new SlotOnlyItem(Item.getItemFromBlock(VoidCraft.blocks.realityHole), itemInventory, 0, 176, 96));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				int index = j + i * 9 + 9;
				addSlotToContainer(ItemStack.areItemStacksEqual(inventory.getStackInSlot(index), parent) ? new SlotCantRemove(inventory, index, 86 + j * 18, 150 + i * 18) : new Slot(inventory, index, 86 + j * 18, 150 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(ItemStack.areItemStacksEqual(inventory.getStackInSlot(i), parent) ? new SlotCantRemove(inventory, i, 86 + i * 18, 208) : new Slot(inventory, i, 86 + i * 18, 208));
		}

		addSlotToContainer(ItemStack.areItemStacksEqual(inventory.getStackInSlot(inventory.getSizeInventory() - 1), parent) ? new SlotCantRemove(inventory, inventory.getSizeInventory() - 1, 230, 127) : new Slot(inventory, inventory.getSizeInventory() - 1, 230, 127) {

			@SideOnly(Side.CLIENT)
			@Override
			public String getSlotTexture() {
				return "minecraft:items/empty_armor_slot_shield";
			}
		});
	}

	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		itemInventory.closeInventory(playerIn);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < listeners.size(); ++i) {
			IContainerListener icontainerlistener = (IContainerListener) listeners.get(i);
			if (cap != null && amount != cap.getCurrentPower()) {
				icontainerlistener.sendProgressBarUpdate(this, 0, cap.getCurrentPower());
				amount = cap.getCurrentPower();
				itemInventory.saveData();
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int i, int v) {
		if (i == 0 && cap != null) cap.setCurrentPower(v);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int hoverSlot) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) inventorySlots.get(hoverSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			final int maxSlots = itemInventory.getSizeInventory();

			if (hoverSlot < maxSlots) {
				if (!mergeItemStack(itemstack1, maxSlots, maxSlots + 36, true)) {
					return ItemStack.EMPTY;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else {
				ItemStack slotCheck = itemInventory.getStackInSlot(0);
				if ((slotCheck.isEmpty() || (slotCheck.getCount() < slotCheck.getMaxStackSize() && slotCheck.isItemEqual(itemstack))) && itemInventory.isItemValidForSlot(0, itemstack1)) {
					if (!mergeItemStack(itemstack1, 0, 0 + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (hoverSlot >= maxSlots && hoverSlot < maxSlots + 27) {
					if (!mergeItemStack(itemstack1, maxSlots + 27, maxSlots + 36, false)) {
						return ItemStack.EMPTY;
					}
				} else if (hoverSlot >= maxSlots + 27 && hoverSlot < maxSlots + 36) {
					if (!mergeItemStack(itemstack1, maxSlots, maxSlots + 27, false)) {
						return ItemStack.EMPTY;
					}
				} else {
					if (!mergeItemStack(itemstack1, maxSlots, maxSlots + 36, false)) {
						return ItemStack.EMPTY;
					}
				}
			}

			if (itemstack1.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, itemstack1);
		}
		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
}