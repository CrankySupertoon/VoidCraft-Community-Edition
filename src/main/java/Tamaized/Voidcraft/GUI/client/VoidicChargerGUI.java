package Tamaized.Voidcraft.GUI.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.server.VoidicChargerContainer;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicCharger;

public class VoidicChargerGUI extends GuiContainer {

	public TileEntityVoidicCharger te;

	private static final ResourceLocation daTexture = new ResourceLocation(VoidCraft.modid, "textures/gui/voidCharger.png");

	public VoidicChargerGUI(InventoryPlayer inventoryPlayer, TileEntityVoidicCharger tileEntity) {
		super(new VoidicChargerContainer(inventoryPlayer, tileEntity));
		this.te = tileEntity;
		this.xSize = 347;
		this.ySize = 320;
	}

	@Override
	public void updateScreen() {

		{
			float scale = 47;
			int k = (int) (((float) te.getPowerAmount() / (float) te.getMaxPower()) * scale);
			drawTexturedModalRect(guiLeft + 124, guiTop + 128 - k, 12, 470 - (k), 12, k + 1);
		}

		super.updateScreen();
	}

	public void actionPerformed(GuiButton button) {

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		String text = "Voidic Power Charger";
		this.fontRendererObj.drawString(text, this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 2, this.ySize - 260, 0xAAAAAA);
		text = "Voidic Power:";
		this.fontRendererObj.drawString(text, (this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 1) - 55, this.ySize / 2 - 70, 0xFF0000);
		text = te.getPowerAmount() + "/";
		this.fontRendererObj.drawString(text, (this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 1) - 55, this.ySize / 2 - 60, 0xFF0000);
		text = "" + te.getMaxPower();
		this.fontRendererObj.drawString(text, (this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 1) - 55, this.ySize / 2 - 50, 0xFF0000);
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
