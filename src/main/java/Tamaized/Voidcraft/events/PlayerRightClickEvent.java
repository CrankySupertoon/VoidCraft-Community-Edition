package Tamaized.Voidcraft.events;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.boss.dragon.sub.voidic.EntityVoidicDragon;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerRightClickEvent {

	@SubscribeEvent
	public void rightClick(PlayerInteractEvent.RightClickBlock e) {
		World world = e.getWorld();
		BlockPos pos = e.getPos();
		ItemStack stack = e.getItemStack();
		IBlockState state = world.getBlockState(pos);
		if (state != null && state.getBlock() == Blocks.DRAGON_EGG && !stack.isEmpty() && stack.getItem() == VoidCraft.items.voidStar) {
			if(!world.isRemote){
				world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 0, true);
				world.setBlockToAir(pos);
				EntityVoidicDragon dragon = new EntityVoidicDragon(world);
				dragon.setPositionAndUpdate(pos.getX(), pos.getY() + 20, pos.getZ());
				world.createExplosion(null, pos.getX(), pos.getY() + 20, pos.getZ(), 0, true);
				world.spawnEntity(dragon);
				stack.shrink(1);
			}
			e.setCanceled(true);
		}
	}

}
