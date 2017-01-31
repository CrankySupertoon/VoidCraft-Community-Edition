package Tamaized.Voidcraft.sound;

import Tamaized.Voidcraft.entity.EntityVoidNPC;
import Tamaized.Voidcraft.items.VoidRecord;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class BossMusicManager {

	public static BossMusicManager instance = new BossMusicManager();

	public static boolean isPlaying = false;
	public static World worldObj;
	private static EntityVoidNPC boss;
	public static int[] location = { 0, 0, 0 };
	public static ItemStack item;
	public static boolean loop = false;
	public static int tick = 0;

	@SubscribeEvent
	public void update(ServerTickEvent e) {
		if (!isPlaying || e.side == Side.CLIENT || e.phase != Phase.END) return;
		if (boss.isDead) boss = null;
		if (isPlaying && boss == null) {
			StopTheSound();
			return;
		}
		if (tick > 0) tick--;
		if (loop && tick <= 0) PlayTheSound(worldObj, boss, item, location, loop);

	}

	/**
	 * Play the Record
	 */
	public static void PlayTheSound(World world, EntityVoidNPC b, ItemStack itemStack, int[] loc, boolean l) {
		if (!itemStack.isEmpty()) {
			if (isPlaying) StopTheSound();
			worldObj = world;
			boss = b;
			location = loc;
			loop = l;
			item = itemStack;
			if (loop && itemStack.getItem() instanceof VoidRecord) tick = (((VoidRecord) itemStack.getItem()).getTime() * 20) - 1;
			worldObj.playEvent((EntityPlayer) null, 1010, new BlockPos(location[0], location[1], location[2]), Item.getIdFromItem(itemStack.getItem()));
			isPlaying = true;
		} else {
			loop = false;
			isPlaying = false;
		}
	}

	/**
	 * Stop the Record
	 */
	public static void StopTheSound() {
		if (!isPlaying) return;
		worldObj.playEvent((EntityPlayer) null, 1010, new BlockPos(location[0], location[1], location[2]), 0);
		isPlaying = false;
		boss = null;
	}

}
