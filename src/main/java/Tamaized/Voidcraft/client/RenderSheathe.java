package Tamaized.Voidcraft.client;

import java.nio.FloatBuffer;
import java.util.Random;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import Tamaized.TamModized.particles.FX.ParticleFluff;
import Tamaized.Voidcraft.events.client.TextureStitch;
import Tamaized.Voidcraft.helper.SheatheHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderSheathe {

	private static final FloatBuffer buffer = BufferUtils.createFloatBuffer(16);

	@SubscribeEvent
	public void renderLivingPre(RenderLivingEvent.Pre<EntityLivingBase> e) {
		GlStateManager.pushMatrix();
		EntityLivingBase entity = e.getEntity();

		GL11.glGetFloat(GL11.GL_CURRENT_COLOR, buffer);

		if (entity != null) {
			render(e.getRenderer(), entity, e.getX(), e.getY(), e.getZ(), Minecraft.getMinecraft().getRenderPartialTicks());
		}

		GlStateManager.color(buffer.get(0), buffer.get(1), buffer.get(2), buffer.get(3));
	}

	@SubscribeEvent
	public void renderLivingPost(RenderLivingEvent.Post<EntityLivingBase> e) {
		GlStateManager.popMatrix();
	}

	private void render(net.minecraft.client.renderer.entity.RenderLivingBase render, EntityLivingBase entity, double x, double y, double z, float partialTicks) {

		float[] colors = SheatheHelper.getColor(entity);
		if (colors == null) return;
		Random rand = entity.world.rand;
		double dx = (rand.nextFloat() * 1.0f) - 0.5f;
		double dz = (rand.nextFloat() * 1.0f) - 0.5f;
		int hexColor = 0;
		hexColor += ((int) (colors[0] * 255f) << 24);
		hexColor += ((int) (colors[1] * 255f) << 16);
		hexColor += ((int) (colors[2] * 255f) << 8);
		hexColor += ((int) (colors[3] * 255f));
		if (!Minecraft.getMinecraft().isGamePaused()) Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleFluff(entity.world, new Vec3d(entity.posX + dx, entity.posY + 1, entity.posZ + dz), new Vec3d(0, 0, 0), 20 * 2, -(rand.nextFloat() * 0.05f) - 0.01f, rand.nextFloat(), hexColor));
		GlStateManager.disableLighting();
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite textureatlassprite = TextureStitch.colorFire_layer_0;
		TextureAtlasSprite textureatlassprite1 = TextureStitch.colorFire_layer_1;
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		float f = entity.width * 1.4F;
		GlStateManager.scale(f, f, f);
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		float f1 = 0.5F;
		float f2 = 0.0F;
		float f3 = entity.height / f;
		float f4 = (float) (entity.posY - entity.getEntityBoundingBox().minY);
		GlStateManager.rotate(-render.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(0.0F, 0.0F, -0.3F + (float) ((int) f3) * 0.02F);
		GlStateManager.color(colors[0], colors[1], colors[2], colors[3]);
		float f5 = 0.0F;
		int i = 0;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);

		while (f3 > 0.0F) {
			TextureAtlasSprite textureatlassprite2 = i % 2 == 0 ? textureatlassprite : textureatlassprite1;
			render.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			float f6 = textureatlassprite2.getMinU();
			float f7 = textureatlassprite2.getMinV();
			float f8 = textureatlassprite2.getMaxU();
			float f9 = textureatlassprite2.getMaxV();

			if (i / 2 % 2 == 0) {
				float f10 = f8;
				f8 = f6;
				f6 = f10;
			}

			vertexbuffer.pos((double) (f1 - 0.0F), (double) (0.0F - f4), (double) f5).tex((double) f8, (double) f9).endVertex();
			vertexbuffer.pos((double) (-f1 - 0.0F), (double) (0.0F - f4), (double) f5).tex((double) f6, (double) f9).endVertex();
			vertexbuffer.pos((double) (-f1 - 0.0F), (double) (1.4F - f4), (double) f5).tex((double) f6, (double) f7).endVertex();
			vertexbuffer.pos((double) (f1 - 0.0F), (double) (1.4F - f4), (double) f5).tex((double) f8, (double) f7).endVertex();
			f3 -= 0.45F;
			f4 -= 0.45F;
			f1 *= 0.9F;
			f5 += 0.03F;
			++i;
		}

		tessellator.draw();
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();
	}

}
