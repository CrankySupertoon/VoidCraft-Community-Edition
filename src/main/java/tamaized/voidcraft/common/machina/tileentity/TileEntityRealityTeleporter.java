package tamaized.voidcraft.common.machina.tileentity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import tamaized.tammodized.common.particles.network.ParticleFluffPacketHandler;
import tamaized.tammodized.common.tileentity.TamTileEntityInventory;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.voidicpower.TileEntityVoidicPowerInventory;
import tamaized.voidcraft.registry.VoidCraftBlocks;

import javax.annotation.Nullable;
import java.util.List;

public class TileEntityRealityTeleporter extends TileEntityVoidicPowerInventory {

	public TamTileEntityInventory.ItemStackFilterHandler SLOT_INPUT;

	private BlockPos link;
	private boolean isOn = false;

	public TileEntityRealityTeleporter() {
		super();
	}

	@Override
	protected ItemStackHandler[] register() {
		return new ItemStackHandler[]{SLOT_INPUT = new TamTileEntityInventory.ItemStackFilterHandler(new ItemStack[]{new ItemStack(VoidCraftBlocks.realityHole)}, true, new ItemStack[0], true)};
	}

	@Nullable
	@Override
	protected IItemHandler getCap(EnumFacing face) {
		return SLOT_INPUT;
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
		return getPowerAmount() >= getPowerUse() && !SLOT_INPUT.getStackInSlot(0).isEmpty() && SLOT_INPUT.getStackInSlot(0).getItem() == Item.getItemFromBlock(VoidCraft.blocks.realityHole);
	}

	public void useResources() {
		drainPower(getPowerUse());
		SLOT_INPUT.getStackInSlot(0).shrink(1);
		for (float xf = 0.0F; xf <= 1.0F; xf += 0.25F)
			for (float zf = 0.0F; zf <= 1.0F; zf += 0.25F)
				for (float yf = 0.0F; yf <= 1.0F; yf += 0.25F) {
					if (world.rand.nextInt(4) != 0)
						continue;
					ParticleFluffPacketHandler.spawnOnServer(world, new Vec3d(pos.getX() + xf, pos.getY() + yf + 1.0F, pos.getZ() + zf), new Vec3d(0, 0, 0), world.rand.nextInt(20 * 2) + 20, -((world.rand.nextFloat() * 0.02F) + 0.01F), (world.rand.nextFloat() * 0.50F) + 0.25F, 0xFFFFFFFF);
				}
	}

	public void useLinkResources() {
		if (link == null)
			return;
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
		nbt.setIntArray("link", link != null ? new int[]{link.getX(), link.getY(), link.getZ()} : new int[]{});
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
						e.setPositionAndUpdate(link.getX() + 0.5, link.getY() + 1, link.getZ() + 0.5);
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
		if (link == null)
			return;
		TileEntity te = world.getTileEntity(link);
		if (!(te instanceof TileEntityRealityTeleporter))
			link = null;
	}

	private boolean canTeleportToLink() {
		if (link == null)
			return false;
		TileEntity te = world.getTileEntity(link);
		if (te != null && te instanceof TileEntityRealityTeleporter) {
			TileEntityRealityTeleporter teLink = (TileEntityRealityTeleporter) te;
			return teLink.canUse();
		}
		return false;
	}

}
