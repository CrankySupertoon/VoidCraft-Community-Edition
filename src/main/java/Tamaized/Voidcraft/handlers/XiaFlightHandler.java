package Tamaized.Voidcraft.handlers;

import java.util.ArrayList;
import java.util.List;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class XiaFlightHandler { // Thanks to Vazkii's Botania Mod Source for this part

	private List<String> playersWithFlight = new ArrayList();

	@SubscribeEvent
	public void updatePlayerFlyStatus(LivingUpdateEvent event) {
		if (event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();

			if (playersWithFlight.contains(playerStr(player))) {
				if (shouldPlayerHaveFlight(player)) { // Keeps the Flight on
					player.capabilities.allowFlying = true;
				} else { // Player should not have Flight
					if (!player.capabilities.isCreativeMode) {
						player.capabilities.allowFlying = false;
						player.capabilities.isFlying = false;
						player.capabilities.disableDamage = false;
						// player.cameraPitch = 0;
						// player.rotationPitch = 0;
					}
					playersWithFlight.remove(playerStr(player));
				}
			} else if (shouldPlayerHaveFlight(player)) { // Initial Flight Turns on here (Called once)
				playersWithFlight.add(playerStr(player));
				player.capabilities.allowFlying = true;
			}
		}
	}

	@SubscribeEvent
	public void playerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
		String username = event.player.getGameProfile().getName();
		playersWithFlight.remove(username + ":false");
		playersWithFlight.remove(username + ":true");
	}

	public static String playerStr(EntityPlayer player) {
		return player.getGameProfile().getName() + ":" + player.world.isRemote;
	}

	public static boolean shouldPlayerHaveFlight(EntityPlayer player) {
		ItemStack helm = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		ItemStack leg = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
		ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
		if (!helm.isEmpty() && helm.getItem() == VoidCraft.armors.xiaHelmet) return true;
		if (!chest.isEmpty() && chest.getItem() == VoidCraft.armors.xiaChest) return true;
		if (!leg.isEmpty() && leg.getItem() == VoidCraft.armors.xiaLegs) return true;
		if (!boots.isEmpty() && boots.getItem() == VoidCraft.armors.xiaBoots) return true;
		return false;
	}

}
