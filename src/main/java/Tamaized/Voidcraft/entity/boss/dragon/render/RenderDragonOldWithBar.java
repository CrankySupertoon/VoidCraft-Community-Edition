package Tamaized.Voidcraft.entity.boss.dragon.render;

import Tamaized.TamModized.entity.dragon.render.RenderDragonOld;
import Tamaized.Voidcraft.entity.boss.dragon.EntityAbstractDragonOld;
import Tamaized.Voidcraft.entity.boss.render.bossBar.RenderAlternateBossBars;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderDragonOldWithBar<T extends EntityAbstractDragonOld> extends RenderDragonOld<T> {

	public RenderDragonOldWithBar(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
		RenderAlternateBossBars.addBoss(entity.bossBarWrapper);
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

}