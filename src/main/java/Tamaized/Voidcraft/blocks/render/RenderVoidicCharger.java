package Tamaized.Voidcraft.blocks.render;

import org.lwjgl.opengl.GL11;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.blocks.model.ModelVoidicCharger;
import Tamaized.Voidcraft.client.ClientRenderTicker;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicCharger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderVoidicCharger extends TileEntitySpecialRenderer<TileEntityVoidicCharger> {
	
	private static final ResourceLocation texture = new ResourceLocation(VoidCraft.modid, "textures/models/blocks/voidiccharger.png");
	
	private final ModelVoidicCharger model;
	
	public RenderVoidicCharger(){
		this.model = new ModelVoidicCharger();
	}
	
	@Override
	public void renderTileEntityAt(TileEntityVoidicCharger te, double x, double y, double z, float partialTicks, int destroyStage){
        GL11.glPushMatrix();
        GlStateManager.disableLighting();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        ResourceLocation textures = (texture); 
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        float f = (float)ClientRenderTicker.tick + partialTicks;
        this.model.render((Entity)null, 0.0F, f*1.0f, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
        GlStateManager.enableLighting();
        GL11.glPopMatrix();
	}
	
}
