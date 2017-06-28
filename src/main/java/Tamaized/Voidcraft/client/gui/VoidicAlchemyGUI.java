package tamaized.voidcraft.client.gui;

import Tamaized.TamModized.helper.TranslateHelper;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.gui.container.VoidicAlchemyContainer;
import tamaized.voidcraft.common.machina.tileentity.TileEntityVoidicAlchemy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class VoidicAlchemyGUI extends GuiContainer {

	public TileEntityVoidicAlchemy te;

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/gui/voidicAlchemy.png");

	public VoidicAlchemyGUI(InventoryPlayer inventoryPlayer, TileEntityVoidicAlchemy tileEntity) {
		super(new VoidicAlchemyContainer(inventoryPlayer, tileEntity));

		this.te = tileEntity;

		this.xSize = 347;
		this.ySize = 320;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void updateScreen() {

		{
			float scale = 47;
			int k = (int) (((float) te.getPowerAmount() / (float) te.getMaxPower()) * scale);
			drawTexturedModalRect(guiLeft + 124, guiTop + 130 - k, 12, 470 - (k), 12, k + 1);
		}

		{
			float scale = 11;
			int k = (int) (((float) te.cookingTick / (float) te.finishTick) * scale);
			drawTexturedModalRect(guiLeft + 182, guiTop + 88 - k, 24, 432 - k, 4, k);
		}

		super.updateScreen();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		String text = TranslateHelper.translate("voidcraft.gui.alchemy.title");
		this.fontRenderer.drawString(text, this.xSize / 2 - this.fontRenderer.getStringWidth(text) / 2, this.ySize - 260, 0xAAAAAA);
		text = TranslateHelper.translate("voidcraft.gui.misc.power")+":";
		this.fontRenderer.drawString(text, (this.xSize / 2 - this.fontRenderer.getStringWidth(text) / 1) - 60, this.ySize / 2 - 65, 0xFF0000);
		text = te.getPowerAmount() + "/";
		this.fontRenderer.drawString(text, (this.xSize / 2 - this.fontRenderer.getStringWidth(text) / 1) - 60, this.ySize / 2 - 55, 0xFF0000);
		text = "" + te.getMaxPower();
		this.fontRenderer.drawString(text, (this.xSize / 2 - this.fontRenderer.getStringWidth(text) / 1) - 60, this.ySize / 2 - 45, 0xFF0000);
		text = TranslateHelper.translate("voidcraft.gui.misc.owner")+":";
		this.fontRenderer.drawString(text, (xSize / 2) + 60, this.ySize / 2 - 65, 0xFF0000);
		text = te.getOwnerName().isEmpty() ? TranslateHelper.translate("voidcraft.gui.misc.none") : te.getOwnerName();
		this.fontRenderer.drawString(text, (xSize / 2) + 60, this.ySize / 2 - 55, 0xFF0000);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GlStateManager.pushMatrix();
		{
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
			drawTexturedModalRect(guiLeft + 78, guiTop + 66, 0, 0, xSize / 2, ySize / 2);
			this.updateScreen();
		}
		GlStateManager.popMatrix();

	}

}
