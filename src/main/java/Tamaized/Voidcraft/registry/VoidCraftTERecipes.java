package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.machina.addons.TERecipeInfuser;
import Tamaized.Voidcraft.machina.addons.TERecipesAlchemy;
import Tamaized.Voidcraft.machina.addons.TERecipesBlastFurnace;
import Tamaized.Voidcraft.machina.addons.TERecipesMacerator;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.oredict.OreDictionary;

public class VoidCraftTERecipes implements ITamRegistry {

	public static TERecipesMacerator macerator;
	public static TERecipeInfuser infuser;
	public static TERecipesAlchemy alchemy;
	public static TERecipesBlastFurnace blastFurnace;

	@Override
	public void preInit() {
		macerator = new TERecipesMacerator();
		infuser = new TERecipeInfuser();
		alchemy = new TERecipesAlchemy();
		blastFurnace = new TERecipesBlastFurnace();
	}

	@Override
	public void init() {
		for (ItemStack input : getOreDict(new String[] { "oreCoal" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.coalDust, 8), 200));
		for (ItemStack input : getOreDict(new String[] { "oreQuartz" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.quartzDust, 4), 200));
		for (ItemStack input : getOreDict(new String[] { "oreIron" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.ironDust, 4), 200));
		for (ItemStack input : getOreDict(new String[] { "oreGold" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.goldDust, 4), 200));
		for (ItemStack input : getOreDict(new String[] { "oreCopper" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.copperDust, 4), 200));
		for (ItemStack input : getOreDict(new String[] { "oreTin" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.tinDust, 4), 200));
		for (ItemStack input : getOreDict(new String[] { "oreLead" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.leadDust, 4), 200));
		for (ItemStack input : getOreDict(new String[] { "oreLapis" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.lapisDust, 8), 200));
		for (ItemStack input : getOreDict(new String[] { "oreEmerald" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.emeraldDust, 4), 200));
		for (ItemStack input : getOreDict(new String[] { "oreDiamond" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.diamondDust, 4), 200));

		macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { new ItemStack(Items.COAL, 1) }, new ItemStack(VoidCraft.items.coalDust, 4), 200));
		for (ItemStack input : getOreDict(new String[] { "gemQuartz" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.quartzDust, 1), 200));
		for (ItemStack input : getOreDict(new String[] { "ingotIron" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.ironDust, 1), 200));
		for (ItemStack input : getOreDict(new String[] { "ingotGold" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.goldDust, 1), 200));
		for (ItemStack input : getOreDict(new String[] { "ingotCopper" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.copperDust, 1), 200));
		for (ItemStack input : getOreDict(new String[] { "ingotTin" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.tinDust, 1), 200));
		for (ItemStack input : getOreDict(new String[] { "ingotLead" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.leadDust, 1), 200));
		for (ItemStack input : getOreDict(new String[] { "gemLapis" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.lapisDust, 1), 200));
		for (ItemStack input : getOreDict(new String[] { "gemEmerald" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.emeraldDust, 1), 200));
		for (ItemStack input : getOreDict(new String[] { "gemDiamond" }))
			macerator.registerRecipe(macerator.new MaceratorRecipe(new ItemStack[] { input }, new ItemStack(VoidCraft.items.diamondDust, 1), 200));

		blastFurnace.registerRecipe(blastFurnace.new BlastFurnaceRecipe(new ItemStack[] { new ItemStack(VoidCraft.items.ironDust), new ItemStack(VoidCraft.items.coalDust) }, new ItemStack(VoidCraft.items.voidicSteel, 1), 500));

		infuser.registerRecipe(infuser.new InfuserRecipe(new ItemStack[] { new ItemStack(Blocks.FURNACE) }, new ItemStack(VoidCraft.blocks.voidMacerator), 1000));
		infuser.registerRecipe(infuser.new InfuserRecipe(new ItemStack[] { new ItemStack(VoidCraft.tools.archSword) }, new ItemStack(VoidCraft.tools.demonSword), 1000));
		infuser.registerRecipe(infuser.new InfuserRecipe(new ItemStack[] { new ItemStack(Blocks.BEACON) }, new ItemStack(VoidCraft.blocks.Heimdall), 1000));

		{
			ItemStack[] voidicImmunityInputStack = new ItemStack[] { new ItemStack(VoidCraft.items.etherealFruit), new ItemStack(VoidCraft.items.etherealFruit_redstone), new ItemStack(VoidCraft.items.etherealFruit_lapis), new ItemStack(VoidCraft.items.etherealFruit_gold), new ItemStack(VoidCraft.items.etherealFruit_emerald), new ItemStack(VoidCraft.items.etherealFruit_diamond) };
			ItemStack result = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), VoidCraft.potions.type_voidImmunity);
			alchemy.registerRecipe(alchemy.new AlchemyRecipe(null, voidicImmunityInputStack, result, 350));
			alchemy.registerRecipe(alchemy.new AlchemyRecipe(

					IVadeMecumCapability.Category.Flame,

					new ItemStack[] {

							new ItemStack(Items.NETHER_WART),

							new ItemStack(Items.BLAZE_POWDER),

							new ItemStack(Items.MAGMA_CREAM),

							new ItemStack(Items.FIRE_CHARGE),

							new ItemStack(VoidCraft.items.etherealFruit),

							new ItemStack(VoidCraft.items.emptyObsidianFlask)

					},

					new ItemStack(VoidCraft.items.obsidianFlaskFire, 1),

					350)

			);

			alchemy.registerRecipe(alchemy.new AlchemyRecipe(

					IVadeMecumCapability.Category.Freeze,

					new ItemStack[] {

							new ItemStack(Blocks.ICE),

							new ItemStack(Blocks.SNOW),

							new ItemStack(VoidCraft.items.ectoplasm),

							new ItemStack(VoidCraft.items.voidcrystal),

							new ItemStack(VoidCraft.items.etherealFruit),

							new ItemStack(VoidCraft.items.emptyObsidianFlask)

					},

					new ItemStack(VoidCraft.items.obsidianFlaskFreeze, 1),

					350)

			);

			alchemy.registerRecipe(alchemy.new AlchemyRecipe(

					IVadeMecumCapability.Category.Shock,

					new ItemStack[] {

							new ItemStack(Blocks.GLOWSTONE),

							new ItemStack(Blocks.END_STONE),

							new ItemStack(Blocks.END_ROD),

							new ItemStack(Items.FEATHER),

							new ItemStack(VoidCraft.items.etherealFruit),

							new ItemStack(VoidCraft.items.emptyObsidianFlask)

					},

					new ItemStack(VoidCraft.items.obsidianFlaskShock, 1),

					350)

			);

			alchemy.registerRecipe(alchemy.new AlchemyRecipe(

					IVadeMecumCapability.Category.AcidSpray,

					new ItemStack[] {

							new ItemStack(Items.FISH, 1, 3),

							new ItemStack(Blocks.SLIME_BLOCK),

							new ItemStack(Items.FERMENTED_SPIDER_EYE),

							new ItemStack(Items.POISONOUS_POTATO),

							new ItemStack(VoidCraft.items.etherealFruit),

							new ItemStack(VoidCraft.items.emptyObsidianFlask)

					},

					new ItemStack(VoidCraft.items.obsidianFlaskAcid, 1),

					350)

			);

			alchemy.registerRecipe(alchemy.new AlchemyRecipe(

					IVadeMecumCapability.Category.Implosion,

					new ItemStack[] {

							new ItemStack(VoidCraft.blocks.blockVoidcrystal),

							new ItemStack(VoidCraft.blocks.realityHole),

							new ItemStack(VoidCraft.items.voidCloth),

							new ItemStack(VoidCraft.items.astralEssence),

							new ItemStack(VoidCraft.items.etherealFruit),

							new ItemStack(VoidCraft.items.emptyObsidianFlask)

					},

					new ItemStack(VoidCraft.items.obsidianFlaskVoid, 1),

					350)

			);
		}
	}

	@Override
	public void postInit() {

	}

	@Override
	public ArrayList<ITamModel> getModelList() {
		return new ArrayList<ITamModel>();
	}

	@Override
	public String getModID() {
		return VoidCraft.modid;
	}

	@Override
	public void clientPreInit() {

	}

	@Override
	public void clientInit() {

	}

	@Override
	public void clientPostInit() {

	}

	public static ItemStack[] getOreDict(String[] input) {
		ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
		for (String ore : input) {
			for (ItemStack item : OreDictionary.getOres(ore)) {
				stacks.add(item);
			}
		}
		return stacks.toArray(new ItemStack[stacks.size()]);
	}

}
