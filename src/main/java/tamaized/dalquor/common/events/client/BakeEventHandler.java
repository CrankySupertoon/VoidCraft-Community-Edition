package tamaized.dalquor.common.events.client;

import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.registry.ModBlocks;

public class BakeEventHandler {

	public static final BakeEventHandler instance = new BakeEventHandler();

	private BakeEventHandler() {
	}

	;

	@SubscribeEvent
	public void onModelBakeEvent(ModelBakeEvent event) {
		DalQuor.instance.logger.info("Baking TESR Blocks");
//		event.getModelManager().getBlockModelShapes().registerBuiltInBlocks(VoidCraft.blocks.Heimdall);
		event.getModelManager().getBlockModelShapes().registerBuiltInBlocks(ModBlocks.noBreak);
//		event.getModelManager().getBlockModelShapes().registerBuiltInBlocks(VoidCraft.blocks.voidicCharger);
	}

}
