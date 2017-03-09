package Tamaized.Voidcraft.events.client;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TextureStitch {

	public static TextureAtlasSprite colorFire_layer_0;
	public static TextureAtlasSprite colorFire_layer_1;

	public static TextureAtlasSprite guiSlot_Tool;
	public static TextureAtlasSprite guiSlot_Block;
	public static TextureAtlasSprite guiSlot_Dragonscale;
	public static TextureAtlasSprite guiSlot_QuoriFragment;
	public static TextureAtlasSprite guiSlot_AstralEsssence;
	public static TextureAtlasSprite guiSlot_VoidicPhlog;

	@SubscribeEvent
	public void stitchTexture(TextureStitchEvent.Pre e) {
		colorFire_layer_0 = e.getMap().registerSprite(new ResourceLocation(VoidCraft.modid, "layer/fire_layer_0"));
		colorFire_layer_1 = e.getMap().registerSprite(new ResourceLocation(VoidCraft.modid, "layer/fire_layer_1"));

		guiSlot_Tool = e.getMap().registerSprite(new ResourceLocation(VoidCraft.modid, "gui/icons/tool"));
		guiSlot_Block = e.getMap().registerSprite(new ResourceLocation(VoidCraft.modid, "gui/icons/block"));
		guiSlot_Dragonscale = e.getMap().registerSprite(new ResourceLocation(VoidCraft.modid, "gui/icons/dragonscale"));
		guiSlot_QuoriFragment = e.getMap().registerSprite(new ResourceLocation(VoidCraft.modid, "gui/icons/quorifragment"));
		guiSlot_AstralEsssence = e.getMap().registerSprite(new ResourceLocation(VoidCraft.modid, "gui/icons/astralessence"));
		guiSlot_VoidicPhlog = e.getMap().registerSprite(new ResourceLocation(VoidCraft.modid, "gui/icons/voidicphlog"));
	}

}
