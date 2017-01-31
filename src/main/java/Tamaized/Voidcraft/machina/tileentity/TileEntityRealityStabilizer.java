package Tamaized.Voidcraft.machina.tileentity;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.api.voidicpower.TileEntityVoidicPowerInventory;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class TileEntityRealityStabilizer extends TileEntityVoidicPowerInventory {

	public static final int SLOT_OUTPUT = 0;
	public static final int[] SLOTS_ALL = new int[] { 0 };

	public TileEntityRealityStabilizer() {
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
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void onUpdate() {
		if (!world.isRemote) {
			if (getStackInSlot(SLOT_OUTPUT).isEmpty() || getStackInSlot(SLOT_OUTPUT).getCount() < getStackInSlot(SLOT_OUTPUT).getMaxStackSize()) pickupNextBlock();
		}
	}

	public int requiredPower() {
		return 2000;
	}

	private void usePower() {
		if (hasEnoughPower()) voidicPower -= requiredPower();
	}

	private boolean hasEnoughPower() {
		return voidicPower >= requiredPower();
	}

	private void pickupNextBlock() {
		if (hasEnoughPower() && (getStackInSlot(SLOT_OUTPUT).isEmpty() || (getStackInSlot(SLOT_OUTPUT).getItem() == Item.getItemFromBlock(VoidCraft.blocks.realityHole) && getStackInSlot(SLOT_OUTPUT).getCount() < getStackInSlot(SLOT_OUTPUT).getMaxStackSize()))) {
			BlockPosWrapper wrapper = searchState(VoidCraft.blocks.realityHole, getPos(), 4);
			if (wrapper.state == null) return;
			world.setBlockToAir(wrapper.pos);
			usePower();
			if (getStackInSlot(SLOT_OUTPUT).isEmpty()) {
				setInventorySlotContents(SLOT_OUTPUT, new ItemStack(VoidCraft.blocks.realityHole));
			} else {
				getStackInSlot(SLOT_OUTPUT).grow(1);
			}
		}
	}

	private BlockPosWrapper searchState(Block type, BlockPos origin, int radius) {
		for (int x = -radius; x <= radius; x++) {
			for (int z = -radius; z <= radius; z++) {
				for (int y = radius; y >= -radius; y--) {
					IBlockState state = world.getBlockState(origin.add(x, y, z));
					if (state != null && state.getBlock() == VoidCraft.blocks.realityHole) {
						return new BlockPosWrapper(origin.add(x, y, z), state);
					}
				}
			}
		}
		return new BlockPosWrapper(origin, null);
	}

	private class BlockPosWrapper {

		public final BlockPos pos;
		public final IBlockState state;

		public BlockPosWrapper(BlockPos p, IBlockState s) {
			pos = p;
			state = s;
		}
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return SLOTS_ALL;
	}

	@Override
	public String getName() {
		return "teRealityStabilizer";
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
		return 320;
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
		return false;
	}

	@Override
	protected boolean canExtractSlot(int i, ItemStack stack) {
		return i == SLOT_OUTPUT;
	}

}
