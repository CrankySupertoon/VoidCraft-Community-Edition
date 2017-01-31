package Tamaized.Voidcraft.entity.boss.xia.animations;

import Tamaized.Voidcraft.entity.EntityVoidNPC.ArmRotation;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.entity.client.animation.IAnimation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AnimationXiaSwordSwing implements IAnimation<EntityBossXia> {

	private int tick = 1;
	private int phase = 0;

	private Vec3d vecResult;
	private Vec3d vecOriginal;
	private float currRotation = 90;
	private double swordLength = 30;

	@Override
	public void init(Vec3d pos) {
		vecOriginal = new Vec3d(pos.xCoord, pos.yCoord, pos.zCoord);
		vecResult = new Vec3d(0, 0, 0);
		currRotation = 90;
		tick = 1;
		phase = 0;
	}

	@Override
	public boolean update(EntityBossXia xia) {
		currRotation--;
		xia.setArmRotations(0, 90, 0, currRotation, false);

		double xCoord = vecOriginal.xCoord + swordLength * -Math.cos(Math.toRadians(xia.getArmRotation(ArmRotation.RightYaw))) * Math.sin(Math.toRadians(90));
		double zCoord = vecOriginal.zCoord + swordLength * -Math.sin(Math.toRadians(xia.getArmRotation(ArmRotation.RightYaw))) * Math.sin(Math.toRadians(90));
		double yCoord = vecOriginal.yCoord + swordLength * Math.cos(Math.toRadians(xia.getArmRotation(ArmRotation.RightPitch)));
		vecResult = new Vec3d(xCoord, yCoord, zCoord);

		if (currRotation <= -90) return true;
		return false;
	}

	@Override
	public Vec3d originalVector() {
		return vecOriginal;
	}

	@Override
	public Vec3d resultVector() {
		return vecResult;
	}

	@Override
	public AxisAlignedBB getBounds(int range) {
		return new AxisAlignedBB(vecOriginal.addVector(-range, 0, -range), vecResult.addVector(range, 0, range));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void render(EntityBossXia xia) {

		double xAngle = -Math.cos(Math.toRadians(90.0D + xia.getArmRotation(ArmRotation.RightYaw))) * Math.sin(xia.getArmRotation(ArmRotation.RightPitch));
		double yAngle = 1;// Math.sin(xia.rightArmPitch);
		double zAngle = -Math.sin(Math.toRadians(90.0D + xia.getArmRotation(ArmRotation.RightYaw))) * Math.sin(xia.getArmRotation(ArmRotation.RightPitch));

		GlStateManager.pushAttrib();
		{
			GlStateManager.pushMatrix();
			{
				GlStateManager.color(0.0f, 0.0f, 1.0f, 1.0f);
				Tessellator tessellator = Tessellator.getInstance();
				VertexBuffer vertexbuffer = tessellator.getBuffer();
				vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
				{
					drawBoundingBox(vertexbuffer, getBounds(1));

				}
				tessellator.draw();
			}
			GlStateManager.popMatrix();
		}
		GlStateManager.popAttrib();

	}

	private static void drawBoundingBox(VertexBuffer vertexbuffer, AxisAlignedBB axisAlignedBB) {
		vertexbuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).normal(0.0F, 0.0F, -1.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).normal(0.0F, 0.0F, -1.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).normal(0.0F, 0.0F, -1.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).normal(0.0F, 0.0F, -1.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).normal(0.0F, 0.0F, 1.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).normal(0.0F, 0.0F, 1.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).normal(0.0F, 0.0F, 1.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).normal(0.0F, 0.0F, 1.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).normal(0.0F, -1.0F, 0.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).normal(0.0F, -1.0F, 0.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).normal(0.0F, -1.0F, 0.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).normal(0.0F, -1.0F, 0.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).normal(0.0F, 1.0F, 0.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).normal(0.0F, 1.0F, 0.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).normal(0.0F, 1.0F, 0.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).normal(0.0F, 1.0F, 0.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).normal(-1.0F, 0.0F, 0.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).normal(-1.0F, 0.0F, 0.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).normal(-1.0F, 0.0F, 0.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).normal(-1.0F, 0.0F, 0.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).normal(1.0F, 0.0F, 0.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).normal(1.0F, 0.0F, 0.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).normal(1.0F, 0.0F, 0.0F).endVertex();
		vertexbuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).normal(1.0F, 0.0F, 0.0F).endVertex();
	}

}
