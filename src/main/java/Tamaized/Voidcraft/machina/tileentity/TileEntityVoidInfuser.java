package Tamaized.Voidcraft.machina.tileentity;

import Tamaized.TamModized.tileentity.TamTileEntityInventory;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.machina.addons.TERecipeInfuser.InfuserRecipe;
import Tamaized.Voidcraft.machina.addons.VoidTank;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class TileEntityVoidInfuser extends TamTileEntityInventory implements IFluidHandler {

	public VoidTank tank;

	public static final int SLOT_BUCKET = 0;
	public static final int SLOT_INPUT = 1;
	public static final int SLOT_OUTPUT = 2;
	public static final int[] SLOTS_ALL = new int[] { 0, 1, 2 };

	public int finishTick = 0;
	public int cookingTick = 0;
	private InfuserRecipe recipe;

	private Item lastCookingItem = null;

	public TileEntityVoidInfuser() {
		super(3);
		tank = new VoidTank(this, 3000);
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {
		if (tank.getFluid() != null) tank.setFluid(new FluidStack(VoidCraft.fluids.voidFluid, nbt.getInteger("fluidAmount")));
		cookingTick = nbt.getInteger("cookingTick");
	}

	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("fluidAmount", tank.getFluidAmount());
		nbt.setInteger("cookingTick", cookingTick);
		return nbt;
	}

	@Override
	public void onUpdate() {
		boolean cooking = false;
		if (lastCookingItem == null || slots[SLOT_INPUT].isEmpty() || lastCookingItem != slots[SLOT_INPUT].getItem()) {
			cookingTick = 0;
			lastCookingItem = (!slots[SLOT_INPUT].isEmpty()) ? slots[SLOT_INPUT].getItem() : null;
		}

		if (tank.getFluidAmount() > 0 && canCook()) {
			cooking = true;
			drain(new FluidStack(VoidCraft.fluids.voidFluid, 1), true);
		}

		if (getFluidAmount() <= getMaxFluidAmount() - 1000) {
			if (!slots[SLOT_BUCKET].isEmpty() && slots[SLOT_BUCKET].isItemEqual(VoidCraft.fluids.voidBucket.getBucket())) {
				fill(new FluidStack(VoidCraft.fluids.voidFluid, 1000), true);
				slots[SLOT_BUCKET] = new ItemStack(Items.BUCKET);
			}
		}

		if (cooking) {
			cookingTick++;
			if (cookingTick >= (finishTick = recipe.getRequiredFluid())) {
				cookingTick = 0;
				bakeItem();
				markDirty();
			}
		}
	}

	private void bakeItem() {
		if (canCook()) {
			if (slots[SLOT_OUTPUT].isEmpty()) {
				slots[SLOT_OUTPUT] = recipe.getOutput().copy();
			} else if (slots[SLOT_OUTPUT].isItemEqual(recipe.getOutput())) {
				slots[SLOT_OUTPUT].grow(recipe.getOutput().getCount());
			}

			slots[SLOT_INPUT].shrink(1);

			if (slots[SLOT_INPUT].getCount() <= 0) {
				slots[SLOT_INPUT] = ItemStack.EMPTY;
			}
		}
	}

	private boolean canCook() {
		if (slots[SLOT_INPUT].isEmpty()) return false;
		recipe = VoidCraft.teRecipes.infuser.getRecipe(new ItemStack[] { slots[SLOT_INPUT] });
		if (recipe == null) return false;
		if (slots[SLOT_OUTPUT].isEmpty()) return true;
		if (!slots[SLOT_OUTPUT].isItemEqual(recipe.getOutput())) return false;
		int result = slots[SLOT_OUTPUT].getCount() + recipe.getOutput().getCount();
		return (result <= getInventoryStackLimit() && result <= recipe.getOutput().getMaxStackSize());
	}

	public static boolean isItemFuel(ItemStack stack) {
		return stack.isItemEqual(VoidCraft.fluids.voidBucket.getBucket());
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return i != SLOT_OUTPUT ? i == SLOT_BUCKET ? isItemFuel(itemstack) : !itemstack.isItemEqual(VoidCraft.fluids.voidBucket.getBucket()) : false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing var1) {
		return SLOTS_ALL;
	}

	@Override
	protected boolean canExtractSlot(int i, ItemStack stack) {
		return i == SLOT_BUCKET ? !slots[SLOT_BUCKET].isEmpty() ? slots[SLOT_BUCKET].isItemEqual(VoidCraft.fluids.voidBucket.getBucket()) : false : i == SLOT_OUTPUT;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public String getName() {
		return "voidInfuser";
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
}