package Tamaized.Voidcraft.starforge;

import Tamaized.Voidcraft.GUI.client.StarForgeGUI;
import Tamaized.Voidcraft.helper.GUIListElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StarForgeEffectEntry extends GUIListElement {

	private final StarForgeEffectRecipeList.Recipe recipe;

	public StarForgeEffectEntry(StarForgeEffectRecipeList.Recipe r) {
		super();
		recipe = r;
	}
	
	public StarForgeEffectRecipeList.Recipe getRecipe(){
		return recipe;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void draw(GuiContainer gui, Minecraft mc, int x, int y, int height, Tessellator tess) {
		if (!(gui instanceof StarForgeGUI)) return;
		StarForgeGUI starforgeGUI = (StarForgeGUI) gui;
		VertexBuffer worldr = tess.getBuffer();
		int min = gui.getGuiLeft()+(gui.getXSize()/2)+10;//gui.width - 200;
		int max = x+7;
		int slotTop = y;
		int slotBuffer = height;
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableTexture2D();
		worldr.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		// worldr.pos(min, slotTop + slotBuffer + 2, 0).tex(0, 1).color(0x80, 0x80, 0x80, 0xFF).endVertex();
		// worldr.pos(max, slotTop + slotBuffer + 2, 0).tex(1, 1).color(0x80, 0x80, 0x80, 0xFF).endVertex();
		// worldr.pos(max, slotTop - 2, 0).tex(1, 0).color(0x80, 0x80, 0x80, 0xFF).endVertex();
		// worldr.pos(min, slotTop - 2, 0).tex(0, 0).color(0x80, 0x80, 0x80, 0xFF).endVertex();
		worldr.pos(min + 1, slotTop + slotBuffer + 1, 0).tex(0, 1).color(0x00, 0x00, 0x00, 0xFF).endVertex();
		worldr.pos(max - 1, slotTop + slotBuffer + 1, 0).tex(1, 1).color(0x00, 0x00, 0x00, 0xFF).endVertex();
		worldr.pos(max - 1, slotTop - 1, 0).tex(1, 0).color(0x00, 0x00, 0x00, 0xFF).endVertex();
		worldr.pos(min + 1, slotTop - 1, 0).tex(0, 0).color(0x00, 0x00, 0x00, 0xFF).endVertex();
		tess.draw();
		GlStateManager.enableTexture2D();
		starforgeGUI.drawString(mc.fontRendererObj, recipe.getEffect().getName(), x - 160, y + 5, 0xFFFFFF);
		int i = mc.fontRendererObj.getStringWidth(recipe.getEffect().getName());
		int index = 0;
		for (ItemStack stack : recipe.getInputs()) {
			starforgeGUI.renderItemStack(stack, (x - 160) + i + 5 + (20 * index), y, starforgeGUI.mouseX, starforgeGUI.mouseY);
			index++;
		}
	}
}
