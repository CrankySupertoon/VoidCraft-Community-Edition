package Tamaized.Voidcraft.items;

import java.util.ArrayList;
import java.util.List;

import Tamaized.TamModized.items.TamItem;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia2;
import Tamaized.Voidcraft.entity.ghost.EntityGhostPlayerBase;
import Tamaized.Voidcraft.handlers.SkinHandler.PlayerNameAlias;
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
						List<PlayerNameAlias> list = new ArrayList<PlayerNameAlias>();
						for (EntityGhostPlayerBase ghost : xia.getGhostList()) {
							list.add(ghost.getAlias());
						}
						PlayerNameAlias alias = getRandomUnusedAlias(0, list);
						EntityGhostPlayerBase entity = EntityGhostPlayerBase.newInstance(world, alias, false, xia, 20 * 30);
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

	private PlayerNameAlias getRandomUnusedAlias(int j, List<PlayerNameAlias> list) {
		int i = 0;
		if (j == 0) i = (int) Math.floor(Math.random() * PlayerNameAlias.values().length);
		else i = j;
		if (i >= PlayerNameAlias.values().length) i = 0;
		return list.contains(PlayerNameAlias.values()[i]) ? getRandomUnusedAlias(i + 1, list) : PlayerNameAlias.values()[i];
	}

}
