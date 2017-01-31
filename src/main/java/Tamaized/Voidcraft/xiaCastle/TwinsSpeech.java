package Tamaized.Voidcraft.xiaCastle;

import java.util.ArrayList;
import java.util.List;

import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

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
			player.sendMessage(new TextComponentString(message.replace("[Player]", player.getName())));
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
						sendMessage(list, TextFormatting.AQUA + "Hello. Do you remember us?");
						messageTick = 20 * 4;
						break;
					case 1:
						sendMessage(list, TextFormatting.GREEN + "Believe it or not, we've met before.");
						messageTick = 20 * 4;
						break;
					case 2:
						sendMessage(list, TextFormatting.AQUA + "In fact, we've never left your side.");
						messageTick = 20 * 4;
						break;
					case 3:
						sendMessage(list, TextFormatting.GREEN + "Though, I could imagine that our presence is all too subtle and overbearing for you to even acknowledge our presence.");
						messageTick = 20 * 7;
						break;
					case 4:
						sendMessage(list, TextFormatting.AQUA + "I wouldn't blame you. After all, it's easy to lose sight of reality while living in your dreams.");
						messageTick = 20 * 7;
						break;
					case 5:
						sendMessage(list, TextFormatting.GREEN + "We are the universe. We are everything you think isn't you.");
						messageTick = 20 * 6;
						break;
					case 6:
						sendMessage(list, TextFormatting.AQUA + "We are kind and loving, but Dol is not very humble.");
						messageTick = 20 * 6;
						break;
					case 7:
						sendMessage(list, TextFormatting.GREEN + "Were it not for me you'd use that line every chance you could, Zol.");
						messageTick = 20 * 6;
						break;
					case 8:
						sendMessage(list, TextFormatting.AQUA + "You're not wrong, but let's get back on topic.");
						messageTick = 20 * 5;
						break;
					case 9:
						sendMessage(list, TextFormatting.GREEN + "We are here because you've reached an even higher level, [Player].");
						messageTick = 20 * 6;
						break;
					case 10:
						sendMessage(list, TextFormatting.GREEN + "You’ve overcome great trial and tribulation, so we’ve come to see you, player.");
						messageTick = 20 * 6;
						break;
					case 11:
						sendMessage(list, TextFormatting.GREEN + "To let you know that you played well.");
						messageTick = 20 * 6;
						break;
					case 12:
						sendMessage(list, TextFormatting.AQUA + "But the question remains: Have you reached the highest level?");
						messageTick = 20 * 6;
						break;
					case 13:
						sendMessage(list, TextFormatting.GREEN + "That is something only you hold the answer to.");
						messageTick = 20 * 5;
						break;
					case 14:
						sendMessage(list, TextFormatting.GREEN + "Your potential is only barred by your own willpower.");
						messageTick = 20 * 5;
						break;
					case 15:
						sendMessage(list, TextFormatting.AQUA + "Your presence here is a testament to your character, but there’s not much room for growth here.");
						messageTick = 20 * 7;
						break;
					case 16:
						sendMessage(list, TextFormatting.AQUA + "Perhaps you’ll find the answers you seek by leaping into the abyss below.");
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
						sendMessage(list, TextFormatting.GREEN + "Welcome back");
						messageTick = 20 * 4;
						break;
					case 1:
						sendMessage(list, TextFormatting.AQUA + "What brings you here?");
						messageTick = 20 * 4;
						break;
					case 2:
						sendMessage(list, TextFormatting.GREEN + "Do you enjoy overcoming challenges?");
						messageTick = 20 * 4;
						break;
					case 3:
						sendMessage(list, TextFormatting.AQUA + "Do you seek a tangible reward for your efforts?");
						messageTick = 20 * 4;
						break;
					case 4:
						sendMessage(list, TextFormatting.GREEN + "Or did you simply come to see us again?");
						messageTick = 20 * 4;
						break;
					case 5:
						sendMessage(list, TextFormatting.AQUA + "Well, if it’s the latter, let us not disappoint.");
						messageTick = 20 * 4;
						break;
					case 6:
						sendMessage(list, TextFormatting.GREEN + "Let’s tell the player a story.");
						messageTick = 20 * 4;
						break;
					case 7:
						sendMessage(list, TextFormatting.GREEN + "Time started when there was change. As time flowed, or rather, as we came to be, you came to be as you are now.");
						messageTick = 20 * 7;
						break;
					case 8:
						sendMessage(list, TextFormatting.AQUA + "Us? We have been here since the beginning.");
						messageTick = 20 * 4;
						break;
					case 9:
						sendMessage(list, TextFormatting.GREEN + "…Though, to be truthful… You are just as old as we are.");
						messageTick = 20 * 5;
						break;
					case 10:
						sendMessage(list, TextFormatting.AQUA + "The difference, though, is that, in principle, we haven’t changed.");
						messageTick = 20 * 5;
						break;
					case 11:
						sendMessage(list, TextFormatting.AQUA + "We were born from nothingness and thrust into a lawless world.");
						messageTick = 20 * 5;
						break;
					case 12:
						sendMessage(list, TextFormatting.GREEN + "And so, by our mere existence, we became law.");
						messageTick = 20 * 4;
						break;
					case 13:
						sendMessage(list, TextFormatting.AQUA + "We had a maker. Though, he neglected to tell us his name.");
						messageTick = 20 * 5;
						break;
					case 14:
						sendMessage(list, TextFormatting.GREEN + "We decided to call him “Xia”");
						messageTick = 20 * 4;
						break;
					case 15:
						sendMessage(list, TextFormatting.AQUA + "He told us that we were to govern the realms that he also created.");
						messageTick = 20 * 5;
						break;
					case 16:
						sendMessage(list, TextFormatting.AQUA + "We knew not his of his intentions, but before him, we were nothing.");
						messageTick = 20 * 5;
						break;
					case 17:
						sendMessage(list, TextFormatting.GREEN + "So logically, we followed his order without hesitation.");
						messageTick = 20 * 5;
						break;
					case 18:
						sendMessage(list, TextFormatting.GREEN + "If he held the power to make us, he could easily eliminate us.");
						messageTick = 20 * 5;
						break;
					case 19:
						sendMessage(list, TextFormatting.GREEN + "After all, it is far more simple to destroy than it is to create. You of all people should know this by now.");
						messageTick = 20 * 7;
						break;
					case 20:
						sendMessage(list, TextFormatting.AQUA + "I, Zol, was to be the order which oversaw the development of the universe.");
						messageTick = 20 * 5;
						break;
					case 21:
						sendMessage(list, TextFormatting.AQUA + "From my intangible skies above, I would set the pace of time itself.");
						messageTick = 20 * 5;
						break;
					case 22:
						sendMessage(list, TextFormatting.GREEN + "I, Dol, was to give the universe form and function.");
						messageTick = 20 * 5;
						break;
					case 23:
						sendMessage(list, TextFormatting.GREEN + "I gave room for all worlds to exist, I even gave you space for your own.");
						messageTick = 20 * 4;
						break;
					case 24:
						sendMessage(list, TextFormatting.AQUA + "Together, we shaped the Universe as you know it.");
						messageTick = 20 * 4;
						break;
					case 25:
						sendMessage(list, TextFormatting.AQUA + "Everything that ever has, is, and will happen falls under our jurisdiction.");
						messageTick = 20 * 5;
						break;
					case 26:
						sendMessage(list, TextFormatting.AQUA + "Even your presence here today.");
						messageTick = 20 * 4;
						break;
					case 27:
						sendMessage(list, TextFormatting.AQUA + "We have been watching you form since you were just a random set of atoms.");
						messageTick = 20 * 5;
						break;
					case 28:
						sendMessage(list, TextFormatting.AQUA + "You were a part of many things before you were called [Player].");
						messageTick = 20 * 5;
						break;
					case 29:
						sendMessage(list, TextFormatting.AQUA + "You were once Dust, Rock, Star, Mountain, River, and Valley.");
						messageTick = 20 * 5;
						break;
					case 30:
						sendMessage(list, TextFormatting.AQUA + "You were also Microbe, Virus, Flora, and Fauna.");
						messageTick = 20 * 4;
						break;
					case 31:
						sendMessage(list, TextFormatting.AQUA + "And countless other things too, this is just to name a few.");
						messageTick = 20 * 5;
						break;
					case 32:
						sendMessage(list, TextFormatting.GREEN + "And in your previous forms, you did many things.");
						messageTick = 20 * 4;
						break;
					case 33:
						sendMessage(list, TextFormatting.GREEN + "Sometimes you sunbathed to convert the kinetic energy from photons into chemical energy in an extremely complex, yet beautiful process which your current form calls “Photosynthesis”");
						messageTick = 20 * 8;
						break;
					case 34:
						sendMessage(list, TextFormatting.GREEN + "Sometimes you were hunting. Sometimes you were being hunted.");
						messageTick = 20 * 5;
						break;
					case 35:
						sendMessage(list, TextFormatting.GREEN + "Other times you were motionless. Either because you were dead or inanimate.");
						messageTick = 20 * 5;
						break;
					case 36:
						sendMessage(list, TextFormatting.GREEN + "Regardless of the form you took, however, there were some things that you always did throughout your entire existence.");
						messageTick = 20 * 7;
						break;
					case 37:
						sendMessage(list, TextFormatting.AQUA + "You changed.");
						messageTick = 20 * 3;
						break;
					case 38:
						sendMessage(list, TextFormatting.GREEN + "We mean that in both senses of the word.");
						messageTick = 20 * 4;
						break;
					case 39:
						sendMessage(list, TextFormatting.AQUA + "You have grown in character as much as you have grown as a body.");
						messageTick = 20 * 5;
						break;
					case 40:
						sendMessage(list, TextFormatting.AQUA + "Gathering together both the knowledge needed to succeed, and the nutrients you need to make your body stronger.");
						messageTick = 20 * 6;
						break;
					case 41:
						sendMessage(list, TextFormatting.GREEN + "You have also altered the world around you. Especially in this Short Dream of the game.");
						messageTick = 20 * 5;
						break;
					case 42:
						sendMessage(list, TextFormatting.GREEN + "We gave you the time and space you needed to do all of this.");
						messageTick = 20 * 5;
						break;
					case 43:
						sendMessage(list, TextFormatting.GREEN + "But the question remains: What will you do with it, if anything at all?");
						messageTick = 20 * 5;
						break;
					case 44:
						sendMessage(list, TextFormatting.GREEN + "Only you hold the answer to that question.");
						messageTick = 20 * 4;
						break;
					case 45:
						sendMessage(list, TextFormatting.GREEN + "But even if you have plans, you will have to leap into action to leave this place.");
						messageTick = 20 * 5;
						break;
					case 46:
						sendMessage(list, TextFormatting.GREEN + "There is nothing left for you here, so go…");
						messageTick = 20 * 4;
						break;
					case 47:
						sendMessage(list, TextFormatting.GREEN + "Go out and live your dream.");
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
