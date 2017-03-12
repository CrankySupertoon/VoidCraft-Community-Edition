package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.world.dim.TheVoid.BiomeGenVoid;
import Tamaized.Voidcraft.world.dim.Xia.BiomeGenXia;
import Tamaized.Voidcraft.world.dim.dalQuor.BiomeGenDream;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VoidCraftBiomes implements ITamRegistry {

	public static Biome biomeVoid;
	public static Biome biomeXia;
	
	public static Biome biomeDreamOverworld;
	public static Biome biomeDreamNether;
	public static Biome biomeDreamEnd;
	public static Biome biomeDreamVoid;

	@Override
	public void preInit() {
		float baseHeight = -1F;
		float heightVariation = 0.1F;
		BiomeProperties biomeVoidProp = new BiomeProperties("The Void").setBaseBiome("voidcraft_biome_void").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeXiaProp = new BiomeProperties("???").setBaseBiome("voidcraft_biome_xia").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		
		BiomeProperties biomeDreamOverworldProp = new BiomeProperties("Dream Overworld").setBaseBiome("voidcraft_biome_dream_overworld").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeDreamNetherProp = new BiomeProperties("Dream Nether").setBaseBiome("voidcraft_biome_dream_nether").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeDreamEndProp = new BiomeProperties("Dream End").setBaseBiome("voidcraft_biome_dream_end").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeDreamVoidProp = new BiomeProperties("Dream Void").setBaseBiome("voidcraft_biome_dream_void").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		
		biomeVoid = new BiomeGenVoid(biomeVoidProp);
		biomeXia = new BiomeGenXia(biomeXiaProp);
		
		biomeDreamOverworld = new BiomeGenDream(biomeDreamOverworldProp);
		biomeDreamNether = new BiomeGenDream(biomeDreamNetherProp);
		biomeDreamEnd = new BiomeGenDream(biomeDreamEndProp);
		biomeDreamVoid = new BiomeGenDream(biomeDreamVoidProp);
		
		biomeVoid.setRegistryName("voidcraft_biome_void");
		biomeXia.setRegistryName("voidcraft_biome_xia");
		
		biomeDreamOverworld.setRegistryName("voidcraft_biome_dream_overworld");
		biomeDreamNether.setRegistryName("voidcraft_biome_dream_nether");
		biomeDreamEnd.setRegistryName("voidcraft_biome_dream_end");
		biomeDreamVoid.setRegistryName("voidcraft_biome_dream_void");

		GameRegistry.register(biomeVoid);
		GameRegistry.register(biomeXia);
		
		GameRegistry.register(biomeDreamOverworld);
		GameRegistry.register(biomeDreamNether);
		GameRegistry.register(biomeDreamEnd);
		GameRegistry.register(biomeDreamVoid);
		
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
