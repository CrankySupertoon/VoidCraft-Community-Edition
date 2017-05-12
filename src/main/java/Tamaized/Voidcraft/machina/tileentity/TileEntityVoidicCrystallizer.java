package Tamaized.Voidcraft.machina.tileentity;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.api.voidicpower.TileEntityVoidicPowerInventory;
import Tamaized.Voidcraft.machina.addons.VoidTank;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class TileEntityVoidicCrystallizer extends TileEntityVoidicPowerInventory implements IFluidHandler {

	public static final int SLOT_BUCKET = 0;
	private int[] slots_all = { SLOT_BUCKET };

	private VoidTank tank;

	public TileEntityVoidicCrystallizer() {
		super(1);
		tank = new VoidTank(this, 1000);
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
		// Create a Crystal
		if (getFluidAmount() >= 1000 && getPowerAmount() >= 144) {
			drain(1000, true);
			voidicPower -= 144;
			if (!world.isRemote) {
				InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(VoidCraft.items.voidcrystal));
				world.playSound(null, getPos(), SoundEvent.REGISTRY.getObject(new ResourceLocation("block.fire.extinguish")), SoundCategory.BLOCKS, 1.0F, 1.0F);
			}
		}
		// Fill from a Bucket
		if (getFluidAmount() <= getMaxFluidAmount() - 1000) {
			if (getStackInSlot(SLOT_BUCKET) != null && getStackInSlot(SLOT_BUCKET).isItemEqual(VoidCraft.fluids.voidBucket.getBucket())) {
				fill(new FluidStack(VoidCraft.fluids.voidFluid, 1000), true);
				setInventorySlotContents(SLOT_BUCKET, new ItemStack(Items.BUCKET));
			}
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public String getName() {
		return "voidicCrystallizer";
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
		return face == EnumFacing.UP || face == EnumFacing.DOWN;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		return slot == SLOT_BUCKET && itemstack.isItemEqual(VoidCraft.fluids.voidBucket.getBucket());
	}

	@Override
	public int[] getSlotsForFace(EnumFacing var1) {
		return slots_all;
	}

	@Override
	protected boolean canExtractSlot(int slot, ItemStack stack) {
		return slot == SLOT_BUCKET && getStackInSlot(SLOT_BUCKET) != null ? getStackInSlot(SLOT_BUCKET).getItem() == Items.BUCKET : false;
	}
}