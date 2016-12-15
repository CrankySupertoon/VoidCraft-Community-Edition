package Tamaized.Voidcraft.world;

import java.util.Random;

import com.google.common.base.Predicate;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.blocks.OreVoidcrystal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGeneratorVoid implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		int dimension = world.provider.getDimension();
		if (dimension == 1) {
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
		} else if (dimension == voidCraft.config.getDimensionIDvoid()) {
			generateVoid(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateEnd(World world, Random random, int BlockX, int BlockZ) {
		for (int i = 0; i < 6; i++) {
			int Xcoord = BlockX + random.nextInt(16);
			int Zcoord = BlockZ + random.nextInt(16);
			int Ycoord = random.nextInt(100);
			new WorldGenMinable(((OreVoidcrystal) voidCraft.blocks.oreVoidcrystal).getStateVoidFalse(), 5, new Predicate<IBlockState>() {

				@Override
				public boolean apply(IBlockState input) {
					return input == Blocks.END_STONE.getDefaultState();
				}

			}).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
		}
		for (int i = 0; i < 6; i++) {
			int Xcoord = BlockX + random.nextInt(16);
			int Zcoord = BlockZ + random.nextInt(16);
			int Ycoord = random.nextInt(100);
			new WorldGenMinable(voidCraft.blocks.cosmicMaterial.getDefaultState(), 3, new Predicate<IBlockState>() {

				@Override
				public boolean apply(IBlockState input) {
					return input == Blocks.END_STONE.getDefaultState();
				}

			}).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
		}
	}

	private void generateVoid(World world, Random random, int BlockX, int BlockZ) {
		for (int i = 0; i < 10; i++) {
			int Xcoord = BlockX + random.nextInt(16);
			int Zcoord = BlockZ + random.nextInt(16);
			int Ycoord = random.nextInt(255);
			new WorldGenMinable(voidCraft.blocks.cosmicMaterial.getDefaultState(), 3, new Predicate<IBlockState>() {

				@Override
				public boolean apply(IBlockState input) {
					return input == voidCraft.blocks.blockFakeBedrock.getDefaultState();
				}

			}).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
		}
	}
}