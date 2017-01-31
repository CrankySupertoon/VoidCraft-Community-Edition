package Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.spectrepick;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListSpectrePick implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.tools.spectrePickaxe).getDisplayName(), new ItemStack[] {
						new ItemStack(VoidCraft.items.ectoplasm),
						new ItemStack(VoidCraft.items.ectoplasm),
						new ItemStack(VoidCraft.items.ectoplasm),
						new ItemStack(VoidCraft.items.ectoplasm),
						new ItemStack(VoidCraft.tools.voidPickaxe),
						new ItemStack(VoidCraft.items.ectoplasm),
						new ItemStack(VoidCraft.items.ectoplasm),
						new ItemStack(VoidCraft.items.ectoplasm),
						new ItemStack(VoidCraft.items.ectoplasm) }, new ItemStack(VoidCraft.tools.spectrePickaxe))) };
	}

}
