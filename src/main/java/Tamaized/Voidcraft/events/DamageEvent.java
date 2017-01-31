package Tamaized.Voidcraft.events;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.starforge.IStarForgeCapability;
import Tamaized.Voidcraft.damageSources.DamageSourceAcid;
import Tamaized.Voidcraft.damageSources.DamageSourceFrost;
import Tamaized.Voidcraft.damageSources.DamageSourceLit;
import Tamaized.Voidcraft.damageSources.DamageSourceVoidicInfusion;
import Tamaized.Voidcraft.entity.EntityVoidMob;
import Tamaized.Voidcraft.entity.EntityVoidNPC;
import Tamaized.Voidcraft.entity.boss.EntityBossCorruptedPawnBase;
import Tamaized.Voidcraft.helper.SheatheHelper;
import Tamaized.Voidcraft.starforge.effects.IStarForgeEffect;
import Tamaized.Voidcraft.starforge.effects.IStarForgeEffect.Tier;
import Tamaized.Voidcraft.starforge.effects.wep.tier3.StarForgeEffectCripplingVoid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DamageEvent {

	@SubscribeEvent
	public void entityDamaged(LivingAttackEvent e) {

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
					living.sendMessage(new TextComponentString(TextFormatting.ITALIC + "" + TextFormatting.DARK_GRAY + "Incorporeal"));
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

	private boolean isWhiteListed(DamageSource source, boolean ranged) {
		Entity e = source.getEntity();
		if (e != null && e instanceof EntityLivingBase) {
			EntityLivingBase living = (EntityLivingBase) e;
			IStarForgeCapability cap = living.getHeldItemMainhand().getCapability(CapabilityList.STARFORGE, null);
			if (cap != null) {
				IStarForgeEffect effect = cap.getEffect(Tier.THREE);
				if (effect != null && effect instanceof StarForgeEffectCripplingVoid) return false;
			}
		}
		return source.damageType.equals("generic") || source.damageType.equals("mob") || source.damageType.equals("player") || source.damageType.equals("arrow") || source.damageType.equals("thrown");
	}

}
