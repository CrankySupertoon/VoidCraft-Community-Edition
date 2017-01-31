package Tamaized.Voidcraft.capabilities.voidicInfusion;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.TamModized.helper.PacketHelper;
import Tamaized.TamModized.helper.PacketHelper.PacketWrapper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicPower.IVoidicPowerCapability;
import Tamaized.Voidcraft.damageSources.DamageSourceVoidicInfusion;
import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.entity.EntityVoidMob;
import Tamaized.Voidcraft.entity.EntityVoidNPC;
import Tamaized.Voidcraft.entity.boss.dragon.EntityDragonOld;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public class VoidicInfusionCapabilityHandler implements IVoidicInfusionCapability {

	public static final ResourceLocation ID = new ResourceLocation(VoidCraft.modid, "VoidicInfusionCapabilityHandler");

	private int infusion = 0;
	private int maxInfusion = 6000;

	private float preInfusionHP = 20.0F;
	private float postInfusionHP = 20.0F;

	private boolean hasFlight = false;
	private int xiaDefeats = 0;

	private boolean hasLoaded = false;

	private int tick = 1;

	@Override
	public void update(EntityLivingBase entity) {
		if (entity == null || entity.world.isRemote || entity instanceof EntityVoidMob || entity instanceof EntityVoidNPC || entity instanceof EntityWither || entity instanceof EntityDragon || entity instanceof EntityDragonOld) return;
		handleInfusionGain(entity);
		doHealthChecks(entity);
		handleEffects(entity);
		if (tick % 20 == 0) sendPacketUpdates(entity);
		tick++;
	}

	private void handleInfusionGain(EntityLivingBase entity) {
		boolean flag = true;
		ItemStack mainHand = entity.getHeldItemMainhand();
		ItemStack offHand = entity.getHeldItemOffhand();
		if ((!mainHand.isEmpty() && mainHand.getItem() == VoidCraft.items.voidicSuppressor)) {
			IVoidicPowerCapability cap = entity.getHeldItemMainhand().getCapability(CapabilityList.VOIDICPOWER, null);
			if (cap != null && cap.getCurrentPower() > 0) {
				cap.drain(1);
				cap.sendUpdates(null, 0, entity.getHeldItemMainhand());
				flag = false;
			}
		} else if (!offHand.isEmpty() && offHand.getItem() == VoidCraft.items.voidicSuppressor) {
			IVoidicPowerCapability cap = entity.getHeldItemOffhand().getCapability(CapabilityList.VOIDICPOWER, null);
			if (cap != null && cap.getCurrentPower() > 0) {
				cap.drain(1);
				cap.sendUpdates(null, 0, entity.getHeldItemOffhand());
				flag = false;
			}
		} else if (entity.getActivePotionEffect(VoidCraft.potions.voidicInfusionImmunity) != null) {
			flag = false;
		}
		if (entity.world.provider.getDimension() == VoidCraft.config.getDimensionIDvoid() && flag) {
			if (entity.onGround && (entity.world.getBlockState(entity.getPosition().down()).getBlock() == VoidCraft.blocks.blockVoidbrick || entity.world.getBlockState(entity.getPosition().down()).getBlock() == VoidCraft.blocks.blockVoidBrickHalfSlab || entity.world.getBlockState(entity.getPosition().down()).getBlock() == VoidCraft.blocks.blockVoidstairs || entity.world.getBlockState(entity.getPosition().down(2)).getBlock() == VoidCraft.blocks.blockVoidfence || entity.world.getBlockState(entity.getPosition().down()).getBlock() == VoidCraft.blocks.blockVoidBrickDoubleSlab)) {

			} else {
				infusion++;
			}
			if (infusion > maxInfusion) infusion = maxInfusion;
		} else {
			infusion -= 5;
			if (infusion < 0) infusion = 0;
		}
	}

	private void doHealthChecks(EntityLivingBase living) {
		if (!living.world.isRemote) {
			float i = 0;
			for (IAttributeInstance att : living.getAttributeMap().getAllAttributes()) {
				if (att.getAttribute() == SharedMonsterAttributes.MAX_HEALTH) {
					for (AttributeModifier mod : att.getModifiers()) {
						i += mod.getAmount();
					}
				}
			}
			float diff = (living.getMaxHealth() - i) - postInfusionHP;
			preInfusionHP += diff;
			postInfusionHP = preInfusionHP * (1F - getInfusionPerc());
			if (postInfusionHP < 0.5F) postInfusionHP = 0.5F;
			living.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(postInfusionHP);
		}
	}

	private void handleEffects(EntityLivingBase entity) {
		if (infusion >= maxInfusion) {
			if (entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entity;
				if (!player.capabilities.isCreativeMode) {
					entity.attackEntityFrom(new DamageSourceVoidicInfusion(), entity.getMaxHealth());
				}
			} else {
				entity.attackEntityFrom(new DamageSourceVoidicInfusion(), entity.getMaxHealth());
			}
			return;
		}
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if (canFly()) {
				player.capabilities.allowFlying = true;
				hasFlight = true;
			} else {
				if (hasFlight) {
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
					player.capabilities.disableDamage = false;
					hasFlight = false;
				}
			}
		}
	}

	@Override
	public float getPreInfusionHP() {
		return preInfusionHP;
	}

	@Override
	public void setPreInfusionHP(float val) {
		preInfusionHP = val;
	}

	@Override
	public float getPostInfusionHP() {
		return postInfusionHP;
	}

	@Override
	public void setPostInfusionHP(float val) {
		postInfusionHP = val;
	}

	@Override
	public int getInfusion() {
		return infusion;
	}

	@Override
	public void addInfusion(int amount) {
		infusion += amount;
	}

	@Override
	public void setInfusion(int i) {
		infusion = i;
	}

	@Override
	public int getMaxInfusion() {
		return maxInfusion;
	}

	@Override
	public void setMaxInfusion(int i) {
		maxInfusion = i;
	}

	@Override
	public float getInfusionPerc() {
		return (float) infusion / (float) maxInfusion;
	}

	@Override
	public boolean canFly() {
		return getInfusionPerc() >= 0.75F;
	}

	@Override
	public int getXiaDefeats() {
		return xiaDefeats;
	}

	@Override
	public void setXiaDefeats(int amount) {
		xiaDefeats = amount;
	}

	@Override
	public boolean hasLoaded() {
		return hasLoaded;
	}

	@Override
	public void load(EntityLivingBase living) {
		if (maxInfusion < 6000) maxInfusion = 6000;
		float i = 0;
		for (IAttributeInstance att : living.getAttributeMap().getAllAttributes()) {
			if (att.getAttribute() == SharedMonsterAttributes.MAX_HEALTH) {
				for (AttributeModifier mod : att.getModifiers()) {
					i += mod.getAmount();
				}
			}
		}
		// preInfusionHP = living.getMaxHealth() / (1F - getInfusionPerc());
		postInfusionHP = living.getMaxHealth() - i;
		if (postInfusionHP < 0.5F) postInfusionHP = 0.5F;
		hasLoaded = true;
	}

	@Override
	public void copyFrom(IVoidicInfusionCapability cap) {
		// setInfusion(cap.getInfusion());
		setMaxInfusion(cap.getMaxInfusion());
		// setPostInfusionHP(cap.getPostInfusionHP());
		setXiaDefeats(cap.getXiaDefeats());
	}

	@Override
	public void decodePacket(ByteBufInputStream stream) throws IOException {
		setInfusion(stream.readInt());
		setMaxInfusion(stream.readInt());
		setPostInfusionHP(stream.readFloat());
		setXiaDefeats(stream.readInt());
	}

	private void sendPacketUpdates(EntityLivingBase living) {
		if (living == null) return;
		try {
			PacketWrapper packet = PacketHelper.createPacket(VoidCraft.channel, VoidCraft.networkChannelName, ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.INFUSION_UPDATE));
			DataOutputStream stream = packet.getStream();
			stream.writeInt(living.getEntityId());
			stream.writeInt(infusion);
			stream.writeInt(maxInfusion);
			stream.writeFloat(postInfusionHP);
			stream.writeFloat(xiaDefeats);
			packet.sendPacket(new TargetPoint(living.dimension, living.posX, living.posY, living.posZ, 16 * 8));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (living instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) living;
			if (canFly()) player.capabilities.allowFlying = true;
			player.sendPlayerAbilities();
		}
	}

}
