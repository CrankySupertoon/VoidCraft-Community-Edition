package Tamaized.Voidcraft.world.dim.dalQuor;

import java.util.Random;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class TeleporterDream extends Teleporter {

	private final WorldServer worldServerInstance;
	private final Random random;

	public TeleporterDream(WorldServer par1WorldServer) {
		super(par1WorldServer);

		worldServerInstance = par1WorldServer;
		random = par1WorldServer.rand;
	}

	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) {
		if (entityIn.dimension != VoidCraft.config.getDimensionIdDalQuor()) {
			BlockPos bedPos = entityIn instanceof EntityPlayer && ((EntityPlayer) entityIn).getBedLocation(0) != null ? ((EntityPlayer) entityIn).getBedLocation(0) : worldServerInstance.getSpawnPoint();
			while (!worldServerInstance.isAirBlock(bedPos)) {
				bedPos = bedPos.up();
			}
			entityIn.setLocationAndAngles((double) bedPos.getX(), (double) bedPos.getY(), (double) bedPos.getZ(), entityIn.rotationYaw, entityIn.rotationPitch);
			return;
		}
		// if (entityIn instanceof EntityPlayer) ((EntityPlayer) entityIn).addStat(VoidCraft.achievements.tooFar, 1);
		BlockPos pos = entityIn instanceof EntityPlayer && ((EntityPlayer) entityIn).getBedLocation(0) != null ? ((EntityPlayer) entityIn).getBedLocation(0) : DimensionManager.getWorld(0).getSpawnPoint();
		pos = new BlockPos(pos.getX(), 45, pos.getZ()).down();
		if (entityIn.dimension == VoidCraft.config.getDimensionIdDalQuor()) {
			boolean create = true;
			while (!worldServerInstance.isAirBlock(pos)) {
				pos = pos.up();
				if (worldServerInstance.getBlockState(pos).getBlock() == VoidCraft.blocks.dreamBed) {
					create = false;
					break;
				}
			}
			entityIn.setPositionAndUpdate(pos.getX(), pos.getY() + 1, pos.getZ());
			if (create) {
				IBlockState[] states = ChunkProviderDalQuor.getGenBlocks(worldServerInstance.getBiome(pos));
				for (int x = -1; x <= 1; x++) {
					for (int z = -1; z <= 1; z++) {
						if (worldServerInstance.isAirBlock(pos.add(x, -1, z))) worldServerInstance.setBlockState(pos.add(x, -1, z), states[0]);
					}
				}
				IBlockState iblockstate2 = VoidCraft.blocks.dreamBed.getDefaultState().withProperty(BlockBed.OCCUPIED, Boolean.valueOf(false)).withProperty(BlockBed.FACING, EnumFacing.NORTH).withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT);
				worldServerInstance.setBlockState(pos, iblockstate2, 10);
				worldServerInstance.setBlockState(pos.offset(EnumFacing.NORTH), iblockstate2.withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), 10);
			}
			entityIn.setPositionAndUpdate(pos.getX() + 0.5F, pos.getY() + 1, pos.getZ() + 0.5F);
		}
	}

	@Override
	public boolean makePortal(Entity e) {
		return true;
	}

	@Override
	public void removeStalePortalLocations(long par1) {

	}

}
