package Tamaized.Voidcraft.Addons.JEI.blastfurnace;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.machina.addons.TERecipesBlastFurnace.BlastFurnaceRecipe;

public class BlastFurnaceRecipeMaker {

	@Nonnull
	public static List<BlastFurnaceRecipeWrapperJEI> getRecipes() {
		ArrayList<BlastFurnaceRecipeWrapperJEI> recipes = new ArrayList<BlastFurnaceRecipeWrapperJEI>();
		for (BlastFurnaceRecipe recipe : VoidCraft.teRecipes.blastFurnace.getList())
			recipes.add(new BlastFurnaceRecipeWrapperJEI(recipe));
		return recipes;
	}
}
