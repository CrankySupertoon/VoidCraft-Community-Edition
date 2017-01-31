package Tamaized.Voidcraft.Addons.JEI.infuser;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.machina.addons.TERecipeInfuser.InfuserRecipe;

public class InfuserRecipeMaker {

	@Nonnull
	public static List<InfuserRecipeWrapperJEI> getRecipes() {
		ArrayList<InfuserRecipeWrapperJEI> recipes = new ArrayList<InfuserRecipeWrapperJEI>();
		for (InfuserRecipe recipe : VoidCraft.teRecipes.infuser.getList())
			recipes.add(new InfuserRecipeWrapperJEI(recipe));
		return recipes;
	}
}
