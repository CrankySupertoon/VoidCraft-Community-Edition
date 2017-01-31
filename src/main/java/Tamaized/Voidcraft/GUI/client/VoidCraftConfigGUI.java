package Tamaized.Voidcraft.GUI.client;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class VoidCraftConfigGUI extends GuiConfig {

	public VoidCraftConfigGUI(GuiScreen parent) {
		super(parent, new ConfigElement(VoidCraft.config.getConfig().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), VoidCraft.modid, false, false, "VoidCraft Config");
	}

	@Override
	public void initGui() {
		super.initGui();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		super.actionPerformed(button);
	}

}
