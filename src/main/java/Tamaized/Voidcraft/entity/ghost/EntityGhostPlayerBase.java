package Tamaized.Voidcraft.entity.ghost;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import Tamaized.Voidcraft.entity.EntityVoidNPC;
import Tamaized.Voidcraft.handlers.SkinHandler;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGhostPlayerBase extends EntityVoidNPC implements IEntityAdditionalSpawnData {

	private GameProfile profile;
	private UUID id;

	private boolean canInteract = false;
	private boolean hasInteracted = false;
	private boolean running = false;
	private int tick = 0;
	private int finalTick = 20 * 6;

	private boolean rune = false;
	private Entity runeTarget;
	private int runeState = 0;
	private int maxRuneState = 100;

	private int runeRotation = 0;

	public EntityGhostPlayerBase(World par1World) {
		super(par1World);

		this.isImmuneToFire = true;
		this.hurtResistantTime = 10;
		this.setSize(0.6F, 1.8F);

		// this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 50.0F));
		// this.tasks.addTask(6, new EntityAILookIdle(this));
		// this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.setInvul(true);

	}

	protected EntityGhostPlayerBase(World world, UUID id, boolean interactable) {
		this(world);
		profile = SkinHandler.getGameProfile(id);
		this.id = id;
		canInteract = interactable;
	}

	protected EntityGhostPlayerBase(World world, UUID id, boolean interactable, Entity target, int length) {
		this(world);
		profile = SkinHandler.getGameProfile(id);
		this.id = id;
		canInteract = interactable;
		rune = true;
		runeTarget = target;
		runeState = 0;
		maxRuneState = length;
		running = true;
		tick = 0;
	}

	public boolean isInteractable() {
		return canInteract;
	}

	public boolean hasRuneState() {
		return rune;
	}

	public int getRuneState() {
		return runeState;
	}

	public int getMaxRuneState() {
		return maxRuneState;
	}

	public float getRuneStatePerc() {
		return (float) runeState / (float) maxRuneState;
	}

	public boolean isRuneCharged() {
		return getRuneStatePerc() >= 1.0F;
	}

	public Entity getRuneTarget() {
		return runeTarget;
	}

	public int getRuneRotationForRender() {
		return runeRotation;
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		ByteBufUtils.writeUTF8String(buffer, id == null ? SkinHandler.getUUID(0).toString() : id.toString());
		buffer.writeBoolean(canInteract);
		buffer.writeBoolean(rune);
		if (rune) {
			buffer.writeInt(runeTarget.getEntityId());
			buffer.writeInt(maxRuneState);
		}
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		id = UUID.fromString(ByteBufUtils.readUTF8String(additionalData));
		profile = SkinHandler.getGameProfile(id);
		canInteract = additionalData.readBoolean();
		rune = additionalData.readBoolean();
		if (rune) {
			runeTarget = world.getEntityByID(additionalData.readInt());
			maxRuneState = additionalData.readInt();
		}
	}

	public UUID getUUID() {
		return id;
	}

	public boolean hasInteracted() {
		return hasInteracted;
	}

	public boolean isRunning() {
		return running;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (!world.isRemote) {
			if (profile == null) {
				this.setDead();
				return;
			}
			if (running) {
				if (rune && runeState < maxRuneState) runeState++;
			}
			if (tick >= finalTick) hasInteracted = true;
			sendPacketUpdates();
		}
		runeRotation += (int) Math.ceil(20F * getRuneStatePerc());
		if (runeRotation >= 360) runeRotation = 0;
		if (rune) {
			if (runeTarget != null) {
				updateLook();
				if (world.isRemote && isRuneCharged()) {
					spawnParticles();
				}
			} else {
				setDead();
			}
		}
		if (running) tick++;
	}

	@SideOnly(Side.CLIENT)
	private void spawnParticles() {
		for (int i = 0; i < 10; i++) {
			double rx = (world.rand.nextDouble() * 1.5) - 0.5;
			double ry = (world.rand.nextDouble() * 1.5) - 0.5;
			double rz = (world.rand.nextDouble() * 1.5) - 0.5;
			double ox = posX;
			double oy = posY;
			double oz = posZ;
			double tx = getRuneTarget().posX + rx;
			double ty = getRuneTarget().posY + (getRuneTarget().height / 2.0F) + ry;
			double tz = getRuneTarget().posZ + rz;
			Vec3d vec = getLookVec();
			double offsetX = (vec.xCoord * 1);
			double offsetY = 1.5;
			double offsetZ = (vec.zCoord * 1);
			double dx = ox - (tx - offsetX);
			double dy = getEntityBoundingBox().minY + (double) (height / 2.0F) - (((ty - offsetY) + (double) (getRuneTarget().height / 2.0F)));
			double dz = oz - (tz - offsetZ);
			net.minecraft.client.Minecraft.getMinecraft().effectRenderer.addEffect(new Tamaized.Voidcraft.particles.ParticleColorEnchantmentTable(world, tx, ty + (getRuneTarget().height / 2F), tz, dx, dy, dz));
		}
	}

	@Override
	protected void encodePacketData(DataOutputStream stream) throws IOException {
		stream.writeBoolean(running);
		stream.writeBoolean(hasInteracted);
		stream.writeBoolean(rune);
		if (rune) {
			stream.writeInt(runeTarget.getEntityId());
			stream.writeInt(runeState);
			stream.writeInt(maxRuneState);
		}
	}

	@Override
	protected void decodePacketData(ByteBufInputStream stream) throws IOException {
		running = stream.readBoolean();
		hasInteracted = stream.readBoolean();
		rune = stream.readBoolean();
		if (rune) {
			runeTarget = world.getEntityByID(stream.readInt());
			runeState = stream.readInt();
			maxRuneState = stream.readInt();
		}
	}

	private void updateLook() {
		getLookHelper().setLookPosition(runeTarget.posX, runeTarget.posY + (double) runeTarget.getEyeHeight(), runeTarget.posZ, 10.0F, (float) getVerticalFaceSpeed());
		double d0 = runeTarget.posX - posX;
		double d2 = runeTarget.posZ - posZ;
		float f = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
		float f3 = MathHelper.wrapDegrees(f - rotationYaw);
		rotationYaw = rotationYaw + f3;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand, ItemStack s) {
		if (running || !canInteract) return false;
		running = true;
		sendPacketUpdates();
		return true;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void collideWithEntity(Entity par1Entity) {
	}

	@Override
	public void applyEntityCollision(Entity par1Entity) {
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(999.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.0D);
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
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {

	}

	@Override
	@SideOnly(Side.CLIENT)
	public ITextComponent getDisplayName() {
		return new TextComponentString(profile == null ? "null" : profile.getName() == null ? "null" : profile.getName());
	}

	public static EntityGhostPlayerBase newInstance(World world, UUID id, boolean interactable) {
		return SkinHandler.isBipedModel(id) ? new EntityGhostBiped(world, id, interactable) : new EntityGhostPlayer(world, id, interactable);
	}

	public static EntityGhostPlayerBase newInstance(World world, UUID id, boolean interactable, Entity target, int length) {
		return SkinHandler.isBipedModel(id) ? new EntityGhostBiped(world, id, interactable, target, length) : new EntityGhostPlayer(world, id, interactable, target, length);
	}

}