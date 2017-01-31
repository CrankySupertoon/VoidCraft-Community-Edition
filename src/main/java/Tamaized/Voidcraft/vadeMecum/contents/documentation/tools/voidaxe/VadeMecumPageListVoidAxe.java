package Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidaxe;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidAxe implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.tools.voidAxe).getDisplayName(), new ItemStack[] {
						null,
						new ItemStack(VoidCraft.items.voidcrystal),
						new ItemStack(VoidCraft.items.voidcrystal),
						null,
						new ItemStack(Blocks.OBSIDIAN),
						new ItemStack(VoidCraft.items.voidcrystal),
						null,
						new ItemStack(Items.DIAMOND),
						null }, new ItemStack(VoidCraft.tools.voidAxe)))};
	}

}
