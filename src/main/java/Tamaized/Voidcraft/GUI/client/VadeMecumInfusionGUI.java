package Tamaized.Voidcraft.GUI.client;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.progression.VadeMecumPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;

public class VadeMecumInfusionGUI extends GuiScreen {

	private final IVadeMecumCapability capability;

	private static final int BUTTON_CLOSE = 0;
	private static final int BUTTON_BACK = 1;
	private static final int BUTTON_ABILITY = 2;

	public VadeMecumInfusionGUI(IVadeMecumCapability cap) {
		capability = cap;
	}

	@Override
	public void initGui() {
		super.initGui();

		buttonList.add(new GuiButton(BUTTON_BACK, 5, height - 50, 80, 20, ("" + I18n.format("voidcraft.gui.misc.back", new Object[0])).trim()));
		buttonList.add(new GuiButton(BUTTON_CLOSE, 5, height - 25, 80, 20, ("" + I18n.format("voidcraft.gui.misc.close", new Object[0])).trim()));

		int xLoc = (width / 2) - 55;
		int yLoc = 28;

		int index = 0;
		for (IVadeMecumCapability.Passive passive : IVadeMecumCapability.Passive.values()) {
			if (capability.canHavePassive(passive)) buttonList.add(new PassiveButton(capability, BUTTON_ABILITY, xLoc, yLoc + (25 * index), passive));
			index++;
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
				case BUTTON_CLOSE:
					mc.displayGuiScreen((GuiScreen) null);
					break;
				case BUTTON_BACK:
					mc.displayGuiScreen(new Tamaized.Voidcraft.GUI.client.VadeMecumGUI(mc.player));
					break;
				case BUTTON_ABILITY:
					if (button instanceof PassiveButton) sendPacket(((PassiveButton) button).getSpell());
					break;
				default:
					break;
			}
		}
	}

	private void sendPacket(IVadeMecumCapability.Passive spell) {
		if (capability.hasPassive(spell) || capability.canHavePassive(spell)) VadeMecumPacketHandler.ClientToServerRequest(VadeMecumPacketHandler.RequestType.PASSIVE, IVadeMecumCapability.getPassiveID(spell));
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void onGuiClosed() {

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawCenteredString(fontRendererObj, ("" + I18n.format("voidcraft.gui.voidicInfusionControl.title", new Object[0])).trim(), width / 2, 15, 16777215);
	}

	public class PassiveButton extends GuiButton {

		private final IVadeMecumCapability.Passive passive;
		private final IVadeMecumCapability data;

		public PassiveButton(IVadeMecumCapability cap, int buttonId, int x, int y, IVadeMecumCapability.Passive thePassive) {
			super(buttonId, x, y, 110, 18, "");
			data = cap;
			passive = thePassive;
		}

		public IVadeMecumCapability.Passive getSpell() {
			return passive;
		}

		public boolean isSet() {
			return data != null && data.hasPassive(passive);
		}

		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY) {
			if (visible) {
				FontRenderer fontrenderer = mc.fontRendererObj;
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				hovered = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;
				int i = getHoverState(hovered);
				GlStateManager.enableBlend();
				GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
				GlStateManager.blendFunc(770, 771);
				// drawRect(xPosition + width / 2, yPosition, width / 2, height, 0xFFFFFFFF);
				mouseDragged(mc, mouseX, mouseY);
				int j = 0xBBFFFFFF;

				if (isSet()) {
					j = hovered ? 0xFF99FF99 : 0xFF00FF00;
				} else if (hovered) {
					j = 0xFFFFFFFF;
				}

				drawRect(xPosition, yPosition, xPosition + width, yPosition + height, j);
				GlStateManager.pushMatrix();
				fontrenderer.drawString(IVadeMecumCapability.getPassiveName(passive), xPosition + 18, yPosition + (height / 2) - (mc.fontRendererObj.FONT_HEIGHT / 2), 0x7700FF);
				GlStateManager.popMatrix();
			}
		}

	}

}
