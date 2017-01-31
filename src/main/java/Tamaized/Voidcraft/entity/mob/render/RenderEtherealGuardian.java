package Tamaized.Voidcraft.entity.mob.render;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.mob.EntityMobEtherealGuardian;
import Tamaized.Voidcraft.entity.mob.model.ModelEtherealGuardian;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEtherealGuardian extends RenderLiving<EntityMobEtherealGuardian> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid + ":textures/entity/etherealguardian.png");

	public RenderEtherealGuardian(RenderManager manager, float par2) {
		super(manager, new ModelEtherealGuardian(), par2);
		addLayer(new LayerBipedArmor(this));
	}

	@Override
	public void doRender(EntityMobEtherealGuardian entity, double x, double y, double z, float yaw, float ticks) {
		GlStateManager.pushMatrix();
		{
			// if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderLivingEvent.Pre(entity, this, x, y, z))) return;
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
			// net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderLivingEvent.Post(entity, this, x, y, z));
			Minecraft mc = Minecraft.getMinecraft();
			World world = mc.world;
			if (!mc.isGamePaused()) {
				for (int i = 0; i < 10; i++) {
					Double dX = (world.rand.nextDouble() * 1.0) - 0.5D;
					Double dZ = (world.rand.nextDouble() * 1.0) - 0.5D;
					world.spawnParticle(EnumParticleTypes.FLAME, entity.posX + dX, entity.posY + 0.75, entity.posZ + dZ, 0, -0.04, 0);
					// world.spawnParticle(EnumParticleTypes.FLAME, entity.posX, entity.posY + 0.25, entity.posZ, 0, 0, 0);
				}
			}
		}
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
	protected ResourceLocation getEntityTexture(EntityMobEtherealGuardian entity) {
		return TEXTURE;
	}

	@Override
	public ModelEtherealGuardian getMainModel() {
		return (ModelEtherealGuardian) super.getMainModel();
	}

}