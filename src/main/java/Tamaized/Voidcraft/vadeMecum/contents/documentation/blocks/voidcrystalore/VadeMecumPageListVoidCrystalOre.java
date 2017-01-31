package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidcrystalore;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidCrystalOre implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.blocks.oreVoidcrystal).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.voidCrystalOre"), };
	}

}
