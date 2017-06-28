package tamaized.voidcraft.common.vademecum.contents.documentation.machines;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.client.gui.VadeMecumGUI;
import tamaized.voidcraft.proxy.ClientProxy;
import tamaized.voidcraft.common.vademecum.VadeMecumEntry;
import tamaized.voidcraft.common.vademecum.contents.documentation.machines.alchemy.VadeMecumPageListAlchemy;
import tamaized.voidcraft.common.vademecum.contents.documentation.machines.anchor.VadeMecumPageListAnchor;
import tamaized.voidcraft.common.vademecum.contents.documentation.machines.blastfurnace.VadeMecumPageListBlastFurnace;
import tamaized.voidcraft.common.vademecum.contents.documentation.machines.cable.VadeMecumPageListCable;
import tamaized.voidcraft.common.vademecum.contents.documentation.machines.charger.VadeMecumPageListCharger;
import tamaized.voidcraft.common.vademecum.contents.documentation.machines.crystallizer.VadeMecumPageListCrystallizer;
import tamaized.voidcraft.common.vademecum.contents.documentation.machines.generator.VadeMecumPageListGenerator;
import tamaized.voidcraft.common.vademecum.contents.documentation.machines.heimdall.VadeMecumPageListHeimdall;
import tamaized.voidcraft.common.vademecum.contents.documentation.machines.infusionAltar.VadeMecumPageListInfusionAltar;
import tamaized.voidcraft.common.vademecum.contents.documentation.machines.macerator.VadeMecumPageListMacerator;
import tamaized.voidcraft.common.vademecum.contents.documentation.machines.musicbox.VadeMecumPageListMusicBox;
import tamaized.voidcraft.common.vademecum.contents.documentation.machines.stabilizer.VadeMecumPageListStabilizer;
import tamaized.voidcraft.common.vademecum.contents.documentation.machines.teleporter.VadeMecumPageListTeleporter;
import net.minecraft.item.ItemStack;

public class VadeMecumMachinesEntry extends VadeMecumEntry {

	public static enum Entry {
		VoidInfusionAltar, VoidInfusedMacerator, BlastFurnace, Heimdall, VoidicGenerator, VoidicCable,

		VoidMusicBox, VoidicCharger, VoidicAnchor, VoidicCrystallizer, RealityStabilizer, AlchemyTable,

		Teleporter
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumEntry voidInfusionAltar;
	public VadeMecumEntry voidInfusedMacerator;
	public VadeMecumEntry blastFurnace;
	public VadeMecumEntry heimdall;
	public VadeMecumEntry voidicGenerator;
	public VadeMecumEntry voidicCable;

	public VadeMecumEntry voidMusicBox;
	public VadeMecumEntry voidicCharger;
	public VadeMecumEntry voidicAnchor;
	public VadeMecumEntry voidicCrystallizer;
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
		blastFurnace = new VadeMecumEntry("docs_Machines_blastFurnace", "", this, new VadeMecumPageListBlastFurnace());
		heimdall = new VadeMecumEntry("docs_Machines_heimdall", "", this, new VadeMecumPageListHeimdall());
		voidicGenerator = new VadeMecumEntry("docs_Machines_voidicGenerator", "", this, new VadeMecumPageListGenerator());
		voidicCable = new VadeMecumEntry("docs_Machines_voidicCable", "", this, new VadeMecumPageListCable());

		voidMusicBox = new VadeMecumEntry("docs_Machines_voidMusicBox", "", this, new VadeMecumPageListMusicBox());
		voidicCharger = new VadeMecumEntry("docs_Machines_voidicCharger", "", this, new VadeMecumPageListCharger());
		voidicAnchor = new VadeMecumEntry("docs_Machines_voidicAnchor", "", this, new VadeMecumPageListAnchor());
		voidicCrystallizer = new VadeMecumEntry("docs_Machines_voidicCrystallizer", "", this, new VadeMecumPageListCrystallizer());
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
		addButton(gui, getEntryID(Entry.BlastFurnace), new ItemStack(VoidCraft.blocks.voidBlastFurnace).getDisplayName(), new ItemStack(VoidCraft.blocks.voidBlastFurnace));
		addButton(gui, getEntryID(Entry.Heimdall), new ItemStack(VoidCraft.blocks.Heimdall).getDisplayName(), new ItemStack(VoidCraft.blocks.Heimdall));
		addButton(gui, getEntryID(Entry.VoidicGenerator), new ItemStack(VoidCraft.blocks.voidicGen).getDisplayName(), new ItemStack(VoidCraft.blocks.voidicGen));
		addButton(gui, getEntryID(Entry.VoidicCable), new ItemStack(VoidCraft.blocks.voidicCable).getDisplayName(), new ItemStack(VoidCraft.blocks.voidicCable));

		addButton(gui, getEntryID(Entry.VoidMusicBox), new ItemStack(VoidCraft.blocks.voidBox).getDisplayName(), new ItemStack(VoidCraft.blocks.voidBox));
		addButton(gui, getEntryID(Entry.VoidicCharger), new ItemStack(VoidCraft.blocks.voidicCharger).getDisplayName(), new ItemStack(VoidCraft.blocks.voidicCharger));
		addButton(gui, getEntryID(Entry.VoidicAnchor), new ItemStack(VoidCraft.blocks.voidicAnchor).getDisplayName(), new ItemStack(VoidCraft.blocks.voidicAnchor));
		addButton(gui, getEntryID(Entry.VoidicCrystallizer), new ItemStack(VoidCraft.blocks.voidicCrystallizer).getDisplayName(), new ItemStack(VoidCraft.blocks.voidicCrystallizer));
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
			case BlastFurnace:
				gui.changeEntry(blastFurnace);
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
			case VoidicAnchor:
				gui.changeEntry(voidicAnchor);
				break;
			case VoidicCrystallizer:
				gui.changeEntry(voidicCrystallizer);
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
