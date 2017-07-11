package tamaized.voidcraft.common.world.dim.dalquor;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.voidcraft.common.handlers.ConfigHandler;
import tamaized.voidcraft.common.world.BiomeProviderDalQuor;

import javax.annotation.Nullable;

public class WorldProviderDalQuor extends WorldProvider {

	private static final DalQuorSkyRender skyRender = new DalQuorSkyRender();

	@Override
	protected void init() {
		biomeProvider = new BiomeProviderDalQuor(world);
		doesWaterVaporize = false;
		nether = false;
		hasSkyLight = true;
	}

	@Override
	public boolean canSnowAt(BlockPos pos, boolean checkLight) {
		return false;
	}

	@Override
	public boolean canDoRainSnowIce(net.minecraft.world.chunk.Chunk chunk) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getFogColor(float par1, float par2) {
		return new Vec3d(66d / 255d, 226d / 255d, 244d / 255d);
	}

	@Override
	protected void generateLightBrightnessTable() {
		float f = 0.0F;

		for (int i = 0; i <= 15; ++i) {
			float f1 = 1.0F - (float) i / 15.0F;
			lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
		}
	}

	@Override
	public IRenderHandler getSkyRenderer() {
		return skyRender;
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkProviderDalQuor(world, world.getWorldInfo().isMapFeaturesEnabled(), world.getSeed(), getSpawnCoordinate());
	}

	@Override
	public boolean isSurfaceWorld() {
		return true;
	}

	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2) {
		return false;
	}

	@Override
	public float calculateCelestialAngle(long par1, float par3) {
		return 0.75F;
	}

	@Nullable
	@SideOnly(Side.CLIENT)
	public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
		return null;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int par1, int par2) {
		return true;
	}

	@Override
	public int getAverageGroundLevel() {
		return 50;
	}

	@Override
	public DimensionType getDimensionType() {
		return DimensionType.getById(ConfigHandler.dimensionIdDalQuor);
	}
}
