package Tamaized.Voidcraft.network;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.handler.codec.EncoderException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;

public class ItemStackNetworkHelper {

	private ItemStackNetworkHelper() {
	}

	public static void encodeStack(ItemStack stack, DataOutputStream stream) throws IOException {
		if (stack.isEmpty()) {
			stream.writeShort(-1);
		} else {
			stream.writeShort(Item.getIdFromItem(stack.getItem()));
			stream.writeByte(stack.getCount());
			stream.writeShort(stack.getMetadata());
			NBTTagCompound nbttagcompound = null;

			if (stack.getItem().isDamageable() || stack.getItem().getShareTag()) {
				nbttagcompound = stack.getTagCompound();
			}

			writeNBTTagCompoundToBuffer(nbttagcompound, stream);
		}
	}

	public static ItemStack decodeStack(ByteBuf buf, ByteBufInputStream stream) throws IOException {
		ItemStack itemstack = ItemStack.EMPTY;
		int i = stream.readShort();

		if (i >= 0) {
			int j = stream.readByte();
			int k = stream.readShort();
			itemstack = new ItemStack(Item.getItemById(i), j, k);
			itemstack.setTagCompound(readNBTTagCompoundFromBuffer(buf));
		}

		return itemstack;
	}

	public static void writeNBTTagCompoundToBuffer(@Nullable NBTTagCompound nbt, DataOutputStream stream) throws IOException {
		if (nbt == null) {
			stream.writeByte(0);
		} else {
			try {
				CompressedStreamTools.write(nbt, stream);
			} catch (IOException ioexception) {
				throw new EncoderException(ioexception);
			}
		}
	}

	@Nullable
	public static NBTTagCompound readNBTTagCompoundFromBuffer(ByteBuf buf) throws IOException {
		int i = buf.readerIndex();
		byte b0 = buf.readByte();

		if (b0 == 0) {
			return null;
		} else {
			buf.readerIndex(i);

			try {
				return CompressedStreamTools.read(new ByteBufInputStream(buf), new NBTSizeTracker(2097152L));
			} catch (IOException ioexception) {
				throw new EncoderException(ioexception);
			}
		}
	}

}
