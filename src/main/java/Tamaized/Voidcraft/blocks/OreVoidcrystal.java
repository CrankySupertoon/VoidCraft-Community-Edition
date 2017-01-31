package Tamaized.Voidcraft.blocks;

import java.util.Random;

import Tamaized.TamModized.blocks.TamBlock;
import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OreVoidcrystal extends TamBlock {

	public static final PropertyBool VOID = PropertyBool.create("void");

	public OreVoidcrystal(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VOID, true));
	}

	public IBlockState getStateVoidFalse() {
		return getDefaultState().withProperty(VOID, false);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if (world.provider.getDimension() == VoidCraft.config.getDimensionIDvoid() && !state.getValue(VOID)) world.setBlockState(pos, state.withProperty(VOID, true), 2);
		else if (world.provider.getDimension() != VoidCraft.config.getDimensionIDvoid() && state.getValue(VOID)) world.setBlockState(pos, state.withProperty(VOID, false), 2);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VOID });
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(VOID) ? 1 : 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VOID, meta == 0 ? false : true);
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return VoidCraft.items.voidcrystal;
	}

	public int quantityDropped(Random random) {
		return 1 + random.nextInt(3);
	}

	@Override
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
		if (entity instanceof EntityDragon) return false;
		return true;
	}

	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		return true;
	}

}
