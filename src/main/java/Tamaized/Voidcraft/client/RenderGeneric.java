package Tamaized.Voidcraft.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderGeneric<T extends EntityLiving> extends RenderLiving<T> {

	private final ResourceLocation TEXTURE;

	public RenderGeneric(RenderManager manager, ModelBase par1ModelBase, float par2, ResourceLocation texture) {
		super(manager, par1ModelBase, par2);
		TEXTURE = texture;
	}

	public void doRender(T par1Entity, double par2, double par4, double par6, float par8, float par9) {
		GlStateManager.enableBlend();
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1, 1, 1, 1);
		super.doRender(par1Entity, par2, par4, par6, par8, par9);
		GlStateManager.disableBlend();
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return TEXTURE;
	}

}
