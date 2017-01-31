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

public class TwinsMessages01 {
	
	public static int tick = 1;
	public static int childPhase = 0;
	public static int childPhaseModulate = 20*5;
	
	public static boolean run(World worldObj, BlockPos pos,EntityBossDol dol, EntityBossZol zol){
		if(tick % childPhaseModulate == 0){
			for(EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))){
				switch(childPhase){
					case 0:
						p.sendMessage(new TextComponentTranslation(TextFormatting.AQUA+p.getGameProfile().getName()+" is approaching."));
						childPhaseModulate = 20*5;
						break;
					case 1:
						p.sendMessage(new TextComponentTranslation(TextFormatting.GREEN+"I know."));
						break;
					case 2:
						p.sendMessage(new TextComponentTranslation(TextFormatting.AQUA+"Isn't it strange? This one is actually able to be here because they changed the rules of our universe."));
						break;
					case 3:
						p.sendMessage(new TextComponentTranslation(TextFormatting.GREEN+"Yes... While so many like "+p.getGameProfile().getName()+" are able to do this, so few choose to do so."));
						break;
					case 4:
						p.sendMessage(new TextComponentTranslation(TextFormatting.AQUA+"I don't blame them. There isn't much information out there about us... yet."));
						break;
					case 5:
						p.sendMessage(new TextComponentTranslation(TextFormatting.GREEN+"True, but to think... we're going to play a bigger part of this one's dream. "+p.getGameProfile().getName()+" will get to meet us."));
						break;
					case 6:
						p.sendMessage(new TextComponentTranslation(TextFormatting.AQUA+"Will "+p.getGameProfile().getName()+" understand what we are?"));
						break;
					case 7:
						p.sendMessage(new TextComponentTranslation(TextFormatting.GREEN+"I don't think so. We are the greatest mystery of all to humans. While this one is strong enough to understand our thoughts, it does not mean it will understand us in all our entirety."));
						childPhaseModulate = 20*7;
						break;
					case 8:
						p.sendMessage(new TextComponentTranslation(TextFormatting.AQUA+"Let us take a form that is familiar to them, then."));
						childPhaseModulate = 20*5;
						break;
					case 9:
						p.sendMessage(new TextComponentTranslation(TextFormatting.GREEN+"I agree."));
						break;
					case 10:
						zol.rotationYawHead = zol.rotationYaw = zol.prevRotationYaw = zol.prevRotationYawHead = zol.prevRenderYawOffset = zol.renderYawOffset = 90;
						dol.rotationYawHead = dol.rotationYaw = dol.prevRotationYaw = dol.prevRotationYawHead = dol.prevRenderYawOffset = dol.renderYawOffset = 90;
						worldObj.spawnEntity(zol);
						worldObj.spawnEntity(dol);
						break;
					case 11:
						p.sendMessage(new TextComponentTranslation(TextFormatting.GREEN+"[Dol] Hello, "+p.getGameProfile().getName()+"."));
						break;
					case 12:
						p.sendMessage(new TextComponentTranslation(TextFormatting.GREEN+"[Dol] As you can tell, you are trapped in here."));
						break;
					case 13:
						p.sendMessage(new TextComponentTranslation(TextFormatting.AQUA+"[Zol] We'd like to have a little battle with you."));
						break;
					case 14:
						p.sendMessage(new TextComponentTranslation(TextFormatting.GREEN+"[Dol] Not just any battle."));
						break;
					case 15:
						p.sendMessage(new TextComponentTranslation(TextFormatting.AQUA+"[Zol] A battle of wits."));
						break;
					case 16:
						p.sendMessage(new TextComponentTranslation(TextFormatting.GREEN+"[Dol] If you pass our little challenge, we'll let you free. If Herobrine is also defeated, Xia's door will be open for you to proceed."));
						break;
					case 17:
						p.sendMessage(new TextComponentTranslation(TextFormatting.AQUA+"[Zol] Now then, shall we begin?"));
						worldObj.setBlockState(pos.add(3, 0, 0), Blocks.CHEST.getDefaultState().withProperty(Blocks.CHEST.FACING, EnumFacing.WEST));
						TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(pos.add(3, 0, 0));
						te.setInventorySlotContents(0, new ItemStack(Items.SIGN));
						worldObj.setBlockState(pos.add(3, 0, 1), Blocks.LEVER.getDefaultState().withProperty(BlockLever.FACING, BlockLever.EnumOrientation.UP_X));
						worldObj.setBlockState(pos.add(3, 1, 0), Blocks.STANDING_SIGN.getDefaultState().withProperty(BlockStandingSign.ROTATION, 4));
						TileEntitySign s = (TileEntitySign) worldObj.getTileEntity(pos.add(3, 1, 0));
						s.signText[0] = new TextComponentString("Material");
						s.signText[1] = new TextComponentString("Elemental");
						s.signText[2] = new TextComponentString("Lawful Neutral");
						s.signText[3] = new TextComponentString("Who am I");
						break;
					case 18:
						p.sendMessage(new TextComponentTranslation(TextFormatting.AQUA+"[Zol] Place this sign, with your answer, in front of the chest and then flip the lever."));
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
