package Tamaized.Voidcraft.machina.tileentity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.api.voidicpower.TileEntityVoidicPowerInventory;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import Tamaized.Voidcraft.fluids.FluidHelper;
import Tamaized.Voidcraft.fluids.IFaceFluidHandler;
import Tamaized.Voidcraft.machina.addons.VoidTank;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class TileEntityVoidicAnchor extends TileEntityVoidicPowerInventory implements IFaceFluidHandler {

	public static final int SLOT_DEFAULT = 0;
	private int[] slots_all = { SLOT_DEFAULT };

	private VoidTank tank;
	private int radius = 10;
	private int rate = 10;

	private List<EnumFacing> fluidOutput = new ArrayList<EnumFacing>();
	private List<EnumFacing> fluidInput = new ArrayList<EnumFacing>();

	public TileEntityVoidicAnchor() {
		super(1);
		tank = new VoidTank(this, 5000);
		fluidOutput.add(EnumFacing.DOWN);
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
		// Fill a bucket
		if (getStackInSlot(SLOT_DEFAULT) != null && getStackInSlot(SLOT_DEFAULT).getItem() == Items.BUCKET && getFluidAmount() >= 1000) {
			drain(1000, true);
			setInventorySlotContents(SLOT_DEFAULT, VoidCraft.fluids.voidBucket.getBucket());
		}
		// Handle Infusion drain
		for (EntityLivingBase entity : world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(getPos().add(-radius, -radius, -radius), getPos().add(radius, radius, radius)))) {
			if (voidicPower > 0 && getFluidAmount() + rate <= getMaxFluidAmount() && entity.hasCapability(CapabilityList.VOIDICINFUSION, null)) {
				IVoidicInfusionCapability cap = entity.getCapability(CapabilityList.VOIDICINFUSION, null);
				if (cap.getInfusion() >= rate) {
					voidicPower--;
					cap.addInfusion(-rate);
					fill(new FluidStack(VoidCraft.fluids.voidFluid, rate), true);
				}
			}
		}
		// Check if Void Machina is nearby; if so give it fluid
		if (tank.getFluidAmount() >= rate) {
			FluidHelper.sendToAllAround(this, world, pos, rate);
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public String getName() {
		return "voidicAnchor";
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
	public int getMaxPower() {
		return 100000;
	}

	@Override
	public int maxPowerTransfer() {
		return 160;
	}

	@Override
	public boolean canOutputPower(EnumFacing face) {
		return false;
	}

	@Override
	public boolean canInputPower(EnumFacing face) {
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		return slot == SLOT_DEFAULT && itemstack.getItem() == Items.BUCKET && getStackInSlot(SLOT_DEFAULT) == null;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing var1) {
		return slots_all;
	}

	@Override
	protected boolean canExtractSlot(int slot, ItemStack stack) {
		return slot == SLOT_DEFAULT ? getStackInSlot(SLOT_DEFAULT) != null ? getStackInSlot(SLOT_DEFAULT).isItemEqual(VoidCraft.fluids.voidBucket.getBucket()) : false : false;
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
