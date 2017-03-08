package Tamaized.Voidcraft.vadeMecum.contents;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.VadeMecumDocumentationEntryList;
import Tamaized.Voidcraft.vadeMecum.contents.progression.VadeMecumProgressionEntryList;
import net.minecraft.item.ItemStack;

public class VadeMecumMainEntry extends VadeMecumEntry {

	public static enum Entry {
		Docs, Progression
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public final VadeMecumDocumentationEntryList Docs;
	public final VadeMecumProgressionEntryList Progression;

	public VadeMecumMainEntry() {
		super("mainEntry", new ItemStack(VoidCraft.items.vadeMecum).getDisplayName(), null, null);
		Docs = new VadeMecumDocumentationEntryList();
		Progression = new VadeMecumProgressionEntryList();
	}

	@Override
	public void init(VadeMecumGUI gui) {
		clearButtons();
		addButton(gui, getEntryID(Entry.Progression), "voidcraft.VadeMecum.title.progression", new ItemStack(VoidCraft.blocks.ritualBlock));
		addButton(gui, getEntryID(Entry.Docs), "voidcraft.VadeMecum.title.docs", new ItemStack(VoidCraft.items.voidcrystal));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case Docs:
				gui.changeEntry(Docs.MAIN);
				break;
			case Progression:
				gui.changeEntry(Progression.MAIN);
				break;
			default:
				break;
		}
	}

	public void preLoadObject() {
		VoidCraft.instance.logger.info("Preloading Vade Mecum Entry Objects");
		Docs.preLoadObjects();
		Progression.preLoadObjects();
	}

}
