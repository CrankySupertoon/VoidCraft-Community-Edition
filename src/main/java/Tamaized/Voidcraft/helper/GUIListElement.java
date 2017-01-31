package Tamaized.Voidcraft.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class GUIListElement {

	public GUIListElement() {

	}

	@SideOnly(Side.CLIENT)
	public abstract void draw(GuiContainer gui, Minecraft mc, int right, int top, int height, Tessellator tess);

}
