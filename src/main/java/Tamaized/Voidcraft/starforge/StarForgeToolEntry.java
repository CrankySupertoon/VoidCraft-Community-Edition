package Tamaized.Voidcraft.starforge;

import Tamaized.Voidcraft.VoidCraft;
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

public class StarForgeToolEntry extends GUIListElement {

	private final ItemStack tool;

	public StarForgeToolEntry(ItemStack tool) throws Exception {
		super();
		this.tool = tool;
		if (tool.isEmpty() || !(tool.getItem() instanceof IStarForgeTool)) throw new Exception("ItemStack was empty or the Item did not implement IStarForgeTool");
	}
	
	public ItemStack getTool(){
		return tool;
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
		starforgeGUI.drawString(mc.fontRendererObj, tool.getDisplayName(), x - 160, y + 5, 0xFFFFFF);
		int i = mc.fontRendererObj.getStringWidth(tool.getDisplayName());
		starforgeGUI.renderItemStack(new ItemStack(VoidCraft.blocks.cosmicMaterial, 4), (x - 160) + i + 5, y, starforgeGUI.mouseX, starforgeGUI.mouseY);
		starforgeGUI.renderItemStack(new ItemStack(VoidCraft.items.quoriFragment, 1), (x - 160) + i + 5 + 20, y, starforgeGUI.mouseX, starforgeGUI.mouseY);
	}

}