package Tamaized.Voidcraft.GUI.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.server.VoidInfuserContainer;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidInfuser;

@SideOnly(Side.CLIENT)
public class VoidInfuserGUI extends GuiContainer {
	
	public static int gleft;
	public static int gtop;
	
	public TileEntityVoidInfuser te;
	
	private static final ResourceLocation daTexture = new ResourceLocation(VoidCraft.modid, "textures/gui/voidInfuser.png");

	public VoidInfuserGUI (InventoryPlayer inventoryPlayer, TileEntityVoidInfuser tileEntity) {
		super(new VoidInfuserContainer(inventoryPlayer, tileEntity));
		te = tileEntity;
		xSize = 347;
		ySize = 320;
		gleft = guiLeft;
		gtop = guiTop;
	}
	
	@Override
	public void updateScreen(){
		
		{
			float scale = 46;
			int k = (int) (((float)te.getFluidAmount()/(float)te.getMaxFluidAmount())*scale);
			drawTexturedModalRect(guiLeft+93, guiTop+131 - k, 0, 497-(k), 12, k+1); 
		}
		
		{
			float scale = 26;
			int k = (int) (((float)te.cookingTick/(float)te.finishTick)*scale);
			drawTexturedModalRect(guiLeft+188, guiTop+102, 0, 435, k, 15); 
		}
			
		super.updateScreen();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		String text = "Void Infuser";
		fontRendererObj.drawString(text, xSize/2 - fontRendererObj.getStringWidth(text) / 2, ySize-260, 0xAAAAAA);
		text = "Fluid:";
		fontRendererObj.drawString(text, (xSize/2 - fontRendererObj.getStringWidth(text) / 2) - 100, ySize/2 - 65, 0x7700FF);
		text = te.getFluidAmount()+"/";
		fontRendererObj.drawString(text, (xSize/2 - fontRendererObj.getStringWidth(text) / 1) - 85, ySize/2 - 55, 0x7700FF);
		text = ""+te.getMaxFluidAmount();
		fontRendererObj.drawString(text, (xSize/2 - fontRendererObj.getStringWidth(text) / 1) - 85, ySize/2 - 45, 0x7700FF);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();{
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			Minecraft.getMinecraft().getTextureManager().bindTexture(daTexture);
			drawTexturedModalRect(guiLeft+78, guiTop+66, 0, 0, xSize/2, ySize/2);
			updateScreen();
		}
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}
}
