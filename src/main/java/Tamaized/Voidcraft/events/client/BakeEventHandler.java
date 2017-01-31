package Tamaized.Voidcraft.events.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import Tamaized.Voidcraft.VoidCraft;

public class BakeEventHandler {
	
	public static final BakeEventHandler instance = new BakeEventHandler();

    private BakeEventHandler() {};

    @SubscribeEvent
    public void onModelBakeEvent(ModelBakeEvent event){
    	VoidCraft.instance.logger.info("Baking TESR Blocks");
        event.getModelManager().getBlockModelShapes().registerBuiltInBlocks(VoidCraft.blocks.Heimdall);
        event.getModelManager().getBlockModelShapes().registerBuiltInBlocks(VoidCraft.blocks.blockNoBreak);
        event.getModelManager().getBlockModelShapes().registerBuiltInBlocks(VoidCraft.blocks.voidicCharger);
    }

}
