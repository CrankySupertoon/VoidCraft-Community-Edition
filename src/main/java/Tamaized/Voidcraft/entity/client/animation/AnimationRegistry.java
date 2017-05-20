package Tamaized.Voidcraft.entity.client.animation;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Tamaized.Voidcraft.entity.EntityVoidNPC;
import Tamaized.Voidcraft.entity.boss.lob.render.ModelLordOfBlades;
import Tamaized.Voidcraft.entity.client.animation.AnimatableModel.AnimatableModelArms;
import io.netty.buffer.ByteBufInputStream;

public class AnimationRegistry {

	private static List<Class<? extends IAnimation>> REGISTRY = new ArrayList<Class<? extends IAnimation>>();

	public static int limbs = register(AnimationLimbs.class);

	public static class AnimationLimbs implements IAnimation<EntityVoidNPC, AnimatableModelArms> {
		
		public static void play(EntityVoidNPC entity, float f1, float f2, float f3, float f4){
			AnimationRegistry.AnimationLimbs animation = ((AnimationRegistry.AnimationLimbs) entity.constructAnimation(AnimationRegistry.limbs));
			animation.init(f1, f2, f3, f4);
			entity.setAnimation(animation);
			entity.playAnimation();
		}

		// Degrees
		private float leftArmYaw = 0.0f;
		private float leftArmPitch = 0.0f;
		private float rightArmYaw = 0.0f;
		private float rightArmPitch = 0.0f;

		private int tick = 20 * 2;

		public void init(float leftArmPitch, float rightArmPitch, float leftArmYaw, float rightArmYaw) {
			this.leftArmPitch = leftArmPitch;
			this.rightArmPitch = rightArmPitch;
			this.leftArmYaw = leftArmYaw;
			this.rightArmYaw = rightArmYaw;
		}

		@Override
		public boolean update(EntityVoidNPC e) {
			tick--;
			return tick <= 0;
		}

		@Override
		public void render(EntityVoidNPC e, AnimatableModelArms model) {
			// Work on smoothness later
			model.setAnimations(leftArmPitch, rightArmPitch, leftArmYaw, rightArmYaw);
		}

		@Override
		public void encodePacket(DataOutputStream stream) throws IOException {
			stream.writeFloat(leftArmYaw);
			stream.writeFloat(leftArmPitch);
			stream.writeFloat(rightArmYaw);
			stream.writeFloat(rightArmPitch);
		}

		@Override
		public void decodePacket(ByteBufInputStream stream) throws IOException {
			leftArmYaw = stream.readFloat();
			leftArmPitch = stream.readFloat();
			rightArmYaw = stream.readFloat();
			rightArmPitch = stream.readFloat();
		}

	}

	public static int register(Class<? extends IAnimation> animation) {
		REGISTRY.add(animation);
		return REGISTRY.indexOf(animation);
	}

	public static Class<? extends IAnimation> getAnimation(int index) {
		return REGISTRY.get(index);
	}

	public static int getAnimationID(IAnimation animation) {
		return REGISTRY.contains(animation.getClass()) ? REGISTRY.indexOf(animation.getClass()) : -1;
	}

}
