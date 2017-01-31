package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.moltensword;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListMoltenSword implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.tools.moltenSword).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.moltenSword"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						new ItemStack(VoidCraft.items.MoltenvoidChain),
						new ItemStack(VoidCraft.items.MoltenvoidChain),
						new ItemStack(VoidCraft.items.MoltenvoidChain),
						new ItemStack(VoidCraft.items.MoltenvoidChain),
						new ItemStack(VoidCraft.tools.chainSword),
						new ItemStack(VoidCraft.items.MoltenvoidChain),
						new ItemStack(VoidCraft.items.MoltenvoidChain),
						new ItemStack(VoidCraft.items.MoltenvoidChain),
						new ItemStack(VoidCraft.items.MoltenvoidChain) }, new ItemStack(VoidCraft.tools.moltenSword))) };
	}

}
