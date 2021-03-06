package tamaized.dalquor.client.layer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.client.model.ModelVoidSpikes;
import tamaized.dalquor.common.capabilities.CapabilityList;

public class LayerVoidSpikes implements LayerRenderer {

	private static final ResourceLocation TEXTURE = new ResourceLocation(DalQuor.modid, "textures/entity/voidspikes.png");
	private final ModelVoidSpikes model;
	private final RenderPlayer renderer;

	public LayerVoidSpikes(RenderPlayer playerRenderer) {
		renderer = playerRenderer;
		model = new ModelVoidSpikes(playerRenderer.getMainModel());
	}

	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (!entitylivingbaseIn.hasCapability(CapabilityList.VOIDICINFUSION, null))
			return;
		GlStateManager.pushMatrix();
		{
			if (entitylivingbaseIn.isSneaking())
				GlStateManager.translate(0.0F, 0.2F, 0.0F);

			this.model.setModelAttributes(renderer.getMainModel());
			this.model.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
			this.model.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn);

			Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);

			model.render(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, scale);
		}
		GlStateManager.popMatrix();
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

}
