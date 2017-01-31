package Tamaized.Voidcraft.handlers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;
import Tamaized.Voidcraft.VoidCraft;

public class CraftingHandler {
	
	@SubscribeEvent
	public void SomethingCrafted(ItemCraftedEvent event){
		if (event.crafting.getItem() == VoidCraft.tools.voidSword) {
			event.player.addStat(VoidCraft.achievements.betterThanDiamond, 1);
		}
		else if(event.crafting.getItem() == VoidCraft.tools.angelicSword){
			event.player.addStat(VoidCraft.achievements.angelicPower, 1);
		}
		else if(event.crafting.getItem() == VoidCraft.tools.chainSword){
			event.player.addStat(VoidCraft.achievements.theChains, 1);
		}
		else if(event.crafting.getItem() == Item.getItemFromBlock((VoidCraft.blocks.voidInfuser))){
			event.player.addStat(VoidCraft.achievements.infuser, 1);
		}
		else if(event.crafting.getItem() == VoidCraft.tools.moltenSword){
			event.player.addStat(VoidCraft.achievements.thirdDegreeBurns, 1);
		}
		else if(event.crafting.getItem() == VoidCraft.tools.archSword){
			event.player.addStat(VoidCraft.achievements.Legendary, 1);
		}
		else if(event.crafting.getItem() == VoidCraft.items.ChainedSkull){
			event.player.addStat(VoidCraft.achievements.demonsCall, 1);
		}
		else if(event.crafting.getItem() == Item.getItemFromBlock(VoidCraft.blocks.voidicGen)){
			event.player.addStat(VoidCraft.achievements.generator, 1);
		}
		else if(event.crafting.getItem() == Item.getItemFromBlock(VoidCraft.blocks.voidicCharger)){
			event.player.addStat(VoidCraft.achievements.charger, 1);
		}
		else if(event.crafting.getItem() == VoidCraft.items.voidicSuppressor){
			event.player.addStat(VoidCraft.achievements.suppressor, 1);
		}
	}
	
	@SubscribeEvent
	public void MachineCrafted(ItemSmeltedEvent event){
		if (event.smelting.getItem() == Item.getItemFromBlock((VoidCraft.blocks.voidMacerator))) {
			event.player.addStat(VoidCraft.achievements.macerator, 1);
		}
		else if(event.smelting.getItem() == VoidCraft.tools.demonSword){
			event.player.addStat(VoidCraft.achievements.demonicPower, 1);
		}
		else if(event.smelting.getItem() == Item.getItemFromBlock((VoidCraft.blocks.Heimdall))){
			event.player.addStat(VoidCraft.achievements.heimdall, 1);
		}
	}
	
}