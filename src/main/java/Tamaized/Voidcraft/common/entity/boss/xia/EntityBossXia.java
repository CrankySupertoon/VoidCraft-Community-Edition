package Tamaized.Voidcraft.common.entity.boss.xia;

import Tamaized.TamModized.helper.PacketHelper;
import Tamaized.TamModized.helper.PacketHelper.PacketWrapper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.common.entity.EntityVoidBoss;
import Tamaized.Voidcraft.client.entity.animation.AnimationRegistry;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import Tamaized.Voidcraft.network.IVoidBossAIPacket;
import Tamaized.Voidcraft.common.sound.VoidSoundEvents;
import Tamaized.Voidcraft.common.xiacastle.logic.battle.xia.XiaBattleHandler;
import Tamaized.Voidcraft.common.xiacastle.logic.battle.xia.phases.EntityAIXiaPhase1;
import Tamaized.Voidcraft.common.xiacastle.logic.battle.xia.phases.EntityAIXiaPhase2;
import Tamaized.Voidcraft.common.xiacastle.logic.battle.xia.phases.EntityAIXiaPhase3;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class EntityBossXia extends EntityVoidBoss<XiaBattleHandler> {

	public EntityBossXia(World par1World) {
		super(par1World);
		this.setInvulnerable(true);
	}

	public EntityBossXia(World world, XiaBattleHandler handler) {
		super(world, handler, false);
		this.setInvulnerable(true);
	}

	@Override
	protected void triggerOnDamage(int phase, DamageSource source, float amount) {
		sendPacketToBus(new XiaTookDamagePacket());
	}

	@Override
	protected void deathHook() {
		world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(VoidCraft.armors.xiaHelmet)));
		world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(VoidCraft.armors.xiaChest)));
		world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(VoidCraft.armors.xiaLegs)));
		world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(VoidCraft.armors.xiaBoots)));
		for (EntityPlayer player : getHandler().getPlayers()) {
			player.sendMessage(new TextComponentTranslation("voidcraft.misc.xia.death"));
		}
	}

	public class XiaTookDamagePacket implements IVoidBossAIPacket {

	}

	@Override
	public void addPotionEffect(PotionEffect potioneffectIn) {
		Potion pot = potioneffectIn.getPotion();
		if (pot == VoidCraft.potions.fireSheathe || pot == VoidCraft.potions.frostSheathe || pot == VoidCraft.potions.litSheathe || pot == VoidCraft.potions.acidSheathe) super.addPotionEffect(potioneffectIn);
		if (!world.isRemote) {
			try {
				PacketWrapper packet = PacketHelper.createPacket(VoidCraft.channel, VoidCraft.networkChannelName, ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.SHEATHE));
				DataOutputStream stream = packet.getStream();
				stream.writeInt(getEntityId());
				stream.writeInt(Potion.getIdFromPotion(pot));
				stream.writeInt(potioneffectIn.getDuration());
				packet.sendPacket(new TargetPoint(world.provider.getDimension(), posX, posY, posZ, 64));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		// start();
		return super.processInteract(player, hand);
	}

	@Override
	protected void initPhase(int phase) {
		if (phase == 1) {
			/**
			 * Cycle: - Teleports around, does various attacks, can be hit directly
			 */
			isFlying = true;
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
			this.setHealth(this.getMaxHealth());
			// BossMusicManager.PlayTheSound(this.worldObj, this, new ItemStack(voidCraft.items.voidDiscs.get(10)), new int[]{(int) this.posX, (int) this.posY, (int) this.posZ}, true);
			setInvulnerable(false);
			addAI(new EntityAIXiaPhase1(this, getFilters()));
		} else if (phase == 2) {
			/**
			 * Cycle: - Teleports a short distance from a target player, walks towards them with a demonic sword.
			 */
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
			this.setHealth(this.getMaxHealth());
			setInvulnerable(false);
			addAI(new EntityAIXiaPhase2(this, getFilters()));
		} else if (phase == 3) {
			/**
			 * Cycle: - Stands still at his throne, various attacks, can take direct hits, upon taking a hit cause a massive blast that throws everyone back His attacks may involve the Vade Mecum spells
			 */
			isFlying = true;
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
			this.setHealth(this.getMaxHealth());
			setInvulnerable(false);
			addAI(new EntityAIXiaPhase3(this, getFilters()));

		}
		AnimationRegistry.AnimationLimbs.play(this, 0, 0, 0, 0);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobXiaSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return VoidSoundEvents.EntityMobXiaSoundEvents.hurtSound;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VoidSoundEvents.EntityMobXiaSoundEvents.deathSound;
	}

	@Override
	protected float getSoundVolume() {
		return 0.0F;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString("xia");
	}

	@Override
	protected void updatePhase(int phase) {

	}

	@Override
	protected ArrayList<Class> getFilters() {
		ArrayList<Class> filter = new ArrayList<Class>();
		filter.add(EntityPlayer.class);
		return filter;
	}

	@Override
	protected boolean immuneToFire() {
		return true;
	}

	@Override
	protected float sizeWidth() {
		return 0.6F;
	}

	@Override
	protected float sizeHeight() {
		return 1.8F;
	}

	@Override
	protected int maxPhases() {
		return 3;
	}

	@Override
	protected void encodePacketData(DataOutputStream stream) throws IOException {

	}

	@Override
	protected void decodePacketData(ByteBufInputStream stream) throws IOException {

	}
}