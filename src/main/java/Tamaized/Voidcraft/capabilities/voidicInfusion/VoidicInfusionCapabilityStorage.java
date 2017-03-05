package Tamaized.Voidcraft.capabilities.voidicInfusion;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class VoidicInfusionCapabilityStorage implements IStorage<IVoidicInfusionCapability> {

	public VoidicInfusionCapabilityStorage() {
		VoidCraft.instance.logger.info("VoidicInfusionCapabilityStorage Registered");
	}

	@Override
	public NBTBase writeNBT(Capability<IVoidicInfusionCapability> capability, IVoidicInfusionCapability instance, EnumFacing side) {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("infusion", instance.getInfusion());
		compound.setInteger("maxInfusion", instance.getMaxInfusion());
		compound.setFloat("xiaDefeats", instance.getXiaDefeats());
		return compound;
	}

	@Override
	public void readNBT(Capability<IVoidicInfusionCapability> capability, IVoidicInfusionCapability instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound compound = (NBTTagCompound) nbt;
		instance.setInfusion(compound.getInteger("infusion"));
		instance.setMaxInfusion(compound.getInteger("maxInfusion"));
		instance.setXiaDefeats(compound.getInteger("xiaDefeats"));
	}

}
