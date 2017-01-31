package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.cosmicMaterial.VadeMecumPageListCosmicMaterial;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.realityhole.VadeMecumPageListRealityHole;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.ritual.VadeMecumPageListRitualBlock;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.softbedrock.VadeMecumPageListSoftBedrock;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidbrick.VadeMecumPageListVoidBrick;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidcrystalblock.VadeMecumPageListVoidCrystalBlock;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidcrystalore.VadeMecumPageListVoidCrystalOre;
import net.minecraft.item.ItemStack;

public class VadeMecumBlocksEntry extends VadeMecumEntry {

	public static enum Entry {
		VoidCrystalBlock, VoidCrystalOre, SoftBedrock, VoidBrick, HoleinReality, CosmicMaterial, RitualBlock
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumEntry voidCrystalBlock;
	public VadeMecumEntry voidCrystalOre;
	public VadeMecumEntry softBedrock;
	public VadeMecumEntry voidBrick;
	public VadeMecumEntry holeInReality;
	public VadeMecumEntry cosmicMaterial;
	public VadeMecumEntry ritualBlock;

	public VadeMecumBlocksEntry(VadeMecumEntry back) {
		super("docs_Blocks", "Blocks", back, null);
	}

	@Override
	public void initObjects() {
		voidCrystalBlock = new VadeMecumEntry("docs_Blocks_voidCrystalBlock", "", this, new VadeMecumPageListVoidCrystalBlock());
		voidCrystalOre = new VadeMecumEntry("docs_Blocks_voidCrystalOre", "", this, new VadeMecumPageListVoidCrystalOre());
		softBedrock = new VadeMecumEntry("docs_Blocks_softBedrock", "", this, new VadeMecumPageListSoftBedrock());
		voidBrick = new VadeMecumEntry("docs_Blocks_voidBrick", "", this, new VadeMecumPageListVoidBrick());
		holeInReality = new VadeMecumEntry("docs_Blocks_holeInReality", "", this, new VadeMecumPageListRealityHole());
		cosmicMaterial = new VadeMecumEntry("docs_Blocks_cosmicMaterial", "", this, new VadeMecumPageListCosmicMaterial());
		ritualBlock = new VadeMecumEntry("docs_Blocks_ritualBlock", "", this, new VadeMecumPageListRitualBlock());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();
		addButton(gui, getEntryID(Entry.VoidCrystalBlock), new ItemStack(VoidCraft.blocks.blockVoidcrystal).getDisplayName(), new ItemStack(VoidCraft.blocks.blockVoidcrystal));
		addButton(gui, getEntryID(Entry.VoidCrystalOre), new ItemStack(VoidCraft.blocks.oreVoidcrystal).getDisplayName(), new ItemStack(VoidCraft.blocks.oreVoidcrystal));
		addButton(gui, getEntryID(Entry.SoftBedrock), new ItemStack(VoidCraft.blocks.blockFakeBedrock).getDisplayName(), new ItemStack(VoidCraft.blocks.blockFakeBedrock));
		addButton(gui, getEntryID(Entry.VoidBrick), new ItemStack(VoidCraft.blocks.blockVoidbrick).getDisplayName(), new ItemStack(VoidCraft.blocks.blockVoidbrick));
		addButton(gui, getEntryID(Entry.HoleinReality), new ItemStack(VoidCraft.blocks.realityHole).getDisplayName(), new ItemStack(VoidCraft.blocks.realityHole));
		addButton(gui, getEntryID(Entry.CosmicMaterial), new ItemStack(VoidCraft.blocks.cosmicMaterial).getDisplayName(), new ItemStack(VoidCraft.blocks.cosmicMaterial));
		// addButton(gui, getEntryID(Entry.RitualBlock), new ItemStack(voidCraft.blocks.ritualBlock).getDisplayName(), new ItemStack(voidCraft.blocks.ritualBlock));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case VoidCrystalBlock:
				gui.changeEntry(voidCrystalBlock);
				break;
			case VoidCrystalOre:
				gui.changeEntry(voidCrystalOre);
				break;
			case SoftBedrock:
				gui.changeEntry(softBedrock);
				break;
			case VoidBrick:
				gui.changeEntry(voidBrick);
				break;
			case HoleinReality:
				gui.changeEntry(holeInReality);
				break;
			case CosmicMaterial:
				gui.changeEntry(cosmicMaterial);
				break;
			case RitualBlock:
				gui.changeEntry(ritualBlock);
				break;
			default:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.MAIN);
				break;
		}
	}

}
