package Tamaized.Voidcraft.GUI.client;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.server.VadeMecumSpellsContainer;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability.Category;
import Tamaized.Voidcraft.handlers.VadeMecumPacketHandler;
import Tamaized.Voidcraft.handlers.VadeMecumWordsOfPower;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class VadeMecumSpellsGUI extends GuiContainer {

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/gui/generic.png");

	private final IVadeMecumCapability capability;

	private ItemStack renderStackHover = null;

	private static final int BUTTON_CLOSE = 0;
	private static final int BUTTON_BACK = 1;
	private static final int BUTTON_SPELL = 2;

	public VadeMecumSpellsGUI(InventoryPlayer inventory, IVadeMecumCapability cap) {
		super(new VadeMecumSpellsContainer(inventory, cap));
		capability = cap;
	}

	@Override
	public void initGui() {
		super.initGui();
		
		xSize = 200;
		ySize = 90;
		guiLeft = (width / 2) - (xSize / 2) - (24);
		guiTop = height - (ySize) - 5;

		if (inventorySlots instanceof VadeMecumSpellsContainer) {
			((VadeMecumSpellsContainer) inventorySlots).initSlots(guiLeft, guiTop);
		}

		buttonList.add(new GuiButton(BUTTON_BACK, 5, height - 50, 80, 20, "Back"));
		buttonList.add(new GuiButton(BUTTON_CLOSE, 5, height - 25, 80, 20, "Close"));

		int xLoc = 25;
		int yLoc = 28;

		int index = 0;
		for (Category spell : capability.getAvailableActivePowers()) {
			buttonList.add(new SpellButton(capability, BUTTON_SPELL, xLoc + (135 * ((int) Math.floor(index / 5))), yLoc + (25 * (index % 5)), spell));
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
				case BUTTON_SPELL:
					if (button instanceof SpellButton) sendPacket(((SpellButton) button).getSpell());
					break;
				default:
					break;
			}
		}
	}

	private void sendPacket(IVadeMecumCapability.Category spell) {
		if (IVadeMecumCapability.isActivePower(spell)) VadeMecumPacketHandler.ClientToServerRequest(VadeMecumPacketHandler.RequestType.ACTIVE_SET, IVadeMecumCapability.getCategoryID(spell));
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void onGuiClosed() {

	}

	@Override
	public void updateScreen() {

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		int i = this.guiLeft;
		int j = this.guiTop;
		drawCenteredString(fontRendererObj, "Words of Power", width / 2, 15, 16777215);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
		int xLoc = 0;
		int yLoc = 21;
		int index = 0;
		for (Category spell : capability.getAvailableActivePowers()) {
			drawTexturedModalRect(xLoc + (135 * ((int) Math.floor(index / 5))), yLoc + (25 * (index % 5)), 0, ySize, 32, 32);
			index++;
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		if (renderStackHover != null) {
			renderToolTip(renderStackHover, mouseX, mouseY);
			renderStackHover = null;
		}
	}

	public void renderItemStack(ItemStack stack, int x, int y, int mx, int my) {
		if (itemRender != null) {
			RenderHelper.enableGUIStandardItemLighting();
			GlStateManager.enableDepth();
			itemRender.renderItemIntoGUI(stack, x, y);
			// drawCenteredString(fontRendererObj, ""+stack.stackSize, x, y, 0xFFFFFF);
			GlStateManager.disableDepth();
			if (stack.stackSize > 1) drawString(fontRendererObj, "" + stack.stackSize, x + 11 - (6 * (Integer.valueOf(stack.stackSize).toString().length() - 1)), y + 9, 0xFFFFFF);
			GlStateManager.enableDepth();
			if (mx >= x && mx <= x + 16 && my >= y && my <= y + 16) renderStackHover = stack;
			RenderHelper.disableStandardItemLighting();
		}
	}

	public class SpellButton extends GuiButton {

		private final IVadeMecumCapability.Category spell;
		private final IVadeMecumCapability data;

		public SpellButton(IVadeMecumCapability cap, int buttonId, int x, int y, IVadeMecumCapability.Category theSpell) {
			super(buttonId, x, y, 110, 18, "");
			data = cap;
			spell = theSpell;
		}

		public IVadeMecumCapability.Category getSpell() {
			return spell;
		}

		public boolean isSet() {
			return data != null && data.getCurrentActive() == spell;
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
					j = 0xFF00FF00;
				} else if (hovered) {
					j = 0xFFFFFFFF;
				}

				drawRect(xPosition, yPosition, xPosition + width, yPosition + height, j);
				GlStateManager.pushMatrix();
				ItemStack stack = VadeMecumWordsOfPower.getCategoryData(spell).getStack();
				if (stack != null) renderItemStack(stack, xPosition, yPosition + (height / 2) - (mc.fontRendererObj.FONT_HEIGHT / 2) - 4, mouseX, mouseY);
				fontrenderer.drawString(VadeMecumWordsOfPower.getCategoryData(spell).getName().substring(6), xPosition + 18, yPosition + (height / 2) - (mc.fontRendererObj.FONT_HEIGHT / 2), 0x7700FF);
				GlStateManager.popMatrix();
			}
		}

	}

	public int getYSize() {
		return ySize;
	}

}
