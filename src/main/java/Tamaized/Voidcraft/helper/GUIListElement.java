package Tamaized.Voidcraft.helper;

import Tamaized.Voidcraft.helper.GUIElementList.GUIContainerWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class GUIListElement {

	public GUIListElement() {

	}

	@SideOnly(Side.CLIENT)
	public abstract void draw(GUIContainerWrapper gui, Minecraft mc, int right, int top, int height, Tessellator tess);

}
