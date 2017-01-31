package Tamaized.Voidcraft.capabilities.vadeMecumItem;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.util.ResourceLocation;

public class VadeMecumItemCapabilityHandler implements IVadeMecumItemCapability {
	
	public static final ResourceLocation ID = new ResourceLocation(VoidCraft.modid, "VadeMecumItemCapabilityHandler");

	private boolean markDirty = false;
	private boolean hasLoaded = false;
	private boolean bookstate = false;

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
	public void setBookState(boolean b) {
		bookstate = b;
		markDirty();
	}

	@Override
	public boolean getBookState() {
		return bookstate;
	}

	@Override
	public void toggleBookState() {
		bookstate ^= true;
		markDirty();
	}

	@Override
	public void copyFrom(IVadeMecumItemCapability cap) {
		setBookState(cap.getBookState());
		setLoaded();
		markDirty();
	}

	@Override
	public void decodePacket(ByteBufInputStream stream) throws IOException {
		setBookState(stream.readBoolean());
	}

	@Override
	public void encodePacket(DataOutputStream stream) throws IOException {
		stream.writeBoolean(getBookState());
	}

}
