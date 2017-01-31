package Tamaized.Voidcraft.entity.nonliving;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.world.World;

public class EntityCasterLightningBolt extends EntityLightningBolt {

	private final EntityLivingBase caster;
	
	public EntityCasterLightningBolt(World world){
		super(world, 0, 0, 0, true);
		caster = null;
	}

	public EntityCasterLightningBolt(World worldIn, EntityLivingBase caster, double x, double y, double z, boolean effectOnlyIn) {
		super(worldIn, x, y, z, effectOnlyIn);
		this.caster = caster;
	}

	public EntityLivingBase getCaster() {
		return caster;
	}

}
