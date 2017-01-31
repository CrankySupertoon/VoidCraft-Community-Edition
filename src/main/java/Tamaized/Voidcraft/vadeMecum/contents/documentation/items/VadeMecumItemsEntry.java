package Tamaized.Voidcraft.vadeMecum.contents.documentation.items;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.chainedskull.VadeMecumPageListChainedSkull;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.charredbone.VadeMecumPageListCharredBone;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.dusts.VadeMecumPageListVoidDusts;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.ectoplasm.VadeMecumPageListEctoplasm;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.moltenvoidchain.VadeMecumPageListMoltenChain;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.moltenvoidchainpart.VadeMecumPageListMoltenChainPart;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.obsidianflask.VadeMecumPageListObsidianFlask;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.quoriFragment.VadeMecumPageListQuoriFragment;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidchain.VadeMecumPageListVoidChain;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidcloth.VadeMecumPageListVoidCloth;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidcrystal.VadeMecumPageListVoidCrystal;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidicDragonscale.VadeMecumPageListVoidicDragonscale;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidicPhlogiston.VadeMecumPageListVoidicPhlogiston;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidicessence.VadeMecumPageListVoidicEssence;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidicsupressor.VadeMecumPageListSuppressor;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidstar.VadeMecumPageListVoidStar;
import net.minecraft.item.ItemStack;

public class VadeMecumItemsEntry extends VadeMecumEntry {

	public static enum Entry {
		VoidCrystal, Ectoplasm, VoidChain, MoltenVoidChainPart, MoltenVoidChain, CharredBone,

		VoidStar, ObsidianFlask, VoidInfusedCloth, VoidDusts, ChainedSkull, VoidicSupressor, VoidicDrill,

		VoidicEssence, VoidicDragonscale, QuoriFragment, AstralEssence, VoidicPhlogiston

	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumEntry voidCrystal;
	public VadeMecumEntry ectoplasm;
	public VadeMecumEntry voidChain;
	public VadeMecumEntry moltenVoidChainPart;
	public VadeMecumEntry moltenVoidChain;
	public VadeMecumEntry charredBone;

	public VadeMecumEntry voidStar;
	public VadeMecumEntry obsidianFlask;
	public VadeMecumEntry voidInfusedCloth;
	public VadeMecumEntry voidDusts;
	public VadeMecumEntry chainedSkull;
	public VadeMecumEntry voidicSupressor;

	public VadeMecumEntry voidicEssence;
	public VadeMecumEntry voidicDragonscale;
	public VadeMecumEntry quoriFragment;
	public VadeMecumEntry astralEssence;
	public VadeMecumEntry voidicPhlogiston;

	public VadeMecumItemsEntry(VadeMecumEntry back) {
		super("docs_Items", "Items", back, null);
	}

