package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.softbedrock;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListSoftBedrock implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.blocks.blockFakeBedrock).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.blockFakeBedrock.pg1"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.blockFakeBedrock.pg2"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.blocks.blockFakeBedrockStairs).getDisplayName(), new ItemStack[] {
						new ItemStack(VoidCraft.blocks.blockFakeBedrock),
						null,
						null,
						new ItemStack(VoidCraft.blocks.blockFakeBedrock),
						new ItemStack(VoidCraft.blocks.blockFakeBedrock),
						null,
						new ItemStack(VoidCraft.blocks.blockFakeBedrock),
						new ItemStack(VoidCraft.blocks.blockFakeBedrock),
						new ItemStack(VoidCraft.blocks.blockFakeBedrock) }, new ItemStack(VoidCraft.blocks.blockFakeBedrockStairs, 6))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.blocks.blockFakeBedrockFence).getDisplayName(), new ItemStack[] {
						null,
						null,
						null,
						new ItemStack(VoidCraft.blocks.blockFakeBedrock),
						new ItemStack(VoidCraft.blocks.blockFakeBedrock),
						new ItemStack(VoidCraft.blocks.blockFakeBedrock),
						new ItemStack(VoidCraft.blocks.blockFakeBedrock),
						new ItemStack(VoidCraft.blocks.blockFakeBedrock),
						new ItemStack(VoidCraft.blocks.blockFakeBedrock) }, new ItemStack(VoidCraft.blocks.blockFakeBedrockFence, 6))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.blocks.blockFakeBedrockHalfSlab).getDisplayName(), new ItemStack[] {
						null,
						null,
						null,
						new ItemStack(VoidCraft.blocks.blockFakeBedrock),
						new ItemStack(VoidCraft.blocks.blockFakeBedrock),
						new ItemStack(VoidCraft.blocks.blockFakeBedrock),
						null,
						null,
						null }, new ItemStack(VoidCraft.blocks.blockFakeBedrockHalfSlab, 6))) };
	}

}
