package Tamaized.Voidcraft.Addons.JEI.macerator;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class MaceratorRecipeHandler implements IRecipeHandler<MaceratorRecipeWrapperJEI> {

	@Override
	public Class<MaceratorRecipeWrapperJEI> getRecipeClass() {
		return MaceratorRecipeWrapperJEI.class;
	}

	@Override
	public String getRecipeCategoryUid(MaceratorRecipeWrapperJEI recipe) {
		return "voidcraft_JEI_recipeCategory_Macerator";
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(MaceratorRecipeWrapperJEI recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(MaceratorRecipeWrapperJEI recipe) {
		return recipe.isValid();
	}

}
