package Tamaized.Voidcraft.vadeMecum;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class VadeMecumButton extends GuiButton {

	public static final ResourceLocation TEXTURES = new ResourceLocation(VoidCraft.modid, "textures/gui/VadeMecum/vadeMecumButton.png");
	private final ItemStack stackToRender;
	private final VadeMecumGUI gui;

	public VadeMecumButton(VadeMecumGUI gui, int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, ItemStack stack) {
		super(buttonId, x, y, widthIn, heightIn, ("" + I18n.format(buttonText, new Object[0])).trim());
		stackToRender = stack;
		this.gui = gui;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			boolean flag = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			mc.getTextureManager().bindTexture(TEXTURES);
			int i = 0;
			int j = 0;

			if (flag) {
				j += 128;
			}
			gui.drawTexturedModalRect(this.xPosition + 10, this.yPosition, width + 60, height, i, j, 256, 128);
			GlStateManager.enableBlend();
			mc.getRenderManager().getFontRenderer().drawSplitString(displayString, xPosition + 20, yPosition + (height / 2) - (mc.getRenderManager().getFontRenderer().FONT_HEIGHT / 2 * ((int) Math.ceil((float) mc.getRenderManager().getFontRenderer().getStringWidth(displayString) / 120F))), 120, 0x000000);
			GlStateManager.disableBlend();
			GlStateManager.color(1F, 1F, 1F, 1F);
			RenderHelper.enableGUIStandardItemLighting();
			if (!stackToRender.isEmpty()) mc.getRenderItem().renderItemIntoGUI(stackToRender, xPosition, yPosition + (height / 2) - 8);
			RenderHelper.disableStandardItemLighting();
		}
	}

}
