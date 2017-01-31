package Tamaized.Voidcraft.world.dim.TheVoid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.base.Predicate;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.structures.voidCity.MapGenVoidCity;
import Tamaized.Voidcraft.structures.voidFortress.MapGenVoidFortress;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCavesHell;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderVoid implements IChunkGenerator {

	private boolean hasXiaGen = false;

	private final World world;
	private final boolean generateStructures;
	private final Random rand;
	public MapGenVoidFortress genFortress = new MapGenVoidFortress();
	private final MapGenVoidCity genCity = new MapGenVoidCity(this);

	private double[] buffer;
	private NoiseGeneratorOctaves lperlinNoise1;
	private NoiseGeneratorOctaves lperlinNoise2;
	private NoiseGeneratorOctaves perlinNoise1;
	/** Determines whether slowsand or gravel can be generated at a location */
	private NoiseGeneratorOctaves slowsandGravelNoiseGen;
	/** Determines whether something other than nettherack can be generated at a location */
	private NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
	public NoiseGeneratorOctaves scaleNoise;
	public NoiseGeneratorOctaves depthNoise;

	/**
	 * Holds the noise used to determine whether slowsand can be generated at a location
	 */
	private double[] slowsandNoise = new double[256];
	private double[] gravelNoise = new double[256];
	private double[] depthBuffer = new double[256];
	private MapGenBase genNetherCaves = new MapGenCavesHell();
	double[] pnr;
	double[] ar;
	double[] br;
	double[] noiseData4;
	double[] dr;

	private final ArrayList blankSpawnList = new ArrayList();

	public ChunkProviderVoid(World worldIn, boolean p_i45637_2_, long seed) {
		this.world = worldIn;
		this.generateStructures = p_i45637_2_;
		this.rand = new Random(seed);
		this.lperlinNoise1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.lperlinNoise2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.perlinNoise1 = new NoiseGeneratorOctaves(this.rand, 8);
		this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.rand, 4);
		this.netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.rand, 4);
		this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
		this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
		worldIn.setSeaLevel(63);

		InitNoiseGensEvent.ContextHell ctx = new InitNoiseGensEvent.ContextHell(lperlinNoise1, lperlinNoise2, perlinNoise1, slowsandGravelNoiseGen, netherrackExculsivityNoiseGen, scaleNoise, depthNoise);
		ctx = TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, ctx);
		this.lperlinNoise1 = ctx.getLPerlin1();
		this.lperlinNoise2 = ctx.getLPerlin2();
		this.perlinNoise1 = ctx.getPerlin();
		this.slowsandGravelNoiseGen = ctx.getPerlin2();
		this.netherrackExculsivityNoiseGen = ctx.getPerlin3();
		this.scaleNoise = ctx.getScale();
		this.depthNoise = ctx.getDepth();
		// this.genNetherBridge = (MapGenNetherBridge)net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(genNetherBridge, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.NETHER_BRIDGE);
		this.genNetherCaves = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(genNetherCaves, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.NETHER_CAVE);
	}

	public void prepareHeights(int p_185936_1_, int p_185936_2_, ChunkPrimer primer) {
		int i = 4;
		int j = this.world.getSeaLevel() / 2 + 1;
		int k = i + 1;
		int l = 17;
		int i1 = i + 1;
		this.buffer = this.getHeights(this.buffer, p_185936_1_ * i, 0, p_185936_2_ * i, k, l, i1);

		for (int j1 = 0; j1 < i; ++j1) {
			for (int k1 = 0; k1 < i; ++k1) {
				for (int l1 = 0; l1 < 16; ++l1) {
					double d0 = 0.125D;
					double d1 = this.buffer[((j1 + 0) * i1 + k1 + 0) * l + l1 + 0];
					double d2 = this.buffer[((j1 + 0) * i1 + k1 + 1) * l + l1 + 0];
					double d3 = this.buffer[((j1 + 1) * i1 + k1 + 0) * l + l1 + 0];
					double d4 = this.buffer[((j1 + 1) * i1 + k1 + 1) * l + l1 + 0];
					double d5 = (this.buffer[((j1 + 0) * i1 + k1 + 0) * l + l1 + 1] - d1) * d0;
					double d6 = (this.buffer[((j1 + 0) * i1 + k1 + 1) * l + l1 + 1] - d2) * d0;
					double d7 = (this.buffer[((j1 + 1) * i1 + k1 + 0) * l + l1 + 1] - d3) * d0;
					double d8 = (this.buffer[((j1 + 1) * i1 + k1 + 1) * l + l1 + 1] - d4) * d0;

					for (int i2 = 0; i2 < 8; ++i2) {
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;

						for (int j2 = 0; j2 < 4; ++j2) {
							double d14 = 0.25D;
							double d15 = d10;
							double d16 = (d11 - d10) * d14;

							for (int k2 = 0; k2 < 4; ++k2) {
								IBlockState iblockstate = Blocks.AIR.getDefaultState();

								if (d15 > 0.0D) {
									double rand4Fluid = Math.round(Math.random() * 600);
									// if(rand4Fluid == 400){
									// iblockstate = voidCraft.fluids.voidFluidBlock.getDefaultState(); //lavaStill
									// }else if(rand4Fluid > 200 && rand4Fluid < 400){
									// l2 = voidCraft.blocks.blockFakeBedrock;
									// }
									// else{
									// double randy = Math.round(Math.random()*4);
									// if(randy == 1) l2 = Blocks.air;
									// else if(randy == 2) l2 = Blocks.end_stone;
									// else if(randy == 3) l2 = Blocks.skull;
									// else l2 = voidCraft.blocks.blockFakeBedrock; //TODO not implementing this change yet cuz I wanna think on it some more.
									iblockstate = VoidCraft.blocks.blockFakeBedrock.getDefaultState();
									// }
								}
								// iblockstate = Blocks.NETHERRACK.getDefaultState();//voidCraft.blocks.blockFakeBedrock.getDefaultState();
								int l2 = j2 + j1 * 4;
								int i3 = i2 + l1 * 8;
								int j3 = k2 + k1 * 4;
								primer.setBlockState(l2, i3, j3, iblockstate);
								d15 += d16;
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	public void buildSurfaces(int p_185937_1_, int p_185937_2_, ChunkPrimer primer) {
		if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, p_185937_1_, p_185937_2_, primer, this.world)) return;
		int i = this.world.getSeaLevel() + 1;
		double d0 = 0.03125D;
		this.slowsandNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.slowsandNoise, p_185937_1_ * 16, p_185937_2_ * 16, 0, 16, 16, 1, d0, d0, 1.0D);
		this.gravelNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.gravelNoise, p_185937_1_ * 16, 109, p_185937_2_ * 16, 16, 1, 16, d0, 1.0D, d0);
		this.depthBuffer = this.netherrackExculsivityNoiseGen.generateNoiseOctaves(this.depthBuffer, p_185937_1_ * 16, p_185937_2_ * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

		for (int j = 0; j < 16; ++j) {
			for (int k = 0; k < 16; ++k) {
				boolean flag = this.slowsandNoise[j + k * 16] + this.rand.nextDouble() * 0.2D > 0.0D;
				boolean flag1 = this.gravelNoise[j + k * 16] + this.rand.nextDouble() * 0.2D > 0.0D;
				int l = (int) (this.depthBuffer[j + k * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
				int i1 = -1;
				IBlockState iblockstate = VoidCraft.blocks.blockFakeBedrock.getDefaultState(); // netherrack Blocks.AIR.getDefaultState();//
				IBlockState iblockstate1 = VoidCraft.blocks.blockFakeBedrock.getDefaultState(); // netherrack

				for (int j1 = 127; j1 >= 0; --j1) {
					if (j1 < 127 - this.rand.nextInt(5) && j1 > this.rand.nextInt(5)) {
						IBlockState iblockstate2 = primer.getBlockState(k, j1, j);

						if (iblockstate2.getBlock() != null && iblockstate2.getMaterial() != Material.AIR) {
							if (iblockstate2.getBlock() == VoidCraft.blocks.blockFakeBedrock) { // netherrack
								if (i1 == -1) {
									if (l <= 0) {
										iblockstate = VoidCraft.blocks.blockFakeBedrock.getDefaultState();
										iblockstate1 = VoidCraft.blocks.blockFakeBedrock.getDefaultState(); // netherrack
									} else if (j1 >= i - 4 && j1 <= i + 1) {
										iblockstate = VoidCraft.blocks.blockFakeBedrock.getDefaultState(); // netherrack
										iblockstate1 = VoidCraft.blocks.blockFakeBedrock.getDefaultState(); // netherrack

										if (flag1) {
											iblockstate = VoidCraft.blocks.realityHole.getDefaultState(); // gravel
											iblockstate1 = VoidCraft.blocks.blockFakeBedrock.getDefaultState(); // netherrack
										}

										if (flag) {
											iblockstate = VoidCraft.blocks.realityHole.getDefaultState(); // soulsand
											iblockstate1 = VoidCraft.blocks.realityHole.getDefaultState(); // soulsand
										}
									}

									if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
										iblockstate = VoidCraft.blocks.blockFakeBedrock.getDefaultState(); // lavaStill
									}

									i1 = l;

									if (j1 >= i - 1) {
										primer.setBlockState(k, j1, j, iblockstate);
									} else {
										primer.setBlockState(k, j1, j, iblockstate1);
									}
								} else if (i1 > 0) {
									--i1;
									primer.setBlockState(k, j1, j, iblockstate1);
								}
							}
						} else {
							i1 = -1;
						}
					} else {
						// primer.setBlockState(k, j1, j, voidCraft.blocks.blockFakeBedrock.getDefaultState()); //bedrock
					}
				}
			}
		}
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		this.rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();
		this.prepareHeights(x, z, chunkprimer);
		this.buildSurfaces(x, z, chunkprimer);
		this.genNetherCaves.generate(this.world, x, z, chunkprimer);
		this.genCity.generate(this.world, x, z, chunkprimer);
		this.genFortress.generate(this.world, x, z, chunkprimer);

		Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
		Biome[] abiome = this.world.getBiomeProvider().getBiomesForGeneration((Biome[]) null, x * 16, z * 16, 16, 16);
		byte[] abyte = chunk.getBiomeArray();

		for (int i = 0; i < abyte.length; ++i) {
			abyte[i] = (byte) Biome.getIdForBiome(abiome[i]);
		}

		chunk.resetRelightChecks();
		return chunk;
	}

	private double[] getHeights(double[] p_185938_1_, int p_185938_2_, int p_185938_3_, int p_185938_4_, int p_185938_5_, int p_185938_6_, int p_185938_7_) {
		if (p_185938_1_ == null) {
			p_185938_1_ = new double[p_185938_5_ * p_185938_6_ * p_185938_7_];
		}

		net.minecraftforge.event.terraingen.ChunkGeneratorEvent.InitNoiseField event = new net.minecraftforge.event.terraingen.ChunkGeneratorEvent.InitNoiseField(this, p_185938_1_, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_);
		net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
		if (event.getResult() == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY) return event.getNoisefield();

		double d0 = 684.412D;
		double d1 = 2053.236D;
		this.noiseData4 = this.scaleNoise.generateNoiseOctaves(this.noiseData4, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, 1, p_185938_7_, 1.0D, 0.0D, 1.0D);
		this.dr = this.depthNoise.generateNoiseOctaves(this.dr, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, 1, p_185938_7_, 100.0D, 0.0D, 100.0D);
		this.pnr = this.perlinNoise1.generateNoiseOctaves(this.pnr, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, d0 / 80.0D, d1 / 60.0D, d0 / 80.0D);
		this.ar = this.lperlinNoise1.generateNoiseOctaves(this.ar, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, d0, d1, d0);
		this.br = this.lperlinNoise2.generateNoiseOctaves(this.br, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, d0, d1, d0);
		int i = 0;
		double[] adouble = new double[p_185938_6_];

		for (int j = 0; j < p_185938_6_; ++j) {
			adouble[j] = Math.cos((double) j * Math.PI * 6.0D / (double) p_185938_6_) * 2.0D;
			double d2 = (double) j;

			if (j > p_185938_6_ / 2) {
				d2 = (double) (p_185938_6_ - 1 - j);
			}

			if (d2 < 4.0D) {
				d2 = 4.0D - d2;
				adouble[j] -= d2 * d2 * d2 * 10.0D;
			}
		}

		for (int l = 0; l < p_185938_5_; ++l) {
			for (int i1 = 0; i1 < p_185938_7_; ++i1) {
				double d3 = 0.0D;

				for (int k = 0; k < p_185938_6_; ++k) {
					double d4 = 0.0D;
					double d5 = adouble[k];
					double d6 = this.ar[i] / 512.0D;
					double d7 = this.br[i] / 512.0D;
					double d8 = (this.pnr[i] / 10.0D + 1.0D) / 2.0D;

					if (d8 < 0.0D) {
						d4 = d6;
					} else if (d8 > 1.0D) {
						d4 = d7;
					} else {
						d4 = d6 + (d7 - d6) * d8;
					}

					d4 = d4 - d5;

					if (k > p_185938_6_ - 4) {
						double d9 = (double) ((float) (k - (p_185938_6_ - 4)) / 3.0F);
						d4 = d4 * (1.0D - d9) + -10.0D * d9;
					}

					if ((double) k < d3) {
						double d10 = (d3 - (double) k) / 4.0D;
						d10 = MathHelper.clamp(d10, 0.0D, 1.0D);
						d4 = d4 * (1.0D - d10) + -10.0D * d10;
					}

					p_185938_1_[i] = d4;
					++i;
				}
			}
		}

		return p_185938_1_;
	}

	@Override
	public void populate(int x, int z) {
		BlockFalling.fallInstantly = true;
		BlockPos blockpos = new BlockPos(x * 16, 0, z * 16);
		ChunkPos chunkpos = new ChunkPos(x, z);
		this.genFortress.generateStructure(this.world, this.rand, chunkpos);
		this.genCity.generateStructure(this.world, this.rand, chunkpos);

		WorldGenMinable worldgenminable = new WorldGenMinable(VoidCraft.blocks.oreVoidcrystal.getDefaultState(), 5, new Predicate<IBlockState>() {

			@Override
			public boolean apply(IBlockState input) {
				return (input == VoidCraft.blocks.blockFakeBedrock.getDefaultState());
			}

		});
		WorldGenEtherealPlants worldGenEtherealPlants = new WorldGenEtherealPlants();
		int k1;
		for (k1 = 0; k1 < 16; ++k1) {
			worldgenminable.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16), this.rand.nextInt(108) + 10, this.rand.nextInt(16)));
		}

		for (k1 = 0; k1 < 16; ++k1) {
			worldGenEtherealPlants.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16), this.rand.nextInt(108) + 10, this.rand.nextInt(16)));
		}

		BlockFalling.fallInstantly = false;
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		if (this.genFortress != null) {
			if (this.genFortress.isInsideStructure(pos)) {
				if (Math.floor(Math.random() * 10) == 0) return this.genFortress.getSpawnList();
				// if(par1EnumCreatureType == EnumCreatureType.creature) return this.genTest.getSpawnList();
			}
		}
		if (this.genFortress.isPositionInStructure(world, pos) && this.world.getBlockState(pos.down()).getBlock() == VoidCraft.blocks.blockVoidbrick) {
			if (Math.floor(Math.random() * 40) == 0) return this.genFortress.getSpawnList();
			// if(par1EnumCreatureType == EnumCreatureType.creature) return this.genTest.getSpawnList();
		}
		if (pos.getY() >= 128) {
			IBlockState state = world.getBlockState(pos.down());
			if (state != null && state.getBlock() == VoidCraft.blocks.blockVoidbrick && world.rand.nextInt(5) == 0) {
				return genCity.getSpawnList();
			}
		}
		// System.out.println(world.getBiomeForCoordsBody(pos));
		// System.out.println(creatureType);
		// System.out.println(world.getBiomeForCoordsBody(pos).getSpawnableList(creatureType));
		return this.world.getBiomeForCoordsBody(pos).getSpawnableList(creatureType);// voidCraft.biomes.biomeVoid.getSpawnableList(creatureType);//new ArrayList();//this.world.getBiomeGenForCoords(pos).getSpawnableList(creatureType);
	}

	@Override
	public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position, boolean p_180513_4_) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		this.genFortress.generate(this.world, x, z, (ChunkPrimer) null);
	}
}
