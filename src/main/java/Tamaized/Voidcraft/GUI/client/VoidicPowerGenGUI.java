package Tamaized.Voidcraft.GUI.client;

import Tamaized.TamModized.helper.TranslateHelper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.server.VoidicPowerGenContainer;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicPowerGen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

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

	public void actionPerformed(GuiButton button) {

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		String text = TranslateHelper.translate("voidcraft.gui.gen.title");
		fontRendererObj.drawString(text, xSize / 2 - fontRendererObj.getStringWidth(text) / 2, ySize - 260, 0xAAAAAA);
		text = TranslateHelper.translate("voidcraft.gui.misc.fluid") + ":";
		fontRendererObj.drawString(text, (xSize / 2 - fontRendererObj.getStringWidth(text) / 2) - 100, ySize / 2 - 65, 0x7700FF);
		text = te.getFluidAmount() + "/";
		fontRendererObj.drawString(text, (xSize / 2 - fontRendererObj.getStringWidth(text) / 1) - 85, ySize / 2 - 55, 0x7700FF);
		text = "" + te.getMaxFluidAmount();
		fontRendererObj.drawString(text, (xSize / 2 - fontRendererObj.getStringWidth(text) / 1) - 85, ySize / 2 - 45, 0x7700FF);
		text = TranslateHelper.translate("voidcraft.gui.misc.power") + ":";
		fontRendererObj.drawString(text, (xSize / 2 - fontRendererObj.getStringWidth(text) / 2) + 70, ySize / 2 - 65, 0xFF0000);
		text = te.getPowerAmount() + "/";
		fontRendererObj.drawString(text, (xSize / 2) + 38, ySize / 2 - 55, 0xFF0000);
		text = "" + te.getMaxPower();
		fontRendererObj.drawString(text, (xSize / 2) + 38, ySize / 2 - 45, 0xFF0000);
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
