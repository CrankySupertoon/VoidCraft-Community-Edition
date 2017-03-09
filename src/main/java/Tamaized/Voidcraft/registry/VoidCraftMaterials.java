package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class VoidCraftMaterials implements ITamRegistry {

	public static ToolMaterial voidTools;
	public static ToolMaterial spectreTools;
	public static ToolMaterial chainTools;
	public static ToolMaterial MoltenTools;
	public static ToolMaterial ArchTools;
	public static ToolMaterial DemonTools;
	public static ToolMaterial CosmicTools;

	public static ArmorMaterial voidArmor;
	public static ArmorMaterial xiaArmor;

	@Override
	public void preInit() {
		voidTools = EnumHelper.addToolMaterial(VoidCraft.modid + ":voidcrystal", 3, 2000, 12.0F, 8.0F, 30);
		spectreTools = EnumHelper.addToolMaterial(VoidCraft.modid + ":spectre", 4, 4000, 30.0F, 9.0F, 30);
		chainTools = EnumHelper.addToolMaterial(VoidCraft.modid + ":chain", 5, 6000, 35.0F, 10.0F, 30);
		MoltenTools = EnumHelper.addToolMaterial(VoidCraft.modid + ":molten", 6, 8000, 40.0F, 11.0F, 30);
		ArchTools = EnumHelper.addToolMaterial(VoidCraft.modid + ":angelic", 7, 10000, 45.0F, 12.0F, 30);
		DemonTools = EnumHelper.addToolMaterial(VoidCraft.modid + ":demonic", 8, 12000, 50.0F, 13.0F, 30);
		CosmicTools = EnumHelper.addToolMaterial(VoidCraft.modid + ":cosmic", 9, 14000, 55.0F, 14.0F, 30);

		// 25 = invul
		voidArmor = EnumHelper.addArmorMaterial( VoidCraft.modid + ":Void", VoidCraft.modid + ":void", 120, new int[] { 4, 6, 8, 4 }, 30, VoidSoundEvents.ArmorSoundEvents.voidcrystal, 0.0f); // 22
		xiaArmor = EnumHelper.addArmorMaterial( VoidCraft.modid + ":Xia", VoidCraft.modid + ":xia", 240, new int[] { 4, 6, 10, 4 }, 30, VoidSoundEvents.ArmorSoundEvents.xia, 0.0f); // 24
	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {

	}

	@Override
	public ArrayList<ITamModel> getModelList() {
		return new ArrayList<ITamModel>();
	}

	@Override
	public String getModID() {
		return VoidCraft.modid;
	}

	@Override
	public void clientPreInit() {

	}

	@Override
	public void clientInit() {

	}

	@Override
	public void clientPostInit() {

	}

}
