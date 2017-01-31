package Tamaized.Voidcraft.entity.nonliving.render;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.nonliving.EntitySpellRune;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class RenderSpellRune<T extends EntitySpellRune> extends Render<T> {

	private static final ResourceLocation TEXTURE_RUNE = new ResourceLocation(VoidCraft.modid, "textures/entity/rune.png");

	public RenderSpellRune(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
		bindTexture(TEXTURE_RUNE);
		Tessellator tess = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tess.getBuffer();
		int color = entity.getColor();
		GlStateManager.color(((color >> 16) & 0xFF) / 255f, ((color >> 8) & 0xFF) / 255f, (color & 0xFF) / 255f, (0xFF) / 255f);
		GlStateManager.disableLighting();
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		double offsetX = 2.5D;
		double offsetY = 0.01D;
		double offsetZ = 2.75D;
		vertexbuffer.pos(x + offsetX - 4.0D, y + offsetY, z + offsetZ).tex(0.0D, 1.0D).endVertex();
		vertexbuffer.pos(x + offsetX, y + offsetY, z + offsetZ).tex(1.0D, 1.0D).endVertex();
		vertexbuffer.pos(x + offsetX, y + offsetY, z + offsetZ - 4.0D).tex(1.0D, 0.0D).endVertex();
		vertexbuffer.pos(x + offsetX - 4.0D, y + offsetY, z + offsetZ - 4.0D).tex(0.0D, 0.0D).endVertex();
		tess.draw();
		GlStateManager.enableLighting();
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return null;
	}

}
