package Tamaized.Voidcraft.entity.mob.dalquor.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHashalaq extends ModelBase {

	public ModelRenderer base;
	public ModelRenderer armRight;
	public ModelRenderer legRight;
	public ModelRenderer head;
	public ModelRenderer body;
	public ModelRenderer legLeft;
	public ModelRenderer armLeft;
	public ModelRenderer armRightSleeve;
	public ModelRenderer hood;
	public ModelRenderer cloak;
	public ModelRenderer armLeftSleeve;

	public ModelHashalaq() {
		textureWidth = 64;
		textureHeight = 64;

		armLeft = new ModelRenderer(this, 0, 48);
		armLeft.setRotationPoint(5.6F, 2.0F, -0.0F);
		armLeft.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		setRotateAngle(armLeft, -0.2617993877991494F, 0.0F, -0.10000736613927509F);

		head = new ModelRenderer(this, 0, 0);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addBox(-4.5F, -8.5F, 1.0F, 9, 9, 2, 0.0F);
		hood = new ModelRenderer(this, 28, 46);
		hood.setRotationPoint(-0.5F, -0.5F, -0.5F);
		hood.addBox(-4.0F, -8.0F, -4.0F, 9, 9, 9, 0.0F);

		armRight = new ModelRenderer(this, 0, 48);
		armRight.setRotationPoint(-5.6F, 2.0F, 0.0F);
		armRight.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		setRotateAngle(armRight, -0.8377580409572781F, 0.0F, 0.10000736613927509F);

		cloak = new ModelRenderer(this, 21, 0);
		cloak.setRotationPoint(-0.5F, 0.0F, -0.5F);
		cloak.addBox(-4.0F, 0.0F, -2.0F, 9, 23, 5, 0.0F);

		legLeft = new ModelRenderer(this, 0, 26);
		legLeft.setRotationPoint(1.9F, 12.0F, 0.1F);
		legLeft.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);

		armLeftSleeve = new ModelRenderer(this, 44, 28);
		armLeftSleeve.setRotationPoint(-0.1F, 0.0F, -0.5F);
		armLeftSleeve.addBox(-1.0F, -2.0F, -2.0F, 5, 13, 5, 0.0F);

		armRightSleeve = new ModelRenderer(this, 44, 28);
		armRightSleeve.setRotationPoint(-0.9F, 0.0F, -0.5F);
		armRightSleeve.addBox(-3.0F, -2.0F, -2.0F, 5, 13, 5, 0.0F);

		base = new ModelRenderer(this, 50, 0);
		base.setRotationPoint(0.0F, -3.0F, 0.0F);
		base.addBox(-3.0F, 0.5F, -1.0F, 6, 6, 1, 0.0F);

		legRight = new ModelRenderer(this, 0, 26);
		legRight.setRotationPoint(-1.9F, 12.0F, 0.1F);
		legRight.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);

		body = new ModelRenderer(this, 0, 48);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);

		base.addChild(armLeft);
		base.addChild(head);
		head.addChild(hood);
		base.addChild(armRight);
		body.addChild(cloak);
		base.addChild(legLeft);
		armLeft.addChild(armLeftSleeve);
		armRight.addChild(armRightSleeve);
		base.addChild(legRight);
		base.addChild(body);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		base.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float limbSwingAmount, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, limbSwingAmount, f2, f3, f4, f5, entity);

		head.rotateAngleX = f4 / (180F / (float) Math.PI);
		head.rotateAngleY = f3 / (180F / (float) Math.PI);

		float maxSwing = 2.5F;

		armLeft.rotateAngleX = (-limbSwingAmount * maxSwing) - 0.2F;
		armRight.rotateAngleX = (-limbSwingAmount * maxSwing) - 0.5F;

		legLeft.rotateAngleX = legRight.rotateAngleX = limbSwingAmount * 0.5F;
		cloak.rotateAngleX = limbSwingAmount * 0.2F;

	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}
