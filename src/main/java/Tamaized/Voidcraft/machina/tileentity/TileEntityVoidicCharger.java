package Tamaized.Voidcraft.machina.tileentity;

import Tamaized.Voidcraft.api.voidicpower.TileEntityVoidicPowerInventory;
import Tamaized.Voidcraft.api.voidicpower.VoidicPowerItem;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicPower.IVoidicPowerCapability;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileEntityVoidicCharger extends TileEntityVoidicPowerInventory {

	public static final int SLOT_DEFAULT = 0;
	private int[] slots_all = { SLOT_DEFAULT };

	public TileEntityVoidicCharger() {
		super(1);
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {

	}

	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		return nbt;
	}

	@Override
	public int getMaxPower() {
		return 200000;
	}

	@Override
	public int maxPowerTransfer() {
		return 600;
	}

	@Override
	public boolean canOutputPower(EnumFacing face) {
		return false;
	}

	@Override
	public boolean canInputPower(EnumFacing face) {
		return face == EnumFacing.DOWN;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return slots_all;
	}

	@Override
	public void onUpdate() {
		if (voidicPower > 0 && !getStackInSlot(SLOT_DEFAULT).isEmpty() && getStackInSlot(SLOT_DEFAULT).getItem() instanceof VoidicPowerItem) {
			IVoidicPowerCapability cap = getStackInSlot(SLOT_DEFAULT).getCapability(CapabilityList.VOIDICPOWER, null);
			if (cap != null && cap.getAmountPerc() < 1.0f) {
				int amount = voidicPower >= maxPowerTransfer() ? maxPowerTransfer() : voidicPower;
				int overflow = cap.fill(amount);
				voidicPower -= (amount - overflow);
				cap.sendUpdates(null, 0, getStackInSlot(SLOT_DEFAULT));
			}
		}
	}

	@Override
	protected boolean canExtractSlot(int i, ItemStack stack) {
		if (stack.isEmpty()) return false;
		IVoidicPowerCapability cap = getStackInSlot(SLOT_DEFAULT).getCapability(CapabilityList.VOIDICPOWER, null);
		return cap != null && cap.getCurrentPower() == cap.getMaxPower();
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return i == SLOT_DEFAULT ? stack.getItem() instanceof VoidicPowerItem : false;
	}

}
