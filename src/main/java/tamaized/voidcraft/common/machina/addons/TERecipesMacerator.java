package tamaized.voidcraft.common.machina.addons;

import net.minecraft.item.ItemStack;
import tamaized.tammodized.common.tileentity.TamTileEntityRecipeList;
import tamaized.voidcraft.VoidCraft;

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
			if (ItemStack.areItemsEqual(recipe.getOutput(), output))
				return recipe.getInput()[0];
		}
		return ItemStack.EMPTY;
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
