package Tamaized.Voidcraft.entity.boss.lob;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.xiaCastle.logic.battle.IBattleHandler;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class EntityLordOfBlades extends EntityVoidBoss<IBattleHandler> {

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
				// TODO Auto-generated method stub
				
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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return 0;
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
