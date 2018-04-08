package tamaized.dalquor.common.vademecum.progression;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.common.capabilities.CapabilityList;
import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;

import java.util.ArrayList;

public class VadeMecumRitualHandler {

	public static void invokeRitual(EntityPlayer player, World world, BlockPos pos) {
		IVadeMecumCapability cap = player.getCapability(CapabilityList.VADEMECUM, null);
		if (cap == null || world.getBlockState(pos).getBlock() != DalQuor.blocks.ritualBlock)
			return;
		IVadeMecumCapability.Category category = getRitual(cap, world, pos, true);
		if (category == null || category == IVadeMecumCapability.Category.NULL) {
			player.sendMessage(new TextComponentTranslation("voidcraft.ritual.error"));
		} else {
			player.sendMessage(new TextComponentTranslation(VadeMecumWordsOfPower.getCategoryData(category).getName()));
			cap.addCategory(player, category);
		}
	}

	public static IVadeMecumCapability.Category getRitual(IVadeMecumCapability cap, World world, BlockPos pos, boolean clear) {
		for (IVadeMecumCapability.Category ritual : getAvailiableRituals(cap)) {
			if (doChecks(ritual, world, pos, clear))
				return ritual;
		}
		return IVadeMecumCapability.Category.NULL;
	}

	public static ArrayList<IVadeMecumCapability.Category> getAvailiableRituals(IVadeMecumCapability cap) {
		ArrayList<IVadeMecumCapability.Category> list = new ArrayList<IVadeMecumCapability.Category>();
		if (cap.getObtainedCategories().isEmpty()) {
			list.add(IVadeMecumCapability.Category.INTRO);
		} else {
			if (cap.hasCategory(IVadeMecumCapability.Category.TOME)) {
				if (cap.hasCategory(IVadeMecumCapability.Category.Flame)) {
					if (cap.hasCategory(IVadeMecumCapability.Category.FireSheathe)) {
						if (cap.hasCategory(IVadeMecumCapability.Category.Fireball)) {
							if (cap.hasCategory(IVadeMecumCapability.Category.FireTrap)) {
								if (cap.hasCategory(IVadeMecumCapability.Category.ExplosionFire)) {
									if (cap.hasCategory(IVadeMecumCapability.Category.RingOfFire)) {

									} else {
										list.add(IVadeMecumCapability.Category.RingOfFire);
									}
								} else {
									list.add(IVadeMecumCapability.Category.ExplosionFire);
								}
							} else {
								list.add(IVadeMecumCapability.Category.FireTrap);
							}
						} else {
							list.add(IVadeMecumCapability.Category.Fireball);
						}
					} else {
						list.add(IVadeMecumCapability.Category.FireSheathe);
					}
				} else {
					list.add(IVadeMecumCapability.Category.Flame);
				}
				if (cap.hasCategory(IVadeMecumCapability.Category.Freeze)) {
					if (cap.hasCategory(IVadeMecumCapability.Category.FrostSheathe)) {
						if (cap.hasCategory(IVadeMecumCapability.Category.IceSpike)) {
							if (cap.hasCategory(IVadeMecumCapability.Category.FrostTrap)) {
								if (cap.hasCategory(IVadeMecumCapability.Category.ExplosionFrost)) {
									if (cap.hasCategory(IVadeMecumCapability.Category.RingOfFrost)) {

									} else {
										list.add(IVadeMecumCapability.Category.RingOfFrost);
									}
								} else {
									list.add(IVadeMecumCapability.Category.ExplosionFrost);
								}
							} else {
								list.add(IVadeMecumCapability.Category.FrostTrap);
							}
						} else {
							list.add(IVadeMecumCapability.Category.IceSpike);
						}
					} else {
						list.add(IVadeMecumCapability.Category.FrostSheathe);
					}
				} else {
					list.add(IVadeMecumCapability.Category.Freeze);
				}
				if (cap.hasCategory(IVadeMecumCapability.Category.Shock)) {
					if (cap.hasCategory(IVadeMecumCapability.Category.ShockSheathe)) {
						if (cap.hasCategory(IVadeMecumCapability.Category.LitStrike)) {
							if (cap.hasCategory(IVadeMecumCapability.Category.LitTrap)) {
								if (cap.hasCategory(IVadeMecumCapability.Category.ExplosionLit)) {
									if (cap.hasCategory(IVadeMecumCapability.Category.RingOfLit)) {

									} else {
										list.add(IVadeMecumCapability.Category.RingOfLit);
									}
								} else {
									list.add(IVadeMecumCapability.Category.ExplosionLit);
								}
							} else {
								list.add(IVadeMecumCapability.Category.LitTrap);
							}
						} else {
							list.add(IVadeMecumCapability.Category.LitStrike);
						}
					} else {
						list.add(IVadeMecumCapability.Category.ShockSheathe);
					}
				} else {
					list.add(IVadeMecumCapability.Category.Shock);
				}
				if (cap.hasCategory(IVadeMecumCapability.Category.AcidSpray)) {
					if (cap.hasCategory(IVadeMecumCapability.Category.AcidSheathe)) {
						if (cap.hasCategory(IVadeMecumCapability.Category.Disint)) {
							if (cap.hasCategory(IVadeMecumCapability.Category.AcidTrap)) {
								if (cap.hasCategory(IVadeMecumCapability.Category.ExplosionAcid)) {
									if (cap.hasCategory(IVadeMecumCapability.Category.RingOfAcid)) {

									} else {
										list.add(IVadeMecumCapability.Category.RingOfAcid);
									}
								} else {
									list.add(IVadeMecumCapability.Category.ExplosionAcid);
								}
							} else {
								list.add(IVadeMecumCapability.Category.AcidTrap);
							}
						} else {
							list.add(IVadeMecumCapability.Category.Disint);
						}
					} else {
						list.add(IVadeMecumCapability.Category.AcidSheathe);
					}
				} else {
					list.add(IVadeMecumCapability.Category.AcidSpray);
				}
				if (cap.hasCategory(IVadeMecumCapability.Category.Flame) && cap.hasCategory(IVadeMecumCapability.Category.Freeze) && cap.hasCategory(IVadeMecumCapability.Category.Shock) && cap.hasCategory(IVadeMecumCapability.Category.AcidSpray)) {
					if (cap.hasCategory(IVadeMecumCapability.Category.VoidicTouch)) {
						if (cap.hasCategory(IVadeMecumCapability.Category.FireSheathe) && cap.hasCategory(IVadeMecumCapability.Category.FrostSheathe) && cap.hasCategory(IVadeMecumCapability.Category.ShockSheathe) && cap.hasCategory(IVadeMecumCapability.Category.AcidSheathe)) {
							if (cap.hasCategory(IVadeMecumCapability.Category.VoidicSheathe)) {
								if (cap.hasCategory(IVadeMecumCapability.Category.ExplosionFire) && cap.hasCategory(IVadeMecumCapability.Category.ExplosionFrost) && cap.hasCategory(IVadeMecumCapability.Category.ExplosionLit) && cap.hasCategory(IVadeMecumCapability.Category.ExplosionAcid)) {
									if (cap.hasCategory(IVadeMecumCapability.Category.Implosion)) {
										if (cap.hasCategory(IVadeMecumCapability.Category.SummonFireElemental)) {

										} else {
											list.add(IVadeMecumCapability.Category.SummonFireElemental);
										}
									} else {
										list.add(IVadeMecumCapability.Category.Implosion);
									}
								}
							} else {
								list.add(IVadeMecumCapability.Category.VoidicSheathe);
							}
						}
					} else {
						list.add(IVadeMecumCapability.Category.VoidicTouch);
					}
				}
			} else {
				list.add(IVadeMecumCapability.Category.TOME);
			}
		}
		return list;
	}

