package tamaized.dalquor.common.entity.boss.herobrine.extra;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityHerobrineWitherSkull extends EntityWitherSkull {

	public EntityHerobrineWitherSkull(World worldIn) {
		super(worldIn);
	}

	public EntityHerobrineWitherSkull(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
		super(worldIn, shooter, accelX, accelY, accelZ);
		setSize(0.3125F, 0.3125F);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (!world.isRemote) {
			if (result.entityHit != null && result.entityHit != shootingEntity) {
				if (shootingEntity != null) {
					if (result.entityHit.attackEntityFrom(DamageSource.causeMobDamage(shootingEntity), 8.0F)) {
						shootingEntity.heal(5.0F);
					}
				} else {
					result.entityHit.attackEntityFrom(DamageSource.MAGIC, 8.0F);
				}

				if (result.entityHit instanceof EntityLivingBase) {
					int i = 0;

					if (world.getDifficulty() == EnumDifficulty.NORMAL) {
						i = 10;
					} else if (world.getDifficulty() == EnumDifficulty.HARD) {
						i = 40;
					}

					if (i > 0) {
						((EntityLivingBase) result.entityHit).addPotionEffect(new PotionEffect(MobEffects.WITHER, 20 * i, 2));
					}
				}
			}

			world.newExplosion(this, posX, posY, posZ, 1.0F, false, false);
			setDead();
		}
	}

}
