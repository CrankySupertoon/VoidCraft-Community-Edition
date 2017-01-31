package Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.phases;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import Tamaized.Voidcraft.entity.boss.herobrine.EntityBossHerobrine;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineFireball;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineShadow;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineTNTPrimed;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineWitherSkull;
import Tamaized.Voidcraft.entity.ghost.EntityGhostPlayerBase;
import Tamaized.Voidcraft.handlers.SkinHandler;
import Tamaized.Voidcraft.handlers.SkinHandler.PlayerNameAlias;
import Tamaized.Voidcraft.network.IVoidBossAIPacket;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityVoidNPCAIBase;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class EntityAIHerobrinePhase3<T extends EntityBossHerobrine> extends EntityVoidNPCAIBase<T> {

	private BlockPos loc;

	private int tick_doAction = 3 * 20;
	private int tick_Spawn = 30 * 20;

	private int spawns = 0;
	private int maxSpawns = 1;

	private EntityGhostPlayerBase currGhost;

	private ArrayList<UUID> alreadyUsed = new ArrayList<UUID>();
	private ArrayList<Integer> usedLocs = new ArrayList<Integer>();

	public EntityAIHerobrinePhase3(T entityBoss, ArrayList<Class> c) {
		super(entityBoss, c);
	}

	@Override
	protected void preInit() {

	}

	@Override
	protected void postInit() {
		loc = getEntity().getPosition().add(0, 10, 0);
		for (int x = -10; x <= 10; x++) {
			for (int z = -10; z <= 10; z++) {
				if ((x == 0 && z == 0) || Math.floor(Math.random() * 10) != 0) continue;
				world.setBlockState(getBlockPosition().add(x, -1, z), Blocks.LAVA.getDefaultState());
			}
		}
		for (EntityPlayer player : world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(getBlockPosition().add(-10, -10, -10), getBlockPosition().add(10, 10, 10)))) {
			alreadyUsed.add(player.getGameProfile().getId());
		}
	}

	@Override
	public void kill() {
		super.kill();
		if (currGhost != null) currGhost.setDead();
		currGhost = null;
	}

	@Override
	public void doAction(BlockPos pos) {

	}

	@Override
	public void readPacket(IVoidBossAIPacket packet) {

	}

	@Override
	public void update() {
		updateLook();
		if (tick % tick_doAction == 0) updateAction();
		updateMotion();

		if (getEntity() == null) {
			if (currGhost != null) currGhost.setDead();
			currGhost = null;
		}

		if (tick % tick_Spawn == 0 && spawns < maxSpawns) setRandomGhost(0);

		if (currGhost != null) {
			if (currGhost.hasInteracted()) {
				currGhost.setDead();
				currGhost = null;
				getEntity().doDamage(25);
				spawns--;
			}
		}
	}

	private void setRandomGhost(int j) {
		int i = 0;
		if (j == 0) i = (int) Math.floor(Math.random() * 3);
		else i = j;
		if (i > 3) i = 0;
		if (usedLocs.contains(0) && usedLocs.contains(1) && usedLocs.contains(2) && usedLocs.contains(3)) {
			i = 0;
		} else if (usedLocs.contains(i)) {
			setRandomGhost(i + 1);
			return;
		}
		spawns++;
		PlayerNameAlias alias = getRandomUnusedAlias(0);
		alreadyUsed.add(SkinHandler.getUUID(alias));
		usedLocs.add(i);
		EntityGhostPlayerBase entity = EntityGhostPlayerBase.newInstance(world, alias, true);
		currGhost = entity;
		switch (i) {
			case 0:
				entity.setLocationAndAngles(getBlockPosition().getX() + 8.5, getBlockPosition().getY(), getBlockPosition().getZ() + 0.5, 0, 0);
				entity.rotationYawHead = entity.rotationYaw = entity.prevRotationYaw = entity.prevRotationYawHead = entity.prevRenderYawOffset = entity.renderYawOffset = 90;
				world.spawnEntity(entity);
				break;
			case 1:
				entity.setLocationAndAngles(getBlockPosition().getX() - 7.5, getBlockPosition().getY(), getBlockPosition().getZ() + 0.5, 0, 0);
				entity.rotationYawHead = entity.rotationYaw = entity.prevRotationYaw = entity.prevRotationYawHead = entity.prevRenderYawOffset = entity.renderYawOffset = 270;
				world.spawnEntity(entity);
				break;
			case 2:
				entity.setLocationAndAngles(getBlockPosition().getX() + 0.5, getBlockPosition().getY(), getBlockPosition().getZ() + 8.5, 0, 0);
				entity.rotationYawHead = entity.rotationYaw = entity.prevRotationYaw = entity.prevRotationYawHead = entity.prevRenderYawOffset = entity.renderYawOffset = 180;
				world.spawnEntity(entity);
				break;
			case 3:
				entity.setLocationAndAngles(getBlockPosition().getX() + 0.5, getBlockPosition().getY(), getBlockPosition().getZ() - 7.5, 0, 0);
				entity.rotationYawHead = entity.rotationYaw = entity.prevRotationYaw = entity.prevRotationYawHead = entity.prevRenderYawOffset = entity.renderYawOffset = 0;
				world.spawnEntity(entity);
				break;
			default:
				break;
		}
	}

	private PlayerNameAlias getRandomUnusedAlias(int j) {
		int i = 0;
		if (j == 0) i = (int) Math.floor(Math.random() * PlayerNameAlias.values().length);
		else i = j;
		if (i >= PlayerNameAlias.values().length) i = 0;
		return alreadyUsed.contains(SkinHandler.getUUID(PlayerNameAlias.values()[i])) ? getRandomUnusedAlias(i + 1) : PlayerNameAlias.values()[i];
	}

	private void updateMotion() {
		double y = getEntity().posY;
		double py = loc.getY();
		double dy = 0;

		if (y < py) dy = 0.2;
		else if (y == py) dy = 0.0;
		else {
			getEntity().posY = py;
			dy = 0;
		}
		getEntity().posY += dy;
	}

	private void updateAction() {
		if (closestEntity != null) {

			double watcherX = getEntity().getPosition().getX();
			double watcherY = getEntity().getPosition().getZ();
			double watcherZ = getEntity().getPosition().getX();

			Random rand = new Random();
			switch (rand.nextInt(5)) {
				case 0: // Fireball
					getEntity().world.playRecord(new BlockPos((int) getEntity().posX, (int) getEntity().posY, (int) getEntity().posZ), null);// ((EntityPlayer)null, 1008, new BlockPos((int)theWatcher.posX, (int)theWatcher.posY, (int)theWatcher.posZ), 0);
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
				case 1: // Spawn Creepers
					for (int i = 0; i < 4; i++) {
						EntityHerobrineCreeper creeper = new EntityHerobrineCreeper(getEntity().world);
						creeper.setPosition(getEntity().getPosition().getX() + rand.nextInt(18) - 8, getEntity().getPosition().getY() - 6, getEntity().getPosition().getZ() + rand.nextInt(18) - 8);
						getEntity().world.spawnEntity(creeper);
					}
					break;
				case 2: // Primed TNT
					for (int i = 0; i < 2; i++) {
						EntityHerobrineTNTPrimed tnt = new EntityHerobrineTNTPrimed(getEntity().world);
						tnt.setPosition(getEntity().getPosition().getX() + rand.nextInt(18) - 8, getEntity().getPosition().getY() - 6, getEntity().getPosition().getZ() + rand.nextInt(18) - 8);
						getEntity().world.spawnEntity(tnt);
					}
					break;
				case 3: // Shadow Clone
					double d52 = closestEntity.posX - getEntity().posX;
					double d62 = closestEntity.getEntityBoundingBox().minY + (double) (closestEntity.height / 2.0F) - (getEntity().posY + (double) (getEntity().height / 2.0F));
					double d72 = closestEntity.posZ - getEntity().posZ;

					EntityHerobrineShadow entityHerobrineShadow = new EntityHerobrineShadow(getEntity().world, getEntity(), d52, d62, d72);
					double d82 = 4.0D;
					Vec3d vec32 = getEntity().getLook(1.0F);
					entityHerobrineShadow.posX = getEntity().posX;// + vec3.xCoord * d8;
					entityHerobrineShadow.posY = getEntity().posY + (double) (getEntity().height / 2.0F) + 0.5D;
					entityHerobrineShadow.posZ = getEntity().posZ;// + vec3.zCoord * d8;
					entityHerobrineShadow.prevRotationYaw = getEntity().prevRotationYaw - 180;
					entityHerobrineShadow.rotationYaw = getEntity().rotationYaw - 180;
					entityHerobrineShadow.prevRotationYawHead = getEntity().prevRotationYawHead - 180;
					entityHerobrineShadow.rotationYawHead = getEntity().rotationYawHead - 180;
					getEntity().world.spawnEntity(entityHerobrineShadow);
					break;
				case 4: // Wither Skeleton Spawns with EntityWitherSkulls from the walls
					for (int i = 0; i < 4; i++) {
						EntityWitherSkeleton skele = new EntityWitherSkeleton(getEntity().world);
						skele.setPosition(getEntity().getPosition().getX() + rand.nextInt(18) - 8, getEntity().getPosition().getY() - 6, getEntity().getPosition().getZ() + rand.nextInt(18) - 8);
						getEntity().world.spawnEntity(skele);
					}
					EntityHerobrineWitherSkull skull1 = new EntityHerobrineWitherSkull(getEntity().world, getEntity(), (watcherX - 7) - watcherX, (watcherY - 10) - watcherY, (watcherZ - 0) - watcherZ);
					EntityHerobrineWitherSkull skull2 = new EntityHerobrineWitherSkull(getEntity().world, getEntity(), (watcherX + 7) - watcherX, (watcherY - 10) - watcherY, (watcherZ - 0) - watcherZ);
					EntityHerobrineWitherSkull skull3 = new EntityHerobrineWitherSkull(getEntity().world, getEntity(), (watcherX - 0) - watcherX, (watcherY - 10) - watcherY, (watcherZ - 7) - watcherZ);
					EntityHerobrineWitherSkull skull4 = new EntityHerobrineWitherSkull(getEntity().world, getEntity(), (watcherX - 0) - watcherX, (watcherY - 10) - watcherY, (watcherZ + 7) - watcherZ);
					getEntity().world.spawnEntity(skull1);
					getEntity().world.spawnEntity(skull2);
					getEntity().world.spawnEntity(skull3);
					getEntity().world.spawnEntity(skull4);
					break;
				default:
					break;
			}
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
