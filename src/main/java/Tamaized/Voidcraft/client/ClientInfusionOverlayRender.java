package Tamaized.Voidcraft.client;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import Tamaized.Voidcraft.capabilities.voidicPower.IVoidicPowerCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class ClientInfusionOverlayRender {

	private static ResourceLocation texture = new ResourceLocation(VoidCraft.modid, "textures/gui/voidicInfusion.png");

	@SubscribeEvent
	public void InGameOverlay(RenderGameOverlayEvent e) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP player = mc.player;
		if (!player.hasCapability(CapabilityList.VOIDICINFUSION, null)) return;
		IVoidicInfusionCapability cap = player.getCapability(CapabilityList.VOIDICINFUSION, null);

		if (e.getType() == e.getType().PORTAL) {
			ScaledResolution scaledRes = new ScaledResolution(mc);
			GlStateManager.disableAlpha();
			GlStateManager.disableDepth();
			GlStateManager.depthMask(false);
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.color(1.0F, 1.0F, 1.0F, cap.getInfusionPerc());
			mc.getTextureManager().bindTexture(texture);

			Tessellator tessellator = Tessellator.getInstance();
			VertexBuffer worldrenderer = tessellator.getBuffer();
			worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
			worldrenderer.pos(0.0D, (double) scaledRes.getScaledHeight(), -90.0D).tex(0, 1).endVertex();
			worldrenderer.pos((double) scaledRes.getScaledWidth(), (double) scaledRes.getScaledHeight(), -90.0D).tex(1, 1).endVertex();
			worldrenderer.pos((double) scaledRes.getScaledWidth(), 0.0D, -90.0D).tex(1, 0).endVertex();
			worldrenderer.pos(0.0D, 0.0D, -90.0D).tex(0, 0).endVertex();
			tessellator.draw();
			GlStateManager.depthMask(true);
			GlStateManager.enableDepth();
			GlStateManager.enableAlpha();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		}

	}

}
