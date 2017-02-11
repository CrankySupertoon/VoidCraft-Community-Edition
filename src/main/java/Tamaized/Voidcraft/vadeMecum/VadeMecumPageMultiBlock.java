package Tamaized.Voidcraft.vadeMecum;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageMultiBlock extends VadeMecumPage {

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/gui/vademecum/ritual.png");

	private final ItemStack[] stackList;

	public VadeMecumPageMultiBlock(String title, ItemStack[] stack) {
		super(title, "");
		stackList = stack;
	}

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		x += offset;
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65, y, 0x000000);
		GlStateManager.color(1, 1, 1, 1);

		int layerX = x;
		int layerY = y + 50;

		gui.mc.getTextureManager().bindTexture(TEXTURE);
		gui.drawTexturedModalRect(layerX, layerY + 35, 128, 128, 0, 0, 256, 256);

		if (stackList == null || stackList.length <= 0) return;
		
		if (stackList[0] != null) gui.renderItemStack(stackList[0], layerX + 23, layerY + 90, mx, my);
		if (stackList[1] != null) gui.renderItemStack(stackList[1], layerX + 39, layerY + 82, mx, my);
		if (stackList[2] != null) gui.renderItemStack(stackList[2], layerX + 55, layerY + 74, mx, my);

		if (stackList[3] != null) gui.renderItemStack(stackList[3], layerX + 39, layerY + 98, mx, my);
		if (stackList[4] != null) gui.renderItemStack(stackList[4], layerX + 55, layerY + 90, mx, my);
		if (stackList[5] != null) gui.renderItemStack(stackList[5], layerX + 71, layerY + 82, mx, my);

		if (stackList[6] != null) gui.renderItemStack(stackList[6], layerX + 55, layerY + 106, mx, my);
		if (stackList[7] != null) gui.renderItemStack(stackList[7], layerX + 71, layerY + 98, mx, my);
		if (stackList[8] != null) gui.renderItemStack(stackList[8], layerX + 87, layerY + 90, mx, my);

		layerX = x;
		layerY = y - 5;

		if (stackList[9] == null || stackList[10] == null || stackList[11] == null || stackList[12] == null || stackList[13] == null || stackList[14] == null || stackList[15] == null || stackList[16] == null || stackList[17] == null) {
			gui.mc.getTextureManager().bindTexture(TEXTURE);
			gui.drawTexturedModalRect(layerX, layerY + 35, 128, 128, 0, 0, 256, 256);
		}

		if (stackList[9] != null) gui.renderItemStack(stackList[9], layerX + 23, layerY + 90, mx, my);
		if (stackList[10] != null) gui.renderItemStack(stackList[10], layerX + 39, layerY + 82, mx, my);
		if (stackList[11] != null) gui.renderItemStack(stackList[11], layerX + 55, layerY + 74, mx, my);

		if (stackList[12] != null) gui.renderItemStack(stackList[12], layerX + 39, layerY + 98, mx, my);
		if (stackList[13] != null) gui.renderItemStack(stackList[13], layerX + 55, layerY + 90, mx, my);
		if (stackList[14] != null) gui.renderItemStack(stackList[14], layerX + 71, layerY + 82, mx, my);

		if (stackList[15] != null) gui.renderItemStack(stackList[15], layerX + 55, layerY + 106, mx, my);
		if (stackList[16] != null) gui.renderItemStack(stackList[16], layerX + 71, layerY + 98, mx, my);
		if (stackList[17] != null) gui.renderItemStack(stackList[17], layerX + 87, layerY + 90, mx, my);

		layerX = x;
		layerY = y - 60;

		if (stackList[18] == null || stackList[19] == null || stackList[20] == null || stackList[21] == null || stackList[22] == null || stackList[23] == null || stackList[24] == null || stackList[25] == null || stackList[26] == null) {
			gui.mc.getTextureManager().bindTexture(TEXTURE);
			gui.drawTexturedModalRect(layerX, layerY + 35, 128, 128, 0, 0, 256, 256);
		}

		if (stackList[18] != null) gui.renderItemStack(stackList[18], layerX + 23, layerY + 90, mx, my);
		if (stackList[19] != null) gui.renderItemStack(stackList[19], layerX + 39, layerY + 82, mx, my);
		if (stackList[20] != null) gui.renderItemStack(stackList[20], layerX + 55, layerY + 74, mx, my);

		if (stackList[21] != null) gui.renderItemStack(stackList[21], layerX + 39, layerY + 98, mx, my);
		if (stackList[22] != null) gui.renderItemStack(stackList[22], layerX + 55, layerY + 90, mx, my);
		if (stackList[23] != null) gui.renderItemStack(stackList[23], layerX + 71, layerY + 82, mx, my);

		if (stackList[24] != null) gui.renderItemStack(stackList[24], layerX + 55, layerY + 106, mx, my);
		if (stackList[25] != null) gui.renderItemStack(stackList[25], layerX + 71, layerY + 98, mx, my);
		if (stackList[26] != null) gui.renderItemStack(stackList[26], layerX + 87, layerY + 90, mx, my);

	}

}
