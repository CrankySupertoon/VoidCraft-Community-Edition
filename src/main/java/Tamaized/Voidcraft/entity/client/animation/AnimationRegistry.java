package Tamaized.Voidcraft.entity.client.animation;

import java.util.ArrayList;
import java.util.List;

public class AnimationRegistry {

	private static List<IAnimation> REGISTRY = new ArrayList<IAnimation>();
	
	

	public static int register(IAnimation animation) {
		REGISTRY.add(animation);
		return REGISTRY.indexOf(animation);
	}

	public static IAnimation getAnimation(int index) {
		return REGISTRY.get(index);
	}

}
