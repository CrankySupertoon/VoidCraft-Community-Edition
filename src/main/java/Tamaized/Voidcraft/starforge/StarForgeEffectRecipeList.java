package Tamaized.Voidcraft.starforge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.starforge.effects.IStarForgeEffect;
import Tamaized.Voidcraft.starforge.effects.StarForgeEffectList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class StarForgeEffectRecipeList {

	public static final StarForgeEffectRecipeList instance = new StarForgeEffectRecipeList();

	private final List<Recipe> recipes = new ArrayList<Recipe>();
	private final Map<IStarForgeEffect.Type, List<Recipe>> typeRecipes = new HashMap<IStarForgeEffect.Type, List<Recipe>>();

	public int getRecipeID(Recipe recipe) {
		return recipes.indexOf(recipe);
	}

	public Recipe getRecipe(int id) {
		return recipes.get(id);
	}

	public Recipe getRecipe(IStarForgeEffect effect) {
		for (Recipe recipe : recipes)
			if (recipe.getEffect() == effect) return recipe;
		return null;
	}

	public List<Recipe> getRecipes(IStarForgeEffect.Type type) {
		return typeRecipes.get(type);
	}

	private StarForgeEffectRecipeList() {

	}

	public void register() {
		recipes.clear();
		typeRecipes.clear();
		registerRecipes();
		List<Recipe> listSword = new ArrayList<Recipe>();
		List<Recipe> listTool = new ArrayList<Recipe>();
		for (Recipe recipe : recipes) {
			switch (recipe.getType()) {
				case SWORD:
					listSword.add(recipe);
					break;
				case TOOL:
					listTool.add(recipe);
					break;
				default:
					break;
			}
		}
		typeRecipes.put(IStarForgeEffect.Type.SWORD, listSword);
		typeRecipes.put(IStarForgeEffect.Type.TOOL, listTool);

	}

	private void registerRecipes() {
		register(StarForgeEffectList.blindingFear, new ItemStack(VoidCraft.blocks.cosmicMaterial, 8), new ItemStack(VoidCraft.items.astralEssence, 4), new ItemStack(VoidCraft.items.voidicPhlogiston, 4));
		register(StarForgeEffectList.firstDegreeBurns, new ItemStack(VoidCraft.blocks.cosmicMaterial, 8), new ItemStack(VoidCraft.items.voidicPhlogiston, 8));
		register(StarForgeEffectList.voidTouch, new ItemStack(VoidCraft.blocks.cosmicMaterial, 8), new ItemStack(VoidCraft.items.astralEssence, 8));

		register(StarForgeEffectList.xiaBlessing, new ItemStack(VoidCraft.blocks.cosmicMaterial, 16), new ItemStack(VoidCraft.items.astralEssence, 8), new ItemStack(VoidCraft.items.voidicPhlogiston, 8));
		register(StarForgeEffectList.secondDegreeBurns, new ItemStack(VoidCraft.blocks.cosmicMaterial, 16), new ItemStack(VoidCraft.items.voidicPhlogiston, 16));
		register(StarForgeEffectList.voidWrath, new ItemStack(VoidCraft.blocks.cosmicMaterial, 16), new ItemStack(VoidCraft.items.astralEssence, 8), new ItemStack(VoidCraft.items.voidicDragonScale, 8));

		register(StarForgeEffectList.mortalFear, new ItemStack(VoidCraft.blocks.cosmicMaterial, 32), new ItemStack(VoidCraft.items.quoriFragment, 1), new ItemStack(VoidCraft.items.astralEssence, 16), new ItemStack(VoidCraft.items.voidicDragonScale, 16));
		register(StarForgeEffectList.thirdDegreeBurns, new ItemStack(VoidCraft.blocks.cosmicMaterial, 32), new ItemStack(VoidCraft.items.quoriFragment, 1), new ItemStack(VoidCraft.items.voidicPhlogiston, 32));
		register(StarForgeEffectList.voidCripple, new ItemStack(VoidCraft.blocks.cosmicMaterial, 32), new ItemStack(VoidCraft.items.quoriFragment, 1), new ItemStack(VoidCraft.items.astralEssence, 8), new ItemStack(VoidCraft.items.voidicDragonScale, 24));
		register(StarForgeEffectList.vorp, new ItemStack(VoidCraft.blocks.cosmicMaterial, 32), new ItemStack(VoidCraft.items.quoriFragment, 1), new ItemStack(VoidCraft.items.astralEssence, 32));

		register(StarForgeEffectList.haste, new ItemStack(VoidCraft.blocks.cosmicMaterial, 8), new ItemStack(VoidCraft.items.astralEssence, 8));

		register(StarForgeEffectList.silkTouch, new ItemStack(VoidCraft.blocks.cosmicMaterial, 16), new ItemStack(VoidCraft.items.astralEssence, 8), new ItemStack(VoidCraft.items.voidicPhlogiston, 4), new ItemStack(VoidCraft.items.voidicDragonScale, 4));
		register(StarForgeEffectList.fortune, new ItemStack(VoidCraft.blocks.cosmicMaterial, 16), new ItemStack(VoidCraft.items.astralEssence, 8), new ItemStack(VoidCraft.items.voidicDragonScale, 8));

		register(StarForgeEffectList.threeByThree, new ItemStack(VoidCraft.blocks.cosmicMaterial, 32), new ItemStack(VoidCraft.items.quoriFragment, 1), new ItemStack(VoidCraft.items.astralEssence, 16), new ItemStack(VoidCraft.items.voidicPhlogiston, 8), new ItemStack(VoidCraft.items.voidicDragonScale, 8));
	}

	private void register(IStarForgeEffect effect, ItemStack... stack) {
		List<ItemStack> stacks = new ArrayList<ItemStack>();
		for (ItemStack s : stack)
			stacks.add(s);
		recipes.add(new Recipe(stacks, effect));
	}

	public final class Recipe {

		private final List<ItemStack> inputs;
		private final IStarForgeEffect effect;

		public Recipe(List<ItemStack> stacks, IStarForgeEffect effect) {
			inputs = stacks;
			this.effect = effect;
		}

		public boolean isInput(ItemStack stack) {
			for (ItemStack s : inputs) {
				if (ItemStack.areItemStacksEqual(s, stack)) return true;
			}
			return false;
		}

		public List<ItemStack> getInputs() {
			return inputs;
		}

		public IStarForgeEffect getEffect() {
			return effect;
		}

		public IStarForgeEffect.Type getType() {
			return effect.getType();
		}

	}

}
