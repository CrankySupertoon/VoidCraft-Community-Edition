package Tamaized.Voidcraft.blocks.tileentity;

import java.util.ArrayList;

import Tamaized.TamModized.tileentity.TamTileEntityInventory;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.starforge.IStarForgeCapability;
import Tamaized.Voidcraft.helper.GUIListElement;
import Tamaized.Voidcraft.starforge.IStarForgeTool;
import Tamaized.Voidcraft.starforge.StarForgeEffectEntry;
import Tamaized.Voidcraft.starforge.StarForgeEffectRecipeList;
import Tamaized.Voidcraft.starforge.StarForgeToolEntry;
import Tamaized.Voidcraft.starforge.effects.IStarForgeEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileEntityStarForge extends TamTileEntityInventory {

	public static final int SLOT_INPUT_TOOL = 0;
	public static final int SLOT_INPUT_COSMICMATERIAL = 1;
	public static final int SLOT_INPUT_DRAGONSCALE = 2;
	public static final int SLOT_INPUT_QUORIFRAGMENT = 3;
	public static final int SLOT_INPUT_ASTRALESSENCE = 4;
	public static final int SLOT_INPUT_VOIDICPHLOG = 5;
	public static final int SLOT_OUTPUT = 6;
	public static final int[] SLOTS_ALL = new int[] { 0, 1, 2, 3, 4, 5, 6 };

	public TileEntityStarForge() {
		super(7);
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {
	}

	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		return nbt;
	}

	public ArrayList<GUIListElement> buildPossibleEffectList() {
		ArrayList<GUIListElement> list = new ArrayList<GUIListElement>();
		IStarForgeEffect.Type type = null;
		ItemStack stack = getStackInSlot(SLOT_INPUT_TOOL);
		if (stack.isEmpty()) {
			try {
				list.add(new StarForgeToolEntry(new ItemStack(VoidCraft.tools.starforgedSword)));
				list.add(new StarForgeToolEntry(new ItemStack(VoidCraft.tools.starforgedPickaxe)));
				list.add(new StarForgeToolEntry(new ItemStack(VoidCraft.tools.starforgedAxe)));
				list.add(new StarForgeToolEntry(new ItemStack(VoidCraft.tools.starforgedSpade)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			IStarForgeCapability cap = stack.getCapability(CapabilityList.STARFORGE, null);
			if (stack.getItem() instanceof IStarForgeTool) type = ((IStarForgeTool) stack.getItem()).getType();
			if (type != null) {
				for (StarForgeEffectRecipeList.Recipe recipe : StarForgeEffectRecipeList.instance.getRecipes(type)) {
					if (cap != null) {
						if (recipe.getEffect().getTier() == IStarForgeEffect.Tier.ONE && cap.getEffect(IStarForgeEffect.Tier.ONE) != null) continue;
						if (recipe.getEffect().getTier() == IStarForgeEffect.Tier.TWO && cap.getEffect(IStarForgeEffect.Tier.TWO) != null) continue;
						if (recipe.getEffect().getTier() == IStarForgeEffect.Tier.THREE && cap.getEffect(IStarForgeEffect.Tier.THREE) != null) continue;
					}
					list.add(new StarForgeEffectEntry(recipe));
				}
			}
		}
		return list;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void onUpdate() {

	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return SLOTS_ALL;
	}

	@Override
	public String getName() {
		return "teStarForge";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		switch (i) {
			case SLOT_INPUT_TOOL:
				return !itemstack.isEmpty() && itemstack.getItem() instanceof IStarForgeTool;
			case SLOT_INPUT_COSMICMATERIAL:
				return !itemstack.isEmpty() && itemstack.getItem() == Item.getItemFromBlock(VoidCraft.blocks.cosmicMaterial);
			case SLOT_INPUT_DRAGONSCALE:
				return !itemstack.isEmpty() && itemstack.getItem() == VoidCraft.items.voidicDragonScale;
			case SLOT_INPUT_QUORIFRAGMENT:
				return !itemstack.isEmpty() && itemstack.getItem() == VoidCraft.items.quoriFragment;
			case SLOT_INPUT_ASTRALESSENCE:
				return !itemstack.isEmpty() && itemstack.getItem() == VoidCraft.items.astralEssence;
			case SLOT_INPUT_VOIDICPHLOG:
				return !itemstack.isEmpty() && itemstack.getItem() == VoidCraft.items.voidicPhlogiston;
			default:
				return false;
		}
	}

	@Override
	protected boolean canExtractSlot(int i, ItemStack stack) {
		return i == SLOT_OUTPUT;
	}

}
