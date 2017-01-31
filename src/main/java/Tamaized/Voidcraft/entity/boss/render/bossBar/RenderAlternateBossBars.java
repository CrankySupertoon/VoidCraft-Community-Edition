package Tamaized.Voidcraft.entity.boss.render.bossBar;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.BossInfo;

public class RenderAlternateBossBars {

	private static final ResourceLocation GUI_BARS_TEXTURES = new ResourceLocation("textures/gui/bars.png");
	private static final Minecraft mc = Minecraft.getMinecraft();

	private static final List<AlternateBossBarWrapper> wrapperList = new ArrayList<AlternateBossBarWrapper>();

	public static void addBoss(AlternateBossBarWrapper boss) {
		wrapperList.add(boss);
	}

	public static void render(ScaledResolution scaledResolution, float partialTicks) {
		int i = scaledResolution.getScaledWidth();
		int j = 40;// 12;
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		for (AlternateBossBarWrapper wrapper : wrapperList) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(GUI_BARS_TEXTURES);
			int k = i / 2 - 91;
			render(wrapper, k, j);
			String s = wrapper.getName().getFormattedText();
			// mc.fontRendererObj.drawStringWithShadow(s, (float) (i / 2 - mc.fontRendererObj.getStringWidth(s) / 2), (float) (j - 9), 16777215);
			j += (10/* + mc.fontRendererObj.FONT_HEIGHT */);
			if (j >= scaledResolution.getScaledHeight() / 3) {
				break;
			}
		}
		wrapperList.clear();
	}

	private static void render(AlternateBossBarWrapper wrapper, int x, int y) {
		drawTexturedModalRect(x, y, 0, wrapper.color.ordinal() * 5 * 2, 182, 5, 0);

		if (wrapper.overlay != BossInfo.Overlay.PROGRESS) {
			drawTexturedModalRect(x, y, 0, 80 + (wrapper.overlay.ordinal() - 1) * 5 * 2, 182, 5, 0);
		}

		int i = (int) (wrapper.getHealthPerc() * 183.0F)-1;

		if (i > 0) {
			drawTexturedModalRect(x, y, 0, wrapper.color.ordinal() * 5 * 2 + 5, i, 5, 0);

			if (wrapper.overlay != BossInfo.Overlay.PROGRESS) {
				drawTexturedModalRect(x, y, 0, 80 + (wrapper.overlay.ordinal() - 1) * 5 * 2 + 5, i, 5, 0);
			}
		}
	}

	public static void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height, double zLevel) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexbuffer.pos((double) (x + 0), (double) (y + height), (double) zLevel).tex((double) ((float) (textureX + 0) * 0.00390625F), (double) ((float) (textureY + height) * 0.00390625F)).endVertex();
		vertexbuffer.pos((double) (x + width), (double) (y + height), (double) zLevel).tex((double) ((float) (textureX + width) * 0.00390625F), (double) ((float) (textureY + height) * 0.00390625F)).endVertex();
		vertexbuffer.pos((double) (x + width), (double) (y + 0), (double) zLevel).tex((double) ((float) (textureX + width) * 0.00390625F), (double) ((float) (textureY + 0) * 0.00390625F)).endVertex();
		vertexbuffer.pos((double) (x + 0), (double) (y + 0), (double) zLevel).tex((double) ((float) (textureX + 0) * 0.00390625F), (double) ((float) (textureY + 0) * 0.00390625F)).endVertex();
		tessellator.draw();
	}

	public static final class AlternateBossBarWrapper {

		public final IAlternateBoss entity;
		public final BossInfo.Color color;
		public final BossInfo.Overlay overlay;

		public AlternateBossBarWrapper(IAlternateBoss e, BossInfo.Color c, BossInfo.Overlay o) {
			entity = e;
			color = c;
			overlay = o;
		}

		public float getHealthPerc() {
			return entity.getHealthPerc();
		}

		public ITextComponent getName() {
			return entity.getAlternateBossName() == null ? new TextComponentString("") : entity.getAlternateBossName();
		}

	}

	public interface IAlternateBoss {

		float getHealthPerc();

		ITextComponent getAlternateBossName();

	}

}
