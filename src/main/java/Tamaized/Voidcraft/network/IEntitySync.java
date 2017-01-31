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

	default void sendPacketUpdates() {
		if (getEntity().world.isRemote) return;
		try {
			PacketWrapper packet = PacketHelper.createPacket(VoidCraft.channel, VoidCraft.networkChannelName, ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.ENTITY_UPDATES));
			DataOutputStream stream = packet.getStream();
			stream.writeInt(getEntity().getEntityId());
			encodePacket(stream);
			packet.sendPacket(new TargetPoint(getEntity().world.provider.getDimension(), getEntity().posX, getEntity().posY, getEntity().posZ, 64));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void encodePacket(DataOutputStream stream) throws IOException;

	void decodePacket(ByteBufInputStream stream) throws IOException;

	/**
	 * Return the entity implementing this interface so the interface can hook into the entity to grab specific values (EntityID, World, Position)
	 */
	Entity getEntity();

}
