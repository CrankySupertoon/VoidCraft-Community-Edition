package Tamaized.Voidcraft.vadeMecum;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageMultiBlock extends VadeMecumPage {

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/gui/VadeMecum/Ritual.png");

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
		
		if (!stackList[0].isEmpty()) gui.renderItemStack(stackList[0], layerX + 23, layerY + 90, mx, my);
		if (!stackList[1].isEmpty()) gui.renderItemStack(stackList[1], layerX + 39, layerY + 82, mx, my);
		if (!stackList[2].isEmpty()) gui.renderItemStack(stackList[2], layerX + 55, layerY + 74, mx, my);

		if (!stackList[3].isEmpty()) gui.renderItemStack(stackList[3], layerX + 39, layerY + 98, mx, my);
		if (!stackList[4].isEmpty()) gui.renderItemStack(stackList[4], layerX + 55, layerY + 90, mx, my);
		if (!stackList[5].isEmpty()) gui.renderItemStack(stackList[5], layerX + 71, layerY + 82, mx, my);

		if (!stackList[6].isEmpty()) gui.renderItemStack(stackList[6], layerX + 55, layerY + 106, mx, my);
		if (!stackList[7].isEmpty()) gui.renderItemStack(stackList[7], layerX + 71, layerY + 98, mx, my);
		if (!stackList[8].isEmpty()) gui.renderItemStack(stackList[8], layerX + 87, layerY + 90, mx, my);

		layerX = x;
		layerY = y - 5;

		if (stackList[9].isEmpty() || stackList[10].isEmpty() || stackList[11].isEmpty() || stackList[12].isEmpty() || stackList[13].isEmpty() || stackList[14].isEmpty() || stackList[15].isEmpty() || stackList[16].isEmpty() || stackList[17].isEmpty()) {
			gui.mc.getTextureManager().bindTexture(TEXTURE);
			gui.drawTexturedModalRect(layerX, layerY + 35, 128, 128, 0, 0, 256, 256);
		}

		if (!stackList[9].isEmpty()) gui.renderItemStack(stackList[9], layerX + 23, layerY + 90, mx, my);
		if (!stackList[10].isEmpty()) gui.renderItemStack(stackList[10], layerX + 39, layerY + 82, mx, my);
		if (!stackList[11].isEmpty()) gui.renderItemStack(stackList[11], layerX + 55, layerY + 74, mx, my);

		if (!stackList[12].isEmpty()) gui.renderItemStack(stackList[12], layerX + 39, layerY + 98, mx, my);
		if (!stackList[13].isEmpty()) gui.renderItemStack(stackList[13], layerX + 55, layerY + 90, mx, my);
		if (!stackList[14].isEmpty()) gui.renderItemStack(stackList[14], layerX + 71, layerY + 82, mx, my);

		if (!stackList[15].isEmpty()) gui.renderItemStack(stackList[15], layerX + 55, layerY + 106, mx, my);
		if (!stackList[16].isEmpty()) gui.renderItemStack(stackList[16], layerX + 71, layerY + 98, mx, my);
		if (!stackList[17].isEmpty()) gui.renderItemStack(stackList[17], layerX + 87, layerY + 90, mx, my);

		layerX = x;
		layerY = y - 60;

		if (stackList[18].isEmpty() || stackList[19].isEmpty() || stackList[20].isEmpty() || stackList[21].isEmpty() || stackList[22].isEmpty() || stackList[23].isEmpty() || stackList[24].isEmpty() || stackList[25].isEmpty() || stackList[26].isEmpty()) {
			gui.mc.getTextureManager().bindTexture(TEXTURE);
			gui.drawTexturedModalRect(layerX, layerY + 35, 128, 128, 0, 0, 256, 256);
		}

		if (!stackList[18].isEmpty()) gui.renderItemStack(stackList[18], layerX + 23, layerY + 90, mx, my);
		if (!stackList[19].isEmpty()) gui.renderItemStack(stackList[19], layerX + 39, layerY + 82, mx, my);
		if (!stackList[20].isEmpty()) gui.renderItemStack(stackList[20], layerX + 55, layerY + 74, mx, my);

		if (!stackList[21].isEmpty()) gui.renderItemStack(stackList[21], layerX + 39, layerY + 98, mx, my);
		if (!stackList[22].isEmpty()) gui.renderItemStack(stackList[22], layerX + 55, layerY + 90, mx, my);
		if (!stackList[23].isEmpty()) gui.renderItemStack(stackList[23], layerX + 71, layerY + 82, mx, my);

		if (!stackList[24].isEmpty()) gui.renderItemStack(stackList[24], layerX + 55, layerY + 106, mx, my);
		if (!stackList[25].isEmpty()) gui.renderItemStack(stackList[25], layerX + 71, layerY + 98, mx, my);
		if (!stackList[26].isEmpty()) gui.renderItemStack(stackList[26], layerX + 87, layerY + 90, mx, my);

	}

}
