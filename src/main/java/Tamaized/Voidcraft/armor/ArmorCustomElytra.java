package Tamaized.Voidcraft.armor;

import javax.annotation.Nullable;

import Tamaized.TamModized.armors.TamArmor;
import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorCustomElytra extends TamArmor {

	public ArmorCustomElytra(CreativeTabs tab, ArmorMaterial armorMaterial, int par3, EntityEquipmentSlot par4, String type, String n) {
		super(tab, armorMaterial, par3, par4, type, n);
		this.maxStackSize = 1;
		this.setMaxDamage(432);
		this.addPropertyOverride(new ResourceLocation("broken"), new IItemPropertyGetter() {
			
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				return ItemElytra.isBroken(stack) ? 0.0F : 1.0F;
			}
			
		});
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, ItemArmor.DISPENSER_BEHAVIOR);
	}

	public static boolean isBroken(ItemStack stack) {
		return stack.getItemDamage() < stack.getMaxDamage() - 1;
	}

	/**
	 * Return whether this item is repairable in an anvil.
	 */
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == VoidCraft.items.voidcrystal;
	}

}
