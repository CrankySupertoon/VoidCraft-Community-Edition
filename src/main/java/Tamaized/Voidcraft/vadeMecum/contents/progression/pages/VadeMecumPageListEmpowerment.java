package Tamaized.Voidcraft.vadeMecum.contents.progression.pages;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageListEmpowerment implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {

				new VadeMecumPage("Empowerment", "I've got a new task for you, it's pretty simple. Just hold a Void Star in your hand. Yup, that's all. This will give you more control over your infusion, you will deal increased damage based on the amount of infusion you have.")

		};
	}

}
