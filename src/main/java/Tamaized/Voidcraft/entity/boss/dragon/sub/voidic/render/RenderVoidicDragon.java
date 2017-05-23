package Tamaized.Voidcraft.entity.boss.dragon.sub.voidic.render;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.boss.dragon.render.RenderDragonOldWithBar;
import Tamaized.Voidcraft.entity.boss.dragon.sub.voidic.EntityVoidicDragon;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderVoidicDragon<T extends EntityVoidicDragon> extends RenderDragonOldWithBar<T> {

	private static final ResourceLocation DRAGON_EXPLODING_TEXTURES = new ResourceLocation(VoidCraft.modid, "textures/entity/dragon/voidic/dragon_exploding.png");
	private static final ResourceLocation DRAGON_TEXTURES = new ResourceLocation(VoidCraft.modid, "textures/entity/dragon/voidic/dragon.png");

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