package Tamaized.Voidcraft;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import Tamaized.TamModized.TamModBase;
import Tamaized.TamModized.TamModized;
import Tamaized.TamModized.proxy.AbstractProxy;
import Tamaized.TamModized.registry.PortalHandlerRegistry;
import Tamaized.Voidcraft.Addons.thaumcraft.VoidCraftThaum;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.blocks.TileEntityNoBreak;
import Tamaized.Voidcraft.blocks.spell.tileentity.TileEntitySpellIceSpike;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityFakeBedrockFarmland;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityStarForge;
import Tamaized.Voidcraft.capabilities.elytraFlying.ElytraFlyingCapabilityHandler;
import Tamaized.Voidcraft.capabilities.elytraFlying.ElytraFlyingCapabilityStorage;
import Tamaized.Voidcraft.capabilities.elytraFlying.IElytraFlyingCapability;
import Tamaized.Voidcraft.capabilities.starforge.IStarForgeCapability;
import Tamaized.Voidcraft.capabilities.starforge.StarForgeCapabilityHandler;
import Tamaized.Voidcraft.capabilities.starforge.StarForgeCapabilityStorage;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.capabilities.vadeMecum.VadeMecumCapabilityHandler;
import Tamaized.Voidcraft.capabilities.vadeMecum.VadeMecumCapabilityStorage;
import Tamaized.Voidcraft.capabilities.vadeMecumItem.IVadeMecumItemCapability;
import Tamaized.Voidcraft.capabilities.vadeMecumItem.VadeMecumItemCapabilityHandler;
import Tamaized.Voidcraft.capabilities.vadeMecumItem.VadeMecumItemCapabilityStorage;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import Tamaized.Voidcraft.capabilities.voidicInfusion.VoidicInfusionCapabilityHandler;
import Tamaized.Voidcraft.capabilities.voidicInfusion.VoidicInfusionCapabilityStorage;
import Tamaized.Voidcraft.capabilities.voidicPower.IVoidicPowerCapability;
import Tamaized.Voidcraft.capabilities.voidicPower.VoidicPowerCapabilityHandler;
import Tamaized.Voidcraft.capabilities.voidicPower.VoidicPowerCapabilityStorage;
import Tamaized.Voidcraft.entity.boss.EntityBossCorruptedPawn;
import Tamaized.Voidcraft.entity.boss.dragon.EntityDragonOld;
import Tamaized.Voidcraft.entity.boss.dragon.sub.voidic.EntityVoidicDragon;
import Tamaized.Voidcraft.entity.boss.herobrine.EntityBossHerobrine;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineFireball;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineShadow;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineTNTPrimed;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineWitherSkull;
import Tamaized.Voidcraft.entity.boss.lob.EntityLordOfBlades;
import Tamaized.Voidcraft.entity.boss.twins.EntityBossDol;
import Tamaized.Voidcraft.entity.boss.twins.EntityBossZol;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia2;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.EntityDragonXia;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.EntityWitherbrine;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.EntityZolXia;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.render.EntityDolXia;
import Tamaized.Voidcraft.entity.companion.EntityCompanionFireElemental;
import Tamaized.Voidcraft.entity.ghost.EntityGhostBiped;
import Tamaized.Voidcraft.entity.ghost.EntityGhostPlayer;
import Tamaized.Voidcraft.entity.mob.EntityMobEtherealGuardian;
import Tamaized.Voidcraft.entity.mob.EntityMobLich;
import Tamaized.Voidcraft.entity.mob.EntityMobSpectreChain;
import Tamaized.Voidcraft.entity.mob.EntityMobVoidWrath;
import Tamaized.Voidcraft.entity.mob.EntityMobWraith;
import Tamaized.Voidcraft.entity.mob.dalquor.EntityHashalaq;
import Tamaized.Voidcraft.entity.mob.lich.EntityLichInferno;
import Tamaized.Voidcraft.entity.nonliving.AcidBall;
import Tamaized.Voidcraft.entity.nonliving.EntityCasterLightningBolt;
import Tamaized.Voidcraft.entity.nonliving.EntityObsidianFlask;
import Tamaized.Voidcraft.entity.nonliving.EntitySpellImplosion;
import Tamaized.Voidcraft.entity.nonliving.EntitySpellRune;
import Tamaized.Voidcraft.entity.nonliving.ProjectileDisintegration;
import Tamaized.Voidcraft.entity.nonliving.VoidChain;
import Tamaized.Voidcraft.events.BlockBreakPlaceEvent;
import Tamaized.Voidcraft.events.CapabilitySyncEvent;
import Tamaized.Voidcraft.events.DamageEvent;
import Tamaized.Voidcraft.events.DeathEvent;
import Tamaized.Voidcraft.events.ItemEntityEvent;
import Tamaized.Voidcraft.events.LitStrikeEvent;
import Tamaized.Voidcraft.events.PickUpEvent;
import Tamaized.Voidcraft.events.PlayerRightClickEvent;
import Tamaized.Voidcraft.events.SpawnEvent;
import Tamaized.Voidcraft.events.VoidTickEvent;
import Tamaized.Voidcraft.handlers.ConfigHandler;
import Tamaized.Voidcraft.handlers.ContributorHandler;
import Tamaized.Voidcraft.handlers.CraftingHandler;
import Tamaized.Voidcraft.handlers.VoidicInfusionHandler;
import Tamaized.Voidcraft.handlers.XiaFlightHandler;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;
import Tamaized.Voidcraft.machina.tileentity.TileEntityRealityStabilizer;
import Tamaized.Voidcraft.machina.tileentity.TileEntityRealityTeleporter;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBlastFurnace;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidInfuser;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicAlchemy;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicAnchor;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicCharger;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicCrystallizer;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicPowerCable;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicPowerGen;
import Tamaized.Voidcraft.network.ServerPacketHandler;
import Tamaized.Voidcraft.registry.VoidCraftAchievements;
import Tamaized.Voidcraft.registry.VoidCraftArmors;
import Tamaized.Voidcraft.registry.VoidCraftBiomes;
import Tamaized.Voidcraft.registry.VoidCraftBlocks;
import Tamaized.Voidcraft.registry.VoidCraftCreativeTabs;
import Tamaized.Voidcraft.registry.VoidCraftFluids;
import Tamaized.Voidcraft.registry.VoidCraftItems;
import Tamaized.Voidcraft.registry.VoidCraftLootTables;
import Tamaized.Voidcraft.registry.VoidCraftMaterials;
import Tamaized.Voidcraft.registry.VoidCraftParticles;
import Tamaized.Voidcraft.registry.VoidCraftPotions;
import Tamaized.Voidcraft.registry.VoidCraftTERecipes;
import Tamaized.Voidcraft.registry.VoidCraftTools;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import Tamaized.Voidcraft.starforge.StarForgeEffectRecipeList;
import Tamaized.Voidcraft.starforge.effects.StarForgeEffectList;
import Tamaized.Voidcraft.structures.voidCity.MapGenVoidCity;
import Tamaized.Voidcraft.structures.voidCity.StructureVoidCityPieces;
import Tamaized.Voidcraft.structures.voidFortress.MapGenVoidFortress;
import Tamaized.Voidcraft.structures.voidFortress.StructureVoidFortressPieces;
import Tamaized.Voidcraft.vadeMecum.progression.RitualList;
import Tamaized.Voidcraft.world.WorldGeneratorVoid;
import Tamaized.Voidcraft.world.dim.TheVoid.ChunkProviderVoid;
import Tamaized.Voidcraft.world.dim.TheVoid.TeleporterVoid;
import Tamaized.Voidcraft.world.dim.TheVoid.WorldProviderVoid;
import Tamaized.Voidcraft.world.dim.Xia.TeleporterXia;
import Tamaized.Voidcraft.world.dim.Xia.WorldProviderXia;
import Tamaized.Voidcraft.world.dim.dalQuor.WorldProviderDalQuor;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = VoidCraft.modid, name = "VoidCraft", guiFactory = "Tamaized.Voidcraft.GUI.client.GUIConfigFactory", version = VoidCraft.version, dependencies = "required-before:" + TamModized.modid + "@[${tamversion},)")
public class VoidCraft extends TamModBase {

