package Tamaized.Voidcraft.entity.companion;

import net.minecraft.world.World;

public class EntityCompanionFireElemental extends EntityCompanion {

	public EntityCompanionFireElemental(World worldIn) {
		super(worldIn);
		setSize(0.40F, 1.15F);
	}

	@Override
	protected double getDefaultMoveSpeed(boolean tamed) {
		return 0.30000001192092896D;
	}

	@Override
	protected double getDefaultMaxHealth(boolean tamed) {
		return tamed ? 20.0D : 8.0D;
	}

	@Override
	protected double getDefaultDamage(boolean tamed) {
		return tamed ? 4.0D : 2.0D;
	}

}
