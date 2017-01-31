package Tamaized.Voidcraft.entity.companion.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelFireElementalCompanion extends ModelBase {

	public ModelRenderer Body;
	public ModelRenderer Neck;
	public ModelRenderer Head;
	public ModelRenderer ArmL;
	public ModelRenderer ArmR;
	public ModelRenderer SpikeL;
	public ModelRenderer SpikeR;
	public ModelRenderer SpikeC;

	public ModelFireElementalCompanion() {
		textureWidth = 64;
		textureHeight = 32;

		ArmR = new ModelRenderer(this, 26, 0);
		ArmR.setRotationPoint(-2.0F, 11.2F, 0.2F);
		ArmR.addBox(-1.0F, 0.0F, -0.5F, 1, 8, 1, 0.0F);
		setRotateAngle(ArmR, 0.0F, 0.0F, 0.07853981633974483F);

		SpikeC = new ModelRenderer(this, 0, 0);
		SpikeC.setRotationPoint(-0.5F, -2.5F, -0.5F);
		SpikeC.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
		setRotateAngle(SpikeC, 0.4363323129985824F, 0.0F, 0.0F);

		Neck = new ModelRenderer(this, 0, 0);
		Neck.setRotationPoint(-0.5F, 10.2F, -0.5F);
		Neck.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);

		Head = new ModelRenderer(this, 0, 0);
		Head.setRotationPoint(0.0F, 10.0F, 0.0F);
		Head.addBox(-1.5F, -2.7F, -1.5F, 3, 3, 3, 0.0F);

		SpikeR = new ModelRenderer(this, 0, 0);
		SpikeR.setRotationPoint(1.5F, -2.0F, -0.5F);
		SpikeR.addBox(0.0F, 0.0F, -3.0F, 1, 1, 4, 0.0F);
		setRotateAngle(SpikeR, 0.0F, 3.5779249665883754F, 0.0F);

		SpikeL = new ModelRenderer(this, 0, 0);
		SpikeL.setRotationPoint(-1.5F, -2.0F, -0.5F);
		SpikeL.addBox(-1.0F, 0.0F, -3.0F, 1, 1, 4, 0.0F);
		setRotateAngle(SpikeL, 0.0F, 2.705260340591211F, 0.0F);

		ArmL = new ModelRenderer(this, 26, 0);
		ArmL.setRotationPoint(2.0F, 11.2F, 0.0F);
		ArmL.addBox(0.0F, 0.0F, -0.5F, 1, 8, 1, 0.0F);
		setRotateAngle(ArmL, 0.0F, 0.0F, -0.07853981633974483F);

		Body = new ModelRenderer(this, 0, 0);
		Body.setRotationPoint(0.0F, 11.0F, 0.0F);
		Body.addBox(-2.0F, 0.0F, -1.0F, 4, 10, 2, 0.0F);

		Head.addChild(SpikeC);
		Head.addChild(SpikeR);
		Head.addChild(SpikeL);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Neck.render(f5);
		ArmR.render(f5);
		Head.render(f5);
		ArmL.render(f5);
		Body.render(f5);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);

		Head.rotateAngleX = headPitch / (180F / (float) Math.PI);
		Head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);

		Body.rotateAngleY = 0.0F;
		ArmR.rotationPointZ = 0.0F;
		ArmR.rotationPointX = -2.0F;
		ArmL.rotationPointZ = 0.0F;
		ArmL.rotationPointX = 2.0F;
		float f = 1.0F;

		ArmR.rotateAngleX = limbSwingAmount*0.25F;//MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
		ArmL.rotateAngleX = limbSwingAmount*0.25F;//MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
		ArmR.rotateAngleZ = 0.0F;
		ArmL.rotateAngleZ = 0.0F;

		ArmR.rotateAngleY = 0.0F;
		ArmR.rotateAngleZ = 0.0F;

		if (swingProgress > 0.0F) {
			EnumHandSide enumhandside = getMainHand(entity);
			ModelRenderer modelrenderer = getArmForSide(enumhandside);
			float f1 = swingProgress;
			Body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;

			if (enumhandside == EnumHandSide.LEFT) {
				Body.rotateAngleY *= -1.0F;
			}

			ArmR.rotationPointZ = MathHelper.sin(Body.rotateAngleY) * 5.0F;
			ArmR.rotationPointX = -MathHelper.cos(Body.rotateAngleY) * 5.0F;
			ArmL.rotationPointZ = -MathHelper.sin(Body.rotateAngleY) * 5.0F;
			ArmL.rotationPointX = MathHelper.cos(Body.rotateAngleY) * 5.0F;
			ArmR.rotateAngleY += Body.rotateAngleY;
			ArmL.rotateAngleY += Body.rotateAngleY;
			ArmL.rotateAngleX += Body.rotateAngleY;

			f1 = 1.0F - swingProgress;
			f1 = f1 * f1;
			f1 = f1 * f1;
			f1 = 1.0F - f1;
			float f2 = MathHelper.sin(f1 * (float) Math.PI);
			float f3 = MathHelper.sin(swingProgress * (float) Math.PI) * -(Head.rotateAngleX - 0.7F) * 0.75F;
			modelrenderer.rotateAngleX = (float) ((double) modelrenderer.rotateAngleX - ((double) f2 * 1.2D + (double) f3));
			modelrenderer.rotateAngleY += Body.rotateAngleY * 2.0F;
			modelrenderer.rotateAngleZ += MathHelper.sin(swingProgress * (float) Math.PI) * -0.4F;
		}

		Body.rotateAngleX = 0.0F;
		Head.rotationPointY = 10.0F;

		ArmR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		ArmL.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		ArmR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		ArmL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
	}

	protected ModelRenderer getArmForSide(EnumHandSide side) {
		return side == EnumHandSide.LEFT ? ArmL : ArmR;
	}

	public void postRenderArm(float scale, EnumHandSide side) {
		getArmForSide(side).postRender(scale);
	}

	protected EnumHandSide getMainHand(Entity entityIn) {
		return entityIn instanceof EntityLivingBase ? ((EntityLivingBase) entityIn).getPrimaryHand() : EnumHandSide.RIGHT;
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
