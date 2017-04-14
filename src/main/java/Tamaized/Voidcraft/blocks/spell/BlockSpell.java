package Tamaized.Voidcraft.blocks.spell;

import Tamaized.TamModized.blocks.TamBlockContainer;
import Tamaized.Voidcraft.blocks.spell.tileentity.TileEntitySpell;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockSpell extends TamBlockContainer {

	public BlockSpell(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness, SoundType.GLASS);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return createNewTileEntitySpell(worldIn, meta);
	}
	
	protected abstract TileEntitySpell createNewTileEntitySpell(World world, int meta);

}
