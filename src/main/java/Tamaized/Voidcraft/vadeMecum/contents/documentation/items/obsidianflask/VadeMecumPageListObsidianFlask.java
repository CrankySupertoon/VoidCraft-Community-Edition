package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.obsidianflask;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListObsidianFlask implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.items.emptyObsidianFlask).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.emptyObsidianFlask.pg1"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.emptyObsidianFlask.pg2"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						ItemStack.EMPTY,
						ItemStack.EMPTY,
						ItemStack.EMPTY,
						new ItemStack(Blocks.OBSIDIAN),
						new ItemStack(Blocks.GLASS),
						new ItemStack(Blocks.OBSIDIAN),
						ItemStack.EMPTY,
						new ItemStack(Blocks.OBSIDIAN),
						ItemStack.EMPTY }, new ItemStack(VoidCraft.items.emptyObsidianFlask, 4))) };
	}

}
