package Tamaized.Voidcraft.particles.network;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.TamModized.particles.ParticleHelper.IParticlePacketData;
import Tamaized.TamModized.particles.ParticlePacketBase;
import Tamaized.TamModized.particles.TamParticle;
import Tamaized.Voidcraft.particles.XiaLaser;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class XiaLaserPacketHandler extends ParticlePacketBase {

	@Override
	public void encode(DataOutputStream packet, IParticlePacketData data) throws IOException {
		if (!(data instanceof XiaLaserParticleData)) throw new IOException("Incorrect IParticlePacketData type: " + data);
		XiaLaserParticleData dat = (XiaLaserParticleData) data;
		packet.writeInt(dat.entityID);
		packet.writeInt(dat.yaw);
		packet.writeInt(dat.pitch);
		packet.writeFloat(dat.colors[0]);
		packet.writeFloat(dat.colors[1]);
		packet.writeFloat(dat.colors[2]);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TamParticle decode(ByteBufInputStream packet, WorldClient world, Vec3d pos) {
		try {
			return new XiaLaser(world, pos, packet.readInt(), packet.readInt(), packet.readInt(), new float[] { packet.readFloat(), packet.readFloat(), packet.readFloat() });
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public class XiaLaserParticleData implements IParticlePacketData {

		public final int entityID;
		public final int yaw;
		public final int pitch;
		public final float[] colors;

		public XiaLaserParticleData(int entityID, int yaw, int pitch, float[] colors) {
			this.entityID = entityID;
			this.yaw = yaw;
			this.pitch = pitch;
			this.colors = colors;
		}

	}

}
