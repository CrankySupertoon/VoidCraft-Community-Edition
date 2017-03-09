package Tamaized.Voidcraft.particles.network;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.TamModized.particles.ParticleHelper.IParticlePacketData;
import Tamaized.TamModized.particles.ParticlePacketBase;
import Tamaized.TamModized.particles.TamParticle;
import Tamaized.Voidcraft.items.VoidicDrill.VoidDrillParticleData;
import Tamaized.Voidcraft.particles.VoidicDrillLaser;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VoidicDrillLaserPacketHandler extends ParticlePacketBase {

	@Override
	public void encode(DataOutputStream packet, IParticlePacketData data) throws IOException {
		if (!(data instanceof VoidDrillParticleData)) throw new IOException("Incorrect IParticlePacketData type: " + data);
		VoidDrillParticleData dat = (VoidDrillParticleData) data;
		boolean flag = dat.target != null;
		packet.writeBoolean(flag);
		if (flag) {
			packet.writeDouble(dat.target.xCoord);
			packet.writeDouble(dat.target.yCoord);
			packet.writeDouble(dat.target.zCoord);
		}
		packet.writeInt(dat.id);
		packet.writeBoolean(dat.offhand);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TamParticle decode(ByteBufInputStream packet, WorldClient world, Vec3d pos) {
		try {
			return new VoidicDrillLaser(world, pos, packet.readBoolean() ? new Vec3d(packet.readDouble(), packet.readDouble(), packet.readDouble()) : null, packet.readInt(), packet.readBoolean());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
