package Tamaized.Voidcraft.xiaCastle.logic.battle.Xia2.phases;

import java.util.ArrayList;

import Tamaized.Voidcraft.entity.boss.dragon.EntityDragonOld;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia2;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.EntityDragonXia;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.EntityWitherbrine;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.EntityZolXia;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.render.EntityDolXia;
import Tamaized.Voidcraft.events.client.DebugEvent;
import Tamaized.Voidcraft.network.IVoidBossAIPacket;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityVoidNPCAIBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;

public class EntityAIXia2Phase3 extends EntityVoidNPCAIBase<EntityBossXia2> {

	private final AxisAlignedBB litBox;
	private final int litStrikeTick = 20 * 2;

	private EntityDolXia dol;
	private EntityZolXia zol;
	private EntityDragonOld dragon;
	private EntityWitherbrine wither;

	public EntityAIXia2Phase3(EntityBossXia2 entityBoss, ArrayList<Class> c) {
		super(entityBoss, c);
		litBox = new AxisAlignedBB(entityBoss.getPosition().add(-5, 0, -5), entityBoss.getPosition().add(5, 1, 5));
	}

	@Override
	protected void preInit() {

	}

	@Override
	protected void postInit() {
		if (!world.isRemote) {
			dol = new EntityDolXia(world, this);
			zol = new EntityZolXia(world, this);
			dragon = new EntityDragonXia(world, this);
			wither = new EntityWitherbrine(world, this);
			wither.ignite();

			dol.setPositionAndUpdate(getEntity().posX - 15, getEntity().posY, getEntity().posZ);
			zol.setPositionAndUpdate(getEntity().posX + 15, getEntity().posY, getEntity().posZ);
			dragon.setPositionAndUpdate(getEntity().posX, getEntity().posY, getEntity().posZ + 15);
			wither.setPositionAndUpdate(getEntity().posX, getEntity().posY, getEntity().posZ - 15);

			world.spawnEntity(dol);
			world.spawnEntity(zol);
			world.spawnEntity(dragon);
			world.spawnEntity(wither);

			getEntity().setArmRotations(135, 135, 45, -45, true);
		}
	}

	@Override
	protected void update() {
		if (!world.isRemote) {
			if (dol == null || zol == null || dragon == null || wither == null) return;
			if (!(dol instanceof EntityDolXia) || !(zol instanceof EntityZolXia) || !(dragon instanceof EntityDragonOld) || !(wither instanceof EntityWitherbrine)) return;
			if (zol.getDistanceToEntity(getEntity()) >= 100) zol.setPositionAndUpdate(getEntity().posX, getEntity().posY, getEntity().posZ);
			if (dol.getDistanceToEntity(getEntity()) >= 100) dol.setPositionAndUpdate(getEntity().posX, getEntity().posY, getEntity().posZ);
			if (dragon.getDistanceToEntity(getEntity()) >= 100) dragon.setPositionAndUpdate(getEntity().posX, getEntity().posY, getEntity().posZ);
			if (wither.getDistanceToEntity(getEntity()) >= 100) wither.setPositionAndUpdate(getEntity().posX, getEntity().posY, getEntity().posZ);
			if (!world.loadedEntityList.contains(dol) && !dol.isDead) world.spawnEntity(dol);
			if (!world.loadedEntityList.contains(zol) && !zol.isDead) world.spawnEntity(zol);
			if (!world.loadedEntityList.contains(wither) && !wither.isDead) world.spawnEntity(wither);
			if (!world.loadedEntityList.contains(dragon) && !dragon.isDead) world.spawnEntity(dragon);
			if (tick % litStrikeTick == 0) {
				int lx = (int) ((world.rand.nextDouble() * (litBox.maxX - litBox.minX)) + litBox.minX);
				int ly = 70;
				int lz = (int) ((world.rand.nextDouble() * (litBox.maxZ - litBox.minZ)) + litBox.minZ);
				EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, lx, ly, lz, false);
				world.addWeatherEffect(entitylightningbolt);
			}
			updateLook();
			updateSphere();
		}
	}

	private void updateSphere() {
		if (world.isRemote) return;
		if (getEntity().shouldSphereRender()) {
			if (zol.isFrozen() && dol.isFrozen() && wither.isDead && dragon.isDead) {
				getEntity().setSphereState(false);
				getEntity().setInvulnerable(false);
			}
		} else {
			if (!zol.isFrozen() || !dol.isFrozen()) {
				getEntity().setSphereState(true);
				getEntity().setInvulnerable(true);
			}
		}
	}

	@Override
	public void doAction(BlockPos pos) {

	}

	@Override
	public void readPacket(IVoidBossAIPacket packet) {

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
