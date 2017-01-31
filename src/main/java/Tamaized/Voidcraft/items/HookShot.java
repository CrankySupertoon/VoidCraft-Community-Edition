package Tamaized.Voidcraft.items;

import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Tamaized.TamModized.helper.PacketHelper;
import Tamaized.TamModized.helper.PacketHelper.PacketWrapper;
import Tamaized.TamModized.items.TamItem;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.network.ServerPacketHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HookShot extends TamItem {

	public HookShot(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	public static Map<EntityPlayer, Boolean> handler = new HashMap<EntityPlayer, Boolean>(); // Keep this Server Side

	/**
	 * Returns True is the item is renderer in full 3D when hold.
	 */
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		/*
		 * player.setItemInUse(itemstack, 20*4); if(world.isRemote) return itemstack; if(!HookShot.handler.containsKey(player)){ voidCraft.logger.info("Adding player ("+player.getGameProfile().getName()+") to HookShot handler"); HookShot.handler.put(player, false); } if(HookShot.handler.get(player)) return itemstack; EntityHookShot hook = new EntityHookShot(world, player, 1.6F); hook.setRangeAndSpeed(0.0F, 1.7D); world.spawnEntityInWorld(hook); HookShot.handler.put(player, true); return itemstack;
		 */return ItemStack.EMPTY;
	}

	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int count) { // Doesnt seem to be called server side so send a packet
		try {
			PacketWrapper packet = PacketHelper.createPacket(VoidCraft.channel, VoidCraft.networkChannelName, ServerPacketHandler.getPacketTypeID(ServerPacketHandler.PacketType.HOOKSHOT_STOP));
			DataOutputStream stream = packet.getStream();
			packet.sendPacketToServer();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldRotateAroundWhenRendering() {
		return true;
	}

	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
		return EnumAction.BOW;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(TextFormatting.AQUA + "Hold down right click in the Air to fire!");
	}

}
