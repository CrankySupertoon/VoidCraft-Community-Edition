package Tamaized.Voidcraft.capabilities.elytraFlying;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ElytraFlyingCapabilityStorage implements IStorage<IElytraFlyingCapability> {

	public ElytraFlyingCapabilityStorage() {
		VoidCraft.instance.logger.info("ElytraFlyingCapabilityStorage Registered");
	}

	@Override
	public NBTBase writeNBT(Capability<IElytraFlyingCapability> capability, IElytraFlyingCapability instance, EnumFacing side) {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setBoolean("flying", instance.isElytraFlying());
		return compound;
	}

	@Override
	public void readNBT(Capability<IElytraFlyingCapability> capability, IElytraFlyingCapability instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound compound = (NBTTagCompound) nbt;
		instance.setElytraFlying(compound.getBoolean("flying"));
		instance.setLoaded();
	}

}
