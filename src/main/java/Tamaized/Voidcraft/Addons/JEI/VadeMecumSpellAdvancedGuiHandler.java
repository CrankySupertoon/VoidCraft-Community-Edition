package Tamaized.Voidcraft.Addons.JEI;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import Tamaized.Voidcraft.GUI.client.VadeMecumSpellsGUI;
import mezz.jei.api.gui.IAdvancedGuiHandler;

public class VadeMecumSpellAdvancedGuiHandler implements IAdvancedGuiHandler<VadeMecumSpellsGUI> {

	@Override
	public Class<VadeMecumSpellsGUI> getGuiContainerClass() {
		return VadeMecumSpellsGUI.class;
	}

	@Override
	public List<Rectangle> getGuiExtraAreas(VadeMecumSpellsGUI instance) {
		List<Rectangle> list = new ArrayList<Rectangle>();
		list.add(new Rectangle(0, 0, instance.width, instance.height - (instance.getYSize()) - 10));
		return list;
	}

	@Override
	public Object getIngredientUnderMouse(VadeMecumSpellsGUI guiContainer, int mouseX, int mouseY) {
		return null;
	}

}
