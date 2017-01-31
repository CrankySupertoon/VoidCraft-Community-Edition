package Tamaized.Voidcraft.structures.voidCity;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.mob.EntityMobEtherealGuardian;
import Tamaized.Voidcraft.world.dim.TheVoid.ChunkProviderVoid;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenVoidCity extends MapGenStructure {
	private final int citySpacing = 20;
	private final int minCitySeparation = 11;
	private final ChunkProviderVoid worldProvider;

	private final List<Biome.SpawnListEntry> spawnList = Lists.<Biome.SpawnListEntry> newArrayList();

	public MapGenVoidCity(ChunkProviderVoid p_i46665_1_) {
		this.worldProvider = p_i46665_1_;
		
		spawnList.add(new Biome.SpawnListEntry(EntityMobEtherealGuardian.class, 1, 1, 3));
	}

	public String getStructureName() {
		return VoidCraft.modid + "EndCity";
	}

	public List<Biome.SpawnListEntry> getSpawnList() {
		return this.spawnList;
	}

	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		int i = chunkX;
		int j = chunkZ;

		if (chunkX < 0) {
			chunkX -= 19;
		}

		if (chunkZ < 0) {
			chunkZ -= 19;
		}

		int k = chunkX / 20;
		int l = chunkZ / 20;
		Random random = this.world.setRandomSeed(k, l, 10387313);
		k = k * 20;
		l = l * 20;
		k = k + (random.nextInt(9) + random.nextInt(9)) / 2;
		l = l + (random.nextInt(9) + random.nextInt(9)) / 2;

		if (i == k && j == l) {
			int i1 = getYPosForStructure(i, j, this.worldProvider);
			return i1 >= 60;
		} else {
			return false;
		}
	}

	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenVoidCity.Start(this.world, this.worldProvider, this.rand, chunkX, chunkZ);
	}

	public BlockPos getClosestStrongholdPos(World worldIn, BlockPos pos, boolean p_180706_3_) {
		this.world = worldIn;
		return findNearestStructurePosBySpacing(worldIn, this, pos, 20, 11, 10387313, true, 100, p_180706_3_);
	}

	private static int getYPosForStructure(int p_191070_0_, int p_191070_1_, ChunkProviderVoid p_191070_2_) {
		return 128;
	}

	public static class Start extends StructureStart {
		private boolean isSizeable;

		public Start() {
		}

		public Start(World worldIn, ChunkProviderVoid chunkProvider, Random random, int chunkX, int chunkZ) {
			super(chunkX, chunkZ);
			this.create(worldIn, chunkProvider, random, chunkX, chunkZ);
		}

		private void create(World worldIn, ChunkProviderVoid chunkProvider, Random rnd, int chunkX, int chunkZ) {
			Random random = new Random((long) (chunkX + chunkZ * 10387313));
			Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
			int i = MapGenVoidCity.getYPosForStructure(chunkX, chunkZ, chunkProvider);

			if (i < 60) {
				this.isSizeable = false;
			} else {
				BlockPos blockpos = new BlockPos(chunkX * 16 + 8, i, chunkZ * 16 + 8);
				StructureVoidCityPieces.startHouseTower(worldIn.getSaveHandler().getStructureTemplateManager(), blockpos, rotation, this.components, rnd);
				this.updateBoundingBox();
				this.isSizeable = true;
			}
		}

		/**
		 * currently only defined for Villages, returns true if Village has more than 2 non-road components
		 */
		public boolean isSizeableStructure() {
			return this.isSizeable;
		}
	}

}