package Tamaized.Voidcraft.blocks.tileentity;

import Tamaized.TamModized.tileentity.TamTileEntity;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.blocks.AIBlock;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityVoidNPCAIBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityAIBlock extends TamTileEntity {

	private TileEntityAIBlock parent;
	private EntityVoidNPCAIBase ai;

	private int state = 0;
	private int oldState = state;
	private boolean dead = false;
	private boolean fake = false;

	public TileEntityAIBlock() {
		super();
	}

	public void setup(EntityVoidNPCAIBase ai, TileEntityAIBlock parent) {
		this.parent = parent;
		this.ai = ai;
	}

	public void setFake() {
		fake = true;
	}

	public boolean isDead() {
		return dead;
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return (oldState.getBlock() != newSate.getBlock());
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void boom() {
		SoundType soundType = SoundType.GLASS;
		world.playSound(null, getPos().getX(), getPos().getY(), getPos().getZ(), soundType.getBreakSound(), SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F);
		if (ai != null) {
			if (state < 2) state++;
			else {
				ai.doAction(getPos());
				setDead();
			}
		}
	}

	public void setDead() {
		this.world.setBlockToAir(pos);
		this.world.removeTileEntity(pos);
		dead = true;
	}

	@Override
	public void onUpdate() {
		if (!this.world.isRemote) {
			if (!fake) {
				if (ai == null && parent == null) {
					setDead();
				} else {
					if (parent != null) {
						state = parent.getState();
						if (parent.isDead()) {
							parent = null;
							setDead();
							return;
						}
					}
				}
			}
			if (oldState != state) {
				oldState = state;
				world.setBlockState(pos, VoidCraft.blocks.AIBlock.getDefaultState().withProperty(AIBlock.STATE, state));
			}
		}
	}

	@Override
	protected void readNBT(NBTTagCompound nbt) {
		state = nbt.getInteger("state");
	}

	@Override
	protected NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("state", state);
		return nbt;
	}

}
