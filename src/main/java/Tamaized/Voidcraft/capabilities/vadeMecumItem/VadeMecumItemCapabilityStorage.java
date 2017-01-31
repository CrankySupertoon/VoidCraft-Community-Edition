package Tamaized.Voidcraft.capabilities.vadeMecumItem;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class VadeMecumItemCapabilityStorage implements IStorage<IVadeMecumItemCapability> {

	public VadeMecumItemCapabilityStorage() {
		VoidCraft.instance.logger.info("VadeMecumItemCapabilityStorage Registered");
	}

	@Override
	public NBTBase writeNBT(Capability<IVadeMecumItemCapability> capability, IVadeMecumItemCapability instance, EnumFacing side) {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setBoolean("bookstate", instance.getBookState());
		return compound;
	}

	@Override
	public void readNBT(Capability<IVadeMecumItemCapability> capability, IVadeMecumItemCapability instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound compound = (NBTTagCompound) nbt;
		instance.setBookState(compound.getBoolean("bookstate"));
		instance.setLoaded();
	}

}