	// public static final boolean isDevBuild = false;

	public static final String version = "${version}";
	public static final String modid = "voidcraft";

	public static String getVersion() {
		return version;
	}

	@Instance(modid)
	public static VoidCraft instance = new VoidCraft();

	public static File configFile;
	public static ConfigHandler config;

	public static FMLEventChannel channel;
	public static final String networkChannelName = "VoidCraft";

	@SidedProxy(clientSide = "Tamaized.Voidcraft.proxy.ClientProxy", serverSide = "Tamaized.Voidcraft.proxy.ServerProxy")
	public static AbstractProxy proxy;

	public VoidTickEvent VoidTickEvent;

	public static RitualList ritualList;

	// Public API Integrations
	public static VoidCraftThaum thaumcraftIntegration;
	public static boolean isAetherLoaded = false;

	public static VoidCraftMaterials materials;
	public static VoidCraftCreativeTabs tabs;
	public static VoidCraftTools tools;
	public static VoidCraftItems items;
	public static VoidCraftPotions potions;
	public static VoidCraftArmors armors;
	public static VoidCraftFluids fluids;
	public static VoidCraftBlocks blocks;
	public static VoidCraftBiomes biomes;
	public static VoidCraftAchievements achievements;
	public static VoidCraftLootTables lootTables;
	public static VoidCraftTERecipes teRecipes;
	public static VoidCraftParticles particles;

