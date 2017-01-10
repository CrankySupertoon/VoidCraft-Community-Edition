package Tamaized.Voidcraft.entity.boss.dragon.sub.voidic.render;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.entity.boss.dragon.render.RenderDragonOld;
import Tamaized.Voidcraft.entity.boss.dragon.sub.voidic.EntityVoidicDragon;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderVoidicDragon<T extends EntityVoidicDragon> extends RenderDragonOld<T> {

	private static final ResourceLocation DRAGON_EXPLODING_TEXTURES = new ResourceLocation(voidCraft.modid, "textures/entity/dragon/voidic/dragon_exploding.png");
	private static final ResourceLocation DRAGON_TEXTURES = new ResourceLocation(voidCraft.modid, "textures/entity/dragon/voidic/dragon.png");

	public RenderVoidicDragon(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	protected ResourceLocation getDragonTexture() {
		return DRAGON_TEXTURES;
	}

	@Override
	protected ResourceLocation getExlodeTexture() {
		return DRAGON_EXPLODING_TEXTURES;
	}

}