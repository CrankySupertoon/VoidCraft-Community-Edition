package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidicsteel;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingBlastFurnace;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidicSteel implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.items.voidicSteel).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.voidicsteel"),
				new VadeMecumPageCrafting(new VadeMecumCraftingBlastFurnace(VoidCraft.modid+".VadeMecum.recipe.blastfurnace", new ItemStack(VoidCraft.items.voidicSteel)))};
	}

}
