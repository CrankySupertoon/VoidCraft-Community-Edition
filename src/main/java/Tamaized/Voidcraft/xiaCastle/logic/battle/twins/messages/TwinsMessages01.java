package Tamaized.Voidcraft.xiaCastle.logic.battle.twins.messages;

import Tamaized.Voidcraft.entity.boss.twins.EntityBossDol;
import Tamaized.Voidcraft.entity.boss.twins.EntityBossZol;
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

public class TwinsMessages01 {
	
	public static int tick = 1;
	public static int childPhase = 0;
	public static int childPhaseModulate = 20*5;
	
	public static boolean run(World worldObj, BlockPos pos,EntityBossDol dol, EntityBossZol zol){
		if(tick % childPhaseModulate == 0){
			for(EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))){
				switch(childPhase){
					case 0:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.1", p.getGameProfile().getName()));
						childPhaseModulate = 20*5;
						break;
					case 1:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.2"));
						break;
					case 2:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.3"));
						break;
					case 3:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.4", p.getGameProfile().getName()));
						break;
					case 4:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.5"));
						break;
					case 5:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.6", p.getGameProfile().getName()));
						break;
					case 6:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.7", p.getGameProfile().getName()));
						break;
					case 7:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.8"));
						childPhaseModulate = 20*7;
						break;
					case 8:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.9"));
						childPhaseModulate = 20*5;
						break;
					case 9:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.10"));
						break;
					case 10:
						zol.rotationYawHead = zol.rotationYaw = zol.prevRotationYaw = zol.prevRotationYawHead = zol.prevRenderYawOffset = zol.renderYawOffset = 90;
						dol.rotationYawHead = dol.rotationYaw = dol.prevRotationYaw = dol.prevRotationYawHead = dol.prevRenderYawOffset = dol.renderYawOffset = 90;
						worldObj.spawnEntity(zol);
						worldObj.spawnEntity(dol);
						break;
					case 11:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.11", p.getGameProfile().getName()));
						break;
					case 12:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.12"));
						break;
					case 13:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.13"));
						break;
					case 14:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.14"));
						break;
					case 15:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.15"));
						break;
					case 16:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.16"));
						break;
					case 17:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.17"));
						worldObj.setBlockState(pos.add(3, 0, 0), Blocks.CHEST.getDefaultState().withProperty(Blocks.CHEST.FACING, EnumFacing.WEST));
						TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(pos.add(3, 0, 0));
						te.setInventorySlotContents(0, new ItemStack(Items.SIGN));
						worldObj.setBlockState(pos.add(3, 0, 1), Blocks.LEVER.getDefaultState().withProperty(BlockLever.FACING, BlockLever.EnumOrientation.UP_X));
						worldObj.setBlockState(pos.add(3, 1, 0), Blocks.STANDING_SIGN.getDefaultState().withProperty(BlockStandingSign.ROTATION, 4));
						TileEntitySign s = (TileEntitySign) worldObj.getTileEntity(pos.add(3, 1, 0));
						s.signText[0] = new TextComponentTranslation("voidcraft.twins.riddle.1.1");
						s.signText[1] = new TextComponentTranslation("voidcraft.twins.riddle.1.2");
						s.signText[2] = new TextComponentTranslation("voidcraft.twins.riddle.1.3");
						s.signText[3] = new TextComponentTranslation("voidcraft.twins.riddle.1.4");
						break;
					case 18:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.1.18"));
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
