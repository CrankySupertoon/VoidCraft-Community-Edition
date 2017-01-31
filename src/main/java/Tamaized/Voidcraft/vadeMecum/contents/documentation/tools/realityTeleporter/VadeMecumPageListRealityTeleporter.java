package Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.realityTeleporter;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListRealityTeleporter implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.items.realityTeleporter).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.realityTeleporter.pg1"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.realityTeleporter.pg2"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						new ItemStack(VoidCraft.items.burnBone),
						new ItemStack(VoidCraft.items.emeraldDust),
						new ItemStack(VoidCraft.blocks.voidicCharger),
						new ItemStack(VoidCraft.items.MoltenvoidChain),
						new ItemStack(VoidCraft.blocks.realityHole),
						new ItemStack(VoidCraft.items.MoltenvoidChain),
						new ItemStack(VoidCraft.items.burnBone),
						new ItemStack(VoidCraft.items.MoltenvoidChain),
						new ItemStack(VoidCraft.items.burnBone) }, new ItemStack(VoidCraft.items.realityTeleporter)))};
	}

}
