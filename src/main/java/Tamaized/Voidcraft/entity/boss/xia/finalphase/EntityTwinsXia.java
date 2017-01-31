package Tamaized.Voidcraft.entity.boss.xia.finalphase;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import Tamaized.TamModized.helper.PacketHelper;
import Tamaized.TamModized.helper.PacketHelper.PacketWrapper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.EntityVoidNPC;
import Tamaized.Voidcraft.entity.boss.render.bossBar.RenderAlternateBossBars;
import Tamaized.Voidcraft.entity.boss.render.bossBar.RenderAlternateBossBars.AlternateBossBarWrapper;
import Tamaized.Voidcraft.entity.boss.render.bossBar.RenderAlternateBossBars.IAlternateBoss;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import Tamaized.Voidcraft.network.IEntitySync;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia2.phases.EntityAIXia2Phase3;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public abstract class EntityTwinsXia extends EntityVoidNPC implements IEntitySync, IAlternateBoss {

	protected Entity watchedEntity;
	protected ArrayList<Class> watchedClass = new ArrayList<Class>();
	private boolean frozen = false;
	private double maxWatchDistance = 16 * 8;

	public final AlternateBossBarWrapper bossBarWrapper;

	private EntityAIXia2Phase3 ai;

	public EntityTwinsXia(World worldIn) {
		super(worldIn);
		isImmuneToFire = true;
		bossBarWrapper = new RenderAlternateBossBars.AlternateBossBarWrapper(this, getBossBarColor(), BossInfo.Overlay.PROGRESS);
		watchedClass.add(EntityPlayer.class);
		watchNew();
	}

	public EntityTwinsXia(World worldIn, EntityAIXia2Phase3 xiaAI) {
		this(worldIn);
		ai = xiaAI;
	}

	protected abstract BossInfo.Color getBossBarColor();

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(25.0D);
	}

	@Override
	public float getHealthPerc() {
		return getHealth() / getMaxHealth();
	}

	public boolean isFrozen() {
		return frozen;
	}

	protected void setFrozen() {
		setInvul(true);
		frozen = true;
		setHealth(0);
		sendPacketUpdates();
	}

	@Override
	public void encodePacketData(DataOutputStream stream) throws IOException {
		stream.writeBoolean(frozen);
	}

	@Override
	public void decodePacketData(ByteBufInputStream stream) throws IOException {
		frozen = stream.readBoolean();
	}

	@Override
	public void addPotionEffect(PotionEffect potioneffectIn) {
		Potion pot = potioneffectIn.getPotion();
		if (pot != VoidCraft.potions.fireSheathe && pot != VoidCraft.potions.frostSheathe && pot != VoidCraft.potions.litSheathe && pot != VoidCraft.potions.acidSheathe) return;
		super.addPotionEffect(potioneffectIn);
		if (!world.isRemote) {
			try {
				PacketWrapper packet = PacketHelper.createPacket(VoidCraft.channel, VoidCraft.networkChannelName, ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.SHEATHE));
				DataOutputStream stream = packet.getStream();
				stream.writeInt(getEntityId());
				stream.writeInt(Potion.getIdFromPotion(pot));
				stream.writeInt(potioneffectIn.getDuration());
				packet.sendPacket(new TargetPoint(world.provider.getDimension(), posX, posY, posZ, 64));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void onDeathUpdate() {
		if (!this.world.isRemote) {
			if (ai != null && ai.getEntity() != null && ai.getEntity().getCurrentPhase() == 3) {
				setFrozen();
			} else {
				setDead();
			}
		}
	}

	@Override
	public void onLivingUpdate() {
		if (!world.isRemote) {
			if (ai == null || ai.getEntity() == null || ai.getEntity().getCurrentPhase() != 3) setDead();
		}
		if (!frozen) {
			update();
			if (!world.isRemote) {
				updateMotion();
				if (ticksExisted % 20 == 0) watchNew();
				updateLook();
				// collideWithEntities(world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox()));
				attackEntitiesInList(world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox()));
			}
		} else {
			if (!world.isRemote) {
				setHealth(getHealth() + 0.25F);
				if (getHealth() >= getMaxHealth()) {
					setHealth(getMaxHealth());
					setInvul(false);
					frozen = false;
					sendPacketUpdates();
				}
			}
		}
		super.onLivingUpdate();
	}

	protected abstract void update();

	private void collideWithEntities(List p_70970_1_) {
		double d0 = (getEntityBoundingBox().minX + getEntityBoundingBox().maxX) / 2.0D;
		double d1 = (getEntityBoundingBox().minZ + getEntityBoundingBox().maxZ) / 2.0D;
		Iterator iterator = p_70970_1_.iterator();

		while (iterator.hasNext()) {
			Entity entity = (Entity) iterator.next();

			if (entity instanceof EntityLivingBase && !(entity instanceof EntityVoidNPC) && !(entity instanceof EntityWitherbrine)) {
				double d2 = entity.posX - d0;
				double d3 = entity.posZ - d1;
				double d4 = d2 * d2 + d3 * d3;
				entity.addVelocity(d2 / d4 * 4.0D, 0.20000000298023224D, d3 / d4 * 4.0D);
			}
		}
	}

	private void attackEntitiesInList(List p_70971_1_) {
		for (int i = 0; i < p_70971_1_.size(); ++i) {
			Entity entity = (Entity) p_70971_1_.get(i);

			if (entity instanceof EntityLivingBase && !(entity instanceof EntityVoidNPC)) {
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), 10.0F);
			}
		}
	}

	private void updateLook() {
		if (watchedEntity != null) {
			getLookHelper().setLookPosition(watchedEntity.posX, watchedEntity.posY + (double) watchedEntity.getEyeHeight(), watchedEntity.posZ, 10.0F, (float) getVerticalFaceSpeed());
			double d0 = watchedEntity.posX - posX;
			double d2 = watchedEntity.posZ - posZ;
			float f = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = MathHelper.wrapDegrees(f - rotationYaw);
			rotationYaw = rotationYaw + f3;
		}
	}

	private void watchNew() {
		ArrayList<Entity> list = new ArrayList<Entity>();
		for (Class c : watchedClass) {
			list.addAll(world.getEntitiesWithinAABB(c, getEntityBoundingBox().expand(maxWatchDistance, 64.0D, maxWatchDistance)));
		}
		Random rand = world.rand;
		watchedEntity = list.size() > 0 ? list.get(rand.nextInt(list.size())) : null;
	}

	private void updateMotion() {
		if (watchedEntity == null) return;
		double x = posX;
		double y = posY;
		double z = posZ;

		double px = watchedEntity.posX;
		double py = watchedEntity.posY;
		double pz = watchedEntity.posZ;

		double dx = 0;
		double dy = 0;
		double dz = 0;

		double moveSpeed = getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();

		dx = px - x;
		dx = dx > moveSpeed ? moveSpeed : dx;
		dx = dx < -moveSpeed ? -moveSpeed : dx;

		dy = py - y;
		dy = dy > moveSpeed ? moveSpeed : dy;
		dy = dy < -moveSpeed ? -moveSpeed : dy;

		dz = pz - z;
		dz = dz > moveSpeed ? moveSpeed : dz;
		dz = dz < -moveSpeed ? -moveSpeed : dz;

		move(MoverType.SELF, dx, dy, dz);
	}

}
