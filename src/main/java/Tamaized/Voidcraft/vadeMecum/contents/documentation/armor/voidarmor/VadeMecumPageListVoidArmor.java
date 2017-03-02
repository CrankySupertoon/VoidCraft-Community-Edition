package Tamaized.Voidcraft.vadeMecum.contents.documentation.armor.voidarmor;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidArmor implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.armors.voidHelmet).getDisplayName(), new ItemStack(VoidCraft.armors.voidHelmet))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.armors.voidChest).getDisplayName(), new ItemStack(VoidCraft.armors.voidChest))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.armors.voidLegs).getDisplayName(), new ItemStack(VoidCraft.armors.voidLegs))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.armors.voidBoots).getDisplayName(), new ItemStack(VoidCraft.armors.voidBoots)))
				};
	}

}
