package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.VoidCraft;

public class VoidCraftLootTables implements ITamRegistry {

	public static final ResourceLocation chest_voidFortress = new ResourceLocation(VoidCraft.modid + ":chests/voidFortress");
	public static final ResourceLocation chest_voidCity = new ResourceLocation(VoidCraft.modid + ":chests/voidCity");

	@Override
	public void preInit() {

	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {
		LootTableList.register(chest_voidFortress);
		LootTableList.register(chest_voidCity);
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
