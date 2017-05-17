package Tamaized.Voidcraft.entity.boss.lob.render;

import Tamaized.Voidcraft.entity.boss.lob.EntityLordOfBlades;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLordOfBlades<T extends EntityLordOfBlades> extends RenderLiving<T> {

	private final ResourceLocation texture;

	public RenderLordOfBlades(RenderManager manager, ModelBase par1ModelBase, float par2, ResourceLocation resourceLocation) {
		super(manager, par1ModelBase, par2);
		texture = resourceLocation;
		addLayer(new LayerBipedArmor(this));
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float yaw, float ticks) {
		super.doRender(entity, x, y, z, yaw, ticks);
	}

	@Override
	public ModelLordOfBlades getMainModel() {
		return (ModelLordOfBlades) super.getMainModel();
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return texture;
	}

}