package Tamaized.Voidcraft.xiaCastle.logic.battle.Xia;

import java.util.ArrayList;
import java.util.Iterator;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.xiaCastle.logic.battle.IBattleHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class XiaBattleHandler implements IBattleHandler {

	private int phase = 0;
	private int tick = 0;
	private int childPhase = 0;
	private int childPhaseModulate = 20;
	private boolean readyForInput = false;

	private boolean running;
	private boolean isDone = false;

	private World worldObj;
	private BlockPos pos;

	private AxisAlignedBB checkBB;
	private ArrayList<EntityPlayer> players = new ArrayList<EntityPlayer>();
	private EntityBossXia xia;

	public ArrayList<EntityPlayer> getPlayers() {
		return players;
	}

	@Override
	public void update() {
		if (worldObj != null && !worldObj.isRemote && running) {
			if (checkBB != null) {
				Iterator<EntityPlayer> iter = players.iterator();
				while (iter.hasNext()) {
					EntityPlayer player = iter.next();
					if (!checkBB.isVecInside(player.getPositionVector())) {
						iter.remove();
					}
				}
			}
			if (xia == null || !xia.isActive() || players.isEmpty()) {
				stop();
				return;
			}
		}
	}

	@Override
	public void start(World world, BlockPos p) {
		worldObj = world;
		pos = p;
		stop();
		BlockPos doorPos = new BlockPos(54, 76, 82);
		for (int x = 0; x > -5; x--) {
			for (int y = 0; y < 4; y++) {
				worldObj.setBlockState(doorPos.add(x, y, 0), (x == 0 || x == -4 || y == 0 || y == 3) ? VoidCraft.blocks.realityHole.getDefaultState() : VoidCraft.blocks.blockNoBreak.getDefaultState());
			}
		}
		phase = 0;
		isDone = false;
		readyForInput = false;
		xia = new EntityBossXia(worldObj, this);
		xia.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 17.5, pos.getZ() + 43.5);
		xia.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(VoidCraft.armors.xiaHelmet));
		xia.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(VoidCraft.armors.xiaChest));
		xia.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(VoidCraft.armors.xiaLegs));
		xia.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(VoidCraft.armors.xiaBoots));
		xia.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(VoidCraft.tools.demonSword));
		ItemStack vade = new ItemStack(VoidCraft.items.vadeMecum);
		if (vade.hasCapability(CapabilityList.VADEMECUMITEM, null)) vade.getCapability(CapabilityList.VADEMECUMITEM, null).setBookState(true);
		xia.setHeldItem(EnumHand.OFF_HAND, vade);
		worldObj.spawnEntity(xia);
		checkBB = new AxisAlignedBB(pos.add(-19, -1, -3), pos.add(19, 26, 51));
		players.addAll(worldObj.getEntitiesWithinAABB(EntityPlayer.class, checkBB));
		xia.start();
		running = true;
	}

	@Override
	public void stop() {
		players.clear();
		readyForInput = false;
		isDone = false;
		if (xia != null) {
			if (xia.isDone()) isDone = true;
			worldObj.removeEntity(xia);
		}
		xia = null;
		if (worldObj != null) {
			BlockPos doorPos = new BlockPos(54, 76, 82);
			for (int x = 0; x > -5; x--) {
				for (int y = 0; y < 4; y++) {
					worldObj.setBlockToAir(doorPos.add(x, y, 0));
				}
			}
		}
		running = false;
	}

	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

	@Override
	public void setDone() {
		stop();
		isDone = true;
	}

}
