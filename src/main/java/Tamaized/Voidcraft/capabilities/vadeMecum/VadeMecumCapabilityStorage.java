package Tamaized.Voidcraft.capabilities.vadeMecum;

import java.util.ArrayList;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class VadeMecumCapabilityStorage implements IStorage<IVadeMecumCapability> {

	public VadeMecumCapabilityStorage() {
		VoidCraft.instance.logger.info("VadeMecumCapabilityStorage Registered");
	}

	@Override
	public NBTBase writeNBT(Capability<IVadeMecumCapability> capability, IVadeMecumCapability instance, EnumFacing side) {
		NBTTagCompound compound = new NBTTagCompound();
		ArrayList<Integer> array = new ArrayList<Integer>();
		{
			for (IVadeMecumCapability.Category category : instance.getObtainedCategories()) {
				array.add(IVadeMecumCapability.getCategoryID(category));
			}
			compound.setIntArray("category", array.stream().mapToInt(i -> i).toArray());
		}
		compound.setInteger("currentActive", IVadeMecumCapability.getCategoryID(instance.getCurrentActive()));
		compound.setString("lastEntry", instance.getLastEntry());
		return compound;
	}

	@Override
	public void readNBT(Capability<IVadeMecumCapability> capability, IVadeMecumCapability instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound compound = (NBTTagCompound) nbt;
		int[] array;
		{
			ArrayList<IVadeMecumCapability.Category> list = new ArrayList<IVadeMecumCapability.Category>();
			array = compound.getIntArray("category");
			for (int i = 0; i < array.length; i++) {
				list.add(IVadeMecumCapability.getCategoryFromID(array[i]));
			}
			instance.setObtainedCategories(list);
		}
		instance.setCurrentActive(IVadeMecumCapability.getCategoryFromID(compound.getInteger("currentActive")));
		instance.setLastEntry(compound.getString("lastEntry"));
		instance.setLoaded();
	}

}
