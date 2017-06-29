package tamaized.voidcraft.common.gui.container;

import tamaized.voidcraft.common.gui.slots.SlotCantPlace;
import tamaized.voidcraft.common.gui.slots.SlotItemHandlerBypass;
import tamaized.voidcraft.common.machina.tileentity.TileEntityVoidInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VoidInfuserContainer extends Container {

	private TileEntityVoidInfuser te;
	private int amount;
	private int cookAmount = 0;

	public VoidInfuserContainer(InventoryPlayer inventory, TileEntityVoidInfuser tileEntity) {
		te = tileEntity;

		addSlotToContainer(new SlotItemHandlerBypass(tileEntity.SLOT_BUCKET, 0, 130, 100));
		addSlotToContainer(new SlotItemHandlerBypass(tileEntity.SLOT_INPUT, 0, 168, 100));
		addSlotToContainer(new SlotCantPlace(tileEntity.SLOT_OUTPUT, 0, 225, 101));

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

		for (IContainerListener icontainerlistener : listeners) {
			if (this.cookAmount != te.cookingTick) {
				cookAmount = te.cookingTick;
				icontainerlistener.sendWindowProperty(this, 0, cookAmount);
				cookAmount = te.cookingTick;
			}

			if (amount != te.getFluidAmount()) {
				icontainerlistener.sendWindowProperty(this, 1, te.getFluidAmount());
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
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < te.getInventorySize()) {
				if (!this.mergeItemStack(itemstack1, te.getInventorySize(), this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, te.getInventorySize(), false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.te.canInteractWith(entityplayer);
	}
}