package Tamaized.Voidcraft.entity.boss.herobrine.extra.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import Tamaized.Voidcraft.entity.boss.herobrine.EntityBossHerobrine;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineShadow;
import Tamaized.Voidcraft.entity.boss.render.bossBar.RenderBossHeathBar;

public class RenderHerobrineShadow<T extends EntityHerobrineShadow> extends Render<T> {

	private static final ResourceLocation Herobrine_Texture = new ResourceLocation("VoidCraft:textures/entity/herobrine.png"); // refers to:assets/yourmod/textures/entity/yourtexture.png
	protected ModelBase mainModel;

	public RenderHerobrineShadow(RenderManager manager, ModelBase par1ModelBase) {
		super(manager);
		mainModel = par1ModelBase;
	}

	/**
	 * Allows the render to do state modifications necessary before the model is rendered.
	 */
	protected void preRenderCallback(T entitylivingbaseIn, float partialTickTime) {

	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1, double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(T entity, double x, double y, double z, float yaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		float f = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
		float f1 = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
		float f2 = f1 - f;
		float f7 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
        GlStateManager.translate((float)x, (float)y, (float)z);
		float f8 = this.handleRotationFloat(entity, partialTicks);
		float f4 = this.prepareScale(entity, partialTicks);
		float f5 = 0.0F;
		float f6 = 0.0F;
        GlStateManager.enableAlpha();
        this.mainModel.setRotationAngles(f6, f5, f8, f2, f7, f4, entity);
		GlStateManager.color(0.0f, 0.0f, 0.0f, 1.0f);
        this.renderManager.renderEngine.bindTexture(Herobrine_Texture);
		mainModel.render(entity, f6, f5, f8, f2, f7, f4);
        GlStateManager.depthMask(true);
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.enableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.enableCull();
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

	public float prepareScale(T entity, float partialTicks) {
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		this.preRenderCallback(entity, partialTicks);
		float f = 0.0625F;
		GlStateManager.translate(0.0F, -1.501F, 0.0F);
		return 0.0625F;
	}

	/**
	 * Defines what float the third param in setRotationAngles of ModelBase is
	 */
	protected float handleRotationFloat(T entity, float partialTicks) {
		return (float) entity.ticksExisted + partialTicks;
	}

	/**
	 * Returns a rotation angle that is inbetween two other rotation angles. par1 and par2 are the angles between which to interpolate, par3 is probably a float between 0.0 and 1.0 that tells us where "between" the two angles we are. Example: par1 = 30, par2 = 50, par3 = 0.5, then return = 40
	 */
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

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return Herobrine_Texture;
	}

}