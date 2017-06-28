package tamaized.voidcraft.common.events;

import tamaized.voidcraft.common.entity.EntityVoidMob;
import tamaized.voidcraft.common.entity.EntityVoidNPC;
import tamaized.voidcraft.common.entity.boss.EntityBossCorruptedPawn;
import tamaized.voidcraft.common.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import tamaized.voidcraft.common.entity.companion.EntityVoidParrot;
import tamaized.voidcraft.common.handlers.ConfigHandler;
import tamaized.voidcraft.common.world.dim.dalquor.BiomeGenDream;
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
			if (event.getWorld().provider.getDimension() == ConfigHandler.dimensionIdVoid) {
				if (event.getEntity() instanceof EntityLivingBase && !(event.getEntity() instanceof EntityPlayer || event.getEntity() instanceof EntityVoidParrot || event.getEntity() instanceof EntityVoidMob || event.getEntity() instanceof EntityBossCorruptedPawn || event.getEntity() instanceof EntityVoidNPC || !(event.getEntity() instanceof EntityHerobrineCreeper) || !(event.getEntity() instanceof EntityShulker))) {
					event.setResult(Result.DENY);
				}
			} else if (event.getWorld().provider.getDimension() == ConfigHandler.dimensionIdDalQuor) {
				if (BiomeGenDream.allowedEntities.contains(event.getEntity().getClass())){
					if(event.getWorld().rand.nextInt(500) == 0) event.setResult(Result.ALLOW);
					else event.setResult(Result.DENY);
				}
			}
		}
	}

}