package Tamaized.Voidcraft.entity.mob.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelLich extends ModelBase {

	public ModelRenderer rightArm;
	public ModelRenderer rightLeg;
	public ModelRenderer head;
	public ModelRenderer body;
	public ModelRenderer leftArm;
	public ModelRenderer leftLeg;
	public ModelRenderer helm;
	public ModelRenderer cloak;
	public ModelRenderer rod;
	public ModelRenderer staffEnd1;
	public ModelRenderer staffEnd2;
	public ModelRenderer staffEnd3;
	public ModelRenderer staffEnd4;

	private int movement = 0;
	private boolean moveup;

	public ModelLich() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.staffEnd2 = new ModelRenderer(this, 0, 41);
		this.staffEnd2.setRotationPoint(0.0F, -10.5F, 0.0F);
		this.staffEnd2.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(staffEnd2, 2.356194490192345F, -0.7853981633974483F, 0.0F);
		this.leftLeg = new ModelRenderer(this, 0, 16);
		this.leftLeg.setRotationPoint(2.0F, 6.200000000000017F, 0.1F);
		this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
		this.leftArm = new ModelRenderer(this, 40, 16);
		this.leftArm.setRotationPoint(4.999999999999999F, -3.800000000000002F, 0.0F);
		this.leftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
		this.setRotateAngle(leftArm, 0.0F, 0.10000736613927509F, -0.10000736613927509F);
		this.staffEnd4 = new ModelRenderer(this, 0, 41);
		this.staffEnd4.setRotationPoint(0.0F, -10.5F, 0.0F);
		this.staffEnd4.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(staffEnd4, 2.356194490192345F, -2.356194490192345F, 0.0F);
		this.cloak = new ModelRenderer(this, 7, 33);
		this.cloak.setRotationPoint(0.0F, -6.0F, 0.0F);
		this.cloak.addBox(-4.5F, 0.0F, -4.5F, 9, 20, 9, 0.0F);
		this.staffEnd1 = new ModelRenderer(this, 0, 41);
		this.staffEnd1.setRotationPoint(0.0F, -10.5F, 0.0F);
		this.staffEnd1.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(staffEnd1, 2.356194490192345F, 0.7853981633974483F, 0.0F);
		this.helm = new ModelRenderer(this, 32, 0);
		this.helm.setRotationPoint(2.7755575615628914E-17F, -5.799999999999999F, 0.0F);
		this.helm.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(2.7755575615628914E-17F, -5.799999999999999F, 0.0F);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.rightLeg = new ModelRenderer(this, 0, 16);
		this.rightLeg.setRotationPoint(-1.9999999999999996F, 6.200000000000017F, 0.1F);
		this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
		this.rightArm = new ModelRenderer(this, 40, 16);
		this.rightArm.setRotationPoint(-5.000000000000001F, -3.800000000000002F, 0.0F);
		this.rightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
		this.setRotateAngle(rightArm, -0.40980330836826856F, 0.36425021489121656F, 0.10000736613927509F);
		this.body = new ModelRenderer(this, 16, 16);
		this.body.setRotationPoint(2.7755575615628914E-17F, -5.799999999999999F, 0.0F);
		this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		this.rod = new ModelRenderer(this, 0, 30);
		this.rod.setRotationPoint(0.0F, 9.0F, -1.5F);
		this.rod.addBox(-0.5F, -11.0F, -0.5F, 1, 25, 1, 0.0F);
		this.setRotateAngle(rod, 0.5174901232163187F, -0.29461157773664276F, -0.15062191444711065F);
		this.staffEnd3 = new ModelRenderer(this, 0, 41);
		this.staffEnd3.setRotationPoint(0.0F, -10.5F, 0.0F);
		this.staffEnd3.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(staffEnd3, 2.356194490192345F, 2.356194490192345F, 0.0F);
		this.rod.addChild(this.staffEnd2);
		this.rod.addChild(this.staffEnd4);
		this.rod.addChild(this.staffEnd1);
		this.rightArm.addChild(this.rod);
		this.rod.addChild(this.staffEnd3);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.leftLeg.render(f5);
		this.leftArm.render(f5);
		this.cloak.render(f5);
		this.helm.render(f5);
		this.head.render(f5);
		this.rightLeg.render(f5);
		this.rightArm.render(f5);
		this.body.render(f5);
	}

	public void setRotationAngles(float f, float limbSwingAmount, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, limbSwingAmount, f2, f3, f4, f5, entity);

		helm.rotateAngleX = head.rotateAngleX = f4 / (180F / (float) Math.PI);
		helm.rotateAngleY = head.rotateAngleY = f3 / (180F / (float) Math.PI);

		float maxSwing = 1.0F;

		leftArm.rotateAngleX = limbSwingAmount * maxSwing;
		rightArm.rotateAngleX = limbSwingAmount * maxSwing;
		leftLeg.rotateAngleX = limbSwingAmount * maxSwing;
		rightLeg.rotateAngleX = limbSwingAmount * maxSwing;

	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}
