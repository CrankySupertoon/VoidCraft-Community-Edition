package Tamaized.Voidcraft.fluids;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;

/**
 * This is a wrapper to be used as an anonymous class for a custom getFluid()
 */
public abstract class UniversalBucketCapWrapper extends FluidBucketWrapper { // TODO: Move this into TamModized

	public UniversalBucketCapWrapper(ItemStack container) {
		super(container);
	}
	
    public abstract FluidStack getFluid();

}
