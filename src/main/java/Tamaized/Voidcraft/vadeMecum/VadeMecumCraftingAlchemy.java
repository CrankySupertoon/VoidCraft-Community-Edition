package Tamaized.Voidcraft.vadeMecum;

import Tamaized.TamModized.helper.TranslateHelper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumCraftingAlchemy implements IVadeMecumCrafting {

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/gui/vademecum/crafting_alchemy.png");

	private final String title;
	private final ItemStack[] input;
	private final ItemStack output;

	public VadeMecumCraftingAlchemy(String title, ItemStack output) {
		this.title = TranslateHelper.translate(title);
		input = VoidCraft.teRecipes.alchemy.getInput(output);
		this.output = output;
	}

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65, y, 0x000000);
		GlStateManager.color(1, 1, 1, 1);
		gui.mc.getTextureManager().bindTexture(TEXTURE);
		gui.drawTexturedModalRect(x, y + 35, 128, 128, 0, 0, 256, 256);

		if (input[0] != null) gui.renderItemStack(input[0], x + 10, y + 40, mx, my);
		if (input[1] != null) gui.renderItemStack(input[1], x + 55, y + 40, mx, my);
		if (input[2] != null) gui.renderItemStack(input[2], x + 100, y + 40, mx, my);

		if (input[3] != null) gui.renderItemStack(input[3], x + 10, y + 140, mx, my);
		if (input[4] != null) gui.renderItemStack(input[4], x + 55, y + 142, mx, my);
		if (input[5] != null) gui.renderItemStack(input[5], x + 106, y + 138, mx, my);

		gui.renderItemStack(output, x + 56, y + 88, mx, my);
	}

}
