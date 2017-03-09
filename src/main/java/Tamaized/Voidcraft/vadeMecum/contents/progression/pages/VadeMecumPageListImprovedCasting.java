package Tamaized.Voidcraft.vadeMecum.contents.progression.pages;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListImprovedCasting implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {

				new VadeMecumPage("voidcraft.VadeMecum.progression.title.improvedCasting", "voidcraft.VadeMecum.progression.desc.improvedCasting.pg1"),

				new VadeMecumPage("", "voidcraft.VadeMecum.progression.desc.improvedCasting.pg2") };
	}

}
