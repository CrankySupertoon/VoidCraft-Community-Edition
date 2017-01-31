package Tamaized.Voidcraft.Addons.JEI.alchemy;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.machina.addons.TERecipesAlchemy.AlchemyRecipe;

public class AlchemyRecipeMaker {

	@Nonnull
	public static List<AlchemyRecipeWrapperJEI> getRecipes() {
		ArrayList<AlchemyRecipeWrapperJEI> recipes = new ArrayList<AlchemyRecipeWrapperJEI>();
		if (VoidCraft.teRecipes.alchemy.getList() != null) {
			for (AlchemyRecipe recipe : VoidCraft.teRecipes.alchemy.getList()) {
				recipes.add(new AlchemyRecipeWrapperJEI(recipe));
			}
		} else {
			VoidCraft.instance.logger.warn("Issue loading AlchemyRecipe JEI list");
		}
		return recipes;
	}
}
