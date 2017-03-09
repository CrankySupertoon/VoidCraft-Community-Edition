package Tamaized.Voidcraft.starforge.effects.tool.tier1;

import Tamaized.Voidcraft.starforge.effects.IStarForgeEffect;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StarForgeEffectHaste implements IStarForgeEffect {

	@Override
	public Type getType() {
		return Type.TOOL;
	}

	@Override
	public Tier getTier() {
		return Tier.ONE;
	}

	@Override
	public void update(ItemStack stack) {

	}

	@Override
	public float getSpeedMod() {
		return 5.0F;
	}

	@Override
	public void onEntityHit(Entity entityUser, Entity entityHit) {

	}

	@Override
	public void onBlockBreak(Entity entityUser, World world, IBlockState state, BlockPos pos, EnumFacing face) {

	}

	@Override
	public boolean onRightClick(Entity entityUser) {
		return false;
	}

	@Override
	public boolean onRightClickBlock(Entity entityUser, World world, IBlockState state, BlockPos pos, EnumFacing face) {
		return false;
	}

	@Override
	public String getName() {
		return ("" + I18n.format("voidcraft.VadeMecum.docs.title.starforge.effect.haste", new Object[0])).trim();
	}

}
