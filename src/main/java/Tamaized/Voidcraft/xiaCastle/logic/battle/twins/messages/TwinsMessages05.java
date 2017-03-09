package Tamaized.Voidcraft.xiaCastle.logic.battle.twins.messages;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class TwinsMessages05 {

	public static int tick = 1;
	public static int childPhase = 0;
	public static int childPhaseModulate = 20 * 5;

	public static boolean run(World worldObj, BlockPos pos) {
		if (tick % childPhaseModulate == 0) {
			for (EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))) {
				switch (childPhase) {
					case 0:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.5.1"));
						childPhaseModulate = 20 * 5;
						break;
					case 1:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.5.2"));
						childPhaseModulate = 20 * 7;
						break;
					case 2:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.5.3"));
						break;
					case 3:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.5.4"));
						childPhaseModulate = 20 * 8;
						break;
					case 4:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.5.5"));
						childPhaseModulate = 20 * 5;
						break;
					case 5:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.5.6"));
						childPhaseModulate = 20 * 7;
						break;
					case 6:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.5.7"));
						childPhaseModulate = 20 * 8;
						break;
					case 7:
						p.sendMessage(new TextComponentTranslation("voidcraft.twins.speech.riddle.5.8", p.getGameProfile().getName()));
						childPhaseModulate = 20 * 5;
						break;
					case 8:
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
