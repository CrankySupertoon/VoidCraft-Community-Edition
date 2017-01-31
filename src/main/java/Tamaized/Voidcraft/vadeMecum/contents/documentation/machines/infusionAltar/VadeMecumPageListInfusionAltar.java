package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.infusionAltar;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListInfusionAltar implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.blocks.voidInfuser).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.voidInfuser"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.blocks.voidInfuserInert).getDisplayName(), new ItemStack[] {
						new ItemStack(VoidCraft.blocks.blockVoidbrick),
						new ItemStack(VoidCraft.items.voidCloth),
						new ItemStack(VoidCraft.blocks.blockVoidbrick),
						new ItemStack(VoidCraft.items.voidCloth),
						new ItemStack(Items.CAULDRON),
						new ItemStack(VoidCraft.items.voidCloth),
						new ItemStack(VoidCraft.blocks.blockVoidbrick),
						new ItemStack(VoidCraft.items.voidCloth),
						new ItemStack(VoidCraft.blocks.blockVoidbrick) }, new ItemStack(VoidCraft.blocks.voidInfuserInert, 1))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.blocks.voidInfuser).getDisplayName(), new ItemStack[] {
						new ItemStack(VoidCraft.blocks.voidInfuserInert),
						new ItemStack(VoidCraft.items.voidStar),
						null,
						null,
						null,
						null,
						null,
						null,
						null }, new ItemStack(VoidCraft.blocks.voidInfuser, 1))) };
	}

}
