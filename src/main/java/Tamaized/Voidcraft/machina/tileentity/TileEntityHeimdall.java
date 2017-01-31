package Tamaized.Voidcraft.machina.tileentity;

import Tamaized.TamModized.tileentity.TamTileEntityInventory;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.machina.addons.VoidTank;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class TileEntityHeimdall extends TamTileEntityInventory implements IFluidHandler, IEnergyStorage {

	public static final int SLOT_INPUT = 0;
	public static final int SLOT_BUCKET = 1;

	public VoidTank tank;
	private int forgeEnergy = 0;
	private int maxForgeEnergy = 20000;

	private static final int quartzAmount = 144 * 5;

	private boolean isDraining = false;

	public TileEntityHeimdall() {
		super(2);
		tank = new VoidTank(this, 10000);
	}

	@Override
	public int receiveEnergy(int power, boolean simulate) {
		int consumed = forgeEnergy + power > maxForgeEnergy ? power - ((forgeEnergy + power) - maxForgeEnergy) : power;
		if (!simulate) forgeEnergy += consumed;
		return consumed;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public boolean canExtract() {
		return false;
	}

	@Override
	public boolean canReceive() {
		return true;
	}

	@Override
	public int getEnergyStored() {
		return forgeEnergy;
	}

	@Override
	public int getMaxEnergyStored() {
		return maxForgeEnergy;
	}

	public void setEnergyAmount(int a) {
		forgeEnergy = a;
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {
		if (tank.getFluid() != null) tank.setFluid(new FluidStack(VoidCraft.fluids.voidFluid, nbt.getInteger("fluidAmount")));
	}

	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("fluidAmount", tank.getFluidAmount());
		return nbt;
	}

	@Override
	public void onUpdate() {
		if (!world.isRemote) {

			// Fill A Bucket
			if (tank.getFluidAmount() >= 1000) {
				if (!slots[SLOT_BUCKET].isEmpty() && slots[SLOT_BUCKET].getItem() == Items.BUCKET) {
					tank.drain(new FluidStack(VoidCraft.fluids.voidFluid, 1000), true);
					slots[SLOT_BUCKET] = VoidCraft.fluids.voidBucket.getBucket();
				}
			}

			// Check for quartz dust and handle
			if (forgeEnergy + quartzAmount < maxForgeEnergy && !slots[SLOT_INPUT].isEmpty() && slots[SLOT_INPUT].getItem() == VoidCraft.items.quartzDust) {
				if (slots[SLOT_INPUT].getCount() > 1) slots[SLOT_INPUT].shrink(1);
				else slots[SLOT_INPUT] = ItemStack.EMPTY;
				forgeEnergy += quartzAmount;
			}

			// Convert Power to Fluid
			if (forgeEnergy >= 5 && getFluidAmount() < getMaxFluidAmount()) {
				forgeEnergy -= 5;
				fill(new FluidStack(VoidCraft.fluids.voidFluid, 5), true);
			}

			// Check if Void Machina is nearby; if so give it fluid
			if (tank.getFluidAmount() > 0) {
				for (EnumFacing face : EnumFacing.VALUES) {
					TileEntity te = world.getTileEntity(getPos().offset(face));
					if (te instanceof IFluidHandler) {
						IFluidHandler fte = ((IFluidHandler) te);
						if (fte.fill(new FluidStack(VoidCraft.fluids.voidFluid, 1), true) > 0) {
							drain(new FluidStack(VoidCraft.fluids.voidFluid, 1), true);
						}
					}
				}
			}

		}
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		return slot == SLOT_BUCKET ? itemstack.getItem() == Items.BUCKET : slot == SLOT_INPUT ? itemstack.getItem() == VoidCraft.items.quartzDust : false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing var1) {
		return new int[] { SLOT_BUCKET, SLOT_INPUT };
	}

	@Override
	protected boolean canExtractSlot(int i, ItemStack stack) {
		return i == SLOT_BUCKET ? !slots[SLOT_BUCKET].isEmpty() ? slots[SLOT_BUCKET].isItemEqual(VoidCraft.fluids.voidBucket.getBucket()) : false : false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public String getName() {
		return "heimdall";
	}

	@Override
	public boolean hasCustomName() {
		return false;
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
	public boolean isEmpty() {
		for (ItemStack stack : slots)
			if (!stack.isEmpty()) return false;
		return true;
	}
}