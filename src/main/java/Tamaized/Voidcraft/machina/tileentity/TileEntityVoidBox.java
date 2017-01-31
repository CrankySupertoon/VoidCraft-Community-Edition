package Tamaized.Voidcraft.machina.tileentity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.stream.JsonReader;

import Tamaized.TamModized.tileentity.TamTileEntityInventory;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.sound.OggLength;
import Tamaized.Voidcraft.sound.VanillaRecordLengths;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class TileEntityVoidBox extends TamTileEntityInventory {

	public static final int SLOT_CURRENT = 0;
	public static final int SLOT_NEXT = 1;
	public static final int SLOT_FINISH = 2;
	public static final int[] SLOTS_ACCESSABLE = new int[] { 1, 2 };
	public static final int[] SLOTS_ALL = new int[] { 0, 1, 2 };

	private boolean playing;
	private int songTimeLeft;
	private int songTime;
	private boolean loop;
	private boolean autoFill;
	private ItemStack oldRecord = ItemStack.EMPTY;

	// Unused for now
	private boolean isPowered = false;
	private boolean pulsed = false;

	public TileEntityVoidBox() {
		super(3);
	}

	public void readNBT(NBTTagCompound nbt) {
		playing = nbt.getBoolean("playing");
		songTimeLeft = nbt.getInteger("songTimeLeft");
		songTime = nbt.getInteger("songTime");
		loop = nbt.getBoolean("loop");
		autoFill = nbt.getBoolean("autoFill");
		int temp = nbt.getInteger("oldRecord");
		oldRecord = temp > -1 ? new ItemStack(Item.getItemById(temp)) : ItemStack.EMPTY;
	}

	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setBoolean("playing", playing);
		nbt.setInteger("songTimeLeft", songTimeLeft);
		nbt.setInteger("songTime", songTime);
		nbt.setBoolean("loop", loop);
		nbt.setBoolean("autoFill", autoFill);
		nbt.setInteger("oldRecord", !oldRecord.isEmpty() ? Item.getIdFromItem(oldRecord.getItem()) : -1);
		return nbt;
	}

	public boolean isPlaying() {
		return playing;
	}

	public int getSongLength() {
		return songTime;
	}

	public int getSongTimeLeft() {
		return songTimeLeft;
	}

	public boolean getAutoState() {
		return autoFill;
	}

	public boolean getLoopState() {
		return loop;
	}

	public void setAutoState() {
		autoFill = !autoFill;
	}

	public void setLoopState() {
		loop = !loop;
	}

	private void PlayTheSound() {
		ItemStack itemStack = slots[SLOT_CURRENT];
		if (!itemStack.isEmpty() && itemStack.getItem() instanceof ItemRecord) {
			try {
				ItemRecord theRecord = (ItemRecord) itemStack.getItem();
				Class<? extends ItemRecord> c = ItemRecord.class;
				Field field = ReflectionHelper.findField(c, new String[] { "field_150928_b", "RECORDS" });
				field.setAccessible(true);
				Map<SoundEvent, ItemRecord> RECORDS = (Map<SoundEvent, ItemRecord>) field.get(theRecord);
				SoundEvent soundEvent = null;
				for (Entry<SoundEvent, ItemRecord> entry : RECORDS.entrySet()) {
					if (entry.getValue() == theRecord) {
						soundEvent = entry.getKey();
						break;
					}
				}
				if (soundEvent == null) throw new IOException("null soundEvent");
				Class<? extends SoundEvent> cS = soundEvent.getClass();
				field = ReflectionHelper.findField(cS, new String[] { "field_187506_b", "field_149219_a", "soundName" });
				field.setAccessible(true);
				ResourceLocation resourceLocation = (ResourceLocation) field.get(soundEvent);
				String modid = resourceLocation.getResourceDomain();
				String soundID = resourceLocation.getResourcePath();
				String encodedValue = "";
				InputStream jsonStream = modid.equals("minecraft") ? null : getClass().getResourceAsStream("/assets/" + modid + "/sounds.json");
				if (jsonStream != null) {
					JsonReader json = new JsonReader(new InputStreamReader(jsonStream, "UTF-8"));
					{
						json.beginObject();
						{
							while (json.hasNext()) {
								String key = json.nextName();
								if (key.equals(soundID)) {
									json.beginObject();
									{
										while (json.hasNext()) {
											key = json.nextName();
											if (key.equals("sounds")) {
												json.beginArray();
												{
													json.beginObject();
													{
														while (json.hasNext()) {
															key = json.nextName();
															if (key.equals("name")) {
																encodedValue = json.nextString();
															} else {
																json.skipValue();
															}
														}
													}
													json.endObject();
												}
												json.endArray();
											} else {
												json.skipValue();
											}
										}
									}
									json.endObject();
								} else {
									json.skipValue();
								}
							}

						}
						json.endObject();
					}
					json.close();
					jsonStream.close();
					String[] decodedValue = { "", "" };
					if (encodedValue.contains(":")) {
						decodedValue = encodedValue.split(":");
					} else {
						decodedValue[0] = "minecraft";
						decodedValue[1] = encodedValue;
					}
					String path = "/assets/" + decodedValue[0] + "/sounds/" + decodedValue[1] + ".ogg";
					songTime = songTimeLeft = OggLength.getLengthInSeconds(path) * 20;
				} else {
					songTime = songTimeLeft = 20 * VanillaRecordLengths.getLength(soundID.split("\\.")[soundID.split("\\.").length - 1]);
				}
				world.playEvent((EntityPlayer) null, 1010, getPos(), Item.getIdFromItem(theRecord));
				oldRecord = slots[SLOT_CURRENT];
				playing = true;
				// IF YOU GOT THIS FAR, CONGRATS THIS WAS NOT FUN TO WRITE
			} catch (Exception e) {
				VoidCraft.instance.logger.warn("Could not play ItemRecord");
				e.printStackTrace();
				loop = false; // Prevent soft lock
			}
		} else {
			VoidCraft.instance.logger.warn("NULL/NON-ITEMRECORD IN SLOT DETECTED");
		}
	}

	public void PlayNextSound() {
		if (slots[SLOT_CURRENT].isEmpty()) {
			slots[SLOT_CURRENT] = slots[SLOT_NEXT];
			slots[SLOT_NEXT] = ItemStack.EMPTY;
			PlayTheSound();
		}
	}

	public void StopTheSound() {
		world.playEvent((EntityPlayer) null, 1010, getPos(), 0);
		playing = false;
	}

	public void StopTheSoundAndDeposit() {
		StopTheSound();
		if (!slots[SLOT_CURRENT].isEmpty() && slots[SLOT_FINISH].isEmpty()) {
			slots[SLOT_FINISH] = slots[SLOT_CURRENT];
			slots[SLOT_CURRENT] = ItemStack.EMPTY;
		}
	}

	public void setHasRedstoneSignal(boolean b) {
		isPowered = b;
	}

	@Override
	public void onUpdate() {
		if (!world.isRemote) {
			if (playing) {
				if (songTimeLeft > 0) songTimeLeft--;
				else StopTheSound();
				if (slots[SLOT_CURRENT].isEmpty() || !slots[SLOT_CURRENT].equals(oldRecord)) StopTheSound();
			} else {
				if (loop && !slots[SLOT_CURRENT].isEmpty()) {
					PlayTheSound();
				}
				if (!loop && !slots[SLOT_CURRENT].isEmpty() && slots[SLOT_FINISH].isEmpty()) {
					slots[SLOT_FINISH] = slots[SLOT_CURRENT];
					slots[SLOT_CURRENT] = ItemStack.EMPTY;
				}
				if (autoFill) {
					if (slots[SLOT_CURRENT].isEmpty() && !slots[SLOT_NEXT].isEmpty()) {
						PlayNextSound();
					}
				}
			}
		}
	}

	@Override
	public String getName() {
		return "voidicMusicBox";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return SLOTS_ACCESSABLE;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	protected boolean canExtractSlot(int i, ItemStack stack) {
		return i == SLOT_FINISH;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return i == SLOT_NEXT ? stack.getItem() instanceof ItemRecord : false;
	}

}
