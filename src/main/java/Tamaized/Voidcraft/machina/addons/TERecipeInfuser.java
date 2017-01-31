package Tamaized.Voidcraft.machina.addons;

import Tamaized.TamModized.tileentity.TamTileEntityRecipeList;
import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.item.ItemStack;

public class TERecipeInfuser extends TamTileEntityRecipeList<TERecipeInfuser.InfuserRecipe> {

	@Override
	protected String getName() {
		return "Infuser";
	}

	@Override
	protected String getModID() {
		return VoidCraft.modid;
	}

	public class InfuserRecipe extends TamTileEntityRecipeList.TamTERecipe {

		private final int fluidAmount;

		public InfuserRecipe(ItemStack[] input, ItemStack output, int requiredFluid) {
			super(input, output);
			fluidAmount = requiredFluid;
		}

		public int getRequiredFluid() {
			return fluidAmount;
		}

	}

}
