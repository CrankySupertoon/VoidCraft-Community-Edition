package Tamaized.Voidcraft.entity.boss.herobrine.extra.render.layer;

import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.render.RenderHerobrineCreeper;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerHerobrineCreeperCharge implements LayerRenderer<EntityHerobrineCreeper>
{
    private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    private final RenderHerobrineCreeper creeperRenderer;
    private final ModelCreeper creeperModel = new ModelCreeper(2.0F);

    public LayerHerobrineCreeperCharge(RenderHerobrineCreeper creeperRendererIn)
    {
        this.creeperRenderer = creeperRendererIn;
    }

    public void doRenderLayer(EntityHerobrineCreeper entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entitylivingbaseIn.getPowered())
        {
        	GlStateManager.pushAttrib();
        	GlStateManager.pushMatrix();
            boolean flag = entitylivingbaseIn.isInvisible();
            GlStateManager.depthMask(!flag);
            this.creeperRenderer.bindTexture(LIGHTNING_TEXTURE);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float f = (float)entitylivingbaseIn.ticksExisted + partialTicks;
            GlStateManager.translate(f * 0.01F, f * 0.01F, 0.0F);
            GlStateManager.matrixMode(5888);
            GlStateManager.enableBlend();
            float f1 = 0.5F;
            GlStateManager.color(1.0F, 0.1F, 0.1F, 1.0F);
            GlStateManager.disableLighting();
            //GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.creeperModel.setModelAttributes(this.creeperRenderer.getMainModel());
            this.creeperModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(!flag);
        	GlStateManager.popMatrix();
        	GlStateManager.popAttrib();
        }
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}