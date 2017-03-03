package Tamaized.Voidcraft.GUI.server;

import Tamaized.Voidcraft.GUI.slots.SlotAdjustedMaxSize;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HeimdallContainer extends Container {

	private TileEntityHeimdall te;
	private int fluidAmount;
	private int powerAmount;

	public HeimdallContainer(InventoryPlayer inventory, TileEntityHeimdall tileEntity) {
		te = tileEntity;

		addSlotToContainer(new Slot(tileEntity, tileEntity.SLOT_INPUT, 158, 87));
		addSlotToContainer(new SlotAdjustedMaxSize(tileEntity, tileEntity.SLOT_BUCKET, 158, 114, 1));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 86 + j * 18, 150 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventory, i, 86 + i * 18, 208));
		}

		addSlotToContainer(new Slot(inventory, inventory.getSizeInventory() - 1, 230, 127) {

			@SideOnly(Side.CLIENT)
			@Override
			public String getSlotTexture() {
				return "minecraft:items/empty_armor_slot_shield";
			}
		});
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < listeners.size(); ++i) {
			IContainerListener icontainerlistener = (IContainerListener) listeners.get(i);
			if (fluidAmount != te.getFluidAmount()) icontainerlistener.sendProgressBarUpdate(this, 0, te.getFluidAmount());
			if (powerAmount != te.getEnergyStored()) icontainerlistener.sendProgressBarUpdate(this, 1, te.getEnergyStored());
		}
		fluidAmount = te.getFluidAmount();
		powerAmount = te.getEnergyStored();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int index, int value) {
		switch (index) {
			case 0:
				te.setFluidAmount(value);
				break;
			case 1:
				te.setEnergyAmount(value);
				break;
			default:
				break;
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int hoverSlot) {
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(hoverSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			final int maxSlots = te.getSizeInventory();

			if (hoverSlot < maxSlots) {
				if (!mergeItemStack(itemstack1, maxSlots, maxSlots + 36, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else {
				ItemStack slotCheck = te.getStackInSlot(te.SLOT_INPUT);
				if ((slotCheck == null || (slotCheck.stackSize < slotCheck.getMaxStackSize() && slotCheck.isItemEqual(itemstack))) && te.canInsertItem(te.SLOT_INPUT, itemstack1, null)) {
					if (!mergeItemStack(itemstack1, te.SLOT_INPUT, te.SLOT_INPUT + 1, false)) {
						return null;
					}
				} else if (!getSlot(te.SLOT_BUCKET).getHasStack() && te.canInsertItem(te.SLOT_BUCKET, itemstack1, null)) {
					if (!mergeItemStack(itemstack1, te.SLOT_BUCKET, te.SLOT_BUCKET + 1, false)) {
						return null;
					}
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
	protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
		boolean flag = false;
		int i = startIndex;

		if (reverseDirection) {
			i = endIndex - 1;
		}

		if (stack.isStackable()) {
			while (stack != null) {
				if (reverseDirection) {
					if (i < startIndex) {
						break;
					}
				} else if (i >= endIndex) {
					break;
				}

				Slot slot = (Slot) this.inventorySlots.get(i);
				ItemStack itemstack = slot.getStack();

				if (itemstack != null && itemstack.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getMetadata() == itemstack.getMetadata()) && ItemStack.areItemStackTagsEqual(stack, itemstack)) {
					int j = itemstack.stackSize + stack.stackSize;
					int maxSize = Math.min(slot.getSlotStackLimit(), stack.getMaxStackSize());

					if (j <= maxSize) {
						stack.stackSize = (0);
						stack = null;
						itemstack.stackSize = (j);
						slot.onSlotChanged();
						flag = true;
					} else if (itemstack.stackSize < maxSize) {
						stack.stackSize -= (maxSize - itemstack.stackSize);
						itemstack.stackSize = (maxSize);
						slot.onSlotChanged();
						flag = true;
					}
				}

				if (reverseDirection) {
					--i;
				} else {
					++i;
				}
			}
		}

		if (stack != null) {
			if (reverseDirection) {
				i = endIndex - 1;
			} else {
				i = startIndex;
			}

			while (true) {
				if (reverseDirection) {
					if (i < startIndex) {
						break;
					}
				} else if (i >= endIndex) {
					break;
				}

				Slot slot1 = (Slot) this.inventorySlots.get(i);
				ItemStack itemstack1 = slot1.getStack();

				if (itemstack1 == null && slot1.isItemValid(stack)) {
					if (stack.stackSize > slot1.getSlotStackLimit()) {
						slot1.putStack(stack.splitStack(slot1.getSlotStackLimit()));
					} else {
						slot1.putStack(stack.splitStack(stack.stackSize));
					}

					slot1.onSlotChanged();
					flag = true;
					break;
				}

				if (reverseDirection) {
					--i;
				} else {
					++i;
				}
			}
		}

		return flag;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return te.isUsableByPlayer(entityplayer);
	}
}