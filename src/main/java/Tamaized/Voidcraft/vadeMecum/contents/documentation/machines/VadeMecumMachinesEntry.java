package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.alchemy.VadeMecumPageListAlchemy;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.cable.VadeMecumPageListCable;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.charger.VadeMecumPageListCharger;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.generator.VadeMecumPageListGenerator;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.heimdall.VadeMecumPageListHeimdall;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.infusionAltar.VadeMecumPageListInfusionAltar;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.macerator.VadeMecumPageListMacerator;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.musicbox.VadeMecumPageListMusicBox;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.stabilizer.VadeMecumPageListStabilizer;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.teleporter.VadeMecumPageListTeleporter;
import net.minecraft.item.ItemStack;

public class VadeMecumMachinesEntry extends VadeMecumEntry {

	public static enum Entry {
		VoidInfusionAltar, VoidInfusedMacerator, Heimdall, VoidicGenerator, VoidicCable, VoidMusicBox, VoidicCharger, RealityStabilizer, AlchemyTable, Teleporter
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumEntry voidInfusionAltar;
	public VadeMecumEntry voidInfusedMacerator;
	public VadeMecumEntry heimdall;
	public VadeMecumEntry voidicGenerator;
	public VadeMecumEntry voidicCable;
	public VadeMecumEntry voidMusicBox;

	public VadeMecumEntry voidicCharger;
	public VadeMecumEntry realityStabilizer;
	public VadeMecumEntry alchemyTable;
	public VadeMecumEntry teleporter;

	public VadeMecumMachinesEntry(VadeMecumEntry back) {
		super("docs_Machines", VoidCraft.modid + ".VadeMecum.docs.title.machines", back, null);
	}

	@Override
	public void initObjects() {
		voidInfusionAltar = new VadeMecumEntry("docs_Machines_voidInfusionAltar", "", this, new VadeMecumPageListInfusionAltar());
		voidInfusedMacerator = new VadeMecumEntry("docs_Machines_voidInfusedMacerator", "", this, new VadeMecumPageListMacerator());
		heimdall = new VadeMecumEntry("docs_Machines_heimdall", "", this, new VadeMecumPageListHeimdall());
		voidicGenerator = new VadeMecumEntry("docs_Machines_voidicGenerator", "", this, new VadeMecumPageListGenerator());
		voidicCable = new VadeMecumEntry("docs_Machines_voidicCable", "", this, new VadeMecumPageListCable());
		voidMusicBox = new VadeMecumEntry("docs_Machines_voidMusicBox", "", this, new VadeMecumPageListMusicBox());

		voidicCharger = new VadeMecumEntry("docs_Machines_voidicCharger", "", this, new VadeMecumPageListCharger());
		realityStabilizer = new VadeMecumEntry("docs_Machines_realityStabilizer", "", this, new VadeMecumPageListStabilizer());
		alchemyTable = new VadeMecumEntry("docs_Machines_alchemyTable", "", this, new VadeMecumPageListAlchemy());
		teleporter = new VadeMecumEntry("docs_Machines_teleporter", "", this, new VadeMecumPageListTeleporter());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();
		addButton(gui, getEntryID(Entry.VoidInfusionAltar), new ItemStack(VoidCraft.blocks.voidInfuser).getDisplayName(), new ItemStack(VoidCraft.blocks.voidInfuser));
		addButton(gui, getEntryID(Entry.VoidInfusedMacerator), new ItemStack(VoidCraft.blocks.voidMacerator).getDisplayName(), new ItemStack(VoidCraft.blocks.voidMacerator));
		addButton(gui, getEntryID(Entry.Heimdall), new ItemStack(VoidCraft.blocks.Heimdall).getDisplayName(), new ItemStack(VoidCraft.blocks.Heimdall));
		addButton(gui, getEntryID(Entry.VoidicGenerator), new ItemStack(VoidCraft.blocks.voidicGen).getDisplayName(), new ItemStack(VoidCraft.blocks.voidicGen));
		addButton(gui, getEntryID(Entry.VoidicCable), new ItemStack(VoidCraft.blocks.voidicCable).getDisplayName(), new ItemStack(VoidCraft.blocks.voidicCable));
		addButton(gui, getEntryID(Entry.VoidMusicBox), new ItemStack(VoidCraft.blocks.voidBox).getDisplayName(), new ItemStack(VoidCraft.blocks.voidBox));

		addButton(gui, getEntryID(Entry.VoidicCharger), new ItemStack(VoidCraft.blocks.voidicCharger).getDisplayName(), new ItemStack(VoidCraft.blocks.voidicCharger));
		addButton(gui, getEntryID(Entry.RealityStabilizer), new ItemStack(VoidCraft.blocks.realityStabilizer).getDisplayName(), new ItemStack(VoidCraft.blocks.realityStabilizer));
		addButton(gui, getEntryID(Entry.AlchemyTable), new ItemStack(VoidCraft.blocks.voidicAlchemyTable).getDisplayName(), new ItemStack(VoidCraft.blocks.voidicAlchemyTable));
		addButton(gui, getEntryID(Entry.Teleporter), new ItemStack(VoidCraft.blocks.realityTeleporterBlock).getDisplayName(), new ItemStack(VoidCraft.blocks.realityTeleporterBlock));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case VoidInfusionAltar:
				gui.changeEntry(voidInfusionAltar);
				break;
			case VoidInfusedMacerator:
				gui.changeEntry(voidInfusedMacerator);
				break;
			case Heimdall:
				gui.changeEntry(heimdall);
				break;
			case VoidicGenerator:
				gui.changeEntry(voidicGenerator);
				break;
			case VoidicCable:
				gui.changeEntry(voidicCable);
				break;
			case VoidMusicBox:
				gui.changeEntry(voidMusicBox);
				break;
			case VoidicCharger:
				gui.changeEntry(voidicCharger);
				break;
			case RealityStabilizer:
				gui.changeEntry(realityStabilizer);
				break;
			case AlchemyTable:
				gui.changeEntry(alchemyTable);
				break;
			case Teleporter:
				gui.changeEntry(teleporter);
				break;
			default:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.MAIN);
				break;
		}
	}

}
