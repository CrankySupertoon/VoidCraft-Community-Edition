package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.heimdall;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingInfuser;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListHeimdall implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.blocks.Heimdall).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.Heimdall.pg1"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.Heimdall.pg2"),
				new VadeMecumPageCrafting(new VadeMecumCraftingInfuser(VoidCraft.modid+".VadeMecum.recipe.infusion", new ItemStack(VoidCraft.blocks.Heimdall, 1))) };
	}

}
