package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import Tamaized.TamModized.armors.TamArmor;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.armor.ArmorCustomElytra;
import Tamaized.Voidcraft.armor.XiaArmor;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VoidCraftArmors implements ITamRegistry {

	public ArrayList<ITamModel> modelList;

	public static TamArmor voidHelmet;
	public static TamArmor voidChest;
	public static TamArmor voidLegs;
	public static TamArmor voidBoots;
	public static XiaArmor xiaHelmet;
	public static XiaArmor xiaChest;
	public static XiaArmor xiaLegs;
	public static XiaArmor xiaBoots;
	//public static ArmorCustomElytra elytra;

	@Override
	public void preInit() {
		modelList = new ArrayList<ITamModel>();

		modelList.add(voidHelmet = new TamArmor(voidCraft.tabs.tabVoid, voidCraft.materials.voidArmor, 0, EntityEquipmentSlot.HEAD, "void", "voidHelmet"));
		modelList.add(voidChest = new TamArmor(voidCraft.tabs.tabVoid, voidCraft.materials.voidArmor, 0, EntityEquipmentSlot.CHEST, "void", "voidChest"));
		modelList.add(voidLegs = new TamArmor(voidCraft.tabs.tabVoid, voidCraft.materials.voidArmor, 0, EntityEquipmentSlot.LEGS, "void", "voidLegs"));
		modelList.add(voidBoots = new TamArmor(voidCraft.tabs.tabVoid, voidCraft.materials.voidArmor, 0, EntityEquipmentSlot.FEET, "void", "voidBoots"));

		modelList.add(xiaHelmet = new XiaArmor(voidCraft.tabs.tabVoid, voidCraft.materials.xiaArmor, 0, EntityEquipmentSlot.HEAD, "xia", "xiaHelmet"));
		modelList.add(xiaChest = new XiaArmor(voidCraft.tabs.tabVoid, voidCraft.materials.xiaArmor, 0, EntityEquipmentSlot.CHEST, "xia", "xiaChest"));
		modelList.add(xiaLegs = new XiaArmor(voidCraft.tabs.tabVoid, voidCraft.materials.xiaArmor, 0, EntityEquipmentSlot.LEGS, "xia", "xiaLegs"));
		modelList.add(xiaBoots = new XiaArmor(voidCraft.tabs.tabVoid, voidCraft.materials.xiaArmor, 0, EntityEquipmentSlot.FEET, "xia", "xiaBoots"));

		//modelList.add(elytra = new ArmorCustomElytra(voidCraft.tabs.tabVoid, voidCraft.materials.voidArmor, 0, EntityEquipmentSlot.CHEST, "void", "voidElytra"));
	}

	@Override
	public void init() {
		GameRegistry.addRecipe(new ItemStack(voidHelmet, 1), "XXX", "X X", 'X', voidCraft.items.voidcrystal);
		GameRegistry.addRecipe(new ItemStack(voidChest, 1), "X X", "XXX", "XXX", 'X', voidCraft.items.voidcrystal);
		GameRegistry.addRecipe(new ItemStack(voidLegs, 1), "XXX", "X X", "X X", 'X', voidCraft.items.voidcrystal);
		GameRegistry.addRecipe(new ItemStack(voidBoots, 1), "X X", "X X", 'X', voidCraft.items.voidcrystal);
		//GameRegistry.addShapelessRecipe(new ItemStack(elytra, 1), voidChest, Items.ELYTRA);
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<ITamModel> getModelList() {
		return modelList;
	}

	@Override
	public String getModID() {
		return voidCraft.modid;
	}

	@Override
	public void clientPreInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clientInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clientPostInit() {
		// TODO Auto-generated method stub

	}

}
