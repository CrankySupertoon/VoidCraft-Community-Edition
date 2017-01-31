package Tamaized.Voidcraft.vadeMecum.contents.documentation.fruit;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingAlchemy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;

public class VadeMecumPageListFruit implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(VoidCraft.modid+".VadeMecum.docs.title.fruit", VoidCraft.modid+".VadeMecum.docs.desc.fruit.pg1"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.fruit.pg2"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.fruit.pg3"),
				new VadeMecumPage(VoidCraft.modid+".VadeMecum.docs.title.plenum", VoidCraft.modid+".VadeMecum.docs.desc.fruit.pg4"),
				new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid+".VadeMecum.recipe.alchemy", new ItemStack[] {
						new ItemStack(VoidCraft.items.etherealFruit),
						new ItemStack(VoidCraft.items.etherealFruit_redstone),
						new ItemStack(VoidCraft.items.etherealFruit_lapis),
						new ItemStack(VoidCraft.items.etherealFruit_gold),
						new ItemStack(VoidCraft.items.etherealFruit_emerald),
						new ItemStack(VoidCraft.items.etherealFruit_diamond) }, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), VoidCraft.potions.type_voidImmunity))) };
	}

}
