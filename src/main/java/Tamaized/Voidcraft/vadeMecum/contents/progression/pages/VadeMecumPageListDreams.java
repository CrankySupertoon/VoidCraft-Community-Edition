package Tamaized.Voidcraft.vadeMecum.contents.progression.pages;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageListDreams implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {

				new VadeMecumPage("Dreams", "This will be the last Task I give you. The benefit of this task is rather large. Instead of your health decreasing with Voidic Infusion, it will increase. Now what is the task? Defeat Xia."),

				new VadeMecumPage("", "Destroy his current body, he is in a weak state right now. I must make my own preparations, this is where we part ways, mortal.")

		};
	}

}
