package Tamaized.Voidcraft.vadeMecum.contents.progression.pages;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListTome implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Words of Power", "As you invoke more and more rituals, more entries will appear in your Vade Mecum. It is not clear why this happens. Though some entries describe a single word. A word which when used on a ready Vade Mecum will cast magical effects. At least, it seems magical. The only explanation is that the Vade Mecum is opening small rifts to specific sections in the void to unleash such powers. To ready your Vade Mecum for a Word you must first select a Word."),
				new VadeMecumPage("", "This can be done by clicking on the Known Words button on the main page. After you have a word ready to use, Shift Right Click in the world with your Vade Mecum in hand and it'll change in color. This means it's ready to invoke the spoken word. To actually use a word, simply Right Click. To deactivate the Vade Mecum, Shift Right Click again. Words of Power can only be used a limited amount of times but they can be recharged."),
				new VadeMecumPage("", "To recharge, head back to the Word selection menu and feed the Vade Mecum the specified items. Each item fed to the Vade Mecum will recharge a single point. Future rituals can increase the amount in which points are recharged from a single item.")
				};
	}

}
