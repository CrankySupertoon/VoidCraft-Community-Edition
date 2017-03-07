package Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.phases;

import java.util.ArrayList;

import Tamaized.TamModized.particles.ParticleHelper;
import Tamaized.TamModized.particles.ParticlePacketHandlerRegistry;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import Tamaized.Voidcraft.damageSources.DamageSourceVoidicInfusion;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia.XiaTookDamagePacket;
import Tamaized.Voidcraft.entity.mob.lich.EntityLichInferno;
import Tamaized.Voidcraft.network.IVoidBossAIPacket;
import Tamaized.Voidcraft.particles.network.XiaLaserPacketHandler;
import Tamaized.Voidcraft.particles.network.XiaLaserPacketHandler.XiaLaserParticleData;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityVoidNPCAIBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAIXiaPhase1<T extends EntityBossXia> extends EntityVoidNPCAIBase<T> {

	private AxisAlignedBB teleportationBox = new AxisAlignedBB(-18, 0, -25, 18, 5, 6);

	private int actionTick = 20 * 10;
	private int teleportTick = 20 * 3;

	private int resetAnimationTick = 0;

	private boolean isTeleporting = false;

	public EntityAIXiaPhase1(T entityBoss, ArrayList<Class> c) {
		super(entityBoss, c);
	}

	@Override
	protected void preInit() {

	}

	@Override
	protected void postInit() {
		teleportationBox = new AxisAlignedBB(-18, 0, -25, 18, 5, 6);
	}

	@Override
	public void readPacket(IVoidBossAIPacket packet) {
		if (packet instanceof XiaTookDamagePacket) doTeleport();
	}

	@Override
	public void update() {
		if (resetAnimationTick == 0) {
			resetAnimationTick--;
			getEntity().setArmRotations(0, 0, 0, 0, true);
		} else if (resetAnimationTick >= 0) {
			resetAnimationTick--;
		}

		if (tick % actionTick == 0) {
			switch (world.rand.nextInt(5)) {
				case 0:
					getEntity().setArmRotations(180, 180, 0, 0, true);
					resetAnimationTick = 20 * 4;
					actionTeleport();
					break;
				case 1: // Voidic Fire (Same as Lich)
					getEntity().setArmRotations(180, 0, 0, 0, true);
					resetAnimationTick = 20 * 4;
					getEntity().world.spawnEntity(new EntityLichInferno(getEntity().world, getEntity().getPosition(), 10, 10));
					break;
				case 2: // Use the force luke :P some sort of choke mechanic idk
					if (closestEntity == null) break;
					resetAnimationTick = 20 * 4;
					getEntity().setArmRotations(90, 90, 0, 0, true);
					closestEntity.attackEntityFrom(new DamageSourceVoidicInfusion(), 8.0f);
					break;
				case 3: // litBolt
					if (closestEntity == null) break;
					getEntity().setArmRotations(90, 0.0f, 0, 0, true);
					resetAnimationTick = 20 * 2;
					EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, closestEntity.posX, closestEntity.posY, closestEntity.posZ, false);
					entitylightningbolt.setLocationAndAngles(closestEntity.posX, closestEntity.posY + 1 + entitylightningbolt.getYOffset(), closestEntity.posZ, closestEntity.rotationYaw, closestEntity.rotationPitch);
					world.addWeatherEffect(entitylightningbolt);
					break;
				case 4: // Give less than 1 of the max voidic infusion to the player
					if (closestEntity == null) break;
					getEntity().setArmRotations(0, 90, 0, 0, true);
					resetAnimationTick = 20 * 2;
					if (closestEntity instanceof EntityPlayer) {
						EntityPlayer player = (EntityPlayer) closestEntity;
						player.addPotionEffect(new PotionEffect(VoidCraft.potions.voidicInfusion, 20 * 10));
					}
					break;
				default:
					actionTeleport();
					break;
			}
			tick = 1;
		}
		if (isTeleporting) {
			spawnLaser();
			if (tick % teleportTick == 0) {
				doTeleport();
			}

		}
	}

	private void actionTeleport() {
		isTeleporting = true;
		spawnLaser();
	}

	private void spawnLaser() {
		XiaLaserParticleData data = ((XiaLaserPacketHandler) ParticlePacketHandlerRegistry.getHandler(VoidCraft.particles.xiaTeleportHandler)).new XiaLaserParticleData(getEntity().getEntityId(), 0, -90, new float[] { 1.0f, 1.0f, 1.0f });
		ParticleHelper.sendPacketToClients(world, VoidCraft.particles.xiaTeleportHandler, new Vec3d(getEntity().posX, getEntity().posY, getEntity().posZ), 64, new ParticleHelper.ParticlePacketHelper(VoidCraft.particles.xiaTeleportHandler, data));
	}

	private void doTeleport() {
		Double[] nextLoc = getNextTeleportLocation();
		getEntity().setPositionAndUpdate(getBlockPosition().getX() + nextLoc[0] + 0.5, getBlockPosition().getY() + nextLoc[1], getBlockPosition().getZ() + nextLoc[2] + 0.5);
		isTeleporting = false;
	}

	private Double[] getNextTeleportLocation() {
		// int i = rand.nextInt(teleportLocations.size());
		// Double[] loc = teleportLocations.get(i > 0 ? i : 0);
		// if (Arrays.equals(loc, currLoc)) loc = getNextTeleportLocation();
		// currLoc = loc;
		Double[] loc = { 0.0D, 0.0D, 0.0D };
		loc[0] = (world.rand.nextDouble() * (teleportationBox.maxX - teleportationBox.minX)) + teleportationBox.minX;
		loc[1] = teleportationBox.maxY;
		loc[2] = (world.rand.nextDouble() * (teleportationBox.maxZ - teleportationBox.minZ)) + teleportationBox.minZ;
		while (world.isAirBlock(new BlockPos(getPosition().xCoord + loc[0], getPosition().yCoord + loc[1], getPosition().zCoord + loc[2]))) {
			loc[1] -= 1.0D;
		}
		return loc;
	}

	@Override
	public void doAction(BlockPos pos) {

	}

}
