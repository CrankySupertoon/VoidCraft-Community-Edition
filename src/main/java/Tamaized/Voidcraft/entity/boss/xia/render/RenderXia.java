package Tamaized.Voidcraft.entity.boss.xia.render;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.boss.model.ModelVoidBossOverlay;
import Tamaized.Voidcraft.entity.boss.render.bossBar.RenderBossHeathBar;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderXia<T extends EntityBossXia> extends RenderLiving<T> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid + ":textures/entity/xia.png");

	public RenderXia(RenderManager manager, ModelBase par1ModelBase, float par2) {
		super(manager, par1ModelBase, par2);
		addLayer(new LayerBipedArmor(this));
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float yaw, float ticks) {
		GlStateManager.pushMatrix();
		{
			if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderLivingEvent.Pre(entity, this, x, y, z))) return;
			ItemStack itemstack = entity.getHeldItemMainhand();
			ItemStack itemstack1 = entity.getHeldItemOffhand();
			ModelBiped.ArmPose modelbiped$armpose = ModelBiped.ArmPose.EMPTY;
			ModelBiped.ArmPose modelbiped$armpose1 = ModelBiped.ArmPose.EMPTY;
			if (!itemstack.isEmpty()) modelbiped$armpose = ModelBiped.ArmPose.ITEM;
			if (!itemstack1.isEmpty()) modelbiped$armpose1 = ModelBiped.ArmPose.ITEM;
			if (entity.getPrimaryHand() == EnumHandSide.RIGHT) {
				getMainModel().rightArmPose = modelbiped$armpose;
				getMainModel().leftArmPose = modelbiped$armpose1;
			} else {
				getMainModel().rightArmPose = modelbiped$armpose1;
				getMainModel().leftArmPose = modelbiped$armpose;
			}
			super.doRender(entity, x, y, z, yaw, ticks);
			boolean flag = entity.getPrimaryHand() == EnumHandSide.RIGHT;

			if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
				GlStateManager.pushMatrix();

				if (getMainModel().isChild) {
					float f = 0.5F;
					GlStateManager.translate(0.0F, 0.625F, 0.0F);
					GlStateManager.rotate(-20.0F, -1.0F, 0.0F, 0.0F);
					GlStateManager.scale(0.5F, 0.5F, 0.5F);
				}
				// EntityPlayer client = Minecraft.getMinecraft().thePlayer;
				// GlStateManager.translate(entity.posX-client.posX, entity.posY-client.posY, entity.posZ-client.posZ);
				GlStateManager.translate(x, y + 1.5, z);
				GlStateManager.rotate(-entity.renderYawOffset, 0, 1, 0);
				GlStateManager.rotate(180, 1, 0, 0);
				renderHeldItem(entity, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
				renderHeldItem(entity, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
				GlStateManager.popMatrix();
			}
			entity.renderSpecials();
			renderLabel(entity, x, y, z);
			RenderBossHeathBar.setCurrentBoss(entity);
		}
		net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderLivingEvent.Post(entity, this, x, y, z));
		GlStateManager.popMatrix();
	}

	private void renderHeldItem(EntityLivingBase entity, ItemStack stack, ItemCameraTransforms.TransformType transform, EnumHandSide hand) {
		if (!stack.isEmpty()) {
			GlStateManager.pushMatrix();

			if (entity.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}
			// Forge: moved this call down, fixes incorrect offset while sneaking.
			getMainModel().postRenderArm(0.0625F, hand);
			GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
			boolean flag = hand == EnumHandSide.LEFT;
			GlStateManager.translate((float) (flag ? -1 : 1) / 16.0F, 0.125F, -0.625F);
			// GlStateManager.translate(entity.posX-Minecraft.getMinecraft().thePlayer.posX, entity.posY-Minecraft.getMinecraft().thePlayer.posY, entity.posZ-Minecraft.getMinecraft().thePlayer.posZ);
			Minecraft.getMinecraft().getItemRenderer().renderItemSide(entity, stack, transform, flag);
			GlStateManager.popMatrix();
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return TEXTURE;
	}

	@Override
	public ModelVoidBossOverlay getMainModel() {
		return (ModelVoidBossOverlay) super.getMainModel();
	}

	protected void renderLabel(T entity, double x, double y, double z) {
		// y += (double)((float)getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * z);
		renderLivingLabel(entity, entity.getDisplayName().getFormattedText(), x, y, z, 32);
	}
}
