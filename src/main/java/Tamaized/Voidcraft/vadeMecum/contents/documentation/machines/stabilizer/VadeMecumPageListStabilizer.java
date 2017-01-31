package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.stabilizer;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListStabilizer implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.blocks.realityStabilizer).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.realityStabilizer"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						new ItemStack(VoidCraft.blocks.voidInfuserInert),
						new ItemStack(VoidCraft.items.diamondDust),
						new ItemStack(VoidCraft.blocks.voidInfuserInert),
						new ItemStack(VoidCraft.items.diamondDust),
						new ItemStack(VoidCraft.items.voidStar),
						new ItemStack(VoidCraft.items.diamondDust),
						new ItemStack(VoidCraft.blocks.voidInfuserInert),
						new ItemStack(VoidCraft.blocks.voidicCharger),
						new ItemStack(VoidCraft.blocks.voidInfuserInert) }, new ItemStack(VoidCraft.blocks.realityStabilizer, 1))) };
	}

}
