package Tamaized.Voidcraft.vadeMecum;

import Tamaized.TamModized.helper.TranslateHelper;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.helper.RecipeHelper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class VadeMecumCraftingNormal implements IVadeMecumCrafting {

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/gui/vademecum/crafting_normal.png");

	private final String title;
	private final IRecipe recipe;
	private final ItemStack output;

	public VadeMecumCraftingNormal(String title, ItemStack output) {
		this.title = TranslateHelper.translate(title);
		recipe = RecipeHelper.getRecipe(output);
		this.output = output;
	}

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65, y, 0x000000);
		GlStateManager.color(1, 1, 1, 1);
		gui.mc.getTextureManager().bindTexture(TEXTURE);
		gui.drawTexturedModalRect(x, y + 35, 128, 128, 0, 0, 256, 256);
		if (recipe instanceof ShapedRecipes) {
			ShapedRecipes r = (ShapedRecipes) recipe;
			int size = (r.recipeItems.size() == 4 || r.recipeItems.size() == 9) ? (int) Math.sqrt(r.recipeItems.size()) : 3;
			for (int index = 0; index <= r.recipeItems.size() - 1; index++) {
				if (r.recipeItems.get(index).getMatchingStacks().length == 0)
					continue;
				ItemStack stack = r.recipeItems.get(index).getMatchingStacks()[0];
				if (!stack.isEmpty())
					gui.renderItemStack(new ItemStack(stack.getItem(), stack.getCount(), stack.getItemDamage() == 32767 ? 0 : stack.getItemDamage()), x + 15 + (40 * (index % size)), y + 50 + (40 * (index / size)), mx, my);
			}
		} else if (recipe instanceof ShapelessOreRecipe) {
			ShapelessOreRecipe r = (ShapelessOreRecipe) recipe;
			int size = r.getIngredients().size() > 3 ? (int) Math.sqrt(r.getIngredients().size()) : 3;
			int index = 0;
			for (Ingredient ing : r.getIngredients()) {
				if (ing.getMatchingStacks().length == 0)
					continue;
				ItemStack stack = ing.getMatchingStacks()[0];
				if (!stack.isEmpty())
					gui.renderItemStack(new ItemStack(stack.getItem(), stack.getCount(), stack.getItemDamage() == 32767 ? 0 : stack.getItemDamage()), x + 15 + (40 * (index % size)), y + 50 + (40 * (index / size)), mx, my);
				index++;
			}
		}

		// if (!input[0].isEmpty()) gui.renderItemStack(input[0], x + 15, y + 50, mx, my);
		// if (!input[1].isEmpty()) gui.renderItemStack(input[1], x + 55, y + 50, mx, my);
		// if (!input[2].isEmpty()) gui.renderItemStack(input[2], x + 95, y + 50, mx, my);
		//
		// if (!input[3].isEmpty()) gui.renderItemStack(input[3], x + 15, y + 90, mx, my);
		// if (!input[4].isEmpty()) gui.renderItemStack(input[4], x + 55, y + 90, mx, my);
		// if (!input[5].isEmpty()) gui.renderItemStack(input[5], x + 95, y + 90, mx, my);
		//
		// if (!input[6].isEmpty()) gui.renderItemStack(input[6], x + 15, y + 130, mx, my);
		// if (!input[7].isEmpty()) gui.renderItemStack(input[7], x + 55, y + 130, mx, my);
		// if (!input[8].isEmpty()) gui.renderItemStack(input[8], x + 95, y + 130, mx, my);

		gui.renderItemStack(output, x + 55, y + 20, mx, my);
	}

}
