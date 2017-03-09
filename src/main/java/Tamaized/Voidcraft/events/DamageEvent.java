package Tamaized.Voidcraft.events;

import java.util.ArrayList;
import java.util.List;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.starforge.IStarForgeCapability;
import Tamaized.Voidcraft.damageSources.DamageSourceVoidicInfusion;
import Tamaized.Voidcraft.helper.SheatheHelper;
import Tamaized.Voidcraft.starforge.effects.IStarForgeEffect;
import Tamaized.Voidcraft.starforge.effects.IStarForgeEffect.Tier;
import Tamaized.Voidcraft.starforge.effects.wep.tier3.StarForgeEffectCripplingVoid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

public class DamageEvent {

	public static List<Item> shieldRegistry = new ArrayList<Item>(); // Move this into TamModized

	@SubscribeEvent
	public void entityDamaged(LivingAttackEvent e) {

		handleShield(e);

		// Vanilla Void
		if (e.getSource().damageType.equals("outOfWorld") && e.getEntity() != null && e.getEntity() instanceof EntityLivingBase && ((EntityLivingBase) e.getEntity()).getActivePotionEffect(VoidCraft.potions.voidImmunity) != null) {
			e.setCanceled(true);
			return;
		}

		// Add Voidic Damage to attacks if attacker has enough infusion
		if (e.getSource() != null && e.getSource() instanceof DamageSourceVoidicInfusion && e.getSource().getEntity() instanceof EntityLivingBase) {
			EntityLivingBase attacker = (EntityLivingBase) e.getSource().getEntity();
			Entity victim = e.getEntity();
			if (!attacker.hasCapability(CapabilityList.VOIDICINFUSION, null) || attacker.getCapability(CapabilityList.VOIDICINFUSION, null).getInfusionPerc() >= 0.30f) {
				victim.attackEntityFrom(new DamageSourceVoidicInfusion(), 1.0f);
			}
		}

		if ((e.getEntity() instanceof EntityLivingBase)) {
			EntityLivingBase living = (EntityLivingBase) e.getEntity();

			// Dodge Mechanic
			if (living.hasCapability(CapabilityList.VOIDICINFUSION, null) && living.getCapability(CapabilityList.VOIDICINFUSION, null).getInfusionPerc() >= 0.50f) {
				if (Math.floor(Math.random() * 5) == 0 && isWhiteListed(e.getSource(), true)) { // 0-4; 25%
					e.setCanceled(true);
					living.sendMessage(new TextComponentTranslation("voidcraft.misc.incorp"));
					return;
				}
			}

			// Sheathe
			if (e.getSource() != null && isWhiteListed(e.getSource(), false) && e.getSource().getEntity() != null && e.getSource().getEntity() instanceof EntityLivingBase) {
				EntityLivingBase attacker = (EntityLivingBase) e.getSource().getEntity();
				SheatheHelper.onAttack(living, attacker);
			}
		}
	}

	private void handleShield(LivingAttackEvent e) {
		float damage = e.getAmount();
		ItemStack activeItemStack;
		EntityPlayer player;
		if (!(e.getEntityLiving() instanceof EntityPlayer)) {
			return;
		}
		player = (EntityPlayer) e.getEntityLiving();
		if (player.getActiveItemStack() == null) {
			return;
		}
		activeItemStack = player.getActiveItemStack();

		if (damage > 0.0F && activeItemStack != null && shieldRegistry.contains(activeItemStack.getItem())) {
			int i = 1 + MathHelper.floor(damage);
			activeItemStack.damageItem(i, player);

			if (activeItemStack.stackSize <= 0) {
				EnumHand enumhand = player.getActiveHand();
				net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, activeItemStack, enumhand);

				if (enumhand == EnumHand.MAIN_HAND) {
					player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, (ItemStack) null);
				} else {
					player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, (ItemStack) null);
				}

				activeItemStack = null;
				if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
					player.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + player.world.rand.nextFloat() * 0.4F);
				}
			}
		}
	}

	private boolean isWhiteListed(DamageSource source, boolean ranged) {
		Entity e = source.getEntity();
		if (e != null && e instanceof EntityLivingBase) {
			EntityLivingBase living = (EntityLivingBase) e;
			IStarForgeCapability cap = living.getHeldItemMainhand() == null ? null : living.getHeldItemMainhand().getCapability(CapabilityList.STARFORGE, null);
			if (cap != null) {
				IStarForgeEffect effect = cap.getEffect(Tier.THREE);
				if (effect != null && effect instanceof StarForgeEffectCripplingVoid) return false;
			}
		}
		return source.damageType.equals("generic") || source.damageType.equals("mob") || source.damageType.equals("player") || source.damageType.equals("arrow") || source.damageType.equals("thrown");
	}

}
