package tamaized.voidcraft.common.starforge.effects.wep.tier2;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tamaized.tammodized.common.helper.TranslateHelper;
import tamaized.voidcraft.common.damagesources.DamageSourceVoidicInfusion;
import tamaized.voidcraft.common.starforge.effects.IStarForgeEffect;

public class StarForgeEffectVoidWrath implements IStarForgeEffect {

	@Override
	public Type getType() {
		return Type.SWORD;
	}

	@Override
	public Tier getTier() {
		return Tier.TWO;
	}

	@Override
	public void update(ItemStack stack) {

	}

	@Override
	public float getSpeedMod() {
		return 0;
	}

	@Override
	public void onEntityHit(Entity entityUser, Entity entityHit) {
		entityHit.attackEntityFrom(new DamageSourceVoidicInfusion(), 5.0F);
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
		return TranslateHelper.translate("voidcraft.VadeMecum.docs.title.starforge.effect.voidWrath");
	}

}
