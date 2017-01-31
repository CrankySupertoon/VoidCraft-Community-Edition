package Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidicdrill;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidicDrill implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.items.voidicDrill).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.voidicDrill"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						new ItemStack(VoidCraft.blocks.blockVoidcrystal),
						new ItemStack(VoidCraft.blocks.realityHole),
						new ItemStack(VoidCraft.blocks.blockVoidcrystal),
						new ItemStack(VoidCraft.items.voidCloth),
						new ItemStack(VoidCraft.blocks.voidicCharger),
						new ItemStack(VoidCraft.items.voidCloth),
						new ItemStack(VoidCraft.items.ectoplasm),
						new ItemStack(VoidCraft.items.voidStar),
						new ItemStack(VoidCraft.items.ectoplasm) }, new ItemStack(VoidCraft.items.voidicDrill)))};
	}

}
