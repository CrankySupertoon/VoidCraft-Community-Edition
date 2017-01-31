package Tamaized.Voidcraft.handlers;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.FileUtils;

import Tamaized.Voidcraft.VoidCraft;

import com.google.common.io.Resources;
import com.google.gson.stream.JsonReader;
import com.mojang.authlib.GameProfile;

public class SkinHandler {

	private static final String SESSION_SERVER = "https://sessionserver.mojang.com";
	private static final String profileUrl = SESSION_SERVER + "/session/minecraft/profile/";
	private static final String nameUrl = "https://api.mojang.com/user/profiles/";
	private static final String skinUrl = "http://skins.minecraft.net/MinecraftSkins/";
	private static final String idUrl = "https://api.mojang.com/profiles/";
	private static final String baseLoc = (System.getenv("APPDATA") == null || System.getenv("APPDATA").contains("null")) ? "./.minecraft/" : (System.getenv("APPDATA")) + "/.minecraft/" + VoidCraft.modid + "/";
	private static final String loc = baseLoc + "assets/" + VoidCraft.modid + "/skins/";
	private static final String skinZip = "/assets/" + VoidCraft.modid + "/skinHandler/skins.zip";

	// Perm
	private Map<PlayerNameAlias, GameProfile> aliasProfile = new HashMap<PlayerNameAlias, GameProfile>();
	private Map<PlayerNameAlias, ResourceLocation> aliasSkin = new HashMap<PlayerNameAlias, ResourceLocation>();
	private Map<PlayerNameAlias, Boolean> aliasBiped = new HashMap<PlayerNameAlias, Boolean>();
	private static final Map<PlayerNameAlias, UUID> aliasUUID = new HashMap<PlayerNameAlias, UUID>();

	// Temp
	private Map<String, UUID> uuidNames = new HashMap<String, UUID>();
	private Map<UUID, String> uuidNamesFlip = new HashMap<UUID, String>();
	private ArrayList<UUID> blacklist = new ArrayList<UUID>();

	public static enum PlayerNameAlias {
		Tamaized, DireWolf20, Cpw11, Soaryn, Vazkii, Tlovetech, Boni, Azanor, Rorax, Slowpoke101, XCompWiz, Pahimar, iChun, RWTema, FireBall1725, TTFTCUTS
	}

	static {
		aliasUUID.put(PlayerNameAlias.Tamaized, UUID.fromString("16fea09e-314e-4955-88c2-6b552ecf314a"));
		aliasUUID.put(PlayerNameAlias.DireWolf20, UUID.fromString("bbb87dbe-690f-4205-bdc5-72ffb8ebc29d"));
		aliasUUID.put(PlayerNameAlias.Cpw11, UUID.fromString("59af7399-5544-4990-81f1-c8f2263b00e5"));
		aliasUUID.put(PlayerNameAlias.Soaryn, UUID.fromString("4f3a8d1e-33c1-44e7-bce8-e683027c7dac"));
		aliasUUID.put(PlayerNameAlias.Vazkii, UUID.fromString("8c826f34-113b-4238-a173-44639c53b6e6"));
		aliasUUID.put(PlayerNameAlias.Tlovetech, UUID.fromString("c2024e2a-dd76-4bc9-9ea3-b771f18f23b6"));
		aliasUUID.put(PlayerNameAlias.Boni, UUID.fromString("6078f46a-bae3-496b-bbdc-dcc25aca75ba"));
		aliasUUID.put(PlayerNameAlias.Azanor, UUID.fromString("0f95811a-b3b6-4dba-ba03-4adfec7cf5ab"));
		aliasUUID.put(PlayerNameAlias.Rorax, UUID.fromString("e8b46b33-3e17-4b64-8d07-9af116df7d3b"));
		aliasUUID.put(PlayerNameAlias.Slowpoke101, UUID.fromString("d2839efc-727a-4263-97ce-3c73cdee5013"));
		aliasUUID.put(PlayerNameAlias.XCompWiz, UUID.fromString("b97e12ce-dbb1-4c0c-afc8-132b708a9b88"));
		aliasUUID.put(PlayerNameAlias.Pahimar, UUID.fromString("0192723f-b3dc-495a-959f-52c53fa63bff"));
		aliasUUID.put(PlayerNameAlias.iChun, UUID.fromString("0b7509f0-2458-4160-9ce1-2772b9a45ac2"));
		aliasUUID.put(PlayerNameAlias.RWTema, UUID.fromString("72ddaa05-7bbe-4ae2-9892-2c8d90ea0ad8"));
		aliasUUID.put(PlayerNameAlias.FireBall1725, UUID.fromString("e43e9766-f903-48e1-818f-d41bb48d80d5"));
		aliasUUID.put(PlayerNameAlias.TTFTCUTS, UUID.fromString("48a16fc8-bc1f-4e72-84e9-7ec73b7d8ea1"));
	}

	public static UUID getUUID(PlayerNameAlias name) {
		return aliasUUID.get(name);
	}

	/**
	 * This will return null if we were unable to update from the Mojang servers or there was an issue with that specific player alias
	 */
	public GameProfile getGameProfile(PlayerNameAlias name) {
		return aliasProfile.get(name);
	}

	@SideOnly(Side.CLIENT)
	public ResourceLocation getSkinResource(PlayerNameAlias name) {
		return aliasSkin.get(name);
	}

	public boolean isBipedModel(PlayerNameAlias alias) {
		return aliasBiped.get(alias);
	}

