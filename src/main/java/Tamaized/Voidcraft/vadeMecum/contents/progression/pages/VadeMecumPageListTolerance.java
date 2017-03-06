package Tamaized.Voidcraft.vadeMecum.contents.progression.pages;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageListTolerance implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {

				new VadeMecumPage("Tolerance", "I've given you a new Word of Power, Invoke Infusion. Upon using this Word you will gain some infusion. For your next task, be outside of the Void, with Voidic Anchor active, use the new Word multiple times to reach maximum Voidic Infusion. Doing so will give you a new ability."),

				new VadeMecumPage("", "This ability when active will double your maximum amount of Voidic Infusion you can handle. This effectively allows you to hold onto this Infusion for twice as long or allows you to live through the Void without protection to Infusion twice as long.")

		};
	}

}
