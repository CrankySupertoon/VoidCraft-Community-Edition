package Tamaized.Voidcraft.events;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.EntityVoidMob;
import Tamaized.Voidcraft.entity.EntityVoidNPC;
import Tamaized.Voidcraft.entity.boss.EntityBossCorruptedPawn;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import Tamaized.Voidcraft.world.dim.dalQuor.BiomeGenDream;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SpawnEvent {

	@SubscribeEvent
	public void canEntitySpawn(LivingSpawnEvent.CheckSpawn event) {
		if (!event.getWorld().isRemote) {
			if (event.getWorld().provider.getDimension() == VoidCraft.config.getDimensionIdVoid()) {
				if (event.getEntity() instanceof EntityLivingBase && !(event.getEntity() instanceof EntityPlayer || event.getEntity() instanceof EntityVoidMob || event.getEntity() instanceof EntityBossCorruptedPawn || event.getEntity() instanceof EntityVoidNPC || !(event.getEntity() instanceof EntityHerobrineCreeper) || !(event.getEntity() instanceof EntityShulker))) {
					event.setResult(Result.DENY);
				}
			} else if (event.getWorld().provider.getDimension() == VoidCraft.config.getDimensionIdDalQuor()) {
				if (BiomeGenDream.allowedEntities.contains(event.getEntity().getClass())){
					if(event.getWorld().rand.nextInt(500) == 0) event.setResult(Result.ALLOW);
					else event.setResult(Result.DENY);
				}
			}
		}
	}

}
