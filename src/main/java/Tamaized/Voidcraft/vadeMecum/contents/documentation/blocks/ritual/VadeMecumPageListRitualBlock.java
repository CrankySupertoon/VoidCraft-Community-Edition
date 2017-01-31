package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.ritual;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListRitualBlock implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.blocks.ritualBlock).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.ritualBlock"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("recipe.normal", new ItemStack[] {
						new ItemStack(Blocks.STONEBRICK),
						new ItemStack(Blocks.STONEBRICK),
						new ItemStack(Blocks.STONEBRICK),
						new ItemStack(Blocks.STONEBRICK),
						new ItemStack(VoidCraft.items.voidcrystal),
						new ItemStack(Blocks.STONEBRICK),
						new ItemStack(Blocks.STONEBRICK),
						new ItemStack(Blocks.STONEBRICK),
						new ItemStack(Blocks.STONEBRICK) }, new ItemStack(VoidCraft.blocks.ritualBlock, 8)))
				};
	}

}
