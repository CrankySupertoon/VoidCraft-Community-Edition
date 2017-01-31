package Tamaized.Voidcraft.Addons.JEI;

import javax.annotation.Nonnull;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.Addons.JEI.alchemy.AlchemyRecipeCategory;
import Tamaized.Voidcraft.Addons.JEI.alchemy.AlchemyRecipeHandler;
import Tamaized.Voidcraft.Addons.JEI.alchemy.AlchemyRecipeMaker;
import Tamaized.Voidcraft.Addons.JEI.infuser.InfuserRecipeCategory;
import Tamaized.Voidcraft.Addons.JEI.infuser.InfuserRecipeHandler;
import Tamaized.Voidcraft.Addons.JEI.infuser.InfuserRecipeMaker;
import Tamaized.Voidcraft.Addons.JEI.macerator.MaceratorRecipeCategory;
import Tamaized.Voidcraft.Addons.JEI.macerator.MaceratorRecipeHandler;
import Tamaized.Voidcraft.Addons.JEI.macerator.MaceratorRecipeMaker;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class VoidCraftJEIPlugin extends BlankModPlugin {

	public static IJeiHelpers jeiHelpers;

	@Override
	public void register(@Nonnull IModRegistry registry) {
		VoidCraft.instance.logger.info("JEI detected, loading VoidCraft JEI Plugin");
		IIngredientRegistry itemRegistry = registry.getIngredientRegistry();
		jeiHelpers = registry.getJeiHelpers();

		registry.addRecipeCategories(new InfuserRecipeCategory(), new MaceratorRecipeCategory(), new AlchemyRecipeCategory());

		registry.addRecipes(InfuserRecipeMaker.getRecipes());
		registry.addRecipes(MaceratorRecipeMaker.getRecipes());
		registry.addRecipes(AlchemyRecipeMaker.getRecipes());

		registry.addRecipeHandlers(new InfuserRecipeHandler(), new MaceratorRecipeHandler(), new AlchemyRecipeHandler());

		registry.addRecipeCategoryCraftingItem(new ItemStack(VoidCraft.blocks.voidInfuser), "voidcraft_JEI_recipeCategory_Infuser");
		registry.addRecipeCategoryCraftingItem(new ItemStack(VoidCraft.blocks.voidMacerator), "voidcraft_JEI_recipeCategory_Macerator");
		registry.addRecipeCategoryCraftingItem(new ItemStack(VoidCraft.blocks.voidicAlchemyTable), "voidcraft_JEI_recipeCategory_Alchemy");

	}

}
