package Tamaized.Voidcraft.vadeMecum.contents.documentation.armor;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.armor.voidarmor.VadeMecumPageListVoidArmor;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.armor.xia.VadeMecumPageListXiaArmor;
import net.minecraft.item.ItemStack;

public class VadeMecumArmorEntry extends VadeMecumEntry {

	public static enum Entry {
		Void, Xia
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumEntry voidCrystal;
	public VadeMecumEntry xia;

	public VadeMecumArmorEntry(VadeMecumEntry back) {
		super("docs_ARMOR", "Armors", back, null);
	}

	@Override
	public void initObjects() {
		voidCrystal = new VadeMecumEntry("docs_Armor_voidCrystal", "", this, new VadeMecumPageListVoidArmor());
		xia = new VadeMecumEntry("docs_Armor_xia", "", this, new VadeMecumPageListXiaArmor());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();
		addButton(gui, getEntryID(Entry.Void), VoidCraft.modid + ".VadeMecum.docs.title.voidArmor", new ItemStack(VoidCraft.armors.voidHelmet));
		addButton(gui, getEntryID(Entry.Xia), VoidCraft.modid + ".VadeMecum.docs.title.xiaArmor", new ItemStack(VoidCraft.armors.xiaHelmet));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case Void:
				gui.changeEntry(voidCrystal);
				break;
			case Xia:
				gui.changeEntry(xia);
				break;
			default:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.MAIN);
				break;
		}
	}

}
