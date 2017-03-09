package Tamaized.Voidcraft.xiaCastle.logic.battle.twins.messages;

import Tamaized.Voidcraft.VoidCraft;
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

public class TwinsMessages04 {

	public static int tick = 1;
	public static int childPhase = 0;
	public static int childPhaseModulate = 20 * 3;

	public static boolean run(World worldObj, BlockPos pos) {
		if (tick % childPhaseModulate == 0) {
			for (EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))) {
				switch (childPhase) {
					case 0:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.4.1"));
						break;
					case 1:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.4.2"));
						break;
					case 2:
						worldObj.setBlockState(pos.add(3, 0, 0), Blocks.CHEST.getDefaultState().withProperty(Blocks.CHEST.FACING, EnumFacing.WEST));
						TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(pos.add(3, 0, 0));
						te.setInventorySlotContents(0, new ItemStack(Items.SIGN));
						worldObj.setBlockState(pos.add(2, 0, 1), Blocks.LEVER.getDefaultState().withProperty(BlockLever.FACING, BlockLever.EnumOrientation.UP_X));
						worldObj.setBlockState(pos.add(3, 0, 1), VoidCraft.blocks.blockNoBreak.getDefaultState());
						worldObj.setBlockState(pos.add(3, 0, -1), VoidCraft.blocks.blockNoBreak.getDefaultState());
						worldObj.setBlockState(pos.add(3, 1, 0), Blocks.STANDING_SIGN.getDefaultState().withProperty(BlockStandingSign.ROTATION, 4));
						worldObj.setBlockState(pos.add(3, 1, 1), Blocks.STANDING_SIGN.getDefaultState().withProperty(BlockStandingSign.ROTATION, 4));
						worldObj.setBlockState(pos.add(3, 1, -1), Blocks.STANDING_SIGN.getDefaultState().withProperty(BlockStandingSign.ROTATION, 4));
						TileEntitySign s1 = (TileEntitySign) worldObj.getTileEntity(pos.add(3, 1, -1));
						TileEntitySign s2 = (TileEntitySign) worldObj.getTileEntity(pos.add(3, 1, 0));
						TileEntitySign s3 = (TileEntitySign) worldObj.getTileEntity(pos.add(3, 1, 1));
						s1.signText[0] = new TextComponentTranslation("voidcraft.twins.riddle.4.1");
						s1.signText[1] = new TextComponentTranslation("voidcraft.twins.riddle.4.2");
						s1.signText[2] = new TextComponentTranslation("voidcraft.twins.riddle.4.3");
						s1.signText[3] = new TextComponentTranslation("voidcraft.twins.riddle.4.4");
						s2.signText[0] = new TextComponentTranslation("voidcraft.twins.riddle.4.5");
						s2.signText[1] = new TextComponentTranslation("voidcraft.twins.riddle.4.6");
						s2.signText[2] = new TextComponentTranslation("voidcraft.twins.riddle.4.7");
						s2.signText[3] = new TextComponentTranslation("voidcraft.twins.riddle.4.8");
						s3.signText[0] = new TextComponentTranslation("voidcraft.twins.riddle.4.9");
						s3.signText[1] = new TextComponentTranslation("voidcraft.twins.riddle.4.10");
						s3.signText[2] = new TextComponentTranslation("voidcraft.twins.riddle.4.11");
						s3.signText[3] = new TextComponentTranslation("");
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
