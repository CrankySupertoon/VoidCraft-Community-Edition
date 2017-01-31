package Tamaized.Voidcraft.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import Tamaized.Voidcraft.api.voidicpower.VoidicPowerItem;

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
