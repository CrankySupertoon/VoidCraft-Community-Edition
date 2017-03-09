package Tamaized.Voidcraft.vadeMecum.contents.documentation.voidicinfusion;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListVoidicInfusion implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(VoidCraft.modid+".VadeMecum.docs.title.voidicInfusion", VoidCraft.modid+".VadeMecum.docs.desc.voidicInfusion.pg1"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.voidicInfusion.pg2") };
	}

}
