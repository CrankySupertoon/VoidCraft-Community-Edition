package Tamaized.Voidcraft.starforge.effects;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IStarForgeEffect {

	enum Type {
		SWORD, TOOL
	}

	Type getType();
	
	enum Tier {
		ONE, TWO, THREE
	}
	
	Tier getTier();
	
	void update(ItemStack stack);
	
	float getSpeedMod();

	void onEntityHit(Entity entityUser, Entity entityHit);

	void onBlockBreak(Entity entityUser, World world, IBlockState state, BlockPos pos, EnumFacing face);

	boolean onRightClick(Entity entityUser);

	boolean onRightClickBlock(Entity entityUser, World world, IBlockState state, BlockPos pos, EnumFacing face);
	
	String getName();

}
