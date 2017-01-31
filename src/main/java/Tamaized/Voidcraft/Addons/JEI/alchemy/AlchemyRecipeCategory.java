package Tamaized.Voidcraft.Addons.JEI.alchemy;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.Addons.JEI.VoidCraftJEIPlugin;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.machina.addons.TERecipesAlchemy.AlchemyRecipe;
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

public class AlchemyRecipeCategory implements IRecipeCategory {

	private final ResourceLocation background = new ResourceLocation(VoidCraft.modid, "textures/gui/JEI/voidicAlchemy.png");
	private IDrawableAnimated powerAnimation;
	private IDrawableAnimated progressAnimation;

	public AlchemyRecipeCategory() {
		// craftingGridHelper = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createCraftingGridHelper(TileEntityVoidicAlchemy.SLOT_INPUT_1, TileEntityVoidicAlchemy.SLOT_OUTPUT);

		IDrawableStatic powerRender = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 12, 419, 12, 47, 5, 0, -14, 0);
		powerAnimation = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createAnimatedDrawable(powerRender, 500, IDrawableAnimated.StartDirection.TOP, true);

		IDrawableStatic progressRender = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 24, 420, 4, 11, 0, 0, 44, 0);
		progressAnimation = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createAnimatedDrawable(progressRender, 100, IDrawableAnimated.StartDirection.BOTTOM, false);
	}

	@Override
	public String getUid() {
		return "voidcraft_JEI_recipeCategory_Alchemy";
	}

	@Override
	public String getTitle() {
		return "Void Alchemy Table";
	}

	@Override
	public IDrawable getBackground() {
		return VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 0, 0, 150, 75, -10, 0, -60, 0);
	}
	
	@Override
	public void drawExtras(Minecraft minecraft) {
		powerAnimation.draw(minecraft);
		progressAnimation.draw(minecraft);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if (recipeWrapper instanceof AlchemyRecipeWrapperJEI) {
			AlchemyRecipeWrapperJEI recipe = (AlchemyRecipeWrapperJEI) recipeWrapper;
			recipe.setupSlots(recipeLayout.getIngredientsGroup(ItemStack.class));
		}
	}

	@Override
	public IDrawable getIcon() {
		return null;
	}

}
