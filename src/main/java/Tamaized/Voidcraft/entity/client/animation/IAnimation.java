package Tamaized.Voidcraft.entity.client.animation;

import Tamaized.Voidcraft.entity.EntityVoidNPC;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IAnimation<T extends EntityVoidNPC> {

	/**
	 * return true when finished
	 */
	public boolean update(T e);

	@SideOnly(Side.CLIENT)
	public void render(T e);

}
