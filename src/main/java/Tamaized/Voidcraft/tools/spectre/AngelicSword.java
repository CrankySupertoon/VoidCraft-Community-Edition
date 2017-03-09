package Tamaized.Voidcraft.tools.spectre;

import java.util.List;

import Tamaized.TamModized.tools.TamSword;
import Tamaized.Voidcraft.entity.EntityVoidMob;
import Tamaized.Voidcraft.entity.boss.EntityBossCorruptedPawn;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AngelicSword extends TamSword{

	public AngelicSword(CreativeTabs tab, ToolMaterial material, String n) {
		super(tab, material, n);
	}

	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase){
        par1ItemStack.damageItem(1, par3EntityLivingBase);
        if(par2EntityLivingBase instanceof EntityVoidMob && !(par2EntityLivingBase instanceof EntityBossCorruptedPawn)){
        	par2EntityLivingBase.attackEntityFrom(DamageSource.GENERIC, 9999);
        }
        return true;
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(TextFormatting.DARK_RED + "This weapon insta-kills most Void creatures.");
	}
}
