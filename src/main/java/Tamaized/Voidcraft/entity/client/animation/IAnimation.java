package Tamaized.Voidcraft.entity.client.animation;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.Voidcraft.entity.EntityVoidNPC;
import io.netty.buffer.ByteBufInputStream;
import net.minecraftforge.client.model.animation.Animation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IAnimation<T extends EntityVoidNPC, E extends AnimatableModel> {

	/**
	 * return true when finished
	 */
	boolean update(T e);

	@SideOnly(Side.CLIENT)
	void render(T e, E model);

	void encodePacket(DataOutputStream stream) throws IOException;

	void decodePacket(ByteBufInputStream stream) throws IOException;
	
}
