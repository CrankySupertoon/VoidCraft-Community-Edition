package tamaized.voidcraft.common.vademecum.contents.documentation.machines.infusionAltar;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumCraftingNormal;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;
import tamaized.voidcraft.common.vademecum.VadeMecumPageCrafting;
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