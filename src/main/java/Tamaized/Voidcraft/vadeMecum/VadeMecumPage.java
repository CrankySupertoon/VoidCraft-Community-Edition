package Tamaized.Voidcraft.vadeMecum;

import Tamaized.TamModized.helper.TranslateHelper;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPage implements IVadeMecumPage {

	protected final String title;
	protected final String text;

	public VadeMecumPage(String title, String text) {
		this.title = TranslateHelper.translate(title);
		this.text = TranslateHelper.translate(text);
	}

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
