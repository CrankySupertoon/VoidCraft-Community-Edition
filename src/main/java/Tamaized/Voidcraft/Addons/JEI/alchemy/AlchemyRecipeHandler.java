package Tamaized.Voidcraft.Addons.JEI.alchemy;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class AlchemyRecipeHandler implements IRecipeHandler<AlchemyRecipeWrapperJEI> {

	@Override
	public Class<AlchemyRecipeWrapperJEI> getRecipeClass() {
		return AlchemyRecipeWrapperJEI.class;
	}

	@Override
	public String getRecipeCategoryUid(AlchemyRecipeWrapperJEI recipe) {
		return "voidcraft_JEI_recipeCategory_Alchemy";
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(AlchemyRecipeWrapperJEI recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(AlchemyRecipeWrapperJEI recipe) {
		return recipe.isValid();
	}

}
