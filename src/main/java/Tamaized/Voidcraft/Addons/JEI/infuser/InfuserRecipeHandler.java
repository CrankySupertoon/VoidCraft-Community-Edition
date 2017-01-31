package Tamaized.Voidcraft.Addons.JEI.infuser;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class InfuserRecipeHandler implements IRecipeHandler<InfuserRecipeWrapperJEI> {

	@Override
	public Class<InfuserRecipeWrapperJEI> getRecipeClass() {
		return InfuserRecipeWrapperJEI.class;
	}

	@Override
	public String getRecipeCategoryUid(InfuserRecipeWrapperJEI recipe) {
		return "voidcraft_JEI_recipeCategory_Infuser";
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(InfuserRecipeWrapperJEI recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(InfuserRecipeWrapperJEI recipe) {
		return recipe.isValid();
	}

}
