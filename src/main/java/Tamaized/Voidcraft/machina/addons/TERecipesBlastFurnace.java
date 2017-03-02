package Tamaized.Voidcraft.machina.addons;

import Tamaized.TamModized.tileentity.TamTileEntityRecipeList;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.machina.addons.TERecipesMacerator.MaceratorRecipe;
import net.minecraft.item.ItemStack;

public class TERecipesBlastFurnace extends TamTileEntityRecipeList<TERecipesBlastFurnace.BlastFurnaceRecipe> {

	@Override
	protected String getName() {
		return "Blast Furnace";
	}

	@Override
	protected String getModID() {
		return VoidCraft.modid;
	}

	public ItemStack[] getInput(ItemStack output) {
		for (BlastFurnaceRecipe recipe : getList()) {
			if (ItemStack.areItemStacksEqual(recipe.getOutput(), output)) return recipe.getInput();
		}
		return new ItemStack[]{ null, null };
	}

	public class BlastFurnaceRecipe extends TamTileEntityRecipeList.TamTERecipe {

		private final int powerAmount;

		public BlastFurnaceRecipe(ItemStack[] input, ItemStack output, int requiredPower) {
			super(input, output);
			powerAmount = requiredPower;
		}

		public int getRequiredPower() {
			return powerAmount;
		}

	}

}
