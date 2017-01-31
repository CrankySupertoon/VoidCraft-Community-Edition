/**
 * Original Class file: BossBarHandler
 * Mod: Botania
 * Author: Vazkii
 * 
 * This is a custom version of it therefore I wont claim this file truly as my own.
 */
package Tamaized.Voidcraft.entity.boss.render.bossBar;

import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class RenderBossHeathBar {

	public static final ResourceLocation whitespace = new ResourceLocation(VoidCraft.modid + ":textures/gui/BossBar/WhiteSpace.png");
	public static final ResourceLocation bar = new ResourceLocation(VoidCraft.modid + ":textures/gui/BossBar/bar.png");
	public static final ResourceLocation bg = new ResourceLocation(VoidCraft.modid + ":textures/gui/BossBar/bg.png");

	private static IVoidBossData voidBoss;

	private static float flashTick = 0;
	private static boolean flashTickFlag = false;
	private static int flashTickWait = 0;

	private static final Rectangle bgRect = new Rectangle(0, 0, 205, 16);
	private static final Rectangle fgRect = new Rectangle(0, bgRect.y + bgRect.height, 181, 10);

	public static void setCurrentBoss(IVoidBossData b) {
		voidBoss = b;
	}

	public static void render(ScaledResolution res) {
		if (voidBoss == null) return;

		Minecraft mc = Minecraft.getMinecraft();
		String name = voidBoss.getNameForBossBar().getFormattedText();
		int c = res.getScaledWidth() / 2;
		int x = c - bgRect.width / 2;
		int y = 20;
		int xf = x + (bgRect.width - fgRect.width) / 2;
		int yf = y + (bgRect.height - fgRect.height) / 2;
		float bossHpPerc = (float) ((float) fgRect.width * (float) voidBoss.getPercentHPForBossBar());
		int tx = c - mc.getRenderManager().getFontRenderer().getStringWidth(name) / 2;

		GL11.glColor4f(1F, 1F, 1F, 1F);

		// GL11.glEnable(GL11.GL_BLEND);
		// GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GlStateManager.enableBlend();

		mc.renderEngine.bindTexture(bg);
		GL11.glColor4f(1F, 1F, 1F, flashTick);
		// drawBar(x, y, 0, bgRect.width, bgRect.height);

		GlStateManager.pushMatrix();
		{
			int xOff = 25;
			int yOff = -10;
			GlStateManager.scale(0.75, 0.35, 1.0);
			Tessellator tessellator = Tessellator.getInstance();
			VertexBuffer worldRender = tessellator.getBuffer();
			worldRender.begin(7, DefaultVertexFormats.POSITION_TEX);
			worldRender.pos(xOff + x + 322, yOff + y, 0).tex(1, 0).endVertex();
			worldRender.pos(xOff + x, yOff + y, 0).tex(0, 0).endVertex();
			worldRender.pos(xOff + x, yOff + y + 141, 0).tex(0, 1).endVertex();
			worldRender.pos(xOff + x + 322, yOff + y + 141, 0).tex(1, 1).endVertex();
			tessellator.draw();
		}
		GlStateManager.popMatrix();
		mc.renderEngine.bindTexture(bar);
		GL11.glColor4f(0.3F, 0.0F, 0.8F, 0.5F);
		drawBar(x, y, 0, bgRect.width, bgRect.height);

		mc.renderEngine.bindTexture(bar);
		GL11.glColor4f(0.1F, 0F, 0.1F, 1F);
		drawBar(xf, yf, 0, fgRect.width, fgRect.height);
		GL11.glColor4f(0F, 1F, 0F, 1F);

		// drawBar(xf, yf, 0, bossHpPerc, fgRect.height);
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer worldRender = tessellator.getBuffer();
		worldRender.begin(7, DefaultVertexFormats.POSITION_TEX);
		worldRender.pos(xf + bossHpPerc, yf, 0).tex((float) voidBoss.getPercentHPForBossBar(), 0).endVertex();
		worldRender.pos(xf, yf, 0).tex(0, 0).endVertex();
		worldRender.pos(xf, yf + fgRect.height, 0).tex(0, 0.1).endVertex();
		worldRender.pos(xf + bossHpPerc, yf + fgRect.height, 0).tex((float) voidBoss.getPercentHPForBossBar(), 0.1).endVertex();
		tessellator.draw();

		GL11.glColor4f(1F, 1F, 1F, 1F);
		String hpText = (int) voidBoss.getHealthForBossBar() + "/" + (int) voidBoss.getMaxHealthForBossBar();
		mc.getRenderManager().getFontRenderer().drawString(hpText, xf + (115 - (mc.getRenderManager().getFontRenderer().getStringWidth(hpText))) + 1, y + 5, 0x000000);
		mc.getRenderManager().getFontRenderer().drawString(hpText, xf + (115 - (mc.getRenderManager().getFontRenderer().getStringWidth(hpText))), y + 4, 0xFF0000);

		mc.getRenderManager().getFontRenderer().drawStringWithShadow(name, tx, y - 10, 0xFF0000);

		GL11.glColor4f(1F, 1F, 1F, 1F);// CleanUp
		GlStateManager.disableBlend();
		// GL11.glEnable(GL11.GL_BLEND);

		Entity e = (Entity) voidBoss;
		EntityPlayer p = mc.player;
		if (e.isDead || !p.world.loadedEntityList.contains(e) || pointDistanceSpace(e.posX, e.posY, e.posZ, p.posX, p.posY, p.posZ) > 32) voidBoss = null;

		if (flashTickWait <= 0) {
			if (flashTickFlag) {
				if (flashTick > 0.0) {
					flashTick -= 0.1;
				} else {
					flashTickFlag = !flashTickFlag;
				}
			} else {
				if (flashTick < 1) {
					flashTick += 0.1;
				} else {
					flashTickFlag = !flashTickFlag;
				}
			}
			flashTickWait = 1;
		} else {
			flashTickWait--;
		}
	}

	public static float pointDistanceSpace(double x1, double y1, double z1, double x2, double y2, double z2) {
		return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
	}

	public static void drawBar(int x, int y, int z, int w, int h) {
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer worldRender = tessellator.getBuffer();
		worldRender.begin(7, DefaultVertexFormats.POSITION_TEX);
		worldRender.pos(x + w, y, z).tex(1, 0).endVertex();
		worldRender.pos(x, y, z).tex(0, 0).endVertex();
		worldRender.pos(x, y + h, z).tex(0, 0.1).endVertex();
		worldRender.pos(x + w, y + h, z).tex(1, 0.1).endVertex();
		tessellator.draw();
	}

}
