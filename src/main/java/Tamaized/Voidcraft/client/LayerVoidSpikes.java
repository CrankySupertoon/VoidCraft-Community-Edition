package Tamaized.Voidcraft.client;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class LayerVoidSpikes implements LayerRenderer {

	private final ModelVoidSpikes model;
	private final RenderPlayer renderer;

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/entity/voidspikes.png");

	public LayerVoidSpikes(RenderPlayer playerRenderer) {
		renderer = playerRenderer;
		model = new ModelVoidSpikes(playerRenderer.getMainModel());
	}

	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (!entitylivingbaseIn.hasCapability(CapabilityList.VOIDICINFUSION, null)) return;
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		{
			IVadeMecumCapability cap = entitylivingbaseIn.getCapability(CapabilityList.VADEMECUM, null);
			float perc = Math.min(0.85F, (entitylivingbaseIn.getCapability(CapabilityList.VOIDICINFUSION, null).getInfusionPerc() + (cap != null && cap.hasPassive(IVadeMecumCapability.Passive.Flight) ? 0.5F : 0.0F)) * 0.85F);
			// DebugEvent.textL=""+Minecraft.getMinecraft().theWorld.getEntityByID(entitylivingbaseIn.getEntityId()).hasCapability(CapabilityList.VOIDICINFUSION, null);

			GlStateManager.scale(perc + 0, perc + 0, perc + 0);
			// GlStateManager.rotate(180, 1, 0, 1);
			// GlStateManager.rotate(-90, 0, 1, 0);
			// GlStateManager.scale(1, -1, 1);
			// GlStateManager.rotate(180, 0, 1, 0);

			if (entitylivingbaseIn.isSneaking()) GlStateManager.translate(0.0F, 0.2F, 0.0F);

			this.model.setModelAttributes(renderer.getMainModel());
			// this.model.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn);
			this.model.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
			this.model.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn);

			Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);

			model.render(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, scale);

			// model.reload(null);
		}
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

}
