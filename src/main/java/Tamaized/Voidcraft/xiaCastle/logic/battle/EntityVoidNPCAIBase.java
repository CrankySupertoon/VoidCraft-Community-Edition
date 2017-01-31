package Tamaized.Voidcraft.xiaCastle.logic.battle;

import java.util.ArrayList;

import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;
import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.network.IVoidBossAIPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class EntityVoidNPCAIBase<T extends EntityVoidBoss> extends EntityAIBase {

	private boolean execute = false;

	private T entity;
	protected World world;

	/** The closest entity which is being watched by this one. */
	protected Entity closestEntity;
	/** This is the Maximum distance that the AI will look for the Entity */
	protected float maxDistanceForPlayer = 30;
	protected ArrayList<Class> watchedClass = new ArrayList<Class>();

	private double[] spawnLoc = new double[3];
	private Vec3d pos;

	protected int tick = 1;
	private int tick_updateClosestEntity = 2 * 20;
	
	public EntityVoidNPCAIBase(T entityBoss, ArrayList<Class> c) {
		watchedClass = new ArrayList<Class>();
		watchedClass.addAll(c);
		entity = entityBoss;
		pos = new Vec3d(entity.posX, entity.posY, entity.posZ);
		world = entityBoss.world;
	}

	@Override
	public boolean shouldExecute() {
		return (execute && entity != null);
	}

	public void kill() {
		execute = false;
		entity.posX = spawnLoc[0];
		entity.posY = spawnLoc[1];
		entity.posZ = spawnLoc[2];
		entity = null;
	}

	protected abstract void preInit();

	public final void Init() {
		preInit();
		spawnLoc[0] = entity.posX;
		spawnLoc[1] = entity.posY;
		spawnLoc[2] = entity.posZ;
		postInit();
		execute = true;
	}

	protected abstract void postInit();

	@Override
	public final void updateTask() {
		if (!shouldExecute() || world.isRemote) return;
		if (tick % tick_updateClosestEntity == 0) updateClosest();
		update();
		tick++;
	}

	protected void updateClosest() {
		for (Class c : watchedClass) {
			Entity e = getEntity().world.findNearestEntityWithinAABB(c, getEntity().getEntityBoundingBox().expand((double) maxDistanceForPlayer, 30.0D, (double) maxDistanceForPlayer), getEntity());
			if (e != null) {
				closestEntity = e;
				break;
			}
			closestEntity = null;
		}
	}

	protected abstract void update();

	/**
	 * Called from {@link TileEntityAIBlock#boom()}
	 */
	public abstract void doAction(BlockPos pos);

	public T getEntity() {
		return entity;
	}

	public Vec3d getPosition() {
		return pos;
	}

	public BlockPos getBlockPosition() {
		return new BlockPos(pos.xCoord, Math.ceil(pos.yCoord), pos.zCoord);
	}

	public abstract void readPacket(IVoidBossAIPacket packet);

}
