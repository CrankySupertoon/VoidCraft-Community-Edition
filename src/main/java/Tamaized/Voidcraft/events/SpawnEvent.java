package Tamaized.Voidcraft.events;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.EntityVoidMob;
import Tamaized.Voidcraft.entity.EntityVoidNPC;
import Tamaized.Voidcraft.entity.boss.EntityBossCorruptedPawnBase;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SpawnEvent {

	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event) {
		if (!event.getWorld().isRemote && event.getWorld().provider.getDimension() == VoidCraft.config.getDimensionIDvoid()) {
			if (event.getEntity() instanceof EntityLivingBase && !(event.getEntity() instanceof EntityPlayer || event.getEntity() instanceof EntityVoidMob || event.getEntity() instanceof EntityBossCorruptedPawnBase || event.getEntity() instanceof EntityVoidNPC || !(event.getEntity() instanceof EntityHerobrineCreeper) || !(event.getEntity() instanceof EntityShulker))) {
				event.setCanceled(true);
				System.out.println(event.getEntity().getClass());
			}
		}
	}

}
