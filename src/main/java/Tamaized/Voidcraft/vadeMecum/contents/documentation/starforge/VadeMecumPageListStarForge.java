package Tamaized.Voidcraft.vadeMecum.contents.documentation.starforge;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import net.minecraft.client.resources.I18n;

public class VadeMecumPageListStarForge implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.starforge", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.pg1"),

				new VadeMecumPage("", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.pg2"),

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.blindingFear", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.blindingFear"),

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.firstDegreeBurns", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.firstDegreeBurns"),

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.voidTouch", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.voidTouch"),

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.xiaBlessing", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.xiaBlessing"),

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.secondDegreeBurns", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.secondDegreeBurns"),

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.voidWrath", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.voidWrath"),

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.mortalFear", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.mortalFear"),

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.thirdDegreeBurns", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.thirdDegreeBurns"),

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.voidCripple", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.voidCripple"),

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.vorp", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.vorp"),

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.haste", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.haste"),

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.silkTouch", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.silkTouch"),

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.fortune", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.fortune"),

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.starforge.effect.threeByThree", VoidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.threeByThree")

		};
	}

}
