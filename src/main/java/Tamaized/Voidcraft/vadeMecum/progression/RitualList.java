package Tamaized.Voidcraft.vadeMecum.progression;

import java.util.HashMap;
import java.util.Map;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * Ritual ItemStack arrays start from the bottom layer
 */
public class RitualList {

	private final Map<IVadeMecumCapability.Category, ItemStack[]> map;

	public ItemStack[] getRitual(IVadeMecumCapability.Category cat) {
		return map.get(cat);
	}

	public RitualList() {
		map = new HashMap<IVadeMecumCapability.Category, ItemStack[]>();
		map.put(IVadeMecumCapability.Category.INTRO, new ItemStack[] {

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(VoidCraft.blocks.ritualBlock), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY });
		map.put(IVadeMecumCapability.Category.TOME, new ItemStack[] {

				ItemStack.EMPTY, new ItemStack(Blocks.MAGMA), ItemStack.EMPTY,

				new ItemStack(Blocks.COAL_BLOCK), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.GLOWSTONE),

				ItemStack.EMPTY, new ItemStack(Blocks.PRISMARINE, 1, 1), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY });
		map.put(IVadeMecumCapability.Category.Flame, new ItemStack[] {

				new ItemStack(Blocks.NETHERRACK), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.NETHERRACK),

				new ItemStack(Blocks.MAGMA), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.MAGMA),

				new ItemStack(Blocks.NETHERRACK), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.NETHERRACK),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY });
		map.put(IVadeMecumCapability.Category.FireSheathe, new ItemStack[] {

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(Blocks.MAGMA), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.MAGMA), ItemStack.EMPTY,

				new ItemStack(Blocks.MAGMA), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.MAGMA),

				ItemStack.EMPTY, new ItemStack(Blocks.MAGMA), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.MAGMA), ItemStack.EMPTY, new ItemStack(Blocks.MAGMA),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.MAGMA), ItemStack.EMPTY, new ItemStack(Blocks.MAGMA) });
		map.put(IVadeMecumCapability.Category.Fireball, new ItemStack[] {

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(VoidCraft.blocks.ritualBlock), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.NETHERRACK), ItemStack.EMPTY,

				new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.TNT), new ItemStack(Blocks.MAGMA),

				ItemStack.EMPTY, new ItemStack(Blocks.NETHERRACK), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(Blocks.NETHERRACK), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, });
		map.put(IVadeMecumCapability.Category.Freeze, new ItemStack[] {

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.SNOW), new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.SNOW), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.SNOW),

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.SNOW), new ItemStack(Blocks.ICE),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY });
		map.put(IVadeMecumCapability.Category.FrostSheathe, new ItemStack[] {

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(Blocks.SNOW), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.SNOW), ItemStack.EMPTY,

				new ItemStack(Blocks.SNOW), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.SNOW),

				ItemStack.EMPTY, new ItemStack(Blocks.SNOW), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.SNOW), ItemStack.EMPTY, new ItemStack(Blocks.SNOW),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.SNOW), ItemStack.EMPTY, new ItemStack(Blocks.SNOW) });
		map.put(IVadeMecumCapability.Category.IceSpike, new ItemStack[] {

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(VoidCraft.blocks.ritualBlock),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.ICE), ItemStack.EMPTY,

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE),

				ItemStack.EMPTY, new ItemStack(Blocks.ICE), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(Blocks.ICE), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY });
		map.put(IVadeMecumCapability.Category.Shock, new ItemStack[] {

				new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_STONE), new ItemStack(Blocks.END_BRICKS),

				new ItemStack(Blocks.END_STONE), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.END_STONE),

				new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_STONE), new ItemStack(Blocks.END_BRICKS),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY });
		map.put(IVadeMecumCapability.Category.ShockSheathe, new ItemStack[] {

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY,

				new ItemStack(Blocks.END_BRICKS), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.END_BRICKS),

				ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS) });
		map.put(IVadeMecumCapability.Category.AcidSpray, new ItemStack[] {

				new ItemStack(Blocks.STONE), new ItemStack(Blocks.DIRT), new ItemStack(Blocks.STONE),

				new ItemStack(Blocks.DIRT), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.DIRT),

				new ItemStack(Blocks.STONE), new ItemStack(Blocks.DIRT), new ItemStack(Blocks.STONE),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY });
		map.put(IVadeMecumCapability.Category.AcidSheathe, new ItemStack[] {

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(Blocks.STONE), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.STONE), ItemStack.EMPTY,

				new ItemStack(Blocks.STONE), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.STONE),

				ItemStack.EMPTY, new ItemStack(Blocks.STONE), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.STONE), ItemStack.EMPTY, new ItemStack(Blocks.STONE),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.STONE), ItemStack.EMPTY, new ItemStack(Blocks.STONE) });
	}

}
