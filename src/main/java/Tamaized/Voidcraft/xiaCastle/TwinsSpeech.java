package Tamaized.Voidcraft.xiaCastle;

import java.util.ArrayList;
import java.util.List;

import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;

public class TwinsSpeech {

	private SpeechBase speech1 = new Speech1();
	private SpeechBase speech2 = new Speech2();

	private ArrayList<EntityPlayer> list1 = new ArrayList<EntityPlayer>();
	private ArrayList<EntityPlayer> list2 = new ArrayList<EntityPlayer>();

	public void reset() {
		speech1.reset();
		speech2.reset();
	}

	public void update(List<EntityPlayer> list) {
		for (EntityPlayer player : list) {
			IVoidicInfusionCapability cap = player.getCapability(CapabilityList.VOIDICINFUSION, null);
			if (cap != null) {
				int i = cap.getXiaDefeats();
				switch (i) {
					case 1:
						list1.add(player);
						break;
					case 2:
						list2.add(player);
						break;
					default:
						break;
				}
			}
		}
		if (!speech1.done()) speech1.update(list1);
		if (!speech2.done()) speech2.update(list2);
		list1.clear();
		list2.clear();
	}

	public boolean done() {
		return speech1.done() && speech2.done();
	}

	private void sendMessage(List<EntityPlayer> list, String message) {
		for (EntityPlayer player : list)
			player.sendMessage(new TextComponentTranslation(message, player.getName()));
	}

	private abstract class SpeechBase {

		protected int index = 0;
		protected int tick = 1;
		protected int messageTick = 20 * 5;
		protected boolean done = false;

		public abstract void update(List<EntityPlayer> list);

		public final void reset() {
			index = 0;
			tick = 1;
			messageTick = 20 * 5;
			done = false;
		}

		public final boolean done() {
			return done;
		}

	}

	private class Speech1 extends SpeechBase {

		@Override
		public void update(List<EntityPlayer> list) {
			if (tick % messageTick == 0) {
				switch (index) {
					case 0:
						sendMessage(list, "voidcraft.twins.speech.end.1.1");
						messageTick = 20 * 4;
						break;
					case 1:
						sendMessage(list, "voidcraft.twins.speech.end.1.2");
						messageTick = 20 * 4;
						break;
					case 2:
						sendMessage(list, "voidcraft.twins.speech.end.1.3");
						messageTick = 20 * 4;
						break;
					case 3:
						sendMessage(list, "voidcraft.twins.speech.end.1.4");
						messageTick = 20 * 7;
						break;
					case 4:
						sendMessage(list, "voidcraft.twins.speech.end.1.5");
						messageTick = 20 * 7;
						break;
					case 5:
						sendMessage(list, "voidcraft.twins.speech.end.1.6");
						messageTick = 20 * 6;
						break;
					case 6:
						sendMessage(list, "voidcraft.twins.speech.end.1.7");
						messageTick = 20 * 6;
						break;
					case 7:
						sendMessage(list, "voidcraft.twins.speech.end.1.8");
						messageTick = 20 * 6;
						break;
					case 8:
						sendMessage(list, "voidcraft.twins.speech.end.1.9");
						messageTick = 20 * 5;
						break;
					case 9:
						sendMessage(list, "voidcraft.twins.speech.end.1.10");
						messageTick = 20 * 6;
						break;
					case 10:
						sendMessage(list, "voidcraft.twins.speech.end.1.11");
						messageTick = 20 * 6;
						break;
					case 11:
						sendMessage(list, "voidcraft.twins.speech.end.1.12");
						messageTick = 20 * 6;
						break;
					case 12:
						sendMessage(list, "voidcraft.twins.speech.end.1.13");
						messageTick = 20 * 6;
						break;
					case 13:
						sendMessage(list, "voidcraft.twins.speech.end.1.14");
						messageTick = 20 * 5;
						break;
					case 14:
						sendMessage(list, "voidcraft.twins.speech.end.1.15");
						messageTick = 20 * 5;
						break;
					case 15:
						sendMessage(list, "voidcraft.twins.speech.end.1.16");
						messageTick = 20 * 7;
						break;
					case 16:
						sendMessage(list, "voidcraft.twins.speech.end.1.17");
						break;
					default:
						done = true;
						break;
				}
				tick = 0;
				index++;
			}
			tick++;
		}

	}

	private class Speech2 extends SpeechBase {

