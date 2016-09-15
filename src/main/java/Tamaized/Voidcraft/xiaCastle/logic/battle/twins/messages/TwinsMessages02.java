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
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import Tamaized.Voidcraft.entity.boss.twins.EntityBossDol;
import Tamaized.Voidcraft.entity.boss.twins.EntityBossZol;

public class TwinsMessages02 {
	
	public static int childPhase = 0;
	public static int childPhaseModulate = 20*5;
	
	public static boolean run(World worldObj, BlockPos pos, int tick){
		if(tick % childPhaseModulate == 0){
			for(EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))){
				switch(childPhase){
					case 0:
						p.addChatMessage(new TextComponentTranslation(TextFormatting.AQUA+"[Zol] "+p.getGameProfile().getName()+" is correct, Dol."));
						break;
					case 1:
						p.addChatMessage(new TextComponentTranslation(TextFormatting.GREEN+"[Dol] Yes indeed."));
						break;
					case 2:
						p.addChatMessage(new TextComponentTranslation(TextFormatting.AQUA+"[Zol] We've got a few more for you, "+p.getGameProfile().getName()));
						break;
					case 3:
						worldObj.setBlockState(pos.add(3, 0, 0), Blocks.CHEST.getDefaultState().withProperty(Blocks.CHEST.FACING, EnumFacing.WEST));
						TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(pos.add(3, 0, 0));
						te.setInventorySlotContents(0, new ItemStack(Items.SIGN));
						worldObj.setBlockState(pos.add(3, 0, 1), Blocks.LEVER.getDefaultState().withProperty(BlockLever.FACING, BlockLever.EnumOrientation.UP_X));
						worldObj.setBlockState(pos.add(3, 1, 0), Blocks.STANDING_SIGN.getDefaultState().withProperty(BlockStandingSign.ROTATION, 4));
						TileEntitySign s = (TileEntitySign) worldObj.getTileEntity(pos.add(3, 1, 0));
						s.signText[0] = new TextComponentString("Nothing");
						s.signText[1] = new TextComponentString("Empty");
						s.signText[2] = new TextComponentString("Consume");
						s.signText[3] = new TextComponentString("What am I");
						return true;
					default:
						break;
				}
			}
			childPhase++;
		}
		return false;
	}

}
