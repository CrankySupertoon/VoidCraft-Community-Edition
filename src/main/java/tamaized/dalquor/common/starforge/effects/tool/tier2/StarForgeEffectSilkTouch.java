package tamaized.dalquor.common.starforge.effects.tool.tier2;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tamaized.dalquor.common.starforge.effects.IStarForgeEffect;

public class StarForgeEffectSilkTouch implements IStarForgeEffect {

	@Override
	public Type getType() {
		return Type.TOOL;
	}

	@Override
	public Tier getTier() {
		return Tier.TWO;
	}

	@Override
	public void update(ItemStack stack) {
		if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) < 1)
			stack.addEnchantment(Enchantments.SILK_TOUCH, 1);
	}

	@Override
	public float getSpeedMod() {
		return 0;
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
		return I18n.format("dalquor.VadeMecum.docs.title.starforge.effect.silkTouch");
	}

}
