package Tamaized.Voidcraft.vadeMecum;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumCraftingNormal implements IVadeMecumCrafting {

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/gui/VadeMecum/Crafting_Normal.png");

	private final String title;
	private final ItemStack[] input;
	private final ItemStack output;

	public VadeMecumCraftingNormal(String title, ItemStack[] input, ItemStack output) {
		this.title = ("" + I18n.format(title, new Object[0])).trim();
		this.input = input;
		this.output = output;
	}

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65, y, 0x000000);
		GlStateManager.color(1, 1, 1, 1);
		gui.mc.getTextureManager().bindTexture(TEXTURE);
		gui.drawTexturedModalRect(x, y + 35, 128, 128, 0, 0, 256, 256);

		if (!input[0].isEmpty()) gui.renderItemStack(input[0], x + 15, y + 50, mx, my);
		if (!input[1].isEmpty()) gui.renderItemStack(input[1], x + 55, y + 50, mx, my);
		if (!input[2].isEmpty()) gui.renderItemStack(input[2], x + 95, y + 50, mx, my);

		if (!input[3].isEmpty()) gui.renderItemStack(input[3], x + 15, y + 90, mx, my);
		if (!input[4].isEmpty()) gui.renderItemStack(input[4], x + 55, y + 90, mx, my);
		if (!input[5].isEmpty()) gui.renderItemStack(input[5], x + 95, y + 90, mx, my);

		if (!input[6].isEmpty()) gui.renderItemStack(input[6], x + 15, y + 130, mx, my);
		if (!input[7].isEmpty()) gui.renderItemStack(input[7], x + 55, y + 130, mx, my);
		if (!input[8].isEmpty()) gui.renderItemStack(input[8], x + 95, y + 130, mx, my);

		gui.renderItemStack(output, x + 55, y + 20, mx, my);
	}

}
