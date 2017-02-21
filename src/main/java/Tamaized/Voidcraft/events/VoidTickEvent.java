package Tamaized.Voidcraft.events;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.handlers.ClientPortalDataHandler;
import Tamaized.Voidcraft.handlers.PortalDataHandler;
import Tamaized.Voidcraft.handlers.XiaFlightHandler;
import Tamaized.Voidcraft.world.dim.Xia.TeleporterXia;
import Tamaized.Voidcraft.world.dim.Xia.WorldProviderXia;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketPlayerAbilities;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class VoidTickEvent {

	public Map<UUID, PortalDataHandler> data = new HashMap<UUID, PortalDataHandler>();

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent e) {
		if (e.phase == e.phase.END) return;

		if (e.player.world.provider.getDimension() == VoidCraft.config.getDimensionIDvoid()) {
			if (e.player.getPosition().getY() >= 127) e.player.attackEntityFrom(DamageSource.OUT_OF_WORLD, 4.0F);
		} else if (e.player.world.provider.getDimension() != VoidCraft.config.getDimensionIDxia()) {
			if (e.player instanceof EntityPlayerMP && e.player.getPosition().getY() <= -256) {
				EntityPlayerMP player = (EntityPlayerMP) e.player;
				transferPlayerToDimension(player.mcServer, player, VoidCraft.config.getDimensionIDvoid(), new TeleportLoc(player.getPosition().add(0, 256 * 2, 0)));
			}
		}

		if (e.player instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP) e.player;
			if (player.dimension == VoidCraft.config.getDimensionIDxia()) {

				// Prevent players from flying in Xia DIM
				if (!player.capabilities.isCreativeMode && player.capabilities.isFlying && (XiaFlightHandler.shouldPlayerHaveFlight(player) ? !(player.world.provider instanceof WorldProviderXia && ((WorldProviderXia) player.world.provider).getXiaCastleHandler().canPlayersFly()) : true)) {
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
					player.capabilities.disableDamage = false;
					player.sendPlayerAbilities();
					player.sendMessage(new TextComponentTranslation(TextFormatting.DARK_GRAY + "You feel heavy, you can not sustain flight"));
				}

				// Teleport Player back to their spawn point if the Xia fight is done or they have over 0 Xia Defeats and they fall to the Void.
				WorldProvider provider = player.world.provider;
				if (provider instanceof WorldProviderXia && (((WorldProviderXia) provider).getXiaCastleHandler().hasFinished() || (player.hasCapability(CapabilityList.VOIDICINFUSION, null) && player.getCapability(CapabilityList.VOIDICINFUSION, null).getXiaDefeats() > 0)) && player.posY <= 0) {
					forcePlayerTeleportFromXia(player);
					player.addStat(VoidCraft.achievements.worldsEnd, 1);
				}

			}
		}

		if (e.player.world.isRemote || e.side == e.side.CLIENT) {
			BlockPos bPos = new BlockPos(MathHelper.floor(e.player.posX), MathHelper.floor(e.player.posY - 0.2D - (double) e.player.getYOffset()), MathHelper.floor(e.player.posZ));
			Block block = e.player.world.getBlockState(bPos).getBlock();
			if (ClientPortalDataHandler.type != VoidCraft.config.getDimensionIDvoid() && block == VoidCraft.blocks.blockPortalVoid) {
				if (!ClientPortalDataHandler.active) {
					ClientPortalDataHandler.type = VoidCraft.config.getDimensionIDvoid();
					ClientPortalDataHandler.active = true;
				}
			} else if (ClientPortalDataHandler.type != VoidCraft.config.getDimensionIDxia() && block == VoidCraft.blocks.blockPortalXia) {
				if (!ClientPortalDataHandler.active) {
					ClientPortalDataHandler.type = VoidCraft.config.getDimensionIDxia();
					ClientPortalDataHandler.active = true;
				}
			} else {
				if (ClientPortalDataHandler.active && !(ClientPortalDataHandler.type == VoidCraft.config.getDimensionIDvoid() && block == VoidCraft.blocks.blockPortalVoid) && !(ClientPortalDataHandler.type == VoidCraft.config.getDimensionIDxia() && block == VoidCraft.blocks.blockPortalXia)) {
					ClientPortalDataHandler.active = false;
					// ClientPortalDataHandler.type = 0;
				}
			}
			return;
		}

		if (data.get(e.player.getGameProfile().getId()) != null) {

			// Determine when to Teleport
			BlockPos bPos = new BlockPos(MathHelper.floor(e.player.posX), MathHelper.floor(e.player.posY - 0.2D - (double) e.player.getYOffset()), MathHelper.floor(e.player.posZ));
			Block block = e.player.world.getBlockState(bPos).getBlock();
			PortalDataHandler j = data.get(e.player.getGameProfile().getId());
			if (j.type != VoidCraft.config.getDimensionIDvoid() && block == VoidCraft.blocks.blockPortalVoid) {
				if (!j.active) {
					j.type = VoidCraft.config.getDimensionIDvoid();
					j.active = true;
				}
			} else if (j.type != VoidCraft.config.getDimensionIDxia() && block == VoidCraft.blocks.blockPortalXia) {
				if (!j.active) {
					j.type = VoidCraft.config.getDimensionIDxia();
					j.active = true;
				}
			} else {
				if (j.active && !(j.type == VoidCraft.config.getDimensionIDvoid() && block == VoidCraft.blocks.blockPortalVoid) && !(j.type == VoidCraft.config.getDimensionIDxia() && block == VoidCraft.blocks.blockPortalXia)) {
					j.active = false;
					j.type = 0;
				}
			}
			if (j.active) {
				if (j.tick < 0.8F) j.tick = j.tick + 0.012F;
				else j.tick = 0.8F;
			} else {
				if (j.tick > 0.0F) j.tick = j.tick - 0.030F;
				else j.tick = 0.0F;
			}

			// Teleport Player to Dimension when Ready
			if (!j.hasTeleported && j.tick >= 0.8F) {
				// System.out.println(e.player);
				if (e.player instanceof EntityPlayerMP) {
					teleport((EntityPlayerMP) e.player);
				} else if (e.player instanceof EntityOtherPlayerMP) {
					// Client Side do nothing
				} else {
					String err = "ISSUE DETECTED, REPORT THIS TO THE AUTHOR OF VOIDCRAFT; Data: " + j + " : " + e.player + "; " + e.player.getClass();
					VoidCraft.instance.logger.info(err);
					// System.out.println(err);
				}
				j.hasTeleported = true;
			}

			if (j.tick <= 0.0F) {
				j.hasTeleported = false;
			}
		} else {
			VoidCraft.instance.logger.info("Adding UUID: '" + e.player.getGameProfile().getId() + "' (" + e.player.getGameProfile().getName() + ") to Portal Overlay Handler");
			data.put(e.player.getGameProfile().getId(), new PortalDataHandler(e.player.getGameProfile().getId(), e.player.dimension == VoidCraft.config.getDimensionIDvoid() ? 0 : e.player.dimension == VoidCraft.config.getDimensionIDxia() ? 0 : e.player.dimension));
		}
	}

	public void forcePlayerTeleportFromXia(EntityPlayerMP player) {
		PortalDataHandler j = data.get(player.getGameProfile().getId());
		transferPlayerToDimension(player.mcServer, player, j.lastDim, new TeleportLoc(new TeleporterXia(player.mcServer.worldServerForDimension(j.lastDim))));
	}

	private void teleport(EntityPlayerMP player) {
		PortalDataHandler j = data.get(player.getGameProfile().getId());
		if (player.dimension != j.type && player.dimension != 1 && player.dimension != VoidCraft.config.getDimensionIDvoid() && player.dimension != VoidCraft.config.getDimensionIDxia()) { // Teleport into void/xia
			j.lastDim = player.dimension;
			transferPlayerToDimension(player.mcServer, player, j.type, new TeleportLoc(j.getTeleporter(player)));
		} else if (player.dimension == 1) { // From end
			j.lastDim = player.dimension;
			transferPlayerToDimension(player.mcServer, player, j.type, new TeleportLoc(j.getTeleporter(player)));
			transferPlayerToDimension(player.mcServer, player, j.type, new TeleportLoc(j.getTeleporter(player)));
		} else { // Teleport out of void/xia
			j.lastDim = j.lastDim == VoidCraft.config.getDimensionIDvoid() ? 0 : j.lastDim == VoidCraft.config.getDimensionIDxia() ? 0 : j.lastDim; // Ensure lastDim never equals Void or Xia IDs
			transferPlayerToDimension(player.mcServer, player, j.lastDim, new TeleportLoc(j.getTeleporter(player)));
		}
	}

	private void transferPlayerToDimension(MinecraftServer mcServer, EntityPlayerMP player, int dimId, TeleportLoc teleporter) { // Custom Made to handle teleporting to and from The End (DIM 1)
		int j = player.dimension;
		WorldServer worldserver = mcServer.worldServerForDimension(player.dimension);
		player.dimension = dimId;
		WorldServer worldserver1 = mcServer.worldServerForDimension(player.dimension);
		player.connection.sendPacket(new SPacketRespawn(player.dimension, worldserver1.getDifficulty(), worldserver1.getWorldInfo().getTerrainType(), player.interactionManager.getGameType())); // Forge: Use new dimensions information
		mcServer.getPlayerList().updatePermissionLevel(player);
		worldserver.removeEntityDangerously(player);
		player.isDead = false;
		transferEntityToWorld(player, j, worldserver, worldserver1, teleporter);
		mcServer.getPlayerList().preparePlayer(player, worldserver);
		player.connection.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
		player.interactionManager.setWorld(worldserver1);
		player.connection.sendPacket(new SPacketPlayerAbilities(player.capabilities));
		mcServer.getPlayerList().updateTimeAndWeatherForPlayer(player, worldserver1);
		mcServer.getPlayerList().syncPlayerInventory(player);
		Iterator iterator = player.getActivePotionEffects().iterator();
		while (iterator.hasNext()) {
			PotionEffect potioneffect = (PotionEffect) iterator.next();
			player.connection.sendPacket(new SPacketEntityEffect(player.getEntityId(), potioneffect));
		}
		FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, j, dimId);
	}

	private void transferEntityToWorld(Entity p_82448_1_, int p_82448_2_, WorldServer p_82448_3_, WorldServer p_82448_4_, TeleportLoc teleporter) { // Custom Made to handle teleporting to and from The End (DIM 1)
		WorldProvider pOld = p_82448_3_.provider;
		WorldProvider pNew = p_82448_4_.provider;
		double moveFactor = pOld.getMovementFactor() / pNew.getMovementFactor();
		double d0 = p_82448_1_.posX * moveFactor;
		double d1 = p_82448_1_.posZ * moveFactor;
		double d3 = p_82448_1_.posX;
		double d4 = p_82448_1_.posY;
		double d5 = p_82448_1_.posZ;
		float f = p_82448_1_.rotationYaw;
		p_82448_3_.theProfiler.startSection("moving");
		p_82448_3_.theProfiler.endSection();

		p_82448_3_.theProfiler.startSection("placing");
		d0 = (double) MathHelper.clamp((int) d0, -29999872, 29999872);
		d1 = (double) MathHelper.clamp((int) d1, -29999872, 29999872);

		if (p_82448_1_.isEntityAlive()) {
			p_82448_1_.setLocationAndAngles(d0, p_82448_1_.posY, d1, p_82448_1_.rotationYaw, p_82448_1_.rotationPitch);
			if (teleporter.teleporter != null) teleporter.teleporter.placeInPortal(p_82448_1_, f);
			else p_82448_1_.setPositionAndUpdate(teleporter.pos.getX(), teleporter.pos.getY(), teleporter.pos.getZ());
			p_82448_4_.spawnEntity(p_82448_1_);
			p_82448_4_.updateEntityWithOptionalForce(p_82448_1_, false);
		}
		p_82448_3_.theProfiler.endSection();
		p_82448_1_.setWorld(p_82448_4_);
	}

	private class TeleportLoc {

		public final Teleporter teleporter;
		public final BlockPos pos;

		public TeleportLoc(Teleporter tele) {
			pos = null;
			teleporter = tele;
		}

		public TeleportLoc(BlockPos p) {
			teleporter = null;
			pos = p;
		}

	}

}
