package Tamaized.Voidcraft.entity.boss.lob.render;

import Tamaized.Voidcraft.entity.EntityVoidNPC;
import Tamaized.Voidcraft.entity.client.animation.AnimatableModel.AnimatableModelArms;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLordOfBlades extends AnimatableModelArms {

	public ModelRenderer armLeft;
	public ModelRenderer legRight;
	public ModelRenderer head;
	public ModelRenderer legLeft;
	public ModelRenderer armRightOverlay;
	public ModelRenderer legRightOverlay;
	public ModelRenderer headOverlay;
	public ModelRenderer body;
	public ModelRenderer armLeftOverlay;
	public ModelRenderer legLeftOverlay;
	public ModelRenderer bodyOverlay;
	public ModelRenderer armRight;
	public ModelRenderer ClawLeft;
	public ModelRenderer ArmbladeLeft;
	public ModelRenderer BackArmbladeLeftT;
	public ModelRenderer BackArmbladeRightC;
	public ModelRenderer BackArmbladeRightB;
	public ModelRenderer shoulderLeft;
	public ModelRenderer ClawLeftBlade1;
	public ModelRenderer ClawLeftBlade2;
	public ModelRenderer shoulderbladeLeft1;
	public ModelRenderer shoulderbladeLeft2;
	public ModelRenderer shoulderbladeLeft3;
	public ModelRenderer ThighbladRightE;
	public ModelRenderer LegbladeRightT;
	public ModelRenderer LegbladeRightC;
	public ModelRenderer LegbladeRightB;
	public ModelRenderer headBladeTop;
	public ModelRenderer CheekbladeR;
	public ModelRenderer CheekbladeL;
	public ModelRenderer shape61;
	public ModelRenderer shape61_1;
	public ModelRenderer shape61_2;
	public ModelRenderer shape61_3;
	public ModelRenderer shape61_4;
	public ModelRenderer ThighbladLeftE;
	public ModelRenderer LegbladeLeftT;
	public ModelRenderer LegbladeRightC_1;
	public ModelRenderer LegbladeRightB_1;
	public ModelRenderer WingBase;
	public ModelRenderer WingLeft;
	public ModelRenderer WingRight;
	public ModelRenderer WingLeftBladeTop;
	public ModelRenderer WingLeftBladeCenter;
	public ModelRenderer WingLeftBladeBottom;
	public ModelRenderer WingRightBladeTop;
	public ModelRenderer WingRightBladeCenter;
	public ModelRenderer WingRightBladeBottom;
	public ModelRenderer ClawRight;
	public ModelRenderer ArmbladeRight;
	public ModelRenderer BackArmbladeRightT;
	public ModelRenderer BackArmbladeRightC_1;
	public ModelRenderer BackArmbladeRightB_1;
	public ModelRenderer shoulderRight;
	public ModelRenderer trident;
	public ModelRenderer ClawRightBlade1;
	public ModelRenderer ClawRightBlade2;
	public ModelRenderer shoulderbladeRight1;
	public ModelRenderer shoulderbladeRight2;
	public ModelRenderer shoulderbladeRight3;
	public ModelRenderer tridentBase;
	public ModelRenderer tridentbladeC;
	public ModelRenderer tridentbladeL;
	public ModelRenderer tridentbladeR;

	public ModelLordOfBlades() {
		textureWidth = 128;
		textureHeight = 128;

		armLeft = new ModelRenderer(this, 0, 0);
		armLeft.setRotationPoint(8.0F, -10.0F, 0.0F);
		armLeft.addBox(-2.0F, -2.0F, -3.0F, 4, 18, 6, 0.0F);
		setRotateAngle(armLeft, 0.0F, 0.0F, -0.10000736613927509F);

		ClawRight = new ModelRenderer(this, 42, 0);
		ClawRight.mirror = true;
		ClawRight.setRotationPoint(-3.0F, 13.5F, 0.0F);
		ClawRight.addBox(-0.5F, 0.0F, -2.5F, 1, 4, 5, 0.0F);

		shoulderRight = new ModelRenderer(this, 78, 59);
		shoulderRight.mirror = true;
		shoulderRight.setRotationPoint(-2.0F, -2.5F, 0.0F);
		shoulderRight.addBox(-3.0F, 0.0F, -3.5F, 6, 1, 7, 0.0F);

		BackArmbladeRightT = new ModelRenderer(this, 14, 0);
		BackArmbladeRightT.mirror = true;
		BackArmbladeRightT.setRotationPoint(-1.0F, 9.0F, 3.5F);
		BackArmbladeRightT.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 4, 0.0F);
		setRotateAngle(BackArmbladeRightT, -0.4363323129985824F, 0.0F, 0.0F);

		ThighbladRightE = new ModelRenderer(this, 22, 24);
		ThighbladRightE.mirror = true;
		ThighbladRightE.setRotationPoint(-0.5F, 6.0F, -0.5F);
		ThighbladRightE.addBox(-0.5F, 0.0F, -0.5F, 3, 8, 1, 0.0F);
		setRotateAngle(ThighbladRightE, 0.7285004297824331F, 0.0F, 2.321986036853256F);

		BackArmbladeLeftT = new ModelRenderer(this, 14, 0);
		BackArmbladeLeftT.setRotationPoint(0.0F, 9.0F, 3.5F);
		BackArmbladeLeftT.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 4, 0.0F);
		setRotateAngle(BackArmbladeLeftT, -0.4363323129985824F, 0.0F, 0.0F);

		WingLeft = new ModelRenderer(this, 114, 66);
		WingLeft.mirror = true;
		WingLeft.setRotationPoint(-0.5F, 7.0F, 3.5F);
		WingLeft.addBox(-0.5F, 0.0F, -0.5F, 1, 24, 1, 0.0F);
		setRotateAngle(WingLeft, 0.4363323129985824F, 0.0F, -2.6179938779914944F);

		CheekbladeR = new ModelRenderer(this, 66, 67);
		CheekbladeR.setRotationPoint(-6.5F, -4.0F, 0.0F);
		CheekbladeR.addBox(-1.4F, 0.0F, -0.5F, 3, 1, 12, 0.0F);
		setRotateAngle(CheekbladeR, -0.6981317007977318F, 2.96705972839036F, 0.0F);

		bodyOverlay = new ModelRenderer(this, 22, 48);
		bodyOverlay.setRotationPoint(0.0F, -12.0F, 0.0F);
		bodyOverlay.addBox(-6.0F, 0.0F, -3.0F, 12, 18, 6, 0.25F);

		CheekbladeL = new ModelRenderer(this, 66, 67);
		CheekbladeL.mirror = true;
		CheekbladeL.setRotationPoint(6.5F, -4.0F, 0.0F);
		CheekbladeL.addBox(-1.4F, 0.0F, -0.5F, 3, 1, 12, 0.0F);
		setRotateAngle(CheekbladeL, -0.6981317007977318F, -2.96705972839036F, 0.0F);

		shape61_3 = new ModelRenderer(this, 100, 90);
		shape61_3.setRotationPoint(0.0F, 10.5F, -0.5F);
		shape61_3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

		legLeftOverlay = new ModelRenderer(this, 0, 24);
		legLeftOverlay.mirror = true;
		legLeftOverlay.setRotationPoint(3.5F, 6.0F, 0.1F);
		legLeftOverlay.addBox(-2.5F, 0.0F, -3.0F, 5, 18, 6, 0.25F);

		ClawLeftBlade1 = new ModelRenderer(this, 112, 0);
		ClawLeftBlade1.setRotationPoint(0.5F, 3.0F, 1.0F);
		ClawLeftBlade1.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);

		ThighbladLeftE = new ModelRenderer(this, 22, 24);
		ThighbladLeftE.setRotationPoint(0.5F, 6.0F, -0.5F);
		ThighbladLeftE.addBox(-0.5F, 0.0F, -0.5F, 3, 8, 1, 0.0F);
		setRotateAngle(ThighbladLeftE, 2.41309222380736F, 0.0F, 0.8196066167365371F);

		ArmbladeLeft = new ModelRenderer(this, 78, 0);
		ArmbladeLeft.setRotationPoint(2.0F, 7.0F, 0.0F);
		ArmbladeLeft.addBox(-1.0F, 0.0F, -0.5F, 2, 10, 1, 0.0F);
		setRotateAngle(ArmbladeLeft, 0.0F, 0.0F, -0.3490658503988659F);

		ClawRightBlade1 = new ModelRenderer(this, 112, 0);
		ClawRightBlade1.mirror = true;
		ClawRightBlade1.setRotationPoint(-0.5F, 3.0F, 1.0F);
		ClawRightBlade1.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);

		headOverlay = new ModelRenderer(this, 22, 24);
		headOverlay.setRotationPoint(0.0F, -16.0F, 0.0F);
		headOverlay.addBox(-6.0F, -8.0F, -6.0F, 12, 12, 12, 0.25F);

		tridentBase = new ModelRenderer(this, 26, 84);
		tridentBase.setRotationPoint(0.0F, 0.5F, -14.0F);
		tridentBase.addBox(-1.5F, -0.5F, -5.0F, 3, 1, 4, 0.0F);

		head = new ModelRenderer(this, 42, 0);
		head.setRotationPoint(0.0F, -16.0F, 0.0F);
		head.addBox(-6.0F, -8.0F, -6.0F, 12, 12, 12, 0.0F);

		legLeft = new ModelRenderer(this, 20, 0);
		legLeft.mirror = true;
		legLeft.setRotationPoint(3.5F, 6.0F, 0.1F);
		legLeft.addBox(-2.5F, 0.0F, -3.0F, 5, 18, 6, 0.0F);

		LegbladeRightC = new ModelRenderer(this, 30, 24);
		LegbladeRightC.mirror = true;
		LegbladeRightC.setRotationPoint(-1.5F, 14.5F, 0.0F);
		LegbladeRightC.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
		setRotateAngle(LegbladeRightC, 0.27314402793711257F, 0.0F, 2.41309222380736F);

		WingBase = new ModelRenderer(this, 58, 31);
		WingBase.setRotationPoint(0.0F, 4.0F, 3.0F);
		WingBase.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 4, 0.0F);
		setRotateAngle(WingBase, -0.9948376736367678F, 0.0F, 0.0F);

		shoulderbladeLeft1 = new ModelRenderer(this, 120, 0);
		shoulderbladeLeft1.setRotationPoint(-1.5F, 0.5F, 0.0F);
		shoulderbladeLeft1.addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
		setRotateAngle(shoulderbladeLeft1, -0.17453292519943295F, 0.0F, 0.3490658503988659F);

		shape61_1 = new ModelRenderer(this, 100, 90);
		shape61_1.setRotationPoint(0.0F, 8.5F, 1.5F);
		shape61_1.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

		shoulderbladeLeft2 = new ModelRenderer(this, 0, 0);
		shoulderbladeLeft2.setRotationPoint(0.0F, 0.5F, 0.0F);
		shoulderbladeLeft2.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
		setRotateAngle(shoulderbladeLeft2, -0.17453292519943295F, 0.0F, 0.5235987755982988F);

		LegbladeRightT = new ModelRenderer(this, 30, 24);
		LegbladeRightT.mirror = true;
		LegbladeRightT.setRotationPoint(-1.5F, 12.0F, 0.0F);
		LegbladeRightT.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
		setRotateAngle(LegbladeRightT, 0.27314402793711257F, 0.0F, 2.41309222380736F);

		shape61_4 = new ModelRenderer(this, 100, 90);
		shape61_4.setRotationPoint(0.0F, 11.5F, -1.5F);
		shape61_4.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

		shoulderbladeRight2 = new ModelRenderer(this, 0, 0);
		shoulderbladeRight2.mirror = true;
		shoulderbladeRight2.setRotationPoint(0.0F, 0.5F, 0.0F);
		shoulderbladeRight2.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
		setRotateAngle(shoulderbladeRight2, -0.17453292519943295F, 0.0F, -0.5235987755982988F);

		shape61 = new ModelRenderer(this, 100, 90);
		shape61.setRotationPoint(0.0F, 5.5F, 2.5F);
		shape61.addBox(-0.5F, -0.5F, -0.5F, 1, 3, 1, 0.0F);

		BackArmbladeRightC_1 = new ModelRenderer(this, 36, 0);
		BackArmbladeRightC_1.mirror = true;
		BackArmbladeRightC_1.setRotationPoint(-1.0F, 11.0F, 3.5F);
		BackArmbladeRightC_1.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 4, 0.0F);
		setRotateAngle(BackArmbladeRightC_1, -0.5235987755982988F, 0.0F, 0.0F);

		WingLeftBladeBottom = new ModelRenderer(this, 18, 76);
		WingLeftBladeBottom.mirror = true;
		WingLeftBladeBottom.setRotationPoint(0.0F, 11.5F, 0.0F);
		WingLeftBladeBottom.addBox(-13.5F, -1.5F, -0.5F, 13, 3, 1, 0.0F);

		legRightOverlay = new ModelRenderer(this, 0, 24);
		legRightOverlay.setRotationPoint(-3.5F, 6.0F, 0.1F);
		legRightOverlay.addBox(-2.5F, 0.0F, -3.0F, 5, 18, 6, 0.25F);

		ClawLeft = new ModelRenderer(this, 42, 0);
		ClawLeft.setRotationPoint(2.0F, 13.5F, 0.0F);
		ClawLeft.addBox(-0.5F, 0.0F, -2.5F, 1, 4, 5, 0.0F);

		LegbladeLeftT = new ModelRenderer(this, 30, 24);
		LegbladeLeftT.setRotationPoint(1.5F, 12.0F, 0.0F);
		LegbladeLeftT.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
		setRotateAngle(LegbladeLeftT, 2.86844862565268F, 0.0F, 0.7285004297824331F);

		shoulderbladeLeft3 = new ModelRenderer(this, 14, 0);
		shoulderbladeLeft3.setRotationPoint(1.5F, 0.5F, 0.0F);
		shoulderbladeLeft3.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
		setRotateAngle(shoulderbladeLeft3, -0.17453292519943295F, 0.0F, 0.6981317007977318F);

		tridentbladeL = new ModelRenderer(this, 38, 84);
		tridentbladeL.setRotationPoint(2.0F, 0.0F, -1.0F);
		tridentbladeL.addBox(-0.5F, -0.5F, -10.0F, 1, 1, 10, 0.0F);
		setRotateAngle(tridentbladeL, -0.17453292519943295F, 0.0F, 0.0F);

		legRight = new ModelRenderer(this, 20, 0);
		legRight.setRotationPoint(-3.5F, 6.0F, 0.1F);
		legRight.addBox(-2.5F, 0.0F, -3.0F, 5, 18, 6, 0.0F);

		LegbladeRightB = new ModelRenderer(this, 30, 24);
		LegbladeRightB.mirror = true;
		LegbladeRightB.setRotationPoint(-1.5F, 17.0F, 0.0F);
		LegbladeRightB.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
		setRotateAngle(LegbladeRightB, 0.27314402793711257F, 0.0F, 2.41309222380736F);

		headBladeTop = new ModelRenderer(this, 96, 66);
		headBladeTop.setRotationPoint(0.0F, -5.0F, -3.0F);
		headBladeTop.addBox(-0.5F, 0.0F, -4.0F, 1, 12, 8, 0.0F);
		setRotateAngle(headBladeTop, 2.443460952792061F, 0.0F, 0.0F);

		BackArmbladeRightB_1 = new ModelRenderer(this, 84, 0);
		BackArmbladeRightB_1.mirror = true;
		BackArmbladeRightB_1.setRotationPoint(-1.0F, 13.0F, 3.5F);
		BackArmbladeRightB_1.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 4, 0.0F);
		setRotateAngle(BackArmbladeRightB_1, -0.6108652381980153F, 0.0F, 0.0F);

		ClawRightBlade2 = new ModelRenderer(this, 112, 0);
		ClawRightBlade2.mirror = true;
		ClawRightBlade2.setRotationPoint(-0.5F, 3.0F, -1.0F);
		ClawRightBlade2.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);

		shoulderbladeRight1 = new ModelRenderer(this, 120, 0);
		shoulderbladeRight1.mirror = true;
		shoulderbladeRight1.setRotationPoint(1.5F, 0.5F, 0.0F);
		shoulderbladeRight1.addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
		setRotateAngle(shoulderbladeRight1, -0.17453292519943295F, 0.0F, -0.3490658503988659F);

		LegbladeRightB_1 = new ModelRenderer(this, 30, 24);
		LegbladeRightB_1.setRotationPoint(1.6F, 17.0F, 0.0F);
		LegbladeRightB_1.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
		setRotateAngle(LegbladeRightB_1, 2.86844862565268F, 0.0F, 0.7285004297824331F);

		WingRightBladeTop = new ModelRenderer(this, 18, 76);
		WingRightBladeTop.setRotationPoint(0.0F, 21.5F, 0.0F);
		WingRightBladeTop.addBox(0.5F, -1.5F, -0.5F, 13, 3, 1, 0.0F);

		BackArmbladeRightB = new ModelRenderer(this, 84, 0);
		BackArmbladeRightB.setRotationPoint(0.0F, 13.0F, 3.5F);
		BackArmbladeRightB.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 4, 0.0F);
		setRotateAngle(BackArmbladeRightB, -0.6108652381980153F, 0.0F, 0.0F);

		armRightOverlay = new ModelRenderer(this, 100, 42);
		armRightOverlay.mirror = true;
		armRightOverlay.setRotationPoint(-7.0F, -10.0F, 0.0F);
		armRightOverlay.addBox(-3.0F, -2.0F, -3.0F, 4, 18, 6, 0.25F);
		setRotateAngle(armRightOverlay, 0.0F, 0.0F, 0.22689280275926282F);

		trident = new ModelRenderer(this, 63, 80);
		trident.setRotationPoint(-1.0F, 16.5F, 0.0F);
		trident.addBox(-0.5F, 0.0F, -15.0F, 1, 1, 30, 0.0F);
		setRotateAngle(trident, 0.0F, 0.5998696639104509F, 1.2362167091875838F);

		ClawLeftBlade2 = new ModelRenderer(this, 112, 0);
		ClawLeftBlade2.setRotationPoint(0.5F, 3.0F, -1.0F);
		ClawLeftBlade2.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);

		body = new ModelRenderer(this, 70, 24);
		body.setRotationPoint(0.0F, -12.0F, 0.0F);
		body.addBox(-6.0F, 0.0F, -3.0F, 12, 18, 6, 0.0F);

		shape61_2 = new ModelRenderer(this, 100, 90);
		shape61_2.setRotationPoint(0.0F, 9.5F, 0.5F);
		shape61_2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

		shoulderLeft = new ModelRenderer(this, 78, 59);
		shoulderLeft.setRotationPoint(1.0F, -2.5F, 0.0F);
		shoulderLeft.addBox(-3.0F, 0.0F, -3.5F, 6, 1, 7, 0.0F);

		LegbladeRightC_1 = new ModelRenderer(this, 30, 24);
		LegbladeRightC_1.setRotationPoint(1.5F, 14.5F, 0.0F);
		LegbladeRightC_1.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
		setRotateAngle(LegbladeRightC_1, 2.86844862565268F, 0.0F, 0.7285004297824331F);

		WingRightBladeCenter = new ModelRenderer(this, 18, 76);
		WingRightBladeCenter.setRotationPoint(0.0F, 16.6F, 0.0F);
		WingRightBladeCenter.addBox(0.5F, -1.5F, -0.5F, 13, 3, 1, 0.0F);

		shoulderbladeRight3 = new ModelRenderer(this, 14, 0);
		shoulderbladeRight3.mirror = true;
		shoulderbladeRight3.setRotationPoint(-1.5F, 0.5F, 0.0F);
		shoulderbladeRight3.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
		setRotateAngle(shoulderbladeRight3, -0.17453292519943295F, 0.0F, -0.6981317007977318F);

		tridentbladeC = new ModelRenderer(this, 34, 84);
		tridentbladeC.setRotationPoint(0.0F, 0.0F, -5.0F);
		tridentbladeC.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 6, 0.0F);
		setRotateAngle(tridentbladeC, 2.6179938779914944F, 0.0F, 0.0F);

		WingLeftBladeCenter = new ModelRenderer(this, 18, 76);
		WingLeftBladeCenter.mirror = true;
		WingLeftBladeCenter.setRotationPoint(0.0F, 16.5F, 0.0F);
		WingLeftBladeCenter.addBox(-13.5F, -1.5F, -0.5F, 13, 3, 1, 0.0F);

		WingRightBladeBottom = new ModelRenderer(this, 18, 76);
		WingRightBladeBottom.setRotationPoint(0.0F, 11.5F, 0.0F);
		WingRightBladeBottom.addBox(0.5F, -1.5F, -0.5F, 13, 3, 1, 0.0F);

		armLeftOverlay = new ModelRenderer(this, 100, 42);
		armLeftOverlay.setRotationPoint(7.0F, -10.0F, 0.0F);
		armLeftOverlay.addBox(-1.0F, -2.0F, -3.0F, 4, 18, 6, 0.25F);
		setRotateAngle(armLeftOverlay, 0.0F, 0.0F, -0.10000736613927509F);

		WingRight = new ModelRenderer(this, 114, 66);
		WingRight.setRotationPoint(0.5F, 7.0F, 3.5F);
		WingRight.addBox(-0.5F, 0.0F, -0.5F, 1, 24, 1, 0.0F);
		setRotateAngle(WingRight, 0.4363323129985824F, 0.0F, 2.6179938779914944F);

		armRight = new ModelRenderer(this, 0, 0);
		armRight.mirror = true;
		armRight.setRotationPoint(-7.0F, -10.0F, 0.0F);
		armRight.addBox(-3.0F, -2.0F, -3.0F, 4, 18, 6, 0.0F);
		setRotateAngle(armRight, 0.0F, 0.0F, 0.22689280275926282F);

		ArmbladeRight = new ModelRenderer(this, 78, 0);
		ArmbladeRight.mirror = true;
		ArmbladeRight.setRotationPoint(-3.0F, 7.0F, 0.0F);
		ArmbladeRight.addBox(-1.0F, 0.0F, -0.5F, 2, 10, 1, 0.0F);
		setRotateAngle(ArmbladeRight, 0.0F, 0.0F, 0.3490658503988659F);

		WingLeftBladeTop = new ModelRenderer(this, 18, 76);
		WingLeftBladeTop.mirror = true;
		WingLeftBladeTop.setRotationPoint(0.0F, 21.5F, 0.0F);
		WingLeftBladeTop.addBox(-13.5F, -1.5F, -0.5F, 13, 3, 1, 0.0F);

		BackArmbladeRightC = new ModelRenderer(this, 36, 0);
		BackArmbladeRightC.setRotationPoint(0.0F, 11.0F, 3.5F);
		BackArmbladeRightC.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 4, 0.0F);
		setRotateAngle(BackArmbladeRightC, -0.5235987755982988F, 0.0F, 0.0F);

		tridentbladeR = new ModelRenderer(this, 0, 85);
		tridentbladeR.setRotationPoint(-2.0F, 0.0F, -1.0F);
		tridentbladeR.addBox(-0.5F, -0.5F, -10.0F, 1, 1, 10, 0.0F);
		setRotateAngle(tridentbladeR, -0.17453292519943295F, 0.0F, 0.0F);

		armRight.addChild(ClawRight);
		armRight.addChild(shoulderRight);
		armRight.addChild(BackArmbladeRightT);
		legRight.addChild(ThighbladRightE);
		armLeft.addChild(BackArmbladeLeftT);
		body.addChild(WingLeft);
		head.addChild(CheekbladeR);
		head.addChild(CheekbladeL);
		headBladeTop.addChild(shape61_3);
		ClawLeft.addChild(ClawLeftBlade1);
		legLeft.addChild(ThighbladLeftE);
		armLeft.addChild(ArmbladeLeft);
		ClawRight.addChild(ClawRightBlade1);
		trident.addChild(tridentBase);
		legRight.addChild(LegbladeRightC);
		body.addChild(WingBase);
		shoulderLeft.addChild(shoulderbladeLeft1);
		headBladeTop.addChild(shape61_1);
		shoulderLeft.addChild(shoulderbladeLeft2);
		legRight.addChild(LegbladeRightT);
		headBladeTop.addChild(shape61_4);
		shoulderRight.addChild(shoulderbladeRight2);
		headBladeTop.addChild(shape61);
		armRight.addChild(BackArmbladeRightC_1);
		WingLeft.addChild(WingLeftBladeBottom);
		armLeft.addChild(ClawLeft);
		legLeft.addChild(LegbladeLeftT);
		shoulderLeft.addChild(shoulderbladeLeft3);
		tridentBase.addChild(tridentbladeL);
		legRight.addChild(LegbladeRightB);
		head.addChild(headBladeTop);
		armRight.addChild(BackArmbladeRightB_1);
		ClawRight.addChild(ClawRightBlade2);
		shoulderRight.addChild(shoulderbladeRight1);
		legLeft.addChild(LegbladeRightB_1);
		WingRight.addChild(WingRightBladeTop);
		armLeft.addChild(BackArmbladeRightB);
		armRight.addChild(trident);
		ClawLeft.addChild(ClawLeftBlade2);
		headBladeTop.addChild(shape61_2);
		armLeft.addChild(shoulderLeft);
		legLeft.addChild(LegbladeRightC_1);
		WingRight.addChild(WingRightBladeCenter);
		shoulderRight.addChild(shoulderbladeRight3);
		tridentBase.addChild(tridentbladeC);
		WingLeft.addChild(WingLeftBladeCenter);
		WingRight.addChild(WingRightBladeBottom);
		body.addChild(WingRight);
		armRight.addChild(ArmbladeRight);
		WingLeft.addChild(WingLeftBladeTop);
		armLeft.addChild(BackArmbladeRightC);
		tridentBase.addChild(tridentbladeR);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		((EntityVoidNPC) entity).renderAnimation(this);
		armLeft.render(f5);
		bodyOverlay.render(f5);
		legLeftOverlay.render(f5);
		headOverlay.render(f5);
		head.render(f5);
		legLeft.render(f5);
		legRightOverlay.render(f5);
		legRight.render(f5);
		armRightOverlay.render(f5);
		body.render(f5);
		armLeftOverlay.render(f5);
		armRight.render(f5);
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
	public void setAnimations(float leftArmPitch, float rightArmPitch, float leftArmYaw, float rightArmYaw) {
		
	}
}
