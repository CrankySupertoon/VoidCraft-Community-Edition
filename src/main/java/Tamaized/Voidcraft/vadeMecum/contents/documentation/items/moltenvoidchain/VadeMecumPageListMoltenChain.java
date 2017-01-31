package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.moltenvoidchain;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListMoltenChain implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.items.MoltenvoidChain).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.MoltenvoidChain"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						new ItemStack(VoidCraft.items.burnBone),
						new ItemStack(VoidCraft.items.MoltenvoidChainPart),
						new ItemStack(VoidCraft.items.burnBone),
						new ItemStack(VoidCraft.items.MoltenvoidChainPart),
						new ItemStack(VoidCraft.items.burnBone),
						new ItemStack(VoidCraft.items.MoltenvoidChainPart),
						new ItemStack(VoidCraft.items.burnBone),
						new ItemStack(VoidCraft.items.MoltenvoidChainPart),
						new ItemStack(VoidCraft.items.burnBone) }, new ItemStack(VoidCraft.items.MoltenvoidChain)))};
	}

}
