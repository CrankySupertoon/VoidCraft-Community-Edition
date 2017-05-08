package Tamaized.Voidcraft.handlers;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
		if (isOnline()) {
			VoidCraft.instance.logger.info("Able to Connect to Mojang Servers, validating skins");
			validateNames();
			handleResources();
			validateSkins();
		} else {
			VoidCraft.instance.logger.info("Unable to Connect to Mojang Servers, using cache");
			handleResources();
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
		File[] filelist = dir.listFiles();
		List<String> list = new ArrayList<String>();
		for (File f : filelist) {
			String fileName = f.getName().replace(".png", "");
			if (!uuidNames.containsKey(fileName)) {
				VoidCraft.instance.logger.info("Deleting: " + fileName);
				f.delete();
			}
			else list.add(fileName);
		}
		for (String name : uuidNames.keySet()) {
			if (!list.contains(name)) {
				VoidCraft.instance.logger.info("Missing: " + name);
				VoidCraft.instance.logger.info("Populating: " + loc);
				extractZip(SkinHandler.class.getResourceAsStream(skinZip), loc);
				return;
			}
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
		for (Entry<String, UUID> entry : uuidNames.entrySet()) {
			String name = entry.getKey();
			UUID id = entry.getValue();
			try {
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
											String newName = json.nextString();
											if (!name.equals(newName)) {
												VoidCraft.instance.logger.info("Detected Name Change (" + name + ") is now (" + newName + ")");
												name = newName;
											}
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
				tempMap.put(name, id);
				VoidCraft.instance.logger.info("Mapped " + name + " -> " + id);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		uuidNames.clear();
		uuidNames.putAll(tempMap);
	}

	private static void validateSkins() {
		for (Entry<String, UUID> entry : uuidNames.entrySet()) {
			String name = entry.getKey();
			UUID id = entry.getValue();
			File f = new File(loc + name + ".png");
			boolean valid = true;
			String urlName = skinUrl.concat(name + ".png");
			try {
				URL url = new URL(urlName);
				long size = getFileSize(url);
				if (!f.exists() || f.length() != size) {
					VoidCraft.instance.logger.info(name + " not valid, will download the new skin. (" + f.exists() + " : " + f.length() + " : " + size + ")");
					valid = false;
				}
			} catch (IOException e) {
				e.printStackTrace();
				VoidCraft.instance.logger.info(name + " couldn't be validated but we'll pass it anyway to load the cache");
			}
			if (valid) {
				VoidCraft.instance.logger.info(name + " was validated");
				loadResource(name, id);
			} else {
				try {
					VoidCraft.instance.logger.info("Updating skin: " + name);
					VoidCraft.instance.logger.info("Downloading skin: " + urlName);
					FileUtils.copyURLToFile(new URL(urlName), new File(loc + name + ".png"));
					loadResource(name, id);
				} catch (IOException e) {
					e.printStackTrace();
					VoidCraft.instance.logger.info("Problem downloading skin");
				}
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
			File file = new File(loc + name + ".png");
			if (!file.exists()) throw new IOException("File doesnt exist! (" + file.getAbsolutePath() + ")");
			BufferedImage bimg = ImageIO.read(file);
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
