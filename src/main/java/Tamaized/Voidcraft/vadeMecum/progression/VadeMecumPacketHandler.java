package Tamaized.Voidcraft.vadeMecum.progression;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.TamModized.helper.PacketHelper;
import Tamaized.TamModized.helper.PacketHelper.PacketWrapper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import Tamaized.Voidcraft.network.ServerPacketHandler;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

public class VadeMecumPacketHandler {

	public static enum RequestType {
		NULL, CATEGORY_ADD, CATEGORY_REMOVE, CATEGORY_CLEAR, ACTIVE_SET, ACTIVE_CLEAR, PASSIVE, SPELLS_PAGE
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
			case SPELLS_PAGE:
				capability.setPage(bbis.readInt());
				PacketWrapper packet = PacketHelper.createPacket(VoidCraft.channel, VoidCraft.networkChannelName, ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.VADEMECUM_UPDATE));
				DataOutputStream stream = packet.getStream();
				capability.encodePacket(stream);
				packet.sendPacket((EntityPlayerMP) player);
				FMLNetworkHandler.openGui(player, VoidCraft.instance, GuiHandler.getTypeID(GuiHandler.Type.VadeMecumSpells), player.world, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
				break;
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
			case PASSIVE:
				IVadeMecumCapability.Passive passive = IVadeMecumCapability.getPassiveFromID(bbis.readInt());
				if (capability.hasPassive(passive)) capability.removePassive(passive);
				else if (capability.canHavePassive(passive)) capability.addPassive(passive);
				break;
			default:
				break;
		}
	}

}
