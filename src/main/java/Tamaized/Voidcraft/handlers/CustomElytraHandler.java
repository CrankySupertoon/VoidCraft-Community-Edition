package Tamaized.Voidcraft.handlers;

import java.io.DataOutputStream;

import Tamaized.TamModized.helper.PacketHelper;
import Tamaized.TamModized.helper.PacketHelper.PacketWrapper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.armor.ArmorCustomElytra;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.network.ServerPacketHandler;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class CustomElytraHandler {

	@SubscribeEvent
	public void handleElytra(PlayerTickEvent e) {
		double px = 0;
		double pz = 0;
		double py = 0;
		if (e.phase == Phase.START) {
			px = e.player.posX;
			py = e.player.posY;
			pz = e.player.posZ;
		}
		if (e.phase == Phase.END) {
			EntityPlayer entity = e.player;
			doElytraFlyingChecks(entity);
			if (entity.world.isRemote) {
				net.minecraft.client.entity.EntityPlayerSP clientPlayer = net.minecraft.client.Minecraft.getMinecraft().player;
				doElytraFlyingChecks(clientPlayer);
				if (clientPlayer.movementInput.jump && !clientPlayer.onGround && clientPlayer.motionY < 0.0D && !isElytraFlying(clientPlayer) && !clientPlayer.capabilities.isFlying) {
					ItemStack itemstack = clientPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
					if (!itemstack.isEmpty() && itemstack.getItem() instanceof ArmorCustomElytra && ArmorCustomElytra.isBroken(itemstack)) {
						setFlying(clientPlayer, true);
						sendPacketToServer(clientPlayer);
						net.minecraft.client.Minecraft.getMinecraft().getSoundHandler().playSound(new net.minecraft.client.audio.ElytraSound(clientPlayer));
					}
				}
			}
			if (entity.isServerWorld() || entity.canPassengerSteer()) {
				if (!entity.isInWater() || entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isFlying) {
					if (!entity.isInLava() || entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isFlying) {
						if (isElytraFlying(entity)) {
							if (entity.motionY > -0.5D) {
								entity.fallDistance = 1.0F;
							}
							Vec3d vec3d = entity.getLookVec();
							float f = entity.rotationPitch * 0.017453292F;
							double d6 = Math.sqrt(vec3d.xCoord * vec3d.xCoord + vec3d.zCoord * vec3d.zCoord);
							double d8 = Math.sqrt(entity.motionX * entity.motionX + entity.motionZ * entity.motionZ);
							double d1 = vec3d.lengthVector();
							float f4 = MathHelper.cos(f);
							f4 = (float) ((double) f4 * (double) f4 * Math.min(1.0D, d1 / 0.4D));
							entity.motionY += -0.08D + (double) f4 * 0.06D;
							if (entity.motionY < 0.0D && d6 > 0.0D) {
								double d2 = entity.motionY * -0.1D * (double) f4;
								entity.motionY += d2;
								entity.motionX += vec3d.xCoord * d2 / d6;
								entity.motionZ += vec3d.zCoord * d2 / d6;
							}
							if (f < 0.0F) {
								double d9 = d8 * (double) (-MathHelper.sin(f)) * 0.04D;
								entity.motionY += d9 * 3.2D;
								entity.motionX -= vec3d.xCoord * d9 / d6;
								entity.motionZ -= vec3d.zCoord * d9 / d6;
							}
							if (d6 > 0.0D) {
								entity.motionX += (vec3d.xCoord / d6 * d8 - entity.motionX) * 0.1D;
								entity.motionZ += (vec3d.zCoord / d6 * d8 - entity.motionZ) * 0.1D;
							}
							entity.motionX *= 0.9900000095367432D;
							entity.motionY *= 0.9800000190734863D;
							entity.motionZ *= 0.9900000095367432D;
							entity.move(MoverType.SELF, entity.motionX, entity.motionY, entity.motionZ);
							if (entity.isCollidedHorizontally && !entity.world.isRemote) {
								double d10 = Math.sqrt(entity.motionX * entity.motionX + entity.motionZ * entity.motionZ);
								double d3 = d8 - d10;
								float f5 = (float) (d3 * 10.0D - 3.0D);
								if (f5 > 0.0F) {
									entity.playSound((int) f5 > 4 ? SoundEvents.ENTITY_PLAYER_BIG_FALL : SoundEvents.ENTITY_PLAYER_SMALL_FALL, 1.0F, 1.0F);
									entity.attackEntityFrom(DamageSource.FLY_INTO_WALL, f5);
								}

								if (entity.onGround && !entity.world.isRemote) {
									setFlying(entity, false);
								}
							}
						}
					}
				}
			}
			if (isElytraFlying(entity)) {
				double p_1 = entity.posX - px;
				double p_2 = entity.posY - py;
				double p_3 = entity.posZ - pz;
				int l = Math.round(MathHelper.sqrt(p_1 * p_1 + p_2 * p_2 + p_3 * p_3) * 100.0F);
				entity.addStat(StatList.AVIATE_ONE_CM, l);
			}
		}
	}

	private void doElytraFlyingChecks(EntityPlayer e) {
		if (!e.hasCapability(CapabilityList.ELYTRAFLYING, null)) return;
		if (!e.onGround && !e.isRiding()) {
			ItemStack itemstack = e.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
			if (!itemstack.isEmpty() && itemstack.getItem() instanceof ArmorCustomElytra && ArmorCustomElytra.isBroken(itemstack)) {

				int ticks = e.getCapability(CapabilityList.ELYTRAFLYING, null).getElytraTime() + 1;
				if (!e.world.isRemote && (ticks) % 20 == 0) {
					itemstack.damageItem(1, e);
				}
				e.getCapability(CapabilityList.ELYTRAFLYING, null).setElytraTime(ticks);
				e.getCapability(CapabilityList.ELYTRAFLYING, null).setElytraFlying(true);
			}
		}
		e.getCapability(CapabilityList.ELYTRAFLYING, null).setElytraTime(0);
		e.getCapability(CapabilityList.ELYTRAFLYING, null).setElytraFlying(false);
	}

	public static boolean isElytraFlying(EntityPlayer player) {
		return player.hasCapability(CapabilityList.ELYTRAFLYING, null) ? player.getCapability(CapabilityList.ELYTRAFLYING, null).isElytraFlying() : false;
	}

	public static void setFlying(EntityPlayer player, boolean flag) {
		if (!player.hasCapability(CapabilityList.ELYTRAFLYING, null)) return;
		player.getCapability(CapabilityList.ELYTRAFLYING, null).setElytraFlying(flag);
	}

	private static void sendPacketToServer(EntityPlayer sender) {
		try {
			PacketWrapper packet = PacketHelper.createPacket(VoidCraft.channel, VoidCraft.networkChannelName, ServerPacketHandler.getPacketTypeID(ServerPacketHandler.PacketType.CUSTOM_ELYTRA));
			DataOutputStream stream = packet.getStream();
			packet.sendPacketToServer();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
