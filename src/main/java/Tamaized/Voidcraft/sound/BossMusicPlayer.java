package Tamaized.Voidcraft.sound;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.sound.client.BGMusic;
import net.minecraft.client.audio.ISound;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class BossMusicPlayer {

	private static volatile ISound music = null;
	private static volatile Entity boss = null;

	private BossMusicPlayer() {
		// This is a static class
	}

	/**
	 * This is updated directly from {@link Tamaized.Voidcraft.sound.client.BGMusic#tick(ClientTickEvent)}
	 * 
	 * @return true if we are playing boss music
	 */
	public static boolean update(ISound sound) {
		if (music != null) {
			if (boss == null || !boss.isEntityAlive()) {
				StopMusic();
			} else if (sound != music) { // We need to loop
				BGMusic.PlayMusic(music);
			}
		} else {
			if (boss != null) boss = null;
		}
		return music != null && boss != null && boss.isEntityAlive();
	}

	public static void PlayMusic(Entity entity, SoundEvent sound) {
		if(!VoidCraft.config.canPlayMusic()) return;
		boss = entity;
		music = BGMusic.PlayMusic(sound);
	}

	public static void StopMusic() {
		boss = null;
		music = null;
		BGMusic.StopMusic();
	}

}
