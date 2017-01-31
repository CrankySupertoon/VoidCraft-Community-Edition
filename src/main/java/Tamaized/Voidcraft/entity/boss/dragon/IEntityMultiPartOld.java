package Tamaized.Voidcraft.entity.boss.dragon;

import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public interface IEntityMultiPartOld {

	World getWorld();

	boolean attackEntityFromPart(EntityDragonPartOld dragonPart, DamageSource source, float damage);

}
