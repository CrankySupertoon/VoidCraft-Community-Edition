package Tamaized.Voidcraft.entity.boss.herobrine.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.entity.boss.herobrine.EntityBossHerobrine;
import Tamaized.Voidcraft.entity.boss.render.bossBar.RenderBossHeathBar;

@SideOnly(Side.CLIENT)
public class RenderHerobrine<T extends EntityBossHerobrine> extends RenderLiving<T>{
	
	private static final ResourceLocation Herobrine_Texture = new ResourceLocation("VoidCraft:textures/entity/Herobrine.png");  //refers to:assets/yourmod/textures/entity/yourtexture.png
	 
    public RenderHerobrine(RenderManager manager, ModelBase par1ModelBase, float par2){
        super(manager, par1ModelBase, par2);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    @Override
    public void doRender(T entity, double x, double y, double z, float yaw, float ticks){
    	super.doRender(entity, x, y, z, yaw, ticks);
		this.renderLabel(entity, x, y, z);
        RenderBossHeathBar.setCurrentBoss(entity);
    }

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return Herobrine_Texture;
	}
	
	protected void renderLabel(T yourentityLiving, double par2, double par4, double par6){
		int distanceToEntity = 32;//if you're less then 32 blocks x-y-z away from this entity,it will display the entity's name.
		this.renderLivingLabel(yourentityLiving, yourentityLiving.getDisplayName().getFormattedText(), par2, par4, par6, distanceToEntity);
		par4 += (double)((float)this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * par6);
	}
}