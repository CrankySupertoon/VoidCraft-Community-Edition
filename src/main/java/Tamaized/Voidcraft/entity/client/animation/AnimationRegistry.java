package Tamaized.Voidcraft.entity.client.animation;

import java.util.ArrayList;
import java.util.List;

import Tamaized.Voidcraft.entity.boss.lob.EntityLordOfBlades;
import net.minecraft.util.math.Vec3d;

public class AnimationRegistry {

	private static List<IAnimation> REGISTRY = new ArrayList<IAnimation>();

	public static int test = register(new IAnimation<EntityLordOfBlades>() {

		@Override
		public boolean update(EntityLordOfBlades e) {
			return false;
		}

		@Override
		public void render(EntityLordOfBlades e) {

		}

	});

	public static int register(IAnimation animation) {
		REGISTRY.add(animation);
		return REGISTRY.indexOf(animation);
	}

	public static IAnimation getAnimation(int index) {
		return REGISTRY.get(index);
	}

}
