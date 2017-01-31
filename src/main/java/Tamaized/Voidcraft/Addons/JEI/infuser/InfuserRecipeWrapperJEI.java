package Tamaized.Voidcraft.Addons.JEI.infuser;

import java.util.Arrays;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.Addons.JEI.VoidCraftRecipeWrapperJEI;
import Tamaized.Voidcraft.machina.addons.TERecipeInfuser.InfuserRecipe;
import mezz.jei.api.gui.IGuiIngredientGroup;
import mezz.jei.plugins.vanilla.ingredients.ItemStackRenderer;

public class InfuserRecipeWrapperJEI extends VoidCraftRecipeWrapperJEI<InfuserRecipe> {

	private static final ItemStackRenderer renderer = new ItemStackRenderer();

	public static final int OUTPUT_SLOT = 0;
	public static final int FLUID_SLOT = 1;
	public static final int INPUT_SLOT = 2;

	public InfuserRecipeWrapperJEI(InfuserRecipe r) {
		super(r);
	}

	@Override
	public void setupSlots(IGuiIngredientGroup g) {
		g.init(OUTPUT_SLOT, false, renderer, 146, 34, 18, 18, 0, 0);
		g.init(FLUID_SLOT, true, renderer, 51, 33, 18, 18, 0, 0);
		g.init(INPUT_SLOT, true, renderer, 89, 33, 18, 18, 0, 0);

		g.set(FLUID_SLOT, Arrays.asList(VoidCraft.fluids.voidBucket.getBucket()));
		g.set(OUTPUT_SLOT, Arrays.asList(getOutput()));
		g.set(INPUT_SLOT, getInputs());
	}

}
