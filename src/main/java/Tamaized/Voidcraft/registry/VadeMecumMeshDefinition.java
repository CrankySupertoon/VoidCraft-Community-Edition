package Tamaized.Voidcraft.registry;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecumItem.IVadeMecumItemCapability;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class VadeMecumMeshDefinition implements ItemMeshDefinition {
	
	private static final ModelResourceLocation closed = new ModelResourceLocation(new ResourceLocation(VoidCraft.modid, "items/vademecum"),  "inventory");
	private static final ModelResourceLocation open = new ModelResourceLocation(new ResourceLocation(VoidCraft.modid, "items/vademecum_open"),  "inventory");
	
	public static void preRegister(){
		//ModelLoader.setCustomModelResourceLocation(voidCraft.items.vadeMecum, 0, closed);
		//ModelLoader.setCustomModelResourceLocation(voidCraft.items.vadeMecum, 0, open);
		ModelBakery.registerItemVariants(VoidCraft.items.vadeMecum, closed, open);
		//ModelBakery.registerItemVariants(voidCraft.items.vadeMecum, open);
	}
	
	public static void register(){
	}

	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack) {
		IVadeMecumItemCapability cap = stack.getCapability(CapabilityList.VADEMECUMITEM, null);
		if(cap != null && cap.getBookState()) return open;
		return closed;
	}

}
