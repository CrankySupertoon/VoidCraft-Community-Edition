package Tamaized.Voidcraft.tools.demon;

import Tamaized.TamModized.tools.TamSword;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class DemonSword extends TamSword {

	public DemonSword(CreativeTabs tab, ToolMaterial material, String n) {
		super(tab, material, n);
	}

	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
		par1ItemStack.damageItem(1, par3EntityLivingBase);
		par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("nausea"), 20 * 20)); // Confuse
		par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("weakness"), 20 * 20)); // Weakness
		par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("blindness"), 20 * 20)); // Blind
		par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("poison"), 20 * 20)); // Poison
		par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("wither"), 20 * 20)); // Wither
		par2EntityLivingBase.setFire(15); // Fire
		return true;
	}
}
