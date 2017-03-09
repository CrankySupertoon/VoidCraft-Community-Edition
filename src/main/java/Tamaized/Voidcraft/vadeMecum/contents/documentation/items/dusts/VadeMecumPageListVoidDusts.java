package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.dusts;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingMacerator;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidDusts implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(VoidCraft.modid+".VadeMecum.docs.title.voidDusts", VoidCraft.modid+".VadeMecum.docs.desc.voidDusts"),
				new VadeMecumPageCrafting(new VadeMecumCraftingMacerator(VoidCraft.modid+".VadeMecum.recipe.macerator", new ItemStack(VoidCraft.items.ironDust, 4))),
				new VadeMecumPageCrafting(new VadeMecumCraftingMacerator(VoidCraft.modid+".VadeMecum.recipe.macerator", new ItemStack(VoidCraft.items.diamondDust))),
				new VadeMecumPageCrafting(new VadeMecumCraftingMacerator(VoidCraft.modid+".VadeMecum.recipe.macerator", new ItemStack(VoidCraft.items.coalDust, 8))),
				new VadeMecumPageCrafting(new VadeMecumCraftingMacerator(VoidCraft.modid+".VadeMecum.recipe.macerator", new ItemStack(VoidCraft.items.coalDust, 4))) };
	}

}
