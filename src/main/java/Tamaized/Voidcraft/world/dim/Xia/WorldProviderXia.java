package Tamaized.Voidcraft.world.dim.Xia;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.xiaCastle.logic.XiaCastleLogicHandler;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderXia extends WorldProvider {

	private static final XiaSkyRender skyRender = new XiaSkyRender();

	private XiaCastleLogicHandler xiaCastleHandler;

	/**
	 * creates a new world chunk manager for WorldProvider
	 */
	@Override
	protected void init() {
		this.biomeProvider = new BiomeProviderSingle(VoidCraft.biomes.biomeXia);
		this.doesWaterVaporize = false;
		this.hasNoSky = true;
		if (world instanceof WorldServer) {
			xiaCastleHandler = new XiaCastleLogicHandler(world);
			if (world.getChunkProvider() != null) xiaCastleHandler.start();
		}
	}

	@Override
	public void onWorldUpdateEntities() {
		super.onWorldUpdateEntities();
		if (xiaCastleHandler != null) xiaCastleHandler.onUpdate();
	}

	public final XiaCastleLogicHandler getXiaCastleHandler() {
		return xiaCastleHandler;
	}

	@Override
	public IRenderHandler getSkyRenderer() {
		return skyRender;
	}

	/**
	 * Return Vec3D with biome specific fog color
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public Vec3d getFogColor(float par1, float par2) {
		return new Vec3d(0.0D, 0.0D, 0.0D);
	}

	/**
	 * Creates the light to brightness table
	 */
	@Override
	protected void generateLightBrightnessTable() {
		float f = 0.1F;

		for (int i = 0; i <= 15; ++i) {
			float f1 = 1.0F - (float) i / 15.0F;
			this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
		}
	}

	/**
	 * Returns a new chunk provider which generates chunks for this world
	 */
	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkProviderXia(world, world.getWorldInfo().isMapFeaturesEnabled(), world.getSeed());
	}

	/**
	 * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
	 */
	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	/**
	 * Will check if the x, z position specified is alright to be set as the map spawn point
	 */
	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2) {
		return false;
	}

	/**
	 * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
	 */
	@Override
	public float calculateCelestialAngle(long par1, float par3) {
		return 0.5F;
	}

	/**
	 * True if the player can respawn in this dimension (true = overworld, false = nether).
	 */
	@Override
	public boolean canRespawnHere() {
		return false;
	}

	/**
	 * Returns true if the given X,Z coordinate should show environmental fog.
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public boolean doesXZShowFog(int par1, int par2) {
		return true;
	}

	@Override
	public int getAverageGroundLevel() {
		return 50;
	}

	@Override
	public WorldBorder createWorldBorder() {
		return new WorldBorder() {

			@Override
			public double getCenterX() {
				return super.getCenterX() / 8.0D;
			}

			@Override
			public double getCenterZ() {
				return super.getCenterZ() / 8.0D;
			}

		};
	}

	@Override
	public DimensionType getDimensionType() {
		return DimensionType.getById(VoidCraft.config.getDimensionIdXia());
	}
}
