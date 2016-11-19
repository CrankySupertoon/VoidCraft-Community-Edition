package Tamaized.Voidcraft.GUI.server;

import Tamaized.Voidcraft.GUI.slots.SlotAdjustedMaxSize;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HeimdallContainer extends ContainerBase {

	private TileEntityHeimdall te;
	private int fluidAmount;
	private int powerAmount;

	public HeimdallContainer(InventoryPlayer inventory, TileEntityHeimdall tileEntity) {
		te = tileEntity;

		addSlotToContainer(new SlotAdjustedMaxSize(tileEntity, tileEntity.SLOT_BUCKET, 158, 114, 1));
		addSlotToContainer(new Slot(tileEntity, tileEntity.SLOT_INPUT, 158, 87));

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
		ItemStack itemstack = ItemStack.field_190927_a;
		Slot slot = (Slot) inventorySlots.get(hoverSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (hoverSlot == te.SLOT_BUCKET || hoverSlot == te.SLOT_INPUT) {
				if (!mergeItemStack(itemstack1, 2, 38, true)) {
					return ItemStack.field_190927_a;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else {
				if (!getSlot(te.SLOT_BUCKET).getHasStack() && te.canInsertItem(te.SLOT_BUCKET, itemstack1, null)) {
					if (!mergeItemStack(itemstack1, te.SLOT_BUCKET, te.SLOT_BUCKET + 1, false)) {
						return ItemStack.field_190927_a;
					}
				} else if (!getSlot(te.SLOT_INPUT).getHasStack() && te.canInsertItem(te.SLOT_INPUT, itemstack1, null)) {
					if (!mergeItemStack(itemstack1, te.SLOT_INPUT, te.SLOT_INPUT + 1, false)) {
						return ItemStack.field_190927_a;
					}
				} else if (hoverSlot >= 2 && hoverSlot < 29) {
					if (!mergeItemStack(itemstack1, 29, 38, false)) {
						return ItemStack.field_190927_a;
					}
				} else if (hoverSlot >= 29 && hoverSlot < 38) {
					if (!mergeItemStack(itemstack1, 2, 29, false)) {
						return ItemStack.field_190927_a;
					}
				}
			}

			if (itemstack1.func_190916_E() == 0) {
				slot.putStack(ItemStack.field_190927_a);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.func_190916_E() == itemstack.func_190916_E()) {
				return ItemStack.field_190927_a;
			}

			slot.func_190901_a(player, itemstack1);
		}
		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return te.isUseableByPlayer(entityplayer);
	}
}