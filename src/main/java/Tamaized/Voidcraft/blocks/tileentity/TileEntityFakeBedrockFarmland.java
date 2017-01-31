package Tamaized.Voidcraft.blocks.tileentity;

import Tamaized.TamModized.tileentity.TamTileEntity;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityFakeBedrockFarmland extends TamTileEntity {

	public static enum Alteration {
		NORMAL, REDSTONE, LAPIS, DIAMOND, EMERALD, GOLD
	}

	private Alteration currAlteration = Alteration.NORMAL;

	public static int getAlterationValue(Alteration alter) {
		return alter.ordinal();
	}

	public static Alteration getAlterationFromValue(int value) {
		return value > (Alteration.values().length - 1) ? Alteration.NORMAL : Alteration.values()[value];
	}

	public void setAlteration(Alteration alter) {
		currAlteration = alter;
		world.markBlockRangeForRenderUpdate(getPos(), getPos());
	}

	public Alteration getAlteration() {
		return currAlteration;
	}

	public static int getColor(Alteration alter) {
		switch (alter) {
			case REDSTONE:
				return 0xb24444;
			case LAPIS:
				return 0x47478e;
			case DIAMOND:
				return 0x14ebed;
			case EMERALD:
				return 0x478e47;
			case GOLD:
				return 0xccac00;
			default:
				return 0xFFFFFF;
		}
	}

	@Override
	protected void readNBT(NBTTagCompound nbt) {
		currAlteration = getAlterationFromValue(nbt.getInteger("alteration"));
	}

	@Override
	protected NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("alteration", getAlterationValue(currAlteration));
		return nbt;
	}

	@Override
	protected void onUpdate() {

	}

}
