package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class VoidCraftCreativeTabs implements ITamRegistry {

	public static CreativeTabs tabVoid;
	public static CreativeTabs tForge;

	@Override
	public void preInit() {
		tabVoid = new CreativeTabs("tabVoid") {
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(VoidCraft.blocks.blockPortalVoid);
			}
		};
		/*
		 * tForge = new CreativeTabs("tForge") {
		 * @Override public Item getTabIconItem() { return Item.getItemFromBlock(voidCraft.blocks.blockNoBreak); } };
		 */}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<ITamModel> getModelList() {
		return new ArrayList<ITamModel>();
	}

	@Override
	public String getModID() {
		return VoidCraft.modid;
	}

	@Override
	public void clientPreInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clientInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clientPostInit() {
		// TODO Auto-generated method stub

	}

}
