package Tamaized.Voidcraft.vadeMecum.contents.progression.pages;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListRitualBlock implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Voidic Ritual Blocks", "These special blocks are used to invoke rituals for the Void Vade Mecum. The purpose of invoking a ritual is to gain new knowledge. To activate such a ritual simply construct one shown then right click the Ritual Block with the Vade Mecum in hand. For your first ritual, just right click a lone placed ritual block. This will open up new knowledge within the Vade Mecum if not done so already.") };
	}

}
