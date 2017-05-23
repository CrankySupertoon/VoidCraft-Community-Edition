package Tamaized.Voidcraft.entity.boss.lob;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.entity.EntityVoidNPC;
import Tamaized.Voidcraft.entity.boss.lob.render.ModelLordOfBlades;
import Tamaized.Voidcraft.entity.client.animation.AnimationRegistry;
import Tamaized.Voidcraft.entity.client.animation.IAnimation;
import Tamaized.Voidcraft.entity.client.animation.AnimatableModel.AnimatableModelArms;
import Tamaized.Voidcraft.network.IVoidBossAIPacket;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityVoidNPCAIBase;
import Tamaized.Voidcraft.xiaCastle.logic.battle.IBattleHandler;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class EntityLordOfBlades extends EntityVoidBoss<IBattleHandler> {
	
	public static final int animations = AnimationRegistry.register(AnimationTest.class);

	public static class AnimationTest implements IAnimation<EntityLordOfBlades, ModelLordOfBlades> {
		
		public static enum Type {
			Idle, Attack, Spell, Spin, Fly, Land, Charge
		}
		
		public static void play(EntityLordOfBlades entity, Type type){
			AnimationTest animation = ((AnimationTest) entity.constructAnimation(animations));
			entity.setAnimation(animation);
			entity.playAnimation();
		}

		// Degrees
		private float leftArmYaw = 0.0f;
		private float leftArmPitch = 0.0f;
		private float rightArmYaw = 0.0f;
		private float rightArmPitch = 0.0f;

		private int tick = 20 * 2;

		@Override
		public boolean update(EntityLordOfBlades e) {
			tick--;
			return tick <= 0;
		}

		@Override
		public void render(EntityLordOfBlades e, ModelLordOfBlades model) {
			model.setAnimations(leftArmPitch, rightArmPitch, leftArmYaw, rightArmYaw);
		}

		@Override
		public void encodePacket(DataOutputStream stream) throws IOException {
			stream.writeFloat(leftArmYaw);
			stream.writeFloat(leftArmPitch);
			stream.writeFloat(rightArmYaw);
			stream.writeFloat(rightArmPitch);
		}

		@Override
		public void decodePacket(ByteBufInputStream stream) throws IOException {
			leftArmYaw = stream.readFloat();
			leftArmPitch = stream.readFloat();
			rightArmYaw = stream.readFloat();
			rightArmPitch = stream.readFloat();
		}

	}
	
	public EntityLordOfBlades(World world) {
		super(world, new IBattleHandler() {

			@Override
			public void update() {
				// TODO Auto-generated method stub

			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}

			@Override
			public void start(World worldObj, BlockPos pos) {

			}

			@Override
			public void setDone() {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean isRunning() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isDone() {
				// TODO Auto-generated method stub
				return false;
			}
		}, true);
	}

	@Override
	protected void deathHook() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initPhase(int phase) {
		switch (phase) {
			case 1:
				addAI(new EntityVoidNPCAIBase<EntityLordOfBlades>(this, getFilters()) {

					@Override
					protected void preInit() {
						// TODO Auto-generated method stub

					}

					@Override
					protected void postInit() {
						// TODO Auto-generated method stub

					}

					@Override
					protected void update() {
						// TODO Auto-generated method stub

					}

					@Override
					public void doAction(BlockPos pos) {
						// TODO Auto-generated method stub

					}

					@Override
					public void readPacket(IVoidBossAIPacket packet) {
						// TODO Auto-generated method stub

					}
				});
				break;
		}
	}

	@Override
	protected void updatePhase(int phase) {
		// TODO Auto-generated method stub

	}

	@Override
	protected ArrayList getFilters() {
		return new ArrayList<Class>();
	}

	@Override
	protected boolean immuneToFire() {
		return true;
	}

	@Override
	protected float sizeWidth() {
		return 1.0F;
	}

	@Override
	protected float sizeHeight() {
		return 1.65F;
	}

	@Override
	protected int maxPhases() {
		return 1;
	}
	
	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand, ItemStack p_184645_3_) {
		return super.processInteract(player, hand, p_184645_3_);
	}
	
	@Override
	protected void triggerOnDamage(int phase, DamageSource source, float amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public ITextComponent getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void encodePacketData(DataOutputStream stream) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void decodePacketData(ByteBufInputStream stream) throws IOException {
		// TODO Auto-generated method stub

	}

}
