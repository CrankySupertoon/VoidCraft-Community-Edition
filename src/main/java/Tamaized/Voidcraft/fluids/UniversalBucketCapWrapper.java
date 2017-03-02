package Tamaized.Voidcraft.fluids;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;

public abstract class UniversalBucketCapWrapper extends FluidBucketWrapper {

	public UniversalBucketCapWrapper(ItemStack container) {
		super(container);
	}
	

    public abstract FluidStack getFluid();

}
