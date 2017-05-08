package Tamaized.Voidcraft.vadeMecum;

import Tamaized.TamModized.helper.TranslateHelper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumCraftingBlastFurnace implements IVadeMecumCrafting {

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/gui/vademecum/crafting_furnace.png");

	private final String title;
	private final ItemStack input[];
	private final ItemStack output;

	public VadeMecumCraftingBlastFurnace(String title, ItemStack output) {
		this.title = TranslateHelper.translate(title);
		input = VoidCraft.teRecipes.blastFurnace.getInput(output);
		this.output = output;
	}

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65, y, 0x000000);
		GlStateManager.color(1, 1, 1, 1);
		gui.mc.getTextureManager().bindTexture(TEXTURE);
		gui.drawTexturedModalRect(x, y + 35, 128, 128, 0, 0, 256, 256);

		gui.renderItemStack(input[0], x + 19, y + 90, mx, my);
		gui.renderItemStack(input[1], x + 41, y + 90, mx, my);

		gui.renderItemStack(output, x + 92, y + 90, mx, my);
	}

}
