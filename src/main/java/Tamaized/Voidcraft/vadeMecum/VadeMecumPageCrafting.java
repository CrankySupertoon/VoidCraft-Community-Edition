package Tamaized.Voidcraft.vadeMecum;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import net.minecraft.client.gui.FontRenderer;

public class VadeMecumPageCrafting implements IVadeMecumPage {

	private final IVadeMecumCrafting crafting;

	public VadeMecumPageCrafting(IVadeMecumCrafting craft) {
		crafting = craft;
	}

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		crafting.render(gui, render, x + offset, y, mx, my);
	}

}
