package Tamaized.Voidcraft.machina.tileentity;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.api.voidicpower.TileEntityVoidicPowerInventory;
import Tamaized.Voidcraft.api.voidicpower.VoidicPowerHandler;
import Tamaized.Voidcraft.machina.addons.VoidTank;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class TileEntityVoidicPowerGen extends TileEntityVoidicPowerInventory implements IFluidHandler {

	public static final int SLOT_DEFAULT = 0;
	private int[] slots_all = { SLOT_DEFAULT };

	private VoidTank tank;
	private int useAmount = 1;
	private int genAmount = 2;
	private int rate = 1;

	public TileEntityVoidicPowerGen() {
		super(1);
		tank = new VoidTank(this, 5000);
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {
		tank.setFluid(new FluidStack(VoidCraft.fluids.voidFluid, nbt.getInteger("fluidAmount")));
	}

	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("fluidAmount", tank.getFluidAmount());
		return nbt;
	}

	@Override
	public void onUpdate() {
		rate = 10;
		int gen = genAmount * rate;
		int use = useAmount * rate;
		if (getFluidAmount() <= getMaxFluidAmount() - 1000) {
			if (!getStackInSlot(SLOT_DEFAULT).isEmpty() && getStackInSlot(SLOT_DEFAULT).isItemEqual(VoidCraft.fluids.voidBucket.getBucket())) {
				fill(new FluidStack(VoidCraft.fluids.voidFluid, 1000), true);
				setInventorySlotContents(SLOT_DEFAULT, new ItemStack(Items.BUCKET));
			}
		}
		if (getFluidAmount() >= use && voidicPower <= getMaxPower() - gen) {
			drain(new FluidStack(VoidCraft.fluids.voidFluid, use), true);
			voidicPower += gen;
		}
		VoidicPowerHandler.sendToSurrounding(this, world, pos);
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public String getName() {
		return "voidicPowerGen";
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
	public IFluidTankProperties[] getTankProperties() {
		return tank.getTankProperties();
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		return tank.drain(resource, doDrain);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

	public int getFluidAmount() {
		return tank.getFluidAmount();
	}

	public int getMaxFluidAmount() {
		return tank.getCapacity();
	}

	public void setFluidAmount(int amount) {
		tank.setFluid(new FluidStack(VoidCraft.fluids.voidFluid, amount > tank.getCapacity() ? tank.getCapacity() : amount));
	}

	@Override
	public int getMaxPower() {
		return 100000;
	}

	@Override
	public int maxPowerTransfer() {
		return 160;
	}

	@Override
	public boolean canOutputPower(EnumFacing face) {
		return true;
	}

	@Override
	public boolean canInputPower(EnumFacing face) {
		return false;
	}

	@Override
	protected boolean canExtractSlot(int i, ItemStack stack) {
		switch (i) {
			case SLOT_DEFAULT:
				return stack.getItem() == Items.BUCKET;
			default:
				return true;
		}
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return i == SLOT_DEFAULT ? stack.isItemEqual(VoidCraft.fluids.voidBucket.getBucket()) : false;
	}

}
