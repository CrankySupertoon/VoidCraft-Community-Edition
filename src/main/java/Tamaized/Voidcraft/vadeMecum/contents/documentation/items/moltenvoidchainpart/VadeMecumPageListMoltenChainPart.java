package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.moltenvoidchainpart;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingFurnace;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListMoltenChainPart implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.items.MoltenvoidChainPart).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.MoltenvoidChainPart"),
				new VadeMecumPageCrafting(new VadeMecumCraftingFurnace(VoidCraft.modid+".VadeMecum.recipe.furnace", new ItemStack(VoidCraft.items.MoltenvoidChainPart)))};
	}

}
