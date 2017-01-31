package Tamaized.Voidcraft.handlers;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.TamModized.helper.PacketHelper;
import Tamaized.TamModized.helper.PacketHelper.PacketWrapper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.network.ServerPacketHandler;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayer;

public class VadeMecumPacketHandler {

	public static enum RequestType {
		NULL, CATEGORY_ADD, CATEGORY_REMOVE, CATEGORY_CLEAR, ACTIVE_SET, ACTIVE_CLEAR, PASSIVE_ADD, PASSIVE_REMOVE, PASSIVE_CLEAR
	}

	public static int getRequestID(RequestType type) {
		return type.ordinal();
	}

	public static RequestType getRequestFromID(int id) {
		return id > RequestType.values().length - 1 ? RequestType.NULL : RequestType.values()[id];
	}

	public static void ClientToServerRequest(RequestType type, int objectID) {
		try {
			PacketWrapper packet = PacketHelper.createPacket(VoidCraft.channel, VoidCraft.networkChannelName, ServerPacketHandler.getPacketTypeID(ServerPacketHandler.PacketType.VADEMECUM));
			DataOutputStream stream = packet.getStream();
			stream.writeInt(getRequestID(type));
			stream.writeInt(objectID);
			packet.sendPacketToServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void DecodeRequestServer(ByteBufInputStream bbis, EntityPlayer player) throws IOException {
		IVadeMecumCapability capability = player.getCapability(CapabilityList.VADEMECUM, null);
		switch (getRequestFromID(bbis.readInt())) {
			case CATEGORY_ADD:
				capability.addCategory(IVadeMecumCapability.getCategoryFromID(bbis.readInt()));
				break;
			case CATEGORY_REMOVE:
				capability.removeCategory(IVadeMecumCapability.getCategoryFromID(bbis.readInt()));
				break;
			case CATEGORY_CLEAR:
				capability.clearCategories();
				break;
			case ACTIVE_SET:
				IVadeMecumCapability.Category category = IVadeMecumCapability.getCategoryFromID(bbis.readInt());
				if (category != null && IVadeMecumCapability.isActivePower(category) && capability.hasCategory(category)) capability.setCurrentActive(category);
				break;
			case ACTIVE_CLEAR:
				capability.clearActivePower();
				break;
			case PASSIVE_ADD:
				break;
			case PASSIVE_REMOVE:
				break;
			case PASSIVE_CLEAR:
				break;
			default:
				break;
		}
	}

}
