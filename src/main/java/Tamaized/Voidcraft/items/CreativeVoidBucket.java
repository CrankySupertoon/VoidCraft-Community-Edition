package Tamaized.Voidcraft.items;

import javax.annotation.Nullable;

import Tamaized.TamModized.items.TamItem;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.fluids.UniversalBucketCapWrapper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class CreativeVoidBucket extends TamItem {

	public CreativeVoidBucket(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitY, float hitZ, float p_180614_9_) {
		switch (facing) {
			default:
			case UP: {
				pos = pos.add(0, 1, 0);
			}
				break;
			case DOWN: {
				pos = pos.add(0, -1, 0);
			}
				break;
			case NORTH: {
				pos = pos.add(0, 0, -1);
			}
				break;
			case SOUTH: {
				pos = pos.add(0, 0, 1);
			}
				break;
			case EAST: {
				pos = pos.add(1, 0, 0);
			}
				break;
			case WEST: {
				pos = pos.add(-1, 0, 0);
			}
				break;
		}
		if (worldIn.isAirBlock(pos)) {
			worldIn.setBlockState(pos, VoidCraft.fluids.voidFluidBlock.getDefaultState());
		}
		return EnumActionResult.SUCCESS;
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, @Nullable net.minecraft.nbt.NBTTagCompound nbt) {
		return new UniversalBucketCapWrapper(stack) {
			@Override
			public FluidStack getFluid() {
				return new FluidStack(VoidCraft.fluids.voidFluid, 1000);
			}
		};
	}

}
