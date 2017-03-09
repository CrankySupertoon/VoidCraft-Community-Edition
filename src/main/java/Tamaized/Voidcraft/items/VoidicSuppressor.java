package Tamaized.Voidcraft.items;

import Tamaized.Voidcraft.api.voidicpower.VoidicPowerItem;
import net.minecraft.creativetab.CreativeTabs;

public class VoidicSuppressor extends VoidicPowerItem {

	public VoidicSuppressor(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	@Override
	protected int getDefaultVoidicPower() {
		return 0;
	}

	@Override
	protected int getDefaultMaxVoidicPower() {
		return 20000;
	}

	@Override
	protected boolean canBeUsed() {
		return false;
	}

	@Override
	protected int useAmount() {
		return 1;
	}

}
