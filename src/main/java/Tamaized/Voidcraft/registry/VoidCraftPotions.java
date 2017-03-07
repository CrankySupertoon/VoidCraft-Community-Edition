package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.potion.PotionSheathe;
import Tamaized.Voidcraft.potion.PotionVoidImmunity;
import Tamaized.Voidcraft.potion.PotionVoidicInfusion;
import Tamaized.Voidcraft.potion.PotionVoidicInfusionImmunity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VoidCraftPotions implements ITamRegistry {

	private ArrayList<ITamModel> modelList;

	public static Potion voidicInfusion;
	public static Potion voidicInfusionImmunity;
	public static Potion voidImmunity;

	public static Potion fireSheathe;
	public static Potion frostSheathe;
	public static Potion litSheathe;
	public static Potion acidSheathe;
	public static Potion voidSheathe;

	public static PotionType type_voidImmunity;

	@Override
	public void preInit() {
		modelList = new ArrayList<ITamModel>();

		voidicInfusion = new PotionVoidicInfusion("voidicinfusion");
		voidicInfusionImmunity = new PotionVoidicInfusionImmunity("voidicinfusionimmunity");
		voidImmunity = new PotionVoidImmunity("voidimmunity");

		fireSheathe = new PotionSheathe("potionsheathefire", PotionSheathe.Type.Fire);
		frostSheathe = new PotionSheathe("potionsheathefrost", PotionSheathe.Type.Frost);
		litSheathe = new PotionSheathe("potionsheathelit", PotionSheathe.Type.Lit);
		acidSheathe = new PotionSheathe("potionsheatheacid", PotionSheathe.Type.Acid);
		voidSheathe = new PotionSheathe("potionsheathevoid", PotionSheathe.Type.Void);

		type_voidImmunity = new PotionType(new PotionEffect[] { new PotionEffect(voidImmunity, ((60 * 3) + 30) * 20) }).setRegistryName("voidimmunity");
		GameRegistry.register(type_voidImmunity);
	}

	@Override
	public void init() {

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

	}

	@Override
	public void clientPostInit() {

	}

}
