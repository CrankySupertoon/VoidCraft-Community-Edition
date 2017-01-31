package Tamaized.Voidcraft.items;

import java.util.List;

import Tamaized.TamModized.items.TamItem;
import Tamaized.Voidcraft.entity.boss.EntityBossCorruptedPawn;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChainedSkull extends TamItem {

	public ChainedSkull(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	private World worldObj;

	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) { // TODO: clean up this mess
		ItemStack stack = player.getHeldItem(hand);
		if (world.isRemote) {
			return EnumActionResult.PASS;
		} else {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			Block i1 = world.getBlockState(pos).getBlock();
			x += side.getFrontOffsetX();
			y += side.getFrontOffsetY();
			z += side.getFrontOffsetZ();
			double d0 = 0.0D;

			if (side.getIndex() == 1) {
				d0 = 0.5D;
			}

			EntityBossCorruptedPawn entity = new EntityBossCorruptedPawn(world);

			if (entity != null) {
				entity.setCustomNameTag("Corrupted Pawn");

				int yaw = (int) player.rotationYaw;

				if (yaw < 0) yaw += 360; // creates the full radial circle of the yaw

				yaw += 22; // centers coordinates you may want to drop this line
				yaw %= 360; // and this one if you want a strict interpretation of the zones

				int facing = yaw / 45; // 360degrees divided by 45 == 8 zones

				switch (facing) {
					case 0:
						entity.setPosition(player.posX, player.posY + 4, player.posZ + 2);
						break;
					case 1:
						entity.setPosition(player.posX - 2, player.posY + 4, player.posZ + 2);
						break;
					case 2:
						entity.setPosition(player.posX - 2, player.posY + 4, player.posZ);
						break;
					case 3:
						entity.setPosition(player.posX - 2, player.posY + 4, player.posZ - 2);
						break;
					case 4:
						entity.setPosition(player.posX, player.posY + 4, player.posZ - 2);
						break;
					case 5:
						entity.setPosition(player.posX + 2, player.posY + 4, player.posZ - 2);
						break;
					case 6:
						entity.setPosition(player.posX + 2, player.posY + 4, player.posZ);
						break;
					case 7:
						entity.setPosition(player.posX + 2, player.posY + 4, player.posZ + 2);
						break;
					default:
						break;
				}
				world.spawnEntity(entity);
				player.sendMessage(new TextComponentTranslation("???: Go forth, my pawn."));
				stack.shrink(1);
			}
			return EnumActionResult.PASS;
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(TextFormatting.DARK_RED + "WARNING: EXPLOSIONS");
	}

}
