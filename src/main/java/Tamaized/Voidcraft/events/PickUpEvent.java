package Tamaized.Voidcraft.events;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import Tamaized.Voidcraft.VoidCraft;

public class PickUpEvent {
	
	@SubscribeEvent
	public void SomethingPickedup(EntityItemPickupEvent event){
		if(event.getItem().getEntityItem().isItemEqual(new ItemStack(VoidCraft.items.voidcrystal))){
			event.getEntityPlayer().addStat(VoidCraft.achievements.voidCrystal, 1);
		}
		else if(event.getItem().getEntityItem().isItemEqual(new ItemStack(VoidCraft.items.voidStar))){
			event.getEntityPlayer().addStat(VoidCraft.achievements.artifact, 1);
		}
	}

}
