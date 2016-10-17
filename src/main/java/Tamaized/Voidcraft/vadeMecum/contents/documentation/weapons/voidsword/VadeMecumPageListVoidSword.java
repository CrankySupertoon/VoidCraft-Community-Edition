package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.voidsword;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidSword {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Void Sword", new ItemStack[] {
						null,
						new ItemStack(voidCraft.items.voidcrystal),
						null,
						new ItemStack(Blocks.OBSIDIAN),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(Blocks.OBSIDIAN),
						null,
						new ItemStack(Items.DIAMOND),
						null }, new ItemStack(voidCraft.tools.voidSword))) };
	}

}
