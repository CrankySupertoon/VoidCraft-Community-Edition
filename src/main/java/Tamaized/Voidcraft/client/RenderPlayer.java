package Tamaized.Voidcraft.client;

import org.lwjgl.util.glu.Sphere;

import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import Tamaized.Voidcraft.handlers.CustomElytraHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderPlayer {

	@SubscribeEvent
	public void renderPlayer(RenderPlayerEvent.Pre e) {
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		EntityPlayer player = e.getEntityPlayer();
		if (player != null) {
			// renderElytra(player, e.getPartialRenderTick());
		}
	}

	@SubscribeEvent
	public void renderPlayer(RenderPlayerEvent.Post e) {
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}

	private void renderElytra(EntityPlayer player, float partialTick) {
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		{
			if (CustomElytraHandler.isElytraFlying(player)) {
				// rotateCorpse(player, player.ticksExisted + e.getPartialRenderTick(), interpolateRotation(player.prevRenderYawOffset, player.renderYawOffset, e.getPartialRenderTick()), e.getPartialRenderTick());
				float f = (float) player.getCapability(CapabilityList.ELYTRAFLYING, null).getElytraTime() + partialTick;
				float zf1 = MathHelper.clamp(f * f / 100.0F, 0.0F, 1.0F);
				GlStateManager.rotate(zf1 * (-90.0F - player.rotationPitch), 1.0F, 0.0F, 0.0F);
				Vec3d vec3d = player.getLook(partialTick);
				double d0 = player.motionX * player.motionX + player.motionZ * player.motionZ;
				double d1 = vec3d.xCoord * vec3d.xCoord + vec3d.zCoord * vec3d.zCoord;

				if (d0 > 0.0D && d1 > 0.0D) {
					double d2 = (player.motionX * vec3d.xCoord + player.motionZ * vec3d.zCoord) / (Math.sqrt(d0) * Math.sqrt(d1));
					double d3 = player.motionX * vec3d.zCoord - player.motionZ * vec3d.xCoord;
					GlStateManager.rotate((float) (Math.signum(d3) * Math.acos(d2)) * 180.0F / (float) Math.PI, 0.0F, 1.0F, 0.0F);
				}
			}
		}
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}

	protected void rotateCorpse(EntityPlayer entityLiving, float p_77043_2_, float p_77043_3_, float partialTicks) {
		GlStateManager.rotate(180.0F - p_77043_3_, 0.0F, 1.0F, 0.0F);

		if (entityLiving.deathTime > 0) {
			float f = ((float) entityLiving.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
			f = MathHelper.sqrt(f);

			if (f > 1.0F) {
				f = 1.0F;
			}

			GlStateManager.rotate(f * 90F, 0.0F, 0.0F, 1.0F);
		} else {
			String s = TextFormatting.getTextWithoutFormattingCodes(entityLiving.getName());

			if (s != null && ("Dinnerbone".equals(s) || "Grumm".equals(s)) && (!(entityLiving instanceof EntityPlayer) || ((EntityPlayer) entityLiving).isWearing(EnumPlayerModelParts.CAPE))) {
				GlStateManager.translate(0.0F, entityLiving.height + 0.1F, 0.0F);
				GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
			}
		}
	}

	protected float interpolateRotation(float prevYawOffset, float yawOffset, float partialTicks) {
		float f;

		for (f = yawOffset - prevYawOffset; f < -180.0F; f += 360.0F) {
			;
		}

		while (f >= 180.0F) {
			f -= 360.0F;
		}

		return prevYawOffset + partialTicks * f;
	}

}
