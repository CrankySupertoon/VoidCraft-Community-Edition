package Tamaized.Voidcraft.vadeMecum.contents.documentation.xia;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import net.minecraft.client.resources.I18n;

public class VadeMecumPageListXia implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] { new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.xia", VoidCraft.modid + ".VadeMecum.docs.desc.xia.pg1") };
	}

}
