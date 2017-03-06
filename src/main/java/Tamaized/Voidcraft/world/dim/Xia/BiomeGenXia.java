package Tamaized.Voidcraft.world.dim.Xia;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenXia extends Biome {

	public BiomeGenXia(BiomeProperties prop) {
		super(prop);

		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		// this.topBlock = voidCraft.blockFakeBedrock;
		// this.fillerBlock = voidCraft.blockFakeBedrock;
	}

	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return 0;
	}

	@Override
	public boolean canRain() {
		return false;
	}

}
