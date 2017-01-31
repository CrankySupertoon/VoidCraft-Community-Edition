package Tamaized.Voidcraft.entity.boss.twins;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import Tamaized.Voidcraft.xiaCastle.logic.battle.twins.TwinsBattleHandler;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class EntityBossZol extends EntityVoidBoss<TwinsBattleHandler> {

	public EntityBossZol(World par1World) {
		super(par1World);
		this.setInvul(true);
	}

	public EntityBossZol(World world, TwinsBattleHandler handler) {
		super(world, handler, true);
		this.setInvul(true);
	}

	@Override
	protected void triggerOnDamage(int phase, DamageSource source, float amount) {

	}
	
	@Override
	protected void deathHook() {
		
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		// start();
		return super.processInteract(player, hand);
	}

	@Override
	protected void initPhase(int phase) {

	}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {

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
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobZolSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound() {
		return VoidSoundEvents.EntityMobZolSoundEvents.hurtSound;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VoidSoundEvents.EntityMobZolSoundEvents.deathSound;
	}

	@Override
	protected float getSoundVolume() {
		return 0.5F;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString("Zol");
	}

	@Override
	protected void encodePacketData(DataOutputStream stream) throws IOException {
		
	}

	@Override
	protected void decodePacketData(ByteBufInputStream stream) throws IOException {
		
	}

}