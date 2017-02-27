package Tamaized.Voidcraft.GUI.server;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.slots.SlotOnlyClass;
import Tamaized.Voidcraft.GUI.slots.SlotOnlyItem;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityStarForge;
import Tamaized.Voidcraft.events.client.TextureStitch;
import Tamaized.Voidcraft.starforge.IStarForgeTool;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StarForgeContainer extends Container {

	private TileEntityStarForge te;

	public StarForgeContainer(InventoryPlayer inventory, TileEntityStarForge tileEntity) {
		te = tileEntity;

		addSlotToContainer(new SlotOnlyClass(IStarForgeTool.class, tileEntity, te.SLOT_INPUT_TOOL, 81, 84) {
			@SideOnly(Side.CLIENT)
			@Override
			public String getSlotTexture() {
				return TextureStitch.guiSlot_Tool.getIconName();
			}
		});

		addSlotToContainer(new SlotOnlyItem(Item.getItemFromBlock(VoidCraft.blocks.cosmicMaterial), tileEntity, te.SLOT_INPUT_COSMICMATERIAL, 37, 105) {
			@SideOnly(Side.CLIENT)
			@Override
			public String getSlotTexture() {
				return TextureStitch.guiSlot_Block.getIconName();
			}
		});

		addSlotToContainer(new SlotOnlyItem(VoidCraft.items.voidicDragonScale, tileEntity, te.SLOT_INPUT_DRAGONSCALE, 59, 105) {
			@SideOnly(Side.CLIENT)
			@Override
			public String getSlotTexture() {
				return TextureStitch.guiSlot_Dragonscale.getIconName();
			}
		});

		addSlotToContainer(new SlotOnlyItem(VoidCraft.items.quoriFragment, tileEntity, te.SLOT_INPUT_QUORIFRAGMENT, 81, 105) {
			@SideOnly(Side.CLIENT)
			@Override
			public String getSlotTexture() {
				return TextureStitch.guiSlot_QuoriFragment.getIconName();
			}
		});

		addSlotToContainer(new SlotOnlyItem(VoidCraft.items.astralEssence, tileEntity, te.SLOT_INPUT_ASTRALESSENCE, 103, 105) {
			@SideOnly(Side.CLIENT)
			@Override
			public String getSlotTexture() {
				return TextureStitch.guiSlot_AstralEsssence.getIconName();
			}
		});

		addSlotToContainer(new SlotOnlyItem(VoidCraft.items.voidicPhlogiston, tileEntity, te.SLOT_INPUT_VOIDICPHLOG, 125, 105) {
			@SideOnly(Side.CLIENT)
			@Override
			public String getSlotTexture() {
				return TextureStitch.guiSlot_VoidicPhlog.getIconName();
			}
		});

		addSlotToContainer(new SlotFurnaceOutput(inventory.player, tileEntity, te.SLOT_OUTPUT, 143, 76));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 18 + j * 18, 127 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventory, i, 18 + i * 18, 185));
		}

		addSlotToContainer(new Slot(inventory, inventory.getSizeInventory() - 1, 162, 105) {

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
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int par2) {

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
				boolean flag = true;
				for (int slotIndex = te.SLOT_INPUT_TOOL; slotIndex <= te.SLOT_INPUT_VOIDICPHLOG; slotIndex++) {
					ItemStack slotCheck = te.getStackInSlot(slotIndex);
					if ((slotCheck == null || (slotCheck.stackSize < slotCheck.getMaxStackSize() && slotCheck.isItemEqual(itemstack))) && te.canInsertItem(slotIndex, itemstack1, null)) {
						if (!mergeItemStack(itemstack1, slotIndex, slotIndex + 1, false)) {
							return null;
						}
						flag = false;
					}
				}
				if (flag) {
					if (hoverSlot >= maxSlots && hoverSlot < maxSlots + 27) {
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
		return te.isUsableByPlayer(entityplayer);
	}
}