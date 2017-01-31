package Tamaized.Voidcraft.machina.tileentity;

import java.util.UUID;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.api.voidicpower.TileEntityVoidicPowerInventory;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.capabilities.vadeMecum.VadeMecumCapabilityHandler;
import Tamaized.Voidcraft.machina.VoidMacerator;
import Tamaized.Voidcraft.machina.addons.TERecipesAlchemy.AlchemyRecipe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class TileEntityVoidicAlchemy extends TileEntityVoidicPowerInventory {

	public static final int SLOT_OUTPUT = 0;
	public static final int SLOT_INPUT_1 = 1;
	public static final int SLOT_INPUT_2 = 2;
	public static final int SLOT_INPUT_3 = 3;
	public static final int SLOT_INPUT_4 = 4;
	public static final int SLOT_INPUT_5 = 5;
	public static final int SLOT_INPUT_6 = 6;
	public static final int[] SLOTS_ALL = new int[] { SLOT_OUTPUT, SLOT_INPUT_1, SLOT_INPUT_2, SLOT_INPUT_3, SLOT_INPUT_4, SLOT_INPUT_5, SLOT_INPUT_6 };
	private Item[] lastItem = new Item[SLOTS_ALL.length + 1];

	public int cookingTick = 0;
	public int finishTick = 0;
	private AlchemyRecipe recipe;

	private String ownerName = "";
	private UUID ownerID;
	private IVadeMecumCapability owner;
	private int capTick = 1;

	public TileEntityVoidicAlchemy() {
		super(SLOTS_ALL.length + 1);
	}

	public void setOwner(EntityPlayer player) {
		ownerName = player.getGameProfile().getName();
		ownerID = player.getGameProfile().getId();
		owner = player.getCapability(CapabilityList.VADEMECUM, null);
		player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "Owner Set: " + player.getGameProfile().getName() + "; pos{" + getPos().getX() + ", " + getPos().getY() + ",  " + getPos().getZ() + "}"));
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public String getName() {
		return "teVoidicAlchemy";
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
		return face != EnumFacing.UP;
	}

	@Override
	protected void readNBT(NBTTagCompound nbt) {
		cookingTick = nbt.getInteger("cookingTick");
		NBTTagCompound ownerData = nbt.getCompoundTag("ownerData");
		ownerName = ownerData.getString("ownerName");
		String id = ownerData.getString("ownerID");
		ownerID = (id.isEmpty() || id.equals("null")) ? null : UUID.fromString(id);
		NBTTagCompound capData = ownerData.getCompoundTag("capData");
		if (!capData.hasNoTags()) {
			owner = new VadeMecumCapabilityHandler();
			CapabilityList.VADEMECUM.readNBT(owner, null, capData);
		}
	}

	@Override
	protected NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("cookingTick", cookingTick);
		NBTTagCompound ownerData = new NBTTagCompound();
		ownerData.setString("ownerName", ownerName);
		ownerData.setString("ownerID", ownerID == null ? "null" : ownerID.toString());
		NBTTagCompound capData = owner == null ? new NBTTagCompound() : (NBTTagCompound) CapabilityList.VADEMECUM.writeNBT(owner, null);
		ownerData.setTag("capData", capData);
		nbt.setTag("ownerData", ownerData);
		return nbt;
	}

	@Override
	protected void onUpdate() {
		boolean cooking = false;

		doLastItemChecks();
		if (capTick++ % 20 == 0) validateCapData();

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

	private void validateCapData() {
		capTick = 1;
		if (ownerID == null) return;
		EntityPlayer player = world.getPlayerEntityByUUID(ownerID);
		if (player != null) {
			owner = new VadeMecumCapabilityHandler();
			owner.copyFrom(player.getCapability(CapabilityList.VADEMECUM, null));
		}
	}

	private void doLastItemChecks() {
		for (int i = SLOT_INPUT_1; i <= SLOT_INPUT_6; i++) {
			if (lastItem[i] == null || getStackInSlot(i).isEmpty() || lastItem[i] != getStackInSlot(i).getItem()) {
				cookingTick = 0;
				lastItem[i] = (!getStackInSlot(i).isEmpty()) ? getStackInSlot(i).getItem() : null;
			}
		}
	}

	private boolean canCook() {
		for (int i = SLOT_INPUT_1; i <= SLOT_INPUT_6; i++) {
			if (getStackInSlot(i).isEmpty()) {
				return false;
			}
		}
		recipe = VoidCraft.teRecipes.alchemy.getRecipe(owner, new ItemStack[] { getStackInSlot(SLOT_INPUT_1), getStackInSlot(SLOT_INPUT_2), getStackInSlot(SLOT_INPUT_3), getStackInSlot(SLOT_INPUT_4), getStackInSlot(SLOT_INPUT_5), getStackInSlot(SLOT_INPUT_6) });
		if (recipe == null) return false;
		if (getStackInSlot(SLOT_OUTPUT).isEmpty()) return true;
		if (!getStackInSlot(SLOT_OUTPUT).isItemEqual(recipe.getOutput())) return false;
		int result = getStackInSlot(SLOT_OUTPUT).getCount() + recipe.getOutput().getCount();
		return (result <= getInventoryStackLimit() && result <= recipe.getOutput().getMaxStackSize());
	}

	private void bakeItem() {
		if (canCook()) {
			if (getStackInSlot(SLOT_OUTPUT).isEmpty()) {
				setInventorySlotContents(SLOT_OUTPUT, recipe.getOutput().copy());
			} else if (getStackInSlot(SLOT_OUTPUT).isItemEqual(recipe.getOutput())) {
				getStackInSlot(SLOT_OUTPUT).grow(recipe.getOutput().getCount());
			}

			for (int i = SLOT_INPUT_1; i <= SLOT_INPUT_6; i++) {
				getStackInSlot(i).shrink(1);;
				if (getStackInSlot(i).getCount() <= 0) {
					setInventorySlotContents(i, ItemStack.EMPTY);
				}
			}
		}
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return index >= SLOT_INPUT_1 && index <= SLOT_INPUT_6 ? true : false;
	}

	@Override
	protected boolean canExtractSlot(int i, ItemStack stack) {
		return i == SLOT_OUTPUT;
	}

}
