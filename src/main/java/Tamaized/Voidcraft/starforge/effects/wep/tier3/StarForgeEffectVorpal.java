package Tamaized.Voidcraft.starforge.effects.wep.tier3;

import Tamaized.Voidcraft.starforge.effects.IStarForgeEffect;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StarForgeEffectVorpal implements IStarForgeEffect {

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
		if (!entityHit.world.isRemote && entityHit.world.rand.nextInt(100) < 5) {
			if (entityHit instanceof EntityPlayer) {
				ItemStack stack = new ItemStack(Items.SKULL, 1, 1);
				if(stack.getTagCompound() == null) stack.setTagCompound(new NBTTagCompound());
				stack.getTagCompound().setTag("SkullOwner", new NBTTagString(((EntityPlayer)entityHit).getName()));
				entityHit.entityDropItem(stack, 0.0F);
			} else if (entityHit instanceof EntityZombie) {
				entityHit.entityDropItem(new ItemStack(Items.SKULL, 1, 2), 0.0F);
			} else if (entityHit instanceof EntityCreeper) {
				entityHit.entityDropItem(new ItemStack(Items.SKULL, 1, 4), 0.0F);
			} else if (entityHit instanceof EntitySkeleton) {
				entityHit.entityDropItem(new ItemStack(Items.SKULL, 1, 0), 0.0F);
			} else if (entityHit instanceof EntityWitherSkeleton) {
				entityHit.entityDropItem(new ItemStack(Items.SKULL, 1, 1), 0.0F);
			}else{
				return;
			}
			entityHit.setDead();
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
		return "Vorpal";
	}

}
