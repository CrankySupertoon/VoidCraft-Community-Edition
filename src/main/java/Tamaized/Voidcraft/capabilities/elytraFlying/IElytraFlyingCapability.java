package Tamaized.Voidcraft.capabilities.elytraFlying;

public interface IElytraFlyingCapability {

	public boolean isDirty();

	public void resetDirty();

	public boolean hasLoaded();

	public void setLoaded();

	public boolean isElytraFlying();

	public void setElytraFlying(boolean flag);
	
	public int getElytraTime();

	void setElytraTime(int time);
	
	public void copyFrom(IElytraFlyingCapability old);

}
