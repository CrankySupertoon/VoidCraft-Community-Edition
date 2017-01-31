package Tamaized.Voidcraft.vadeMecum.contents.progression.ritualList;

import java.util.ArrayList;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.handlers.VadeMecumRitualHandler;
import Tamaized.Voidcraft.handlers.VadeMecumWordsOfPower;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageMultiBlock;

public class VadeMecumPageListRitualList implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		ArrayList<IVadeMecumPage> pages = new ArrayList<IVadeMecumPage>();
		pages.add(new VadeMecumPage("Voidic Ritual List", "This page will display the available rituals that can be invoked to advance your Vade Mecum Knowledge"));
		for (IVadeMecumCapability.Category category : VadeMecumRitualHandler.getAvailiableRituals(cap))
			pages.add(new VadeMecumPageMultiBlock(VadeMecumWordsOfPower.getCategoryData(category).getName(), VoidCraft.ritualList.getRitual(category)));
		return pages.toArray(new IVadeMecumPage[pages.size()]);
	}

}
