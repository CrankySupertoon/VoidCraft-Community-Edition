package Tamaized.Voidcraft.entity.boss.xia.render;

import java.util.Random;

import Tamaized.TamModized.particles.FX.ParticleFluff;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.boss.render.bossBar.RenderBossHeathBar;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia2;
import Tamaized.Voidcraft.entity.boss.xia.model.ModelXia2;
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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderXia2<T extends EntityBossXia2> extends RenderLiving<T> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/entity/xiaform2.png");

	public RenderXia2(RenderManager manager, ModelBase par1ModelBase, float par2) {
		super(manager, par1ModelBase, par2);
		addLayer(new LayerBipedArmor(this));
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float yaw, float ticks) {
		GlStateManager.pushMatrix();
		{
			if (entity.shouldSphereRender()) renderSphere(entity.world, entity, 1, 10);
			// if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderLivingEvent.Pre(entity, this, x, y, z))) return;
			ItemStack itemstack = entity.getHeldItemMainhand();
			ItemStack itemstack1 = entity.getHeldItemOffhand();
			ModelBiped.ArmPose modelbiped$armpose = ModelBiped.ArmPose.EMPTY;
			ModelBiped.ArmPose modelbiped$armpose1 = ModelBiped.ArmPose.EMPTY;
			if (itemstack != null) modelbiped$armpose = ModelBiped.ArmPose.ITEM;
			if (itemstack1 != null) modelbiped$armpose1 = ModelBiped.ArmPose.ITEM;
			if (entity.getPrimaryHand() == EnumHandSide.RIGHT) {
				getMainModel().rightArmPose = modelbiped$armpose;
				getMainModel().leftArmPose = modelbiped$armpose1;
			} else {
				getMainModel().rightArmPose = modelbiped$armpose1;
				getMainModel().leftArmPose = modelbiped$armpose;
			}
			super.doRender(entity, x, y, z, yaw, ticks);
			boolean flag = entity.getPrimaryHand() == EnumHandSide.RIGHT;

			if (itemstack != null || itemstack1 != null) {
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
			renderLabel(entity, x, y, z);
			RenderBossHeathBar.setCurrentBoss(entity);
		}
		// net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderLivingEvent.Post(entity, this, x, y, z));
		Minecraft mc = Minecraft.getMinecraft();
		World world = mc.world;
		if (!mc.isGamePaused() && ticks != 1.0F) {
			for (int i = 0; i < 10; i++) {
				Double dX = (world.rand.nextDouble() * 1.0) - 0.5D;
				Double dZ = (world.rand.nextDouble() * 1.0) - 0.5D;
				// world.spawnParticle(EnumParticleTypes.PORTAL, entity.posX + dX, entity.posY, entity.posZ + dZ, 0, 0, 0);
				world.spawnParticle(EnumParticleTypes.PORTAL, entity.posX, entity.posY + 0.25, entity.posZ, dX, 0, dZ);
			}
		}
		GlStateManager.popMatrix();
	}

	private void renderHeldItem(EntityLivingBase entity, ItemStack stack, ItemCameraTransforms.TransformType transform, EnumHandSide hand) {
		if (stack != null) {
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

	private void renderSphere(World world, T entity, double radius, int amount) {
		if (world == null || Minecraft.getMinecraft().isGamePaused()) return;
		Random rand = world.rand;
		for (int index = 0; index < amount; index++) {
			float speed = 0.08F;
			Vec3d vec = new Vec3d(0, radius, 0).rotatePitch(rand.nextInt(360)).rotateYaw(rand.nextInt(360));
			Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleFluff(world, entity.getPositionVector().addVector(0, entity.getEyeHeight(), 0).add(vec), Vec3d.ZERO, rand.nextInt(6) + 2, 0, rand.nextFloat() * 0.90F + 0.10F, 0x7700FFFF));
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return TEXTURE;
	}

	@Override
	public ModelXia2 getMainModel() {
		return (ModelXia2) super.getMainModel();
	}

	protected void renderLabel(T entity, double x, double y, double z) {
		// y += (double)((float)getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * z);
		renderLivingLabel(entity, entity.getDisplayName().getFormattedText(), x, y, z, 32);
	}

}