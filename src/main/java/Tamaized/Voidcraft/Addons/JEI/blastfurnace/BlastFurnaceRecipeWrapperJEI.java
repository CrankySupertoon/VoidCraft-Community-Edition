package Tamaized.Voidcraft.Addons.JEI.blastfurnace;

import java.util.Arrays;

import Tamaized.Voidcraft.Addons.JEI.ItemStackRenderer;
import Tamaized.Voidcraft.Addons.JEI.VoidCraftRecipeWrapperJEI;
import Tamaized.Voidcraft.machina.addons.TERecipesBlastFurnace.BlastFurnaceRecipe;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBlastFurnace;
import mezz.jei.api.gui.IGuiIngredientGroup;

public class BlastFurnaceRecipeWrapperJEI extends VoidCraftRecipeWrapperJEI<BlastFurnaceRecipe> {
	
	private static final ItemStackRenderer renderer = new ItemStackRenderer();

	public BlastFurnaceRecipeWrapperJEI(BlastFurnaceRecipe recipe) {
		super(recipe);
	}

	@Override
	public void setupSlots(IGuiIngredientGroup g) {
		g.init(TileEntityVoidBlastFurnace.SLOT_OUTPUT, false, renderer, 133, 14, 16, 16, 0, 0);
		g.init(TileEntityVoidBlastFurnace.SLOT_INPUT_IRON, true, renderer, 87, 6, 16, 16, 0, 0);
		g.init(TileEntityVoidBlastFurnace.SLOT_INPUT_COAL, true, renderer, 87, 24, 16, 16, 0, 0);

		g.set(TileEntityVoidBlastFurnace.SLOT_OUTPUT, Arrays.asList(getOutput()));
		g.set(TileEntityVoidBlastFurnace.SLOT_INPUT_IRON, getInputs().get(0));
		g.set(TileEntityVoidBlastFurnace.SLOT_INPUT_COAL, getInputs().get(1));
	}

}
