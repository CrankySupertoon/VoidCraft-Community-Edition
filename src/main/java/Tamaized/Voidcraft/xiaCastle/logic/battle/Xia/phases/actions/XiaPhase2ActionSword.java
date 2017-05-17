package Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.phases.actions;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.TamModized.helper.PacketHelper;
import Tamaized.TamModized.helper.PacketHelper.PacketWrapper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.entity.boss.xia.animations.AnimationXiaSwordSwing;
import Tamaized.Voidcraft.entity.boss.xia.render.EntityAnimationsXia;
import Tamaized.Voidcraft.entity.client.animation.IAnimation;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class XiaPhase2ActionSword {

	private final EntityBossXia xia;
	private final Vec3d throne;

	private int tick = 1;
	private int actionTick = (int) (20f * 1.5f);
	private boolean isActing = false;
	private boolean done = true;
	private EntityAnimationsXia.Animation animation;
	private IAnimation<EntityBossXia> currAnimation;

	public XiaPhase2ActionSword(EntityBossXia entity, Vec3d pos) {
		xia = entity;
		throne = pos;
	}

	public void init() {
		xia.setPosition(throne.xCoord, throne.yCoord, throne.zCoord);
		xia.setArmRotations(0, 90, 0, 90, true);
		tick = 1;
		animation = null;
		isActing = false;
		done = false;
	}

	public void update() {
		if (done) return;
		if (!isActing) {
			xia.setArmRotations(0, 0, 0, 0, true);
			if (tick % actionTick == 0) {
				animation = EntityAnimationsXia.Animation.SWORD_SWING;
				sendPacketToClients();
				currAnimation = new AnimationXiaSwordSwing();
				currAnimation.init(throne);
				isActing = true;
				tick = 1;
			}
		} else {
			if (currAnimation != null && currAnimation.update(xia)) {
				finish();
				isActing = false;
				tick = 1;
			}
		}
		tick++;
	}

	private void sendPacketToClients() {
		try {
			PacketWrapper packet = PacketHelper.createPacket(VoidCraft.channel, VoidCraft.networkChannelName, ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.BOSS_ANIMATIONS));
			DataOutputStream stream = packet.getStream();
			stream.writeInt(xia.getEntityId());
			stream.writeInt(EntityAnimationsXia.getAnimationID(animation));
			packet.sendPacket(new TargetPoint(xia.world.provider.getDimension(), xia.posX, xia.posY, xia.posZ, 64));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void finish() {
		currAnimation = null;
		xia.setArmRotations(0, 0, 0, 0, true);
		done = true;
	}

	public boolean isDone() {
		return done;
	}

}
