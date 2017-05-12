package Tamaized.Voidcraft.machina.tileentity;

import Tamaized.TamModized.particles.FX.network.ParticleFluffPacketHandler;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.api.voidicpower.TileEntityVoidicPowerInventory;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

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
			if (getStackInSlot(SLOT_OUTPUT) == null || getStackInSlot(SLOT_OUTPUT).stackSize < getStackInSlot(SLOT_OUTPUT).getMaxStackSize()) pickupNextBlock();
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
		if (hasEnoughPower() && (getStackInSlot(SLOT_OUTPUT) == null || (getStackInSlot(SLOT_OUTPUT).getItem() == Item.getItemFromBlock(VoidCraft.blocks.realityHole) && getStackInSlot(SLOT_OUTPUT).stackSize < getStackInSlot(SLOT_OUTPUT).getMaxStackSize()))) {
			BlockPosWrapper wrapper = searchState(VoidCraft.blocks.realityHole, getPos(), 4);
			if (wrapper.state == null) return;
			world.setBlockToAir(wrapper.pos);
			for (float xf = 0.0F; xf <= 1.0F; xf += 0.25F)
				for (float zf = 0.0F; zf <= 1.0F; zf += 0.25F)
					for (float yf = 0.0F; yf <= 1.0F; yf += 0.25F)
				ParticleFluffPacketHandler.spawnOnServer(world, new Vec3d(wrapper.pos.getX() + xf, wrapper.pos.getY()+yf, wrapper.pos.getZ() + zf), new Vec3d(0, 0, 0), world.rand.nextInt(20 * 2) + 20, -((world.rand.nextFloat() * 0.02F) + 0.01F), (world.rand.nextFloat() * 0.50F) + 0.25F, 0xFFFFFFFF);
			usePower();
			if (getStackInSlot(SLOT_OUTPUT) == null) {
				setInventorySlotContents(SLOT_OUTPUT, new ItemStack(VoidCraft.blocks.realityHole));
			} else {
				getStackInSlot(SLOT_OUTPUT).stackSize += (1);
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
