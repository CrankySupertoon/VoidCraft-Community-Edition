package Tamaized.Voidcraft.capabilities.elytraFlying;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.util.ResourceLocation;

public class ElytraFlyingCapabilityHandler implements IElytraFlyingCapability {

	private boolean markDirty = false;

	public static final ResourceLocation ID = new ResourceLocation(VoidCraft.modid, "ElytraFlyingCapabilityHandler");
	private boolean hasLoaded = false;
	
	private boolean isFlying = false;
	private int flyTime = 0;

	public void markDirty() {
		markDirty = true;
	}

	@Override
	public boolean isDirty() {
		return markDirty;
	}

	@Override
	public void resetDirty() {
		markDirty = false;
	}

	@Override
	public boolean hasLoaded() {
		return hasLoaded;
	}

	@Override
	public void setLoaded() {
		hasLoaded = true;
	}

	@Override
	public boolean isElytraFlying() {
		return isFlying;
	}

	@Override
	public void setElytraFlying(boolean flag) {
		isFlying = flag;
	}

	@Override
	public int getElytraTime() {
		return flyTime;
	}

	@Override
	public void setElytraTime(int time) {
		flyTime = time;
	}

	@Override
	public void copyFrom(IElytraFlyingCapability old) {
		isFlying = old.isElytraFlying();
		flyTime = old.getElytraTime();
		setLoaded();
	}

}
