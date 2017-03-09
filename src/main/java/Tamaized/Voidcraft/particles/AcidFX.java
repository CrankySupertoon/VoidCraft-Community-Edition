package Tamaized.Voidcraft.particles;

import org.lwjgl.opengl.GL11;

import Tamaized.TamModized.particles.TamParticle;
import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AcidFX extends TamParticle {

	private static final ResourceLocation texture = new ResourceLocation(VoidCraft.modid, "textures/particle/acidfx.png");

	public AcidFX(World par1World, double x, double y, double z) {
		super(par1World, new Vec3d(x, y, z));
		prevPosX = posX = x;
		prevPosY = posY = y;
		prevPosZ = posZ = z;

		double newrand = (float) Math.random();
		this.motionY = this.motionY * 0.009999999776482582D + 1;
		this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
		this.particleAlpha = 0.5F;
		setGravity(.005F);
		setMaxAge(50);
	}

	@Override
	public boolean render(VertexBuffer worldRenderer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		//GL11.glDepthMask(false);
		//GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		//GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
		// worldRenderer.setBrightness(getBrightnessForRender(partialTicks));
		//GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		float scale = 0.1F * particleScale;
		float x = (float) (prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
		float y = (float) (prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
		float z = (float) (prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		vertexbuffer.pos(x - rotationX * scale - rotationXY * scale, y - rotationZ * scale, z - rotationYZ * scale - rotationXZ * scale).tex(0, 0).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).endVertex();
		vertexbuffer.pos(x - rotationX * scale + rotationXY * scale, y + rotationZ * scale, z - rotationYZ * scale + rotationXZ * scale).tex(1, 0).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).endVertex();
		vertexbuffer.pos(x + rotationX * scale + rotationXY * scale, y + rotationZ * scale, z + rotationYZ * scale + rotationXZ * scale).tex(1, 1).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).endVertex();
		vertexbuffer.pos(x + rotationX * scale - rotationXY * scale, y - rotationZ * scale, z + rotationYZ * scale - rotationXZ * scale).tex(0, 1).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).endVertex();
		tessellator.draw();
		//GlStateManager.disableAlpha();
		GlStateManager.disableBlend();
		//GL11.glDisable(GL11.GL_BLEND);
		//GL11.glDepthMask(true);
		//GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		return false;
	}

	@Override
	public int getBrightnessForRender(float p_70070_1_) {
		float f1 = ((float) this.particleAge + p_70070_1_) / (float) this.particleMaxAge;

		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		if (f1 > 1.0F) {
			f1 = 1.0F;
		}

		int i = super.getBrightnessForRender(p_70070_1_);
		int j = i & 255;
		int k = i >> 16 & 255;
		j += (int) (f1 * 15.0F * 16.0F);

		if (j > 240) {
			j = 240;
		}

		return j | k << 16;
	}

	/**
	 * Gets how bright this entity is.
	 */
	public float getBrightness(float p_70013_1_) {
		float f1 = ((float) this.particleAge + p_70013_1_) / (float) this.particleMaxAge;

		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		if (f1 > 1.0F) {
			f1 = 1.0F;
		}

		float f2 = super.getBrightnessForRender(p_70013_1_);
		return f2 * f1 + (1.0F - f1);
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		this.particleScale += this.particleScale / 15;
		// this.particleAlpha = (this.particleAge / this.particleMaxAge);

		if (this.particleAge++ >= 15)// this.particleMaxAge)
		{
			this.setExpired();
		}
	}

	@Override
	public int getFXLayer() {
		return 3;
	}

	@Override
	public void setMaxAge(int maxAge) {
		particleMaxAge = maxAge;
	}

	public AcidFX setGravity(float gravity) {
		particleGravity = gravity;
		return this;
	}

	public AcidFX setScale(float scale) {
		particleScale = scale;
		return this;
	}
}
