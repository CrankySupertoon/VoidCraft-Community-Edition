package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.macerator;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingInfuser;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListMacerator implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.blocks.voidMacerator).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.voidMacerator"),
				new VadeMecumPageCrafting(new VadeMecumCraftingInfuser(voidCraft.modid+".VadeMecum.recipe.infusion", new ItemStack(Blocks.FURNACE), new ItemStack(voidCraft.blocks.voidMacerator, 1))) };
	}

}
