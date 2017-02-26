package Tamaized.Voidcraft.capabilities.vadeMecum;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import Tamaized.Voidcraft.handlers.VadeMecumWordsOfPower;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.item.ItemStack;

public interface IVadeMecumCapability {

	public static enum Category {
		NULL, INTRO, TOME,

		// Active
		Flame, FireSheathe, Fireball, FireTrap, ExplosionFire, RingOfFire,

		Shock, ShockSheathe, LitStrike, LitTrap, ExplosionLit, RingOfLit,

		Freeze, FrostSheathe, IceSpike, FrostTrap, ExplosionFrost, RingOfFrost,

		AcidSpray, AcidSheathe, Disint, AcidTrap, ExplosionAcid, RingOfAcid,

		VoidicTouch, VoidicSheathe, Implosion

	}

	public static int getCategoryID(Category c) {
		return c == null ? -1 : c.ordinal();
	}

	public static Category getCategoryFromID(int id) {
		return (id > Category.values().length || id < 0) ? null : Category.values()[id];
	}

	public static class CategoryDataWrapper {

		public enum Element {
			NULL, FIRE, WATER, EARTH, AIR, VOID
		}

		private final Element element;
		private final String name;
		private final ItemStack stack;

		public CategoryDataWrapper(Element type, String name, ItemStack stack) {
			element = type;
			this.name = name;
			this.stack = stack;
		}

		public Element getElement() {
			return element;
		}

		public String getName() {
			return name;
		}

		public ItemStack getStack() {
			return stack;
		}
	}

	public static boolean isActivePower(Category c) {
		return VadeMecumWordsOfPower.getCategoryData(c).getName().contains("Word:");
	}

	public static boolean isPassivePower(Category c) {
		return false;
	}

	public boolean isDirty();

	public void resetDirty();

	public ArrayList<Category> getObtainedCategories();

	public void setObtainedCategories(ArrayList<Category> list);

	public void addCategory(Category category);

	public void removeCategory(Category category);

	public void clearCategories();

	public boolean hasCategory(Category category);

	public ArrayList<Category> getAvailableActivePowers();

	public void setCurrentActive(Category power);

	public void clearActivePower();

	public Category getCurrentActive();
	
	/**
	 * Return a value from 0 to 100
	 */
	public int getFailureChance();

	public void setLastEntry(String e);

	public String getLastEntry();

	public Map<Category, ItemStack> getComponents();

	public void clearComponents();

	public ItemStack getStackInSlot(Category slot);

	public ItemStack addStackToSlot(Category slot, ItemStack stack);

	public void setStackSlot(Category slot, ItemStack stack);

	public ItemStack decrStackSize(Category slot, int amount);

	public ItemStack removeStackFromSlot(Category slot);

	public boolean hasLoaded();

	public void setLoaded();

	public void copyFrom(IVadeMecumCapability cap);

	public void decodePacket(ByteBuf buf, ByteBufInputStream stream) throws IOException;

	public void encodePacket(DataOutputStream stream) throws IOException;

}
