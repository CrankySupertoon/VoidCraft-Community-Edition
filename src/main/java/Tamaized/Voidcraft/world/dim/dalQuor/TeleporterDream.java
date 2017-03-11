package Tamaized.Voidcraft.world.dim.dalQuor;

import java.util.Random;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.blocks.BlockPortalVoid;
import Tamaized.Voidcraft.world.SchematicLoader;
import Tamaized.Voidcraft.world.dim.Xia.WorldProviderXia;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;

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
		if (entityIn.dimension != VoidCraft.config.getDimensionIDdalQuor()) {
			BlockPos bedPos = entityIn instanceof EntityPlayer && ((EntityPlayer) entityIn).getBedLocation(0) != null ? ((EntityPlayer) entityIn).getBedLocation(0) : worldServerInstance.getSpawnPoint();
			while (!worldServerInstance.isAirBlock(bedPos)) {
				bedPos = bedPos.up();
			}
			entityIn.setLocationAndAngles((double) bedPos.getX(), (double) bedPos.getY(), (double) bedPos.getZ(), entityIn.rotationYaw, entityIn.rotationPitch);
			return;
		}
		// if (entityIn instanceof EntityPlayer) ((EntityPlayer) entityIn).addStat(VoidCraft.achievements.tooFar, 1);
		entityIn.setPositionAndUpdate(entityIn.posX, entityIn.posY, entityIn.posZ);
		makePortal(entityIn);
		entityIn.setPositionAndUpdate(entityIn.posX, entityIn.posY, entityIn.posZ);
	}

	@Override
	public boolean makePortal(Entity e) {
		if (e.dimension == VoidCraft.config.getDimensionIDdalQuor()) {
			worldServerInstance.setBlockState(e.getPosition(), Blocks.OBSIDIAN.getDefaultState());
		}

		return true;
	}

	@Override
	public void removeStalePortalLocations(long par1) {

	}

}
