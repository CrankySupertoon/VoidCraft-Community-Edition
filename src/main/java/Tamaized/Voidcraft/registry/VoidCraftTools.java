package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.TamModized.tools.TamAxe;
import Tamaized.TamModized.tools.TamHoe;
import Tamaized.TamModized.tools.TamPickaxe;
import Tamaized.TamModized.tools.TamSpade;
import Tamaized.TamModized.tools.TamSword;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.starforge.tools.StarForgeAxe;
import Tamaized.Voidcraft.starforge.tools.StarForgePickaxe;
import Tamaized.Voidcraft.starforge.tools.StarForgeShovel;
import Tamaized.Voidcraft.starforge.tools.StarForgeSword;
import Tamaized.Voidcraft.tools.VoidHoe;
import Tamaized.Voidcraft.tools.arch.ArchSword;
import Tamaized.Voidcraft.tools.chain.ChainSword;
import Tamaized.Voidcraft.tools.demon.DemonSword;
import Tamaized.Voidcraft.tools.molten.MoltenSword;
import Tamaized.Voidcraft.tools.spectre.AngelicSword;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VoidCraftTools implements ITamRegistry {

	public static ArrayList<ITamModel> modelList;

	public static TamPickaxe voidPickaxe;
	public static TamSpade voidSpade;
	public static TamAxe voidAxe;
	public static TamSword voidSword;
	public static TamHoe voidHoe;
	public static AngelicSword angelicSword;
	public static ChainSword chainSword;
	public static MoltenSword moltenSword;
	public static ArchSword archSword;
	public static DemonSword demonSword;
	public static TamPickaxe spectrePickaxe;
	public static TamAxe spectreAxe;
	public static TamSpade spectreSpade;
	public static TamHoe spectreHoe;

	public static StarForgeSword starforgedSword;
	public static StarForgePickaxe starforgedPickaxe;
	public static StarForgeAxe starforgedAxe;
	public static StarForgeShovel starforgedSpade;

	@Override
	public void preInit() {
		modelList = new ArrayList<ITamModel>();

		// Swords: 3+dmg; Axe: dmg; Pick: 1+dmg; Spade: 1.5+dmg;
		// Axe: -3; Pick: -2.8; Spade: -3;
		// Tools
		// void
		modelList.add(voidPickaxe = new TamPickaxe(VoidCraft.tabs.tabVoid, VoidCraft.materials.voidTools, "voidpickaxe"));
		modelList.add(voidSpade = new TamSpade(VoidCraft.tabs.tabVoid, VoidCraft.materials.voidTools, "voidshovel"));
		modelList.add(voidAxe = new TamAxe(VoidCraft.tabs.tabVoid, VoidCraft.materials.voidTools, "voidaxe"));
		modelList.add(voidSword = new TamSword(VoidCraft.tabs.tabVoid, VoidCraft.materials.voidTools, "voidsword"));
		modelList.add(voidHoe = new VoidHoe(VoidCraft.tabs.tabVoid, VoidCraft.materials.voidTools, "voidhoe"));

		// spectre
		modelList.add(angelicSword = new AngelicSword(VoidCraft.tabs.tabVoid, VoidCraft.materials.spectreTools, "angelicsword"));
		modelList.add(spectrePickaxe = new TamPickaxe(VoidCraft.tabs.tabVoid, VoidCraft.materials.spectreTools, "spectrepickaxe"));
		modelList.add(spectreAxe = new TamAxe(VoidCraft.tabs.tabVoid, VoidCraft.materials.spectreTools, "spectreaxe"));
		modelList.add(spectreSpade = new TamSpade(VoidCraft.tabs.tabVoid, VoidCraft.materials.spectreTools, "spectrespade"));
		modelList.add(spectreHoe = new VoidHoe(VoidCraft.tabs.tabVoid, VoidCraft.materials.spectreTools, "spectrehoe"));
		// chain
		modelList.add(chainSword = new ChainSword(VoidCraft.tabs.tabVoid, VoidCraft.materials.chainTools, "chainsword"));
		// molten
		modelList.add(moltenSword = new MoltenSword(VoidCraft.tabs.tabVoid, VoidCraft.materials.MoltenTools, "moltensword"));
		// arch
		modelList.add(archSword = new ArchSword(VoidCraft.tabs.tabVoid, VoidCraft.materials.ArchTools, "archsword"));
		// demon
		modelList.add(demonSword = new DemonSword(VoidCraft.tabs.tabVoid, VoidCraft.materials.DemonTools, "demonsword"));

		// Cosmic
		modelList.add(starforgedSword = new StarForgeSword(VoidCraft.tabs.tabVoid, VoidCraft.materials.CosmicTools, "starforgedsword"));
		modelList.add(starforgedPickaxe = new StarForgePickaxe(VoidCraft.tabs.tabVoid, VoidCraft.materials.CosmicTools, "starforgedpickaxe"));
		modelList.add(starforgedAxe = new StarForgeAxe(VoidCraft.tabs.tabVoid, VoidCraft.materials.CosmicTools, "starforgedaxe"));
		modelList.add(starforgedSpade = new StarForgeShovel(VoidCraft.tabs.tabVoid, VoidCraft.materials.CosmicTools, "starforgedshovel"));
	}

	@Override
	public void init() {
		GameRegistry.addRecipe(new ItemStack(voidPickaxe, 1), "XXX", " O ", " D ", 'X', VoidCraft.items.voidcrystal, 'O', Blocks.OBSIDIAN, 'D', Items.DIAMOND);
		GameRegistry.addRecipe(new ItemStack(voidAxe, 1), " XX", " OX", " D ", 'X', VoidCraft.items.voidcrystal, 'O', Blocks.OBSIDIAN, 'D', Items.DIAMOND);
		GameRegistry.addRecipe(new ItemStack(voidSpade, 1), " X ", " O ", " D ", 'X', VoidCraft.items.voidcrystal, 'O', Blocks.OBSIDIAN, 'D', Items.DIAMOND);
		GameRegistry.addRecipe(new ItemStack(voidHoe, 1), " XX", " O ", " D ", 'X', VoidCraft.items.voidcrystal, 'O', Blocks.OBSIDIAN, 'D', Items.DIAMOND);
		GameRegistry.addRecipe(new ItemStack(voidSword, 1), " X ", "OXO", " D ", 'X', VoidCraft.items.voidcrystal, 'O', Blocks.OBSIDIAN, 'D', Items.DIAMOND);
		// +spectre
		GameRegistry.addRecipe(new ItemStack(angelicSword, 1), " X ", "XSX", " I ", 'S', new ItemStack(voidSword, 1, VoidCraft.WILDCARD_VALUE), 'X', VoidCraft.items.ectoplasm, 'I', VoidCraft.items.voidicSteel);
		GameRegistry.addRecipe(new ItemStack(spectrePickaxe, 1), " X ", "XSX", " I ", 'S', new ItemStack(voidPickaxe, 1, VoidCraft.WILDCARD_VALUE), 'X', VoidCraft.items.ectoplasm, 'I', VoidCraft.items.voidicSteel);
		GameRegistry.addRecipe(new ItemStack(spectreAxe, 1), " X ", "XSX", " I ", 'S', new ItemStack(voidAxe, 1, VoidCraft.WILDCARD_VALUE), 'X', VoidCraft.items.ectoplasm, 'I', VoidCraft.items.voidicSteel);
		GameRegistry.addRecipe(new ItemStack(spectreSpade, 1), " X ", "XSX", " I ", 'S', new ItemStack(voidSpade, 1, VoidCraft.WILDCARD_VALUE), 'X', VoidCraft.items.ectoplasm, 'I', VoidCraft.items.voidicSteel);
		GameRegistry.addRecipe(new ItemStack(spectreHoe, 1), " X ", "XSX", " I ", 'S', new ItemStack(voidHoe, 1, VoidCraft.WILDCARD_VALUE), 'X', VoidCraft.items.ectoplasm, 'I', VoidCraft.items.voidicSteel);
		// +molten and beyond
		GameRegistry.addRecipe(new ItemStack(moltenSword), " X ", "XSX", " I ", 'X', VoidCraft.items.MoltenvoidChain, 'S', new ItemStack(chainSword, 1, VoidCraft.WILDCARD_VALUE), 'I', VoidCraft.items.voidicSteel);
		GameRegistry.addRecipe(new ItemStack(chainSword), " X ", "XSX", " I ", 'X', VoidCraft.items.voidChain, 'S', new ItemStack(voidSword, 1, VoidCraft.WILDCARD_VALUE), 'I', VoidCraft.items.voidicSteel);
		GameRegistry.addRecipe(new ItemStack(archSword), "SSS", "XYZ", "SSS", 'S', VoidCraft.items.MoltenvoidChain, 'X', new ItemStack(chainSword, 1, VoidCraft.WILDCARD_VALUE), 'Y', new ItemStack(angelicSword, 1, VoidCraft.WILDCARD_VALUE), 'Z', new ItemStack(moltenSword, 1, VoidCraft.WILDCARD_VALUE));
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<ITamModel> getModelList() {
		return modelList;
	}

	@Override
	public String getModID() {
		return VoidCraft.modid;
	}

	@Override
	public void clientPreInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clientInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clientPostInit() {
		// TODO Auto-generated method stub

	}

}
