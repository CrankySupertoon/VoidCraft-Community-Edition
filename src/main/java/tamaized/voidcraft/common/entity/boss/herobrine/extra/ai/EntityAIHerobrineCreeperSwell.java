package tamaized.voidcraft.common.entity.boss.herobrine.extra.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import tamaized.voidcraft.common.entity.boss.herobrine.extra.EntityHerobrineCreeper;

public class EntityAIHerobrineCreeperSwell extends EntityAIBase {
	/**
	 * The creeper that is swelling.
	 */
	EntityHerobrineCreeper swellingCreeper;
	/**
	 * The creeper's attack target. This is used for the changing of the creeper's state.
	 */
	EntityLivingBase creeperAttackTarget;

	public EntityAIHerobrineCreeperSwell(EntityHerobrineCreeper entitycreeperIn) {
		swellingCreeper = entitycreeperIn;
		setMutexBits(1);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = swellingCreeper.getAttackTarget();
		return swellingCreeper.getCreeperState() > 0 || entitylivingbase != null && swellingCreeper.getDistance(entitylivingbase) < 9.0D;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting() {
		swellingCreeper.getNavigator().clearPath();
		creeperAttackTarget = swellingCreeper.getAttackTarget();
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask() {
		creeperAttackTarget = null;
	}

	/**
	 * Updates the task
	 */
	@Override
	public void updateTask() {
		if (creeperAttackTarget == null) {
			swellingCreeper.setCreeperState(-1);
		} else if (swellingCreeper.getDistance(creeperAttackTarget) > 49.0D) {
			swellingCreeper.setCreeperState(-1);
		} else if (!swellingCreeper.getEntitySenses().canSee(creeperAttackTarget)) {
			swellingCreeper.setCreeperState(-1);
		} else {
			swellingCreeper.setCreeperState(1);
		}
	}
}