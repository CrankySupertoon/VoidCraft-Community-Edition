package Tamaized.Voidcraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import Tamaized.Voidcraft.common.voidCraft;

public class BlockVoidcrystal extends BasicVoidBlock {

	public BlockVoidcrystal(Material Material, String s) {
		super(Material, s);
	}
	
	/**
	* Is this block (a) opaque and (B) a full 1m cube? This determines whether or not to render the shared face of two
	* adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	*/
	public boolean isOpaqueCube(){
	return false;
	}
	
	public int getRenderBlockPass(){
        return 1;
    }
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return voidCraft.items.voidcrystal;
    }
	
	public int quantityDropped(Random random){
		return 9;
	}

}