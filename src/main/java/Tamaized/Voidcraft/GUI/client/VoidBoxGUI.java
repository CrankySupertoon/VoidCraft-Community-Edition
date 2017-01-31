package Tamaized.Voidcraft.GUI.client;

import java.io.DataOutputStream;

import org.lwjgl.opengl.GL11;

import Tamaized.TamModized.helper.PacketHelper;
import Tamaized.TamModized.helper.PacketHelper.PacketWrapper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.server.VoidBoxContainer;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;
import Tamaized.Voidcraft.network.ServerPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;

public class VoidBoxGUI extends GuiContainer {

	public TileEntityVoidBox voidBox;

	private GuiButton BtnPlay;
	private GuiButton BtnStop;
	private GuiButton BtnLoop;
	private GuiButton BtnDebug;

	private static final int BUTTON_PLAY = 0;
	private static final int BUTTON_STOP = 1;
	private static final int BUTTON_LOOP = 2;
	private static final int BUTTON_AUTO = 3;
	private static final int BUTTON_DEBUG = 4;

	private int CurrColor = 0;

	private static final ResourceLocation daTexture = new ResourceLocation(VoidCraft.modid, "textures/gui/voidBox.png");

	public VoidBoxGUI(InventoryPlayer inventoryPlayer, TileEntityVoidBox tileEntity) {
		super(new VoidBoxContainer(inventoryPlayer, tileEntity));

		this.voidBox = tileEntity;

		this.xSize = 347;
		this.ySize = 320;
	}

	@Override
	public void initGui() {
		super.initGui();

		buttonList.clear();
		buttonList.add(BtnPlay = new GuiButton(BUTTON_PLAY, guiLeft + 200, guiTop + 113, 33, 20, "Play"));
		buttonList.add(BtnStop = new GuiButton(BUTTON_STOP, guiLeft + 100, guiTop + 113, 33, 20, "Stop"));
		buttonList.add(BtnLoop = new GuiButton(BUTTON_LOOP, guiLeft + 240, guiTop + 113, 33, 20, "Loop"));
		buttonList.add(BtnLoop = new GuiButton(BUTTON_AUTO, guiLeft + 25, guiTop + 113, 66, 20, "Auto Insert"));
	}

	@Override
	public void updateScreen() {
		super.updateScreen();

		BtnPlay.enabled = !voidBox.getStackInSlot(voidBox.SLOT_NEXT).isEmpty() && ((voidBox.getStackInSlot(voidBox.SLOT_NEXT).getItem() instanceof ItemRecord) && voidBox.getStackInSlot(voidBox.SLOT_CURRENT).isEmpty());
		BtnStop.enabled = voidBox.isPlaying();

		if (CurrColor > 102) CurrColor = 15;

		if (voidBox.isPlaying()) CurrColor++;
	}

	@Override
	public void actionPerformed(GuiButton button) {
		int pktType;
		int xcoord = voidBox.getPos().getX();
		int ycoord = voidBox.getPos().getY();
		int zcoord = voidBox.getPos().getZ();
		switch (button.id) {
			case BUTTON_PLAY:
				sendPacket(ServerPacketHandler.PacketType.VOIDBOX_PLAY);
				break;
			case BUTTON_STOP:
				sendPacket(ServerPacketHandler.PacketType.VOIDBOX_STOP);
				break;
			case BUTTON_LOOP:
				sendPacket(ServerPacketHandler.PacketType.VOIDBOX_LOOP);
				break;
			case BUTTON_AUTO:
				sendPacket(ServerPacketHandler.PacketType.VOIDBOX_AUTO);
				break;
			default:
				break;
		}
	}