	@Override
	public void initObjects() {
		voidCrystal = new VadeMecumEntry("docs_Items_voidCrystal", "", this, new VadeMecumPageListVoidCrystal());
		ectoplasm = new VadeMecumEntry("docs_Items_ectoplasm", "", this, new VadeMecumPageListEctoplasm());
		voidChain = new VadeMecumEntry("docs_Items_voidChain", "", this, new VadeMecumPageListVoidChain());
		moltenVoidChainPart = new VadeMecumEntry("docs_Items_moltenVoidChainPart", "", this, new VadeMecumPageListMoltenChainPart());
		moltenVoidChain = new VadeMecumEntry("docs_Items_moltenVoidChain", "", this, new VadeMecumPageListMoltenChain());
		charredBone = new VadeMecumEntry("docs_Items_charredBone", "", this, new VadeMecumPageListCharredBone());

		voidStar = new VadeMecumEntry("docs_Items_voidStar", "", this, new VadeMecumPageListVoidStar());
		obsidianFlask = new VadeMecumEntry("docs_Items_obsidianFlask", "", this, new VadeMecumPageListObsidianFlask());
		voidInfusedCloth = new VadeMecumEntry("docs_Items_voidInfusedCloth", "", this, new VadeMecumPageListVoidCloth());
		voidDusts = new VadeMecumEntry("docs_Items_voidDusts", "", this, new VadeMecumPageListVoidDusts());
		chainedSkull = new VadeMecumEntry("docs_Items_chainedSkull", "", this, new VadeMecumPageListChainedSkull());
		voidicSupressor = new VadeMecumEntry("docs_Items_voidicSupressor", "", this, new VadeMecumPageListSuppressor());

		voidicEssence = new VadeMecumEntry("docs_Items_voidicEssence", "", this, new VadeMecumPageListVoidicEssence());
		voidicDragonscale = new VadeMecumEntry("docs_Items_voidicDragonscale", "", this, new VadeMecumPageListVoidicDragonscale());
		quoriFragment = new VadeMecumEntry("docs_Items_quoriFragment", "", this, new VadeMecumPageListQuoriFragment());
		astralEssence = new VadeMecumEntry("docs_Items_astralEssence", "", this, new VadeMecumPageListVoidicPhlogiston());
		voidicPhlogiston = new VadeMecumEntry("docs_Items_voidicPhlogiston", "", this, new VadeMecumPageListVoidicPhlogiston());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();

		addButton(gui, getEntryID(Entry.VoidCrystal), new ItemStack(VoidCraft.items.voidcrystal).getDisplayName(), new ItemStack(VoidCraft.items.voidcrystal));
		addButton(gui, getEntryID(Entry.Ectoplasm), new ItemStack(VoidCraft.items.ectoplasm).getDisplayName(), new ItemStack(VoidCraft.items.ectoplasm));
		addButton(gui, getEntryID(Entry.VoidChain), new ItemStack(VoidCraft.items.voidChain).getDisplayName(), new ItemStack(VoidCraft.items.voidChain));
		addButton(gui, getEntryID(Entry.MoltenVoidChainPart), new ItemStack(VoidCraft.items.MoltenvoidChainPart).getDisplayName(), new ItemStack(VoidCraft.items.MoltenvoidChainPart));
		addButton(gui, getEntryID(Entry.MoltenVoidChain), new ItemStack(VoidCraft.items.MoltenvoidChain).getDisplayName(), new ItemStack(VoidCraft.items.MoltenvoidChain));
		addButton(gui, getEntryID(Entry.CharredBone), new ItemStack(VoidCraft.items.burnBone).getDisplayName(), new ItemStack(VoidCraft.items.burnBone));

		addButton(gui, getEntryID(Entry.VoidStar), new ItemStack(VoidCraft.items.voidStar).getDisplayName(), new ItemStack(VoidCraft.items.voidStar));
		addButton(gui, getEntryID(Entry.ObsidianFlask), VoidCraft.modid + ".VadeMecum.docs.title.obsidianFlask", new ItemStack(VoidCraft.items.obsidianFlask));
		addButton(gui, getEntryID(Entry.VoidInfusedCloth), new ItemStack(VoidCraft.items.voidCloth).getDisplayName(), new ItemStack(VoidCraft.items.voidCloth));
		addButton(gui, getEntryID(Entry.VoidDusts), VoidCraft.modid + ".VadeMecum.docs.title.dusts", new ItemStack(VoidCraft.items.diamondDust));
		addButton(gui, getEntryID(Entry.ChainedSkull), new ItemStack(VoidCraft.items.ChainedSkull).getDisplayName(), new ItemStack(VoidCraft.items.ChainedSkull));
		addButton(gui, getEntryID(Entry.VoidicSupressor), new ItemStack(VoidCraft.items.voidicSuppressor).getDisplayName(), new ItemStack(VoidCraft.items.voidicSuppressor));

		addButton(gui, getEntryID(Entry.VoidicEssence), new ItemStack(VoidCraft.items.voidicEssence).getDisplayName(), new ItemStack(VoidCraft.items.voidicEssence));
		addButton(gui, getEntryID(Entry.VoidicDragonscale), new ItemStack(VoidCraft.items.voidicDragonScale).getDisplayName(), new ItemStack(VoidCraft.items.voidicDragonScale));
		addButton(gui, getEntryID(Entry.QuoriFragment), new ItemStack(VoidCraft.items.quoriFragment).getDisplayName(), new ItemStack(VoidCraft.items.quoriFragment));
		addButton(gui, getEntryID(Entry.AstralEssence), new ItemStack(VoidCraft.items.astralEssence).getDisplayName(), new ItemStack(VoidCraft.items.astralEssence));
		addButton(gui, getEntryID(Entry.VoidicPhlogiston), new ItemStack(VoidCraft.items.voidicPhlogiston).getDisplayName(), new ItemStack(VoidCraft.items.voidicPhlogiston));

	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case VoidCrystal:
				gui.changeEntry(voidCrystal);
				break;
			case Ectoplasm:
				gui.changeEntry(ectoplasm);
				break;
			case VoidChain:
				gui.changeEntry(voidChain);
				break;
			case MoltenVoidChainPart:
				gui.changeEntry(moltenVoidChainPart);
				break;
			case MoltenVoidChain:
				gui.changeEntry(moltenVoidChain);
				break;
			case CharredBone:
				gui.changeEntry(charredBone);
				break;
			case VoidStar:
				gui.changeEntry(voidStar);
				break;
			case ObsidianFlask:
				gui.changeEntry(obsidianFlask);
				break;
			case VoidInfusedCloth:
				gui.changeEntry(voidInfusedCloth);
				break;
			case VoidDusts:
				gui.changeEntry(voidDusts);
				break;
			case ChainedSkull:
				gui.changeEntry(chainedSkull);
				break;
			case VoidicSupressor:
				gui.changeEntry(voidicSupressor);
				break;
			case VoidicEssence:
				gui.changeEntry(voidicEssence);
				break;
			case VoidicDragonscale:
				gui.changeEntry(voidicDragonscale);
				break;
			case QuoriFragment:
				gui.changeEntry(quoriFragment);
				break;
			case AstralEssence:
				gui.changeEntry(astralEssence);
				break;
			case VoidicPhlogiston:
				gui.changeEntry(voidicPhlogiston);
				break;
			default:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.MAIN);
				break;
		}
	}

}
