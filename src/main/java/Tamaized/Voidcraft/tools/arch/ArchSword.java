package Tamaized.Voidcraft.tools.arch;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import Tamaized.TamModized.tools.TamSword;
import Tamaized.Voidcraft.VoidCraft;

public class ArchSword extends TamSword {

	public ArchSword(CreativeTabs tab, ToolMaterial material, String n) {
		super(tab, material, n);
	}

	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
		par1ItemStack.damageItem(1, par3EntityLivingBase);
		par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("nausea"), 10 * 20)); // Confuse
		par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("weakness"), 10 * 20)); // Weakness
		par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("blindness"), 10 * 20)); // Blind
		par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("poison"), 10 * 20)); // Poison
		par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("wither"), 10 * 20)); // Wither
		par2EntityLivingBase.setFire(10); // Fire
		return true;
	}

}
