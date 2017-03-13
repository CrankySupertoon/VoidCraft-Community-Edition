package Tamaized.Voidcraft.world.dim.dalQuor;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;
import net.minecraft.world.gen.layer.GenLayerEdge;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.GenLayerRareBiome;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;

public class DalQuorGen {

	public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType p_180781_2_) {
		GenLayer genlayer = new GenLayerDalQuor(1L);
		// biomes = new GenLayerAddIsland(100L, biomes);
		// biomes = new GenLayerAddIsland(1001L, biomes);
		// biomes = new GenLayerAddIsland(1002L, biomes);
		// biomes = new GenLayerAddIsland(1003L, biomes);
		// biomes = new GenLayerAddIsland(1004L, biomes);
//		// biomes = new GenLayerAddIsland(1005L, biomes);
//		GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);
//		biomes.initWorldGenSeed(seed);
//		genlayervoronoizoom.initWorldGenSeed(seed);
//		return new GenLayer[] { biomes, genlayervoronoizoom };
        genlayer = new GenLayerFuzzyZoom(2000L, genlayer);
        GenLayerAddIsland genlayeraddisland = new GenLayerAddIsland(1L, genlayer);
        GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
        GenLayerAddIsland genlayeraddisland1 = new GenLayerAddIsland(2L, genlayerzoom);
        genlayeraddisland1 = new GenLayerAddIsland(50L, genlayeraddisland1);
        genlayeraddisland1 = new GenLayerAddIsland(70L, genlayeraddisland1);
        GenLayerAddIsland genlayeraddisland2 = new GenLayerAddIsland(3L, genlayeraddisland1);
        GenLayerEdge genlayeredge = new GenLayerEdge(2L, genlayeraddisland2, GenLayerEdge.Mode.COOL_WARM);
        genlayeredge = new GenLayerEdge(2L, genlayeredge, GenLayerEdge.Mode.HEAT_ICE);
        genlayeredge = new GenLayerEdge(3L, genlayeredge, GenLayerEdge.Mode.SPECIAL);
        GenLayerZoom genlayerzoom1 = new GenLayerZoom(2002L, genlayeredge);
        genlayerzoom1 = new GenLayerZoom(2003L, genlayerzoom1);
        GenLayerAddIsland genlayeraddisland3 = new GenLayerAddIsland(4L, genlayerzoom1);
        GenLayer genlayer4 = GenLayerZoom.magnify(1000L, genlayeraddisland3, 0);
        int i = 4;
        int j = i;

        GenLayer lvt_7_1_ = GenLayerZoom.magnify(1000L, genlayer4, 0);
        GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, lvt_7_1_);
        GenLayer lvt_9_1_ = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        GenLayer genlayer5 = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        genlayer5 = GenLayerZoom.magnify(1000L, genlayer5, j);
        GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayer5);

        for (int k = 0; k < i; ++k)
        {
            genlayer4 = new GenLayerZoom((long)(1000 + k), genlayer4);

            if (k == 0)
            {
                genlayer4 = new GenLayerAddIsland(3L, genlayer4);
            }

            if (k == 1 || i == 1)
            {
                genlayer4 = new GenLayerAddIsland(3L, genlayer4);
            }
        }

        GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, genlayer4);
        GenLayer genlayer3 = new GenLayerVoronoiZoom(10L, genlayersmooth1);
        genlayersmooth1.initWorldGenSeed(seed);
        genlayer3.initWorldGenSeed(seed);
        genlayersmooth.initWorldGenSeed(seed);
        return new GenLayer[] {genlayersmooth1, genlayer3, genlayersmooth};
	}

	public static class GenLayerDalQuor extends GenLayer {

		public static final Biome[] allowedBiomes = {

				VoidCraft.biomes.biomeDreamOverworld,

				VoidCraft.biomes.biomeDreamNether,

				VoidCraft.biomes.biomeDreamEnd,

				VoidCraft.biomes.biomeDreamVoid

		};

		public GenLayerDalQuor(long seed) {
			super(seed);
		}

		public GenLayerDalQuor(long seed, GenLayer genlayer) {
			super(seed);
			this.parent = genlayer;
		}

		@Override
		public int[] getInts(int x, int z, int width, int depth) {
			int[] dest = IntCache.getIntCache(width * depth);
			for (int dz = 0; dz < depth; dz++) {
				for (int dx = 0; dx < width; dx++) {
					this.initChunkSeed(dx + x, dz + z);
					dest[(dx + dz * width)] = Biome.getIdForBiome(this.allowedBiomes[nextInt(this.allowedBiomes.length)]);
				}
			}

			return dest;
		}
	}

}
