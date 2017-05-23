package Tamaized.Voidcraft.handlers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;

import Tamaized.TamModized.config.AbstractConfigHandler;
import Tamaized.Voidcraft.VoidCraft;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler extends AbstractConfigHandler {

	private boolean generate_VoidOre = true;
	private boolean generate_CosmicOre = true;
	private int dimensionIdVoid = -2;
	private int dimensionIdXia = -3;
	private int dimensionIdDalQuor = -4;
	private List<Integer> realityWhitelist = new ArrayList<Integer>();
	private boolean renderFirstPersonVadeMecumParticles;
	private boolean renderThirdPersonVadeMecumParticles;
	private boolean voidTeleport;
	private boolean music;

	private boolean default_generate_VoidOre = true;
	private boolean default_generate_CosmicOre = true;
	private int default_dimensionIdVoid = -2;
	private int default_dimensionIdXia = -3;
	private int default_dimensionIdDalQuor = -4;
	private int[] default_realityWhitelist = new int[] { 0, -1 };
	private boolean default_renderFirstPersonVadeMecumParticles = true;
	private boolean default_renderThirdPersonVadeMecumParticles = true;
	private boolean default_voidTeleport = true;
	private boolean default_music = true;

	// Use these to store values that wont be updated during runtime but need to be stored to the config from in-game gui
	private int temp_dimensionIdVoid = -2;
	private int temp_dimensionIdXia = -3;
	private int temp_dimensionIdDalQuor = -4;

	public ConfigHandler(VoidCraft instance, File f, Configuration c) {
		super(instance, f, c);
	}

	@Override
	protected void init() {
		generate_VoidOre = true;
		generate_CosmicOre = true;
		dimensionIdVoid = -2;
		dimensionIdXia = -3;
		dimensionIdDalQuor = -4;
		realityWhitelist = new ArrayList<Integer>();

		default_generate_VoidOre = true;
		default_generate_CosmicOre = true;
		default_dimensionIdVoid = -2;
		default_dimensionIdXia = -3;
		default_dimensionIdDalQuor = -4;
		default_realityWhitelist = new int[] { 0, -1 };
		default_renderFirstPersonVadeMecumParticles = true;
		default_renderThirdPersonVadeMecumParticles = true;
		default_voidTeleport = true;
		default_music = true;

		temp_dimensionIdVoid = -2;
		temp_dimensionIdXia = -3;
		temp_dimensionIdDalQuor = -4;
	}

	@Override
	protected void loadData(boolean firstLoad) {
		music = config.get(Configuration.CATEGORY_GENERAL, "Enable all custom BG Music", default_music).getBoolean();
		voidTeleport = config.get(Configuration.CATEGORY_GENERAL, "Teleport below Y level -256", default_voidTeleport).getBoolean();
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
		System.out.println(config);
		System.out.println(default_realityWhitelist);
		System.out.println(config.get(Configuration.CATEGORY_GENERAL, "Reality Hole Dimension Whitelist", default_realityWhitelist, "List of Dimension IDs the Reality Hole will attempt to send you to").getIntList());
		realityWhitelist = IntStream.of(config.get(Configuration.CATEGORY_GENERAL, "Reality Hole Dimension Whitelist", default_realityWhitelist, "List of Dimension IDs the Reality Hole will attempt to send you to").getIntList()).boxed().collect(Collectors.toList());
		Iterator<Integer> iter = realityWhitelist.iterator();
		while (iter.hasNext()) {
			int id = iter.next();
			if (id == getDimensionIdDalQuor() || id == getDimensionIdXia()) iter.remove();
		}
		generate_VoidOre = config.get(Configuration.CATEGORY_GENERAL, "Enable VoidCrystal Ore Gen", default_generate_VoidOre).getBoolean();
		generate_CosmicOre = config.get(Configuration.CATEGORY_GENERAL, "Enable Cosmic Material Gen", default_generate_CosmicOre).getBoolean();
	}

	@Override
	protected void cleanup() throws IOException {
		// config.get(Configuration.CATEGORY_GENERAL, "Render First Person Particles", default_renderFirstPersonVadeMecumParticles).set(renderFirstPersonVadeMecumParticles);
		config.get(Configuration.CATEGORY_GENERAL, "Render Vade Mecum Item Particles", default_renderThirdPersonVadeMecumParticles).set(renderThirdPersonVadeMecumParticles);
		config.get(Configuration.CATEGORY_GENERAL, "Void Dimension ID", default_dimensionIdVoid).set(temp_dimensionIdVoid);
		config.get(Configuration.CATEGORY_GENERAL, "Xia Dimension ID", dimensionIdXia).set(temp_dimensionIdXia);
		config.get(Configuration.CATEGORY_GENERAL, "Dal Quor Dimension ID", dimensionIdDalQuor).set(temp_dimensionIdDalQuor);
		config.get(Configuration.CATEGORY_GENERAL, "Reality Hole Dimension Whitelist", default_realityWhitelist, "List of Dimension IDs the Reality Hole will attempt to send you to").set(ArrayUtils.toPrimitive(realityWhitelist.toArray(new Integer[realityWhitelist.size()])));
		config.get(Configuration.CATEGORY_GENERAL, "Enable VoidCrystal Ore Gen", default_generate_VoidOre).set(generate_VoidOre);
		config.get(Configuration.CATEGORY_GENERAL, "Enable Cosmic Material Gen", default_generate_CosmicOre).set(generate_CosmicOre);
		config.get(Configuration.CATEGORY_GENERAL, "Teleport below Y level -256", default_voidTeleport).set(voidTeleport);
		config.get(Configuration.CATEGORY_GENERAL, "Enable all custom BG Music", default_music).set(music);
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

	public boolean voidYLevelTeleport() {
		return voidTeleport;
	}

	public boolean canPlayMusic() {
		return music;
	}

}
