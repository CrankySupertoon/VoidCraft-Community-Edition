package Tamaized.Voidcraft.entity.boss.herobrine.extra;

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
		this.setSize(0.3125F, 0.3125F);
	}

	/**
	 * Called when this EntityFireball hits a block or entity.
	 */
	protected void onImpact(RayTraceResult result) {
		if (!this.worldObj.isRemote) {
			if (result.entityHit != null) {
				if (this.shootingEntity != null) {
					if (result.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 8.0F)) {
						if (result.entityHit.isEntityAlive()) {
							this.applyEnchantments(this.shootingEntity, result.entityHit);
						} else {
							this.shootingEntity.heal(5.0F);
						}
					}
				} else {
					result.entityHit.attackEntityFrom(DamageSource.magic, 8.0F);
				}

				if (result.entityHit instanceof EntityLivingBase) {
					int i = 0;

					if (this.worldObj.getDifficulty() == EnumDifficulty.NORMAL) {
						i = 10;
					} else if (this.worldObj.getDifficulty() == EnumDifficulty.HARD) {
						i = 40;
					}

					if (i > 0) {
						((EntityLivingBase) result.entityHit).addPotionEffect(new PotionEffect(MobEffects.WITHER, 20 * i, 2));
					}
				}
			}

			this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 1.0F, false, false);
			this.setDead();
		}
	}

}
