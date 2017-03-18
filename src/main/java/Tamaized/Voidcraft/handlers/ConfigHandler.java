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

	private int dimensionIdVoid = -2;
	private int dimensionIdXia = -3;
	private List<Integer> realityWhitelist = new ArrayList<Integer>();
	private boolean renderFirstPersonVadeMecumParticles;
	private boolean renderThirdPersonVadeMecumParticles;

	private int default_dimensionIdVoid = -2;
	private int default_dimensionIdXia = -3;
	private int[] default_realityWhitelist = new int[] { 0, -1 };
	private boolean default_renderFirstPersonVadeMecumParticles = true;
	private boolean default_renderThirdPersonVadeMecumParticles = true;

	// Use these to store values that wont be updated during runtime but need to be stored to the config from in-game gui
	private int temp_dimensionIdVoid = -2;
	private int temp_dimensionIdXia = -3;

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
		// renderFirstPersonVadeMecumParticles = config.get(Configuration.CATEGORY_GENERAL, "Render First Person Particles", default_renderFirstPersonVadeMecumParticles).getBoolean();
		renderThirdPersonVadeMecumParticles = config.get(Configuration.CATEGORY_GENERAL, "Render Vade Mecum Item Particles", default_renderThirdPersonVadeMecumParticles).getBoolean();
		if (firstLoad) {
			temp_dimensionIdVoid = dimensionIdVoid = config.get(Configuration.CATEGORY_GENERAL, "Void Dimension ID", default_dimensionIdVoid).getInt();
			temp_dimensionIdXia = dimensionIdXia = config.get(Configuration.CATEGORY_GENERAL, "Xia Dimension ID", dimensionIdXia).getInt();
		} else {
			temp_dimensionIdVoid = config.get(Configuration.CATEGORY_GENERAL, "Void Dimension ID", default_dimensionIdVoid).getInt();
			temp_dimensionIdXia = config.get(Configuration.CATEGORY_GENERAL, "Xia Dimension ID", dimensionIdXia).getInt();
		}
		realityWhitelist = IntStream.of(config.get(Configuration.CATEGORY_GENERAL, "Reality Hole Dimension Whitelist", default_realityWhitelist, "List of Dimension IDs the Reality Hole will attempt to send you to").getIntList()).boxed().collect(Collectors.toList());
		Iterator<Integer> iter = realityWhitelist.iterator();
		while (iter.hasNext()) {
			int id = iter.next();
			if (id == getDimensionIDdalQuor() || id == getDimensionIDxia()) iter.remove();

		}
	}

	private void cleanupFile() throws IOException {
		VoidCraft.configFile.delete();
		VoidCraft.configFile.createNewFile();
		config = new Configuration(VoidCraft.configFile);
		// config.get(Configuration.CATEGORY_GENERAL, "Render First Person Particles", default_renderFirstPersonVadeMecumParticles).set(renderFirstPersonVadeMecumParticles);
		config.get(Configuration.CATEGORY_GENERAL, "Render Vade Mecum Item Particles", default_renderThirdPersonVadeMecumParticles).set(renderThirdPersonVadeMecumParticles);
		config.get(Configuration.CATEGORY_GENERAL, "Void Dimension ID", default_dimensionIdVoid).set(temp_dimensionIdVoid);
		config.get(Configuration.CATEGORY_GENERAL, "Xia Dimension ID", dimensionIdXia).set(temp_dimensionIdXia);
		config.get(Configuration.CATEGORY_GENERAL, "Reality Hole Dimension Whitelist", default_realityWhitelist, "List of Dimension IDs the Reality Hole will attempt to send you to").set(ArrayUtils.toPrimitive(realityWhitelist.toArray(new Integer[realityWhitelist.size()])));
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

	public int getDimensionIDvoid() {
		return dimensionIdVoid;
	}

	public int getDimensionIDxia() {
		return dimensionIdXia;
	}

	public int getDimensionIDdalQuor() {
		return -4;
	}

	public List<Integer> getRealityWhiteList() {
		return realityWhitelist;
	}

}
