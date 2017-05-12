package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import Tamaized.TamModized.blocks.TamBlock;
import Tamaized.TamModized.blocks.TamBlockFence;
import Tamaized.TamModized.blocks.TamBlockStairs;
import Tamaized.TamModized.blocks.slab.TamBlockSlabDouble;
import Tamaized.TamModized.blocks.slab.TamBlockSlabHalf;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.TamModized.registry.RegistryHelper;
import Tamaized.TamModized.registry.TamColorRegistry;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.blocks.AIBlock;
import Tamaized.Voidcraft.blocks.BlockDreamBed;
import Tamaized.Voidcraft.blocks.BlockEtherealPlant;
import Tamaized.Voidcraft.blocks.BlockFakeBedrock;
import Tamaized.Voidcraft.blocks.BlockFakeBedrockFarmland;
import Tamaized.Voidcraft.blocks.BlockNoBreak;
import Tamaized.Voidcraft.blocks.BlockPortalVoid;
import Tamaized.Voidcraft.blocks.BlockPortalXia;
import Tamaized.Voidcraft.blocks.BlockRealityHole;
import Tamaized.Voidcraft.blocks.BlockVoidcrystal;
import Tamaized.Voidcraft.blocks.FireVoid;
import Tamaized.Voidcraft.blocks.OreVoidcrystal;
import Tamaized.Voidcraft.blocks.StarForgeBlock;
import Tamaized.Voidcraft.blocks.spell.BlockSpellIceSpike;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityFakeBedrockFarmland;
import Tamaized.Voidcraft.machina.Heimdall;
import Tamaized.Voidcraft.machina.RealityStabilizer;
import Tamaized.Voidcraft.machina.RealityTeleporterBlock;
import Tamaized.Voidcraft.machina.VoidBlastFurnace;
import Tamaized.Voidcraft.machina.VoidBox;
import Tamaized.Voidcraft.machina.VoidInfuser;
import Tamaized.Voidcraft.machina.VoidMacerator;
import Tamaized.Voidcraft.machina.VoidicAlchemyTable;
import Tamaized.Voidcraft.machina.VoidicAnchor;
import Tamaized.Voidcraft.machina.VoidicPowerCable;
import Tamaized.Voidcraft.machina.VoidicPowerCharger;
import Tamaized.Voidcraft.machina.VoidicPowerGen;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VoidCraftBlocks implements ITamRegistry {

	private static ArrayList<ITamModel> modelList;

	public static BlockVoidcrystal blockVoidcrystal;
	public static OreVoidcrystal oreVoidcrystal;
	public static BlockFakeBedrock blockFakeBedrock;
	public static BlockFakeBedrockFarmland blockFakeBedrockFarmland;
	public static TamBlockFence blockFakeBedrockFence;
	public static TamBlockStairs blockFakeBedrockStairs;
	public static TamBlockSlabDouble blockFakeBedrockDoubleSlab;
	public static TamBlockSlabHalf blockFakeBedrockHalfSlab;
	public static BlockNoBreak blockNoBreak;
	public static TamBlock blockVoidbrick;
	public static TamBlockFence blockVoidfence;
	public static TamBlockStairs blockVoidstairs;
	public static TamBlockSlabDouble blockVoidBrickDoubleSlab;
	public static TamBlockSlabHalf blockVoidBrickHalfSlab;
	public static BlockPortalVoid blockPortalVoid;
	public static BlockPortalXia blockPortalXia;
	public static FireVoid fireVoid;
	public static BlockRealityHole realityHole;
	public static TamBlock ritualBlock;

	public static VoidBox voidBox;
	public static VoidMacerator voidMacerator;
	public static TamBlock voidInfuserInert;
	public static VoidInfuser voidInfuser;
	public static Heimdall Heimdall;
	public static VoidicPowerGen voidicGen;
	public static VoidicPowerCable voidicCable;
	public static VoidicPowerCharger voidicCharger;
	public static RealityStabilizer realityStabilizer;
	public static VoidicAlchemyTable voidicAlchemyTable;
	public static RealityTeleporterBlock realityTeleporterBlock;
	public static VoidBlastFurnace voidBlastFurnace;
	public static VoidicAnchor voidicAnchor;

	public static BlockEtherealPlant etherealPlant;

	public static BlockSpellIceSpike iceSpike;

	public static AIBlock AIBlock;

	public static TamBlock cosmicMaterial;
	public static TamBlock starforge;
	public static StarForgeBlock starforgeStation;

	public static BlockDreamBed dreamBed;

	@Override
	public void preInit() {
		modelList = new ArrayList<ITamModel>();

		modelList.add(blockVoidcrystal = new BlockVoidcrystal(VoidCraft.tabs.tabVoid, Material.GLASS, "blockvoidcrystal", 7.0F));
		modelList.add(oreVoidcrystal = new OreVoidcrystal(VoidCraft.tabs.tabVoid, Material.ROCK, "orevoidcrystal", 3.0F));
		modelList.add(blockFakeBedrock = new BlockFakeBedrock(VoidCraft.tabs.tabVoid, Blocks.BEDROCK.getMaterial(Blocks.BEDROCK.getDefaultState()), "blockfakebedrock", 30.0F));
		modelList.add(blockFakeBedrockFarmland = new BlockFakeBedrockFarmland(VoidCraft.tabs.tabVoid, Blocks.BEDROCK.getMaterial(Blocks.BEDROCK.getDefaultState()), "blockfakebedrockfarmland", 30.0F));
		modelList.add(blockFakeBedrockFence = new TamBlockFence(VoidCraft.tabs.tabVoid, Material.ROCK, MapColor.OBSIDIAN, "blockfakebedrockfence", SoundType.STONE));
		modelList.add(blockFakeBedrockStairs = new TamBlockStairs(VoidCraft.tabs.tabVoid, blockFakeBedrock.getDefaultState(), "blockfakebedrockstairs", SoundType.STONE));
		modelList.add(blockFakeBedrockDoubleSlab = new TamBlockSlabDouble(VoidCraft.tabs.tabVoid, Material.ROCK, "blockfakebedrockdoubleslab", Item.getItemFromBlock(blockFakeBedrockHalfSlab)));
		modelList.add(blockFakeBedrockHalfSlab = new TamBlockSlabHalf(VoidCraft.tabs.tabVoid, Material.ROCK, "blockfakebedrockhalfslab", Item.getItemFromBlock(blockFakeBedrockHalfSlab)));
		modelList.add(blockNoBreak = new BlockNoBreak(VoidCraft.tabs.tabVoid, Material.ROCK, "blocknobreak", -1F));
		modelList.add(blockVoidbrick = new TamBlock(VoidCraft.tabs.tabVoid, Material.ROCK, "blockvoidbrick", 30.0F, SoundType.STONE));
		modelList.add(blockVoidfence = new TamBlockFence(VoidCraft.tabs.tabVoid, Material.ROCK, MapColor.OBSIDIAN, "blockvoidfence", SoundType.STONE));
		modelList.add(blockVoidstairs = new TamBlockStairs(VoidCraft.tabs.tabVoid, blockVoidbrick.getDefaultState(), "blockvoidstairs", SoundType.STONE));
		modelList.add(blockVoidBrickDoubleSlab = new TamBlockSlabDouble(VoidCraft.tabs.tabVoid, Material.ROCK, "blockvoidbrickdoubleslab", Item.getItemFromBlock(blockVoidBrickHalfSlab)));
		modelList.add(blockVoidBrickHalfSlab = new TamBlockSlabHalf(VoidCraft.tabs.tabVoid, Material.ROCK, "blockvoidbrickhalfslab", Item.getItemFromBlock(blockVoidBrickHalfSlab)));
		modelList.add(AIBlock = new AIBlock("aiblock"));
		modelList.add(realityHole = new BlockRealityHole(VoidCraft.tabs.tabVoid, Material.CLOTH, "blockrealityhole", -1F));
		modelList.add(ritualBlock = new TamBlock(VoidCraft.tabs.tabVoid, Material.IRON, "ritualblock", 3.0F, SoundType.STONE));
		modelList.add(cosmicMaterial = new TamBlock(VoidCraft.tabs.tabVoid, Material.ROCK, "cosmicmaterial", 30.0F, SoundType.STONE));
		modelList.add(starforge = new TamBlock(VoidCraft.tabs.tabVoid, Material.IRON, "starforge", -1.0F, SoundType.METAL));
		modelList.add(starforgeStation = new StarForgeBlock(VoidCraft.tabs.tabVoid, Material.IRON, "starforgestation", -1.0F));

		modelList.add(blockPortalVoid = new BlockPortalVoid(VoidCraft.tabs.tabVoid, "blockportalvoid"));
		modelList.add(blockPortalXia = new BlockPortalXia(VoidCraft.tabs.tabVoid, "blockportalxia"));
		modelList.add(fireVoid = new FireVoid(VoidCraft.tabs.tabVoid, "firevoid"));

		modelList.add(voidMacerator = new VoidMacerator(VoidCraft.tabs.tabVoid, Material.ROCK, "voidmacerator", 3.5F));
		modelList.add(voidBlastFurnace = new VoidBlastFurnace(VoidCraft.tabs.tabVoid, Material.IRON, "voidblastfurnace", 7.0F));
		modelList.add(Heimdall = new Heimdall(VoidCraft.tabs.tabVoid, Material.IRON, "heimdall", 3.5F));
		modelList.add(voidBox = new VoidBox(VoidCraft.tabs.tabVoid, Material.IRON, "voidbox", 7.0F));
		modelList.add(voidInfuserInert = new TamBlock(VoidCraft.tabs.tabVoid, Material.IRON, "voidinfuserinert", 7.0F, SoundType.METAL));
		modelList.add(voidInfuser = new VoidInfuser(VoidCraft.tabs.tabVoid, Material.IRON, "voidinfuser", 7.0F));
		modelList.add(voidicGen = new VoidicPowerGen(VoidCraft.tabs.tabVoid, Material.IRON, "voidicgen", 3.5f));
		modelList.add(voidicCable = new VoidicPowerCable(VoidCraft.tabs.tabVoid, Material.CIRCUITS, "voidiccable", 1.5f));
		modelList.add(voidicCharger = new VoidicPowerCharger(VoidCraft.tabs.tabVoid, Material.IRON, "voidiccharger", 3.5f));
		modelList.add(realityStabilizer = new RealityStabilizer(VoidCraft.tabs.tabVoid, Material.IRON, "realitystabilizer", 3.5f));
		modelList.add(voidicAlchemyTable = new VoidicAlchemyTable(VoidCraft.tabs.tabVoid, Material.IRON, "voidicalchemytable", 3.5f));
		modelList.add(realityTeleporterBlock = new RealityTeleporterBlock(VoidCraft.tabs.tabVoid, Material.IRON, "realityteleporterblock", 3.5f));
		modelList.add(voidicAnchor = new VoidicAnchor(VoidCraft.tabs.tabVoid, Material.IRON, "voidicanchor", 3.5f));

		modelList.add(etherealPlant = new BlockEtherealPlant(VoidCraft.tabs.tabVoid, "etherealplant", 1.0f));

		modelList.add(iceSpike = new BlockSpellIceSpike(VoidCraft.tabs.tabVoid, Material.ICE, "blockspellicespike", -1F));

		modelList.add(dreamBed = new BlockDreamBed(VoidCraft.tabs.tabVoid, "dreambed"));

		// Slabs have to be registered outside of their class
		RegistryHelper.registerBlockSlab(blockVoidBrickHalfSlab, blockVoidBrickDoubleSlab);
		RegistryHelper.registerBlockSlab(blockFakeBedrockHalfSlab, blockFakeBedrockDoubleSlab);

	}

	@Override
	public void init() {
		GameRegistry.addRecipe(new ItemStack(blockVoidcrystal), "XXX", "XXX", "XXX", 'X', VoidCraft.items.voidcrystal);
		GameRegistry.addRecipe(new ItemStack(ritualBlock, 8), "XXX", "XYX", "XXX", 'X', Blocks.STONEBRICK, 'Y', VoidCraft.items.voidcrystal);
		GameRegistry.addRecipe(new ItemStack(voidInfuserInert), "XYX", "YZY", "XYX", 'X', blockVoidbrick, 'Y', VoidCraft.items.voidCloth, 'Z', Items.CAULDRON);
		GameRegistry.addShapelessRecipe(new ItemStack(voidInfuser), voidInfuserInert, VoidCraft.items.voidStar);
		GameRegistry.addRecipe(new ItemStack(voidBlastFurnace), "CBC", "BMB", "CBC", 'C', VoidCraft.items.voidCloth, 'B', blockVoidbrick, 'M', VoidCraft.items.MoltenvoidChainPart);
		GameRegistry.addRecipe(new ItemStack(voidBox), "XXX", "XYX", "XZX", 'X', VoidCraft.items.voidCloth, 'Y', Blocks.JUKEBOX, 'Z', VoidCraft.items.voidStar);
		GameRegistry.addRecipe(new ItemStack(blockVoidbrick), "XX", "XX", 'X', blockVoidcrystal);
		GameRegistry.addRecipe(new ItemStack(blockVoidstairs, 6), "X  ", "XX ", "XXX", 'X', blockVoidbrick);
		GameRegistry.addRecipe(new ItemStack(blockVoidstairs, 6), "  X", " XX", "XXX", 'X', blockVoidbrick);
		GameRegistry.addRecipe(new ItemStack(blockVoidBrickHalfSlab, 6), "XXX", 'X', blockVoidbrick);
		GameRegistry.addRecipe(new ItemStack(blockVoidfence, 6), "   ", "XXX", "XXX", 'X', blockVoidbrick);
		GameRegistry.addRecipe(new ItemStack(blockFakeBedrockStairs, 6), "X  ", "XX ", "XXX", 'X', blockFakeBedrock);
		GameRegistry.addRecipe(new ItemStack(blockFakeBedrockStairs, 6), "  X", " XX", "XXX", 'X', blockFakeBedrock);
		GameRegistry.addRecipe(new ItemStack(blockFakeBedrockHalfSlab, 6), "XXX", 'X', blockFakeBedrock);
		GameRegistry.addRecipe(new ItemStack(blockFakeBedrockFence, 6), "   ", "XXX", "XXX", 'X', blockFakeBedrock);
		GameRegistry.addRecipe(new ItemStack(voidicCable, 8), "XXX", "XYX", "XXX", 'Y', blockVoidcrystal, 'X', Items.REDSTONE);
		GameRegistry.addRecipe(new ItemStack(voidicGen), "XXX", "XYX", "XXX", 'Y', voidInfuser, 'X', Items.REDSTONE);
		GameRegistry.addRecipe(new ItemStack(voidicCharger), "DSD", "CZC", "DPD", 'D', VoidCraft.items.diamondDust, 'S', VoidCraft.items.voidStar, 'C', VoidCraft.items.voidCloth, 'Z', Items.END_CRYSTAL, 'P', voidicCable);
		GameRegistry.addRecipe(new ItemStack(voidicAnchor), "DSD", "CZC", "XPX", 'X', VoidCraft.items.voidicSteel, 'D', VoidCraft.items.diamondDust, 'S', VoidCraft.items.voidStar, 'C', VoidCraft.items.voidCloth, 'Z', Items.END_CRYSTAL, 'P', voidicAnchor);
		GameRegistry.addRecipe(new ItemStack(realityStabilizer), "IDI", "DSD", "ICI", 'I', VoidCraft.items.voidicSteel, 'C', voidicCharger, 'S', VoidCraft.items.voidStar, 'D', VoidCraft.items.diamondDust);
		GameRegistry.addRecipe(new ItemStack(voidicAlchemyTable), "BCB", "CSC", "BIB", 'B', blockVoidbrick, 'C', VoidCraft.items.voidicSteel, 'S', Items.BREWING_STAND, 'I', voidInfuserInert);
		GameRegistry.addRecipe(new ItemStack(realityTeleporterBlock), "BEB", "LHL", "BCB", 'B', VoidCraft.items.voidicSteel, 'E', VoidCraft.items.emeraldDust, 'L', VoidCraft.items.voidCloth, 'H', realityHole, 'C', voidicCharger);
	}

	@Override
	public void postInit() {

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

	}

	@Override
	public void clientInit() {

		final net.minecraft.client.renderer.color.IBlockColor fakeBedrockFarmlandColorHandler = (state, blockAccess, pos, tintIndex) -> {
			if (blockAccess != null && pos != null && blockAccess.getTileEntity(pos) != null) {
				return TileEntityFakeBedrockFarmland.getColor(((TileEntityFakeBedrockFarmland) blockAccess.getTileEntity(pos)).getAlteration());
			}
			return 0xFFFFFF;
		};

		final net.minecraft.client.renderer.color.IBlockColor etherealPlantColorHandler = (state, blockAccess, pos, tintIndex) -> {
			if (blockAccess != null && pos != null && blockAccess.getTileEntity(pos.down()) != null) {
				return TileEntityFakeBedrockFarmland.getColor(((TileEntityFakeBedrockFarmland) blockAccess.getTileEntity(pos.down())).getAlteration());
			}
			return 0xFFFFFF;
		};

		TamColorRegistry.registerBlockColors(blockFakeBedrockFarmland, fakeBedrockFarmlandColorHandler);
		TamColorRegistry.registerBlockColors(etherealPlant, etherealPlantColorHandler);
	}

	@Override
	public void clientPostInit() {

	}

}
