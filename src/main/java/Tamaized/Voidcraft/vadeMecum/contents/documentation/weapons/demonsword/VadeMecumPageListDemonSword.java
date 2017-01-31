package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.demonsword;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingInfuser;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListDemonSword implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.tools.demonSword).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.demonSword"),
				new VadeMecumPageCrafting(new VadeMecumCraftingInfuser(VoidCraft.modid+".VadeMecum.recipe.infusion", new ItemStack(VoidCraft.tools.archSword), new ItemStack(VoidCraft.tools.demonSword))) };
	}

}
