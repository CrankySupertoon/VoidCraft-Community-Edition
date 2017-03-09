package Tamaized.Voidcraft.GUI.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.server.VoidMaceratorContainer;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;

public class VoidMaceratorGUI extends GuiContainer {

	public TileEntityVoidMacerator te;

	private static final ResourceLocation daTexture = new ResourceLocation(VoidCraft.modid, "textures/gui/voidMacerator.png");

	public VoidMaceratorGUI(InventoryPlayer inventoryPlayer, TileEntityVoidMacerator tileEntity) {
		super(new VoidMaceratorContainer(inventoryPlayer, tileEntity));

		this.te = tileEntity;

		this.xSize = 347;
		this.ySize = 320;
	}

	@Override
	public void updateScreen() {

		{
			float scale = 45;
			int k = (int) (((float) te.getPowerAmount() / (float) te.getMaxPower()) * scale);
			drawTexturedModalRect(guiLeft + 114, guiTop + 134 - k, 12, 497 - (k), 12, k);
		}

		{
			float scale = 26;
			int k = (int) (((float) te.cookingTick / (float) te.finishTick) * scale);
			drawTexturedModalRect(guiLeft + 188, guiTop + 100, 0, 435, k, 15);
		}

		super.updateScreen();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		String text = ("" + I18n.format("voidcraft.gui.macerator.title", new Object[0])).trim();
		this.fontRendererObj.drawString(text, this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 2, this.ySize - 260, 0xAAAAAA);
		text = ("" + I18n.format("voidcraft.gui.misc.power", new Object[0])).trim()+":";
		this.fontRendererObj.drawString(text, (this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 1) - 60, this.ySize / 2 - 65, 0xFF0000);
		text = te.getPowerAmount() + "/";
		this.fontRendererObj.drawString(text, (this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 1) - 60, this.ySize / 2 - 55, 0xFF0000);
		text = "" + te.getMaxPower();
		this.fontRendererObj.drawString(text, (this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 1) - 60, this.ySize / 2 - 45, 0xFF0000);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		{
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			Minecraft.getMinecraft().getTextureManager().bindTexture(daTexture);
			drawTexturedModalRect(guiLeft + 78, guiTop + 66, 0, 0, xSize / 2, ySize / 2);
			this.updateScreen();
		}
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();

	}

}
