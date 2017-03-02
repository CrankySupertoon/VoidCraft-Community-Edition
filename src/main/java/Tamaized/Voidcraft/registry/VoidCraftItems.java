package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import Tamaized.TamModized.blocks.TamBlockFarmland;
import Tamaized.TamModized.items.TamItem;
import Tamaized.TamModized.items.TamItemSeed;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityFakeBedrockFarmland;
import Tamaized.Voidcraft.events.DamageEvent;
import Tamaized.Voidcraft.items.ChainedSkull;
import Tamaized.Voidcraft.items.Debugger;
import Tamaized.Voidcraft.items.EmptyObsidianFlask;
import Tamaized.Voidcraft.items.EtherealFruit;
import Tamaized.Voidcraft.items.ObsidianFlask;
import Tamaized.Voidcraft.items.RealityTeleporter;
import Tamaized.Voidcraft.items.VadeMecum;
import Tamaized.Voidcraft.items.VoidCrystalShield;
import Tamaized.Voidcraft.items.VoidStar;
import Tamaized.Voidcraft.items.VoidicDrill;
import Tamaized.Voidcraft.items.VoidicEssence;
import Tamaized.Voidcraft.items.VoidicSuppressor;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class VoidCraftItems implements ITamRegistry {

	public static ArrayList<ItemRecord> voidDiscs;
	private ArrayList<ITamModel> modelList;

	public static VadeMecum vadeMecum;

	public static TamItem ectoplasm;
	public static TamItem voidcrystal;
	public static EmptyObsidianFlask emptyObsidianFlask;
	public static ObsidianFlask obsidianFlask;
	public static TamItem voidChain;
	public static TamItem MoltenvoidChain;
	public static TamItem MoltenvoidChainPart;
	public static TamItem burnBone;
	public static VoidStar voidStar;
	public static ChainedSkull ChainedSkull;
	public static TamItem voidCloth;
	public static TamItem voidCrystalBucket;
	public static VoidicEssence voidicEssence;
	public static TamItem voidicPhlogiston;
	public static TamItem voidicDragonScale;
	public static TamItem astralEssence;
	public static TamItem quoriFragment;

	public static TamItem voidicSteel;

	public static TamItem quartzDust;
	public static TamItem coalDust;
	public static TamItem ironDust;
	public static TamItem goldDust;
	public static TamItem copperDust;
	public static TamItem tinDust;
	public static TamItem leadDust;
	public static TamItem lapisDust;
	public static TamItem emeraldDust;
	public static TamItem diamondDust;

	public static Debugger debugger;
	// public static HookShot hookShot;

	public static VoidicSuppressor voidicSuppressor;
	public static VoidicDrill voidicDrill;
	public static RealityTeleporter realityTeleporter;

	public static VoidCrystalShield voidCrystalShield;

	public static TamItemSeed etherealSeed;
	public static EtherealFruit etherealFruit;
	public static EtherealFruit etherealFruit_redstone;
	public static EtherealFruit etherealFruit_lapis;
	public static EtherealFruit etherealFruit_gold;
	public static EtherealFruit etherealFruit_emerald;
	public static EtherealFruit etherealFruit_diamond;

	// public static VoidRecord record_noStrings;
	// public static VoidRecord record_bleedingThrough;
	// public static VoidRecord record_stringsAttached;
	// public static VoidRecord record_running;

	@Override
	public void preInit() {
		modelList = new ArrayList<ITamModel>();

		vadeMecum = new VadeMecum(VoidCraft.tabs.tabVoid, "vademecum", 1); // Don't add this to the model list as it uses an ItemMeshDefinition

		modelList.add(emptyObsidianFlask = new EmptyObsidianFlask(VoidCraft.tabs.tabVoid, "emptyobsidianflask", 16));
		modelList.add(obsidianFlask = new ObsidianFlask(VoidCraft.tabs.tabVoid, "obsidianflask", 16));
		modelList.add(ectoplasm = new TamItem(VoidCraft.tabs.tabVoid, "ectoplasm", 64));
		modelList.add(voidcrystal = new TamItem(VoidCraft.tabs.tabVoid, "voidcrystal", 64));
		modelList.add(voidChain = new TamItem(VoidCraft.tabs.tabVoid, "voidchain", 64));
		modelList.add(MoltenvoidChain = new TamItem(VoidCraft.tabs.tabVoid, "moltenvoidchain", 64));
		modelList.add(MoltenvoidChainPart = new TamItem(VoidCraft.tabs.tabVoid, "moltenvoidchainpart", 64));
		modelList.add(burnBone = new TamItem(VoidCraft.tabs.tabVoid, "burnbone", 64));
		modelList.add(voidStar = new VoidStar(VoidCraft.tabs.tabVoid, "voidstar", 1));
		modelList.add(ChainedSkull = new ChainedSkull(VoidCraft.tabs.tabVoid, "chainedskull", 1));
		modelList.add(voidCloth = new TamItem(VoidCraft.tabs.tabVoid, "voidcloth", 64));
		modelList.add(voidCrystalBucket = new TamItem(VoidCraft.tabs.tabVoid, "voidcrystalbucket", 1));
		modelList.add(voidicEssence = new VoidicEssence(VoidCraft.tabs.tabVoid, "voidicessence", 1));
		modelList.add(voidicPhlogiston = new TamItem(VoidCraft.tabs.tabVoid, "voidicphlogiston", 64));
		modelList.add(voidicDragonScale = new TamItem(VoidCraft.tabs.tabVoid, "voidicdragonscale", 64));
		modelList.add(astralEssence = new TamItem(VoidCraft.tabs.tabVoid, "astralessence", 64));
		modelList.add(quoriFragment = new TamItem(VoidCraft.tabs.tabVoid, "quorifragment", 64));
		
		modelList.add(voidicSteel = new TamItem(VoidCraft.tabs.tabVoid, "voidicsteel", 64));

		// dust
		modelList.add(quartzDust = new TamItem(VoidCraft.tabs.tabVoid, "quartzdust", 64));
		modelList.add(coalDust = new TamItem(VoidCraft.tabs.tabVoid, "coaldust", 64));
		modelList.add(ironDust = new TamItem(VoidCraft.tabs.tabVoid, "irondust", 64));
		modelList.add(goldDust = new TamItem(VoidCraft.tabs.tabVoid, "golddust", 64));
		modelList.add(copperDust = new TamItem(VoidCraft.tabs.tabVoid, "copperdust", 64));
		modelList.add(tinDust = new TamItem(VoidCraft.tabs.tabVoid, "tindust", 64));
		modelList.add(leadDust = new TamItem(VoidCraft.tabs.tabVoid, "leaddust", 64));
		modelList.add(lapisDust = new TamItem(VoidCraft.tabs.tabVoid, "lapisdust", 64));
		modelList.add(emeraldDust = new TamItem(VoidCraft.tabs.tabVoid, "emeralddust", 64));
		modelList.add(diamondDust = new TamItem(VoidCraft.tabs.tabVoid, "diamonddust", 64));

		modelList.add(debugger = new Debugger(VoidCraft.tabs.tabVoid, "debugger", 1));
		// modelList.add(hookShot = new HookShot(voidCraft.tabs.tabVoid, "hookShot", 1));

		modelList.add(voidicSuppressor = new VoidicSuppressor(VoidCraft.tabs.tabVoid, "voidicsuppressor", 1));
		modelList.add(voidicDrill = new VoidicDrill(VoidCraft.tabs.tabVoid, "voidicdrill", 1));
		modelList.add(realityTeleporter = new RealityTeleporter(VoidCraft.tabs.tabVoid, "realityteleporter", 1));

		modelList.add(voidCrystalShield = new VoidCrystalShield(VoidCraft.tabs.tabVoid, "voidcrystalshield"));
		DamageEvent.shieldRegistry.add(voidCrystalShield);

		ArrayList<TamBlockFarmland> soilList = new ArrayList<TamBlockFarmland>();
		soilList.add(VoidCraft.blocks.blockFakeBedrockFarmland);
		modelList.add(etherealSeed = new TamItemSeed(VoidCraft.tabs.tabVoid, "etherealseed", 64, VoidCraft.blocks.etherealPlant, soilList));
		modelList.add(etherealFruit = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.NORMAL, VoidCraft.tabs.tabVoid, "etherealfruit", 64, 2, false));
		modelList.add(etherealFruit_redstone = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.REDSTONE, VoidCraft.tabs.tabVoid, "etherealfruit_redstone", 64, 2, false));
		modelList.add(etherealFruit_lapis = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.LAPIS, VoidCraft.tabs.tabVoid, "etherealfruit_lapis", 64, 2, false));
		modelList.add(etherealFruit_gold = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.GOLD, VoidCraft.tabs.tabVoid, "etherealfruit_gold", 64, 2, false));
		modelList.add(etherealFruit_emerald = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.EMERALD, VoidCraft.tabs.tabVoid, "etherealfruit_emerald", 64, 2, false));
		modelList.add(etherealFruit_diamond = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.DIAMOND, VoidCraft.tabs.tabVoid, "etherealfruit_diamond", 64, 2, false));

		// modelList.add(record_noStrings = new VoidRecord("Approaching Nirvana - No Strings Attached", VoidSoundEvents.MusicDiscSoundEvents.No_Strings_Attached, "voidDisc1"));
		// modelList.add(record_bleedingThrough = new VoidRecord("Haven - Bleeding Through", VoidSoundEvents.MusicDiscSoundEvents.Haven_Bleeding_Through, "voidDisc2"));
		// modelList.add(record_stringsAttached = new VoidRecord("Approaching Nirvana - Strings Attached", VoidSoundEvents.MusicDiscSoundEvents.Strings_Attached, "voidDisc3"));
		// modelList.add(record_running = new VoidRecord("Approaching Nirvana - Running", VoidSoundEvents.MusicDiscSoundEvents.Running, "voidDisc4"));

		voidDiscs = new ArrayList<ItemRecord>();
		// voidDiscs.add(record_noStrings);
		// voidDiscs.add(record_bleedingThrough);
		// voidDiscs.add(record_stringsAttached);
		// voidDiscs.add(record_running);
	}

	@Override
	public void init() {

		OreDictionary.registerOre("ingotSteel", voidicSteel);
		
		OreDictionary.registerOre("dustQuartz", quartzDust);
		OreDictionary.registerOre("dustCoal", coalDust);
		OreDictionary.registerOre("dustIron", ironDust);
		OreDictionary.registerOre("dustGold", goldDust);
		OreDictionary.registerOre("dustCopper", copperDust);
		OreDictionary.registerOre("dustTin", tinDust);
		OreDictionary.registerOre("dustLead", leadDust);
		OreDictionary.registerOre("dustLapis", lapisDust);
		OreDictionary.registerOre("dustEmerald", emeraldDust);
		OreDictionary.registerOre("dustDiamond", diamondDust);

		GameRegistry.addShapelessRecipe(new ItemStack(voidcrystal, 9), VoidCraft.blocks.blockVoidcrystal);
		GameRegistry.addShapelessRecipe(new ItemStack(voidCrystalBucket), voidcrystal, Items.BUCKET);
		GameRegistry.addRecipe(new ItemStack(emptyObsidianFlask, 4), "OGO", " O ", 'O', Blocks.OBSIDIAN, 'G', Blocks.GLASS);
		GameRegistry.addShapelessRecipe(new ItemStack(voidicSuppressor), voidcrystal, Items.COMPASS, Items.REDSTONE, voidCloth);
		GameRegistry.addRecipe(new ItemStack(voidicDrill), "BHB", "CZC", "ESE", 'B', voidicSteel, 'H', VoidCraft.blocks.realityHole, 'C', voidCloth, 'Z', VoidCraft.blocks.voidicCharger, 'E', ectoplasm, 'S', voidStar);
		GameRegistry.addRecipe(new ItemStack(MoltenvoidChain), "XYX", "YXY", "XYX", 'Y', MoltenvoidChainPart, 'X', burnBone);
		GameRegistry.addRecipe(new ItemStack(ChainedSkull), "XYX", "YZY", "XYX", 'X', MoltenvoidChain, 'Y', burnBone, 'Z', new ItemStack(Items.SKULL, 1, 1));
		GameRegistry.addRecipe(new ItemStack(Items.SKULL, 1, 1), "XX", "XX", 'X', burnBone);
		GameRegistry.addRecipe(new ItemStack(realityTeleporter), "BEZ", "CHC", "BCB", 'B', voidicSteel, 'E', emeraldDust, 'Z', VoidCraft.blocks.voidicCharger, 'C', MoltenvoidChain, 'H', VoidCraft.blocks.realityHole);

		GameRegistry.addRecipe(new ItemStack(voidCrystalShield), "CCC", "CBC", " C ", 'C', voidcrystal, 'B', voidicSteel);

		GameRegistry.addSmelting(VoidCraft.blocks.oreVoidcrystal, new ItemStack(voidcrystal), 0.1F);
		GameRegistry.addSmelting(voidChain, new ItemStack(MoltenvoidChainPart), 0.1F);
		GameRegistry.addSmelting(voidCrystalBucket, VoidCraft.fluids.voidBucket.getBucket(), 0.1F);
		// dust
		GameRegistry.addSmelting(ironDust, new ItemStack(Items.IRON_INGOT), 0);
		GameRegistry.addSmelting(goldDust, new ItemStack(Items.GOLD_INGOT), 0);
		GameRegistry.addSmelting(diamondDust, new ItemStack(Items.DIAMOND), 0);
		this.addPreSmelting(copperDust, "ingotCopper");
		this.addPreSmelting(tinDust, "ingotTin");
		this.addPreSmelting(leadDust, "ingotLead");

		// Discs
		// GameRegistry.addRecipe(new ItemStack(voidDiscs.get(0)), "XZZ", "ZYZ", "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		// GameRegistry.addRecipe(new ItemStack(voidDiscs.get(1)), "ZXZ", "ZYZ", "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		// GameRegistry.addRecipe(new ItemStack(voidDiscs.get(2)), "ZZX", "ZYZ", "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		// GameRegistry.addRecipe(new ItemStack(voidDiscs.get(3)), "ZZZ", "XYZ", "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		// GameRegistry.addRecipe(new ItemStack(voidDiscs.get(2)), "ZZZ", "XYZ",
		// "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		// GameRegistry.addRecipe(new ItemStack(voidDiscs.get(3)), "ZZZ", "ZYX",
		// "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		// GameRegistry.addRecipe(new ItemStack(voidDiscs.get(4)), "ZZZ", "ZYZ",
		// "XZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		// GameRegistry.addRecipe(new ItemStack(voidDiscs.get(5)), "ZZZ", "ZYZ",
		// "ZXZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
	}

	@Override
	public void postInit() {

	}

	private void addPreSmelting(Item i, String s) {
		for (ItemStack ore : OreDictionary.getOres(s)) {
			if (ore != null) {
				GameRegistry.addSmelting(i, ore, ore.getItemDamage());
			}
		}
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
	@SideOnly(Side.CLIENT)
	public void clientPreInit() {
		VadeMecumMeshDefinition.preRegister();
		ModelLoader.setCustomMeshDefinition(vadeMecum, new VadeMecumMeshDefinition());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void clientInit() {
		// VadeMecumMeshDefinition.register();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void clientPostInit() {
		// TODO Auto-generated method stub

	}

}
