package Tamaized.Voidcraft.Addons.JEI.infuser;

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

public class InfuserRecipeCategory implements IRecipeCategory {

	private final ResourceLocation background = new ResourceLocation(VoidCraft.modid, "textures/gui/JEI/voidInfuser.png");
	private IDrawableAnimated fluidAnimation;
	private IDrawableAnimated progressAnimation;

	// private final ICraftingGridHelper craftingGridHelper;

	public InfuserRecipeCategory() {
		// craftingGridHelper = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createCraftingGridHelper(INPUT_SLOT, OUTPUT_SLOT);

		IDrawableStatic fluidRender = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 0, 452, 20, 46, 20, 0, 15, 0);
		fluidAnimation = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createAnimatedDrawable(fluidRender, 200, IDrawableAnimated.StartDirection.TOP, true);

		IDrawableStatic progressRender = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 0, 434, 80, 16, 35, 0, 110, 0);
		progressAnimation = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createAnimatedDrawable(progressRender, 80, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public String getUid() {
		return "voidcraft_JEI_recipeCategory_Infuser";
	}

	@Override
	public String getTitle() {
		return "Void Infuser";
	}

	@Override
	public IDrawable getBackground() {
		return VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 0, 0, 180, 70, 0, 0, 0, 0);
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
		fluidAnimation.draw(minecraft);
		progressAnimation.draw(minecraft);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if (recipeWrapper instanceof InfuserRecipeWrapperJEI) {
			InfuserRecipeWrapperJEI recipe = (InfuserRecipeWrapperJEI) recipeWrapper;
			recipe.setupSlots(recipeLayout.getIngredientsGroup(ItemStack.class));
		}
	}

	@Override
	public IDrawable getIcon() {
		return null;
	}

}
