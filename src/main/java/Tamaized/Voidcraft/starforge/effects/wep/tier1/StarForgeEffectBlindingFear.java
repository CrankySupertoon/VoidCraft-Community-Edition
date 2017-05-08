package Tamaized.Voidcraft.starforge.effects.wep.tier1;

import Tamaized.TamModized.helper.TranslateHelper;
import Tamaized.Voidcraft.starforge.effects.IStarForgeEffect;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StarForgeEffectBlindingFear implements IStarForgeEffect {

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
			EntityLivingBase livingHit = (EntityLivingBase) entityHit;
			livingHit.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 20 * 10, 5));
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
		return TranslateHelper.translate("voidcraft.VadeMecum.docs.title.starforge.effect.blindingFear");
	}

}
