package Tamaized.Voidcraft.capabilities.voidicInfusion;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;

import Tamaized.TamModized.helper.PacketHelper;
import Tamaized.TamModized.helper.PacketHelper.PacketWrapper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.capabilities.voidicPower.IVoidicPowerCapability;
import Tamaized.Voidcraft.damageSources.DamageSourceVoidicInfusion;
import Tamaized.Voidcraft.entity.EntityVoidMob;
import Tamaized.Voidcraft.entity.EntityVoidNPC;
import Tamaized.Voidcraft.entity.boss.dragon.EntityDragonOld;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class VoidicInfusionCapabilityHandler implements IVoidicInfusionCapability {

	public static final ResourceLocation ID = new ResourceLocation(VoidCraft.modid, "VoidicInfusionCapabilityHandler");

	private int infusion = 0;
	private int maxInfusion = 6000;

	private boolean hasFlight = false;
	private int xiaDefeats = 0;

	private boolean hasLoaded = false;

	private int tick = 1;

	@Override
	public void update(EntityLivingBase entity) {
		if (entity.world == null || entity.world.isRemote || entity == null || entity instanceof EntityVoidMob || entity instanceof EntityVoidNPC || entity instanceof EntityWither || entity instanceof EntityDragon || entity instanceof EntityDragonOld) return;
		handleInfusionGain(entity);
		if (tick % 10 == 0) doHealthChecks(entity);
		handleEffects(entity);
		if (tick % 20 == 0) sendPacketUpdates(entity);
		tick++;
		if (infusion >= 3000 && entity.hasCapability(CapabilityList.VADEMECUM, null)) {
			IVadeMecumCapability cap = entity.getCapability(CapabilityList.VADEMECUM, null);
			if (!cap.hasCategory(IVadeMecumCapability.Category.Voice)) {
				if (cap.hasCategory(IVadeMecumCapability.Category.Flame)) {
					if (cap.hasCategory(IVadeMecumCapability.Category.Freeze)) {
						if (cap.hasCategory(IVadeMecumCapability.Category.AcidSpray)) {
							if (cap.hasCategory(IVadeMecumCapability.Category.Shock)) {
								cap.addCategory(entity, IVadeMecumCapability.Category.Voice);
								entity.sendMessage(new TextComponentTranslation("voidcraft.VadeMecum.voice.Voice"));
							}
						}
					}
				}
			}
		}
	}

	private void handleInfusionGain(EntityLivingBase entity) {
		IVadeMecumCapability vade = entity.getCapability(CapabilityList.VADEMECUM, null);
		int gain = 1;
		boolean flag = (vade == null || !vade.hasPassive(IVadeMecumCapability.Passive.Vigor));
		boolean override = false;
		ItemStack mainHand = entity.getHeldItemMainhand();
		ItemStack offHand = entity.getHeldItemOffhand();
		if (entity.getActivePotionEffect(VoidCraft.potions.voidicInfusion) != null) {
			override = true;
			flag = true;
			gain = 20;
		}
		if ((mainHand != null && mainHand.getItem() == VoidCraft.items.voidicSuppressor)) {
			IVoidicPowerCapability cap = entity.getHeldItemMainhand().getCapability(CapabilityList.VOIDICPOWER, null);
			if (cap != null && cap.getCurrentPower() > 0) {
				cap.drain(1);
				cap.sendUpdates(null, 0, entity.getHeldItemMainhand());
				flag = false;
			}
		} else if (offHand != null && offHand.getItem() == VoidCraft.items.voidicSuppressor) {
			IVoidicPowerCapability cap = entity.getHeldItemOffhand().getCapability(CapabilityList.VOIDICPOWER, null);
			if (cap != null && cap.getCurrentPower() > 0) {
				cap.drain(1);
				cap.sendUpdates(null, 0, entity.getHeldItemOffhand());
				flag = false;
			}
		} else if (entity.getActivePotionEffect(VoidCraft.potions.voidicInfusionImmunity) != null) {
			flag = false;
		}
		if ((entity.world.provider.getDimension() == VoidCraft.config.getDimensionIDvoid() || override) && flag) {
			if (entity.onGround && (entity.world.getBlockState(entity.getPosition().down()).getBlock() == VoidCraft.blocks.blockVoidbrick || entity.world.getBlockState(entity.getPosition().down()).getBlock() == VoidCraft.blocks.blockVoidBrickHalfSlab || entity.world.getBlockState(entity.getPosition().down()).getBlock() == VoidCraft.blocks.blockVoidstairs || entity.world.getBlockState(entity.getPosition().down(2)).getBlock() == VoidCraft.blocks.blockVoidfence || entity.world.getBlockState(entity.getPosition().down()).getBlock() == VoidCraft.blocks.blockVoidBrickDoubleSlab)) {

			} else {
				infusion += gain;
			}
		} else {
			infusion -= (vade != null && vade.hasPassive(IVadeMecumCapability.Passive.Vigor) ? 10 : 5);
			if (infusion < 0) infusion = 0;
		}
		if (infusion > maxInfusion) infusion = maxInfusion;
	}

	private void doHealthChecks(EntityLivingBase living) {
		if (living instanceof EntityVoidMob || living instanceof EntityVoidNPC || !living.isNonBoss()) return;
		final String name = "Voidic Infusion";
		for (IAttributeInstance att : living.getAttributeMap().getAllAttributes()) {
			if (att.getAttribute() == SharedMonsterAttributes.MAX_HEALTH) {
				Iterator<AttributeModifier> iter = att.getModifiers().iterator();
				while (iter.hasNext()) {
					AttributeModifier mod = iter.next();
					if (mod.getName().equals(name)) {
						att.removeModifier(mod);
					}
				}
			}
		}
		IVadeMecumCapability cap = living.getCapability(CapabilityList.VADEMECUM, null);
		float value = getInfusionPerc() * ((cap == null) ? 1.0F : cap.hasPassive(IVadeMecumCapability.Passive.Anchor) ? cap.hasPassive(IVadeMecumCapability.Passive.Vigor) ? 0.5F : 2.0F : 1.0F);
		if (value == 0) return;
		float min = 1F - (0.5F / living.getMaxHealth());
		living.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(new AttributeModifier(name, Math.min(value, min) * ((cap != null && cap.hasPassive(IVadeMecumCapability.Passive.Vigor) ? 1 : -1)), 2));
		if (living.getHealth() > living.getMaxHealth()) {
			if (living instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) living;
				try {
					PacketWrapper packet = PacketHelper.createPacket(VoidCraft.channel, VoidCraft.networkChannelName, ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.CLIENT_HEALTH));
					packet.getStream().writeFloat(player.getMaxHealth());
					packet.sendPacket(player);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			living.setHealth(living.getMaxHealth());
		}
	}

	private void handleEffects(EntityLivingBase entity) {
		if (infusion >= maxInfusion) {
			boolean kill = true;
			if (entity.hasCapability(CapabilityList.VADEMECUM, null)) {
				IVadeMecumCapability cap = entity.getCapability(CapabilityList.VADEMECUM, null);
				if (cap.hasPassive(IVadeMecumCapability.Passive.Anchor)) kill = false;
				if (cap.hasCategory(IVadeMecumCapability.Category.Voice) && !cap.hasCategory(IVadeMecumCapability.Category.VoidicControl)) {
					kill = false;
					infusion = 0;
					cap.addCategory(entity, IVadeMecumCapability.Category.VoidicControl);
					entity.sendMessage(new TextComponentTranslation("voidcraft.VadeMecum.voice.VoidicControl"));
				}
				if (cap.hasCategory(IVadeMecumCapability.Category.Empowerment) && !cap.hasCategory(IVadeMecumCapability.Category.Tolerance) && entity.world.provider.getDimension() != VoidCraft.config.getDimensionIDvoid()) {
					kill = false;
					infusion = 0;
					cap.addCategory(entity, IVadeMecumCapability.Category.Tolerance);
					entity.sendMessage(new TextComponentTranslation("voidcraft.VadeMecum.voice.Tolerance"));
				}
			}
			if (kill) {
				if (entity instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) entity;
					if (!player.capabilities.isCreativeMode) {
						entity.attackEntityFrom(new DamageSourceVoidicInfusion(), Integer.MAX_VALUE);
					}
				} else {
					entity.attackEntityFrom(new DamageSourceVoidicInfusion(), Integer.MAX_VALUE);
				}
				return;
			}
		}

		{
			final String name = "Voidic Infusion";
			for (IAttributeInstance att : entity.getAttributeMap().getAllAttributes()) {
				if (att.getAttribute() == SharedMonsterAttributes.ATTACK_DAMAGE) {
					Iterator<AttributeModifier> iter = att.getModifiers().iterator();
					while (iter.hasNext()) {
						AttributeModifier mod = iter.next();
						if (mod.getName().equals(name)) {
							att.removeModifier(mod);
						}
					}
				}
			}
			IVadeMecumCapability cap = entity.getCapability(CapabilityList.VADEMECUM, null);
			if (cap != null) {
				if (cap.hasPassive(IVadeMecumCapability.Passive.Empowerment)) {
					float value = getInfusionPerc();
					if (value != 0) {
						entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(new AttributeModifier(name, value, 2));
					}
				}
				if (cap.hasPassive(IVadeMecumCapability.Passive.Tolerance)) maxInfusion = 12000;
				else maxInfusion = 6000;

			}
		}

		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if (!player.capabilities.isCreativeMode) {
				if (!hasFlight && canFly(entity)) {
					player.capabilities.allowFlying = true;
					player.sendPlayerAbilities();
					hasFlight = true;
				} else if (hasFlight && !canFly(entity)) {
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
					player.capabilities.disableDamage = false;
					hasFlight = false;
					player.sendPlayerAbilities();
				}
			}
		}

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
	public boolean canFly(EntityLivingBase entity) {
		IVadeMecumCapability cap = entity.getCapability(CapabilityList.VADEMECUM, null);
		return getInfusionPerc() >= (cap != null && cap.hasPassive(IVadeMecumCapability.Passive.Flight) ? 0.25F : 0.75F);
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
		hasLoaded = true;
	}

	@Override
	public void copyFrom(IVoidicInfusionCapability cap) {
		// setInfusion(cap.getInfusion());
		setMaxInfusion(cap.getMaxInfusion());
		setXiaDefeats(cap.getXiaDefeats());
	}

	@Override
	public void decodePacket(ByteBufInputStream stream) throws IOException {
		setInfusion(stream.readInt());
		setMaxInfusion(stream.readInt());
		setXiaDefeats(stream.readInt());
	}

	private void sendPacketUpdates(EntityLivingBase living) {
		if (living == null || living.world.isRemote) return;
		try {
			PacketWrapper packet = PacketHelper.createPacket(VoidCraft.channel, VoidCraft.networkChannelName, ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.INFUSION_UPDATE));
			DataOutputStream stream = packet.getStream();
			stream.writeInt(living.getEntityId());
			stream.writeInt(infusion);
			stream.writeInt(maxInfusion);
			stream.writeFloat(xiaDefeats);
			packet.sendPacket(new TargetPoint(living.dimension, living.posX, living.posY, living.posZ, 16 * 8));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
