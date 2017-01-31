package Tamaized.Voidcraft.vadeMecum.contents.documentation;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class VadeMecumDocumentationEntry extends VadeMecumEntry {

	public VadeMecumDocumentationEntry() {
		super("docsMainEntry", VoidCraft.modid + ".VadeMecum.title.docs", null, null);
	}

	@Override
	public void init(VadeMecumGUI gui) {
		clearButtons();
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.BLOCKS), VoidCraft.modid + ".VadeMecum.docs.title.blocks", new ItemStack(Item.getItemFromBlock(VoidCraft.blocks.blockVoidcrystal)));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.MACHINES), VoidCraft.modid + ".VadeMecum.docs.title.machines", new ItemStack(Item.getItemFromBlock(VoidCraft.blocks.voidMacerator)));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.ITEMS), VoidCraft.modid + ".VadeMecum.docs.title.items", new ItemStack(VoidCraft.items.voidcrystal));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.TOOLS), VoidCraft.modid + ".VadeMecum.docs.title.tools", new ItemStack(VoidCraft.tools.voidPickaxe));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.WEAPONS), VoidCraft.modid + ".VadeMecum.docs.title.weps", new ItemStack(VoidCraft.tools.demonSword));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.ARMOR), VoidCraft.modid + ".VadeMecum.docs.title.armor", new ItemStack(VoidCraft.armors.voidChest));

		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.ETHEREALFRUIT), VoidCraft.modid + ".VadeMecum.docs.title.fruit", new ItemStack(VoidCraft.items.etherealFruit));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.MOBS), VoidCraft.modid + ".VadeMecum.docs.title.mobs", new ItemStack(Items.SKULL, 1, 1));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.BOSSES), VoidCraft.modid + ".VadeMecum.docs.title.bosses", new ItemStack(VoidCraft.items.ChainedSkull));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.VOID), VoidCraft.modid + ".VadeMecum.docs.title.theVoid", new ItemStack(Item.getItemFromBlock(VoidCraft.blocks.blockPortalVoid)));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.VOIDICINFUSION), VoidCraft.modid + ".VadeMecum.docs.title.voidicInfusion", new ItemStack(VoidCraft.items.voidicSuppressor));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.XIA), VoidCraft.modid + ".VadeMecum.docs.title.xia", new ItemStack(VoidCraft.blocks.blockPortalXia));

		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.STARFORGE), VoidCraft.modid + ".VadeMecum.docs.title.starforge", new ItemStack(VoidCraft.blocks.starforgeStation));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (VadeMecumDocumentationEntryList.getEntryFromID(id)) {
			case BLOCKS:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.BLOCKS);
				break;
			case MACHINES:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.MACHINES);
				break;
			case ITEMS:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.ITEMS);
				break;
			case TOOLS:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.TOOLS);
				break;
			case WEAPONS:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.WEAPONS);
				break;
			case ARMOR:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.ARMOR);
				break;
			case ETHEREALFRUIT:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.ETHEREALFRUIT);
				break;
			case MOBS:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.MOBS);
				break;
			case BOSSES:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.BOSSES);
				break;
			case VOID:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.VOID);
				break;
			case VOIDICINFUSION:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.VOIDICINFUSION);
				break;
			case XIA:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.XIA);
				break;
			case STARFORGE:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.STARFORGE);
				break;
			default:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.MAIN);
				break;
		}
	}

}
