package Tamaized.Voidcraft.blocks.render;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.blocks.TileEntityNoBreak;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicCharger;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;

public class RenderItemStack extends TileEntityItemStackRenderer {
	
	private static final TileEntityHeimdall heimdallInstance = new TileEntityHeimdall();
	private static final TileEntityNoBreak noBreakInstance = new TileEntityNoBreak();
	private static final TileEntityVoidicCharger voidicChargerInstance = new TileEntityVoidicCharger();
	
	@Override
	public void renderByItem(ItemStack itemStack) {
		Block block = Block.getBlockFromItem(itemStack.getItem());
		if(block == VoidCraft.blocks.Heimdall) {
			TileEntityRendererDispatcher.instance.renderTileEntityAt(heimdallInstance, 0.0D, 0.0D, 0.0D, 0.0F);
		}else if(block == VoidCraft.blocks.blockNoBreak) {
			TileEntityRendererDispatcher.instance.renderTileEntityAt(noBreakInstance, 0.0D, 0.0D, 0.0D, 0.0F);
		}else if(block == VoidCraft.blocks.voidicCharger) {
			TileEntityRendererDispatcher.instance.renderTileEntityAt(voidicChargerInstance, 0.0D, 0.0D, 0.0D, 0.0F);
		}else{
			super.renderByItem(itemStack);
		}
	}

}
