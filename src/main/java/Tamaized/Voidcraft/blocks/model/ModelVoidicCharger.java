package Tamaized.Voidcraft.blocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * ModelEnderCrystal - Either Mojang or a mod author Created using Tabula 5.1.0
 */
public class ModelVoidicCharger extends ModelBase {
	public ModelRenderer glass;
	public ModelRenderer cube;
	public ModelRenderer base;
	public ModelRenderer Base;
	public ModelRenderer Stem;
	public ModelRenderer Base_1;
	public ModelRenderer Stem_1;
	public ModelRenderer Base_2;
	public ModelRenderer Stem_2;
	public ModelRenderer Base_3;
	public ModelRenderer Stem_3;

	public ModelVoidicCharger() {
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.cube = new ModelRenderer(this, 32, 0);
		this.cube.setRotationPoint(0.0F, 19.9F, 0.0F);
		this.cube.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);

		this.Base = new ModelRenderer(this, 16, 17);
		this.Base.setRotationPoint(3.0F, 13.899999999999972F, -4.0F);
		this.Base.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);

		this.Stem = new ModelRenderer(this, 16, 17);
		this.Stem.setRotationPoint(0.5F, 0.3F, 0.5F);
		this.Stem.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(Stem, 2.443460952792061F, -0.7853981633974483F, 0.0F);

		this.Stem_3 = new ModelRenderer(this, 16, 17);
		this.Stem_3.setRotationPoint(0.5F, 0.3F, 0.5F);
		this.Stem_3.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(Stem_3, 2.443460952792061F, 0.7853981633974483F, 0.0F);

		this.Base_3 = new ModelRenderer(this, 16, 17);
		this.Base_3.setRotationPoint(-4.0F, 13.9F, -4.0F);
		this.Base_3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);

		this.Base_2 = new ModelRenderer(this, 16, 17);
		this.Base_2.setRotationPoint(-4.000000000000002F, 13.899999999999974F, 3.0000000000000013F);
		this.Base_2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);

		this.Base_1 = new ModelRenderer(this, 16, 17);
		this.Base_1.setRotationPoint(3.0F, 13.899999999999972F, 3.0F);
		this.Base_1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);

		this.Stem_1 = new ModelRenderer(this, 16, 17);
		this.Stem_1.setRotationPoint(0.5F, 0.3F, 0.5F);
		this.Stem_1.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(Stem_1, 2.443460952792061F, -2.356194490192345F, 0.0F);

		this.Stem_2 = new ModelRenderer(this, 16, 17);
		this.Stem_2.setRotationPoint(0.5F, 0.3F, 0.5F);
		this.Stem_2.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(Stem_2, 2.443460952792061F, 2.356194490192345F, 0.0F);

		this.glass = new ModelRenderer(this, 0, 0);
		this.glass.setRotationPoint(0.0F, 0.0f, 0.0F);
		this.glass.addBox(-4F, -4F, -4F, 8, 8, 8, 0.0F);

		this.base = new ModelRenderer(this, 0, 16);
		this.base.setRotationPoint(0.0F, 19.900000000000002F, 0.0F);
		this.base.addBox(-6.0F, 0.0F, -6.0F, 12, 4, 12, 0.0F);

		this.Base.addChild(this.Stem);
		this.Base_3.addChild(this.Stem_3);
		this.Base_1.addChild(this.Stem_1);
		this.Base_2.addChild(this.Stem_2);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, 0, 0, 0);
	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, double offsetX, double offsetY, double offsetZ) {
		GlStateManager.pushMatrix();
		{
			this.cube.render(scale);
			this.Base.render(scale);
			this.Base_3.render(scale);
			this.Base_2.render(scale);
			this.Base_1.render(scale);
			GlStateManager.pushMatrix();
			{
				GlStateManager.translate(0 + offsetX, 1.2 + offsetY, 0 + offsetZ);
				GlStateManager.rotate(limbSwingAmount, 0, 1, 0);
				GlStateManager.rotate(limbSwingAmount, 1, 1, 0);
				GlStateManager.rotate(limbSwingAmount, 0, 1, 1);
				this.glass.render(scale);
			}
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			{
				GlStateManager.translate(0 + offsetX, 1.2 + offsetY, 0 + offsetZ);
				GlStateManager.rotate(20 - limbSwingAmount, 0, 1, 0);
				GlStateManager.rotate(20 - limbSwingAmount, 1, 1, 0);
				GlStateManager.rotate(20 - limbSwingAmount, 0, 1, 1);
				this.glass.render(scale);
			}
			GlStateManager.popMatrix();
			this.base.render(scale);
		}
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
}
