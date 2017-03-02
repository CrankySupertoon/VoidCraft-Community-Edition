package Tamaized.Voidcraft.machina.addons;

import Tamaized.TamModized.tileentity.TamTileEntityRecipeList;
import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.item.ItemStack;

public class TERecipesMacerator extends TamTileEntityRecipeList<TERecipesMacerator.MaceratorRecipe> {

	@Override
	protected String getName() {
		return "Macerator";
	}

	@Override
	protected String getModID() {
		return VoidCraft.modid;
	}

	public ItemStack getInput(ItemStack output) {
		for (MaceratorRecipe recipe : getList()) {
			if (ItemStack.areItemStacksEqual(recipe.getOutput(), output)) return recipe.getInput()[0];
		}
		return null;
	}

	public class MaceratorRecipe extends TamTileEntityRecipeList.TamTERecipe {

		private final int powerAmount;

		public MaceratorRecipe(ItemStack[] input, ItemStack output, int requiredPower) {
			super(input, output);
			powerAmount = requiredPower;
		}

		public int getRequiredPower() {
			return powerAmount;
		}

	}

}
