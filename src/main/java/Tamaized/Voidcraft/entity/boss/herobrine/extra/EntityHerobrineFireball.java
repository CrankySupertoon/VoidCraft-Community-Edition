package Tamaized.Voidcraft.entity.boss.herobrine.extra;

import Tamaized.Voidcraft.blocks.AIBlock;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;
import Tamaized.Voidcraft.entity.EntityVoidBoss;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityHerobrineFireball extends EntityFireball {

	public int field_92057_e = 1;

	public EntityHerobrineFireball(World p_i1767_1_) {
		super(p_i1767_1_);
	}

	@SideOnly(Side.CLIENT)
	public EntityHerobrineFireball(World p_i1768_1_, double p_i1768_2_, double p_i1768_4_, double p_i1768_6_, double p_i1768_8_, double p_i1768_10_, double p_i1768_12_) {
		super(p_i1768_1_, p_i1768_2_, p_i1768_4_, p_i1768_6_, p_i1768_8_, p_i1768_10_, p_i1768_12_);
	}

	public EntityHerobrineFireball(World p_i1769_1_, EntityLivingBase p_i1769_2_, double p_i1769_3_, double p_i1769_5_, double p_i1769_7_) {
		super(p_i1769_1_, p_i1769_2_, p_i1769_3_, p_i1769_5_, p_i1769_7_);
	}

	/**
	 * Called when this EntityFireball hits a block or entity.
	 */
	@Override
	protected void onImpact(RayTraceResult p_70227_1_) {
		if (!this.world.isRemote && !(p_70227_1_.entityHit != null && p_70227_1_.entityHit instanceof EntityVoidBoss)) {
			if (p_70227_1_.entityHit != null) {
				p_70227_1_.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 45.0F);
			} else if (p_70227_1_.typeOfHit == RayTraceResult.Type.BLOCK) {
				Block b = world.getBlockState(p_70227_1_.getBlockPos()).getBlock();
				if (b instanceof AIBlock) {
					TileEntity te = ((AIBlock) b).getMyTileEntity(this.world, p_70227_1_.getBlockPos());
					if (te instanceof TileEntityAIBlock) {
						((TileEntityAIBlock) te).boom();
					}
				}
			}

			this.world.newExplosion((Entity) null, this.posX, this.posY, this.posZ, (float) this.field_92057_e, true, false);
			this.setDead();
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		super.writeEntityToNBT(p_70014_1_);
		p_70014_1_.setInteger("ExplosionPower", this.field_92057_e);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		super.readEntityFromNBT(p_70037_1_);

		if (p_70037_1_.hasKey("ExplosionPower", 99)) {
			this.field_92057_e = p_70037_1_.getInteger("ExplosionPower");
		}
	}
}
