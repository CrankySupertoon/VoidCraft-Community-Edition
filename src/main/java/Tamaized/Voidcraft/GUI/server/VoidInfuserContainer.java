package Tamaized.Voidcraft.GUI.server;

import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VoidInfuserContainer extends ContainerBase {

	private TileEntityVoidInfuser te;
	private int amount;
	private int cookAmount = 0;

	public VoidInfuserContainer(InventoryPlayer inventory, TileEntityVoidInfuser tileEntity) {
		te = tileEntity;

		addSlotToContainer(new Slot(tileEntity, 0, 130, 100));
		addSlotToContainer(new Slot(tileEntity, 1, 168, 100));
		addSlotToContainer(new SlotFurnaceOutput(inventory.player, tileEntity, 2, 225, 101));

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

			if (this.cookAmount != te.cookingTick) {
				cookAmount = te.cookingTick;
				icontainerlistener.sendProgressBarUpdate(this, 0, cookAmount);
				cookAmount = te.cookingTick;
			}

			if (amount != te.getFluidAmount()) {
				icontainerlistener.sendProgressBarUpdate(this, 1, te.getFluidAmount());
				amount = te.getFluidAmount();
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int par2) {
		if (slot == 0) te.cookingTick = par2;
		if (slot == 1) te.setFluidAmount(par2);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int hoverSlot) {
		/*
		 * array[] = {a, b, c} array[].length = 3 mergeItemStack 3rd param needs to be 1 higher than our actual length, so we do 1+length because array[].length returns AMOUNT we must subtract 1 to get to index 0 main inv = 9*3 = 27; subtract 1 for index 0 we get 26. Translation : min:(array[].length-1)+1; max:(array[].length-1)+27; This gets us 3-30 hot bar: 9 slots; -1 for index 0 so 0-8; after Translation: min(array[].length-1)+1+mainInvMaxNoShift(27)+1; max:min(array[].length-1)+1+mainInvMaxNoShift(27)+8+1; this gets us 3+27+1 to 3+27+8+1 or 31-39 So, we can shorten all this nicely +Main (arrayLength) to (arrayLength+27) +Hotbar (Main.max+1) to (Main.max+9)
		 */
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
				ItemStack slotCheck = te.getStackInSlot(te.SLOT_INPUT);
				if ((slotCheck.isEmpty() || (slotCheck.getCount() < slotCheck.getMaxStackSize() && slotCheck.isItemEqual(itemstack))) && te.canInsertItem(te.SLOT_INPUT, itemstack1, null)) {
					if (!mergeItemStack(itemstack1, te.SLOT_INPUT, te.SLOT_INPUT + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (!getSlot(te.SLOT_BUCKET).getHasStack() && te.canInsertItem(te.SLOT_BUCKET, itemstack1, null)) {
					if (!mergeItemStack(itemstack1, te.SLOT_BUCKET, te.SLOT_BUCKET + 1, false)) {
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