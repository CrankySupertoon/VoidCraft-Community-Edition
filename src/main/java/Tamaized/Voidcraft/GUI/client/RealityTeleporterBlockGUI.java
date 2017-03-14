package Tamaized.Voidcraft.GUI.client;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.server.RealityTeleporterBlockContainer;
import Tamaized.Voidcraft.machina.tileentity.TileEntityRealityTeleporter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RealityTeleporterBlockGUI extends GuiContainer {

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/gui/voidcharger.png");

	private final TileEntityRealityTeleporter te;

	public RealityTeleporterBlockGUI(InventoryPlayer inventoryPlayer, TileEntityRealityTeleporter host) {
		super(new RealityTeleporterBlockContainer(inventoryPlayer, host));
		te = host;
		xSize = 347;
		ySize = 320;
	}

	@Override
	public void updateScreen() {

		{
			float scale = 47;
			int k = te == null ? 0 : (int) (te.getAmountPerc() * scale);
			drawTexturedModalRect(guiLeft + 124, guiTop + 128 - k, 12, 470 - (k), 12, k + 1);
		}

		super.updateScreen();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		String text = ("" + I18n.format("voidcraft.gui.realityteleporterblock.title", new Object[0])).trim();
		this.fontRendererObj.drawString(text, this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 2, this.ySize - 260, 0xAAAAAA);
		BlockPos linkPos = te.getLink();
		if (linkPos != null) {
			text = ("" + I18n.format("voidcraft.gui.realityteleporterblock.link", new Object[0])).trim()+":";
			this.fontRendererObj.drawString(text, (this.xSize / 2) + 30, this.ySize / 2 - 70, 0xAAAAAA);
			text = "{ x: " + linkPos.getX() + ", y:" + linkPos.getY() + ", z:" + linkPos.getZ() + " }";
			this.fontRendererObj.drawString(text, (this.xSize / 2) + 30, this.ySize / 2 - 60, 0xAAAAAA);
		} else {
			text = ("" + I18n.format("voidcraft.gui.realityteleporterblock.nolink", new Object[0])).trim()+":";
			this.fontRendererObj.drawString(text, (this.xSize / 2) + 30, this.ySize / 2 - 70, 0xAAAAAA);
		}
		text = ("" + I18n.format("voidcraft.gui.misc.power", new Object[0])).trim()+":";
		this.fontRendererObj.drawString(text, (this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 1) - 55, this.ySize / 2 - 70, 0xFF0000);
		text = te == null ? "N/A" : te.getPowerAmount() + "/";
		this.fontRendererObj.drawString(text, (this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 1) - 55, this.ySize / 2 - 60, 0xFF0000);
		text = te == null ? "N/A" : te.getMaxPower() + "";
		this.fontRendererObj.drawString(text, (this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 1) - 55, this.ySize / 2 - 50, 0xFF0000);
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
