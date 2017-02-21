package Tamaized.Voidcraft.entity.boss.xia.finalphase.render;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.boss.model.ModelVoidBoss;
import Tamaized.Voidcraft.entity.boss.render.bossBar.RenderAlternateBossBars;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.EntityTwinsXia;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderTwinsXia extends RenderLiving<EntityTwinsXia> {

	public static final ResourceLocation TEXTURE_DOL = new ResourceLocation(VoidCraft.modid, "textures/entity/dol.png");
	public static final ResourceLocation TEXTURE_ZOL = new ResourceLocation(VoidCraft.modid, "textures/entity/zol.png");

	private final ResourceLocation TEXTURE;

	public RenderTwinsXia(RenderManager manager, ResourceLocation texture, ModelVoidBoss model, float shadowsizeIn) {
		super(manager, model, shadowsizeIn);
		TEXTURE = texture;
	}

	@Override
	public void doRender(EntityTwinsXia entity, double x, double y, double z, float entityYaw, float partialTicks) {
		RenderAlternateBossBars.addBoss(entity.bossBarWrapper);
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	@Override
	protected void preRenderCallback(EntityTwinsXia entity, float partialTickTime) {
		float f = entity.isFrozen() ? entity.getHealthPerc() : 1.0F;
		GlStateManager.color(f, f, f, 1.0F);
		super.preRenderCallback(entity, partialTickTime);
	}

	protected void renderLabel(EntityTwinsXia entity, double par2, double par4, double par6) {
		int distanceToEntity = 32;
		this.renderLivingLabel(entity, entity.getDisplayName().getFormattedText(), par2, par4, par6, distanceToEntity);
		par4 += (double) ((float) this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * par6);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTwinsXia entity) {
		return TEXTURE;
	}

}
