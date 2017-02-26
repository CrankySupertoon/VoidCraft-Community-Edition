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

				null, null, null,

				null, new ItemStack(VoidCraft.blocks.ritualBlock), null,

				null, null, null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null });
		map.put(IVadeMecumCapability.Category.TOME, new ItemStack[] {

				null, new ItemStack(Blocks.MAGMA), null,

				new ItemStack(Blocks.COAL_BLOCK), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.GLOWSTONE),

				null, new ItemStack(Blocks.PRISMARINE, 1, 1), null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null });
		map.put(IVadeMecumCapability.Category.Flame, new ItemStack[] {

				new ItemStack(Blocks.NETHERRACK), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.NETHERRACK),

				new ItemStack(Blocks.MAGMA), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.MAGMA),

				new ItemStack(Blocks.NETHERRACK), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.NETHERRACK),

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null });
		map.put(IVadeMecumCapability.Category.FireSheathe, new ItemStack[] {

				null, null, null,

				null, new ItemStack(Blocks.MAGMA), null,

				null, null, null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, new ItemStack(Blocks.MAGMA), null,

				new ItemStack(Blocks.MAGMA), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.MAGMA),

				null, new ItemStack(Blocks.MAGMA), null,

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.MAGMA), null, new ItemStack(Blocks.MAGMA),

				null, null, null,

				new ItemStack(Blocks.MAGMA), null, new ItemStack(Blocks.MAGMA) });
		map.put(IVadeMecumCapability.Category.Fireball, new ItemStack[] {

				null, null, null,

				null, new ItemStack(VoidCraft.blocks.ritualBlock), null,

				null, null, null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, new ItemStack(Blocks.NETHERRACK), null,

				new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.TNT), new ItemStack(Blocks.MAGMA),

				null, new ItemStack(Blocks.NETHERRACK), null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, new ItemStack(Blocks.NETHERRACK), null,

				null, null, null });
		map.put(IVadeMecumCapability.Category.FireTrap, new ItemStack[] {

				new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA),

				new ItemStack(Blocks.MAGMA), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.MAGMA),

				new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA),

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null });
		map.put(IVadeMecumCapability.Category.Freeze, new ItemStack[] {

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.SNOW), new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.SNOW), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.SNOW),

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.SNOW), new ItemStack(Blocks.ICE),

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null });
		map.put(IVadeMecumCapability.Category.FrostSheathe, new ItemStack[] {

				null, null, null,

				null, new ItemStack(Blocks.SNOW), null,

				null, null, null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, new ItemStack(Blocks.SNOW), null,

				new ItemStack(Blocks.SNOW), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.SNOW),

				null, new ItemStack(Blocks.SNOW), null,

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.SNOW), null, new ItemStack(Blocks.SNOW),

				null, null, null,

				new ItemStack(Blocks.SNOW), null, new ItemStack(Blocks.SNOW) });
		map.put(IVadeMecumCapability.Category.IceSpike, new ItemStack[] {

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(VoidCraft.blocks.ritualBlock),

				//////////////////////////////////////////////////////////////////////////////////////////

				null, new ItemStack(Blocks.ICE), null,

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE),

				null, new ItemStack(Blocks.ICE), null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, new ItemStack(Blocks.ICE), null,

				null, null, null });
		map.put(IVadeMecumCapability.Category.Shock, new ItemStack[] {

				new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_STONE), new ItemStack(Blocks.END_BRICKS),

				new ItemStack(Blocks.END_STONE), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.END_STONE),

				new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_STONE), new ItemStack(Blocks.END_BRICKS),

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null });
		map.put(IVadeMecumCapability.Category.ShockSheathe, new ItemStack[] {

				null, null, null,

				null, new ItemStack(Blocks.END_BRICKS), null,

				null, null, null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, new ItemStack(Blocks.END_BRICKS), null,

				new ItemStack(Blocks.END_BRICKS), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.END_BRICKS),

				null, new ItemStack(Blocks.END_BRICKS), null,

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.END_BRICKS), null, new ItemStack(Blocks.END_BRICKS),

				null, null, null,

				new ItemStack(Blocks.END_BRICKS), null, new ItemStack(Blocks.END_BRICKS) });
		map.put(IVadeMecumCapability.Category.AcidSpray, new ItemStack[] {

				new ItemStack(Blocks.STONE), new ItemStack(Blocks.DIRT), new ItemStack(Blocks.STONE),

				new ItemStack(Blocks.DIRT), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.DIRT),

				new ItemStack(Blocks.STONE), new ItemStack(Blocks.DIRT), new ItemStack(Blocks.STONE),

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null });
		map.put(IVadeMecumCapability.Category.AcidSheathe, new ItemStack[] {

				null, null, null,

				null, new ItemStack(Blocks.STONE), null,

				null, null, null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, new ItemStack(Blocks.STONE), null,

				new ItemStack(Blocks.STONE), new ItemStack(VoidCraft.blocks.ritualBlock), new ItemStack(Blocks.STONE),

				null, new ItemStack(Blocks.STONE), null,

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.STONE), null, new ItemStack(Blocks.STONE),

				null, null, null,

				new ItemStack(Blocks.STONE), null, new ItemStack(Blocks.STONE) });
	}

}
