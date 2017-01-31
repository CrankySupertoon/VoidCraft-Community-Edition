package Tamaized.Voidcraft.structures.voidFortress;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import Tamaized.Voidcraft.entity.mob.EntityMobVoidWrath;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenVoidFortress extends MapGenStructure {

	private final List<Biome.SpawnListEntry> spawnList = Lists.<Biome.SpawnListEntry> newArrayList();

	public MapGenVoidFortress() {
		this.spawnList.add(new SpawnListEntry(EntityMobVoidWrath.class, 3, 0, 1));
		this.spawnList.add(new SpawnListEntry(EntityHerobrineCreeper.class, 1, 0, 1));
	}

	@Override
	public String getStructureName() {
		return "Fortress";
	}

	public List<Biome.SpawnListEntry> getSpawnList() {
		return this.spawnList;
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		int i = chunkX >> 4;
		int j = chunkZ >> 4;
		this.rand.setSeed((long) (i ^ j << 4) ^ this.world.getSeed());
		this.rand.nextInt();
		return this.rand.nextInt(3) != 0 ? false : (chunkX != (i << 4) + 4 + this.rand.nextInt(8) ? false : chunkZ == (j << 4) + 4 + this.rand.nextInt(8));
	}

	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenVoidFortress.Start(this.world, this.rand, chunkX, chunkZ);
	}

	public static class Start extends StructureStart {

		public Start() {

		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ) {
			super(chunkX, chunkZ);
			StructureVoidFortressPieces.Start structurenetherbridgepieces$start = new StructureVoidFortressPieces.Start(random, (chunkX << 4) + 2, (chunkZ << 4) + 2);
			this.components.add(structurenetherbridgepieces$start);
			structurenetherbridgepieces$start.buildComponent(structurenetherbridgepieces$start, this.components, random);
			List<StructureComponent> list = structurenetherbridgepieces$start.pendingChildren;

			while (!list.isEmpty()) {
				int i = random.nextInt(list.size());
				StructureComponent structurecomponent = (StructureComponent) list.remove(i);
				structurecomponent.buildComponent(structurenetherbridgepieces$start, this.components, random);
			}

			this.updateBoundingBox();
			this.setRandomHeight(worldIn, random, 48, 70);
		}
	}

	@Override
	public BlockPos getClosestStrongholdPos(World worldIn, BlockPos pos, boolean p_180706_3_) {
		return null;
	}

}
