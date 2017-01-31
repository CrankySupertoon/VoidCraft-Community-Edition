package Tamaized.Voidcraft.items;

import java.util.HashSet;

import Tamaized.TamModized.helper.RayTraceHelper;
import Tamaized.TamModized.particles.ParticleHelper;
import Tamaized.TamModized.particles.ParticleHelper.IParticlePacketData;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.api.voidicpower.VoidicPowerItem;
import Tamaized.Voidcraft.damageSources.DamageSourceVoidicInfusion;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fluids.IFluidBlock;

public class VoidicDrill extends VoidicPowerItem {

	public VoidicDrill(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	protected boolean canBeUsed() {
		return true;
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase entity, int tick) {
		if (!((tick % 5) == 0)) return;
		if (!(entity instanceof EntityPlayer)) return;
		EntityPlayer player = (EntityPlayer) entity;
		World world = player.world;
		EnumHand hand = player.getActiveHand();
		if (!world.isRemote) {
			HashSet<Entity> exclude = new HashSet<Entity>();
			exclude.add(player);
			int maxDistance = 10;
			RayTraceResult result = RayTraceHelper.tracePath(world, player, maxDistance, 1, exclude);
			Vec3d ray = RayTraceHelper.getPlayerTraceVec(player, maxDistance)[1];
			if (result != null) {
				ray = result.hitVec;
				if (result.entityHit != null) {
					result.entityHit.attackEntityFrom(new DamageSourceVoidicInfusion(), 5.0f);
				} else {
					switch (result.sideHit) {
						case UP: // Y
							caseY(player, world, stack, result.getBlockPos());
							break;
						case DOWN: // Y
							caseY(player, world, stack, result.getBlockPos());
							break;
						case EAST: // X
							caseX(player, world, stack, result.getBlockPos());
							break;
						case WEST: // X
							caseX(player, world, stack, result.getBlockPos());
							break;
						case NORTH: // Z
							caseZ(player, world, stack, result.getBlockPos());
							break;
						case SOUTH: // Z
							caseZ(player, world, stack, result.getBlockPos());
							break;
						default:
							break;
					}
				}
			}
			VoidDrillParticleData data = new VoidDrillParticleData(ray == null ? player.getLook(1.0F).scale(10) : ray, hand == EnumHand.OFF_HAND, player.getEntityId());
			ParticleHelper.sendPacketToClients(world, VoidCraft.particles.drillRayHandler, new Vec3d(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ()), 64, new ParticleHelper.ParticlePacketHelper(VoidCraft.particles.drillRayHandler, data));
		}
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

	@Override
	protected int getDefaultVoidicPower() {
		return 0;
	}

	@Override
	protected int getDefaultMaxVoidicPower() {
		return 5000;
	}

	public class VoidDrillParticleData implements IParticlePacketData {

		public final Vec3d target;
		public final boolean offhand;
		public final int id;

		public VoidDrillParticleData(Vec3d t, boolean o, int entityID) {
			target = t;
			offhand = o;
			id = entityID;
		}

	}

	@Override
	protected int useAmount() {
		return 1;
	}

}