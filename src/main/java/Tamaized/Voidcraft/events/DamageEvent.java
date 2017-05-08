package Tamaized.Voidcraft.events;

import java.util.ArrayList;
import java.util.List;

import Tamaized.TamModized.helper.FloatyTextHelper;
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
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DamageEvent {

	public static List<Item> shieldRegistry = new ArrayList<Item>(); // Move this into TamModized

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
					if (living instanceof EntityPlayer) FloatyTextHelper.sendText((EntityPlayer) living, "Incorporeal");
					return;
				}
			}

			// Sheathe
			if (e.getSource() != null && isWhiteListed(e.getSource(), false) && e.getSource().getEntity() != null && e.getSource().getEntity() instanceof EntityLivingBase) {
				EntityLivingBase attacker = (EntityLivingBase) e.getSource().getEntity();
				SheatheHelper.onAttack(living, attacker);
			}
		}

		handleShield(e); // Do this last
	}

	private void handleShield(LivingAttackEvent e) {
		float damage = e.getAmount();
		ItemStack activeItemStack;
		EntityPlayer player;
		if (!(e.getEntityLiving() instanceof EntityPlayer)) {
			return;
		}
		player = (EntityPlayer) e.getEntityLiving();

		if (canBlockDamageSource(player, e.getSource()) && damage > 0.0F) {
			damageShield(player, damage);
			e.setCanceled(true);
			if (!e.getSource().isProjectile()) {
				Entity entity = e.getSource().getSourceOfDamage();

				if (entity instanceof EntityLivingBase) {
					EntityLivingBase p_190629_1_ = (EntityLivingBase) entity;
					p_190629_1_.knockBack(player, 0.5F, player.posX - p_190629_1_.posX, player.posZ - p_190629_1_.posZ);
				}
				player.world.setEntityState(player, (byte) 29);
			}
		}
	}

	private void damageShield(EntityPlayer player, float damage) {
		if (damage >= 3.0F && player.getActiveItemStack() != null && shieldRegistry.contains(player.getActiveItemStack().getItem())) {
			int i = 1 + MathHelper.floor(damage);
			player.getActiveItemStack().damageItem(i, player);

			if (player.getActiveItemStack() == null) {
				EnumHand enumhand = player.getActiveHand();
				net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, player.getActiveItemStack(), enumhand);

				if (enumhand == EnumHand.MAIN_HAND) {
					player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, null);
				} else {
					player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, null);
				}

				player.resetActiveHand();
				player.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + player.world.rand.nextFloat() * 0.4F);
			}
		}
	}

	private boolean canBlockDamageSource(EntityPlayer player, DamageSource damageSourceIn) {
		if (!damageSourceIn.isUnblockable() && player.isActiveItemStackBlocking()) {
			Vec3d vec3d = damageSourceIn.getDamageLocation();

			if (vec3d != null) {
				Vec3d vec3d1 = player.getLook(1.0F);
				Vec3d vec3d2 = vec3d.subtractReverse(new Vec3d(player.posX, player.posY, player.posZ)).normalize();
				vec3d2 = new Vec3d(vec3d2.xCoord, 0.0D, vec3d2.zCoord);

				if (vec3d2.dotProduct(vec3d1) < 0.0D) {
					return true;
				}
			}
		}

		return false;
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
