package Tamaized.Voidcraft.GUI.server;

import Tamaized.Voidcraft.GUI.slots.SlotCantPlace;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicAlchemy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VoidicAlchemyContainer extends ContainerBase {

	private TileEntityVoidicAlchemy te;

	private int powerAmount = 0;
	private int cookTick = 0;
	private int finishTick = 0;

	public VoidicAlchemyContainer(InventoryPlayer inventory, TileEntityVoidicAlchemy tileEntity) {
		this.te = tileEntity;

		this.addSlotToContainer(new SlotCantPlace(tileEntity, te.SLOT_OUTPUT, 176, 97));
		this.addSlotToContainer(new Slot(tileEntity, te.SLOT_INPUT_1, 152, 72));
		this.addSlotToContainer(new Slot(tileEntity, te.SLOT_INPUT_2, 141, 97));
		this.addSlotToContainer(new Slot(tileEntity, te.SLOT_INPUT_3, 152, 122));
		this.addSlotToContainer(new Slot(tileEntity, te.SLOT_INPUT_4, 200, 72));
		this.addSlotToContainer(new Slot(tileEntity, te.SLOT_INPUT_5, 211, 97));
		this.addSlotToContainer(new Slot(tileEntity, te.SLOT_INPUT_6, 200, 122));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 86 + j * 18, 150 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 86 + i * 18, 208));
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

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icontainerlistener = (IContainerListener) this.listeners.get(i);

			if (this.cookTick != te.cookingTick) {
				cookTick = te.cookingTick;
				icontainerlistener.sendProgressBarUpdate(this, 0, cookTick);
			}

			if (this.finishTick != te.finishTick) {
				finishTick = te.finishTick;
				icontainerlistener.sendProgressBarUpdate(this, 1, finishTick);
			}

			if (this.powerAmount != te.getPowerAmount()) {
				powerAmount = te.getPowerAmount();
				icontainerlistener.sendProgressBarUpdate(this, 2, powerAmount);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int par2) {
		if (slot == 0) te.cookingTick = par2;
		if (slot == 1) te.finishTick = par2;
		if (slot == 2) te.setPowerAmount(par2);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int hoverSlot) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(hoverSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			final int maxSlots = te.getSizeInventory();

			if (hoverSlot < maxSlots) {
				if (!mergeItemStack(itemstack1, maxSlots, maxSlots + 36, true)) {
					return ItemStack.EMPTY;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else {
				ItemStack slotCheck1 = te.getStackInSlot(te.SLOT_INPUT_1);
				ItemStack slotCheck2 = te.getStackInSlot(te.SLOT_INPUT_2);
				ItemStack slotCheck3 = te.getStackInSlot(te.SLOT_INPUT_3);
				ItemStack slotCheck4 = te.getStackInSlot(te.SLOT_INPUT_4);
				ItemStack slotCheck5 = te.getStackInSlot(te.SLOT_INPUT_5);
				ItemStack slotCheck6 = te.getStackInSlot(te.SLOT_INPUT_6);
				if ((slotCheck1.isEmpty() || (slotCheck1.getCount() < slotCheck1.getMaxStackSize() && slotCheck1.isItemEqual(itemstack))) && te.canInsertItem(te.SLOT_INPUT_1, itemstack1, null)) {
					if (!mergeItemStack(itemstack1, te.SLOT_INPUT_1, te.SLOT_INPUT_1 + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if ((slotCheck2.isEmpty() || (slotCheck2.getCount() < slotCheck2.getMaxStackSize() && slotCheck2.isItemEqual(itemstack))) && te.canInsertItem(te.SLOT_INPUT_2, itemstack1, null)) {
					if (!mergeItemStack(itemstack1, te.SLOT_INPUT_2, te.SLOT_INPUT_2 + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if ((slotCheck3.isEmpty() || (slotCheck3.getCount() < slotCheck3.getMaxStackSize() && slotCheck3.isItemEqual(itemstack))) && te.canInsertItem(te.SLOT_INPUT_3, itemstack1, null)) {
					if (!mergeItemStack(itemstack1, te.SLOT_INPUT_3, te.SLOT_INPUT_3 + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if ((slotCheck4.isEmpty() || (slotCheck4.getCount() < slotCheck4.getMaxStackSize() && slotCheck4.isItemEqual(itemstack))) && te.canInsertItem(te.SLOT_INPUT_4, itemstack1, null)) {
					if (!mergeItemStack(itemstack1, te.SLOT_INPUT_4, te.SLOT_INPUT_4 + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if ((slotCheck5.isEmpty() || (slotCheck5.getCount() < slotCheck5.getMaxStackSize() && slotCheck5.isItemEqual(itemstack))) && te.canInsertItem(te.SLOT_INPUT_5, itemstack1, null)) {
					if (!mergeItemStack(itemstack1, te.SLOT_INPUT_5, te.SLOT_INPUT_5 + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if ((slotCheck6.isEmpty() || (slotCheck6.getCount() < slotCheck6.getMaxStackSize() && slotCheck6.isItemEqual(itemstack))) && te.canInsertItem(te.SLOT_INPUT_1, itemstack1, null)) {
					if (!mergeItemStack(itemstack1, te.SLOT_INPUT_6, te.SLOT_INPUT_6 + 1, false)) {
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
		return te.isUsableByPlayer(entityplayer);
	}

}
