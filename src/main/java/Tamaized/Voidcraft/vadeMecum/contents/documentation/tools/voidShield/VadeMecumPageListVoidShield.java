package Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidShield;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidShield implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.items.voidCrystalShield).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.voidShield"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						new ItemStack(VoidCraft.items.voidcrystal),
						new ItemStack(VoidCraft.items.voidcrystal),
						new ItemStack(VoidCraft.items.voidcrystal),
						new ItemStack(VoidCraft.items.voidcrystal),
						new ItemStack(VoidCraft.blocks.blockVoidcrystal),
						new ItemStack(VoidCraft.items.voidcrystal),
						ItemStack.EMPTY,
						new ItemStack(VoidCraft.items.voidcrystal),
						ItemStack.EMPTY }, new ItemStack(VoidCraft.items.voidCrystalShield))) };
	}

}
