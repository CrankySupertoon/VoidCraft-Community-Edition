package Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine;

import Tamaized.Voidcraft.entity.boss.herobrine.EntityBossHerobrine;
import Tamaized.Voidcraft.xiaCastle.logic.battle.IBattleHandler;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HerobrineBattleHandler implements IBattleHandler {

	private int phase = 0;
	private int tick = 0;
	private int childPhase = 0;
	private int childPhaseModulate = 20;
	private boolean readyForInput = false;

	private boolean running;
	private boolean isDone = false;

	private World worldObj;
	private BlockPos pos;

	private EntityBossHerobrine herobrine;

	@Override
	public void update() {
		if (worldObj != null && !worldObj.isRemote && running) {
			if (herobrine == null || !herobrine.isActive()) {
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
		for (int z = -2; z <= 2; z++) {
			for (int y = 5; y > 0; y--) {
				world.setBlockState(p.add(11, y, z), Blocks.NETHER_BRICK.getDefaultState());
			}
		}
		herobrine = new EntityBossHerobrine(worldObj, this);
		herobrine.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
		worldObj.spawnEntity(herobrine);
		herobrine.start();
		running = true;
	}

	@Override
	public void stop() {
		if (pos == null) return;
		readyForInput = false;
		isDone = false;
		if (herobrine != null) {
			if (herobrine.isDone()) isDone = true;
			worldObj.removeEntity(herobrine);
		}
		herobrine = null;
		for (int z = -2; z <= 2; z++) {
			for (int y = 5; y > 0; y--) {
				worldObj.setBlockToAir(pos.add(11, y, z));
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
