package tamaized.dalquor.common.tools.chain;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import tamaized.tammodized.common.tools.TamSword;

public class ChainSword extends TamSword {

	public ChainSword(CreativeTabs tab, ToolMaterial material, String n) {
		super(tab, material, n);
	}

	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
		par1ItemStack.damageItem(1, par3EntityLivingBase);
		par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("slowness"), 5 * 20)); // slow
		return true;
	}

}
