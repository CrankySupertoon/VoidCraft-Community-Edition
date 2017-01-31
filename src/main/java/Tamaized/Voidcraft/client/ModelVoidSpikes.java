package Tamaized.Voidcraft.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelVoidSpikes extends ModelBase {

	public ModelRenderer ArmLeft;
	public ModelRenderer LeftDown;
	public ModelRenderer LeftUp;
	public ModelRenderer ArmRight;
	public ModelRenderer RightUp;
	public ModelRenderer RightDown;
	public ModelRenderer Back;
	public ModelRenderer BackRight;
	public ModelRenderer BackLeft;

	public ModelRenderer bipedHead;

	public ModelBiped.ArmPose leftArmPose;
	public ModelBiped.ArmPose rightArmPose;
	public boolean isSneak;

	public ModelVoidSpikes(ModelBiped parent) {
		this.textureWidth = 64;
		this.textureHeight = 64;

		reload(parent);

	}

	public void reload(ModelBiped parent) {
		this.leftArmPose = ModelBiped.ArmPose.EMPTY;
		this.rightArmPose = ModelBiped.ArmPose.EMPTY;

		this.RightUp = new ModelRenderer(this, 0, 0);
		this.RightUp.setRotationPoint(-3.0F, 2.0F, 0.0F);
		this.RightUp.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
		this.setRotateAngle(RightUp, 0.0F, 0.0F, 0.5759586531581287F);

		this.ArmLeft = new ModelRenderer(this, 48, 48);
		this.ArmLeft.setRotationPoint(5.0F, 2.0F, -0.0F);
		this.ArmLeft.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
		this.setRotateAngle(ArmLeft, 0.0F, 0.0F, -0.10000736613927509F);

		this.ArmRight = new ModelRenderer(this, 40, 16);
		this.ArmRight.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.ArmRight.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.setRotateAngle(ArmRight, 0.0F, 0.0F, 0.10000736613927509F);

		this.LeftUp = new ModelRenderer(this, 0, 0);
		this.LeftUp.setRotationPoint(2.4F, 2.0F, 0.0F);
		this.LeftUp.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
		this.setRotateAngle(LeftUp, 0.0F, 0.0F, -0.5759586531581287F);

		this.RightDown = new ModelRenderer(this, 0, 0);
		this.RightDown.setRotationPoint(-3.0F, 8.0F, 0.0F);
		this.RightDown.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
		this.setRotateAngle(RightDown, 0.0F, 0.0F, 0.5759586531581287F);

		this.BackRight = new ModelRenderer(this, 0, 0);
		this.BackRight.setRotationPoint(-3.0F, 3.0F, 1.0F);
		this.BackRight.addBox(0.0F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
		this.setRotateAngle(BackRight, 0.40142572795869574F, 0.0F, 0.0F);

		this.BackLeft = new ModelRenderer(this, 0, 0);
		this.BackLeft.setRotationPoint(2.0F, 3.0F, 1.0F);
		this.BackLeft.addBox(0.0F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
		this.setRotateAngle(BackLeft, 0.40142572795869574F, 0.0F, 0.0F);

		this.LeftDown = new ModelRenderer(this, 0, 0);
		this.LeftDown.setRotationPoint(2.4F, 8.0F, 0.0F);
		this.LeftDown.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
		this.setRotateAngle(LeftDown, 0.0F, 0.0F, -0.5759586531581287F);

		this.Back = new ModelRenderer(this, 16, 16);
		this.Back.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Back.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);

		this.bipedHead = new ModelRenderer(this, 0, 0);
		this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0f);
		this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);

		this.ArmRight.addChild(this.RightUp);
		this.ArmLeft.addChild(this.LeftUp);
		this.ArmRight.addChild(this.RightDown);
		this.Back.addChild(this.BackRight);
		this.Back.addChild(this.BackLeft);
		this.ArmLeft.addChild(this.LeftDown);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.pushMatrix();
		this.ArmLeft.render(f5);
		this.ArmRight.render(f5);
		this.Back.render(f5);
		GlStateManager.popMatrix();
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;
		this.bipedHead.rotateAngleX = headPitch * 0.017453292F;

		this.Back.rotateAngleY = 0.0F;
		this.ArmRight.rotationPointZ = 0.0F;
		this.ArmRight.rotationPointX = -5.0F;
		this.ArmLeft.rotationPointZ = 0.0F;
		this.ArmLeft.rotationPointX = 5.0F;
		float f = 1.0F;

		this.ArmRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
		this.ArmLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
		this.ArmRight.rotateAngleZ = 0.0F;
		this.ArmLeft.rotateAngleZ = 0.0F;

		if (this.isRiding) {
			this.ArmRight.rotateAngleX += -((float) Math.PI / 5F);
			this.ArmLeft.rotateAngleX += -((float) Math.PI / 5F);
		}

		this.ArmRight.rotateAngleY = 0.0F;
		this.ArmRight.rotateAngleZ = 0.0F;

		switch (this.leftArmPose) {
			case EMPTY:
				this.ArmLeft.rotateAngleY = 0.0F;
				break;
			case BLOCK:
				this.ArmLeft.rotateAngleX = this.ArmLeft.rotateAngleX * 0.5F - 0.9424779F;
				this.ArmLeft.rotateAngleY = 0.5235988F;
				break;
			case ITEM:
				this.ArmLeft.rotateAngleX = this.ArmLeft.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
				this.ArmLeft.rotateAngleY = 0.0F;
			default:
				break;
		}

		switch (this.rightArmPose) {
			case EMPTY:
				this.ArmRight.rotateAngleY = 0.0F;
				break;
			case BLOCK:
				this.ArmRight.rotateAngleX = this.ArmRight.rotateAngleX * 0.5F - 0.9424779F;
				this.ArmRight.rotateAngleY = -0.5235988F;
				break;
			case ITEM:
				this.ArmRight.rotateAngleX = this.ArmRight.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
				this.ArmRight.rotateAngleY = 0.0F;
			default:
				break;
		}

		if (this.swingProgress > 0.0F) {
			EnumHandSide enumhandside = this.getMainHand(entityIn);
			ModelRenderer modelrenderer = this.getArmForSide(enumhandside);
			this.getArmForSide(enumhandside.opposite());
			float f1 = this.swingProgress;
			this.Back.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;

			if (enumhandside == EnumHandSide.LEFT) {
				this.Back.rotateAngleY *= -1.0F;
			}

			this.ArmRight.rotationPointZ = MathHelper.sin(this.Back.rotateAngleY) * 5.0F;
			this.ArmRight.rotationPointX = -MathHelper.cos(this.Back.rotateAngleY) * 5.0F;
			this.ArmLeft.rotationPointZ = -MathHelper.sin(this.Back.rotateAngleY) * 5.0F;
			this.ArmLeft.rotationPointX = MathHelper.cos(this.Back.rotateAngleY) * 5.0F;
			this.ArmRight.rotateAngleY += this.Back.rotateAngleY;
			this.ArmLeft.rotateAngleY += this.Back.rotateAngleY;
			this.ArmLeft.rotateAngleX += this.Back.rotateAngleY;
			f1 = 1.0F - this.swingProgress;
			f1 = f1 * f1;
			f1 = f1 * f1;
			f1 = 1.0F - f1;
			float f2 = MathHelper.sin(f1 * (float) Math.PI);
			float f3 = MathHelper.sin(this.swingProgress * (float) Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
			modelrenderer.rotateAngleX = (float) ((double) modelrenderer.rotateAngleX - ((double) f2 * 1.2D + (double) f3));
			modelrenderer.rotateAngleY += this.Back.rotateAngleY * 2.0F;
			modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
		}

		if (this.isSneak) {
			this.Back.rotateAngleX = 0.5F;
			this.ArmRight.rotateAngleX += 0.4F;
			this.ArmLeft.rotateAngleX += 0.4F;
			this.bipedHead.rotationPointY = 1.0F;
		} else {
			this.Back.rotateAngleX = 0.0F;
			this.bipedHead.rotationPointY = 0.0F;
		}

		this.ArmRight.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.ArmLeft.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.ArmRight.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		this.ArmLeft.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

		if (this.rightArmPose == ModelBiped.ArmPose.BOW_AND_ARROW) {
			this.ArmRight.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY;
			this.ArmLeft.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY + 0.4F;
			this.ArmRight.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
			this.ArmLeft.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
		} else if (this.leftArmPose == ModelBiped.ArmPose.BOW_AND_ARROW) {
			this.ArmRight.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY - 0.4F;
			this.ArmLeft.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY;
			this.ArmRight.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
			this.ArmLeft.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
		}
	}

	@Override
	public void setModelAttributes(ModelBase model) {
		super.setModelAttributes(model);

		if (model instanceof ModelBiped) {
			ModelBiped modelbiped = (ModelBiped) model;
			this.leftArmPose = modelbiped.leftArmPose;
			this.rightArmPose = modelbiped.rightArmPose;
			this.isSneak = modelbiped.isSneak;
		}
	}

	public void postRenderArm(float scale, EnumHandSide side) {
		this.getArmForSide(side).postRender(scale);
	}

	protected ModelRenderer getArmForSide(EnumHandSide side) {
		return side == EnumHandSide.LEFT ? this.ArmLeft : this.ArmRight;
	}

	protected EnumHandSide getMainHand(Entity entityIn) {
		return entityIn instanceof EntityLivingBase ? ((EntityLivingBase) entityIn).getPrimaryHand() : EnumHandSide.RIGHT;
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
		super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
	}

}
