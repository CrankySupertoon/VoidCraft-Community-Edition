package Tamaized.Voidcraft.Addons.JEI.macerator;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.Addons.JEI.VoidCraftJEIPlugin;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class MaceratorRecipeCategory implements IRecipeCategory {

	private final ResourceLocation background = new ResourceLocation(VoidCraft.modid, "textures/gui/JEI/voidMacerator.png");
	private IDrawableAnimated fluidAnimation;
	private IDrawableAnimated progressAnimation;

	// private final ICraftingGridHelper craftingGridHelper;

	public MaceratorRecipeCategory() {
		// craftingGridHelper = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createCraftingGridHelper(INPUT_SLOT, OUTPUT_SLOT);

		IDrawableStatic powerRender = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 12, 448, 20, 48, 20 - 20, 0, 36, 0);
		fluidAnimation = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createAnimatedDrawable(powerRender, 200, IDrawableAnimated.StartDirection.TOP, true);

		IDrawableStatic progressRender = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 00, 434, 26, 16, 33 - 20, 0, 110, 0);
		progressAnimation = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createAnimatedDrawable(progressRender, 100, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public String getUid() {
		return "voidcraft_JEI_recipeCategory_Macerator";
	}

	@Override
	public String getTitle() {
		return "Void Macerator";
	}

	@Override
	public IDrawable getBackground() {
		return VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 0, 0, 180, 75, -20, 0, 0, 0);
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
		fluidAnimation.draw(minecraft);
		progressAnimation.draw(minecraft);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if (recipeWrapper instanceof MaceratorRecipeWrapperJEI) {
			MaceratorRecipeWrapperJEI recipe = (MaceratorRecipeWrapperJEI) recipeWrapper;
			recipe.setupSlots(recipeLayout.getIngredientsGroup(ItemStack.class));
		}
	}

	@Override
	public IDrawable getIcon() {
		return null;
	}

}
