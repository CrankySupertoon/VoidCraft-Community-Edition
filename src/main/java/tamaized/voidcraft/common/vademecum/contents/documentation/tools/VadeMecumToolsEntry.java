package tamaized.voidcraft.common.vademecum.contents.documentation.tools;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.client.gui.VadeMecumGUI;
import tamaized.voidcraft.common.vademecum.VadeMecumEntry;
import tamaized.voidcraft.common.vademecum.contents.documentation.tools.realityTeleporter.VadeMecumPageListRealityTeleporter;
import tamaized.voidcraft.common.vademecum.contents.documentation.tools.spectreaxe.VadeMecumPageListSpectreAxe;
import tamaized.voidcraft.common.vademecum.contents.documentation.tools.spectrehoe.VadeMecumPageListSpectreHoe;
import tamaized.voidcraft.common.vademecum.contents.documentation.tools.spectrepick.VadeMecumPageListSpectrePick;
import tamaized.voidcraft.common.vademecum.contents.documentation.tools.spectreshovel.VadeMecumPageListSpectreShovel;
import tamaized.voidcraft.common.vademecum.contents.documentation.tools.voidShield.VadeMecumPageListVoidShield;
import tamaized.voidcraft.common.vademecum.contents.documentation.tools.voidaxe.VadeMecumPageListVoidAxe;
import tamaized.voidcraft.common.vademecum.contents.documentation.tools.voidhoe.VadeMecumPageListVoidHoe;
import tamaized.voidcraft.common.vademecum.contents.documentation.tools.voidicdrill.VadeMecumPageListVoidicDrill;
import tamaized.voidcraft.common.vademecum.contents.documentation.tools.voidpick.VadeMecumPageListVoidPick;
import tamaized.voidcraft.common.vademecum.contents.documentation.tools.voidspade.VadeMecumPageListVoidSpade;
import tamaized.voidcraft.proxy.ClientProxy;

public class VadeMecumToolsEntry extends VadeMecumEntry {

	public static enum Entry {
		VoidShield, VoidPick, VoidAxe, VoidShovel, VoidHoe, SpectrePick,

		SpectreAxe, SpectreShovel, SpectreHoe, VoidicDrill, RealityTeleporter
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumEntry voidShield;
	public VadeMecumEntry voidPick;
	public VadeMecumEntry voidAxe;
	public VadeMecumEntry voidShovel;
	public VadeMecumEntry voidHoe;
	public VadeMecumEntry spectrePick;

	public VadeMecumEntry spectreAxe;
	public VadeMecumEntry spectreShovel;
	public VadeMecumEntry spectreHoe;
	public VadeMecumEntry voidicDrill;
	public VadeMecumEntry realityTeleporter;

	public VadeMecumToolsEntry(VadeMecumEntry back) {
		super("docs_Tools", VoidCraft.modid + ".VadeMecum.docs.title.tools", back, null);
	}

	@Override
	public void initObjects() {
		voidShield = new VadeMecumEntry("docs_Tools_voidShield", "", this, new VadeMecumPageListVoidShield());

		voidPick = new VadeMecumEntry("docs_Tools_voidPick", "", this, new VadeMecumPageListVoidPick());
		voidAxe = new VadeMecumEntry("docs_Tools_voidAxe", "", this, new VadeMecumPageListVoidAxe());
		voidShovel = new VadeMecumEntry("docs_Tools_voidShovel", "", this, new VadeMecumPageListVoidSpade());
		voidHoe = new VadeMecumEntry("docs_Tools_voidHoe", "", this, new VadeMecumPageListVoidHoe());
		spectrePick = new VadeMecumEntry("docs_Tools_spectrePick", "", this, new VadeMecumPageListSpectrePick());

		spectreAxe = new VadeMecumEntry("docs_Tools_spectreAxe", "", this, new VadeMecumPageListSpectreAxe());
		spectreShovel = new VadeMecumEntry("docs_Tools_spectreShovel", "", this, new VadeMecumPageListSpectreShovel());
		spectreHoe = new VadeMecumEntry("docs_Tools_spectreHoe", "", this, new VadeMecumPageListSpectreHoe());
		voidicDrill = new VadeMecumEntry("docs_Tools_voidicDrill", "", this, new VadeMecumPageListVoidicDrill());
		realityTeleporter = new VadeMecumEntry("docs_Items_realityTeleporter", "", this, new VadeMecumPageListRealityTeleporter());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();

		addButton(gui, getEntryID(Entry.VoidShield), new ItemStack(VoidCraft.items.voidCrystalShield).getDisplayName(), new ItemStack(VoidCraft.items.voidCrystalShield));
		addButton(gui, getEntryID(Entry.VoidPick), new ItemStack(VoidCraft.tools.voidPickaxe).getDisplayName(), new ItemStack(VoidCraft.tools.voidPickaxe));
		addButton(gui, getEntryID(Entry.VoidAxe), new ItemStack(VoidCraft.tools.voidAxe).getDisplayName(), new ItemStack(VoidCraft.tools.voidAxe));
		addButton(gui, getEntryID(Entry.VoidShovel), new ItemStack(VoidCraft.tools.voidSpade).getDisplayName(), new ItemStack(VoidCraft.tools.voidSpade));
		addButton(gui, getEntryID(Entry.VoidHoe), new ItemStack(VoidCraft.tools.voidHoe).getDisplayName(), new ItemStack(VoidCraft.tools.voidHoe));
		addButton(gui, getEntryID(Entry.SpectrePick), new ItemStack(VoidCraft.tools.spectrePickaxe).getDisplayName(), new ItemStack(VoidCraft.tools.spectrePickaxe));

		addButton(gui, getEntryID(Entry.SpectreAxe), new ItemStack(VoidCraft.tools.spectreAxe).getDisplayName(), new ItemStack(VoidCraft.tools.spectreAxe));
		addButton(gui, getEntryID(Entry.SpectreShovel), new ItemStack(VoidCraft.tools.spectreSpade).getDisplayName(), new ItemStack(VoidCraft.tools.spectreSpade));
		addButton(gui, getEntryID(Entry.SpectreHoe), new ItemStack(VoidCraft.tools.spectreHoe).getDisplayName(), new ItemStack(VoidCraft.tools.spectreHoe));
		addButton(gui, getEntryID(Entry.VoidicDrill), new ItemStack(VoidCraft.items.voidicDrill).getDisplayName(), new ItemStack(VoidCraft.items.voidicDrill));
		addButton(gui, getEntryID(Entry.RealityTeleporter), new ItemStack(VoidCraft.items.realityTeleporter).getDisplayName(), new ItemStack(VoidCraft.items.realityTeleporter));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case VoidShield:
				gui.changeEntry(voidShield);
				break;
			case VoidPick:
				gui.changeEntry(voidPick);
				break;
			case VoidAxe:
				gui.changeEntry(voidAxe);
				break;
			case VoidShovel:
				gui.changeEntry(voidShovel);
				break;
			case VoidHoe:
				gui.changeEntry(voidHoe);
				break;
			case SpectrePick:
				gui.changeEntry(spectrePick);
				break;
			case SpectreAxe:
				gui.changeEntry(spectreAxe);
				break;
			case SpectreShovel:
				gui.changeEntry(spectreShovel);
				break;
			case SpectreHoe:
				gui.changeEntry(spectreHoe);
				break;
			case VoidicDrill:
				gui.changeEntry(voidicDrill);
				break;
			case RealityTeleporter:
				gui.changeEntry(realityTeleporter);
				break;
			default:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.MAIN);
				break;
		}
	}
}
