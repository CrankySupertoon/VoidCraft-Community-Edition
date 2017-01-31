package Tamaized.Voidcraft.Addons.JEI.macerator;

import java.util.Arrays;

import Tamaized.Voidcraft.Addons.JEI.ItemStackRenderer;
import Tamaized.Voidcraft.Addons.JEI.VoidCraftRecipeWrapperJEI;
import Tamaized.Voidcraft.machina.addons.TERecipesMacerator.MaceratorRecipe;
import mezz.jei.api.gui.IGuiIngredientGroup;

public class MaceratorRecipeWrapperJEI extends VoidCraftRecipeWrapperJEI<MaceratorRecipe> {

	private static final ItemStackRenderer renderer = new ItemStackRenderer();

	private static final int OUTPUT_SLOT = 0;
	private static final int INPUT_SLOT = 2;

	public MaceratorRecipeWrapperJEI(MaceratorRecipe recipe) {
		super(recipe);
	}

	@Override
	public void setupSlots(IGuiIngredientGroup g) {
		g.init(OUTPUT_SLOT, false, renderer, 146, 34 - 20, 16, 16, 0, 0);
		g.init(INPUT_SLOT, true, renderer, 89, 33 - 20, 16, 16, 0, 0);

		g.set(OUTPUT_SLOT, Arrays.asList(getOutput()));
		g.set(INPUT_SLOT, getInputs());

	}

}
