package Tamaized.Voidcraft.vadeMecum.contents.progression.pages;

import java.util.ArrayList;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageMultiBlock;
import Tamaized.Voidcraft.vadeMecum.progression.VadeMecumRitualHandler;
import Tamaized.Voidcraft.vadeMecum.progression.VadeMecumWordsOfPower;

public class VadeMecumPageListRitualList implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		ArrayList<IVadeMecumPage> pages = new ArrayList<IVadeMecumPage>();
		pages.add(new VadeMecumPage("voidcraft.VadeMecum.progression.title.ritualList", "voidcraft.VadeMecum.progression.desc.ritualList"));
		for (IVadeMecumCapability.Category category : VadeMecumRitualHandler.getAvailiableRituals(cap))
			pages.add(new VadeMecumPageMultiBlock(VadeMecumWordsOfPower.getCategoryData(category).getName(), VoidCraft.ritualList.getRitual(category)));
		return pages.toArray(new IVadeMecumPage[pages.size()]);
	}

}
