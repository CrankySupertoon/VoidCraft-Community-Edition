package Tamaized.Voidcraft.capabilities;

import Tamaized.Voidcraft.capabilities.elytraFlying.IElytraFlyingCapability;
import Tamaized.Voidcraft.capabilities.starforge.IStarForgeCapability;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.capabilities.vadeMecumItem.IVadeMecumItemCapability;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import Tamaized.Voidcraft.capabilities.voidicPower.IVoidicPowerCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class CapabilityList {

	@CapabilityInject(IVoidicInfusionCapability.class)
	public static final Capability<IVoidicInfusionCapability> VOIDICINFUSION = null;

	@CapabilityInject(IVoidicPowerCapability.class)
	public static final Capability<IVoidicPowerCapability> VOIDICPOWER = null;

	@CapabilityInject(IVadeMecumCapability.class)
	public static final Capability<IVadeMecumCapability> VADEMECUM = null;

	@CapabilityInject(IVadeMecumItemCapability.class)
	public static final Capability<IVadeMecumItemCapability> VADEMECUMITEM = null;

	@CapabilityInject(IElytraFlyingCapability.class)
	public static final Capability<IElytraFlyingCapability> ELYTRAFLYING = null;

	@CapabilityInject(IStarForgeCapability.class)
	public static final Capability<IStarForgeCapability> STARFORGE = null;

}
