package Tamaized.Voidcraft.helper;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;

public class TextureAtlasSpriteHelper extends TextureAtlasSprite {

	protected TextureAtlasSpriteHelper(String spriteName) {
		super(spriteName);
	}

	public static TextureAtlasSprite makeAtlasSprite(ResourceLocation spriteResourceLocation) {
		return new TextureAtlasSpriteHelper(spriteResourceLocation.toString());
	}

}
