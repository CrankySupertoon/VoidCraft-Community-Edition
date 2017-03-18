package Tamaized.Voidcraft.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import Tamaized.TamModized.registry.ITamModel;
import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDreamBed extends BlockBed implements ITamModel {

	private final String name;

	public BlockDreamBed(CreativeTabs tab, String n) {
		super();
		name = n;
		setUnlocalizedName(name);
		setLightLevel(1.0F);
		GameRegistry.register(this.setRegistryName(getModelDir() + "/" + getName()));
		GameRegistry.register(new ItemBlock(this).setRegistryName(getModelDir() + "/" + getName()));
		this.setCreativeTab(tab);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getModelDir() {
		return "blocks";
	}

	@Override
	public Item getAsItem() {
		return VoidCraft.items.dreamBed;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.AIR;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) VoidCraft.instance.VoidTickEvent.dream(playerIn);
		return true;
	}

}
