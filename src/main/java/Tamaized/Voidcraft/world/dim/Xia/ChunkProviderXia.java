package Tamaized.Voidcraft.world.dim.Xia;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;

public class ChunkProviderXia implements IChunkGenerator {

	private World worldObj;

	public ChunkProviderXia(World worldIn, boolean p_i45637_2_, long seed) {
		this.worldObj = worldIn;
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		ChunkPrimer chunkprimer = new ChunkPrimer();
		Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
		Biome[] abiome = this.worldObj.getBiomeProvider().getBiomesForGeneration((Biome[]) null, x * 16, z * 16, 16, 16);
		byte[] abyte = chunk.getBiomeArray();

		for (int i = 0; i < abyte.length; ++i) {
			abyte[i] = (byte) Biome.getIdForBiome(abiome[i]);
		}

		chunk.resetRelightChecks();
		return chunk;
	}

	@Override
	public void populate(int x, int z) {

	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
		return new ArrayList();
	}

	@Override
	public void recreateStructures(Chunk chunk, int par1, int par2) {

	}

	@Override
	public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position, boolean p_180513_4_) {
		return null;
	}

}
