package Tamaized.Voidcraft.handlers;

import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class VoidicInfusionHandler {

	@SubscribeEvent
	public void update(LivingUpdateEvent e) {
		EntityLivingBase entity = e.getEntityLiving();
		if (entity != null && entity.hasCapability(CapabilityList.VOIDICINFUSION, null)) {
			IVoidicInfusionCapability cap = entity.getCapability(CapabilityList.VOIDICINFUSION, null);
			if (cap.hasLoaded()) cap.update(entity);
			else cap.load(entity);
		}
	}

}
