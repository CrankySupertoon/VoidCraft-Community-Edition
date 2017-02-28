package Tamaized.Voidcraft.machina.tileentity;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.api.voidicpower.TileEntityVoidicPowerInventory;
import Tamaized.Voidcraft.machina.VoidMacerator;
import Tamaized.Voidcraft.machina.addons.TERecipesBlastFurnace.BlastFurnaceRecipe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityVoidBlastFurnace extends TileEntityVoidicPowerInventory {

	public static final int SLOT_INPUT_IRON = 0;
	public static final int SLOT_INPUT_COAL = 1;
	public static final int SLOT_OUTPUT = 2;
	public static final int[] SLOTS_ALL = new int[] { SLOT_INPUT_IRON, SLOT_INPUT_COAL, SLOT_OUTPUT };

	public int cookingTick = 0;
	public int finishTick = 0;
	private BlastFurnaceRecipe recipe;

	private Item[] lastItem = new Item[SLOTS_ALL.length + 1];

	public TileEntityVoidBlastFurnace() {
		super(SLOTS_ALL.length);
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return (oldState.getBlock() != newSate.getBlock());
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {
		cookingTick = nbt.getInteger("cookingTick");
	}

	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("cookingTick", cookingTick);
		return nbt;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void onUpdate() {
		boolean cooking = false;

		doLastItemChecks();

		if (voidicPower > 0 && canCook()) {
			cooking = true;
			voidicPower--;
		}

		if (!world.isRemote) {
			if (cooking) {
				cookingTick++;
				if (cookingTick >= (finishTick = recipe.getRequiredPower())) {
					cookingTick = 0;
					bakeItem();
					markDirty();
				}
			}

			IBlockState state = world.getBlockState(pos);
			if (state.getBlock() instanceof VoidMacerator) {
				VoidMacerator theMacerator = (VoidMacerator) state.getBlock();
				if (theMacerator != null) {
					if (theMacerator.getIsActive(state) && !cooking) theMacerator.setState(false, world, pos);
					if (!theMacerator.getIsActive(state) && cooking) theMacerator.setState(true, world, pos);
				}
			}
		}
	}

	private void doLastItemChecks() {
		for (int i = SLOT_INPUT_IRON; i <= SLOT_INPUT_COAL; i++) {
			if (lastItem[i] == null || getStackInSlot(i) == null || lastItem[i] != getStackInSlot(i).getItem()) {
				cookingTick = 0;
				lastItem[i] = (getStackInSlot(i) != null) ? getStackInSlot(i).getItem() : null;
			}
		}
	}

	private void bakeItem() {
		if (canCook()) {
			if (getStackInSlot(SLOT_OUTPUT) == null) {
				setInventorySlotContents(SLOT_OUTPUT, recipe.getOutput().copy());
			} else if (getStackInSlot(SLOT_OUTPUT).isItemEqual(recipe.getOutput())) {
				getStackInSlot(SLOT_OUTPUT).stackSize += (recipe.getOutput().stackSize);
			}

			getStackInSlot(SLOT_INPUT_COAL).stackSize -= (1);
			getStackInSlot(SLOT_INPUT_IRON).stackSize -= (1);

			if (getStackInSlot(SLOT_INPUT_COAL).stackSize <= 0) {
				setInventorySlotContents(SLOT_INPUT_COAL, null);
			}

			if (getStackInSlot(SLOT_INPUT_IRON).stackSize <= 0) {
				setInventorySlotContents(SLOT_INPUT_IRON, null);
			}
		}
	}

	private boolean canCook() {
		if (getStackInSlot(SLOT_INPUT_IRON) == null || getStackInSlot(SLOT_INPUT_COAL) == null || world.getBlockState(getPos().down()) == null || world.getBlockState(getPos().down()).getBlock() != VoidCraft.blocks.fireVoid) return false;
		recipe = VoidCraft.teRecipes.blastFurnace.getRecipe(new ItemStack[] { getStackInSlot(SLOT_INPUT_IRON), getStackInSlot(SLOT_INPUT_COAL) });
		if (recipe == null) return false;
		if (getStackInSlot(SLOT_OUTPUT) == null) return true;
		if (!getStackInSlot(SLOT_OUTPUT).isItemEqual(recipe.getOutput())) return false;
		int result = getStackInSlot(SLOT_OUTPUT).stackSize + recipe.getOutput().stackSize;
		return (result <= getInventoryStackLimit() && result <= recipe.getOutput().getMaxStackSize());
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return SLOTS_ALL;
	}

	@Override
	public String getName() {
		return "teVoidBlastFurnace";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getMaxPower() {
		return 50000;
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
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return (i == SLOT_INPUT_IRON && itemstack.getItem() == VoidCraft.items.ironDust) || (i == SLOT_INPUT_COAL && itemstack.getItem() == VoidCraft.items.coalDust);
	}

	@Override
	protected boolean canExtractSlot(int i, ItemStack stack) {
		return i == SLOT_OUTPUT;
	}
}
