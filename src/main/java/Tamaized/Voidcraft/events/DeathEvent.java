package Tamaized.Voidcraft.events;

import Tamaized.Voidcraft.entity.mob.EntityMobSpectreChain;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DeathEvent {

	@SubscribeEvent
	public void onDeath(LivingDeathEvent e) {
		EntityLivingBase target = e.getEntityLiving();
		DamageSource source = e.getSource();
		if(target != null && !(target instanceof EntityMobSpectreChain) && source != null && source.getEntity() != null && source.getEntity() instanceof EntityMobSpectreChain){
			EntityMobSpectreChain newSpawn = new EntityMobSpectreChain(target.world);
			newSpawn.setLocationAndAngles(target.posX, target.posY, target.posZ, target.rotationYaw, target.rotationPitch);
			target.world.spawnEntity(newSpawn);
		}
	}

}