	@Override
	protected AbstractProxy getProxy() {
		return proxy;
	}

	@Override
	public String getModID() {
		return modid;
	}

	@Override
	@EventHandler
	public void FMLpreInit(FMLPreInitializationEvent event) {
		super.FMLpreInit(event);
	}

	@Override
	@EventHandler
	public void FMLinit(FMLInitializationEvent event) {
		super.FMLinit(event);
	}

	@Override
	@EventHandler
	public void FMLpostInit(FMLPostInitializationEvent event) {
		super.FMLpostInit(event);
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		logger = LogManager.getLogger("VoidCraft");

		logger.info("Uh oh, I guess we need to open a portal to the Void");
		logger.info("Starting VoidCraft PreInit");

		configFile = event.getSuggestedConfigurationFile();
		config = new ConfigHandler(new Configuration(configFile));

		ContributorHandler.start();

		// Initialize Network
		channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(networkChannelName);

		// yo dawg, Register the Registers... i'm sorry
		register(materials = new VoidCraftMaterials());
		register(tabs = new VoidCraftCreativeTabs());
		register(blocks = new VoidCraftBlocks());
		register(fluids = new VoidCraftFluids());
		register(items = new VoidCraftItems());
		register(potions = new VoidCraftPotions());
		register(tools = new VoidCraftTools());
		register(armors = new VoidCraftArmors());
		register(biomes = new VoidCraftBiomes());
		register(achievements = new VoidCraftAchievements());
		register(lootTables = new VoidCraftLootTables());
		register(teRecipes = new VoidCraftTERecipes());
		register(particles = new VoidCraftParticles());

		// Register Sounds Events
		VoidSoundEvents.register();

		// API Loader
		if (Loader.isModLoaded("Thaumcraft")) {
			// logger.info("Thaumcraft Detected. Attempting to load API");
			try {
				// thaumcraftIntegration = new VoidCraftThaum();
				// logger.info("Loaded ThaumcraftAPI into VoidCraft");
			} catch (Exception e1) {
				logger.info("Error while adding ThaumcraftAPI into VoidCraft");
				e1.printStackTrace(System.err);
			}
		}

		// if(thaumcraftIntegration != null) thaumcraftIntegration.preInit();

		// Register Capabilities
		CapabilityManager.INSTANCE.register(IVoidicInfusionCapability.class, new VoidicInfusionCapabilityStorage(), VoidicInfusionCapabilityHandler.class);
		CapabilityManager.INSTANCE.register(IVadeMecumCapability.class, new VadeMecumCapabilityStorage(), VadeMecumCapabilityHandler.class);
		CapabilityManager.INSTANCE.register(IVadeMecumItemCapability.class, new VadeMecumItemCapabilityStorage(), VadeMecumItemCapabilityHandler.class);
		CapabilityManager.INSTANCE.register(IElytraFlyingCapability.class, new ElytraFlyingCapabilityStorage(), ElytraFlyingCapabilityHandler.class);
		CapabilityManager.INSTANCE.register(IVoidicPowerCapability.class, new VoidicPowerCapabilityStorage(), VoidicPowerCapabilityHandler.class);
		CapabilityManager.INSTANCE.register(IStarForgeCapability.class, new StarForgeCapabilityStorage(), StarForgeCapabilityHandler.class);
		MinecraftForge.EVENT_BUS.register(new Tamaized.Voidcraft.capabilities.EventHandler());

	}

