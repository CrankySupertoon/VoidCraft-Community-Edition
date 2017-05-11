package Tamaized.Voidcraft.entity.boss.xia;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Tamaized.TamModized.helper.PacketHelper;
import Tamaized.TamModized.helper.PacketHelper.PacketWrapper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.entity.ghost.EntityGhostPlayerBase;
import Tamaized.Voidcraft.events.client.DebugEvent;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import Tamaized.Voidcraft.network.IEntitySync;
import Tamaized.Voidcraft.network.IVoidBossAIPacket;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia2.Xia2BattleHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia2.phases.EntityAIXia2Phase1;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia2.phases.EntityAIXia2Phase2;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia2.phases.EntityAIXia2Phase3;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class EntityBossXia2 extends EntityVoidBoss<Xia2BattleHandler> implements IEntitySync {

	private boolean sphereState = false;

	private List<EntityGhostPlayerBase> ghostList = new ArrayList<EntityGhostPlayerBase>();

	public EntityBossXia2(World par1World) {
		super(par1World);
		this.setInvul(true);
	}

	public EntityBossXia2(World world, Xia2BattleHandler handler) {
		super(world, handler, false);
		this.setInvul(true);
	}

	public void setSphereState(boolean state) {
		sphereState = state;
		if (!world.isRemote) sendPacketUpdates();
	}

	public boolean shouldSphereRender() {
		return sphereState;
	}

	@Override
	protected void encodePacketData(DataOutputStream stream) throws IOException {
		stream.writeBoolean(sphereState);
	}

	@Override
	protected void decodePacketData(ByteBufInputStream stream) throws IOException {
		setSphereState(stream.readBoolean());
	}

	@Override
	protected void triggerOnDamage(int phase, DamageSource source, float amount) {
		sendPacketToBus(new Xia2TookDamagePacket());
	}

	@Override
	protected void deathHook() {

	}

	public class Xia2TookDamagePacket implements IVoidBossAIPacket {

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

	public void addGhost(EntityGhostPlayerBase ghost) {
		ghostList.add(ghost);
	}

	public List<EntityGhostPlayerBase> getGhostList() {
		return ghostList;
	}

	public void clearGhosts() {
		ghostList.clear();
	}

	@Override
	protected void initPhase(int phase) {
		setSphereState(false);
		clearGhosts();
		switch (phase) {
			case 1: {
				isFlying = true;
				getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
				setHealth(getMaxHealth());
				// BossMusicManager.PlayTheSound(this.worldObj, this, new ItemStack(voidCraft.items.voidDiscs.get(10)), new int[]{(int) this.posX, (int) this.posY, (int) this.posZ}, true);
				setInvul(false);
				addAI(new EntityAIXia2Phase1(this, getFilters()));
			}
				break;
			case 2: {
				getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
				setHealth(getMaxHealth());
				setInvul(false);
				addAI(new EntityAIXia2Phase2(this, getFilters()));
			}
				break;
			case 3: {
				isFlying = true;
				getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
				setHealth(getMaxHealth());
				setInvul(false);
				addAI(new EntityAIXia2Phase3(this, getFilters()));
			}
				break;
			default:
				break;
		}
		setArmRotations(0, 0, 0, 0, true);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobXiaSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound() {
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
		return new TextComponentString("Xia");
	}

	@Override
	protected void updatePhase(int phase) {
		for (EntityPlayer player : world.playerEntities) {
			if (player.getDistanceToEntity(getEntity()) >= 100) {
				player.sendMessage(new TextComponentTranslation("voidcraft.misc.xia.escape"));
				player.setPositionAndUpdate(getEntity().posX, getEntity().posY, getEntity().posZ - 2);
			}
		}
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

}