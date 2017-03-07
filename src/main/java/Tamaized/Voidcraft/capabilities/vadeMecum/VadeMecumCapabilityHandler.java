package Tamaized.Voidcraft.capabilities.vadeMecum;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.companion.EntityCompanion;
import Tamaized.Voidcraft.network.ItemStackNetworkHelper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class VadeMecumCapabilityHandler implements IVadeMecumCapability {

	private boolean markDirty = false;

	public static final ResourceLocation ID = new ResourceLocation(VoidCraft.modid, "VadeMecumCapabilityHandler");
	private boolean hasLoaded = false;

	private ArrayList<IVadeMecumCapability.Category> categoryList = new ArrayList<IVadeMecumCapability.Category>();

	private Category currActivePower;
	private final List<Passive> passiveList = new ArrayList<Passive>();
	private String lastEntry = "null";
	private int page = 0;
	private EntityCompanion companion;

	private Map<Category, ItemStack> spellComponents = new HashMap<Category, ItemStack>();

	public void markDirty() {
		markDirty = true;
	}

	@Override
	public boolean isDirty() {
		return markDirty;
	}

	@Override
	public void resetDirty() {
		markDirty = false;
	}

	@Override
	public boolean hasLoaded() {
		return hasLoaded;
	}

	@Override
	public void setLoaded() {
		hasLoaded = true;
	}

	@Override
	public void summonCompanion(EntityCompanion entity) {
		killCompanion();
		companion = entity;
	}

	@Override
	public void killCompanion() {
		if (companion != null) companion.setDead();
		companion = null;
	}

	@Override
	public EntityCompanion getCompanion() {
		return companion;
	}

	@Override
	public ArrayList<Category> getObtainedCategories() {
		return categoryList;
	}

	@Override
	public void setObtainedCategories(ArrayList<Category> list) {
		categoryList.clear();
		categoryList.addAll(list);
		markDirty();
	}

	@Override
	public void addCategory(Category category) {
		if (!categoryList.contains(category)) categoryList.add(category);
		markDirty();
	}

	@Override
	public void removeCategory(Category category) {
		categoryList.remove(category);
		markDirty();
	}

	@Override
	public void clearCategories() {
		categoryList.clear();
		passiveList.clear();
		currActivePower = null;
		markDirty();
	}

	@Override
	public boolean hasCategory(Category category) {
		return categoryList.contains(category);
	}

	@Override
	public ArrayList<Category> getAvailableActivePowers() {
		ArrayList<Category> activeList = new ArrayList<Category>();
		for (Category cat : categoryList)
			if (IVadeMecumCapability.isActivePower(cat)) activeList.add(cat);
		return activeList;
	}

	@Override
	public void setCurrentActive(Category power) {
		if (IVadeMecumCapability.isActivePower(power)) currActivePower = power;
		markDirty();
	}

	@Override
	public void clearActivePower() {
		currActivePower = null;
		markDirty();
	}

	@Override
	public Category getCurrentActive() {
		return currActivePower;
	}

	@Override
	public List<Passive> getActivePassiveList() {
		return passiveList;
	}

	@Override
	public void addPassive(Passive ability) {
		if (canHavePassive(ability)) {
			passiveList.add(ability);
			markDirty();
		}
	}

	@Override
	public void removePassive(Passive ability) {
		if (hasPassive(ability)) {
			passiveList.remove(ability);
			markDirty();
		}
	}

	@Override
	public boolean hasPassive(Passive ability) {
		return passiveList.contains(ability);
	}

	@Override
	public void setLastEntry(String e) {
		lastEntry = (e == null || e.isEmpty()) ? "null" : e;
		markDirty();
	}

	@Override
	public String getLastEntry() {
		return lastEntry;
	}

	@Override
	public Map<Category, ItemStack> getComponents() {
		return spellComponents;
	}

	@Override
	public void clearComponents() {
		spellComponents.clear();
		markDirty();
	}

	@Override
	public ItemStack getStackInSlot(Category slot) {
		return spellComponents.get(slot);
	}

	@Override
	public ItemStack addStackToSlot(Category slot, ItemStack stack) {
		ItemStack slotStack = spellComponents.get(slot);
		if (slotStack == null) {
			setStackSlot(slot, stack);
			markDirty();
			return null;
		} else if (ItemStack.areItemsEqual(slotStack, stack)) {
			int room = slotStack.getMaxStackSize() - slotStack.stackSize;
			int amount = Math.min(room, stack.stackSize);
			slotStack.stackSize += (amount);
			setStackSlot(slot, slotStack);
			stack.stackSize -= (amount);
			markDirty();
			return stack.stackSize <= 0 ? null : stack;
		}
		markDirty();
		return stack;
	}

	@Override
	public void setStackSlot(Category slot, ItemStack stack) {
		spellComponents.put(slot, stack);
		markDirty();
	}

	@Override
	public ItemStack decrStackSize(Category slot, int amount) {
		if (getStackInSlot(slot) != null) {
			ItemStack itemstack;
			if (getStackInSlot(slot).stackSize <= amount) {
				itemstack = getStackInSlot(slot);
				setStackSlot(slot, null);
				markDirty();
				return itemstack;
			} else {
				itemstack = getStackInSlot(slot).splitStack(amount);
				if (getStackInSlot(slot).stackSize == 0) {
					setStackSlot(slot, null);
				}
				markDirty();
				return itemstack;
			}
		}
		markDirty();
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(Category slot) {
		if (getStackInSlot(slot) != null) {
			ItemStack itemstack = getStackInSlot(slot);
			setStackSlot(slot, null);
			markDirty();
			return itemstack;
		}
		markDirty();
		return null;
	}

	@Override
	public int getPage() {
		return page;
	}

	@Override
	public void setPage(int page) {
		this.page = page;
		markDirty();
	}

	@Override
	public void copyFrom(IVadeMecumCapability cap) {
		if (cap == null) return;
		clearComponents();
		spellComponents.putAll(cap.getComponents());
		setObtainedCategories(cap.getObtainedCategories());
		for (IVadeMecumCapability.Passive passive : cap.getActivePassiveList())
			addPassive(passive);
		setCurrentActive(cap.getCurrentActive());
		setLastEntry(cap.getLastEntry());
		setPage(cap.getPage());
		setLoaded();
		markDirty();
	}

	@Override
	public void decodePacket(ByteBuf buf, ByteBufInputStream stream) throws IOException {
		setLastEntry(stream.readUTF());
		setPage(stream.readInt());
		{
			clearCategories();
			int l = stream.readInt();
			for (int i = 0; i < l; i++) {
				addCategory(IVadeMecumCapability.getCategoryFromID(stream.readInt()));
			}
		}
		{
			passiveList.clear();
			int l = stream.readInt();
			for (int i = 0; i < l; i++) {
				addPassive(IVadeMecumCapability.getPassiveFromID(stream.readInt()));
			}
		}
		{
			clearComponents();
			int l = stream.readInt();
			for (int i = 0; i < l; i++) {
				spellComponents.put(IVadeMecumCapability.getCategoryFromID(stream.readInt()), ItemStackNetworkHelper.decodeStack(buf, stream));
			}
		}
		setCurrentActive(IVadeMecumCapability.getCategoryFromID(stream.readInt()));
	}

	@Override
	public void encodePacket(DataOutputStream stream) throws IOException {
		stream.writeUTF(getLastEntry());
		stream.writeInt(getPage());
		// Do Arrays last
		stream.writeInt(getObtainedCategories().size());
		for (Category cat : getObtainedCategories()) {
			stream.writeInt(IVadeMecumCapability.getCategoryID(cat));
		}
		stream.writeInt(getActivePassiveList().size());
		for (Passive passive : getActivePassiveList()) {
			stream.writeInt(IVadeMecumCapability.getPassiveID(passive));
		}
		stream.writeInt(spellComponents.size());
		for (Entry<Category, ItemStack> entry : spellComponents.entrySet()) {
			stream.writeInt(IVadeMecumCapability.getCategoryID(entry.getKey()));
			ItemStackNetworkHelper.encodeStack(entry.getValue(), stream);
		}
		stream.writeInt(IVadeMecumCapability.getCategoryID(getCurrentActive()));
	}

}
