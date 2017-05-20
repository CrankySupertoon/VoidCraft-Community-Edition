package Tamaized.Voidcraft.entity.boss.xia.model;

import Tamaized.Voidcraft.entity.EntityVoidNPC;
import Tamaized.Voidcraft.entity.client.animation.AnimatableModel.AnimatableModelArms;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelXia2<T extends EntityVoidNPC> extends AnimatableModelArms {

	public ModelRenderer Head;
	public ModelRenderer Body;
	public ModelRenderer LeftArm;
	public ModelRenderer RightArm;

	public ModelBiped.ArmPose leftArmPose;
	public ModelBiped.ArmPose rightArmPose;

	public ModelXia2() {
		leftArmPose = ModelBiped.ArmPose.EMPTY;
		rightArmPose = ModelBiped.ArmPose.EMPTY;

		this.textureWidth = 64;
		this.textureHeight = 32;

		this.LeftArm = new ModelRenderer(this, 16, 16);
		this.LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.LeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
		this.setRotateAngle(LeftArm, 0.0F, 0.0F, -0.10000736613927509F);

		this.Head = new ModelRenderer(this, 0, 0);
		this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);

		this.RightArm = new ModelRenderer(this, 0, 16);
		this.RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.RightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
		this.setRotateAngle(RightArm, 0.0F, 0.0F, 0.10000736613927509F);

		this.Body = new ModelRenderer(this, 32, 0);
		this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		((EntityVoidNPC) entity).renderAnimation(this);
		this.LeftArm.render(f5);
		this.Head.render(f5);
		this.RightArm.render(f5);
		this.Body.render(f5);
	}

	public void setAnimations(float leftArmPitch, float rightArmPitch, float leftArmYaw, float rightArmYaw) {
		setRotation(LeftArm, Math.toRadians(leftArmPitch), Math.toRadians(leftArmYaw), 0.0F);
		setRotation(RightArm, Math.toRadians(rightArmPitch), Math.toRadians(rightArmYaw), 0.0F);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);

		Head.rotateAngleX = headPitch / (180F / (float) Math.PI);
		Head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);

		Body.rotateAngleY = 0.0F;
		RightArm.rotationPointZ = 0.0F;
		RightArm.rotationPointX = -5.0F;
		LeftArm.rotationPointZ = 0.0F;
		LeftArm.rotationPointX = 5.0F;
		float f = 1.0F;

		RightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
		LeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
		RightArm.rotateAngleZ = 0.0F;
		LeftArm.rotateAngleZ = 0.0F;

		RightArm.rotateAngleY = 0.0F;
		RightArm.rotateAngleZ = 0.0F;

		switch (leftArmPose) {
			case EMPTY:
				LeftArm.rotateAngleY = 0.0F;
				break;
			case BLOCK:
				LeftArm.rotateAngleX = LeftArm.rotateAngleX * 0.5F - 0.9424779F;
				LeftArm.rotateAngleY = 0.5235988F;
				break;
			case ITEM:
				LeftArm.rotateAngleX = LeftArm.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
				LeftArm.rotateAngleY = 0.0F;
			default:
				break;
		}

		switch (rightArmPose) {
			case EMPTY:
				RightArm.rotateAngleY = 0.0F;
				break;
			case BLOCK:
				RightArm.rotateAngleX = RightArm.rotateAngleX * 0.5F - 0.9424779F;
				RightArm.rotateAngleY = -0.5235988F;
				break;
			case ITEM:
				RightArm.rotateAngleX = RightArm.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
				RightArm.rotateAngleY = 0.0F;
			default:
				break;
		}

		if (swingProgress > 0.0F) {
			EnumHandSide enumhandside = getMainHand(entity);
			ModelRenderer modelrenderer = getArmForSide(enumhandside);
			float f1 = swingProgress;
			Body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;

			if (enumhandside == EnumHandSide.LEFT) {
				Body.rotateAngleY *= -1.0F;
			}

			RightArm.rotationPointZ = MathHelper.sin(Body.rotateAngleY) * 5.0F;
			RightArm.rotationPointX = -MathHelper.cos(Body.rotateAngleY) * 5.0F;
			LeftArm.rotationPointZ = -MathHelper.sin(Body.rotateAngleY) * 5.0F;
			LeftArm.rotationPointX = MathHelper.cos(Body.rotateAngleY) * 5.0F;
			RightArm.rotateAngleY += Body.rotateAngleY;
			LeftArm.rotateAngleY += Body.rotateAngleY;
			LeftArm.rotateAngleX += Body.rotateAngleY;

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
		Head.rotationPointY = 0.0F;

		RightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		LeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		RightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		LeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
	}

	protected ModelRenderer getArmForSide(EnumHandSide side) {
		return side == EnumHandSide.LEFT ? LeftArm : RightArm;
	}

	public void postRenderArm(float scale, EnumHandSide side) {
		this.getArmForSide(side).postRender(scale);
	}

	protected EnumHandSide getMainHand(Entity entityIn) {
		return entityIn instanceof EntityLivingBase ? ((EntityLivingBase) entityIn).getPrimaryHand() : EnumHandSide.RIGHT;
	}
}
