package Tamaized.Voidcraft.Addons.JEI.macerator;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.machina.addons.TERecipesMacerator.MaceratorRecipe;

public class MaceratorRecipeMaker {

	@Nonnull
	public static List<MaceratorRecipeWrapperJEI> getRecipes() {
		ArrayList<MaceratorRecipeWrapperJEI> recipes = new ArrayList<MaceratorRecipeWrapperJEI>();
		for (MaceratorRecipe recipe : VoidCraft.teRecipes.macerator.getList())
			recipes.add(new MaceratorRecipeWrapperJEI(recipe));
		return recipes;
	}
}
