package Tamaized.Voidcraft.fluids;

import java.util.Random;

import Tamaized.TamModized.registry.ITamModel;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TamFluidFiniteBlock extends BlockFluidFinite implements ITamModel { // TODO: MOVE THIS INTO TAMMODIZED

	private final String name;
	private final DamageSource damageSource;
	private final int damage;

	public TamFluidFiniteBlock(CreativeTabs tab, Fluid fluid, Material material, String name, DamageSource source, int dmg) {
		super(fluid, material);
		this.name = name;
		damageSource = source;
		damage = dmg;
		setUnlocalizedName(name);
		GameRegistry.register(this.setRegistryName(getName()));
		GameRegistry.register(new ItemBlock(this).setRegistryName(getName()));
		this.setCreativeTab(tab);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getModelDir() {
		return "fluids";
	}

	@Override
	public Item getAsItem() {
		return Item.getItemFromBlock(this);
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
		entityIn.attackEntityFrom(damageSource, damage);
	}

}
