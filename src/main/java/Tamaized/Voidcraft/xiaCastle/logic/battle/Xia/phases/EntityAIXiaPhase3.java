package Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.phases;

import java.util.ArrayList;
import java.util.Random;

import Tamaized.TamModized.particles.ParticleHelper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineFireball;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia.XiaTookDamagePacket;
import Tamaized.Voidcraft.entity.nonliving.ProjectileDisintegration;
import Tamaized.Voidcraft.helper.EntityMotionHelper;
import Tamaized.Voidcraft.network.IVoidBossAIPacket;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityVoidNPCAIBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class EntityAIXiaPhase3<T extends EntityBossXia> extends EntityVoidNPCAIBase<T> {

	private final Random rand;
	private int actionTick = 20 * 5;
	private int resetAnimationTick = 0;

	private static final int ACTION_FIREBALL = 0;
	private static final int ACTION_LITSTRIKE = 1;
	private static final int ACTION_ICESPIKE = 2;
	private static final int ACTION_DISINT = 3;
	private static final int ACTION_VOIDICINFUSION = 4;

	public EntityAIXiaPhase3(T entityBoss, ArrayList<Class> c) {
		super(entityBoss, c);
		rand = world.rand;
		watchNew();
	}

	@Override
	protected void updateClosest() {

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
			getEntity().setArmRotations(0, 0, 0, 0, true);
		} else if (resetAnimationTick >= 0) {
			resetAnimationTick--;
		}
		updateLook();
		if (tick % (actionTick) == 0 && closestEntity != null) doAction(getBlockPosition());
	}

	@Override
	public void doAction(BlockPos pos) {
		if (hasSheathe()) {
			switch (rand.nextInt(5)) {
				default:
				case ACTION_FIREBALL:
					getEntity().setArmRotations(90, 0, 0, 0, true);
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
					getEntity().setArmRotations(0, 180, 0, 0, true);
					resetAnimationTick = 20 * 2;
					double x = closestEntity.posX;
					double y = closestEntity.posY;
					double z = closestEntity.posZ;
					EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, x, y, z, false);
					entitylightningbolt.setLocationAndAngles(x, y + 1 + entitylightningbolt.getYOffset(), z, closestEntity.rotationYaw, closestEntity.rotationPitch);
					world.addWeatherEffect(entitylightningbolt);
					break;
				case ACTION_ICESPIKE:
					getEntity().setArmRotations(180, 180, 0, 0, true);
					resetAnimationTick = 20 * 2;
					if (world.isAirBlock(closestEntity.getPosition())) world.setBlockState(closestEntity.getPosition(), VoidCraft.blocks.iceSpike.getDefaultState());
					break;
				case ACTION_DISINT:
					if (closestEntity instanceof EntityLivingBase) {
						getEntity().setArmRotations(0, 90, 0, 0, true);
						resetAnimationTick = 20 * 2;
						ProjectileDisintegration disint = new ProjectileDisintegration(world, getEntity(), (EntityLivingBase) closestEntity, 10.0F);
						world.spawnEntity(disint);
					}
					break;
				case ACTION_VOIDICINFUSION:
					getEntity().setArmRotations(90, 90, 45, -45, true);
					resetAnimationTick = 20 * 2;
					if (closestEntity instanceof EntityPlayer) {
						EntityPlayer player = (EntityPlayer) closestEntity;
						IVoidicInfusionCapability cap = player.getCapability(CapabilityList.VOIDICINFUSION, null);
						if (cap != null) cap.setInfusion(cap.getMaxInfusion() - 1);
					}
					break;
			}
		} else {
			castSheathe();
		}
	}

	private void doBlast() {
		getEntity().setArmRotations(135, 135, 45, -45, true);
		resetAnimationTick = 20 * 2;
		world.playSound((EntityPlayer) null, getEntity().posX, getEntity().posY, getEntity().posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F);
		ParticleHelper.spawnVanillaParticleOnServer(world, EnumParticleTypes.EXPLOSION_HUGE, getEntity().posX, getEntity().posY, getEntity().posZ, 1.0D, 0.0D, 0.0D);

		for (Entity e : world.getEntitiesWithinAABBExcludingEntity(getEntity(), new AxisAlignedBB(getEntity().posX - 5, getEntity().posY - 5, getEntity().posZ - 5, getEntity().posX + 5, getEntity().posY + 5, getEntity().posZ + 5))) {
			double mX = 0;
			double mY = 1;
			double mZ = -5;
			EntityMotionHelper.addMotion(e, mX, mY, mZ);
		}
	}

	private boolean hasSheathe() {
		if (getEntity().getActivePotionEffect(VoidCraft.potions.fireSheathe) != null) {
			return true;
		} else if (getEntity().getActivePotionEffect(VoidCraft.potions.frostSheathe) != null) {
			return true;
		} else if (getEntity().getActivePotionEffect(VoidCraft.potions.litSheathe) != null) {
			return true;
		} else if (getEntity().getActivePotionEffect(VoidCraft.potions.acidSheathe) != null) {
			return true;
		} else if (getEntity().getActivePotionEffect(VoidCraft.potions.voidSheathe) != null) {
			return true;
		}
		return false;
	}

	private void castSheathe() {
		getEntity().setArmRotations(180, 0, 0, 0, true);
		resetAnimationTick = 20 * 2;
		getEntity().clearActivePotions();
		Potion sheathe = VoidCraft.potions.fireSheathe;
		switch (rand.nextInt(5)) {
			case 0:
				sheathe = VoidCraft.potions.fireSheathe;
				break;
			case 1:
				sheathe = VoidCraft.potions.frostSheathe;
				break;
			case 2:
				sheathe = VoidCraft.potions.litSheathe;
				break;
			case 3:
				sheathe = VoidCraft.potions.acidSheathe;
				break;
			case 4:
				sheathe = VoidCraft.potions.voidSheathe;
				break;
			default:
				break;
		}
		getEntity().addPotionEffect(new PotionEffect(sheathe, 20 * 30));
	}

	@Override
	public void readPacket(IVoidBossAIPacket packet) {
		if (packet instanceof XiaTookDamagePacket) {
			doBlast();
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

	private void watchNew() {
		ArrayList<Entity> list = new ArrayList<Entity>();
		for (Class c : watchedClass) {
			list.addAll(getEntity().world.getEntitiesWithinAABB(c, getEntity().getEntityBoundingBox().expand((double) maxDistanceForPlayer, 30.0D, (double) maxDistanceForPlayer)));
		}
		Random rand = world.rand;
		closestEntity = list.size() > 0 ? list.get(rand.nextInt(list.size())) : null;
	}

}
