package Tamaized.Voidcraft.capabilities.starforge;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.starforge.effects.IStarForgeEffect;
import Tamaized.Voidcraft.starforge.effects.StarForgeEffectList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StarForgeCapabilityHandler implements IStarForgeCapability {

	public static final ResourceLocation ID = new ResourceLocation(VoidCraft.modid, "StarForgeCapabilityHandler");

	private IStarForgeEffect tier1;
	private IStarForgeEffect tier2;
	private IStarForgeEffect tier3;

	private boolean dirty = false;

	@Override
	public void addEffect(IStarForgeEffect effect) {
		if (effect != null) setEffect(effect.getTier(), effect);
	}

	@Override
	public void setEffect(IStarForgeEffect.Tier tier, IStarForgeEffect effect) {
		switch (tier) {
			case ONE:
				if (effect != null && tier != effect.getTier()) break;
				tier1 = effect;
				break;
			case TWO:
				if (effect != null && tier != effect.getTier()) break;
				tier2 = effect;
				break;
			case THREE:
				if (effect != null && tier != effect.getTier()) break;
				tier3 = effect;
				break;
			default:
				break;
		}
		dirty = true;
	}

	@Override
	public IStarForgeEffect getEffect(IStarForgeEffect.Tier tier) {
		switch (tier) {
			case ONE:
				return tier1;
			case TWO:
				return tier2;
			case THREE:
				return tier3;
			default:
				return null;
		}
	}

	@Override
	public void clearEffects() {
		tier1 = null;
		tier2 = null;
		tier3 = null;
		dirty = true;
	}

	@Override
	public float getSpeedMod() {
		return (tier1 == null ? 0.0F : tier1.getSpeedMod()) + (tier2 == null ? 0.0F : tier2.getSpeedMod()) + (tier3 == null ? 0.0F : tier3.getSpeedMod());
	}

	@Override
	public void onEntityHit(Entity entityUser, Entity entityHit) {
		if (tier1 != null) tier1.onEntityHit(entityUser, entityHit);
		if (tier2 != null) tier2.onEntityHit(entityUser, entityHit);
		if (tier3 != null) tier3.onEntityHit(entityUser, entityHit);
	}

	@Override
	public void onBlockBreak(Entity entityUser, World world, IBlockState state, BlockPos pos, EnumFacing face) {
		if (tier1 != null) tier1.onBlockBreak(entityUser, world, state, pos, face);
		if (tier2 != null) tier2.onBlockBreak(entityUser, world, state, pos, face);
		if (tier3 != null) tier3.onBlockBreak(entityUser, world, state, pos, face);
	}

	@Override
	public boolean onRightClick(Entity entityUser) {
		boolean flag = false;
		if (tier1 != null) if (tier1.onRightClick(entityUser)) flag = true;
		if (tier2 != null) if (tier2.onRightClick(entityUser)) flag = true;
		if (tier3 != null) if (tier3.onRightClick(entityUser)) flag = true;
		return flag;
	}

	@Override
	public boolean onRightClickBlock(Entity entityUser, World world, IBlockState state, BlockPos pos, EnumFacing face) {
		boolean flag = false;
		if (tier1 != null) if (tier1.onRightClickBlock(entityUser, world, state, pos, face)) flag = true;
		if (tier2 != null) if (tier2.onRightClickBlock(entityUser, world, state, pos, face)) flag = true;
		if (tier3 != null) if (tier3.onRightClickBlock(entityUser, world, state, pos, face)) flag = true;
		return flag;
	}

	@Override
	public void update(World world, ItemStack stack) {
		if (tier1 != null) tier1.update(stack);
		if (tier2 != null) tier2.update(stack);
		if (tier3 != null) tier3.update(stack);
		if (world.isRemote) {
			updateClient(stack);
		} else {
			if (dirty) {
				sendClientUpdates(stack);
				dirty = false;
			}
		}
	}

	private void sendClientUpdates(ItemStack stack) {
		NBTTagCompound ct = stack.getOrCreateSubCompound(VoidCraft.modid);
		NBTTagCompound starforgeCompound = new NBTTagCompound();
		starforgeCompound.setInteger("tier1", StarForgeEffectList.getEffectID(tier1));
		starforgeCompound.setInteger("tier2", StarForgeEffectList.getEffectID(tier2));
		starforgeCompound.setInteger("tier3", StarForgeEffectList.getEffectID(tier3));
		ct.setTag("StarForge_Client", starforgeCompound);
	}

	private void updateClient(ItemStack stack) {
		NBTTagCompound ct = stack.getOrCreateSubCompound(VoidCraft.modid);
		NBTTagCompound starforgeCompound = ct.getCompoundTag("StarForge_Client");
		setEffect(IStarForgeEffect.Tier.ONE, StarForgeEffectList.getEffect(starforgeCompound.getInteger("tier1")));
		setEffect(IStarForgeEffect.Tier.TWO, StarForgeEffectList.getEffect(starforgeCompound.getInteger("tier2")));
		setEffect(IStarForgeEffect.Tier.THREE, StarForgeEffectList.getEffect(starforgeCompound.getInteger("tier3")));
	}

}
