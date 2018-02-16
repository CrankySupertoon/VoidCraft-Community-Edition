package tamaized.dalquor.common.xiacastle.logic.battle.xia2.phases;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import tamaized.dalquor.common.entity.boss.herobrine.extra.EntityHerobrineFireball;
import tamaized.dalquor.common.entity.boss.xia.EntityBossXia2;
import tamaized.dalquor.common.entity.boss.xia.EntityBossXia2.Xia2TookDamagePacket;
import tamaized.dalquor.common.entity.nonliving.ProjectileDisintegration;
import tamaized.dalquor.common.xiacastle.logic.battle.EntityVoidNPCAIBase;
import tamaized.dalquor.network.IVoidBossAIPacket;
import tamaized.dalquor.registry.VoidCraftPotions;

import java.util.List;

public class EntityAIXia2Phase1 extends EntityVoidNPCAIBase<EntityBossXia2> {

	private static final int ACTION_FIREBALL = 0;
	private static final int ACTION_LITSTRIKE = 1;
	private static final int ACTION_DISINT = 2;
	private static final int ACTION_VOIDICINFUSION = 3;
	private final AxisAlignedBB litBox;
	private final int litStrikeTick = 20 * 2;
	private final int actionTick = 20 * 5;
	private int resetAnimationTick = 0;

	public EntityAIXia2Phase1(EntityBossXia2 entityBoss, List<Class> c) {
		super(entityBoss, c);
		litBox = new AxisAlignedBB(entityBoss.getPosition().add(-5, 0, -5), entityBoss.getPosition().add(5, 1, 5));
	}

	@Override
	protected void preInit() {

	}

	@Override
	protected void postInit() {

	}

	@Override
	protected void update() {
		if (resetAnimationTick == 0) {
			resetAnimationTick--;
			getEntity().setLimbRotations(0, 0, 0, 0);
		} else if (resetAnimationTick >= 0) {
			resetAnimationTick--;
		}
		if (tick % litStrikeTick == 0) {
			int lx = (int) ((world.rand.nextDouble() * (litBox.maxX - litBox.minX)) + litBox.minX);
			int ly = 70;
			int lz = (int) ((world.rand.nextDouble() * (litBox.maxZ - litBox.minZ)) + litBox.minZ);
			EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, lx, ly, lz, false);
			world.addWeatherEffect(entitylightningbolt);
		}
		updateLook();
		if (tick % (actionTick) == 0 && closestEntity != null)
			doAction(getBlockPosition());
	}

	@Override
	public void doAction(BlockPos pos) {
		switch (world.rand.nextInt(4)) {
			default:
			case ACTION_FIREBALL:
				getEntity().setLimbRotations(90, 0, 0, 0);
				resetAnimationTick = 20 * 2;
				double d5 = closestEntity.posX - getEntity().posX;
				double d6 = closestEntity.getEntityBoundingBox().minY + (double) (closestEntity.height / 2.0F) - (getEntity().posY + (double) (getEntity().height / 2.0F));
				double d7 = closestEntity.posZ - getEntity().posZ;
				EntityHerobrineFireball entitylargefireball = new EntityHerobrineFireball(getEntity().world, getEntity(), d5, d6, d7);
				double d8 = 4.0D;
				Vec3d vec3 = getEntity().getLook(1.0F);
				entitylargefireball.posX = getEntity().posX;// + vec3.xCoord * d8;
				entitylargefireball.posY = getEntity().posY + (double) (getEntity().height / 2.0F) + 0.5D;
				entitylargefireball.posZ = getEntity().posZ;// + vec3.zCoord * d8;
				getEntity().world.spawnEntity(entitylargefireball);
				break;
			case ACTION_LITSTRIKE:
				getEntity().setLimbRotations(0, 180, 0, 0);
				resetAnimationTick = 20 * 2;
				double x = closestEntity.posX;
				double y = closestEntity.posY;
				double z = closestEntity.posZ;
				EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, x, y, z, false);
				entitylightningbolt.setLocationAndAngles(x, y + 1 + entitylightningbolt.getYOffset(), z, closestEntity.rotationYaw, closestEntity.rotationPitch);
				world.addWeatherEffect(entitylightningbolt);
				break;
			case ACTION_DISINT:
				if (closestEntity instanceof EntityLivingBase) {
					getEntity().setLimbRotations(0, 90, 0, 0);
					resetAnimationTick = 20 * 2;
					ProjectileDisintegration disint = new ProjectileDisintegration(world, getEntity(), (EntityLivingBase) closestEntity, 10.0F);
					world.spawnEntity(disint);
				}
				break;
			case ACTION_VOIDICINFUSION:
				getEntity().setLimbRotations(90, 90, 45, -45);
				resetAnimationTick = 20 * 2;
				if (closestEntity instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) closestEntity;
					player.addPotionEffect(new PotionEffect(VoidCraftPotions.voidicInfusion, 20 * 5));
				}
				break;
		}
	}

	@Override
	public void readPacket(IVoidBossAIPacket packet) {
		if (packet instanceof Xia2TookDamagePacket) {
			getEntity().setLimbRotations(135, 135, 45, -45);
			resetAnimationTick = 20 * 2;
			world.newExplosion(getEntity(), getEntity().posX, getEntity().posY + (double) getEntity().getEyeHeight(), getEntity().posZ, 2.0F, false, true);
			world.playBroadcastSound(1023, getBlockPosition(), 0);
		}
	}

	private void updateLook() {
		if (closestEntity != null) {
			getEntity().getLookHelper().setLookPosition(closestEntity.posX, closestEntity.posY + (double) closestEntity.getEyeHeight(), closestEntity.posZ, 10.0F, (float) getEntity().getVerticalFaceSpeed());
			double d0 = closestEntity.posX - getEntity().posX;
			double d2 = closestEntity.posZ - getEntity().posZ;
			float f = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = MathHelper.wrapDegrees(f - getEntity().rotationYaw);
			getEntity().rotationYaw = getEntity().rotationYaw + f3;
		}
	}

}
