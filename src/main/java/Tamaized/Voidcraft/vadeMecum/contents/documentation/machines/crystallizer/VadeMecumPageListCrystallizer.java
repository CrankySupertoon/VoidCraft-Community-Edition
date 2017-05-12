package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.crystallizer;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListCrystallizer implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.blocks.voidicCrystallizer).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.voidicCrystallizer.pg1"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid+".VadeMecum.recipe.normal", new ItemStack(VoidCraft.blocks.voidicCrystallizer, 1))) };
	}

}
