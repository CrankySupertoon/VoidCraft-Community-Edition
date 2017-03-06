package Tamaized.Voidcraft.capabilities.vadeMecum;

import java.util.ArrayList;
import java.util.Map.Entry;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.network.ItemStackNetworkHelper;
import net.minecraft.item.ItemStack;
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
		{
			NBTTagCompound comp = new NBTTagCompound();
			for (Entry<IVadeMecumCapability.Category, ItemStack> entry : instance.getComponents().entrySet()) {
				comp.setTag(String.valueOf(IVadeMecumCapability.getCategoryID(entry.getKey())), entry.getValue().writeToNBT(new NBTTagCompound()));
			}
			compound.setTag("SpellComponents", comp);

		}
		{
			ArrayList<Integer> array = new ArrayList<Integer>();
			for (IVadeMecumCapability.Category category : instance.getObtainedCategories()) {
				array.add(IVadeMecumCapability.getCategoryID(category));
			}
			compound.setIntArray("category", array.stream().mapToInt(i -> i).toArray());
		}
		{
			ArrayList<Integer> array = new ArrayList<Integer>();
			for (IVadeMecumCapability.Passive passive : instance.getActivePassiveList()) {
				array.add(IVadeMecumCapability.getPassiveID(passive));
			}
			compound.setIntArray("passive", array.stream().mapToInt(i -> i).toArray());
		}
		compound.setInteger("currentActive", IVadeMecumCapability.getCategoryID(instance.getCurrentActive()));
		compound.setString("lastEntry", instance.getLastEntry());
		compound.setInteger("page", instance.getPage());
		return compound;
	}

	@Override
	public void readNBT(Capability<IVadeMecumCapability> capability, IVadeMecumCapability instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound compound = (NBTTagCompound) nbt;
		{
			NBTTagCompound spellComponents = compound.getCompoundTag("SpellComponents");
			instance.clearComponents();
			for (String key : spellComponents.getKeySet()) {
				NBTTagCompound value = spellComponents.getCompoundTag(key);
				instance.setStackSlot(IVadeMecumCapability.getCategoryFromID(Integer.valueOf(key)), ItemStack.func_77949_a(value));
			}
		}
		{
			int[] array;
			ArrayList<IVadeMecumCapability.Category> list = new ArrayList<IVadeMecumCapability.Category>();
			array = compound.getIntArray("category");
			for (int i = 0; i < array.length; i++) {
				list.add(IVadeMecumCapability.getCategoryFromID(array[i]));
			}
			instance.setObtainedCategories(list);
		}
		{
			for (int i : compound.getIntArray("passive")) {
				instance.addPassive(IVadeMecumCapability.getPassiveFromID(i));
			}
		}
		instance.setCurrentActive(IVadeMecumCapability.getCategoryFromID(compound.getInteger("currentActive")));
		instance.setLastEntry(compound.getString("lastEntry"));
		instance.setPage(compound.getInteger("page"));
		instance.setLoaded();
	}

}
