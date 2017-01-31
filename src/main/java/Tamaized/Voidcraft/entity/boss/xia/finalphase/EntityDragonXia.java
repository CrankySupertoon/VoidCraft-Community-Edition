package Tamaized.Voidcraft.entity.boss.xia.finalphase;

import Tamaized.Voidcraft.entity.boss.dragon.EntityDragonOld;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia2.phases.EntityAIXia2Phase3;
import net.minecraft.world.World;

public class EntityDragonXia extends EntityDragonOld {

	private EntityAIXia2Phase3 ai;

	public EntityDragonXia(World p_i1700_1_) {
		super(p_i1700_1_);
	}

	public EntityDragonXia(World world, EntityAIXia2Phase3 entityAIXia2Phase3) {
		this(world);
		ai = entityAIXia2Phase3;
	}

	@Override
	public void onLivingUpdate() {
		if (!world.isRemote) {
			if (ai == null || ai.getEntity() == null || ai.getEntity().getCurrentPhase() != 3) setDead();
		}
		super.onLivingUpdate();
	}

}
