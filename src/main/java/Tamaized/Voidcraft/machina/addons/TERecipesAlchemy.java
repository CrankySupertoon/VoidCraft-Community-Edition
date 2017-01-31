package Tamaized.Voidcraft.machina.addons;

import Tamaized.TamModized.tileentity.TamTileEntityRecipeList;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import net.minecraft.item.ItemStack;

public class TERecipesAlchemy extends TamTileEntityRecipeList<TERecipesAlchemy.AlchemyRecipe> {

	protected String getName() {
		return "Alchemy";
	}

	@Override
	protected String getModID() {
		return VoidCraft.modid;
	}

	public ItemStack getOutput(IVadeMecumCapability cap, ItemStack[] stacks) {
		loop: for (TERecipesAlchemy.AlchemyRecipe recipe : getList()) {
			if (recipe.getInput().length != stacks.length || (recipe.getCategory() != null && (cap == null || !cap.hasCategory(recipe.getCategory())))) continue;
			for (ItemStack stack : stacks) {
				for (ItemStack checkStack : recipe.getInput()) {
					if (stack.getItem() == checkStack.getItem()) {
						continue loop;
					}
				}
			}
			return recipe.getOutput();
		}
		return ItemStack.EMPTY;
	}

	public TERecipesAlchemy.AlchemyRecipe getRecipe(IVadeMecumCapability cap, ItemStack[] stacks) {
		loop: for (TERecipesAlchemy.AlchemyRecipe recipe : getList()) {
			if (recipe.getInput().length != stacks.length || (recipe.getCategory() != null && (cap == null || !cap.hasCategory(recipe.getCategory())))) continue;
			for (ItemStack stack : stacks) {
				boolean flag2 = false;
				for (ItemStack checkStack : recipe.getInput()) {
					if (stack.getItem() == checkStack.getItem()) {
						flag2 = true;
						break;
					}
				}
				if (!flag2) continue loop;
			}
			return recipe;
		}
		return null;
	}

	public class AlchemyRecipe extends TamTileEntityRecipeList.TamTERecipe {

		private final int powerAmount;
		private final IVadeMecumCapability.Category category;

		public AlchemyRecipe(IVadeMecumCapability.Category cat, ItemStack[] input, ItemStack output, int requiredPower) {
			super(input, output);
			powerAmount = requiredPower;
			category = cat;
		}

		public int getRequiredPower() {
			return powerAmount;
		}

		public IVadeMecumCapability.Category getCategory() {
			return category;
		}

	}

}
