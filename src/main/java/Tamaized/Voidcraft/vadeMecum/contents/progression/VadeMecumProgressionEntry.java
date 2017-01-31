package Tamaized.Voidcraft.vadeMecum.contents.progression;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.capabilities.vadeMecumItem.IVadeMecumItemCapability;
import Tamaized.Voidcraft.handlers.VadeMecumPacketHandler;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class VadeMecumProgressionEntry extends VadeMecumEntry {

	public static enum Entry {
		RitualBlocks, RitualList, Tome
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	private final ItemStack activeVade;

	public VadeMecumProgressionEntry() {
		super("progressionMainEntry", "Void Vade Mecum - Progression", null, null);
		activeVade = new ItemStack(VoidCraft.items.vadeMecum);
		activeVade.getCapability(CapabilityList.VADEMECUMITEM, null).setBookState(true);
	}

	@Override
	public void init(VadeMecumGUI gui) {
		clearButtons();
		addButton(gui, getEntryID(Entry.RitualBlocks), "Ritual Blocks", new ItemStack(Item.getItemFromBlock(VoidCraft.blocks.ritualBlock)));
		if (gui.getPlayerStats().getObtainedCategories().contains(IVadeMecumCapability.Category.INTRO)) {
			addButton(gui, getEntryID(Entry.RitualList), "Rituals", new ItemStack(VoidCraft.blocks.ritualBlock));
			if (gui.getPlayerStats().getObtainedCategories().contains(IVadeMecumCapability.Category.TOME)) {
				addButton(gui, getEntryID(Entry.Tome), "Words of Power", activeVade);
			}
		}
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case RitualBlocks:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Progression.RITUALBLOCKS);
				break;
			case RitualList:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Progression.RITUALLIST);
				break;
			case Tome:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Progression.TOME);
				break;
			default:
				gui.changeEntry(ClientProxy.vadeMecumEntryList);
				break;
		}
		// gui.sendPacketUpdates(VadeMecumPacketHandler.RequestType.ACTIVE_CURRENT_SET, IVadeMecumCapability.getActivePowerID(IVadeMecumCapability.ActivePower.TEST));
		// gui.sendPacketUpdates(VadeMecumPacketHandler.RequestType.ACTIVE_CURRENT_SET, -1);
	}
}
