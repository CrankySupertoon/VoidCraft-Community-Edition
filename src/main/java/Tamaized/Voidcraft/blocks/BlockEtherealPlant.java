package Tamaized.Voidcraft.blocks;

import java.util.ArrayList;
import java.util.Random;

import Tamaized.TamModized.blocks.TamBlockCrops;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityFakeBedrockFarmland;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class BlockEtherealPlant extends TamBlockCrops {

	protected static final AxisAlignedBB[] AABB = new AxisAlignedBB[] { new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.125D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.25D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.375D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.5D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.625D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.75D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.875D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D) };
	private static final ArrayList<Block> plantableBlocks = new ArrayList<Block>();

	public BlockEtherealPlant(CreativeTabs tab, String n, float hardness) {
		super(tab, Material.PLANTS, n, hardness);
		plantableBlocks.add(voidCraft.blocks.blockFakeBedrockFarmland);
	}

	@Override
	protected AxisAlignedBB[] getBounds() {
		return AABB;
	}

	@Override
	protected ArrayList<Block> plantableBlocks() {
		return plantableBlocks;
	}

	@Override
	protected boolean isCorrectLightLevel(int currentLight) {
		//System.out.println(currentLight);
		return currentLight <= 2;
	}

	@Override
	protected Item getSeed() {
		return voidCraft.items.etherealSeed;
	}

	@Override
	protected Item getCrop() {
		return voidCraft.items.etherealFruit;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (getAge(state) >= getMaxAge()) {
			if (worldIn.isRemote) return true;
			TileEntity tileEntity = worldIn.getTileEntity(pos.down());
			int meta = 0;
			if (tileEntity instanceof TileEntityFakeBedrockFarmland) {
				TileEntityFakeBedrockFarmland soil = (TileEntityFakeBedrockFarmland) tileEntity;
				meta = soil.getAlterationValue(soil.getAlteration());
				soil.setAlteration(TileEntityFakeBedrockFarmland.Alteration.NORMAL);
			}
			Random rand = worldIn.rand;
			int a = rand.nextInt(3);
			if (a > 0) {
				ItemStack newStack = new ItemStack(voidCraft.items.etherealFruit, a);
				newStack.getSubCompound(voidCraft.modid, true).setInteger("alteration", meta);
				ItemHandlerHelper.giveItemToPlayer(player, newStack);
			}
			int amount = rand.nextInt(3);
			if (amount > 0) ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(getSeed(), amount));
			worldIn.setBlockToAir(pos);
			return true;
		}
		return false;
	}

}