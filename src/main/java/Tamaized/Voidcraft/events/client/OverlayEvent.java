package Tamaized.Voidcraft.events.client;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.handlers.ClientPortalDataHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OverlayEvent {

	private static final String texture_void = (VoidCraft.modid + ":blocks/blockportalvoid");
	private static final String texture_xia = (VoidCraft.modid + ":blocks/blockportalxia");

	@SubscribeEvent
	public void InGameOverlay(RenderGameOverlayEvent e) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP player = mc.player;

		if (e.getType() == e.getType().PORTAL) {
			if (ClientPortalDataHandler.active) {
				if (ClientPortalDataHandler.tick < 0.8F) ClientPortalDataHandler.tick += 0.004F;
				else ClientPortalDataHandler.tick = 0.8F;
			} else {
				if (ClientPortalDataHandler.tick > 0) ClientPortalDataHandler.tick -= 0.005F;
				else {
					ClientPortalDataHandler.tick = 0;
					ClientPortalDataHandler.type = 0;
				}
			}
			float j = ClientPortalDataHandler.tick;
			int type = ClientPortalDataHandler.type;
			ScaledResolution scaledRes = new ScaledResolution(mc);
			GlStateManager.disableAlpha();
			GlStateManager.disableDepth();
			GlStateManager.depthMask(false);
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.color(1.0F, 1.0F, 1.0F, j);
			mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

			TextureAtlasSprite textureatlassprite = mc.getBlockRendererDispatcher().getBlockModelShapes().getModelManager().getTextureMap().getAtlasSprite(type == VoidCraft.config.getDimensionIdXia() ? texture_xia : texture_void);
			float f = textureatlassprite.getMinU();
			float f1 = textureatlassprite.getMinV();
			float f2 = textureatlassprite.getMaxU();
			float f3 = textureatlassprite.getMaxV();

			Tessellator tessellator = Tessellator.getInstance();
			VertexBuffer worldrenderer = tessellator.getBuffer();
			worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
			worldrenderer.pos(0.0D, (double) scaledRes.getScaledHeight(), -90.0D).tex((double) f, (double) f3).endVertex();
			worldrenderer.pos((double) scaledRes.getScaledWidth(), (double) scaledRes.getScaledHeight(), -90.0D).tex((double) f2, (double) f3).endVertex();
			worldrenderer.pos((double) scaledRes.getScaledWidth(), 0.0D, -90.0D).tex((double) f2, (double) f1).endVertex();
			worldrenderer.pos(0.0D, 0.0D, -90.0D).tex((double) f, (double) f1).endVertex();
			tessellator.draw();
			GlStateManager.depthMask(true);
			GlStateManager.enableDepth();
			GlStateManager.enableAlpha();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}
