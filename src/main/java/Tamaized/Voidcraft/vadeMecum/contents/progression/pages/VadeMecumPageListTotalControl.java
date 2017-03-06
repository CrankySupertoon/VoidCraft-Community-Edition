package Tamaized.Voidcraft.vadeMecum.contents.progression.pages;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageListTotalControl implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {

				new VadeMecumPage("Total Control", "Another task for you, this task will do quite a bit for you. You will decrease your explosion chance by another 25 percent, meaning you will no longer explode, and you will gain a new ability, Voidic Flight."),

				new VadeMecumPage("", "This ability will allow you to gain flight at 25 percent infusion rather than 75 percent."),

				new VadeMecumPage("", "Your task may be a bit more challenging, you must reach up to the top of the Void to the Void Cities, get up high enough to where the Airships dock.")

		};
	}

}
