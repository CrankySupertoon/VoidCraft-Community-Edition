package Tamaized.Voidcraft.fluids;

import Tamaized.TamModized.fluids.TamFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class ArcaneSludgeFluidBlock extends TamFluidBlock {

	public ArcaneSludgeFluidBlock(CreativeTabs tab, Fluid fluid, Material material, String name) {
		super(tab, fluid, material, name);
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
		entityIn.attackEntityFrom(DamageSource.MAGIC, 5.0F);
	}

}
