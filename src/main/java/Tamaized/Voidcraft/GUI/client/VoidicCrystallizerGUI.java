package Tamaized.Voidcraft.GUI.client;

import Tamaized.TamModized.helper.TranslateHelper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.server.VoidicCrystallizerContainer;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicCrystallizer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class VoidicCrystallizerGUI extends GuiContainer {

	public static int gleft;
	public static int gtop;

	public TileEntityVoidicCrystallizer te;

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/gui/crystallizer.png");

	public VoidicCrystallizerGUI(InventoryPlayer inventoryPlayer, TileEntityVoidicCrystallizer tileEntity) {
		super(new VoidicCrystallizerContainer(inventoryPlayer, tileEntity));
		te = tileEntity;
		xSize = 347;
		ySize = 320;
		gleft = guiLeft;
		gtop = guiTop;
	}

	@Override
	public void updateScreen() {

		{
			float scale = 47;
			int k = (int) (((float) te.getFluidAmount() / (float) te.getMaxFluidAmount()) * scale);
			drawTexturedModalRect(guiLeft + 196, guiTop + 132 - k, 0, 498 - (k), 12, k + 1);
		}

		{
			float scale = 46;
			int k = (int) (((float) te.getPowerAmount() / (float) te.getMaxPower()) * scale);
			drawTexturedModalRect(guiLeft + 124, guiTop + 131 - k, 12, 498 - (k), 12, k + 1);
		}

		super.updateScreen();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		String text = TranslateHelper.translate("voidcraft.gui.crystallizer.title");
		fontRendererObj.drawString(text, xSize / 2 - fontRendererObj.getStringWidth(text) / 2, ySize - 260, 0xAAAAAA);

		text = TranslateHelper.translate("voidcraft.gui.misc.fluid") + ":";
		fontRendererObj.drawString(text, (xSize) - 132, ySize - 230, 0x7700FF);
		text = te.getFluidAmount() + "";
		fontRendererObj.drawString(text, (xSize) - 132, ySize - 220, 0x7700FF);
		text = "/" + te.getMaxFluidAmount() + "mb";
		fontRendererObj.drawString(text, (xSize) - 132, ySize - 210, 0x7700FF);

		text = TranslateHelper.translate("voidcraft.gui.misc.power") + ":";
		fontRendererObj.drawString(text, (xSize - fontRendererObj.getStringWidth(text)) - 226, ySize - 230, 0xAAAAFF);
		text = te.getPowerAmount() + "";
		fontRendererObj.drawString(text, ((xSize) - (fontRendererObj.getStringWidth(text))) - 226, ySize - 220, 0xAAAAFF);
		text = "/" + te.getMaxPower();
		fontRendererObj.drawString(text, (xSize - fontRendererObj.getStringWidth(text)) - 226, ySize - 210, 0xAAAAFF);
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
