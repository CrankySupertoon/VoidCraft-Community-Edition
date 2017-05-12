package Tamaized.Voidcraft.machina.tileentity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Tamaized.TamModized.particles.FX.network.ParticleFluffPacketHandler;
import Tamaized.TamModized.tileentity.TamTileEntityInventory;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.fluids.FluidHelper;
import Tamaized.Voidcraft.fluids.IFaceFluidHandler;
import Tamaized.Voidcraft.machina.addons.VoidTank;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class TileEntityHeimdall extends TamTileEntityInventory implements IEnergyStorage, IFaceFluidHandler {

	public static final int SLOT_INPUT = 0;
	public static final int SLOT_BUCKET = 1;

	public VoidTank tank;
	private int forgeEnergy = 0;
	private int maxForgeEnergy = 20000;

	private static final int quartzAmount = 144 * 5;

	private boolean isDraining = false;

	private List<EnumFacing> fluidOutput = new ArrayList<EnumFacing>();
	private List<EnumFacing> fluidInput = new ArrayList<EnumFacing>();

	public TileEntityHeimdall() {
		super(2);
		tank = new VoidTank(this, 10000);
		fluidOutput.add(EnumFacing.DOWN);
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
				if (slots[SLOT_BUCKET] != null && slots[SLOT_BUCKET].getItem() == Items.BUCKET) {
					tank.drain(new FluidStack(VoidCraft.fluids.voidFluid, 1000), true);
					slots[SLOT_BUCKET] = VoidCraft.fluids.voidBucket.getBucket();
				}
			}

			// Check for quartz dust and handle
			if (forgeEnergy + quartzAmount < maxForgeEnergy && slots[SLOT_INPUT] != null && slots[SLOT_INPUT].getItem() == VoidCraft.items.quartzDust) {
				if (slots[SLOT_INPUT].stackSize > 1) slots[SLOT_INPUT].stackSize--;
				else slots[SLOT_INPUT] = null;
				forgeEnergy += quartzAmount;
			}

			// Convert Power to Fluid
			if (forgeEnergy >= 1 && getFluidAmount() < getMaxFluidAmount()) {
				int rate = MathHelper.clamp(forgeEnergy, 1, 100);
				forgeEnergy -= rate;
				fill(new FluidStack(VoidCraft.fluids.voidFluid, rate), true);
				ParticleFluffPacketHandler.spawnOnServer(world, new Vec3d(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F), new Vec3d(0, 0, 0), world.rand.nextInt(20 * 2) + 20, -((world.rand.nextFloat() * 0.02F) + 0.01F), (world.rand.nextFloat() * 0.50F) + 0.25F, 0x7700FFFF);
			}

			// Check if Void Machina is nearby; if so give it fluid
			if (tank.getFluidAmount() > 0) {
				FluidHelper.sendToAllAround(this, world, pos, MathHelper.clamp(getFluidAmount(), 1, 100));
			}

		}
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		return slot == SLOT_BUCKET ? (itemstack.getItem() == Items.BUCKET && getStackInSlot(SLOT_BUCKET) == null) : slot == SLOT_INPUT ? itemstack.getItem() == VoidCraft.items.quartzDust : false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing var1) {
		return new int[] { SLOT_BUCKET, SLOT_INPUT };
	}

	@Override
	protected boolean canExtractSlot(int i, ItemStack stack) {
		return i == SLOT_BUCKET ? slots[SLOT_BUCKET] != null ? slots[SLOT_BUCKET].isItemEqual(VoidCraft.fluids.voidBucket.getBucket()) : false : false;
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
	public List<EnumFacing> outputFaces() {
		return Collections.unmodifiableList(fluidOutput);
	}

	@Override
	public List<EnumFacing> inputFaces() {
		return Collections.unmodifiableList(fluidInput);
	}
}