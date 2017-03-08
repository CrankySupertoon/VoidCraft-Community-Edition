package Tamaized.Voidcraft.vadeMecum.contents.progression;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.capabilities.vadeMecumItem.IVadeMecumItemCapability;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.progression.VadeMecumPacketHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumProgressionEntry extends VadeMecumEntry {

	public static enum Entry {
		RitualBlocks, RitualList, Tome, Potions, Voice, VoidicControl, ImprovedCasting, Empowerment, Tolerance, TotalControl, Dreams
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	private final ItemStack activeVade;

	public VadeMecumProgressionEntry() {
		super("progressionMainEntry", "Void Vade Mecum", null, null);
		activeVade = new ItemStack(VoidCraft.items.vadeMecum);
		activeVade.getCapability(CapabilityList.VADEMECUMITEM, null).setBookState(true);
	}

	@Override
	public void init(VadeMecumGUI gui) {
		clearButtons();
		addButton(gui, getEntryID(Entry.RitualBlocks), "Ritual Blocks", new ItemStack(Item.getItemFromBlock(VoidCraft.blocks.ritualBlock)));
		if (gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.INTRO)) {
			addButton(gui, getEntryID(Entry.RitualList), "Rituals", new ItemStack(VoidCraft.blocks.ritualBlock));
			if (gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.TOME)) {
				addButton(gui, getEntryID(Entry.Tome), "Words of Power", activeVade);
			}
		}
		if (gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.Flame) ||

				gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.Freeze) ||

				gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.Shock) ||

				gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.AcidSpray) ||

				gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.Implosion)) {
			addButton(gui, getEntryID(Entry.Potions), "Throwable Potions", new ItemStack(VoidCraft.items.obsidianFlaskFire));
		}
		if (gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.Voice)) {
			if (!gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.VoidicControl)) {
				addButton(gui, getEntryID(Entry.VoidicControl), "Voidic Control", activeVade);
			}
			if (!gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.ImprovedCasting)) {
				addButton(gui, getEntryID(Entry.ImprovedCasting), "Improved Casting", activeVade);
			} else {
				if (gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.VoidicControl)) {
					if (!gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.Empowerment)) {
						addButton(gui, getEntryID(Entry.Empowerment), "Empowerment", activeVade);
					} else {
						if (!gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.Tolerance)) {
							addButton(gui, getEntryID(Entry.Tolerance), "Tolerance", activeVade);
						} else {
							if (!gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.TotalControl)) {
								addButton(gui, getEntryID(Entry.TotalControl), "Total Control", activeVade);
							} else {
								if (!gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.Dreams)) {
									addButton(gui, getEntryID(Entry.Dreams), "Dreams", activeVade);
								}
							}
						}
					}
				}
			}
		} else {
			if (gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.Flame)) {
				if (gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.Freeze)) {
					if (gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.AcidSpray)) {
						if (gui.getPlayerStats().hasCategory(IVadeMecumCapability.Category.Shock)) {
							addButton(gui, getEntryID(Entry.Voice), TextFormatting.OBFUSCATED + "" + TextFormatting.DARK_PURPLE + "The Voice", activeVade);
						}
					}
				}
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
			case Potions:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Progression.POTIONS);
				break;
			case Voice:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Progression.VOICE);
				break;
			case VoidicControl:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Progression.VOIDICCONTROL);
				break;
			case ImprovedCasting:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Progression.IMPROVEDCASTING);
				break;
			case Empowerment:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Progression.EMPOWERMENT);
				break;
			case Tolerance:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Progression.TOLERANCE);
				break;
			case TotalControl:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Progression.TOTALCONTROL);
				break;
			case Dreams:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Progression.DREAMS);
				break;
			default:
				gui.changeEntry(ClientProxy.vadeMecumEntryList);
				break;
		}
		// gui.sendPacketUpdates(VadeMecumPacketHandler.RequestType.ACTIVE_CURRENT_SET, IVadeMecumCapability.getActivePowerID(IVadeMecumCapability.ActivePower.TEST));
		// gui.sendPacketUpdates(VadeMecumPacketHandler.RequestType.ACTIVE_CURRENT_SET, -1);
	}
}