	@Override
	public void init(FMLInitializationEvent event) {
		logger.info("Starting VoidCraft Init");

		// Register StarForge Effects
		StarForgeEffectList.register();
		StarForgeEffectRecipeList.instance.register();

		// Tile Entities
		GameRegistry.registerTileEntity(TileEntityVoidMacerator.class, "tileEntityVoidMacerator");
		GameRegistry.registerTileEntity(TileEntityVoidBox.class, "tileEntityVoidBox");
		GameRegistry.registerTileEntity(TileEntityVoidInfuser.class, "tileEntityVoidInfuser");
		GameRegistry.registerTileEntity(TileEntityHeimdall.class, "tileEntityHeimdall");
		GameRegistry.registerTileEntity(TileEntityNoBreak.class, "tileEntityNoBreak");
		GameRegistry.registerTileEntity(TileEntityAIBlock.class, "tileEntityAIBlock");
		GameRegistry.registerTileEntity(TileEntityVoidicPowerGen.class, "tileEntityVoidicPowerGen");
		GameRegistry.registerTileEntity(TileEntityVoidicPowerCable.class, "tileEntityVoidicPowerCable");
		GameRegistry.registerTileEntity(TileEntityVoidicCharger.class, "tileEntityVoidicCharger");
		GameRegistry.registerTileEntity(TileEntityRealityStabilizer.class, "tileEntityRealityStabilizer");
		GameRegistry.registerTileEntity(TileEntityFakeBedrockFarmland.class, "tileEntityFakeBedrockFarmland");
		GameRegistry.registerTileEntity(TileEntityVoidicAlchemy.class, "tileEntityVoidicAlchemy");
		GameRegistry.registerTileEntity(TileEntityRealityTeleporter.class, "tileEntityRealityTeleporter");
		GameRegistry.registerTileEntity(TileEntitySpellIceSpike.class, "tileEntitySpellIceSpike");
		GameRegistry.registerTileEntity(TileEntityStarForge.class, "tileEntityStarForge");
		GameRegistry.registerTileEntity(TileEntityVoidBlastFurnace.class, "tileEntityVoidBlastFurnace");
		GameRegistry.registerTileEntity(TileEntityVoidicAnchor.class, "tileEntityVoidicAnchor");
		GameRegistry.registerTileEntity(TileEntityVoidicCrystallizer.class, "tileEntityVoidicCrystallizer");

		// GUI Handler
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

		// Register Events
		VoidTickEvent = new VoidTickEvent();
		MinecraftForge.EVENT_BUS.register(VoidTickEvent);
		MinecraftForge.EVENT_BUS.register(new PickUpEvent());
		MinecraftForge.EVENT_BUS.register(new SpawnEvent());
		MinecraftForge.EVENT_BUS.register(new CraftingHandler());
		MinecraftForge.EVENT_BUS.register(new BlockBreakPlaceEvent());
		MinecraftForge.EVENT_BUS.register(new VoidicInfusionHandler());
		MinecraftForge.EVENT_BUS.register(new DamageEvent());
		MinecraftForge.EVENT_BUS.register(new XiaFlightHandler());
		MinecraftForge.EVENT_BUS.register(new CapabilitySyncEvent());
		// MinecraftForge.EVENT_BUS.register(new CustomElytraHandler());
		MinecraftForge.EVENT_BUS.register(config);
		MinecraftForge.EVENT_BUS.register(new ItemEntityEvent());
		MinecraftForge.EVENT_BUS.register(new DeathEvent());
		MinecraftForge.EVENT_BUS.register(new PlayerRightClickEvent());
		MinecraftForge.EVENT_BUS.register(new LitStrikeEvent());

		// Register Projectiles and other misc entities
		registerEntity(VoidChain.class, "VoidChain", this, modid, 128, 1, true);
		registerEntity(AcidBall.class, "AcidBall", this, modid, 128, 1, true);
		// registerEntity(EntityHookShot.class, "HookShot", this, 128, 1, true);
		registerEntity(EntityHerobrineFireball.class, "HerobrineFireball", this, modid, 128, 1, true);
		registerEntity(EntityObsidianFlask.class, "EntityObsidianFlask", this, modid, 128, 1, true);
		registerEntity(EntityHerobrineWitherSkull.class, "HerobrineWitherSkull", this, modid, 128, 1, true);
		registerEntity(EntityHerobrineTNTPrimed.class, "HerobrineTNTPrimed", this, modid, 128, 1, true);
		registerEntity(EntityHerobrineShadow.class, "HerobrineShadow", this, modid, 128, 1, true);
		registerEntity(EntityLichInferno.class, "LichInferno", this, modid, 128, 1, true);
		registerEntity(ProjectileDisintegration.class, "ProjectileDisintegration", this, modid, 128, 1, true);
		registerEntity(EntitySpellRune.class, "EntitySpellRune", this, modid, 64, 1, true);
		registerEntity(EntityCasterLightningBolt.class, "EntityCasterLightningBolt", this, modid, 64, 1, true);
		registerEntity(EntitySpellImplosion.class, "EntitySpellImplosion", this, modid, 64, 1, true);

		// Register Dimensions
		DimensionManager.registerDimension(config.getDimensionIdVoid(), DimensionType.register("The Void", "_void", config.getDimensionIdVoid(), WorldProviderVoid.class, false));
		DimensionManager.registerDimension(config.getDimensionIdXia(), DimensionType.register("???", "_xia", config.getDimensionIdXia(), WorldProviderXia.class, false));
		DimensionManager.registerDimension(config.getDimensionIdDalQuor(), DimensionType.register("Dal Quor", "_dalquor", config.getDimensionIdDalQuor(), WorldProviderDalQuor.class, false));

		// Register Portals
		PortalHandlerRegistry.register(blocks.blockPortalVoid, config.getDimensionIdVoid(), TeleporterVoid.class);
		PortalHandlerRegistry.register(blocks.blockPortalXia, config.getDimensionIdXia(), TeleporterXia.class);

		// Register World Gen
		GameRegistry.registerWorldGenerator(new WorldGeneratorVoid(), 0);
		new WorldType(modid+"_void") {
			@Override
			public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
				return new ChunkProviderVoid(world, true, world.getSeed());
			}
		};

