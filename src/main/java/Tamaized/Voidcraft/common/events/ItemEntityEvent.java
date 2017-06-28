package tamaized.voidcraft.common.events;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.registry.VoidCraftBlocks;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

import java.util.ConcurrentModificationException;

public class ItemEntityEvent {

	@SubscribeEvent
	public void tick(WorldTickEvent e) {
		if (e.phase == Phase.END || e.world == null || e.world.isRemote) return;
		try {
			for (EntityItem entity : e.world.getEntities(EntityItem.class, EntitySelectors.NOT_SPECTATING)) {
				ItemStack stack = entity.getItem();
				if (!stack.isEmpty() && stack.getItem() == Items.BOOK && e.world.getBlockState(entity.getPosition()).getBlock() == VoidCraftBlocks.fireVoid) {
					e.world.setBlockToAir(entity.getPosition());
					e.world.addWeatherEffect(new EntityLightningBolt(e.world, entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ(), true));
					e.world.spawnEntity(new EntityItem(e.world, entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ(), new ItemStack(VoidCraft.items.vadeMecum)));
					entity.setDead();
				}
			}
		} catch (ConcurrentModificationException stacktrace) {
			VoidCraft.instance.logger.error("Something went very wrong while trying to retrieve the loadedEntityList from the world!");
			stacktrace.printStackTrace();
		}
	}

}