		@Override
		public void update(List<EntityPlayer> list) {

			if (tick % messageTick == 0) {
				switch (index) {
					case 0:
						sendMessage(list, "voidcraft.twins.speech.end.2.1");
						messageTick = 20 * 4;
						break;
					case 1:
						sendMessage(list, "voidcraft.twins.speech.end.2.2");
						messageTick = 20 * 4;
						break;
					case 2:
						sendMessage(list, "voidcraft.twins.speech.end.2.3");
						messageTick = 20 * 4;
						break;
					case 3:
						sendMessage(list, "voidcraft.twins.speech.end.2.4");
						messageTick = 20 * 4;
						break;
					case 4:
						sendMessage(list, "voidcraft.twins.speech.end.2.5");
						messageTick = 20 * 4;
						break;
					case 5:
						sendMessage(list, "voidcraft.twins.speech.end.2.6");
						messageTick = 20 * 4;
						break;
					case 6:
						sendMessage(list, "voidcraft.twins.speech.end.2.7");
						messageTick = 20 * 4;
						break;
					case 7:
						sendMessage(list, "voidcraft.twins.speech.end.2.8");
						messageTick = 20 * 7;
						break;
					case 8:
						sendMessage(list, "voidcraft.twins.speech.end.2.9");
						messageTick = 20 * 4;
						break;
					case 9:
						sendMessage(list, "voidcraft.twins.speech.end.2.10");
						messageTick = 20 * 5;
						break;
					case 10:
						sendMessage(list, "voidcraft.twins.speech.end.2.11");
						messageTick = 20 * 5;
						break;
					case 11:
						sendMessage(list, "voidcraft.twins.speech.end.2.12");
						messageTick = 20 * 5;
						break;
					case 12:
						sendMessage(list, "voidcraft.twins.speech.end.2.13");
						messageTick = 20 * 4;
						break;
					case 13:
						sendMessage(list, "voidcraft.twins.speech.end.2.14");
						messageTick = 20 * 5;
						break;
					case 14:
						sendMessage(list, "voidcraft.twins.speech.end.2.15");
						messageTick = 20 * 4;
						break;
					case 15:
						sendMessage(list, "voidcraft.twins.speech.end.2.16");
						messageTick = 20 * 5;
						break;
					case 16:
						sendMessage(list, "voidcraft.twins.speech.end.2.17");
						messageTick = 20 * 5;
						break;
					case 17:
						sendMessage(list, "voidcraft.twins.speech.end.2.18");
						messageTick = 20 * 5;
						break;
					case 18:
						sendMessage(list, "voidcraft.twins.speech.end.2.19");
						messageTick = 20 * 5;
						break;
					case 19:
						sendMessage(list, "voidcraft.twins.speech.end.2.20");
						messageTick = 20 * 7;
						break;
					case 20:
						sendMessage(list, "voidcraft.twins.speech.end.2.21");
						messageTick = 20 * 5;
						break;
					case 21:
						sendMessage(list, "voidcraft.twins.speech.end.2.22");
						messageTick = 20 * 5;
						break;
					case 22:
						sendMessage(list, "voidcraft.twins.speech.end.2.23");
						messageTick = 20 * 5;
						break;
					case 23:
						sendMessage(list, "voidcraft.twins.speech.end.2.24");
						messageTick = 20 * 4;
						break;
					case 24:
						sendMessage(list, "voidcraft.twins.speech.end.2.25");
						messageTick = 20 * 4;
						break;
					case 25:
						sendMessage(list, "voidcraft.twins.speech.end.2.26");
						messageTick = 20 * 5;
						break;
					case 26:
						sendMessage(list, "voidcraft.twins.speech.end.2.27");
						messageTick = 20 * 4;
						break;
					case 27:
						sendMessage(list, "voidcraft.twins.speech.end.2.28");
						messageTick = 20 * 5;
						break;
					case 28:
						sendMessage(list, "voidcraft.twins.speech.end.2.29");
						messageTick = 20 * 5;
						break;
					case 29:
						sendMessage(list, "voidcraft.twins.speech.end.2.30");
						messageTick = 20 * 5;
						break;
					case 30:
						sendMessage(list, "voidcraft.twins.speech.end.2.31");
						messageTick = 20 * 4;
						break;
					case 31:
						sendMessage(list, "voidcraft.twins.speech.end.2.32");
						messageTick = 20 * 5;
						break;
					case 32:
						sendMessage(list, "voidcraft.twins.speech.end.2.33");
						messageTick = 20 * 4;
						break;
					case 33:
						sendMessage(list, "voidcraft.twins.speech.end.2.34");
						messageTick = 20 * 8;
						break;
					case 34:
						sendMessage(list, "voidcraft.twins.speech.end.2.35");
						messageTick = 20 * 5;
						break;
					case 35:
						sendMessage(list, "voidcraft.twins.speech.end.2.36");
						messageTick = 20 * 5;
						break;
					case 36:
						sendMessage(list, "voidcraft.twins.speech.end.2.37");
						messageTick = 20 * 7;
						break;
					case 37:
						sendMessage(list, "voidcraft.twins.speech.end.2.38");
						messageTick = 20 * 3;
						break;
					case 38:
						sendMessage(list, "voidcraft.twins.speech.end.2.39");
						messageTick = 20 * 4;
						break;
					case 39:
						sendMessage(list, "voidcraft.twins.speech.end.2.40");
						messageTick = 20 * 5;
						break;
					case 40:
						sendMessage(list, "voidcraft.twins.speech.end.2.41");
						messageTick = 20 * 6;
						break;
					case 41:
						sendMessage(list, "voidcraft.twins.speech.end.2.42");
						messageTick = 20 * 5;
						break;
					case 42:
						sendMessage(list, "voidcraft.twins.speech.end.2.43");
						messageTick = 20 * 5;
						break;
					case 43:
						sendMessage(list, "voidcraft.twins.speech.end.2.44");
						messageTick = 20 * 5;
						break;
					case 44:
						sendMessage(list, "voidcraft.twins.speech.end.2.45");
						messageTick = 20 * 4;
						break;
					case 45:
						sendMessage(list, "voidcraft.twins.speech.end.2.46");
						messageTick = 20 * 5;
						break;
					case 46:
						sendMessage(list, "voidcraft.twins.speech.end.2.47");
						messageTick = 20 * 4;
						break;
					case 47:
						sendMessage(list, "voidcraft.twins.speech.end.2.48");
						messageTick = 20 * 4;
						break;
					default:
						done = true;
						break;
				}
				tick = 0;
				index++;
			}
			tick++;
		}

	}

}
