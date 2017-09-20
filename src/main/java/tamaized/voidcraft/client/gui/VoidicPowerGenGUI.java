package tamaized.voidcraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.gui.container.VoidicPowerGenContainer;
import tamaized.voidcraft.common.machina.tileentity.TileEntityVoidicPowerGen;

public class VoidicPowerGenGUI extends GuiContainer {

	public TileEntityVoidicPowerGen te;

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/gui/voidgen.png");

	public VoidicPowerGenGUI(InventoryPlayer inventoryPlayer, TileEntityVoidicPowerGen tileEntity) {
		super(new VoidicPowerGenContainer(inventoryPlayer, tileEntity));
		te = tileEntity;
		xSize = 347;
		ySize = 320;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void updateScreen() {

		{
			float scale = 50;
			int k = (int) (((float) te.getFluidAmount() / (float) te.getMaxFluidAmount()) * scale);
			drawTexturedModalRect(guiLeft + 93, guiTop + 134 - k, 0, 470 - (k), 12, k + 1);
		}

		{
			float scale = 47;
			int k = (int) (((float) te.getPowerAmount() / (float) te.getMaxPower()) * scale);
			drawTexturedModalRect(guiLeft + 196, guiTop + 133 - k, 12, 470 - (k), 12, k + 1);
		}

		super.updateScreen();
	}

	@Override
	public void actionPerformed(GuiButton button) {

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		String text = I18n.format("voidcraft.gui.gen.title");
		fontRenderer.drawString(text, xSize / 2 - fontRenderer.getStringWidth(text) / 2, ySize - 260, 0xAAAAAA);
		text = I18n.format("voidcraft.gui.misc.fluid") + ":";
		fontRenderer.drawString(text, (xSize / 2 - fontRenderer.getStringWidth(text) / 2) - 100, ySize / 2 - 65, 0x7700FF);
		text = te.getFluidAmount() + "/";
		fontRenderer.drawString(text, (xSize / 2 - fontRenderer.getStringWidth(text)) - 85, ySize / 2 - 55, 0x7700FF);
		text = "" + te.getMaxFluidAmount();
		fontRenderer.drawString(text, (xSize / 2 - fontRenderer.getStringWidth(text)) - 85, ySize / 2 - 45, 0x7700FF);
		text = I18n.format("voidcraft.gui.misc.power") + ":";
		fontRenderer.drawString(text, (xSize / 2 - fontRenderer.getStringWidth(text) / 2) + 70, ySize / 2 - 65, 0xFF0000);
		text = te.getPowerAmount() + "/";
		fontRenderer.drawString(text, (xSize / 2) + 38, ySize / 2 - 55, 0xFF0000);
		text = "" + te.getMaxPower();
		fontRenderer.drawString(text, (xSize / 2) + 38, ySize / 2 - 45, 0xFF0000);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GlStateManager.pushMatrix();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		drawTexturedModalRect(guiLeft + 78, guiTop + 66, 0, 0, xSize / 2, ySize / 2);
		updateScreen();
		GlStateManager.popMatrix();
	}
}
