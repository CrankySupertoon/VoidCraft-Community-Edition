package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.macerator;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingInfuser;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListMacerator implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.blocks.voidMacerator).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.voidMacerator"),
				new VadeMecumPageCrafting(new VadeMecumCraftingInfuser(VoidCraft.modid+".VadeMecum.recipe.infusion", new ItemStack(VoidCraft.blocks.voidMacerator, 1))) };
	}

}