	public static boolean doChecks(IVadeMecumCapability.Category ritual, World world, BlockPos oPos, boolean clear) {
		ItemStack[] ritualStacks = DalQuor.ritualList.getRitual(ritual);
		if (ritualStacks == null || ritualStacks.length <= 0)
			return false;
		for (int yPos = 0; yPos >= -2; yPos--) {
			for (int xPos = -1; xPos <= 1; xPos++) {
				for (int zPos = -1; zPos <= 1; zPos++) {
					BlockPos pos = oPos.add(xPos, yPos, zPos);
					int val = check(ritualStacks, world, pos);
					if (val > 0) {
						if (clear)
							clear(ritualStacks, val, world, pos);
						return true;
					}
				}
			}
		}
		return false;
	}

	private static int check(ItemStack[] stackList, World world, BlockPos pos) {
		int i = -1;
		boolean flag = true;
		loop:
		for (int y = 0; y <= 2; y++) {
			for (int z = 1; z >= -1; z--) {
				for (int x = 1; x >= -1; x--) {
					i++;
					if (stackList[i].isEmpty())
						continue;
					IBlockState state = world.getBlockState(pos.add(x, y, z));
					Block block = stackList[i].getItem() == Items.WATER_BUCKET ? Blocks.WATER : stackList[i].getItem() == Items.LAVA_BUCKET ? Blocks.LAVA : Block.getBlockFromItem(stackList[i].getItem());
					if (state.getBlock() != block) {
						flag = false;
						break loop;
					}
				}
			}
		}
		if (flag)
			return 1;

		i = -1;
		flag = true;
		loop:
		for (int y = 0; y <= 2; y++) {
			for (int x = -1; x <= 1; x++) {
				for (int z = 1; z >= -1; z--) {
					i++;
					if (stackList[i].isEmpty())
						continue;
					IBlockState state = world.getBlockState(pos.add(x, y, z));
					Block block = stackList[i].getItem() == Items.WATER_BUCKET ? Blocks.WATER : stackList[i].getItem() == Items.LAVA_BUCKET ? Blocks.LAVA : Block.getBlockFromItem(stackList[i].getItem());
					if (state.getBlock() != block) {
						flag = false;
						break loop;
					}
				}
			}
		}
		if (flag)
			return 2;

		i = -1;
		flag = true;
		loop:
		for (int y = 0; y <= 2; y++) {
			for (int z = -1; z <= 1; z++) {
				for (int x = -1; x <= 1; x++) {
					i++;
					if (stackList[i].isEmpty())
						continue;
					IBlockState state = world.getBlockState(pos.add(x, y, z));
					Block block = stackList[i].getItem() == Items.WATER_BUCKET ? Blocks.WATER : stackList[i].getItem() == Items.LAVA_BUCKET ? Blocks.LAVA : Block.getBlockFromItem(stackList[i].getItem());
					if (state.getBlock() != block) {
						flag = false;
						break loop;
					}
				}
			}
		}
		if (flag)
			return 3;

		i = -1;
		flag = true;
		loop:
		for (int y = 0; y <= 2; y++) {
			for (int x = 1; x >= -1; x--) {
				for (int z = -1; z <= 1; z++) {
					i++;
					if (stackList[i].isEmpty())
						continue;
					IBlockState state = world.getBlockState(pos.add(x, y, z));
					Block block = stackList[i].getItem() == Items.WATER_BUCKET ? Blocks.WATER : stackList[i].getItem() == Items.LAVA_BUCKET ? Blocks.LAVA : Block.getBlockFromItem(stackList[i].getItem());
					if (state.getBlock() != block) {
						flag = false;
						break loop;
					}
				}
			}
		}
		if (flag)
			return 4;

		return 0;
	}

