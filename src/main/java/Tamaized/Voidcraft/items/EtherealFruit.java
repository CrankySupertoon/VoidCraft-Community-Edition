package Tamaized.Voidcraft.items;

import java.util.List;

import Tamaized.TamModized.items.TamItemFood;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityFakeBedrockFarmland;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EtherealFruit extends TamItemFood {
	
	private final TileEntityFakeBedrockFarmland.Alteration alteration;

	public EtherealFruit(TileEntityFakeBedrockFarmland.Alteration alter, CreativeTabs tab, String n, int maxStackSize, int hungerAmount, boolean isWolfFood) {
		super(tab, n, maxStackSize, hungerAmount, isWolfFood);
		setAlwaysEdible();
		alteration = alter;
	}

	@Override
	protected void doneEating(ItemStack stack, World worldIn, EntityLivingBase e) {
		if (!worldIn.isRemote) {
			switch (alteration) {
				case NORMAL:
					e.addPotionEffect(new PotionEffect(VoidCraft.potions.voidicInfusionImmunity, 20 * 90));
					break;
				case REDSTONE:
					e.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20 * 90, 2));
					break;
				case LAPIS:
					e.addPotionEffect(new PotionEffect(MobEffects.SPEED, 20 * 90, 2));
					break;
				case DIAMOND:
					e.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 20 * 90, 2));
					break;
				case EMERALD:
					e.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 20 * 90, 2));
					break;
				case GOLD:
					e.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 20 * 90, 2));
					e.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 20 * 90, 2));
					break;
				default:
					break;
			}
		}
	}
	
}
