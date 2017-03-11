package Tamaized.Voidcraft.world.dim.dalQuor;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenDream extends Biome {

	public BiomeGenDream(BiomeProperties prop) {
		super(prop);

		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();

		// this.spawnableCreatureList.add(new SpawnListEntry(EntityMobWraith.class, 1, 0, 1));

	}

	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return 0x7700FF;
	}

	@Override
	public boolean canRain() {
		return true;
	}

}
