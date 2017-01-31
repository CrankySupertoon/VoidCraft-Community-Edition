package Tamaized.Voidcraft.entity.boss.herobrine.extra;

import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.entity.boss.herobrine.EntityBossHerobrine;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityHerobrineShadow extends EntityHerobrineFireball {

	public float prevRenderYawOffset;
	public float renderYawOffset;
	/** Entity head rotation yaw */
	public float rotationYawHead;
	/** Entity head rotation yaw at previous tick */
	public float prevRotationYawHead;

	public EntityHerobrineShadow(World p_i1767_1_) {
		super(p_i1767_1_);
		rotationYawHead = rotationYaw;
	}

	@SideOnly(Side.CLIENT)
	public EntityHerobrineShadow(World p_i1768_1_, double p_i1768_2_, double p_i1768_4_, double p_i1768_6_, double p_i1768_8_, double p_i1768_10_, double p_i1768_12_) {
		super(p_i1768_1_, p_i1768_2_, p_i1768_4_, p_i1768_6_, p_i1768_8_, p_i1768_10_, p_i1768_12_);
	}

	public EntityHerobrineShadow(World p_i1769_1_, EntityLivingBase p_i1769_2_, double p_i1769_3_, double p_i1769_5_, double p_i1769_7_) {
		super(p_i1769_1_, p_i1769_2_, p_i1769_3_, p_i1769_5_, p_i1769_7_);
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		prevRenderYawOffset = renderYawOffset;
		prevRotationYawHead = rotationYawHead;
		prevRotationYaw = rotationYaw;
		prevRotationPitch = rotationPitch;
	}

	@Override
	protected boolean isFireballFiery() {
		return false;
	}

	/**
	 * Called when this EntityFireball hits a block or entity.
	 */
	@Override
	protected void onImpact(RayTraceResult p_70227_1_) {
		if (!world.isRemote && !(p_70227_1_.entityHit != null && p_70227_1_.entityHit instanceof EntityVoidBoss)) {
			if (p_70227_1_.entityHit != null) {
				p_70227_1_.entityHit.attackEntityFrom(DamageSource.MAGIC, 15.0F);
			} else if (p_70227_1_.typeOfHit == RayTraceResult.Type.BLOCK) {

			}
			setDead();
		}
	}

}
