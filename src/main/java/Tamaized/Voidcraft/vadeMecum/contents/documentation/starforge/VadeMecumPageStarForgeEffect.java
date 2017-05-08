package Tamaized.Voidcraft.vadeMecum.contents.documentation.starforge;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.starforge.StarForgeEffectRecipeList;
import Tamaized.Voidcraft.starforge.effects.IStarForgeEffect;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class VadeMecumPageStarForgeEffect extends VadeMecumPage {

	private StarForgeEffectRecipeList.Recipe recipe;

	public VadeMecumPageStarForgeEffect(String title, String text, IStarForgeEffect effect) {
		super(title, text);
		recipe = StarForgeEffectRecipeList.instance.getRecipe(effect);
	}

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		super.render(gui, render, x, y, mx, my, offset);
		int index = 0;
		for (ItemStack stack : recipe.getInputs()) {
			gui.renderItemStack(stack, (x + offset) + (24 * index), y + 130, mx, my, 0xCCCCCC);
			index++;
		}
	}

}
