package tamaized.dalquor.client.entity.boss.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.client.entity.boss.model.ModelVoidBoss;
import tamaized.dalquor.common.entity.EntityVoidNPC;
import tamaized.dalquor.common.entity.boss.twins.EntityBossDol;

@SideOnly(Side.CLIENT)
public class RenderDol<T extends EntityVoidNPC> extends RenderLiving<T> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(DalQuor.modid, "textures/entity/dol.png");

	public RenderDol(RenderManager manager, float shadow) {
		super(manager, new ModelVoidBoss<EntityBossDol>(), shadow);
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float yaw, float ticks) {
		super.doRender(entity, x, y, z, yaw, ticks);
		this.renderLabel(entity, x, y, z);
	}

	protected void renderLabel(T yourentityLiving, double par2, double par4, double par6) {
		int distanceToEntity = 32;
		this.renderLivingLabel(yourentityLiving, yourentityLiving.getDisplayName().getFormattedText(), par2, par4, par6, distanceToEntity);
		par4 += (double) ((float) this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * par6);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return TEXTURE;
	}

	@Override
	public ModelVoidBoss getMainModel() {
		return (ModelVoidBoss) super.getMainModel();
	}
}