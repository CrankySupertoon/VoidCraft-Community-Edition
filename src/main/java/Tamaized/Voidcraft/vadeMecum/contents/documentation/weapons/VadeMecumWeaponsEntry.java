package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.angelicsword.VadeMecumPageListAngelicSword;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.archangelicsword.VadeMecumPageListArchSword;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.bindsword.VadeMecumPageListBindSword;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.demonsword.VadeMecumPageListDemonSword;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.moltensword.VadeMecumPageListMoltenSword;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.voidsword.VadeMecumPageListVoidSword;
import net.minecraft.item.ItemStack;

public class VadeMecumWeaponsEntry extends VadeMecumEntry {

	public static enum Entry {
		VoidSword, AngelicSword, BindSword, MoltenSword, ArchAngelicSword, DemonSword
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumEntry voidSword;
	public VadeMecumEntry angelicSword;
	public VadeMecumEntry bindSword;
	public VadeMecumEntry moltenSword;
	public VadeMecumEntry archAngelicSword;
	public VadeMecumEntry demonSword;

	public VadeMecumWeaponsEntry(VadeMecumEntry back) {
		super("docs_Weapons", VoidCraft.modid + ".VadeMecum.docs.title.weps", back, null);
	}

	@Override
	public void initObjects() {
		voidSword = new VadeMecumEntry("docs_Weapons_voidSword", "", this, new VadeMecumPageListVoidSword());
		angelicSword = new VadeMecumEntry("docs_Weapons_angelicSword", "", this, new VadeMecumPageListAngelicSword());
		bindSword = new VadeMecumEntry("docs_Weapons_bindSword", "", this, new VadeMecumPageListBindSword());
		moltenSword = new VadeMecumEntry("docs_Weapons_moltenSword", "", this, new VadeMecumPageListMoltenSword());
		archAngelicSword = new VadeMecumEntry("docs_Weapons_archAngelicSword", "", this, new VadeMecumPageListArchSword());
		demonSword = new VadeMecumEntry("docs_Weapons_demonSword", "", this, new VadeMecumPageListDemonSword());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();
		addButton(gui, getEntryID(Entry.VoidSword), new ItemStack(VoidCraft.tools.voidSword).getDisplayName(), new ItemStack(VoidCraft.tools.voidSword));
		addButton(gui, getEntryID(Entry.AngelicSword), new ItemStack(VoidCraft.tools.angelicSword).getDisplayName(), new ItemStack(VoidCraft.tools.angelicSword));
		addButton(gui, getEntryID(Entry.BindSword), new ItemStack(VoidCraft.tools.chainSword).getDisplayName(), new ItemStack(VoidCraft.tools.chainSword));
		addButton(gui, getEntryID(Entry.MoltenSword), new ItemStack(VoidCraft.tools.moltenSword).getDisplayName(), new ItemStack(VoidCraft.tools.moltenSword));
		addButton(gui, getEntryID(Entry.ArchAngelicSword), new ItemStack(VoidCraft.tools.archSword).getDisplayName(), new ItemStack(VoidCraft.tools.archSword));
		addButton(gui, getEntryID(Entry.DemonSword), new ItemStack(VoidCraft.tools.demonSword).getDisplayName(), new ItemStack(VoidCraft.tools.demonSword));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case VoidSword:
				gui.changeEntry(voidSword);
				break;
			case AngelicSword:
				gui.changeEntry(angelicSword);
				break;
			case BindSword:
				gui.changeEntry(bindSword);
				break;
			case MoltenSword:
				gui.changeEntry(moltenSword);
				break;
			case ArchAngelicSword:
				gui.changeEntry(archAngelicSword);
				break;
			case DemonSword:
				gui.changeEntry(demonSword);
				break;
			default:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.MAIN);
				break;
		}
	}

}
