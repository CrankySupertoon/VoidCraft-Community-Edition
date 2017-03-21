package Tamaized.Voidcraft.handlers;

import java.util.UUID;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.world.dim.TheVoid.TeleporterVoid;
import Tamaized.Voidcraft.world.dim.Xia.TeleporterXia;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.Teleporter;

public class PortalDataHandler {

	private final UUID id;
	public int type;
	public float tick;
	public boolean hasTeleported;
	public int lastDim;
	public boolean active = false;

	public PortalDataHandler(UUID uuid, int dim) {
		id = uuid;
		type = 0;
		tick = 0.0F;
		hasTeleported = false;
		lastDim = dim;
	}

	public UUID getID() {
		return id;
	}

	public Teleporter getTeleporter(EntityPlayerMP player) {
		if (player.dimension == VoidCraft.config.getDimensionIdVoid()) {
			return new TeleporterVoid(player.mcServer.worldServerForDimension(lastDim)); // From Void to ?
		} else if (player.dimension == VoidCraft.config.getDimensionIdXia()) {
			return new TeleporterXia(player.mcServer.worldServerForDimension(lastDim)); // From Xia to ?
		} else if (type == VoidCraft.config.getDimensionIdVoid()) {
			return new TeleporterVoid(player.mcServer.worldServerForDimension(VoidCraft.config.getDimensionIdVoid())); // From ? to Void
		} else if (type == VoidCraft.config.getDimensionIdXia()) {
			return new TeleporterXia(player.mcServer.worldServerForDimension(VoidCraft.config.getDimensionIdXia())); // From ? to Xia
		} else {
			return new TeleporterVoid(player.mcServer.worldServerForDimension(0)); // Shouldnt happen
		}
	}
}
