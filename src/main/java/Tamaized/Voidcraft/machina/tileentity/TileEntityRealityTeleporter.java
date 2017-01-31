package Tamaized.Voidcraft.machina.tileentity;

import java.util.List;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.api.voidicpower.TileEntityVoidicPowerInventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileEntityRealityTeleporter extends TileEntityVoidicPowerInventory {

	public static final int SLOT_INPUT = 0;
	public static final int[] SLOTS_ALL = new int[] { SLOT_INPUT };

	private BlockPos link;
	private boolean isOn = false;

	public TileEntityRealityTeleporter() {
		super(1);
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public String getName() {
		return "teRealityTeleporter";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return SLOTS_ALL;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return i == SLOT_INPUT && !stack.isEmpty() && stack.getItem() == Item.getItemFromBlock(VoidCraft.blocks.realityHole);
	}

	@Override
	protected boolean canExtractSlot(int i, ItemStack stack) {
		return true;
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
		return face != EnumFacing.UP;
	}

	public int getPowerUse() {
		return 2000;
	}

	public boolean canUse() {
		return getPowerAmount() >= getPowerUse() && !getStackInSlot(SLOT_INPUT).isEmpty() && getStackInSlot(SLOT_INPUT).getItem() == Item.getItemFromBlock(VoidCraft.blocks.realityHole);
	}

	public void useResources() {
		drainPower(getPowerUse());
		decrStackSize(SLOT_INPUT, 1);
	}

	public void useLinkResources() {
		if (link == null) return;
		TileEntity te = world.getTileEntity(link);
		if (te != null && te instanceof TileEntityRealityTeleporter) {
			TileEntityRealityTeleporter teLink = (TileEntityRealityTeleporter) te;
			teLink.useResources();
		}
	}

	@Override
	protected void readNBT(NBTTagCompound nbt) {
		int[] loc = nbt.getIntArray("link");
		link = loc.length == 3 ? new BlockPos(loc[0], loc[1], loc[2]) : null;
		isOn = nbt.getBoolean("isOn");
	}

	@Override
	protected NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setBoolean("isOn", isOn);
		nbt.setIntArray("link", link != null ? new int[] { link.getX(), link.getY(), link.getZ() } : new int[] {});
		return nbt;
	}

	@Override
	protected void onUpdate() {
		boolean flag = world.isBlockIndirectlyGettingPowered(getPos()) > 0;
		validateLink();
		if (link != null) {
			if (!isOn && flag) {
				List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(getPos().up()));
				if (!list.isEmpty() && canUse() && canTeleportToLink()) {
					useResources();
					useLinkResources();
					for (EntityLivingBase e : list) {
						e.setPositionAndUpdate(link.getX()+0.5, link.getY()+1, link.getZ()+0.5);
					}
				}
			}
		}
		isOn = flag;
	}

	public boolean setLink(BlockPos pos) {
		link = pos;
		validateLink();
		return link != null;
	}

	public BlockPos getLink() {
		return link;
	}

	private void validateLink() {
		if (link == null) return;
		TileEntity te = world.getTileEntity(link);
		if (!(te instanceof TileEntityRealityTeleporter)) link = null;
	}

	private boolean canTeleportToLink() {
		if (link == null) return false;
		TileEntity te = world.getTileEntity(link);
		if (te != null && te instanceof TileEntityRealityTeleporter) {
			TileEntityRealityTeleporter teLink = (TileEntityRealityTeleporter) te;
			return teLink.canUse();
		}
		return false;
	}

}
