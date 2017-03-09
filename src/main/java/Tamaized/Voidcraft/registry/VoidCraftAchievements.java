package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class VoidCraftAchievements implements ITamRegistry {

	public static Achievement vadeMecum;
	public static Achievement theVoice;
	public static Achievement anchor; // Require Voice
	public static Achievement stabilization; // Require Voice
	public static Achievement empowerment; // Require Voice
	public static Achievement tolerance;
	public static Achievement totalControl;
	public static Achievement nightmare;

	public static Achievement familiarity;

	public static Achievement voidCrystal;
	public static Achievement enterVoid;
	public static Achievement demonsCall;
	public static Achievement artifact;
	public static Achievement godsSleep;
	public static Achievement tooFar;
	public static Achievement Ascension;
	public static Achievement worldsEnd;

	public static Achievement betterThanDiamond;
	public static Achievement angelicPower;

	public static Achievement theChains;
	public static Achievement thirdDegreeBurns;
	public static Achievement Legendary;
	public static Achievement demonicPower;

	public static Achievement infuser;
	public static Achievement macerator;
	public static Achievement heimdall;

	public static Achievement generator;
	public static Achievement charger;
	public static Achievement suppressor;

	@Override
	public void preInit() {

	}

	@Override
	public void init() {
		ItemStack activeVade = new ItemStack(VoidCraft.items.vadeMecum);
		activeVade.getCapability(CapabilityList.VADEMECUMITEM, null).setBookState(true);

		vadeMecum = new Achievement("achievement.vadeMecum", "vadeMecum", 0, 0, VoidCraft.items.vadeMecum, (Achievement) null);
		theVoice = new Achievement("achievement.theVoice", "theVoice", 0, 0, activeVade, vadeMecum);
		anchor = new Achievement("achievement.anchor", "anchor", 0, 0, VoidCraft.items.voidcrystal, theVoice);
		stabilization = new Achievement("achievement.stabilization", "stabilization", 0, 0, VoidCraft.blocks.blockVoidbrick, theVoice);
		empowerment = new Achievement("achievement.empowerment", "empowerment", 0, 0, VoidCraft.items.voidStar, theVoice);
		tolerance = new Achievement("achievement.tolerance", "tolerance", 0, 0, activeVade, empowerment);
		totalControl = new Achievement("achievement.totalControl", "totalControl", 0, 0, activeVade, tolerance);
		nightmare = new Achievement("achievement.nightmare", "nightmare", 0, 0, VoidCraft.items.quoriFragment, totalControl);

		voidCrystal = new Achievement("achievement.voidCrystal", "voidCrystal", 0, 0, VoidCraft.items.voidcrystal, (Achievement) null);
		voidCrystal.setSpecial().initIndependentStat().registerStat();
		enterVoid = new Achievement("achievement.enterVoid", "enterVoid", 0, 3, VoidCraft.blocks.blockVoidcrystal, voidCrystal);
		enterVoid.setSpecial().registerStat();
		demonsCall = new Achievement("achievement.demonsCall", "demonsCall", 0, 6, VoidCraft.items.ChainedSkull, enterVoid);
		demonsCall.registerStat();
		artifact = new Achievement("achievement.artifact", "artifact", 0, 9, VoidCraft.items.voidStar, demonsCall);
		artifact.setSpecial().registerStat();
		godsSleep = new Achievement("achievement.godsSleep", "godsSleep", 2, 9, VoidCraft.blocks.blockNoBreak, artifact);
		godsSleep.registerStat();
		tooFar = new Achievement("achievement.tooFar", "tooFar", 4, 9, VoidCraft.blocks.blockPortalXia, godsSleep);
		tooFar.registerStat();
		Ascension = new Achievement("achievement.Ascension", "Ascension", 6, 9, VoidCraft.armors.xiaHelmet, tooFar);
		Ascension.setSpecial().registerStat();
		worldsEnd = new Achievement("achievement.worldsEnd", "worldsEnd", 8, 9, VoidCraft.blocks.realityHole, Ascension);
		worldsEnd.setSpecial().registerStat();

		betterThanDiamond = new Achievement("achievement.betterThanDiamond", "betterThanDiamond", 3, 0, VoidCraft.tools.voidSword, voidCrystal);
		betterThanDiamond.registerStat();
		angelicPower = new Achievement("achievement.angelicPower", "angelicPower", 6, 0, VoidCraft.tools.angelicSword, betterThanDiamond);
		angelicPower.setSpecial().registerStat();

		theChains = new Achievement("achievement.theChains", "theChains", 6, 3, VoidCraft.tools.chainSword, betterThanDiamond);
		theChains.registerStat();
		thirdDegreeBurns = new Achievement("achievement.thirdDegreeBurns", "thirdDegreeBurns", 6, 6, VoidCraft.tools.moltenSword, theChains);
		thirdDegreeBurns.registerStat();
		Legendary = new Achievement("achievement.Legendary", "Legendary", 4, 6, VoidCraft.tools.archSword, thirdDegreeBurns);
		Legendary.setSpecial().registerStat();
		demonicPower = new Achievement("achievement.demonicPower", "demonicPower", 2, 6, VoidCraft.tools.demonSword, Legendary);
		demonicPower.setSpecial().registerStat();

		infuser = new Achievement("achievement.infuser", "infuser", -2, 9, VoidCraft.blocks.voidInfuser, artifact);
		infuser.registerStat();
		macerator = new Achievement("achievement.macerator", "macerator", -2, 6, VoidCraft.blocks.voidMacerator, infuser);
		macerator.registerStat();
		heimdall = new Achievement("achievement.heimdall", "heimdall", -2, 3, VoidCraft.blocks.Heimdall, macerator);
		heimdall.registerStat();

		generator = new Achievement("achievement.generator", "generator", -4, 9, VoidCraft.blocks.voidicGen, infuser);
		generator.registerStat();
		charger = new Achievement("achievement.charger", "charger", -4, 6, VoidCraft.blocks.voidicCharger, generator);
		charger.setSpecial().registerStat();
		suppressor = new Achievement("achievement.suppressor", "suppressor", -4, 3, VoidCraft.items.voidicSuppressor, charger);
		suppressor.registerStat();
	}

	@Override
	public void postInit() {
		Achievement[] achArray = {

				voidCrystal, enterVoid, demonsCall, artifact, godsSleep, tooFar, Ascension, worldsEnd,

				betterThanDiamond, angelicPower,

				theChains, thirdDegreeBurns, Legendary, demonicPower,

				infuser, macerator, heimdall,

				generator, charger, suppressor

		};
		AchievementPage.registerAchievementPage(new AchievementPage("VoidCraft", achArray));
	}

	@Override
	public ArrayList<ITamModel> getModelList() {
		return new ArrayList<ITamModel>();
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
