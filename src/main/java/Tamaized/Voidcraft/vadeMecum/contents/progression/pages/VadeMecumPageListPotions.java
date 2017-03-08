package Tamaized.Voidcraft.vadeMecum.contents.progression.pages;

import java.util.ArrayList;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingAlchemy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListPotions implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		ArrayList<IVadeMecumPage> pages = new ArrayList<IVadeMecumPage>();
		pages.add(new VadeMecumPage("Throwable Potions", "With some of your knowledge on these Voidic Spells you can create potions that cause some interesting effects upon impacting against the ground. To craft these potions be sure to Shift Right click the Alchemical device with your Vade Mecum first. These are handy to share with those who cannot cast such spells unlike yourself. "));
		if (cap.hasCategory(IVadeMecumCapability.Category.Flame)) pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskFire))));
		if (cap.hasCategory(IVadeMecumCapability.Category.Freeze)) pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskFreeze))));
		if (cap.hasCategory(IVadeMecumCapability.Category.Shock)) pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskShock))));
		if (cap.hasCategory(IVadeMecumCapability.Category.AcidSpray)) pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskAcid))));
		if (cap.hasCategory(IVadeMecumCapability.Category.Implosion)) pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskVoid))));
		return pages.toArray(new IVadeMecumPage[pages.size()]);
	}

}
