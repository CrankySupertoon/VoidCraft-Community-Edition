package Tamaized.Voidcraft.entity.companion.render;

import org.lwjgl.opengl.GL11;

import Tamaized.TamModized.particles.FX.ParticleFluff;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.companion.EntityCompanionFireElemental;
import Tamaized.Voidcraft.entity.companion.model.ModelFireElementalCompanion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RenderFireElementalCompanion extends RenderLiving<EntityCompanionFireElemental> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/entity/elemental_fire.png");

	public RenderFireElementalCompanion(RenderManager manager, float par2) {
		super(manager, new ModelFireElementalCompanion(), par2);
	}

	@Override
	public void doRender(EntityCompanionFireElemental entity, double x, double y, double z, float yaw, float ticks) {
		GlStateManager.pushMatrix();
		{
			// GlStateManager.rotate(180, 0, 1, 0);
			// GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			// GL11.glEnable(GL11.GL_BLEND);

			GlStateManager.pushMatrix();

			GlStateManager.matrixMode(5890);
			GlStateManager.loadIdentity();
			float f = (float) entity.ticksExisted + ticks;
			GlStateManager.rotate(90, 0, 0, 1);
			GlStateManager.translate(0.0F, f * 0.05F, f * 0.01F);
			GlStateManager.matrixMode(5888);
			// GlStateManager.rotate(-90, 1, 0, 0);

			super.doRender(entity, x, y, z, yaw, ticks);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glDisable(GL11.GL_BLEND);

			GlStateManager.matrixMode(5890);
			GlStateManager.loadIdentity();
			GlStateManager.matrixMode(5888);

			GlStateManager.popMatrix();

			GlStateManager.disableBlend();
			Minecraft mc = Minecraft.getMinecraft();
			World world = mc.world;
			if (!mc.isGamePaused() && ticks != 1.0F) {
				if (world.rand.nextInt(5) == 0) {
					Double dX = (world.rand.nextDouble() * 0.2) - 0.1D;
					Double dZ = (world.rand.nextDouble() * 0.2) - 0.1D;
					world.spawnParticle(EnumParticleTypes.FLAME, entity.posX + dX, entity.posY + 0.25, entity.posZ + dZ, 0, -0.04, 0);
				}
				if (world.rand.nextInt(3) == 0) {
					Double dX = (world.rand.nextDouble() * 0.40) - 0.2D;
					Double dY = (world.rand.nextDouble() * 0.8) - 0.4D;
					Double dZ = (world.rand.nextDouble() * 0.40) - 0.2D;
					mc.effectRenderer.addEffect(new ParticleFluff(world, new Vec3d(entity.posX + dX, entity.posY + dY + 0.6F, entity.posZ + dZ), new Vec3d(0, 0, 0), world.rand.nextInt(30), 0, world.rand.nextFloat() * 0.5F + 0.1F, (entity.getColor().getMapColor().colorValue << 8) + 0xFF));
				}
			}
		}
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCompanionFireElemental entity) {
		return TEXTURE;
	}

	@Override
	protected void preRenderCallback(EntityCompanionFireElemental entitylivingbaseIn, float partialTickTime) {
		super.preRenderCallback(entitylivingbaseIn, partialTickTime);
		EnumDyeColor c = entitylivingbaseIn.getColor();
		GlStateManager.color((c.getMapColor().colorValue >> 16 & 0xFF) / 255F, (c.getMapColor().colorValue >> 8 & 0xFF) / 255F, (c.getMapColor().colorValue & 0xFF) / 255F, 1.0F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_DST_ALPHA);
	}

	@Override
	public ModelFireElementalCompanion getMainModel() {
		return (ModelFireElementalCompanion) super.getMainModel();
	}

}