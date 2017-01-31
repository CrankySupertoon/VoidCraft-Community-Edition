package Tamaized.Voidcraft.capabilities.voidicPower;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.Voidcraft.VoidCraft;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class VoidicPowerCapabilityHandler implements IVoidicPowerCapability {

	public static final ResourceLocation ID = new ResourceLocation(VoidCraft.modid, "VoidicPowerCapabilityHandler");

	private int currPower = 0;
	private int maxPower = 0;
	private boolean inUse = false;

	private boolean hasLoaded = false;
	private boolean isDefault = true;
	private boolean dirty = false;

	@Override
	public boolean hasLoaded() {
		return hasLoaded;
	}

	@Override
	public void setLoaded() {
		hasLoaded = true;
	}

	@Override
	public boolean isDefault() {
		return isDefault;
	}

	@Override
	public void setDefault(boolean state) {
		isDefault = state;
		markDirty();
	}

	@Override
	public void setInUse(boolean state) {
		inUse = state;
		markDirty();
	}

	@Override
	public boolean isInUse() {
		return inUse;
	}

	@Override
	public void setValues(int curr, int max) {
		setCurrentPower(curr);
		setMaxPower(max);
		markDirty();
	}

	@Override
	public void setCurrentPower(int curr) {
		currPower = curr;
		markDirty();
	}

	@Override
	public void setMaxPower(int max) {
		maxPower = max;
		markDirty();
	}

	@Override
	public int getCurrentPower() {
		return currPower;
	}

	@Override
	public int getMaxPower() {
		return maxPower;
	}

	@Override
	public float getAmountPerc() {
		return (float) currPower / (float) maxPower;
	}

	@Override
	public int fill(int amount) {
		if (amount < 0) return amount;
		markDirty();
		if (getCurrentPower() + amount > getMaxPower()) {
			setCurrentPower(getMaxPower());
			return (getCurrentPower() + amount) - getMaxPower();
		} else {
			setCurrentPower(getCurrentPower() + amount);
			return 0;
		}
	}

	@Override
	public int drain(int amount) {
		if (amount < 0) return amount;
		markDirty();
		if (getCurrentPower() - amount < 0) {
			setCurrentPower(0);
			return (getCurrentPower());
		} else {
			setCurrentPower(getCurrentPower() - amount);
			return amount;
		}
	}

	@Override
	public void markDirty() {
		dirty = true;
	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	@Override
	public void copyFrom(IVoidicPowerCapability cap) {
		setValues(cap.getCurrentPower(), cap.getMaxPower());
		setDefault(cap.isDefault());
		setLoaded();
	}

	@Override
	public void sendUpdates(EntityPlayer player, int slot, ItemStack stack) {
		//if (player instanceof EntityPlayerMP) sendPacketUpdates((EntityPlayerMP) player, slot, stack, this);
		sendPacketUpdates(null, 0, stack, this);
		dirty = false;
	}

	@Override
	public void decodePacket(ByteBufInputStream stream) throws IOException {
		currPower = stream.readInt();
		maxPower = stream.readInt();
	}

	@Override
	public void encodePacket(DataOutputStream stream) throws IOException {
		stream.writeInt(currPower);
		stream.writeInt(maxPower);
	}

	private void sendPacketUpdates(EntityPlayerMP player, int slot, ItemStack stack, IVoidicPowerCapability cap) {
		NBTTagCompound nbt = stack.getOrCreateSubCompound(VoidCraft.modid);
		nbt.setInteger("currPower", cap.getCurrentPower());
		nbt.setInteger("maxPower", cap.getMaxPower());
		/*
		 * ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer()); DataOutputStream outputStream = new DataOutputStream(bos); try { outputStream.writeInt(ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.VOIDICPOWERITEM)); outputStream.writeInt(slot); ItemStackNetworkHelper.encodeStack(stack, outputStream); cap.encodePacket(outputStream); FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(bos.buffer()), voidCraft.networkChannelName); if (voidCraft.channel != null && packet != null) voidCraft.channel.sendTo(packet, player); bos.close(); } catch (Exception ex) { ex.printStackTrace(); }
		 */
	}

}
