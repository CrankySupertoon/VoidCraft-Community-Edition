package Tamaized.Voidcraft.capabilities.vadeMecum;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import Tamaized.Voidcraft.VoidCraft;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.util.ResourceLocation;

public class VadeMecumCapabilityHandler implements IVadeMecumCapability {

	private boolean markDirty = false;

	public static final ResourceLocation ID = new ResourceLocation(VoidCraft.modid, "VadeMecumCapabilityHandler");
	private boolean hasLoaded = false;

	private ArrayList<IVadeMecumCapability.Category> categoryList = new ArrayList<IVadeMecumCapability.Category>();

	private Category currActivePower;
	private String lastEntry = "null";

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
	}

	@Override
	public Category getCurrentActive() {
		return currActivePower;
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
	public void copyFrom(IVadeMecumCapability cap) {
		if (cap == null) return;
		setObtainedCategories(cap.getObtainedCategories());
		setCurrentActive(cap.getCurrentActive());
		// setPassivePowers(cap.getPassivePowers());
		setCurrentActive(cap.getCurrentActive());
		setLastEntry(cap.getLastEntry());
		setLoaded();
		markDirty();
	}

	@Override
	public void decodePacket(ByteBufInputStream stream) throws IOException {
		setCurrentActive(IVadeMecumCapability.getCategoryFromID(stream.readInt()));
		setLastEntry(stream.readUTF());
		// Do Arrays last
		int category = stream.readInt();
		clearCategories();
		for (int i = 0; i < category; i++) {
			addCategory(IVadeMecumCapability.getCategoryFromID(stream.readInt()));
		}
	}

	@Override
	public void encodePacket(DataOutputStream stream) throws IOException {
		stream.writeInt(IVadeMecumCapability.getCategoryID(getCurrentActive()));
		stream.writeUTF(getLastEntry());
		// Do Arrays last
		stream.writeInt(getObtainedCategories().size());
		for (Category cat : getObtainedCategories()) {
			stream.writeInt(IVadeMecumCapability.getCategoryID(cat));
		}
	}

}