		MapGenStructureIO.registerStructure(MapGenVoidFortress.Start.class, "VoidFortress");
		MapGenStructureIO.registerStructure(MapGenVoidCity.Start.class, "VoidCity");
		StructureVoidFortressPieces.registerNetherFortressPieces();
		StructureVoidCityPieces.registerPieces();

		// Register Mobs
		registerEntityWithEgg(EntityMobWraith.class, "Wraith", this, modid, 64, 1, true, 0xFFFFFF, 0x000000);
		registerEntityWithEgg(EntityMobSpectreChain.class, "SpectreChain", this, modid, 64, 1, true, 0xFFFFFF, 0xAA0077);
		registerEntityWithEgg(EntityMobVoidWrath.class, "VoidWrath", this, modid, 64, 1, true, 0xFF0000, 0x000000);
		registerEntityWithEgg(EntityMobLich.class, "Lich", this, modid, 64, 1, true, 0xAA00FF, 0x000000);
		registerEntity(EntityBossCorruptedPawn.class, "VoidBoss", this, modid, 64, 1, true);
		registerEntity(EntityBossHerobrine.class, "Herobrine", this, modid, 64, 1, true);
		registerEntity(EntityBossDol.class, "Dol", this, modid, 64, 1, true);
		registerEntity(EntityBossZol.class, "Zol", this, modid, 64, 1, true);
		registerEntity(EntityBossXia.class, "Xia", this, modid, 64, 1, true);
		registerEntity(EntityBossXia2.class, "Xia2", this, modid, 250, 1, true);
		registerEntity(EntityGhostPlayer.class, "GhostPlayer", this, modid, 64, 1, true);
		registerEntity(EntityGhostBiped.class, "GhostBiped", this, modid, 64, 1, true);
		registerEntityWithEgg(EntityHerobrineCreeper.class, "HerobrineCreeper", this, modid, 64, 1, true, 0xFF0000, 0x000000);
		registerEntity(EntityWitherbrine.class, "Witherbrine", this, modid, 250, 1, true);
		registerEntity(EntityDragonOld.class, "DragonOld", this, modid, 64, 1, true);
		registerEntity(EntityDragonXia.class, "DragonXia", this, modid, 250, 1, true);
		registerEntity(EntityDolXia.class, "DolXia", this, modid, 250, 1, true);
		registerEntity(EntityZolXia.class, "ZolXia", this, modid, 250, 1, true);
		registerEntity(EntityVoidicDragon.class, "VoidicDragon", this, modid, 64, 1, true);
		registerEntityWithEgg(EntityMobEtherealGuardian.class, "EtherealGuardian", this, modid, 64, 1, true, 0xFF0000, 0x000000);
		registerEntity(EntityCompanionFireElemental.class, "FireElemental", this, modid, 64, 1, true);
		registerEntityWithEgg(EntityHashalaq.class, "Hashalaq", this, modid, 64, 1, true, 0x000000, 0x00FFFF);
		registerEntityWithEgg(EntityLordOfBlades.class, "LordOfBlades", this, modid, 250, 1, true, 0x777777, 0x000000);

		// Register Biomes
		Biome.getBiome(6).getSpawnableList(EnumCreatureType.MONSTER).add(new SpawnListEntry(EntityMobLich.class, 10, 0, 1));

		// if(thaumcraftIntegration != null) thaumcraftIntegration.init();

	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		logger.info("Starting VoidCraft PostInit");

		ForgeChunkManager.setForcedChunkLoadingCallback(this, new LoadingCallback() {
			@Override
			public void ticketsLoaded(List<Ticket> tickets, World world) {
				for (Ticket ticket : tickets) {

				}
			}
		});

		// Load Rituals
		reloadRitualList();

		// Register Network
		channel.register(new ServerPacketHandler());

		// if(thaumcraftIntegration != null) thaumcraftIntegration.postInit();

	}

	public static void reloadRitualList() {
		ritualList = new RitualList();
	}

}