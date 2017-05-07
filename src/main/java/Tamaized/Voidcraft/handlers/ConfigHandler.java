package Tamaized.Voidcraft.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {

	private Configuration config;

	private boolean generate_VoidOre = true;
	private boolean generate_CosmicOre = true;
	private int dimensionIdVoid = -2;
	private int dimensionIdXia = -3;
	private int dimensionIdDalQuor = -4;
	private List<Integer> realityWhitelist = new ArrayList<Integer>();
	private boolean renderFirstPersonVadeMecumParticles;
	private boolean renderThirdPersonVadeMecumParticles;

	private boolean default_generate_VoidOre = true;
	private boolean default_generate_CosmicOre = true;
	private int default_dimensionIdVoid = -2;
	private int default_dimensionIdXia = -3;
	private int default_dimensionIdDalQuor = -4;
	private int[] default_realityWhitelist = new int[] { 0, -1 };
	private boolean default_renderFirstPersonVadeMecumParticles = true;
	private boolean default_renderThirdPersonVadeMecumParticles = true;

	// Use these to store values that wont be updated during runtime but need to be stored to the config from in-game gui
	private int temp_dimensionIdVoid = -2;
	private int temp_dimensionIdXia = -3;
	private int temp_dimensionIdDalQuor = -4;

	public ConfigHandler(Configuration c) {
		config = c;
		config.load();
		sync(true);
	}

	public Configuration getConfig() {
		return config;
	}

	public void sync(boolean firstLoad) {
		try {
			if (VoidCraft.isAetherLoaded) default_realityWhitelist = new int[] { 0, -1, 3 };
			loadData(firstLoad);
			cleanupFile();
			config.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadData(boolean firstLoad) {

		renderThirdPersonVadeMecumParticles = config.get(Configuration.CATEGORY_GENERAL, "Render Vade Mecum Item Particles", default_renderThirdPersonVadeMecumParticles).getBoolean();
		if (firstLoad) {
			temp_dimensionIdVoid = dimensionIdVoid = config.get(Configuration.CATEGORY_GENERAL, "Void Dimension ID", default_dimensionIdVoid).getInt();
			temp_dimensionIdXia = dimensionIdXia = config.get(Configuration.CATEGORY_GENERAL, "Xia Dimension ID", dimensionIdXia).getInt();
			temp_dimensionIdDalQuor = dimensionIdDalQuor = config.get(Configuration.CATEGORY_GENERAL, "Dal Quor Dimension ID", dimensionIdDalQuor).getInt();
		} else {
			temp_dimensionIdVoid = config.get(Configuration.CATEGORY_GENERAL, "Void Dimension ID", default_dimensionIdVoid).getInt();
			temp_dimensionIdXia = config.get(Configuration.CATEGORY_GENERAL, "Xia Dimension ID", dimensionIdXia).getInt();
			temp_dimensionIdDalQuor = config.get(Configuration.CATEGORY_GENERAL, "Dal Quor Dimension ID", dimensionIdDalQuor).getInt();
		}
		realityWhitelist = IntStream.of(config.get(Configuration.CATEGORY_GENERAL, "Reality Hole Dimension Whitelist", default_realityWhitelist, "List of Dimension IDs the Reality Hole will attempt to send you to").getIntList()).boxed().collect(Collectors.toList());
		Iterator<Integer> iter = realityWhitelist.iterator();
		while (iter.hasNext()) {
			int id = iter.next();
			if (id == getDimensionIdDalQuor() || id == getDimensionIdXia()) iter.remove();
		}
		generate_VoidOre = config.get(Configuration.CATEGORY_GENERAL, "Enable VoidCrystal Ore Gen", default_generate_VoidOre).getBoolean();
		generate_CosmicOre = config.get(Configuration.CATEGORY_GENERAL, "Enable Cosmic Material Gen", default_generate_CosmicOre).getBoolean();
	}

	private void cleanupFile() throws IOException {
		VoidCraft.configFile.delete();
		VoidCraft.configFile.createNewFile();
		config = new Configuration(VoidCraft.configFile);
		// config.get(Configuration.CATEGORY_GENERAL, "Render First Person Particles", default_renderFirstPersonVadeMecumParticles).set(renderFirstPersonVadeMecumParticles);
		config.get(Configuration.CATEGORY_GENERAL, "Render Vade Mecum Item Particles", default_renderThirdPersonVadeMecumParticles).set(renderThirdPersonVadeMecumParticles);
		config.get(Configuration.CATEGORY_GENERAL, "Void Dimension ID", default_dimensionIdVoid).set(temp_dimensionIdVoid);
		config.get(Configuration.CATEGORY_GENERAL, "Xia Dimension ID", dimensionIdXia).set(temp_dimensionIdXia);
		config.get(Configuration.CATEGORY_GENERAL, "Dal Quor Dimension ID", dimensionIdDalQuor).set(temp_dimensionIdDalQuor);
		config.get(Configuration.CATEGORY_GENERAL, "Reality Hole Dimension Whitelist", default_realityWhitelist, "List of Dimension IDs the Reality Hole will attempt to send you to").set(ArrayUtils.toPrimitive(realityWhitelist.toArray(new Integer[realityWhitelist.size()])));
		config.get(Configuration.CATEGORY_GENERAL, "Enable VoidCrystal Ore Gen", default_generate_VoidOre).set(generate_VoidOre);
		config.get(Configuration.CATEGORY_GENERAL, "Enable Cosmic Material Gen", default_generate_CosmicOre).set(generate_CosmicOre);
	}

	@SubscribeEvent
	public void configChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(VoidCraft.modid)) sync(false);
	}

	public boolean getRenderFirstPersonParticles() {
		return renderFirstPersonVadeMecumParticles;
	}

	public boolean getRenderThirdPersonParticles() {
		return renderThirdPersonVadeMecumParticles;
	}

	public int getDimensionIdVoid() {
		return dimensionIdVoid;
	}

	public int getDimensionIdXia() {
		return dimensionIdXia;
	}

	public int getDimensionIdDalQuor() {
		return dimensionIdDalQuor;
	}

	public List<Integer> getRealityWhiteList() {
		return realityWhitelist;
	}

	public boolean canGenVoidCrystalOre() {
		return generate_VoidOre;
	}

	public boolean canGenCosmicMaterial() {
		return generate_CosmicOre;
	}

}
