package Tamaized.Voidcraft.entity.nonliving.render;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHook{/* extends Render {

	public RenderHook(RenderManager renderManager) {
		super(renderManager);
	}

	private static final ResourceLocation hookTexture = new ResourceLocation("VoidCraft:textures/entity/voidHook.png");

	public void renderArrow(EntityHookShot hook, double par2, double par4, double par6, float par8, float par9) {
		this.bindEntityTexture(hook);
		GL11.glPushMatrix();

		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer worldrender = tessellator.getBuffer();

		if (hook.shootingEntity != null) {
			float f9 = hook.shootingEntity.getSwingProgress(par9);
			float f10 = MathHelper.sin(MathHelper.sqrt_float(f9) * (float) Math.PI);
			Vec3d vec3 = new Vec3d(-0.5D, 0.03D, 0.8D);
			vec3.rotatePitch(-(hook.shootingEntity.prevRotationPitch + (hook.shootingEntity.rotationPitch - hook.shootingEntity.prevRotationPitch) * par9) * (float) Math.PI / 180.0F);
			vec3.rotateYaw(-(hook.shootingEntity.prevRotationYaw + (hook.shootingEntity.rotationYaw - hook.shootingEntity.prevRotationYaw) * par9) * (float) Math.PI / 180.0F);
			vec3.rotateYaw(f10 * 0.5F);
			vec3.rotatePitch(-f10 * 0.7F);
			double d3 = hook.shootingEntity.prevPosX + (hook.shootingEntity.posX - hook.shootingEntity.prevPosX) * (double) par9 + vec3.xCoord;
			double d4 = hook.shootingEntity.prevPosY + (hook.shootingEntity.posY - hook.shootingEntity.prevPosY) * (double) par9 + vec3.yCoord;
			double d5 = hook.shootingEntity.prevPosZ + (hook.shootingEntity.posZ - hook.shootingEntity.prevPosZ) * (double) par9 + vec3.zCoord;
			double d6 = hook.shootingEntity == Minecraft.getMinecraft().thePlayer ? 0.0D : (double) hook.shootingEntity.getEyeHeight();

			if (this.renderManager.options.thirdPersonView > 0 || hook.shootingEntity != Minecraft.getMinecraft().thePlayer) {
				float f11 = (hook.shootingEntity.prevRenderYawOffset + (hook.shootingEntity.renderYawOffset - hook.shootingEntity.prevRenderYawOffset) * par9) * (float) Math.PI / 180.0F;
				double d7 = (double) MathHelper.sin(f11);
				double d9 = (double) MathHelper.cos(f11);
				d3 = hook.shootingEntity.prevPosX + (hook.shootingEntity.posX - hook.shootingEntity.prevPosX) * (double) par9 - d9 * 0.35D - d7 * 0.85D;
				d4 = hook.shootingEntity.prevPosY + d6 + (hook.shootingEntity.posY - hook.shootingEntity.prevPosY) * (double) par9 - 0.45D;
				d5 = hook.shootingEntity.prevPosZ + (hook.shootingEntity.posZ - hook.shootingEntity.prevPosZ) * (double) par9 - d7 * 0.35D + d9 * 0.85D;
			}

			double d14 = hook.prevPosX + (hook.posX - hook.prevPosX) * (double) par9;
			double d8 = hook.prevPosY + (hook.posY - hook.prevPosY) * (double) par9 + 0.25D;
			double d10 = hook.prevPosZ + (hook.posZ - hook.prevPosZ) * (double) par9;
			double d11 = (double) ((float) (d3 - d14));
			double d12 = (double) ((float) (d4 - d8));
			double d13 = (double) ((float) (d5 - d10));
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_LIGHTING);
			worldrender.begin(3, DefaultVertexFormats.POSITION_COLOR);
			// worldrender.setColorOpaque_I(0);
			byte b2 = 16;

			for (int i = 0; i <= b2; ++i) {
				float f12 = (float) i / (float) b2;
				worldrender.pos(par2 + d11 * (double) f12, par4 + d12 * (double) (f12 * f12 + f12) * 0.5D + 0.25D, par6 + d13 * (double) f12).endVertex();
			}

			tessellator.draw();
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}
		/*
		 * GL11.glPushMatrix(); double pX = hook.lineCoord[0][0]; double pY = hook.lineCoord[0][1]; double pZ = hook.lineCoord[0][2]; double hX = hook.lineCoord[1][0]; double hY = hook.lineCoord[1][1]; double hZ = hook.lineCoord[1][2]; GL11.glTranslated(-pX, -pY, -pZ); GL11.glColor3ub((byte)0,(byte)0,(byte)0); GL11.glBegin(GL11.GL_LINES); GL11.glVertex3d(hX + 0.4f, hY, hZ + 0.4f); GL11.glVertex3d(hX - 0.4f, hY, hZ - 0.4f); //GL11.glVertex3d(hX + 0.4f, hY, hZ - 0.4f); //GL11.glVertex3d(hX - 0.4f, hY, hZ + 0.4f); GL11.glEnd(); GL11.glPopMatrix();
		 *//*
		GL11.glTranslatef((float) par2, (float) par4, (float) par6);
		GL11.glRotatef(hook.prevRotationYaw + (hook.rotationYaw - hook.prevRotationYaw) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(hook.prevRotationPitch + (hook.rotationPitch - hook.prevRotationPitch) * par9, 0.0F, 0.0F, 1.0F);
		byte b0 = 0;
		float f2 = 0.0F;
		float f3 = 0.5F;
		float f4 = (float) (0 + b0 * 10) / 32.0F;
		float f5 = (float) (5 + b0 * 10) / 32.0F;
		float f6 = 0.0F;
		float f7 = 0.15625F;
		float f8 = (float) (5 + b0 * 10) / 32.0F;
		float f9 = (float) (10 + b0 * 10) / 32.0F;
		float f10 = 0.05625F;
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		float f11 = (float) 0 - par9;

		if (f11 > 0.0F) {
			float f12 = -MathHelper.sin(f11 * 3.0F) * f11;
			GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
		}

		GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
		GL11.glScalef(f10, f10, f10);
		GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
		GL11.glNormal3f(f10, 0.0F, 0.0F);
		worldrender.begin(7, DefaultVertexFormats.ITEM);
		worldrender.pos(-7.0D, -2.0D, -2.0D).tex((double) f6, (double) f8).endVertex();
		worldrender.pos(-7.0D, -2.0D, 2.0D).tex((double) f7, (double) f8).endVertex();
		worldrender.pos(-7.0D, 2.0D, 2.0D).tex((double) f7, (double) f9).endVertex();
		worldrender.pos(-7.0D, 2.0D, -2.0D).tex((double) f6, (double) f9).endVertex();
		tessellator.draw();
		GL11.glNormal3f(-f10, 0.0F, 0.0F);
		worldrender.begin(7, DefaultVertexFormats.ITEM);
		worldrender.pos(-7.0D, 2.0D, -2.0D).tex((double) f6, (double) f8).endVertex();
		worldrender.pos(-7.0D, 2.0D, 2.0D).tex((double) f7, (double) f8).endVertex();
		worldrender.pos(-7.0D, -2.0D, 2.0D).tex((double) f7, (double) f9).endVertex();
		worldrender.pos(-7.0D, -2.0D, -2.0D).tex((double) f6, (double) f9).endVertex();
		tessellator.draw();

		for (int i = 0; i < 4; ++i) {
			GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
			GL11.glNormal3f(0.0F, 0.0F, f10);
			worldrender.begin(7, DefaultVertexFormats.ITEM);
			worldrender.pos(-8.0D, -2.0D, 0.0D).tex((double) f2, (double) f4).endVertex();
			worldrender.pos(8.0D, -2.0D, 0.0D).tex((double) f3, (double) f4).endVertex();
			worldrender.pos(8.0D, 2.0D, 0.0D).tex((double) f3, (double) f5).endVertex();
			worldrender.pos(-8.0D, 2.0D, 0.0D).tex((double) f2, (double) f5).endVertex();
			tessellator.draw();
		}

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1, double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 *//*
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.renderArrow((EntityHookShot) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return hookTexture;
	}
*/
}
