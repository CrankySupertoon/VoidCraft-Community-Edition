package Tamaized.Voidcraft.registry;

import Tamaized.TamModized.fluids.TamFluidBlock;
import Tamaized.TamModized.registry.TamFluidRegistryBase;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.damageSources.DamageSourceAcid;
import Tamaized.Voidcraft.fluids.TamFluidFiniteBlock;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.Fluid;

public class VoidCraftFluids extends TamFluidRegistryBase {

	public static Fluid voidFluid;
	public static Fluid acidFluid;

	public static MaterialLiquid voidMaterialLiquid;
	public static MaterialLiquid acidMaterialLiquid;

	public static TamFluidBlock voidFluidBlock;
	public static TamFluidFiniteBlock acidFluidBlock;

	public static BucketWrapper voidBucket;

	@Override
	public void preInit() {
		voidFluid = createFluid("void", "fluids/void/fluid", true, true);
		acidFluid = createFluid("acid", "fluids/acid/fluid", true, false);

		voidFluid.setLuminosity(3).setDensity(-400).setViscosity(1500).setGaseous(true);
		acidFluid.setLuminosity(7).setDensity(2).setViscosity(500).setGaseous(false);

		voidMaterialLiquid = new MaterialLiquid(MapColor.PURPLE);
		acidMaterialLiquid = new MaterialLiquid(MapColor.GREEN);

		register(voidFluidBlock = new TamFluidBlock(VoidCraft.tabs.tabVoid, voidFluid, Material.WATER, "blockvoidfluid"));
		register(acidFluidBlock = new TamFluidFiniteBlock(VoidCraft.tabs.tabVoid, acidFluid, Material.WATER, "blockacidfluid", new DamageSourceAcid(), 5));

		voidBucket = new BucketWrapper(ForgeModContainer.getInstance().universalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, voidFluid));
	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {

	}

	@Override
	public String getModID() {
		return VoidCraft.modid;
	}

	@Override
	public void clientInit() {

	}

	@Override
	public void clientPostInit() {

	}

	public class BucketWrapper {

		private final ItemStack bucket;

		public BucketWrapper(ItemStack stack) {
			bucket = stack;
		}

		public ItemStack getBucket() {
			return bucket.copy();
		}

	}

}
