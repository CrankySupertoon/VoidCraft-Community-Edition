package tamaized.voidcraft.common.potion;

import tamaized.voidcraft.VoidCraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionSheathe extends Potion {
	
	public static enum Type {
		Fire, Frost, Lit, Acid, Void
	}

	private final ResourceLocation iconTexture;
	private final Type type;
	
	public PotionSheathe(String name, Type type) {
		super(false, 0x000000);
		iconTexture = new ResourceLocation(VoidCraft.modid, "textures/potions/"+name+".png");
		this.type = type;
		setRegistryName(VoidCraft.modid, name);
		setPotionName("effect." + getRegistryName().toString());
	}

	@Override
	public boolean isInstant() {
		return false;
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		return true;
	}

	@Override
	public void performEffect(EntityLivingBase entityLivingBaseIn, int p_76394_2_) {
	}

	@Override
	public void applyAttributesModifiersToEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
		super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc) {
		mc.getTextureManager().bindTexture(iconTexture);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		net.minecraft.client.gui.Gui.drawModalRectWithCustomSizedTexture(x + 7, y + 8, 0, 0, 16, 16, 16, 16);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderHUDEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc, float alpha) {
		mc.getTextureManager().bindTexture(iconTexture);
		net.minecraft.client.gui.Gui.drawModalRectWithCustomSizedTexture(x + 4, y + 4, 0, 0, 16, 16, 16, 16);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
	}

}