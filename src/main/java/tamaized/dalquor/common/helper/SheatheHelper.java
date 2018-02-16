package tamaized.dalquor.common.helper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import tamaized.dalquor.VoidCraft;
import tamaized.dalquor.common.damagesources.DamageSourceAcid;
import tamaized.dalquor.common.damagesources.DamageSourceFrost;
import tamaized.dalquor.common.damagesources.DamageSourceLit;
import tamaized.dalquor.common.damagesources.DamageSourceVoidicInfusion;
import tamaized.dalquor.common.potion.PotionSheathe;

public final class SheatheHelper {

	private SheatheHelper() {

	}

	public static void onAttack(EntityLivingBase living, EntityLivingBase attacker) {
		if (living.getActivePotionEffect(VoidCraft.potions.fireSheathe) != null) {
			attacker.attackEntityFrom(DamageSource.ON_FIRE, 2.0f);
		} else if (living.getActivePotionEffect(VoidCraft.potions.frostSheathe) != null) {
			attacker.attackEntityFrom(new DamageSourceFrost(), 2.0f);
		} else if (living.getActivePotionEffect(VoidCraft.potions.litSheathe) != null) {
			attacker.attackEntityFrom(new DamageSourceLit(), 2.0f);
		} else if (living.getActivePotionEffect(VoidCraft.potions.acidSheathe) != null) {
			attacker.attackEntityFrom(new DamageSourceAcid(), 2.0f);
		} else if (living.getActivePotionEffect(VoidCraft.potions.voidSheathe) != null) {
			attacker.attackEntityFrom(new DamageSourceVoidicInfusion(), 2.0f);
			attacker.addPotionEffect(new PotionEffect(VoidCraft.potions.voidicInfusion, 20 * 3));
		}
	}

	public static void castSheathe(EntityLivingBase entity, PotionSheathe.Type type, int duration) {
		entity.removePotionEffect(VoidCraft.potions.fireSheathe);
		entity.removePotionEffect(VoidCraft.potions.litSheathe);
		entity.removePotionEffect(VoidCraft.potions.frostSheathe);
		entity.removePotionEffect(VoidCraft.potions.acidSheathe);
		entity.removePotionEffect(VoidCraft.potions.voidSheathe);
		switch (type) {
			case Fire:
				entity.addPotionEffect(new PotionEffect(VoidCraft.potions.fireSheathe, duration));
				break;
			case Frost:
				entity.addPotionEffect(new PotionEffect(VoidCraft.potions.frostSheathe, duration));
				break;
			case Lit:
				entity.addPotionEffect(new PotionEffect(VoidCraft.potions.litSheathe, duration));
				break;
			case Acid:
				entity.addPotionEffect(new PotionEffect(VoidCraft.potions.acidSheathe, duration));
				break;
			case Void:
				entity.addPotionEffect(new PotionEffect(VoidCraft.potions.voidSheathe, duration));
				break;
			default:
				break;
		}
	}

	public static float[] getColor(EntityLivingBase entity) {
		if (entity.getActivePotionEffect(VoidCraft.potions.fireSheathe) != null) {
			return new float[]{1.0f, 0.65f, 0.0f, 1.0f};
		} else if (entity.getActivePotionEffect(VoidCraft.potions.frostSheathe) != null) {
			return new float[]{0.0f, 1.0f, 1.0f, 1.0f};
		} else if (entity.getActivePotionEffect(VoidCraft.potions.litSheathe) != null) {
			return new float[]{1.0f, 1.0f, 1.0f, 1.0f};
		} else if (entity.getActivePotionEffect(VoidCraft.potions.acidSheathe) != null) {
			return new float[]{0.0f, 1.0f, 0.0f, 1.0f};
		} else if (entity.getActivePotionEffect(VoidCraft.potions.voidSheathe) != null) {
			return new float[]{0x77 / 255F, 0.0f, 0xFF / 255F, 1.0f};
		}
		return null;
	}

}
