package Tamaized.Voidcraft.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityBeaconRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import Tamaized.TamModized.particles.TamParticle;

public class XiaLaser extends TamParticle {

	private final int entityID;
	private final int yaw;
	private final int pitch;
	private final float[] colors;

	public XiaLaser(World world, Vec3d pos, int id, int yaw, int pitch, float[] colors) {
		super(world, pos, null);
		entityID = id;
		this.yaw = yaw;
		this.pitch = pitch;
		this.colors = colors;
		particleMaxAge = 20*3;
		isExpired = false;
	}

	@Override
	public boolean render(VertexBuffer worldRenderer, Entity entity, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		Entity e = entity.world.getEntityByID(entityID);
		if (e == null) return false;
		GlStateManager.alphaFunc(516, 0.1F);
		GlStateManager.disableFog();
		Minecraft.getMinecraft().renderEngine.bindTexture(TileEntityBeaconRenderer.TEXTURE_BEACON_BEAM);
		double distX = entity.posX - e.posX;
		double distY = entity.posY - e.posY;
		double distZ = entity.posZ - e.posZ;
		GlStateManager.pushMatrix();
		GlStateManager.translate(-distX, -distY, -distZ);
		GlStateManager.translate(-0.5, 0, 0.5);
		for (int i = 0; i < 20; i++) {
			renderBeamSegment(yaw, pitch, 0, 0, 0, partialTicks, 1, entity.world.getWorldTime(), i, 1, new float[] { 1.0f, 1.0f, 1.0f }, 0.2D, 0.25D);
		}
		GlStateManager.popMatrix();
		GlStateManager.enableFog();
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

	public XiaLaser setGravity(float gravity) {
		particleGravity = gravity;
		return this;
	}

	public XiaLaser setScale(float scale) {
		particleScale = scale;
		return this;
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param partialTicks
	 * @param shouldBeamRender
	 *            (Value > 0 = true)
	 * @param worldTime
	 * @param segment
	 * @param height
	 * @param colors
	 * @param p_188205_15_
	 *            (= 0.2D)
	 * @param p_188205_17_
	 *            (= 0.25D)
	 */
	public static void renderBeamSegment(float yaw, float pitch, double x, double y, double z, double partialTicks, double shouldBeamRender, double worldTime, int segment, int height, float[] colors, double p_188205_15_, double p_188205_17_) {
		GlStateManager.pushMatrix();
		{
			// System.out.println(rotate+" : "+x+" : "+y+" : "+z);
			// GlStateManager.rotate(90F, 1.0F, 0.0F, 0.0F);
			GlStateManager.translate(0, 1.5, 0);
			GlStateManager.rotate(-yaw + 90F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(pitch + 90F, 0.0F, 0.0F, 1.0F);
			// GlStateManager.rotate(1F, 1.0F, 0.0F, 1.0F);
			// GlStateManager.translate(-1.6, 0.0, 0);
			int i = segment + height;
			GlStateManager.glTexParameteri(3553, 10242, 10497);
			GlStateManager.glTexParameteri(3553, 10243, 10497);
			GlStateManager.disableLighting();
			GlStateManager.disableCull();
			GlStateManager.disableBlend();
			GlStateManager.depthMask(true);
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			Tessellator tessellator = Tessellator.getInstance();
			VertexBuffer vertexbuffer = tessellator.getBuffer();
			double d0 = worldTime + partialTicks;
			double d1 = height < 0 ? d0 : -d0;
			double d2 = MathHelper.frac(d1 * 0.2D - (double) MathHelper.floor(d1 * 0.1D));
			float f = colors[0];
			float f1 = colors[1];
			float f2 = colors[2];
			double d3 = d0 * 0.025D * -1.5D;
			double d4 = 0.5D + Math.cos(d3 + 2.356194490192345D) * p_188205_15_;
			double d5 = 0.5D + Math.sin(d3 + 2.356194490192345D) * p_188205_15_;
			double d6 = 0.5D + Math.cos(d3 + (Math.PI / 4D)) * p_188205_15_;
			double d7 = 0.5D + Math.sin(d3 + (Math.PI / 4D)) * p_188205_15_;
			double d8 = 0.5D + Math.cos(d3 + 3.9269908169872414D) * p_188205_15_;
			double d9 = 0.5D + Math.sin(d3 + 3.9269908169872414D) * p_188205_15_;
			double d10 = 0.5D + Math.cos(d3 + 5.497787143782138D) * p_188205_15_;
			double d11 = 0.5D + Math.sin(d3 + 5.497787143782138D) * p_188205_15_;
			double d12 = 0.0D;
			double d13 = 1.0D;
			double d14 = -1.0D + d2;
			double d15 = (double) height * shouldBeamRender * (0.5D / p_188205_15_) + d14;
			vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
			vertexbuffer.pos(x + d4, y + (double) i, z + d5).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d4, y + (double) segment, z + d5).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d6, y + (double) segment, z + d7).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d6, y + (double) i, z + d7).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d10, y + (double) i, z + d11).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d10, y + (double) segment, z + d11).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d8, y + (double) segment, z + d9).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d8, y + (double) i, z + d9).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d6, y + (double) i, z + d7).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d6, y + (double) segment, z + d7).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d10, y + (double) segment, z + d11).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d10, y + (double) i, z + d11).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d8, y + (double) i, z + d9).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d8, y + (double) segment, z + d9).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d4, y + (double) segment, z + d5).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d4, y + (double) i, z + d5).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
			tessellator.draw();
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			GlStateManager.depthMask(false);
			d3 = 0.5D - p_188205_17_;
			d4 = 0.5D - p_188205_17_;
			d5 = 0.5D + p_188205_17_;
			d6 = 0.5D - p_188205_17_;
			d7 = 0.5D - p_188205_17_;
			d8 = 0.5D + p_188205_17_;
			d9 = 0.5D + p_188205_17_;
			d10 = 0.5D + p_188205_17_;
			d11 = 0.0D;
			d12 = 1.0D;
			d13 = -1.0D + d2;
			d14 = (double) height * shouldBeamRender + d13;
			vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
			vertexbuffer.pos(x + d3, y + (double) i, z + d4).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d3, y + (double) segment, z + d4).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d5, y + (double) segment, z + d6).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d5, y + (double) i, z + d6).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d9, y + (double) i, z + d10).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d9, y + (double) segment, z + d10).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d7, y + (double) segment, z + d8).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d7, y + (double) i, z + d8).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d5, y + (double) i, z + d6).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d5, y + (double) segment, z + d6).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d9, y + (double) segment, z + d10).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d9, y + (double) i, z + d10).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d7, y + (double) i, z + d8).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d7, y + (double) segment, z + d8).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d3, y + (double) segment, z + d4).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d3, y + (double) i, z + d4).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
			tessellator.draw();
			GlStateManager.enableLighting();
			GlStateManager.enableTexture2D();
			GlStateManager.depthMask(true);
		}
		GlStateManager.popMatrix();
	}
}
