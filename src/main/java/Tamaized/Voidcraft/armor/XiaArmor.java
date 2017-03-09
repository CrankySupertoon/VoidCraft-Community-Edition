package Tamaized.Voidcraft.armor;

import Tamaized.TamModized.armors.TamArmor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;

public class XiaArmor extends TamArmor {

	public XiaArmor(CreativeTabs tab, ArmorMaterial armorMaterial, int par3, EntityEquipmentSlot par4, String type, String n) {
		super(tab, armorMaterial, par3, par4, type, n);
		setHasSubtypes(true);
	}

}
