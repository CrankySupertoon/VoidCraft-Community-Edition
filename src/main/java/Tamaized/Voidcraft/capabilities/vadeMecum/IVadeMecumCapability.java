package Tamaized.Voidcraft.capabilities.vadeMecum;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Tamaized.Voidcraft.entity.companion.EntityCompanion;
import Tamaized.Voidcraft.vadeMecum.progression.VadeMecumWordsOfPower;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface IVadeMecumCapability {

	public static enum Category {
		NULL, INTRO, TOME,

		// Active
		Flame, FireSheathe, Fireball, FireTrap, ExplosionFire, RingOfFire,

		Shock, ShockSheathe, LitStrike, LitTrap, ExplosionLit, RingOfLit,

		Freeze, FrostSheathe, IceSpike, FrostTrap, ExplosionFrost, RingOfFrost,

		AcidSpray, AcidSheathe, Disint, AcidTrap, ExplosionAcid, RingOfAcid,

		VoidicTouch, VoidicSheathe, Implosion,

		Invoke, SummonFireElemental,

		// Tasks
		Voice, VoidicControl, ImprovedCasting, Empowerment, Tolerance, TotalControl, Dreams
	}

	public static int getCategoryID(Category c) {
		return c == null ? -1 : c.ordinal();
	}

	public static Category getCategoryFromID(int id) {
		return (id > Category.values().length || id < 0) ? null : Category.values()[id];
	}

	public static enum Passive {
		Anchor, Empowerment, Tolerance, Flight, Vigor
	}

	public static int getPassiveID(Passive c) {
		return c == null ? -1 : c.ordinal();
	}

	public static Passive getPassiveFromID(int id) {
		return (id > Passive.values().length || id < 0) ? null : Passive.values()[id];
	}

	public static String getPassiveName(Passive passive) {
		switch (passive) {
			case Anchor:
				return "voidcraft.VadeMecum.passive.Anchor";
			case Empowerment:
				return "voidcraft.VadeMecum.passive.Empowerment";
			case Tolerance:
				return "voidcraft.VadeMecum.passive.Tolerance";
			case Flight:
				return "voidcraft.VadeMecum.passive.Flight";
			case Vigor:
				return "voidcraft.VadeMecum.passive.Vigor";
			default:
				return "null";
		}
	}

	public default boolean canHavePassive(Passive passive) {
		switch (passive) {
			case Anchor:
				return hasCategory(Category.VoidicControl);
			case Empowerment:
				return hasCategory(Category.Empowerment);
			case Tolerance:
				return hasCategory(Category.Tolerance);
			case Flight:
				return hasCategory(Category.TotalControl);
			case Vigor:
				return hasCategory(Category.Dreams);
			default:
				return false;
		}
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
		return VadeMecumWordsOfPower.getCategoryData(c).getElement() != CategoryDataWrapper.Element.NULL;
	}

	public boolean isDirty();

	public void resetDirty();
	
	public void summonCompanion(EntityCompanion entity);
	
	public void killCompanion();
	
	public EntityCompanion getCompanion();

	public ArrayList<Category> getObtainedCategories();

	public void setObtainedCategories(ArrayList<Category> list);

	public void addCategory(EntityLivingBase entity, Category category);

	public void removeCategory(Category category);

	public void clearCategories();

	public boolean hasCategory(Category category);

	public ArrayList<Category> getAvailableActivePowers();

	public void setCurrentActive(Category power);

	public void clearActivePower();

	public Category getCurrentActive();

	public List<Passive> getActivePassiveList();

	public void addPassive(Passive ability);

	public void removePassive(Passive ability);

	public boolean hasPassive(Passive ability);

	/**
	 * Return a value from 0 to 100
	 */
	public default int getFailureChance() {
		int chance = 75;
		if (hasCategory(Category.Voice)) chance -= 25;
		if (hasCategory(Category.ImprovedCasting)) chance -= 25;
		if (hasCategory(Category.TotalControl)) chance -= 25;
		return chance;
	}

	public void setLastEntry(String e);

	public String getLastEntry();

	public Map<Category, ItemStack> getComponents();

	public void clearComponents();

	public ItemStack getStackInSlot(Category slot);

	public ItemStack addStackToSlot(Category slot, ItemStack stack);

	public void setStackSlot(Category slot, ItemStack stack);

	public ItemStack decrStackSize(Category slot, int amount);

	public ItemStack removeStackFromSlot(Category slot);

	public int getPage();

	public void setPage(int page);

	public boolean hasLoaded();

	public void setLoaded();

	public void copyFrom(IVadeMecumCapability cap);

	public void decodePacket(ByteBuf buf, ByteBufInputStream stream) throws IOException;

	public void encodePacket(DataOutputStream stream) throws IOException;

}
