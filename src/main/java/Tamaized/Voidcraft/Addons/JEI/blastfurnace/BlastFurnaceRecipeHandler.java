package Tamaized.Voidcraft.Addons.JEI.blastfurnace;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class BlastFurnaceRecipeHandler implements IRecipeHandler<BlastFurnaceRecipeWrapperJEI> {

	@Override
	public Class<BlastFurnaceRecipeWrapperJEI> getRecipeClass() {
		return BlastFurnaceRecipeWrapperJEI.class;
	}

	@Override
	public String getRecipeCategoryUid(BlastFurnaceRecipeWrapperJEI recipe) {
		return "voidcraft_JEI_recipeCategory_BlastFurnace";
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(BlastFurnaceRecipeWrapperJEI recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(BlastFurnaceRecipeWrapperJEI recipe) {
		return recipe.isValid();
	}

}
