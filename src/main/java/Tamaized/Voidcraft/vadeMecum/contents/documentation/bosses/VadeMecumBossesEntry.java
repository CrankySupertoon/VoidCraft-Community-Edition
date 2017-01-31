package Tamaized.Voidcraft.vadeMecum.contents.documentation.bosses;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.bosses.herobrine.VadeMecumPageListHerobrine;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.bosses.pawn.VadeMecumPageListCorruptedPawn;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.bosses.twins.VadeMecumPageListTwins;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.bosses.voidicDragon.VadeMecumPageListVoidicDragon;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.bosses.xia.VadeMecumPageListXia;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VadeMecumBossesEntry extends VadeMecumEntry {

	public static enum Entry {
		CorruptedPawn, Herobrine, Twins, Xia, VoidicDragon
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumEntry corruptedPawn;
	public VadeMecumEntry herobrine;
	public VadeMecumEntry twins;
	public VadeMecumEntry xia;
	public VadeMecumEntry voidicDragon;

	public VadeMecumBossesEntry(VadeMecumEntry back) {
		super("docs_Bosses", "Bosses", back, null);
	}

	@Override
	public void initObjects() {
		corruptedPawn = new VadeMecumEntry("docs_Bosses_corruptedPawn", "", this, new VadeMecumPageListCorruptedPawn());
		herobrine = new VadeMecumEntry("docs_Bosses_herobrine", "", this, new VadeMecumPageListHerobrine());
		twins = new VadeMecumEntry("docs_Bosses_twins", "", this, new VadeMecumPageListTwins());
		xia = new VadeMecumEntry("docs_Bosses_xia", "", this, new VadeMecumPageListXia());
		voidicDragon = new VadeMecumEntry("docs_Bosses_voidicDragon", "", this, new VadeMecumPageListVoidicDragon());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();

		addButton(gui, getEntryID(Entry.CorruptedPawn), VoidCraft.modid + ".VadeMecum.docs.title.corruptedPawn", new ItemStack(VoidCraft.items.ChainedSkull));
		addButton(gui, getEntryID(Entry.Herobrine), VoidCraft.modid + ".VadeMecum.docs.title.herobrine", new ItemStack(Blocks.TNT));
		addButton(gui, getEntryID(Entry.Twins), VoidCraft.modid + ".VadeMecum.docs.title.twins", new ItemStack(Blocks.GRASS));
		addButton(gui, getEntryID(Entry.Xia), VoidCraft.modid + ".VadeMecum.docs.title.xia", new ItemStack(VoidCraft.armors.xiaHelmet));
		addButton(gui, getEntryID(Entry.VoidicDragon), VoidCraft.modid + ".VadeMecum.docs.title.voidicDragon", new ItemStack(Blocks.DRAGON_EGG));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case CorruptedPawn:
				gui.changeEntry(corruptedPawn);
				break;
			case Herobrine:
				gui.changeEntry(herobrine);
				break;
			case Twins:
				gui.changeEntry(twins);
				break;
			case Xia:
				gui.changeEntry(xia);
				break;
			case VoidicDragon:
				gui.changeEntry(voidicDragon);
				break;
			default:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.MAIN);
				break;
		}
	}

}
