package Tamaized.Voidcraft.events;

import java.io.DataOutputStream;

import Tamaized.TamModized.helper.PacketHelper;
import Tamaized.TamModized.helper.PacketHelper.PacketWrapper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class CapabilitySyncEvent {

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent e) {
		if (e.phase == TickEvent.Phase.END && e.side == Side.SERVER && e.player instanceof EntityPlayerMP) {
			IVadeMecumCapability cap = e.player.getCapability(CapabilityList.VADEMECUM, null);
			if (cap != null && cap.isDirty()) {
				sendPacketUpdates((EntityPlayerMP) e.player, cap);
				cap.resetDirty();
			}
		}
	}

	private void sendPacketUpdates(EntityPlayerMP player, IVadeMecumCapability cap) {
		try {
			PacketWrapper packet = PacketHelper.createPacket(VoidCraft.channel, VoidCraft.networkChannelName, ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.VADEMECUM_UPDATE));
			DataOutputStream stream = packet.getStream();
			cap.encodePacket(stream);
			packet.sendPacket(player);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
