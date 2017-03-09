package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.infusionAltar;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListInfusionAltar implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.blocks.voidInfuser).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.voidInfuser.pg1"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.voidInfuser.pg2"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.blocks.voidInfuserInert).getDisplayName(), new ItemStack(VoidCraft.blocks.voidInfuserInert, 1))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.blocks.voidInfuser).getDisplayName(), new ItemStack(VoidCraft.blocks.voidInfuser, 1))) };
	}

}
