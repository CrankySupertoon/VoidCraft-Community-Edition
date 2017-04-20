package Tamaized.Voidcraft.vadeMecum.contents.documentation.starforge;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.starforge.effects.StarForgeEffectList;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListStarForge implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.starforge", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.pg1"),

				new VadeMecumPage("", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.pg2"),

				new VadeMecumPageStarForgeEffect(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.blindingFear", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.blindingFear", StarForgeEffectList.blindingFear),

				new VadeMecumPageStarForgeEffect(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.firstDegreeBurns", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.firstDegreeBurns", StarForgeEffectList.firstDegreeBurns),

				new VadeMecumPageStarForgeEffect(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.voidTouch", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.voidTouch", StarForgeEffectList.voidTouch),

				new VadeMecumPageStarForgeEffect(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.xiaBlessing", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.xiaBlessing", StarForgeEffectList.xiaBlessing),

				new VadeMecumPageStarForgeEffect(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.secondDegreeBurns", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.secondDegreeBurns", StarForgeEffectList.secondDegreeBurns),

				new VadeMecumPageStarForgeEffect(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.voidWrath", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.voidWrath", StarForgeEffectList.voidWrath),

				new VadeMecumPageStarForgeEffect(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.mortalFear", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.mortalFear", StarForgeEffectList.mortalFear),

				new VadeMecumPageStarForgeEffect(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.thirdDegreeBurns", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.thirdDegreeBurns", StarForgeEffectList.thirdDegreeBurns),

				new VadeMecumPageStarForgeEffect(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.voidCripple", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.voidCripple", StarForgeEffectList.voidCripple),

				new VadeMecumPageStarForgeEffect(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.vorp", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.vorp", StarForgeEffectList.vorp),

				new VadeMecumPageStarForgeEffect(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.haste", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.haste", StarForgeEffectList.haste),

				new VadeMecumPageStarForgeEffect(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.silkTouch", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.silkTouch", StarForgeEffectList.silkTouch),

				new VadeMecumPageStarForgeEffect(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.fortune", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.fortune", StarForgeEffectList.fortune),

				new VadeMecumPageStarForgeEffect(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.threeByThree", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.threeByThree", StarForgeEffectList.threeByThree)

		};
	}

}
