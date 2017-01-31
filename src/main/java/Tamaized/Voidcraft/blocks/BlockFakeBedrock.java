package Tamaized.Voidcraft.blocks;

import Tamaized.TamModized.blocks.TamBlock;
import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.IBlockAccess;

public class BlockFakeBedrock extends TamBlock {

	public BlockFakeBedrock(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness);
	}

	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		return true;
	}

}
