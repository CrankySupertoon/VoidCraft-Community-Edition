package tamaized.dalquor.common.starforge.effects.wep.tier1;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tamaized.dalquor.common.starforge.effects.IStarForgeEffect;
import tamaized.dalquor.registry.VoidCraftPotions;

public class StarForgeEffectVoidTouch implements IStarForgeEffect {

	@Override
	public Type getType() {
		return Type.SWORD;
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
		return 0;
	}

	@Override
	public void onEntityHit(Entity entityUser, Entity entityHit) {
		if (entityHit instanceof EntityLivingBase) {
			((EntityLivingBase) entityHit).addPotionEffect(new PotionEffect(VoidCraftPotions.voidicInfusion, 20 * 3));
		}
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
		return I18n.format("voidcraft.VadeMecum.docs.title.starforge.effect.voidTouch");
	}

}
