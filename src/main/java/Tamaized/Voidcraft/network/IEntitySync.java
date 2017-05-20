package Tamaized.Voidcraft.network;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.TamModized.helper.PacketHelper;
import Tamaized.TamModized.helper.PacketHelper.PacketWrapper;
import Tamaized.Voidcraft.VoidCraft;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public interface IEntitySync {

	default void sendPacketUpdates(Entity entity) {
		if (entity.world.isRemote) return;
		try {
			PacketWrapper packet = PacketHelper.createPacket(VoidCraft.channel, VoidCraft.networkChannelName, ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.ENTITY_UPDATES));
			DataOutputStream stream = packet.getStream();
			stream.writeInt(entity.getEntityId());
			encodePacket(stream);
			packet.sendPacket(new TargetPoint(entity.world.provider.getDimension(), entity.posX, entity.posY, entity.posZ, 64));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void encodePacket(DataOutputStream stream) throws IOException;

	void decodePacket(ByteBufInputStream stream) throws IOException;

}
