package Tamaized.Voidcraft.entity.boss.twins.render;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderZol<T extends EntityLiving> extends RenderLiving<T> {

	private static final ResourceLocation Herobrine_Texture = new ResourceLocation(VoidCraft.modid, "textures/entity/zol.png");

	public RenderZol(RenderManager manager, ModelBase par1ModelBase, float par2) {
		super(manager, par1ModelBase, par2);
	}

	@Override
	public void doRender(T par1Entity, double par2, double par4, double par6, float par8, float par9) {
		super.doRender(par1Entity, par2, par4, par6, par8, par9);
		this.renderLabel(par1Entity, par2, par4, par6);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return Herobrine_Texture;
	}

	protected void renderLabel(T yourentityLiving, double par2, double par4, double par6) {
		int distanceToEntity = 32;// if you're less then 32 blocks x-y-z away from this entity,it will display the entity's name.
		this.renderLivingLabel(yourentityLiving, yourentityLiving.getDisplayName().getFormattedText(), par2, par4, par6, distanceToEntity);
		par4 += (double) ((float) this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * par6);
	}

	public void passSpecialRender(T par1EntityLiving, double par2, double par4, double par6) {
		this.renderLabel(par1EntityLiving, par2, par4, par6);
	}
}