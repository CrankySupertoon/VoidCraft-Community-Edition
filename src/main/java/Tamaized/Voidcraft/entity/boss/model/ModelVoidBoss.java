package Tamaized.Voidcraft.entity.boss.model;

import Tamaized.Voidcraft.entity.EntityVoidNPC;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.EntityTwinsXia;
import Tamaized.Voidcraft.entity.client.animation.AnimatableModel.AnimatableModelArms;
import Tamaized.Voidcraft.events.client.DebugEvent;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelVoidBoss<T extends EntityVoidNPC> extends AnimatableModelArms {

	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;

	public ModelBiped.ArmPose leftArmPose;
	public ModelBiped.ArmPose rightArmPose;

	public ModelVoidBoss() {
		leftArmPose = ModelBiped.ArmPose.EMPTY;
		rightArmPose = ModelBiped.ArmPose.EMPTY;

		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 0F, 0F);
		head.setTextureSize(textureWidth, textureHeight);
		setRotation(head, 0F, 0F, 0F);

		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4F, 0F, -2F, 8, 12, 4);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(textureWidth, textureHeight);
		setRotation(body, 0F, 0F, 0F);

		rightarm = new ModelRenderer(this, 40, 16);
		rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
		rightarm.setRotationPoint(-5F, 2F, 0F);
		rightarm.setTextureSize(textureWidth, textureHeight);
		setRotation(rightarm, 0F, 0F, 0F);

		leftarm = new ModelRenderer(this, 40, 16);
		leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
		leftarm.setRotationPoint(5F, 2F, 0F);
		leftarm.setTextureSize(textureWidth, textureHeight);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);

		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		rightleg.setRotationPoint(-2F, 12F, 0F);
		rightleg.setTextureSize(textureWidth, textureHeight);
		setRotation(rightleg, 0F, 0F, 0F);

		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		leftleg.setRotationPoint(2F, 12F, 0F);
		leftleg.setTextureSize(textureWidth, textureHeight);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0F, 0F);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		if (!(entity instanceof EntityTwinsXia) || !((EntityTwinsXia) entity).isFrozen()) {
			setRotationAngles(f, f1, f2, f3, f4, f5, entity);
			((EntityVoidNPC) entity).renderAnimation(this);
		}
		head.render(f5);
		body.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float f1, float f2, float partialTickTime) {
		// setAnimations((EntityBossXia) entitylivingbaseIn, partialTickTime);
	}

	public void setAnimations(float leftArmPitch, float rightArmPitch, float leftArmYaw, float rightArmYaw) {
		setRotation(leftarm, Math.toRadians(leftArmPitch), Math.toRadians(leftArmYaw), 0.0F);
		setRotation(rightarm, Math.toRadians(rightArmPitch), Math.toRadians(rightArmYaw), 0.0F);
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		if (entity instanceof EntityTwinsXia && ((EntityTwinsXia) entity).isFrozen()) return;
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);

		head.rotateAngleX = headPitch / (180F / (float) Math.PI);
		head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);

		body.rotateAngleY = 0.0F;
		rightarm.rotationPointZ = 0.0F;
		rightarm.rotationPointX = -5.0F;
		leftarm.rotationPointZ = 0.0F;
		leftarm.rotationPointX = 5.0F;
		float f = 1.0F;

		rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
		leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
		rightarm.rotateAngleZ = 0.0F;
		leftarm.rotateAngleZ = 0.0F;
		rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
		leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
		rightleg.rotateAngleY = 0.0F;
		leftleg.rotateAngleY = 0.0F;
		rightleg.rotateAngleZ = 0.0F;
		leftleg.rotateAngleZ = 0.0F;

		rightarm.rotateAngleY = 0.0F;
		rightarm.rotateAngleZ = 0.0F;

		switch (leftArmPose) {
			case EMPTY:
				leftarm.rotateAngleY = 0.0F;
				break;
			case BLOCK:
				leftarm.rotateAngleX = leftarm.rotateAngleX * 0.5F - 0.9424779F;
				leftarm.rotateAngleY = 0.5235988F;
				break;
			case ITEM:
				leftarm.rotateAngleX = leftarm.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
				leftarm.rotateAngleY = 0.0F;
			default:
				break;
		}

		switch (rightArmPose) {
			case EMPTY:
				rightarm.rotateAngleY = 0.0F;
				break;
			case BLOCK:
				rightarm.rotateAngleX = rightarm.rotateAngleX * 0.5F - 0.9424779F;
				rightarm.rotateAngleY = -0.5235988F;
				break;
			case ITEM:
				rightarm.rotateAngleX = rightarm.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
				rightarm.rotateAngleY = 0.0F;
			default:
				break;
		}

		if (swingProgress > 0.0F) {
			EnumHandSide enumhandside = getMainHand(entity);
			ModelRenderer modelrenderer = getArmForSide(enumhandside);
			float f1 = swingProgress;
			body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;

			if (enumhandside == EnumHandSide.LEFT) {
				body.rotateAngleY *= -1.0F;
			}

			rightarm.rotationPointZ = MathHelper.sin(body.rotateAngleY) * 5.0F;
			rightarm.rotationPointX = -MathHelper.cos(body.rotateAngleY) * 5.0F;
			leftarm.rotationPointZ = -MathHelper.sin(body.rotateAngleY) * 5.0F;
			leftarm.rotationPointX = MathHelper.cos(body.rotateAngleY) * 5.0F;
			rightarm.rotateAngleY += body.rotateAngleY;
			leftarm.rotateAngleY += body.rotateAngleY;
			leftarm.rotateAngleX += body.rotateAngleY;

			f1 = 1.0F - swingProgress;
			f1 = f1 * f1;
			f1 = f1 * f1;
			f1 = 1.0F - f1;
			float f2 = MathHelper.sin(f1 * (float) Math.PI);
			float f3 = MathHelper.sin(swingProgress * (float) Math.PI) * -(head.rotateAngleX - 0.7F) * 0.75F;
			modelrenderer.rotateAngleX = (float) ((double) modelrenderer.rotateAngleX - ((double) f2 * 1.2D + (double) f3));
			modelrenderer.rotateAngleY += body.rotateAngleY * 2.0F;
			modelrenderer.rotateAngleZ += MathHelper.sin(swingProgress * (float) Math.PI) * -0.4F;
		}

		body.rotateAngleX = 0.0F;
		rightleg.rotationPointZ = 0.1F;
		leftleg.rotationPointZ = 0.1F;
		rightleg.rotationPointY = 12.0F;
		leftleg.rotationPointY = 12.0F;
		head.rotationPointY = 0.0F;

		rightarm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		leftarm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		rightarm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		leftarm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
	}

	protected ModelRenderer getArmForSide(EnumHandSide side) {
		return side == EnumHandSide.LEFT ? leftarm : rightarm;
	}

	public void postRenderArm(float scale, EnumHandSide side) {
		this.getArmForSide(side).postRender(scale);
	}

	protected EnumHandSide getMainHand(Entity entityIn) {
		return entityIn instanceof EntityLivingBase ? ((EntityLivingBase) entityIn).getPrimaryHand() : EnumHandSide.RIGHT;
	}
}