	public void run() {
		VoidCraft.instance.logger.info("Running SkinHandler");
		handleResources();
		if (isOnline()) {
			VoidCraft.instance.logger.info("Able to Connect to Mojang Servers, validating skins");
			validateNames();
			validateSkins();
			updateSkins();
		} else {
			VoidCraft.instance.logger.info("Unable to Connect to Mojang Servers, using cache");
			useCacheNames();
			cacheSkins();
		}
		freeMemory();
	}

	private void freeMemory() {
		uuidNames.clear();
		uuidNamesFlip.clear();
		blacklist.clear();
		uuidNames = null;
		uuidNamesFlip = null;
		blacklist = null;
	}

	private void handleResources() {
		File dir = new File(loc);
		dir.mkdirs();
		if (dir.list().length < 16) {
			VoidCraft.instance.logger.info("Populating: " + loc);
			extractZip(getClass().getResourceAsStream(skinZip), loc);
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

	private void useCacheNames() {
		uuidNamesFlip.put(getUUID(PlayerNameAlias.Azanor), "azanor");
		uuidNamesFlip.put(getUUID(PlayerNameAlias.Boni), "boni");
		uuidNamesFlip.put(getUUID(PlayerNameAlias.Cpw11), "cpw11");
		uuidNamesFlip.put(getUUID(PlayerNameAlias.DireWolf20), "direwolf20");
		uuidNamesFlip.put(getUUID(PlayerNameAlias.FireBall1725), "FireBall1725");
		uuidNamesFlip.put(getUUID(PlayerNameAlias.iChun), "iChun");
		uuidNamesFlip.put(getUUID(PlayerNameAlias.Pahimar), "Pahimar");
		uuidNamesFlip.put(getUUID(PlayerNameAlias.Rorax), "Rorax");
		uuidNamesFlip.put(getUUID(PlayerNameAlias.RWTema), "RWTema");
		uuidNamesFlip.put(getUUID(PlayerNameAlias.Slowpoke101), "slowpoke101");
		uuidNamesFlip.put(getUUID(PlayerNameAlias.Soaryn), "Soaryn");
		uuidNamesFlip.put(getUUID(PlayerNameAlias.Tamaized), "Tamaized");
		uuidNamesFlip.put(getUUID(PlayerNameAlias.Tlovetech), "tlovetech");
		uuidNamesFlip.put(getUUID(PlayerNameAlias.TTFTCUTS), "TTFTCUTS");
		uuidNamesFlip.put(getUUID(PlayerNameAlias.Vazkii), "Vazkii");
		uuidNamesFlip.put(getUUID(PlayerNameAlias.XCompWiz), "XCompWiz");
	}

	private void validateNames() {
		VoidCraft.instance.logger.info("Mapping Names to UUIDs");
		for (UUID id : aliasUUID.values()) {
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
				uuidNames.put(theName, id);
				uuidNamesFlip.put(id, theName);
				VoidCraft.instance.logger.info("Mapped " + theName + " -> " + id);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void validateSkins() {
		for (File file : new File(loc).listFiles()) {
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

	private int getFileSize(URL url) {
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

	private void updateSkins() {
		aliasList: for (PlayerNameAlias alias : PlayerNameAlias.values()) {
			if (blacklist.contains(getUUID(alias))) {
				loadCachedAlias(alias);
				continue;
			}
			VoidCraft.instance.logger.info("Updating alias: " + alias);
			try {
				BufferedReader reader = Resources.asCharSource(new URL(profileUrl + ("" + getUUID(alias)).replace("-", "")), StandardCharsets.UTF_8).openBufferedStream();

				String encodedString = null;
				String profileName = null;
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
									loadCachedAlias(alias);
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
					loadCachedAlias(alias);
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
								case "profileName":
									profileName = json.nextString();
									break;
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
				FileUtils.copyURLToFile(new URL(skinUrl), new File(loc + uuidNamesFlip.get(getUUID(alias)) + ".png"));
				loadAlias(alias, profileName);
			} catch (IOException e) {
				VoidCraft.instance.logger.info("Request was sent too often or there was an issue with the Mojang servers, using cache for " + alias);
				loadCachedAlias(alias);
			}
		}
	}

	private void cacheSkins() {
		for (PlayerNameAlias alias : PlayerNameAlias.values()) {
			loadCachedAlias(alias);
		}
	}

	private void loadCachedAlias(PlayerNameAlias alias) {
		VoidCraft.instance.logger.info("Loading alias cache: " + uuidNamesFlip.get(getUUID(alias)));
		aliasProfile.put(alias, new GameProfile(getUUID(alias), uuidNamesFlip.get(getUUID(alias))));
		loadAliasResource(alias);
	}

	private void loadAlias(PlayerNameAlias alias, String name) {
		VoidCraft.instance.logger.info("Loading updated alias: " + uuidNamesFlip.get(getUUID(alias)));
		aliasProfile.put(alias, new GameProfile(getUUID(alias), name));
		loadAliasResource(alias);
	}

	private void loadAliasResource(PlayerNameAlias alias) {
		try {
			BufferedImage bimg = ImageIO.read(new File(loc + uuidNamesFlip.get(getUUID(alias)) + ".png"));
			if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
				net.minecraft.client.renderer.texture.DynamicTexture dt = new net.minecraft.client.renderer.texture.DynamicTexture(bimg);
				net.minecraft.client.renderer.texture.TextureManager tm = net.minecraft.client.Minecraft.getMinecraft().getTextureManager();
				ResourceLocation resource = tm.getDynamicTextureLocation(VoidCraft.modid + ":skin_" + alias.toString(), dt);
				aliasSkin.put(alias, resource);
			}
			aliasBiped.put(alias, bimg.getHeight() == 32);
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

}
