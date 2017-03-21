package Tamaized.Voidcraft.world.dim.Xia;

import java.util.Random;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.world.SchematicLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;

public class TeleporterXia extends Teleporter {

	private SchematicLoader sut = new SchematicLoader();

	private final WorldServer worldServerInstance;
	private final Random random;

	public TeleporterXia(WorldServer par1WorldServer) {
		super(par1WorldServer);

		worldServerInstance = par1WorldServer;
		random = par1WorldServer.rand;
	}

	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) {
		if (entityIn.dimension != VoidCraft.config.getDimensionIdXia()) {
			BlockPos bedPos = entityIn instanceof EntityPlayer && ((EntityPlayer) entityIn).getBedLocation(0) != null ? ((EntityPlayer) entityIn).getBedLocation(0) : worldServerInstance.getSpawnPoint();
			while(!worldServerInstance.isAirBlock(bedPos)){
				bedPos = bedPos.up();
			}
			entityIn.setLocationAndAngles((double) bedPos.getX(), (double) bedPos.getY(), (double) bedPos.getZ(), entityIn.rotationYaw, entityIn.rotationPitch);
			return;
		}
		if (entityIn instanceof EntityPlayer) ((EntityPlayer) entityIn).addStat(VoidCraft.achievements.tooFar, 1);
		entityIn.setPositionAndUpdate(52.5, 62, 4.5);
		if (!isActive(entityIn)) makePortal(entityIn);
		entityIn.setPositionAndUpdate(52.5, 62, 4.5);
	}

	public boolean isActive(Entity e) {
		WorldProvider wp = worldServerInstance.provider;
		if (wp instanceof WorldProviderXia) {
			WorldProviderXia xiaProvider = ((WorldProviderXia) wp);
			xiaProvider.getXiaCastleHandler().validateInstance();
			return xiaProvider.getXiaCastleHandler().isActive();
		}
		return false;
	}

	@Override
	public boolean makePortal(Entity e) {
		byte b0 = 16;
		double d0 = -1.0D;
		int i = MathHelper.floor(e.posX);
		int j = MathHelper.floor(e.posY);
		int k = MathHelper.floor(e.posZ);

		if (e.dimension == VoidCraft.config.getDimensionIdXia()) {
			// doStructure(sut, worldServerInstance, new BlockPos(-11, 59, -4));
			// worldServerInstance.setBlockState(new BlockPos(0, 0, 58), voidCraft.blocks.xiaBlock.getDefaultState());
			SchematicLoader loader = new SchematicLoader();
			SchematicLoader.buildSchematic("xiacastle_new_2.schematic", loader, worldServerInstance, new BlockPos(0, 60, 0));
		}

		return true;
	}

	@Override
	public void removeStalePortalLocations(long par1) {

	}
}