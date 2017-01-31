package Tamaized.Voidcraft.sound.client;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import Tamaized.Voidcraft.sound.VoidSoundEvents.SoundTrack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class BGMusic {

	private static ISound sound;
	public static boolean isPlaying = false;
	public static int tick = 0;
	public static boolean pause = false;

	@SubscribeEvent
	public void PlaySoundEvent(PlaySoundEvent e) {
		// if(e.getSound().getCategory() == SoundCategory.MUSIC)DebugEvent.textL = (e.getName()+" "+e.getSound().getCategory()+" "+e.getResultSound().getSoundLocation());
		World world = Minecraft.getMinecraft().world;
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		if (e.getSound().getCategory() == SoundCategory.MUSIC && world != null && world.provider != null) {
			/*
			 * if(world.provider.getDimension() == voidCraft.dimensionIdXia){ TODO if(Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(sound)){ e.setResultSound(null); }else{ sound = PositionedSoundRecord.getMusicRecord(VoidSoundEvents.BGMusicSoundEvents.undertale_core.getTrack()); e.setResultSound(sound); } }else
			 */if (world.provider.getDimension() == VoidCraft.config.getDimensionIDvoid()) {
				if (Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(sound)) {
					e.setResultSound(null);
				} else {
					// int rand = (int) Math.floor(Math.random()*7);
					sound = PositionedSoundRecord.getMusicRecord(
							// rand == 0 ? VoidSoundEvents.BGMusicSoundEvents.emile_wish.getTrack() :
							// rand < 4 ? VoidSoundEvents.BGMusicSoundEvents.emile_nether.getTrack() :
							VoidSoundEvents.BGMusicSoundEvents.mcMusic_end.getTrack());
					e.setResultSound(sound);
				}
			}
		}
	}

	@SubscribeEvent
	public void tick(ClientTickEvent e) {
		if (e.phase == Phase.END) {
			World world = Minecraft.getMinecraft().world;
			if (world != null && world.provider != null) {
				if (world.provider.getDimension() != VoidCraft.config.getDimensionIDxia()) {
					if (world.provider.getDimension() != VoidCraft.config.getDimensionIDvoid()) {
						Minecraft.getMinecraft().getSoundHandler().stopSound(sound);
						sound = null;
					}
				} else {
					if (sound != null && !Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(sound)) sound = null;
				}
			} else {
				if (Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(sound)) Minecraft.getMinecraft().getSoundHandler().stopSound(sound);
				sound = null;
			}
		}
	}

	private static void PlayMusic(SoundTrack track) {
		StopMusic();
		isPlaying = true;
		tick = track.getLength() * 20;
		sound = PositionedSoundRecord.getMusicRecord(track.getTrack());
		Minecraft.getMinecraft().getSoundHandler().playSound(sound);
	}

	private static void StopMusic() {
		if (Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(sound)) Minecraft.getMinecraft().getSoundHandler().stopSound(sound);
		else sound = null;
		Minecraft.getMinecraft().getMusicTicker().stopMusic();
		isPlaying = false;
	}

}
