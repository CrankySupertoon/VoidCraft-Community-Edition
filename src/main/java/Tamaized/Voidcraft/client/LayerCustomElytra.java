package Tamaized.Voidcraft.client;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.armor.ArmorCustomElytra;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerCustomElytra implements LayerRenderer<AbstractClientPlayer> {
	/** The basic Elytra texture. */
	private static final ResourceLocation TEXTURE_ELYTRA = new ResourceLocation(VoidCraft.modid, "textures/models/armor/elytra.png");
	/** Instance of the player renderer. */
	private final RenderPlayer renderPlayer;
	/** The model used by the Elytra. */
	private final ModelCustomElytra modelElytra = new ModelCustomElytra();

	public LayerCustomElytra(RenderPlayer renderPlayerIn) {
		renderPlayer = renderPlayerIn;
	}

	@Override
	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		ItemStack itemstack = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

		if (!itemstack.isEmpty() && itemstack.getItem() instanceof ArmorCustomElytra) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableBlend();

			renderPlayer.bindTexture(TEXTURE_ELYTRA);

			GlStateManager.pushMatrix();
			GlStateManager.translate(0.0F, 0.0F, 0.125F);
			modelElytra.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn);
			modelElytra.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

			if (itemstack.isItemEnchanted()) {
				LayerArmorBase.renderEnchantedGlint(renderPlayer, entitylivingbaseIn, modelElytra, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
			}

			GlStateManager.popMatrix();
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}