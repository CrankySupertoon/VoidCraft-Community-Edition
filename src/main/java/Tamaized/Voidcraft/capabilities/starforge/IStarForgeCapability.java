package Tamaized.Voidcraft.capabilities.starforge;

import Tamaized.Voidcraft.starforge.effects.IStarForgeEffect;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IStarForgeCapability {
	
	void addEffect(IStarForgeEffect effect);
	
	void setEffect(IStarForgeEffect.Tier tier, IStarForgeEffect effect);
	
	IStarForgeEffect getEffect(IStarForgeEffect.Tier tier);
	
	void clearEffects();
	
	float getSpeedMod();
	
	void onEntityHit(Entity entityUser, Entity entityHit);
	
	void onBlockBreak(Entity entityUser, World world, IBlockState state, BlockPos pos, EnumFacing face);
	
	boolean onRightClick(Entity entityUser);
	
	boolean onRightClickBlock(Entity entityUser, World world, IBlockState state, BlockPos pos, EnumFacing face);
	
	void update(World world, ItemStack stack);
	
}
