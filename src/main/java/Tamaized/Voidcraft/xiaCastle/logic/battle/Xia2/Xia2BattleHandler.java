package Tamaized.Voidcraft.xiaCastle.logic.battle.Xia2;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia2;
import Tamaized.Voidcraft.xiaCastle.logic.battle.IBattleHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Xia2BattleHandler implements IBattleHandler {

	private int phase = 0;
	private int tick = 0;
	private int childPhase = 0;
	private int childPhaseModulate = 20;
	private boolean readyForInput = false;

	private boolean running;
	private boolean isDone = false;

	private World worldObj;
	private BlockPos pos;

	private EntityBossXia2 xia;

	@Override
	public void update() {
		if (worldObj != null && !worldObj.isRemote && running) {
			if (xia == null || !xia.isActive()) {
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
		phase = 0;
		isDone = false;
		readyForInput = false;
		xia = new EntityBossXia2(worldObj, this);
		xia.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
		ItemStack vade = new ItemStack(VoidCraft.items.vadeMecum);
		if (vade.hasCapability(CapabilityList.VADEMECUMITEM, null)) vade.getCapability(CapabilityList.VADEMECUMITEM, null).setBookState(true);
		xia.setHeldItem(EnumHand.MAIN_HAND, vade);
		worldObj.spawnEntity(xia);
		xia.start();
		running = true;
	}

	@Override
	public void stop() {
		readyForInput = false;
		isDone = false;
		if (xia != null) {
			if (xia.isDone()) isDone = true;
			worldObj.removeEntity(xia);
		}
		xia = null;
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
