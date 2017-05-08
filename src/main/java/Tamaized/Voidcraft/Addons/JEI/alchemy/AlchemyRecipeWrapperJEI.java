package Tamaized.Voidcraft.Addons.JEI.alchemy;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.Addons.JEI.ItemStackRenderer;
import Tamaized.TamModized.helper.TranslateHelper;
import Tamaized.Voidcraft.Addons.JEI.VoidCraftRecipeWrapperJEI;
import Tamaized.Voidcraft.machina.addons.TERecipesAlchemy.AlchemyRecipe;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicAlchemy;
import Tamaized.Voidcraft.vadeMecum.progression.VadeMecumWordsOfPower;
import mezz.jei.api.gui.IGuiIngredientGroup;
import net.minecraft.client.Minecraft;

public class AlchemyRecipeWrapperJEI extends VoidCraftRecipeWrapperJEI<AlchemyRecipe> {

	private static final ItemStackRenderer renderer = new ItemStackRenderer();

	public AlchemyRecipeWrapperJEI(AlchemyRecipe r) {
		super(r);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		minecraft.fontRendererObj.drawString(TranslateHelper.translate("voidcraft.gui.jei.alch.research"), -40, 0, 0x000000);
		minecraft.fontRendererObj.drawString(getRecipe().getCategory() == null ? TranslateHelper.translate("voidcraft.gui.misc.none") : TranslateHelper.translate(VadeMecumWordsOfPower.getCategoryData(getRecipe().getCategory()).getName()), -40, 10, 0x000000);
		super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);
	}

	@Override
	public void setupSlots(IGuiIngredientGroup g) {
		
		final int x = 0;
		final int y = 41;
		
		g.init(TileEntityVoidicAlchemy.SLOT_OUTPUT, false, renderer, 38 + x, 21 + y, 16, 16, 0, 0);
		g.init(TileEntityVoidicAlchemy.SLOT_INPUT_1, true, renderer, 14 + x, -4 + y, 16, 16, 0, 0);
		g.init(TileEntityVoidicAlchemy.SLOT_INPUT_2, true, renderer, 3 + x, 21 + y, 16, 16, 0, 0);
		g.init(TileEntityVoidicAlchemy.SLOT_INPUT_3, true, renderer, 14 + x, 46 + y, 16, 16, 0, 0);
		g.init(TileEntityVoidicAlchemy.SLOT_INPUT_4, true, renderer, 62 + x, -4 + y, 16, 16, 0, 0);
		g.init(TileEntityVoidicAlchemy.SLOT_INPUT_5, true, renderer, 73 + x, 21 + y, 16, 16, 0, 0);
		g.init(TileEntityVoidicAlchemy.SLOT_INPUT_6, true, renderer, 62 + x, 46 + y, 16, 16, 0, 0);

		g.set(TileEntityVoidicAlchemy.SLOT_OUTPUT, getOutput());

		g.set(TileEntityVoidicAlchemy.SLOT_INPUT_1, getInputs().get(0));
		g.set(TileEntityVoidicAlchemy.SLOT_INPUT_2, getInputs().get(1));
		g.set(TileEntityVoidicAlchemy.SLOT_INPUT_3, getInputs().get(2));
		g.set(TileEntityVoidicAlchemy.SLOT_INPUT_4, getInputs().get(3));
		g.set(TileEntityVoidicAlchemy.SLOT_INPUT_5, getInputs().get(4));
		g.set(TileEntityVoidicAlchemy.SLOT_INPUT_6, getInputs().get(5));

	}

}
