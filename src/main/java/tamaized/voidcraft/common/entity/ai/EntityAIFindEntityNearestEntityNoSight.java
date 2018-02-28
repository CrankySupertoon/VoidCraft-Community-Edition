package tamaized.voidcraft.common.entity.ai;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public class EntityAIFindEntityNearestEntityNoSight<T extends EntityLivingBase> extends EntityAIFindEntityNearestPlayer {

	private final EntityLiving entityLiving;
	private final Predicate<Entity> predicate;
	private final Class<T> filter;
	private final EntityAINearestAttackableTarget.Sorter sorter;
	private EntityLivingBase entityTarget;

	/**
	 * VanillaCopy super, but change predicate to not check sight, or bother reducing range for sneaking/invisibility
	 */
	public EntityAIFindEntityNearestEntityNoSight(EntityLiving entityLivingIn, Class<T> filter) {
		super(entityLivingIn);
		entityLiving = entityLivingIn;
		this.filter = filter;
		predicate = entity -> {
			if ((entity instanceof EntityPlayer) && ((EntityPlayer) entity).capabilities.disableDamage) {
				return false;
			} else {
				double maxRange = maxTargetRange();

				return !((double) entity.getDistance(entityLiving) > maxRange) && EntityAITarget.isSuitableTarget(entityLiving, (EntityLivingBase) entity, false, false);
			}
		};

		sorter = new EntityAINearestAttackableTarget.Sorter(entityLivingIn);
	}

	/**
	 * VanillaCopy super, but change bounding box y expansion from 4 to full range
	 */
	@Override
	public boolean shouldExecute() {
		double maxRange = maxTargetRange();
		List<T> list = entityLiving.world.getEntitiesWithinAABB(filter, entityLiving.getEntityBoundingBox().grow(maxRange), predicate);
		list.sort(sorter);

		if (list.isEmpty()) {
			return false;
		} else {
			entityTarget = list.get(0);
			return true;
		}
	}

	/**
	 * Use our target instead of super's.
	 */
	@Override
	public void startExecuting() {
		entityLiving.setAttackTarget(entityTarget);
	}
}