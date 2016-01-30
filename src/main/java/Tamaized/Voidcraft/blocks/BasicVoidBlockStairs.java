package Tamaized.Voidcraft.blocks;

import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.registry.IBasicVoid;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BasicVoidBlockStairs extends BlockStairs implements IBasicVoid{
	
	private final String name;

	protected BasicVoidBlockStairs(IBlockState modelState, String n) {
		super(modelState);
		name = n;
		setUnlocalizedName(name);
		GameRegistry.registerBlock(this, n);
	}
	
	@Override
	public String getName() {
		return name;
	}

}
