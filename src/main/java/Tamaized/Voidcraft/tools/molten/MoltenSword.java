package Tamaized.Voidcraft.tools.molten;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import Tamaized.TamModized.tools.TamSword;
import Tamaized.Voidcraft.VoidCraft;

public class MoltenSword extends TamSword {

	public MoltenSword(CreativeTabs tab, ToolMaterial material, String n) {
		super(tab, material, n);
	}

	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
		par1ItemStack.damageItem(1, par3EntityLivingBase);
		par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("nausea"), 5 * 20)); // confuse
		par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("weakness"), 5 * 20)); // weakness
		par2EntityLivingBase.setFire(5);
		return true;
	}

}
