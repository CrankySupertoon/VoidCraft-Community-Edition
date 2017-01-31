package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.musicbox;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListMusicBox implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.blocks.voidBox).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.voidBox"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						new ItemStack(VoidCraft.items.voidCloth),
						new ItemStack(VoidCraft.items.voidCloth),
						new ItemStack(VoidCraft.items.voidCloth),
						new ItemStack(VoidCraft.items.voidCloth),
						new ItemStack(Blocks.JUKEBOX),
						new ItemStack(VoidCraft.items.voidCloth),
						new ItemStack(VoidCraft.items.voidCloth),
						new ItemStack(VoidCraft.items.voidStar),
						new ItemStack(VoidCraft.items.voidCloth) }, new ItemStack(VoidCraft.blocks.voidBox, 1))) };
	}

}
