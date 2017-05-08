package Tamaized.Voidcraft.client;

import org.lwjgl.opengl.GL11;

import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderLiving {

	@SubscribeEvent
	public void renderPre(RenderLivingEvent.Pre e) {
		GlStateManager.pushMatrix();
		EntityLivingBase living = e.getEntity();
		if (living != null) {
			float f1 = 1.0f;
			if (living.hasCapability(CapabilityList.VOIDICINFUSION, null)) {
				IVoidicInfusionCapability cap = living.getCapability(CapabilityList.VOIDICINFUSION, null);
				f1 = 1.45F - cap.getInfusionPerc();
				f1 = f1 < 0.5F ? 0.5F : f1;
				f1 = f1 > 1.0f ? 1.0f : f1;
			}
			if (f1 < 1) {
				GlStateManager.enableBlend();
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			}
			GlStateManager.color(f1, f1, f1, f1);
		}
	}

	@SubscribeEvent
	public void renderPost(RenderLivingEvent.Post e) {
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

}
