package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.world.dim.TheVoid.BiomeGenVoid;
import Tamaized.Voidcraft.world.dim.Xia.BiomeGenXia;

public class VoidCraftBiomes implements ITamRegistry {

	public static Biome biomeVoid;
	public static Biome biomeXia;

	@Override
	public void preInit() {
		float baseHeight = -1F;
		float heightVariation = 0.1F;
		BiomeProperties biomeVoidProp = new BiomeProperties("The Void").setBaseBiome("voidcraft_biome_void").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeXiaProp = new BiomeProperties("???").setBaseBiome("voidcraft_biome_xia").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();

		biomeVoid = new BiomeGenVoid(biomeVoidProp);
		biomeXia = new BiomeGenXia(biomeXiaProp);
		
		biomeVoid.setRegistryName("voidcraft_biome_void");
		biomeXia.setRegistryName("voidcraft_biome_xia");

		GameRegistry.register(biomeVoid);
		GameRegistry.register(biomeXia);
		
		BiomeDictionary.addTypes(biomeVoid, BiomeDictionary.Type.VOID);
		BiomeDictionary.addTypes(biomeXia, BiomeDictionary.Type.VOID);
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
