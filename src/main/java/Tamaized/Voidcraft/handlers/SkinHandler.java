package Tamaized.Voidcraft.handlers;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.FileUtils;

import com.google.common.io.Resources;
import com.google.gson.stream.JsonReader;
import com.mojang.authlib.GameProfile;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SkinHandler {

	public static final SkinHandler instance = new SkinHandler();

	private SkinHandler() {
	}

	private static final String SESSION_SERVER = "https://sessionserver.mojang.com";
	private static final String profileUrl = SESSION_SERVER + "/session/minecraft/profile/";
	private static final String nameUrl = "https://api.mojang.com/user/profiles/";
	private static final String skinUrl = "http://skins.minecraft.net/MinecraftSkins/";
	private static final String idUrl = "https://api.mojang.com/profiles/";
	private static final String baseLoc = (System.getenv("APPDATA") == null || System.getenv("APPDATA").contains("null")) ? "./.minecraft/" : (System.getenv("APPDATA")) + "/.minecraft/" + VoidCraft.modid + "/";
	private static final String loc = baseLoc + "assets/" + VoidCraft.modid + "/skins/";
	private static final String skinZip = "/assets/" + VoidCraft.modid + "/skinHandler/skins.zip";

	// Perm
	private static volatile Map<UUID, GameProfile> uuidProfile = new HashMap<UUID, GameProfile>();
	private static volatile Map<UUID, ResourceLocation> uuidSkin = new HashMap<UUID, ResourceLocation>();
	private static volatile Map<UUID, Boolean> uuidBiped = new HashMap<UUID, Boolean>();
	private static volatile Map<String, UUID> uuidNames = new HashMap<String, UUID>();

	// Temp
	private static volatile ArrayList<UUID> blacklist = new ArrayList<UUID>();

	public static synchronized UUID getUUID(String name) {
		return uuidNames.get(name);
	}

	public static synchronized int getSize() {
		return uuidNames.size();
	}

	public static synchronized UUID getUUID(int index) {
		return (UUID) uuidNames.values().toArray()[index];
	}

	/**
	 * This will return null if we were unable to update from the Mojang servers or there was an issue with that specific player alias
	 */
	public static synchronized GameProfile getGameProfile(UUID id) {
		return uuidProfile.get(id);
	}

	@SideOnly(Side.CLIENT)
	public static synchronized ResourceLocation getSkinResource(UUID id) {
		return uuidSkin.get(id);
	}

	public static synchronized boolean isBipedModel(UUID id) {
		return uuidBiped.get(id);
	}

	public static synchronized void run() {
		VoidCraft.instance.logger.info("Running SkinHandler");
		fillNames();
		handleResources();
		if (isOnline()) {
			VoidCraft.instance.logger.info("Able to Connect to Mojang Servers, validating skins");
			validateNames();
			validateSkins();
			updateSkins();
		} else {
			VoidCraft.instance.logger.info("Unable to Connect to Mojang Servers, using cache");
			loadCacheSkins();
		}
		freeMemory();
	}

	private static void freeMemory() {
		blacklist.clear();
		blacklist = null;
	}

	private static void fillNames() {
		if (!ContributorHandler.skinList.isEmpty()) {
			for (Entry<String, UUID> entry : ContributorHandler.skinList.entrySet()) {
				String key = entry.getKey();
				UUID value = entry.getValue();
				uuidNames.put(key, value);
			}
		} else {
			cache("azanor");
			cache("boni");
			cache("cpw11");
			cache("direwolf20");
			cache("FireBall1725");
			cache("iChun");
			cache("Pahimar");
			cache("Rorax");
			cache("RWTema");
			cache("slowpoke101");
			cache("Soaryn");
			cache("Tamaized");
			cache("tlovetech");
			cache("TTFTCUTS");
			cache("Vazkii");
			cache("XCompWiz");
		}
	}

	private static void cache(String name) {
		uuidNames.put(name, getUUID(name));
	}

	private static void handleResources() {
		File dir = new File(loc);
		dir.mkdirs();
		if (dir.list().length < uuidNames.size()) {
			VoidCraft.instance.logger.info("Populating: " + loc);
			extractZip(SkinHandler.class.getResourceAsStream(skinZip), loc);
		}
	}

	public static void extractZip(InputStream loc, String dest) {
		try {
			// Open the zip file
			ZipInputStream zipFile = new ZipInputStream(loc);
			ZipEntry zipEntry;
			while ((zipEntry = zipFile.getNextEntry()) != null) {
				String name = zipEntry.getName();
				long size = zipEntry.getSize();
				long compressedSize = zipEntry.getCompressedSize();
				System.out.printf("name: %-20s | size: %6d | compressed size: %6d\n", name, size, compressedSize);

				// Do we need to create a directory ?
				File file = new File(dest + name);
				if (name.endsWith("/")) {
					file.mkdirs();
					continue;
				}

				File parent = file.getParentFile();
				if (parent != null) {
					parent.mkdirs();
				}

				// Extract the file
				FileOutputStream fos = new FileOutputStream(file);
				byte[] bytes = new byte[1024];
				int length;
				while ((length = zipFile.read(bytes)) >= 0) {
					fos.write(bytes, 0, length);
				}
				fos.close();

			}
			zipFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void validateNames() {
		VoidCraft.instance.logger.info("Mapping Names to UUIDs");
		Map<String, UUID> tempMap = new HashMap<String, UUID>();
		for (UUID id : uuidNames.values()) {
			try {
				String theName = id.toString().replace("-", "");
				URL url = new URL(nameUrl + id.toString().replace("-", "") + "/names");
				BufferedReader reader = Resources.asCharSource(url, StandardCharsets.UTF_8).openBufferedStream();
				JsonReader json = new JsonReader(reader);
				{
					json.beginArray();
					{
						while (json.hasNext()) {
							json.beginObject();
							{
								while (json.hasNext()) {
									switch (json.nextName()) {
										case "name":
											theName = json.nextString();
											break;
										default:
											json.skipValue();
											break;
									}
								}
							}
							json.endObject();
						}
					}
				}
				json.close();
				tempMap.put(theName, id);
				VoidCraft.instance.logger.info("Mapped " + theName + " -> " + id);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		uuidNames.clear();
		uuidNames.putAll(tempMap);
	}

	private static void validateSkins() {
		File[] list = new File(loc).listFiles();
		for (File file : list) {
			String name = file.getName().split("\\.")[0];
			if (!uuidNames.containsKey(name)) {
				file.delete();
			}
			try {
				long size = getFileSize(new URL(skinUrl.concat(file.getName())));
				if (file.length() != size) {
					file.delete();
				}
			} catch (IOException e) {
				e.printStackTrace();
				file.delete();
			}
			if (file.exists()) {
				blacklist.add(uuidNames.get(name));
				VoidCraft.instance.logger.info(name + " was validated");
			}
		}
	}

	private static int getFileSize(URL url) {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("HEAD");
			conn.getInputStream();
			return conn.getContentLength();
		} catch (IOException e) {
			return -1;
		} finally {
			conn.disconnect();
		}
	}

	private static void updateSkins() {
		aliasList: for (Entry<String, UUID> entry : uuidNames.entrySet()) {
			String name = entry.getKey();
			UUID id = entry.getValue();
			if (blacklist.contains(id)) {
				loadResource(name, id);
				continue;
			}
			VoidCraft.instance.logger.info("Updating alias: " + id);
			try {
				BufferedReader reader = Resources.asCharSource(new URL(profileUrl + id.toString().replace("-", "")), StandardCharsets.UTF_8).openBufferedStream();

				String encodedString = null;
				String skinUrl = "";

				JsonReader json = new JsonReader(reader);
				{
					json.beginObject();
					{
						while (json.hasNext()) {
							String key = json.nextName();
							switch (key) {
								case "properties":
									json.beginArray(); // properties
								{
									json.beginObject(); // 0
									{
										while (json.hasNext()) {
											String key2 = json.nextName();
											switch (key2) {
												case "value":
													encodedString = json.nextString();
													break;
												default:
													json.skipValue();
													break;
											}
										}
									}
									json.endObject();
								}
									json.endArray();
									break;
								case "error":
									loadResource(name, id);
									continue aliasList;
								default:
									json.skipValue();
									break;
							}
						}

					}
					json.endObject();
				}
				json.close();

				if (encodedString == null) {
					loadResource(name, id);
					continue;
				}

				InputStream is = new ByteArrayInputStream(StringUtils.newStringUtf8(Base64.decodeBase64(encodedString)).getBytes());
				reader = new BufferedReader(new InputStreamReader(is));
				json = new JsonReader(reader);
				{
					json.beginObject();
					{
						while (json.hasNext()) {
							String key = json.nextName();
							switch (key) {
								case "textures":
									json.beginObject(); // textures
								{
									while (json.hasNext()) {
										String key2 = json.nextName();
										switch (key2) {
											case "SKIN":
												json.beginObject(); // SKIN
											{
												while (json.hasNext()) {
													String key3 = json.nextName();
													switch (key3) {
														case "url":
															skinUrl = json.nextString();
															break;
														default:
															json.skipValue();
															break;
													}
												}
											}
												json.endObject();
												break;
											default:
												json.skipValue();
												break;
										}
									}
								}
									json.endObject();
									break;
								default:
									json.skipValue();
									break;
							}
						}
					}
					json.endObject();
				}
				json.close();
				VoidCraft.instance.logger.info("Downloading skin: " + skinUrl);
				FileUtils.copyURLToFile(new URL(skinUrl), new File(loc + name + ".png"));
				loadResource(name, id);
			} catch (IOException e) {
				VoidCraft.instance.logger.info("Request was sent too often or there was an issue with the Mojang servers, using cache for " + name);
				loadResource(name, id);
			}
		}
	}

	private static void loadCacheSkins() {
		for (Entry<String, UUID> entry : uuidNames.entrySet()) {
			String name = entry.getKey();
			UUID id = entry.getValue();
			loadResource(name, id);
		}
	}

	private static void loadResource(String name, UUID id) {
		VoidCraft.instance.logger.info("Queue resource: " + name);
		uuidProfile.put(id, new GameProfile(id, name));
		try {
			BufferedImage bimg = ImageIO.read(new File(loc + name + ".png"));
			if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) bimgQueue.add(new ImgWrapper(bimg, name, id));
			uuidBiped.put(id, bimg.getHeight() == 32);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean isOnline() {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(SESSION_SERVER).openConnection();
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static volatile List<ImgWrapper> bimgQueue = new ArrayList<ImgWrapper>();

	@SubscribeEvent
	public void handleQueue(TickEvent.ClientTickEvent e) {
		Iterator<ImgWrapper> iter = bimgQueue.iterator();
		while (iter.hasNext()) {
			ImgWrapper i = iter.next();
			VoidCraft.instance.logger.info("Loading resource: " + i.name);
			net.minecraft.client.renderer.texture.DynamicTexture dt = new net.minecraft.client.renderer.texture.DynamicTexture(i.bimg);
			net.minecraft.client.renderer.texture.TextureManager tm = net.minecraft.client.Minecraft.getMinecraft().getTextureManager();
			ResourceLocation resource = tm.getDynamicTextureLocation(VoidCraft.modid + ":skin_" + i.name, dt);
			uuidSkin.put(i.id, resource);
			iter.remove();
		}
	}

	private static class ImgWrapper {

		public final BufferedImage bimg;
		public final String name;
		public final UUID id;

		public ImgWrapper(BufferedImage bi, String n, UUID uuid) {
			bimg = bi;
			name = n;
			id = uuid;
		}

	}

}
