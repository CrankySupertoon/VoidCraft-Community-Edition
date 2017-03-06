package Tamaized.Voidcraft.vadeMecum.contents.progression.pages;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageListVoidicControl implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {

				new VadeMecumPage("Voidic Control", "You may know that Voidic Infusion inflicts some rather nasty effects upon you, however you may also know that some of these effects can be beneficial. There are ways to control your infusion effects in a way to prove more good than bad."),

				new VadeMecumPage("", "Enter the Void and allow yourself to be consumed by the Voidic Infusion, I will anchor your body to the material plane to prevent death long enough for you to gain new power.") };
	}

}
