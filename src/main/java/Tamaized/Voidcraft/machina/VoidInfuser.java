package Tamaized.Voidcraft.machina;

import java.util.Random;

import Tamaized.TamModized.blocks.TamBlockContainer;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidInfuser;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VoidInfuser extends TamBlockContainer {
	private Random rand = new Random();

	public VoidInfuser(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = playerIn.getHeldItem(hand);
		if (worldIn.isRemote) {
			return true;
		} else {
			FMLNetworkHandler.openGui(playerIn, VoidCraft.instance, GuiHandler.getTypeID(GuiHandler.Type.Infuser), worldIn, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		int l = MathHelper.floor((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			world.setBlockState(pos, this.getStateFromMeta(2), 2);
		}

		if (l == 1) {
			world.setBlockState(pos, this.getStateFromMeta(5), 2);
		}

		if (l == 2) {
			world.setBlockState(pos, this.getStateFromMeta(3), 2);
		}

		if (l == 3) {
			world.setBlockState(pos, this.getStateFromMeta(4), 2);
		}

	}

	/**
	 * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old metadata
	 */
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {

		TileEntityVoidInfuser tileentity = (TileEntityVoidInfuser) world.getTileEntity(pos);

		if (tileentity != null) {
			for (int i = 0; i < tileentity.getSizeInventory(); i++) {
				ItemStack itemstack = tileentity.getStackInSlot(i);

				if (!itemstack.isEmpty()) {
					float f = this.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

					while (itemstack.getCount() > 0) {
						int j = this.rand.nextInt(21);

						if (j > itemstack.getCount()) {
							j = itemstack.getCount();
						}

						itemstack.shrink(j);

						EntityItem item = new EntityItem(world, (double) ((float) pos.getX() + f), (double) ((float) pos.getY() + f1), (double) ((float) pos.getZ() + f2), itemstack);

						if (itemstack.hasTagCompound()) {
							item.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						item.motionX = (double) ((float) this.rand.nextGaussian() * f3);
						item.motionY = (double) ((float) this.rand.nextGaussian() * f3 + 0.2F);
						item.motionZ = (double) ((float) this.rand.nextGaussian() * f3);

						world.spawnEntity(item);
					}
				}
			}

			// world.func_96440_m(x, y, z, oldBlockID);
		}

		super.breakBlock(world, pos, state);
	}

	@SideOnly(Side.CLIENT)
	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		double d0 = (double) ((float) pos.getX() + 0.4F + rand.nextFloat() * 0.2F);
		double d1 = (double) ((float) pos.getY() + 0.0F + rand.nextFloat() * 0.3F);
		double d2 = (double) ((float) pos.getZ() + 0.4F + rand.nextFloat() * 0.2F);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.5D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 1.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, -1.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.0D, 1.0D);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.0D, -1.0D);

		d0 = (double) ((float) pos.getX() + 0.4F + rand.nextFloat() * 0.2F);
		d1 = (double) ((float) pos.getY() + 0.0F + rand.nextFloat() * 0.3F);
		d2 = (double) ((float) pos.getZ() + 0.4F + rand.nextFloat() * 0.2F);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.5D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 1.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, -1.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.0D, 1.0D);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.0D, -1.0D);
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		TileEntityVoidInfuser te = new TileEntityVoidInfuser();
		return te;
	}
}
