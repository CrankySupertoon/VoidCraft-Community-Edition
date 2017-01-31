package Tamaized.Voidcraft.events;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockBreakPlaceEvent {

	@SubscribeEvent
	public void BreakBlockEvent(BlockEvent.BreakEvent e) {
		EntityPlayer player = e.getPlayer();
		World world = player.world;
		if (world.provider.getDimension() == VoidCraft.config.getDimensionIDxia() && !player.capabilities.isCreativeMode && (e.getState().getBlock() != Blocks.STANDING_SIGN && e.getState().getBlock() != Blocks.WALL_SIGN)) {
			e.setCanceled(true);
			if(player instanceof EntityPlayerMP) ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
		}
	}

	@SubscribeEvent
	public void BreakBlockEvent(BlockEvent.PlaceEvent e) {
		EntityPlayer player = e.getPlayer();
		World world = player.world;
		if (world.provider.getDimension() == VoidCraft.config.getDimensionIDxia() && !player.capabilities.isCreativeMode && (e.getState().getBlock() != Blocks.STANDING_SIGN && e.getState().getBlock() != Blocks.WALL_SIGN)) {
			e.setCanceled(true);
			if(player instanceof EntityPlayerMP) ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
		}
	}

}
