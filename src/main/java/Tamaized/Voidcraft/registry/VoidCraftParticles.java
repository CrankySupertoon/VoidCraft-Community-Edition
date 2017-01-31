package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import Tamaized.TamModized.particles.ParticlePacketHandlerRegistry;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.particles.VoidicDrillLaser;
import Tamaized.Voidcraft.particles.network.VoidicDrillLaserPacketHandler;
import Tamaized.Voidcraft.particles.network.XiaLaserPacketHandler;

public class VoidCraftParticles implements ITamRegistry {

	public static int drillRayHandler;
	public static int xiaTeleportHandler;

	@Override
	public void preInit() {
		drillRayHandler = ParticlePacketHandlerRegistry.register(new VoidicDrillLaserPacketHandler());
		xiaTeleportHandler = ParticlePacketHandlerRegistry.register(new XiaLaserPacketHandler());
	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {

	}

	@Override
	public ArrayList<ITamModel> getModelList() {
		return new ArrayList<ITamModel>();
	}

	@Override
	public String getModID() {
		return VoidCraft.modid;
	}

	@Override
	public void clientPreInit() {

	}

	@Override
	public void clientInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clientPostInit() {
		// TODO Auto-generated method stub

	}

}
