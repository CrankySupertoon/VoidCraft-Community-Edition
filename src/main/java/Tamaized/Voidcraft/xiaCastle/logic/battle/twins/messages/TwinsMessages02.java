package Tamaized.Voidcraft.xiaCastle.logic.battle.twins.messages;

import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockStandingSign;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class TwinsMessages02 {

	public static int tick = 1;
	public static int childPhase = 0;
	public static int childPhaseModulate = 20 * 5;

	public static boolean run(World worldObj, BlockPos pos) {
		if (tick % childPhaseModulate == 0) {
			for (EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))) {
				switch (childPhase) {
					case 0:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.2.1", p.getGameProfile().getName()));
						break;
					case 1:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.2.2"));
						break;
					case 2:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.2.3", p.getGameProfile().getName()));
						break;
					case 3:
						worldObj.setBlockState(pos.add(3, 0, 0), Blocks.CHEST.getDefaultState().withProperty(Blocks.CHEST.FACING, EnumFacing.WEST));
						TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(pos.add(3, 0, 0));
						te.setInventorySlotContents(0, new ItemStack(Items.SIGN));
						worldObj.setBlockState(pos.add(3, 0, 1), Blocks.LEVER.getDefaultState().withProperty(BlockLever.FACING, BlockLever.EnumOrientation.UP_X));
						worldObj.setBlockState(pos.add(3, 1, 0), Blocks.STANDING_SIGN.getDefaultState().withProperty(BlockStandingSign.ROTATION, 4));
						TileEntitySign s = (TileEntitySign) worldObj.getTileEntity(pos.add(3, 1, 0));
						s.signText[0] = new TextComponentTranslation("voidcraft.twins.riddle.2.1");
						s.signText[1] = new TextComponentTranslation("voidcraft.twins.riddle.2.2");
						s.signText[2] = new TextComponentTranslation("voidcraft.twins.riddle.2.3");
						s.signText[3] = new TextComponentTranslation("voidcraft.twins.riddle.2.4");
						return true;
					default:
						break;
				}
			}
			childPhase++;
			tick = 1;
		}
		tick++;
		return false;
	}

}
