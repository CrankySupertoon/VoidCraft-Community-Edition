package Tamaized.Voidcraft.vadeMecum.contents.documentation.thevoid;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListVoid implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(VoidCraft.modid+".VadeMecum.docs.title.theVoid", VoidCraft.modid+".VadeMecum.docs.desc.theVoid.pg1"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.theVoid.pg2"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.theVoid.pg3"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.theVoid.pg4"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.theVoid.pg5") };
	}

}
