package Tamaized.Voidcraft.starforge.effects.tool.tier3;

import Tamaized.Voidcraft.starforge.effects.IStarForgeEffect;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fluids.IFluidBlock;

public class StarForgeEffectThreeByThree implements IStarForgeEffect {

	@Override
	public Type getType() {
		return Type.TOOL;
	}

	@Override
	public Tier getTier() {
		return Tier.THREE;
	}

	@Override
	public void update(ItemStack stack) {

	}

	@Override
	public float getSpeedMod() {
		return 0;
	}

	@Override
	public void onEntityHit(Entity entityUser, Entity entityHit) {

	}

	@Override
	public void onBlockBreak(Entity entityUser, World world, IBlockState state, BlockPos pos, EnumFacing face) {
		if (entityUser instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityUser;
			ItemStack stack = ((EntityPlayer) entityUser).getHeldItemMainhand();
			if (face != null) {
				switch (face) {
					case UP: // Y
						caseY(player, world, stack, pos);
						break;
					case DOWN: // Y
						caseY(player, world, stack, pos);
						break;
					case EAST: // X
						caseX(player, world, stack, pos);
						break;
					case WEST: // X
						caseX(player, world, stack, pos);
						break;
					case NORTH: // Z
						caseZ(player, world, stack, pos);
						break;
					case SOUTH: // Z
						caseZ(player, world, stack, pos);
						break;
					default:
						break;
				}
			}
		}
	}

	@Override
	public boolean onRightClick(Entity entityUser) {
		return false;
	}

	@Override
	public boolean onRightClickBlock(Entity entityUser, World world, IBlockState state, BlockPos pos, EnumFacing face) {
		return false;
	}

	@Override
	public String getName() {
		return "3x3 Mining";
	}

	private void caseY(EntityPlayer player, World world, ItemStack tool, BlockPos pos) {
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				removeBlockWithDrops(player, world, tool, pos.add(x, 0, z), true);
			}
		}
	}

	private void caseX(EntityPlayer player, World world, ItemStack tool, BlockPos pos) {
		for (int y = -1; y <= 1; y++) {
			for (int z = -1; z <= 1; z++) {
				removeBlockWithDrops(player, world, tool, pos.add(0, y, z), true);
			}
		}
	}

	private void caseZ(EntityPlayer player, World world, ItemStack tool, BlockPos pos) {
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				removeBlockWithDrops(player, world, tool, pos.add(x, y, 0), true);
			}
		}
	}

	private static void removeBlockWithDrops(EntityPlayer player, World world, ItemStack tool, BlockPos pos, boolean particles) {
		int harvestLevel = 8;
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		if (!world.isRemote && block != null && !block.isAir(state, world, pos) && !(block instanceof BlockLiquid) && !(block instanceof IFluidBlock) && block.getPlayerRelativeBlockHardness(state, player, world, pos) > 0) {
			int neededHarvestLevel = block.getHarvestLevel(state);
			if (neededHarvestLevel > harvestLevel && (!tool.isEmpty() && !tool.canHarvestBlock(state))) return;

			BreakEvent event = new BreakEvent(world, pos, state, player);
			MinecraftForge.EVENT_BUS.post(event);
			if (!event.isCanceled()) {
				if (!player.capabilities.isCreativeMode) {
					if (block.removedByPlayer(state, world, pos, player, true)) {
						block.onBlockDestroyedByPlayer(world, pos, state);
						block.harvestBlock(world, player, pos, state, world.getTileEntity(pos), tool);
					}
				} else world.setBlockToAir(pos);
			}

			if (particles) world.playEvent(2001, pos, Block.getStateId(state));
		}
	}

}
