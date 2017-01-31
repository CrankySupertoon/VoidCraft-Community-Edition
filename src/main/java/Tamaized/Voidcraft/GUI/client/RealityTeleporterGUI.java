package Tamaized.Voidcraft.GUI.client;

import java.io.DataOutputStream;

import Tamaized.TamModized.helper.PacketHelper;
import Tamaized.TamModized.helper.PacketHelper.PacketWrapper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.server.RealityTeleporterContainer;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicPower.IVoidicPowerCapability;
import Tamaized.Voidcraft.items.RealityTeleporter;
import Tamaized.Voidcraft.items.inventory.InventoryItem;
import Tamaized.Voidcraft.network.ServerPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RealityTeleporterGUI extends GuiContainer {

	private static final ResourceLocation daTexture = new ResourceLocation(VoidCraft.modid, "textures/gui/voidCharger.png");

	private final ItemStack parent;
	private final int slotID;
	private final InventoryItem itemInventory;
	private final IVoidicPowerCapability cap;

	private GuiButton button_clearLink;

	private static final int BUTTON_LINK_CLEAR = 0;

	public RealityTeleporterGUI(InventoryPlayer inventoryPlayer, ItemStack host) {
		super(new RealityTeleporterContainer(inventoryPlayer, host));
		parent = host;
		itemInventory = RealityTeleporter.createInventory(parent);
		cap = parent.hasCapability(CapabilityList.VOIDICPOWER, null) ? parent.getCapability(CapabilityList.VOIDICPOWER, null) : null;
		xSize = 347;
		ySize = 320;
		int temp = -2;
		for (int i = 0; i < inventoryPlayer.mainInventory.size(); i++) {
			if (ItemStack.areItemStacksEqual(parent, inventoryPlayer.mainInventory.get(i))) {
				temp = i;
				break;
			}
		}
		if (temp == -2) {
			if (ItemStack.areItemStacksEqual(parent, inventoryPlayer.offHandInventory.get(0))) temp = -1;
		}
		slotID = temp;
	}

	@Override
	public void initGui() {
		super.initGui();

		buttonList.clear();
		buttonList.add(button_clearLink = new GuiButton(BUTTON_LINK_CLEAR, guiLeft + 170, guiTop + 122, 56, 20, "Clear Link"));
	}

	@Override
	public void updateScreen() {

		button_clearLink.enabled = RealityTeleporter.hasLink(parent);

		{
			float scale = 47;
			int k = cap == null ? 0 : (int) (cap.getAmountPerc() * scale);
			drawTexturedModalRect(guiLeft + 124, guiTop + 128 - k, 12, 470 - (k), 12, k + 1);
		}

		super.updateScreen();
	}

	@Override
	public void actionPerformed(GuiButton button) {
		switch (button.id) {
			case BUTTON_LINK_CLEAR:
				sendPacket(ServerPacketHandler.PacketType.LINK_CLEAR);
				RealityTeleporter.clearLink(parent);
				break;
			default:
				break;
		}
	}

	private void sendPacket(ServerPacketHandler.PacketType type) {
		try {
			PacketWrapper packet = PacketHelper.createPacket(VoidCraft.channel, VoidCraft.networkChannelName, ServerPacketHandler.getPacketTypeID(type));
			DataOutputStream stream = packet.getStream();
			stream.writeInt(slotID);
			packet.sendPacketToServer();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		String text = "Reality Teleporter";
		fontRendererObj.drawString(text, xSize / 2 - fontRendererObj.getStringWidth(text) / 2, ySize - 260, 0xAAAAAA);
		BlockPos linkPos = RealityTeleporter.getLink(parent);
		if (linkPos != null) {
			text = "Linked to:";
			fontRendererObj.drawString(text, (xSize / 2) + 30, ySize / 2 - 70, 0xAAAAAA);
			text = "{ x: " + linkPos.getX() + ", y:" + linkPos.getY() + ", z:" + linkPos.getZ() + " }";
			fontRendererObj.drawString(text, (xSize / 2) + 30, ySize / 2 - 60, 0xAAAAAA);
		} else {
			text = "Not Linked";
			fontRendererObj.drawString(text, (xSize / 2) + 30, ySize / 2 - 70, 0xAAAAAA);
		}
		text = "Voidic Power:";
		fontRendererObj.drawString(text, (xSize / 2 - fontRendererObj.getStringWidth(text) / 1) - 55, ySize / 2 - 70, 0xFF0000);
		text = cap == null ? "N/A" : cap.getCurrentPower() + "/";
		fontRendererObj.drawString(text, (xSize / 2 - fontRendererObj.getStringWidth(text) / 1) - 55, ySize / 2 - 60, 0xFF0000);
		text = cap == null ? "N/A" : cap.getMaxPower() + "";
		fontRendererObj.drawString(text, (xSize / 2 - fontRendererObj.getStringWidth(text) / 1) - 55, ySize / 2 - 50, 0xFF0000);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		{
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			Minecraft.getMinecraft().getTextureManager().bindTexture(daTexture);
			drawTexturedModalRect(guiLeft + 78, guiTop + 66, 0, 0, xSize / 2, ySize / 2);
			updateScreen();
		}
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}

}
