package tamaized.voidcraft.common.machina.addons;

import tamaized.tammodized.common.tileentity.TamTileEntityRecipeList;
import tamaized.voidcraft.VoidCraft;
import net.minecraft.item.ItemStack;

public class TERecipeInfuser extends TamTileEntityRecipeList<TERecipeInfuser.InfuserRecipe> {

	@Override
	protected String getName() {
		return "Infuser";
	}

	@Override
	protected String getModID() {
		return VoidCraft.modid;
	}

	public ItemStack getInput(ItemStack output) {
		for (InfuserRecipe recipe : getList()) {
			if (ItemStack.areItemStacksEqual(recipe.getOutput(), output)) return recipe.getInput()[0];
		}
		return ItemStack.EMPTY;
	}

	public class InfuserRecipe extends TamTileEntityRecipeList.TamTERecipe {

		private final int fluidAmount;

		public InfuserRecipe(ItemStack[] input, ItemStack output, int requiredFluid) {
			super(input, output);
			fluidAmount = requiredFluid;
		}

		public int getRequiredFluid() {
			return fluidAmount;
		}

	}

}
