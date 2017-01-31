package Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.phases;

import java.util.ArrayList;
import java.util.Random;

import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia.XiaTookDamagePacket;
import Tamaized.Voidcraft.network.IVoidBossAIPacket;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityVoidNPCAIBase;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.phases.actions.XiaPhase2ActionSword;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class EntityAIXiaPhase2<T extends EntityBossXia> extends EntityVoidNPCAIBase<T> {

	private int actionTick = 20 * 10;

	private XiaPhase2ActionSword actionSword;

	private AxisAlignedBB teleportationBox = new AxisAlignedBB(-18, 0, -25, 18, 5, 6);
	private boolean isTeleporting = false;

	private Action currAction = Action.IDLE;

	public static enum Action {
		IDLE, SWORD, FOLLOW
	}

	public EntityAIXiaPhase2(T entityBoss, ArrayList<Class> c) {
		super(entityBoss, c);
		actionSword = new XiaPhase2ActionSword(entityBoss, getPosition());
		watchNew();
	}

	@Override
	protected void updateClosest() {

	}

	@Override
	protected void preInit() {

	}

	@Override
	public void postInit() {
		teleportationBox = new AxisAlignedBB(-18, 0, -25, 18, 5, 6);
	}

	@Override
	protected void update() {
		updateLook();
		switch (currAction) {
			case FOLLOW:
				updateMotion();
				double ezMin = closestEntity.getEntityBoundingBox().minZ;
				double ezMax = closestEntity.getEntityBoundingBox().maxZ;
				double exMin = closestEntity.getEntityBoundingBox().minX;
				double exMax = closestEntity.getEntityBoundingBox().maxX;
				if (getEntity().posZ >= ezMin && getEntity().posZ <= ezMax && getEntity().posX >= exMin && getEntity().posX <= exMax) {
					closestEntity.attackEntityFrom(DamageSource.causeMobDamage(getEntity()), 60);
					ItemStack stack = getEntity().getHeldItem(EnumHand.MAIN_HAND);
					if (!stack.isEmpty() && closestEntity instanceof EntityLivingBase) stack.getItem().hitEntity(stack, (EntityLivingBase) closestEntity, getEntity());
					getEntity().swingArm(EnumHand.MAIN_HAND);
					doTeleport();
					currAction = Action.IDLE;
				}
			case IDLE:
				if (tick % (20 * 2) == 0) {
					watchNewAndTeleport();
					// switch (world.rand.nextInt(1)) {
					// case 0:
					// beginSwordAction();
					// break;
					// case 1:
					// watchNewAndTeleport();
					// break;
					// default:
					// watchNewAndTeleport();
					// break;
					// }
				}
				break;
			case SWORD:
				actionSword.update();
				if (actionSword.isDone()) finishAction();
				break;
			default:
				break;
		}
	}

	private void updateMotion() {
		double x = getEntity().posX;
		double y = getEntity().posY;
		double z = getEntity().posZ;

		double px = closestEntity.posX;
		double py = closestEntity.posY;
		double pz = closestEntity.posZ;

		double dx = 0;
		double dy = 0;
		double dz = 0;

		boolean xPos;
		boolean zPos;

		if (px > x) xPos = true;
		else xPos = false;

		if (pz > z) zPos = true;
		else zPos = false;

		if (x < px) dx = getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
		else if (x == px) dx = 0;
		else if (!xPos && (x - px) < getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()) {
			dx = 0;
			getEntity().posX = px;
		} else if (xPos && (px - x) < getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()) {
			dx = 0;
			getEntity().posX = px;
		} else if (px < x) dx = -(getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());

		if (y < py) dy = 0.2;
		else if (y == py) dy = 0.0;
		else {
			if (Math.abs(y - py) < 0.2) getEntity().posY = py;
			dy = -0.2;
		}

		double ezMin = closestEntity.getEntityBoundingBox().minZ;
		double ezMax = closestEntity.getEntityBoundingBox().maxZ;
		double exMin = closestEntity.getEntityBoundingBox().minX;
		double exMax = closestEntity.getEntityBoundingBox().maxX;

		if (z < pz) dz = getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
		else if (z == pz) dz = 0;
		else if (!zPos && (z - pz) < getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()) {
			dz = 0;
			getEntity().posZ = pz;
		} else if (zPos && (pz - z) < getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()) {
			dz = 0;
			getEntity().posZ = pz;
		} else if (pz < z) dz = -(getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());

		getEntity().posX += dx;
		getEntity().posZ += dz;
		getEntity().posY += dy;
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

	@Override
	public void doAction(BlockPos pos) {

	}

	@Override
	public void readPacket(IVoidBossAIPacket packet) {
		if (packet instanceof XiaTookDamagePacket) {
			doTeleport();
			currAction = Action.IDLE;
		}
	}

	private void finishAction() {
		currAction = Action.IDLE;
		tick = 1;
	}

	private void beginSwordAction() {
		actionSword.init();
		currAction = Action.SWORD;
	}

	private void watchNewAndTeleport() {
		watchNew();
		if (closestEntity == null) return;
		Vec3d vecA = new Vec3d(getEntity().posX, getEntity().posY, getEntity().posZ);
		Vec3d vecB = new Vec3d(closestEntity.posX, closestEntity.posY, closestEntity.posZ);

		float dist = 0.60f;

		double newPointX = vecA.xCoord + ((vecB.xCoord - vecA.xCoord) * dist);
		double newPointY = vecA.yCoord + ((vecB.yCoord - vecA.yCoord) * dist);
		double newPointZ = vecA.zCoord + ((vecB.zCoord - vecA.zCoord) * dist);

		getEntity().setPosition(newPointX, newPointY, newPointZ);
		currAction = Action.FOLLOW;
	}

	private void watchNew() {
		ArrayList<Entity> list = new ArrayList<Entity>();
		for (Class c : watchedClass) {
			list.addAll(getEntity().world.getEntitiesWithinAABB(c, getEntity().getEntityBoundingBox().expand((double) maxDistanceForPlayer, 30.0D, (double) maxDistanceForPlayer)));
		}
		Random rand = world.rand;
		closestEntity = list.size() > 0 ? list.get(rand.nextInt(list.size())) : null;
	}

	private void doTeleport() {
		Double[] nextLoc = getNextTeleportLocation();
		getEntity().setPositionAndUpdate(getBlockPosition().getX() + nextLoc[0] + 0.5, getBlockPosition().getY() + nextLoc[1], getBlockPosition().getZ() + nextLoc[2] + 0.5);
		isTeleporting = false;
	}

	private Double[] getNextTeleportLocation() {
		Double[] loc = { 0.0D, 0.0D, 0.0D };
		loc[0] = (world.rand.nextDouble() * (teleportationBox.maxX - teleportationBox.minX)) + teleportationBox.minX;
		loc[1] = teleportationBox.maxY;
		loc[2] = (world.rand.nextDouble() * (teleportationBox.maxZ - teleportationBox.minZ)) + teleportationBox.minZ;
		while (world.isAirBlock(new BlockPos(getPosition().xCoord + loc[0], getPosition().yCoord + loc[1], getPosition().zCoord + loc[2]))) {
			loc[1] -= 1.0D;
		}
		return loc;
	}

}
