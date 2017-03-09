package Tamaized.Voidcraft.starforge.effects.wep.tier3;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import Tamaized.Voidcraft.starforge.effects.IStarForgeEffect;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StarForgeEffectCripplingVoid implements IStarForgeEffect {

	@Override
	public Type getType() {
		return Type.SWORD;
	}

	@Override
	public Tier getTier() {
		return Tier.THREE;
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
		if (entityHit.world.rand.nextInt(100) < 20) {
			if (entityHit instanceof EntityLivingBase) {
				EntityLivingBase livingHit = (EntityLivingBase) entityHit;
				livingHit.addPotionEffect(new PotionEffect(VoidCraft.potions.voidicInfusion, 20 * 15));
			}
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
		return ("" + I18n.format("voidcraft.VadeMecum.docs.title.starforge.effect.voidCripple", new Object[0])).trim();
	}

}
