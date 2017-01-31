package Tamaized.Voidcraft.items;

import Tamaized.TamModized.helper.RayTraceHelper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.api.voidicpower.VoidicPowerItem;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicPower.IVoidicPowerCapability;
import Tamaized.Voidcraft.items.inventory.InventoryItem;
import Tamaized.Voidcraft.machina.tileentity.TileEntityRealityTeleporter;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class RealityTeleporter extends VoidicPowerItem {

	public RealityTeleporter(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	private void activate(ItemStack stack, EntityPlayer player) {
		Vec3d[] vecs = RayTraceHelper.getPlayerTraceVec(player, 64);
		RayTraceResult result = RayTraceHelper.tracePath(player.world, vecs[0], vecs[1], 1, null);
		if (result != null && result.getBlockPos() != null) {
			BlockPos pos = result.getBlockPos();
			switch (result.sideHit) {
				case UP:
					pos = pos.add(0, 1, 0);
					break;
				case DOWN:
					pos = pos.add(0, -1, 0);
					break;
				case NORTH:
					pos = pos.add(0, 0, -1);
					break;
				case SOUTH:
					pos = pos.add(0, 0, 1);
					break;
				case EAST:
					pos = pos.add(1, 0, 0);
					break;
				case WEST:
					pos = pos.add(-1, 0, 0);
					break;
				default:
					break;
			}
			player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
		} else {
			player.setPositionAndUpdate(vecs[1].xCoord, vecs[1].yCoord, vecs[1].zCoord);
		}
	}

	public static InventoryItem createInventory(ItemStack stack) {
		return new InventoryItem(stack, 1) {

			@Override
			public boolean hasCustomName() {
				return false;
			}

			@Override
			public String getName() {
				return "realityTeleporter";
			}

			@Override
			public int getInventoryStackLimit() {
				return 64;
			}

			@Override
			public ITextComponent getDisplayName() {
				return null;
			}

			@Override
			public boolean isItemValidForSlot(int index, ItemStack stack) {
				return stack.isEmpty() ? false : stack.getItem() == Item.getItemFromBlock(VoidCraft.blocks.realityHole);
			}

			@Override
			public boolean isEmpty() {
				return stack.isEmpty();
			}
		};
	}

	public static void clearLink(ItemStack stack) {
		NBTTagCompound ct = stack.getOrCreateSubCompound(VoidCraft.modid + "_LinkLoc");
		ct.removeTag("link");
	}

	public static boolean hasLink(ItemStack stack) {
		NBTTagCompound ct = stack.getOrCreateSubCompound(VoidCraft.modid + "_LinkLoc");
		int[] loc = ct.getIntArray("link");
		return loc.length == 3;
	}

	public static BlockPos getLink(ItemStack stack) {
		if (!hasLink(stack)) return null;
		NBTTagCompound ct = stack.getOrCreateSubCompound(VoidCraft.modid + "_LinkLoc");
		int[] loc = ct.getIntArray("link");
		return new BlockPos(loc[0], loc[1], loc[2]);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = playerIn.getHeldItem(hand);
		TileEntity te = worldIn.getTileEntity(pos);
		if (te != null && te instanceof TileEntityRealityTeleporter) {
			TileEntityRealityTeleporter teleporter = (TileEntityRealityTeleporter) te;
			NBTTagCompound ct = stack.getOrCreateSubCompound(VoidCraft.modid + "_LinkLoc");
			int[] loc = ct.getIntArray("link");
			if (loc.length == 3) {
				BlockPos newPos = new BlockPos(loc[0], loc[1], loc[2]);
				if (teleporter.getPos().equals(newPos)) {
					if (!worldIn.isRemote) playerIn.sendMessage(new TextComponentString("Cannot link to the same block"));
				} else {
					teleporter.setLink(newPos);
					if (!worldIn.isRemote) playerIn.sendMessage(new TextComponentString("Linked: { x:" + loc[0] + ", y:" + loc[1] + ", z:" + loc[2] + " }"));
				}
			} else {
				ct.setIntArray("link", new int[] { pos.getX(), pos.getY(), pos.getZ() });
				if (!worldIn.isRemote) playerIn.sendMessage(new TextComponentString("Saved Link: { x:" + pos.getX() + ", y:" + pos.getY() + ", z:" + pos.getZ() + " }"));
			}
			return EnumActionResult.SUCCESS;
		}
		return super.onItemUse(playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (world.isRemote) return ActionResult.newResult(EnumActionResult.PASS, stack);
		InventoryItem inv = createInventory(stack);
		ItemStack holes = inv.getStackInSlot(0);
		IVoidicPowerCapability cap = stack.getCapability(CapabilityList.VOIDICPOWER, null);
		if (!player.isSneaking() && cap != null && cap.getCurrentPower() >= useAmount() && !holes.isEmpty() && holes.getItem() == Item.getItemFromBlock(VoidCraft.blocks.realityHole)) {
			activate(stack, player);
			cap.drain(useAmount());
			holes.shrink(1);
			inv.saveData();
			return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
		} else {
			player.openGui(VoidCraft.instance, GuiHandler.getTypeID(GuiHandler.Type.RealityTeleporter), world, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
		}
		return ActionResult.newResult(EnumActionResult.FAIL, stack);
	}

	@Override
	protected int getDefaultVoidicPower() {
		return 0;
	}

	@Override
	protected int getDefaultMaxVoidicPower() {
		return 5000;
	}

	@Override
	protected int useAmount() {
		return 200;
	}

	@Override
	protected boolean canBeUsed() {
		return true;
	}

}