	private void sendPacket(ServerPacketHandler.PacketType type) {
		int xcoord = voidBox.getPos().getX();
		int ycoord = voidBox.getPos().getY();
		int zcoord = voidBox.getPos().getZ();
		try {
			PacketWrapper packet = PacketHelper.createPacket(VoidCraft.channel, VoidCraft.networkChannelName, ServerPacketHandler.getPacketTypeID(type));
			DataOutputStream stream = packet.getStream();
			stream.writeInt(xcoord);
			stream.writeInt(ycoord);
			stream.writeInt(zcoord);
			packet.sendPacketToServer();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {

		String name = "Void Infused Box";

		this.fontRendererObj.drawString("Void Music Box", this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, this.ySize - 260, 0x7700FF);
		this.fontRendererObj.drawString("Currently Playing:", (this.xSize / 12 - this.fontRendererObj.getStringWidth(name) / 12) - 5, this.ySize - 240, 0xFF0000);

		if (voidBox.isPlaying() && !voidBox.getStackInSlot(0).isEmpty()) {
			int hexacolor = 0x000000;

			if (CurrColor == 0) hexacolor = 0x000000;
			else if (CurrColor == 1) hexacolor = 0x110000;
			else if (CurrColor == 2) hexacolor = 0x220000;
			else if (CurrColor == 3) hexacolor = 0x330000;
			else if (CurrColor == 4) hexacolor = 0x440000;
			else if (CurrColor == 5) hexacolor = 0x550000;
			else if (CurrColor == 6) hexacolor = 0x660000;
			else if (CurrColor == 7) hexacolor = 0x770000;
			else if (CurrColor == 8) hexacolor = 0x880000;
			else if (CurrColor == 9) hexacolor = 0x990000;
			else if (CurrColor == 10) hexacolor = 0xAA0000;
			else if (CurrColor == 11) hexacolor = 0xBB0000;
			else if (CurrColor == 12) hexacolor = 0xCC0000;
			else if (CurrColor == 13) hexacolor = 0xDD0000;
			else if (CurrColor == 14) hexacolor = 0xEE0000;
			else if (CurrColor == 15) hexacolor = 0xFF0000;
			else if (CurrColor == 16) hexacolor = 0xFF1100;
			else if (CurrColor == 17) hexacolor = 0xFF2200;
			else if (CurrColor == 18) hexacolor = 0xFF3300;
			else if (CurrColor == 19) hexacolor = 0xFF4400;
			else if (CurrColor == 20) hexacolor = 0xFF5500;
			else if (CurrColor == 21) hexacolor = 0xFF6600;
			else if (CurrColor == 22) hexacolor = 0xFF7700;
			else if (CurrColor == 23) hexacolor = 0xFF8800;
			else if (CurrColor == 24) hexacolor = 0xFF9900;
			else if (CurrColor == 25) hexacolor = 0xFFAA00;
			else if (CurrColor == 26) hexacolor = 0xFFBB00;
			else if (CurrColor == 27) hexacolor = 0xFFCC00;
			else if (CurrColor == 28) hexacolor = 0xFFDD00;
			else if (CurrColor == 29) hexacolor = 0xFFEE00;
			else if (CurrColor == 30) hexacolor = 0xEEFF00;
			else if (CurrColor == 31) hexacolor = 0xDDFF00;
			else if (CurrColor == 32) hexacolor = 0xCCFF00;
			else if (CurrColor == 33) hexacolor = 0xBBFF00;
			else if (CurrColor == 34) hexacolor = 0xAAFF00;
			else if (CurrColor == 35) hexacolor = 0x99FF00;
			else if (CurrColor == 36) hexacolor = 0x88FF00;
			else if (CurrColor == 37) hexacolor = 0x77FF00;
			else if (CurrColor == 38) hexacolor = 0x66FF00;
			else if (CurrColor == 39) hexacolor = 0x55FF00;
			else if (CurrColor == 40) hexacolor = 0x44FF00;
			else if (CurrColor == 41) hexacolor = 0x33FF00;
			else if (CurrColor == 42) hexacolor = 0x22FF00;
			else if (CurrColor == 43) hexacolor = 0x11FF00;
			else if (CurrColor == 44) hexacolor = 0x00FF00;
			else if (CurrColor == 45) hexacolor = 0x00FF11;
			else if (CurrColor == 46) hexacolor = 0x00FF22;
			else if (CurrColor == 47) hexacolor = 0x00FF33;
			else if (CurrColor == 48) hexacolor = 0x00FF44;
			else if (CurrColor == 49) hexacolor = 0x00FF55;
			else if (CurrColor == 50) hexacolor = 0x00FF66;
			else if (CurrColor == 51) hexacolor = 0x00FF77;
			else if (CurrColor == 52) hexacolor = 0x00FF88;
			else if (CurrColor == 53) hexacolor = 0x00FF99;
			else if (CurrColor == 54) hexacolor = 0x00FFAA;
			else if (CurrColor == 55) hexacolor = 0x00FFBB;
			else if (CurrColor == 56) hexacolor = 0x00FFCC;
			else if (CurrColor == 57) hexacolor = 0x00FFDD;
			else if (CurrColor == 58) hexacolor = 0x00FFEE;
			else if (CurrColor == 59) hexacolor = 0x00FFFF;
			else if (CurrColor == 60) hexacolor = 0x00EEFF;
			else if (CurrColor == 61) hexacolor = 0x00DDFF;
			else if (CurrColor == 62) hexacolor = 0x00CCFF;
			else if (CurrColor == 63) hexacolor = 0x00BBFF;
			else if (CurrColor == 64) hexacolor = 0x00AAFF;
			else if (CurrColor == 65) hexacolor = 0x0099FF;
			else if (CurrColor == 66) hexacolor = 0x0088FF;
			else if (CurrColor == 67) hexacolor = 0x0077FF;
			else if (CurrColor == 68) hexacolor = 0x0066FF;
			else if (CurrColor == 69) hexacolor = 0x0055FF;
			else if (CurrColor == 70) hexacolor = 0x0044FF;
			else if (CurrColor == 71) hexacolor = 0x0033FF;
			else if (CurrColor == 72) hexacolor = 0x0022FF;
			else if (CurrColor == 73) hexacolor = 0x0011FF;
			else if (CurrColor == 74) hexacolor = 0x0000FF;
			else if (CurrColor == 75) hexacolor = 0x1100FF;
			else if (CurrColor == 76) hexacolor = 0x2200FF;
			else if (CurrColor == 77) hexacolor = 0x3300FF;
			else if (CurrColor == 78) hexacolor = 0x4400FF;
			else if (CurrColor == 79) hexacolor = 0x5500FF;
			else if (CurrColor == 80) hexacolor = 0x6600FF;
			else if (CurrColor == 81) hexacolor = 0x7700FF;
			else if (CurrColor == 82) hexacolor = 0x8800FF;
			else if (CurrColor == 83) hexacolor = 0x9900FF;
			else if (CurrColor == 84) hexacolor = 0xAA00FF;
			else if (CurrColor == 85) hexacolor = 0xBB00FF;
			else if (CurrColor == 86) hexacolor = 0xCC00FF;
			else if (CurrColor == 87) hexacolor = 0xDD00FF;
			else if (CurrColor == 88) hexacolor = 0xEE00FF;
			else if (CurrColor == 89) hexacolor = 0xFF00FF;
			else if (CurrColor == 90) hexacolor = 0xFF00EE;
			else if (CurrColor == 91) hexacolor = 0xFF00DD;
			else if (CurrColor == 92) hexacolor = 0xFF00CC;
			else if (CurrColor == 93) hexacolor = 0xFF00BB;
			else if (CurrColor == 94) hexacolor = 0xFF00AA;
			else if (CurrColor == 95) hexacolor = 0xFF0099;
			else if (CurrColor == 96) hexacolor = 0xFF0088;
			else if (CurrColor == 97) hexacolor = 0xFF0077;
			else if (CurrColor == 98) hexacolor = 0xFF0066;
			else if (CurrColor == 99) hexacolor = 0xFF0055;
			else if (CurrColor == 100) hexacolor = 0xFF0044;
			else if (CurrColor == 101) hexacolor = 0xFF0033;
			else if (CurrColor == 102) hexacolor = 0xFF0022;
			else if (CurrColor == 103) hexacolor = 0xFF0011;

			fontRendererObj.drawString(!voidBox.getStackInSlot(0).isEmpty() ? ((ItemRecord) voidBox.getStackInSlot(0).getItem()).getRecordNameLocal() : "", (xSize / 12) - 13 - (fontRendererObj.getStringWidth(name) / 12) + 102, (ySize / 12) + 54, hexacolor);
		}

		if (voidBox.getLoopState()) fontRendererObj.drawString("Loop: On", (xSize / 12) - (fontRendererObj.getStringWidth(name) / 12) + 220, ySize - 220, 0x00FF00);
		else fontRendererObj.drawString("Loop: Off", (xSize / 12) - (fontRendererObj.getStringWidth(name) / 12) + 220, ySize - 220, 0xFF0000);

		if (voidBox.getAutoState()) fontRendererObj.drawString("Auto: On", (xSize / 12) - (fontRendererObj.getStringWidth(name) / 12) + 7, ySize - 180, 0x00FF00);
		else fontRendererObj.drawString("Auto: Off", (xSize / 12) - (fontRendererObj.getStringWidth(name) / 12) + 7, ySize - 180, 0xFF0000);

		if (voidBox.isPlaying()) fontRendererObj.drawString(getTimeInMinutes(voidBox.getSongLength() - voidBox.getSongTimeLeft()) + "/" + getTimeInMinutes(voidBox.getSongLength()), (xSize / 12) + (fontRendererObj.getStringWidth(name) / 12), ySize - 220, 0xFFFF00);
	}

	private String getTimeInMinutes(int t) {
		t = t / 20;
		int s = t % 60;
		int m = (t / 60) % 60;
		return m + ":" + (String.valueOf(s).length() == 1 ? "0" + s : s);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1F, 1F, 1F, 1F);

		Minecraft.getMinecraft().getTextureManager().bindTexture(daTexture);
		drawTexturedModalRect(guiLeft + 78, guiTop + 99, 0, 0, xSize / 2, ySize / 2);
		// buttonList.add(BtnDebug = new GuiButton(BUTTON_DEBUG, guiLeft + 240, guiTop + 136, 33, 20, "DEBUG"));

		this.updateScreen();
	}

}
