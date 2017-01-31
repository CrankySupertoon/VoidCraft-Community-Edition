package Tamaized.Voidcraft.client;

import net.minecraft.client.model.ModelBase;
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
		super.doRender(par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return TEXTURE;
	}

}
