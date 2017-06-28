package tamaized.voidcraft.common.items;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Tamaized.TamModized.items.TamItem;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.entity.boss.xia.EntityBossXia2;
import tamaized.voidcraft.common.entity.ghost.EntityGhostPlayerBase;
import tamaized.voidcraft.common.handlers.SkinHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class VoidicEssence extends TamItem {

	public VoidicEssence(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			ItemStack stack = player.getHeldItem(hand);
			NBTTagCompound nbt = stack.getOrCreateSubCompound(VoidCraft.modid);
			int id = nbt.getInteger("xia");
			int phase = nbt.getInteger("phase");
			if (id > 0) {
				Entity e = world.getEntityByID(id);
				if (e instanceof EntityBossXia2) {
					EntityBossXia2 xia = (EntityBossXia2) e;
					if (xia.getCurrentPhase() == phase) {
						List<UUID> list = new ArrayList<UUID>();
						for (EntityGhostPlayerBase ghost : xia.getGhostList()) {
							list.add(ghost.getUUID());
						}
						UUID uuid = getRandomUnusedUUID(0, list);
						EntityGhostPlayerBase entity = EntityGhostPlayerBase.newInstance(world, uuid, false, xia, 20 * 30);
						entity.setPositionAndUpdate(player.posX, player.posY, player.posZ);
						world.spawnEntity(entity);
						xia.addGhost(entity);
					}
				}
			}
			stack.shrink(1);
		}
		return super.onItemRightClick(world, player, hand);
	}

	private UUID getRandomUnusedUUID(int j, List<UUID> list) {
		int i = 0;
		if (j == 0) i = (int) Math.floor(Math.random() * SkinHandler.getSize());
		else i = j;
		if (i >= SkinHandler.getSize()) i = 0;
		return list.contains(SkinHandler.getUUID(i)) ? getRandomUnusedUUID(i + 1, list) : SkinHandler.getUUID(i);
	}

}
