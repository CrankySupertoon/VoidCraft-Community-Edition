package Tamaized.Voidcraft.entity.boss.model;

import Tamaized.Voidcraft.entity.EntityVoidNPC;
import Tamaized.Voidcraft.entity.EntityVoidNPC.ArmRotation;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelVoidBossOverlay<T extends EntityVoidNPC> extends ModelBase {

	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;

	ModelRenderer overlay_head;
	ModelRenderer overlay_body;
	ModelRenderer overlay_rightarm;
	ModelRenderer overlay_leftarm;
	ModelRenderer overlay_rightleg;
	ModelRenderer overlay_leftleg;

	public ModelBiped.ArmPose leftArmPose;
	public ModelBiped.ArmPose rightArmPose;

	public ModelVoidBossOverlay() {
		leftArmPose = ModelBiped.ArmPose.EMPTY;
		rightArmPose = ModelBiped.ArmPose.EMPTY;

		textureWidth = 64;
		textureHeight = 64;

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

		overlay_head = new ModelRenderer(this, 32, 0);
		overlay_head.addBox(-4F, -8F, -4F, 8, 8, 8, 0.25F);
		overlay_head.setRotationPoint(0F, 0F, 0F);
		overlay_head.setTextureSize(textureWidth, textureHeight);
		setRotation(overlay_head, 0F, 0F, 0F);

		overlay_body = new ModelRenderer(this, 16, 32);
		overlay_body.addBox(-4F, 0F, -2F, 8, 12, 4, 0.25F);
		overlay_body.setRotationPoint(0F, 0F, 0F);
		overlay_body.setTextureSize(textureWidth, textureHeight);
		setRotation(overlay_body, 0F, 0F, 0F);

		overlay_rightarm = new ModelRenderer(this, 40, 32);
		overlay_rightarm.addBox(-3F, -2F, -2F, 4, 12, 4, 0.25F);
		overlay_rightarm.setRotationPoint(-5F, 2F, 0F);
		overlay_rightarm.setTextureSize(textureWidth, textureHeight);
		setRotation(overlay_rightarm, 0F, 0F, 0F);

		overlay_leftarm = new ModelRenderer(this, 40, 32);
		overlay_leftarm.addBox(-1F, -2F, -2F, 4, 12, 4, 0.25F);
		overlay_leftarm.setRotationPoint(5F, 2F, 0F);
		overlay_leftarm.setTextureSize(textureWidth, textureHeight);
		overlay_leftarm.mirror = true;
		setRotation(overlay_leftarm, 0F, 0F, 0F);

		overlay_rightleg = new ModelRenderer(this, 0, 32);
		overlay_rightleg.addBox(-2F, 0F, -2F, 4, 12, 4, 0.25F);
		overlay_rightleg.setRotationPoint(-2F, 12F, 0F);
		overlay_rightleg.setTextureSize(textureWidth, textureHeight);
		overlay_rightleg.mirror = true;
		setRotation(overlay_rightleg, 0F, 0F, 0F);

		overlay_leftleg = new ModelRenderer(this, 0, 32);
		overlay_leftleg.addBox(-2F, 0F, -2F, 4, 12, 4, 0.25F);
		overlay_leftleg.setRotationPoint(2F, 12F, 0F);
		overlay_leftleg.setTextureSize(textureWidth, textureHeight);
		setRotation(overlay_leftleg, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		setAnimations((T) entity, 1.0f);
		head.render(f5);
		body.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
		overlay_head.render(f5);
		overlay_body.render(f5);
		overlay_rightarm.render(f5);
		overlay_leftarm.render(f5);
		overlay_rightleg.render(f5);
		overlay_leftleg.render(f5);
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float f1, float f2, float partialTickTime) {
		// setAnimations((EntityBossXia) entitylivingbaseIn, partialTickTime);
	}

	private void setAnimations(T entity, float partialTickTime) {
		setRotation(leftarm, Math.toRadians(entity.getArmRotation(ArmRotation.LeftPitch)), Math.toRadians(entity.getArmRotation(ArmRotation.LeftYaw)), 0.0F);
		setRotation(rightarm, Math.toRadians(entity.getArmRotation(ArmRotation.RightPitch)), Math.toRadians(entity.getArmRotation(ArmRotation.RightYaw)), 0.0F);
		setRotation(overlay_leftarm, Math.toRadians(entity.getArmRotation(ArmRotation.LeftPitch)), Math.toRadians(entity.getArmRotation(ArmRotation.LeftYaw)), 0.0F);
		setRotation(overlay_rightarm, Math.toRadians(entity.getArmRotation(ArmRotation.RightPitch)), Math.toRadians(entity.getArmRotation(ArmRotation.RightYaw)), 0.0F);
	}

	private void setRotation(ModelRenderer model, double x, double y, double z) {
		model.rotateAngleX -= x;
		model.rotateAngleY -= y;
		model.rotateAngleZ -= z;
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);

		head.rotateAngleX = headPitch / (180F / (float) Math.PI);
		head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
		overlay_head.rotateAngleX = headPitch / (180F / (float) Math.PI);
		overlay_head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);

		body.rotateAngleY = 0.0F;
		rightarm.rotationPointZ = 0.0F;
		rightarm.rotationPointX = -5.0F;
		leftarm.rotationPointZ = 0.0F;
		leftarm.rotationPointX = 5.0F;
		overlay_body.rotateAngleY = 0.0F;
		overlay_rightarm.rotationPointZ = 0.0F;
		overlay_rightarm.rotationPointX = -5.0F;
		overlay_leftarm.rotationPointZ = 0.0F;
		overlay_leftarm.rotationPointX = 5.0F;
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
		overlay_rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
		overlay_leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
		overlay_rightarm.rotateAngleZ = 0.0F;
		overlay_leftarm.rotateAngleZ = 0.0F;
		overlay_rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
		overlay_leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
		overlay_rightleg.rotateAngleY = 0.0F;
		overlay_leftleg.rotateAngleY = 0.0F;
		overlay_rightleg.rotateAngleZ = 0.0F;
		overlay_leftleg.rotateAngleZ = 0.0F;

		rightarm.rotateAngleY = 0.0F;
		rightarm.rotateAngleZ = 0.0F;
		overlay_rightarm.rotateAngleY = 0.0F;
		overlay_rightarm.rotateAngleZ = 0.0F;

		switch (leftArmPose) {
			case EMPTY:
				leftarm.rotateAngleY = 0.0F;
				overlay_leftarm.rotateAngleY = 0.0F;
				break;
			case BLOCK:
				leftarm.rotateAngleX = leftarm.rotateAngleX * 0.5F - 0.9424779F;
				overlay_leftarm.rotateAngleX = overlay_leftarm.rotateAngleX * 0.5F - 0.9424779F;
				leftarm.rotateAngleY = 0.5235988F;
				overlay_leftarm.rotateAngleY = 0.5235988F;
				break;
			case ITEM:
				leftarm.rotateAngleX = leftarm.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
				overlay_leftarm.rotateAngleX = overlay_leftarm.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
				leftarm.rotateAngleY = 0.0F;
				overlay_leftarm.rotateAngleY = 0.0F;
			default:
				break;
		}

		switch (rightArmPose) {
			case EMPTY:
				rightarm.rotateAngleY = 0.0F;
				overlay_rightarm.rotateAngleY = 0.0F;
				break;
			case BLOCK:
				rightarm.rotateAngleX = rightarm.rotateAngleX * 0.5F - 0.9424779F;
				overlay_rightarm.rotateAngleX = overlay_rightarm.rotateAngleX * 0.5F - 0.9424779F;
				rightarm.rotateAngleY = -0.5235988F;
				overlay_rightarm.rotateAngleY = -0.5235988F;
				break;
			case ITEM:
				rightarm.rotateAngleX = rightarm.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
				overlay_rightarm.rotateAngleX = overlay_rightarm.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
				rightarm.rotateAngleY = 0.0F;
				overlay_rightarm.rotateAngleY = 0.0F;
			default:
				break;
		}

		if (swingProgress > 0.0F) {
			EnumHandSide enumhandside = getMainHand(entity);
			ModelRenderer modelrenderer = getArmForSide(enumhandside, false);
			ModelRenderer modelrendererOverlay = getArmForSide(enumhandside, true);
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

			overlay_rightarm.rotationPointZ = MathHelper.sin(body.rotateAngleY) * 5.0F;
			overlay_rightarm.rotationPointX = -MathHelper.cos(body.rotateAngleY) * 5.0F;
			overlay_leftarm.rotationPointZ = -MathHelper.sin(body.rotateAngleY) * 5.0F;
			overlay_leftarm.rotationPointX = MathHelper.cos(body.rotateAngleY) * 5.0F;
			overlay_rightarm.rotateAngleY += body.rotateAngleY;
			overlay_leftarm.rotateAngleY += body.rotateAngleY;
			overlay_leftarm.rotateAngleX += body.rotateAngleY;

			f1 = 1.0F - swingProgress;
			f1 = f1 * f1;
			f1 = f1 * f1;
			f1 = 1.0F - f1;
			float f2 = MathHelper.sin(f1 * (float) Math.PI);
			float f3 = MathHelper.sin(swingProgress * (float) Math.PI) * -(head.rotateAngleX - 0.7F) * 0.75F;
			modelrenderer.rotateAngleX = (float) ((double) modelrenderer.rotateAngleX - ((double) f2 * 1.2D + (double) f3));
			modelrenderer.rotateAngleY += body.rotateAngleY * 2.0F;
			modelrenderer.rotateAngleZ += MathHelper.sin(swingProgress * (float) Math.PI) * -0.4F;
			modelrendererOverlay.rotateAngleX = (float) ((double) modelrenderer.rotateAngleX - ((double) f2 * 1.2D + (double) f3));
			modelrendererOverlay.rotateAngleY += body.rotateAngleY * 2.0F;
			modelrendererOverlay.rotateAngleZ += MathHelper.sin(swingProgress * (float) Math.PI) * -0.4F;
		}

		body.rotateAngleX = 0.0F;
		rightleg.rotationPointZ = 0.1F;
		leftleg.rotationPointZ = 0.1F;
		rightleg.rotationPointY = 12.0F;
		leftleg.rotationPointY = 12.0F;
		head.rotationPointY = 0.0F;
		overlay_body.rotateAngleX = 0.0F;
		overlay_rightleg.rotationPointZ = 0.1F;
		overlay_leftleg.rotationPointZ = 0.1F;
		overlay_rightleg.rotationPointY = 12.0F;
		overlay_leftleg.rotationPointY = 12.0F;
		overlay_head.rotationPointY = 0.0F;

		rightarm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		leftarm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		rightarm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		leftarm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		overlay_rightarm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		overlay_leftarm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		overlay_rightarm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		overlay_leftarm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
	}

	protected ModelRenderer getArmForSide(EnumHandSide side, boolean overlay) {
		return side == EnumHandSide.LEFT ? overlay ? overlay_leftarm : leftarm : overlay ? overlay_rightarm : rightarm;
	}

	public void postRenderArm(float scale, EnumHandSide side) {
		this.getArmForSide(side, false).postRender(scale);
	}

	protected EnumHandSide getMainHand(Entity entityIn) {
		return entityIn instanceof EntityLivingBase ? ((EntityLivingBase) entityIn).getPrimaryHand() : EnumHandSide.RIGHT;
	}
}
