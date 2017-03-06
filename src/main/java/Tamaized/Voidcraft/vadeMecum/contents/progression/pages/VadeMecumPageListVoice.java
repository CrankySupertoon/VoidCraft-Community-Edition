package Tamaized.Voidcraft.vadeMecum.contents.progression.pages;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageListVoice implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {

				new VadeMecumPage(new TextComponentString(TextFormatting.OBFUSCATED + "" + TextFormatting.DARK_PURPLE + "The Voice").getFormattedText(), "I'm sure you've noticed each time you attempt to cast a spell that there's a very good chance you'll explode. Now you may be wondering where this text is even coming from, i'll just let you know that I, the Vade Mecum, am no ordinary book, but we'll get back to that some other time. Right onto the explosion bits, I have a task for you, doing this task will decrease the chance for you to explode by 25 percent, currently your chance is 75 percent."),

				new VadeMecumPage("", "The task is simple, I want you to enter the Void and gain some infusion. It wont be much, but I'll let you know when your task is complete.") };
	}

}
