package Tamaized.Voidcraft.entity.boss.xia.finalphase;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia2.phases.EntityAIXia2Phase3;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.BossInfo.Color;
import net.minecraft.world.World;

public class EntityZolXia extends EntityTwinsXia {

	private int actionTick = 20 * 3;

	public EntityZolXia(World worldIn) {
		super(worldIn);
		ignoreFrustumCheck = true;
	}

	public EntityZolXia(World world, EntityAIXia2Phase3 entityAIXia2Phase3) {
		super(world, entityAIXia2Phase3);
	}

	@Override
	protected void update() {
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000000298023224D);
		if (!world.isRemote) {
			if (getActivePotionEffect(VoidCraft.potions.litSheathe) == null) {
				clearActivePotions();
				addPotionEffect(new PotionEffect(VoidCraft.potions.litSheathe, 100));
			}
			if (ticksExisted % actionTick == 0 && watchedEntity != null && watchedEntity instanceof EntityLivingBase) {
				double x = watchedEntity.posX;
				double y = watchedEntity.posY;
				double z = watchedEntity.posZ;
				EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, x, y, z, false);
				world.addWeatherEffect(entitylightningbolt);
			}
		}
	}

	@Override
	public ITextComponent getAlternateBossName() {
		return new TextComponentString("Zol");
	}

	@Override
	protected Color getBossBarColor() {
		return Color.WHITE;
	}

}
