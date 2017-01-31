package Tamaized.Voidcraft.entity.mob;

import java.io.DataOutputStream;
import java.io.IOException;

import com.google.common.base.Predicate;

import Tamaized.TamModized.helper.PacketHelper;
import Tamaized.TamModized.helper.PacketHelper.PacketWrapper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.starforge.IStarForgeCapability;
import Tamaized.Voidcraft.entity.EntityVoidMob;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import Tamaized.Voidcraft.starforge.effects.StarForgeEffectList;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public class EntityMobEtherealGuardian extends EntityVoidMob {

	public EntityMobEtherealGuardian(World p_i1738_1_) {
		super(p_i1738_1_);
		isImmuneToFire = true;
		ItemStack stack = new ItemStack(VoidCraft.tools.starforgedSword);
		IStarForgeCapability cap = stack.getCapability(CapabilityList.STARFORGE, null);
		if (cap != null) {
			cap.addEffect(StarForgeEffectList.firstDegreeBurns);
			cap.addEffect(StarForgeEffectList.secondDegreeBurns);
			cap.addEffect(StarForgeEffectList.thirdDegreeBurns);
		}
		setHeldItem(EnumHand.MAIN_HAND, stack);

		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIBreakDoor(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(2, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));

		Predicate ies = new Predicate() {

			public boolean apply(Entity p_82704_1_) {
				if (p_82704_1_ instanceof EntityWitherSkeleton) return false;
				else if (p_82704_1_ instanceof EntityHerobrineCreeper || p_82704_1_ instanceof EntityShulker) return false;
				else return true;
			}

			public boolean apply(Object p_apply_1_) {
				return p_apply_1_ instanceof Entity ? this.apply((Entity) p_apply_1_) : false;
			}
		};

		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySnowman.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true, false, ies));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000000298023224D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(45.0D);
	}

	@Override
	public void onLivingUpdate() {
		if (!world.isRemote) {
			if (getActivePotionEffect(VoidCraft.potions.fireSheathe) == null) {
				clearActivePotions();
				Potion potion = VoidCraft.potions.fireSheathe;
				PotionEffect effect = new PotionEffect(potion, 100);
				addPotionEffect(effect);
				try {
					PacketWrapper packet = PacketHelper.createPacket(VoidCraft.channel, VoidCraft.networkChannelName, ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.SHEATHE));
					DataOutputStream stream = packet.getStream();
					stream.writeInt(getEntityId());
					stream.writeInt(Potion.getIdFromPotion(potion));
					stream.writeInt(effect.getDuration());
					packet.sendPacket(new TargetPoint(world.provider.getDimension(), posX, posY, posZ, 64));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		super.onLivingUpdate();
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return world.rand.nextInt(2) == 0 ? VoidSoundEvents.EntityMobWrathSoundEvents.ambientSound1 : VoidSoundEvents.EntityMobWrathSoundEvents.ambientSound2;
	}

	@Override
	protected SoundEvent getHurtSound() {
		return VoidSoundEvents.EntityMobWrathSoundEvents.hurtSound;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VoidSoundEvents.EntityMobWrathSoundEvents.deathSound;
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	protected Item getDropItem() {
		return VoidCraft.items.voidicPhlogiston;
	}

	/**
	 * Overridden to remove the ability to drop Weapons and Armor
	 */
	@Override
	protected int getExperiencePoints(EntityPlayer player) {
		return this.experienceValue;
	}

}
