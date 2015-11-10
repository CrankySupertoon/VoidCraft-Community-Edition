package Tamaized.Voidcraft.Addons.thaumcraft;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchCategories;
import Tamaized.Voidcraft.Addons.thaumcraft.Research.CorruptedSwordResearch;
import Tamaized.Voidcraft.Addons.thaumcraft.Research.VoidCrystalResearch;

public class VoidCraftResearch {
	
	private VoidCraftThaumRecipes recipes;
	
	public VoidCraftResearch(VoidCraftThaumRecipes r){
		recipes = r;
	}
	
	public void register(){
		ResearchCategories.registerCategory("VoidCraft", new ResourceLocation("VoidCraft:textures/items/Thaumcraft/corruptedSword.png.png"), new ResourceLocation("VoidCraft:textures/GUI/Thaumcraft/VoidBG-3.gif"));
		
		ResearchCategories.addResearch(new VoidCrystalResearch(recipes));
		ResearchCategories.addResearch(new CorruptedSwordResearch(recipes));
	}

}
