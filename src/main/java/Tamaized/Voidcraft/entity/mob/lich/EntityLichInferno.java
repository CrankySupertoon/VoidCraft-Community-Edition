package Tamaized.Voidcraft.entity.mob.lich;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.blocks.FireVoid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityLichInferno extends Entity {

	private int tick = 0;
	private int actionTick = 20 * 1;

	private int range = 5;

	private int currIndex = 0;
	private boolean headBack = false;

	public EntityLichInferno(World worldIn) {
		super(worldIn);
	}

	public EntityLichInferno(World world, BlockPos pos) {
		this(world);
		setPosition(pos.getX()+0.5, pos.getY(), pos.getZ()+0.5);
	}

	public EntityLichInferno(World world, BlockPos pos, int range) {
		this(world, pos);
		this.range = range;
	}

	public EntityLichInferno(World world, BlockPos pos, int range, int tick) {
		this(world, pos, range);
		actionTick = tick;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (tick % actionTick == 0) {
			killFire();
			calcIndex();
			if (!isDead) spreadFire();
			tick = 0;
		}
		tick++;
	}

	private void calcIndex() {
		if (headBack) {
			currIndex--;
			if (currIndex <= 0) setDead();
		} else {
			currIndex++;
			if (currIndex >= range) setDead();// headBack = true;
		}
	}

	private void killFire() {
		for (int x = -currIndex; x <= currIndex; x++) {
			for (int z = -currIndex; z <= currIndex; z++) {
				if (Math.abs(x) == currIndex || Math.abs(z) == currIndex) {
					int y = getLowY(getPosition().add(x, 0, z), 4);
					BlockPos pos = getPosition().add(x, y < 4 ? y - 1 : 3, z);
					IBlockState state = world.getBlockState(pos);
					if (state != null && state.getBlock() instanceof FireVoid) {
						world.setBlockToAir(pos);
					}
				}
			}
		}
	}

	private void spreadFire() {
		for (int x = -currIndex; x <= currIndex; x++) {
			for (int z = -currIndex; z <= currIndex; z++) {
				if (Math.abs(x) == currIndex || Math.abs(z) == currIndex) {
					int y = getLowY(getPosition().add(x, 0, z), 4);
					if (y < 4) placeFireAt(getPosition().add(x, y, z));
				}
			}
		}
	}

	private int getLowY(BlockPos pos, int y) {
		if (y < -4) return 4;
		if (world.isAirBlock(pos.add(0, y, 0))) return getLowY(pos, y - 1);
		else return y + 1;
	}

	private void placeFireAt(BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state == null || state.getBlock().isReplaceable(world, pos)) {
			world.setBlockState(pos, VoidCraft.blocks.fireVoid.getDefaultState());
		}
	}

	@Override
	protected void entityInit() {

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {

	}

}
