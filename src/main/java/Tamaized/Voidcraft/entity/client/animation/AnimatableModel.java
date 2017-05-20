package Tamaized.Voidcraft.entity.client.animation;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public abstract class AnimatableModel extends ModelBase {

	public AnimatableModel() {

	}

	public final void setRotation(ModelRenderer model, double x, double y, double z) {
		model.rotateAngleX -= x;
		model.rotateAngleY -= y;
		model.rotateAngleZ -= z;
	}

	public static abstract class AnimatableModelArms extends AnimatableModel {

		public AnimatableModelArms() {

		}

		public abstract void setAnimations(float leftArmPitch, float rightArmPitch, float leftArmYaw, float rightArmYaw);

	}

}