	private static void clear(ItemStack[] stackList, int face, World world, BlockPos pos) {
		int i;
		switch (face) {
			case 1:
				i = -1;
				for (int y = 0; y <= 2; y++) {
					for (int z = 1; z >= -1; z--) {
						for (int x = 1; x >= -1; x--) {
							i++;
							if (!stackList[i].isEmpty())
								world.setBlockToAir(pos.add(x, y, z));
						}
					}
				}
				break;
			case 2:
				i = -1;
				for (int y = 0; y <= 2; y++) {
					for (int x = -1; x <= 1; x++) {
						for (int z = 1; z >= -1; z--) {
							i++;
							if (!stackList[i].isEmpty())
								world.setBlockToAir(pos.add(x, y, z));
						}
					}
				}
				break;
			case 3:
				i = -1;
				for (int y = 0; y <= 2; y++) {
					for (int z = -1; z <= 1; z++) {
						for (int x = -1; x <= 1; x++) {
							i++;
							if (!stackList[i].isEmpty())
								world.setBlockToAir(pos.add(x, y, z));
						}
					}
				}
				break;
			case 4:
				i = -1;
				for (int y = 0; y <= 2; y++) {
					for (int x = 1; x >= -1; x--) {
						for (int z = -1; z <= 1; z++) {
							i++;
							if (!stackList[i].isEmpty())
								world.setBlockToAir(pos.add(x, y, z));
						}
					}
				}
				break;
			default:
				break;
		}
	}

}
