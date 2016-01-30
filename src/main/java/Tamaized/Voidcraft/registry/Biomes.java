package Tamaized.Voidcraft.registry;

import Tamaized.Voidcraft.world.dim.TheVoid.BiomeGenVoid;
import Tamaized.Voidcraft.world.dim.Xia.BiomeGenXia;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Biomes extends RegistryBase {

	public static BiomeGenBase biomeVoid;
	public static BiomeGenBase biomeXia;

	@Override
	public void preInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		Height bvoidmm = new Height(-1F, 0.1F);
		biomeVoid = new BiomeGenVoid(251).setBiomeName("The Void").setHeight(bvoidmm).setTemperatureRainfall(0.21F, 0.0F).setDisableRain();
		biomeXia = new BiomeGenXia(252).setBiomeName("???").setHeight(bvoidmm).setTemperatureRainfall(0.21F, 0.0F).setDisableRain();
		//BiomeManager.coolBiomes.add(new BiomeEntry(biomeVoid, 100));
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub

	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void setupRender() {
		
	}

}
