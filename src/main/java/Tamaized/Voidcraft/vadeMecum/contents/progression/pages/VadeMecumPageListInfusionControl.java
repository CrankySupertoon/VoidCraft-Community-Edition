package Tamaized.Voidcraft.vadeMecum.contents.progression.pages;

import java.util.ArrayList;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListInfusionControl implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		ArrayList<IVadeMecumPage> pages = new ArrayList<IVadeMecumPage>();
		pages.add(new VadeMecumPage("voidcraft.VadeMecum.progression.title.infusionControl", "voidcraft.VadeMecum.progression.desc.infusionControl.main"));
		for(IVadeMecumCapability.Passive passive : IVadeMecumCapability.Passive.values()){
			if(cap.canHavePassive(passive)){
				pages.add(new VadeMecumPage(IVadeMecumCapability.getPassiveName(passive), "voidcraft.VadeMecum.passive.".concat(passive.name()).concat(".desc")));
			}
		}
		return pages.toArray(new IVadeMecumPage[pages.size()]);
	}

}
