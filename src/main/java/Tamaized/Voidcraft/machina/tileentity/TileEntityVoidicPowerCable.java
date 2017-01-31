package Tamaized.Voidcraft.machina.tileentity;

import Tamaized.Voidcraft.api.voidicpower.TileEntityVoidicPower;
import Tamaized.Voidcraft.api.voidicpower.VoidicPowerHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileEntityVoidicPowerCable extends TileEntityVoidicPower {

	public void readNBT(NBTTagCompound nbt) {

	}

	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		return nbt;
	}

	@Override
	public void onUpdate() {
		VoidicPowerHandler.sendToSurrounding(this, world, pos);
	}

	@Override
	public int getMaxPower() {
		return 160;
	}

	@Override
	public int maxPowerTransfer() {
		return 160;
	}

	@Override
	public boolean canOutputPower(EnumFacing face) {
		return true;
	}

	@Override
	public boolean canInputPower(EnumFacing face) {
		return !blockFace.contains(face);
	}

}
