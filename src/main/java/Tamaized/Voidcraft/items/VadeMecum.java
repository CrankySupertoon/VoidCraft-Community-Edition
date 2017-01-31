package Tamaized.Voidcraft.items;

import Tamaized.TamModized.items.TamItem;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.capabilities.vadeMecumItem.IVadeMecumItemCapability;
import Tamaized.Voidcraft.handlers.VadeMecumRitualHandler;
import Tamaized.Voidcraft.handlers.VadeMecumWordsOfPower;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicAlchemy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VadeMecum extends TamItem {

	public VadeMecum(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new ICapabilitySerializable<NBTTagCompound>() {

			IVadeMecumItemCapability inst = CapabilityList.VADEMECUMITEM.getDefaultInstance();

			@Override
			public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
				return capability == CapabilityList.VADEMECUMITEM;
			}

			@Override
			public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
				return capability == CapabilityList.VADEMECUMITEM ? CapabilityList.VADEMECUMITEM.<T> cast(inst) : null;
			}

			@Override
			public NBTTagCompound serializeNBT() {
				return (NBTTagCompound) CapabilityList.VADEMECUMITEM.getStorage().writeNBT(CapabilityList.VADEMECUMITEM, inst, null);
			}

			@Override
			public void deserializeNBT(NBTTagCompound nbt) {
				CapabilityList.VADEMECUMITEM.getStorage().readNBT(CapabilityList.VADEMECUMITEM, inst, null, nbt);
			}

		};
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = world.getBlockState(pos);
		if (state != null) {
			if (state.getBlock() == VoidCraft.blocks.ritualBlock) {
				if (!world.isRemote) VadeMecumRitualHandler.invokeRitual(player, world, pos);
				return EnumActionResult.SUCCESS;
			} else if (state.getBlock() == VoidCraft.blocks.voidicAlchemyTable) {
				if (!world.isRemote) {
					TileEntity te = world.getTileEntity(pos);
					if (te instanceof TileEntityVoidicAlchemy) {
						TileEntityVoidicAlchemy tile = (TileEntityVoidicAlchemy) te;
						tile.setOwner(player);
					}
				}
				return EnumActionResult.SUCCESS;
			}
		}
		return super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		dorightClick(world, player, stack);
		return super.onItemRightClick(world, player, hand);
	}

	// @SuppressWarnings("unused")
	private boolean dorightClick(World world, EntityPlayer player, ItemStack stack) {
		IVadeMecumItemCapability cap = stack.getCapability(CapabilityList.VADEMECUMITEM, null);
		if (cap == null) return false;
		if (player.isSneaking() && VoidCraft.isDevBuild) {
			cap.toggleBookState();
		} else {
			if (cap.getBookState() && VoidCraft.isDevBuild) {
				VadeMecumWordsOfPower.invoke(world, player);
			} else {
				if (world.isRemote) openBook(player, world, player.getPosition());
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	private void openBook(EntityPlayer player, World world, BlockPos pos) {
		// player.openGui(voidCraft.instance, GuiHandler.getTypeID(GuiHandler.Type.VadeMecum), world, pos.getX(), pos.getY(), pos.getZ());
		net.minecraft.client.Minecraft.getMinecraft().displayGuiScreen(new Tamaized.Voidcraft.GUI.client.VadeMecumGUI(player));
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (world.isRemote && entity instanceof EntityLivingBase && isSelected && !net.minecraft.client.Minecraft.getMinecraft().isGamePaused() && world.rand.nextInt(20) == 0) {
			EntityLivingBase living = (EntityLivingBase) entity;
			IVadeMecumCapability playerCap = living.getCapability(CapabilityList.VADEMECUM, null);
			IVadeMecumItemCapability itemCap = stack.getCapability(CapabilityList.VADEMECUMITEM, null);
			if (itemCap != null && itemCap.getBookState()) {
				/*
				 * if (flag) { if (voidCraft.config.getRenderFirstPersonParticles()) { double pitch180 = (180 - (living.rotationPitch + 90)); double pitch90 = ((living.rotationPitch)); double pitch1802 = ((living.rotationPitch + 90)); double pitch = (1 + Math.cos(Math.toRadians(living.rotationPitch + 90))) / 2; double yaw = Math.toRadians(living.rotationYaw - 90); double range = ((pitch1802 < 90 ? pitch1802 : 180 - pitch1802) / 90) * 2.0D; double xOffset = range * -Math.cos(yaw); double yOffset = pitch * 2.85D; double zOffset = range * -Math.sin(yaw); double yaw2 = Math.toRadians(living.rotationYaw); double range2 = 0.25D; double xOffset2 = range2 * -Math.cos(yaw2); double zOffset2 = range2 * -Math.sin(yaw2); world.spawnParticle(EnumParticleTypes.PORTAL, living.posX + xOffset, living.posY + yOffset, living.posZ + zOffset, -xOffset + xOffset2, 1.15D - yOffset, -zOffset + zOffset2); } } else {
				 */
				if (VoidCraft.config.getRenderThirdPersonParticles()) {
					particles(world, living);
					// world.spawnParticle(EnumParticleTypes.PORTAL, living.posX + xOffset, living.posY + yOffset, living.posZ + zOffset, 0, 0, 0);
				}
				// }
			}
		}
	}

	@SideOnly(Side.CLIENT)
	private void particles(World world, EntityLivingBase living) {
		double yaw = Math.toRadians(living.renderYawOffset - 50);
		double range = 0.63D;
		double sneakRange = 0.43D;
		double xOffset = living.isSneaking() ? (sneakRange * -Math.cos(yaw)) : (range * -Math.cos(yaw));
		double yOffset = living.isSneaking() ? -0.5D : 0.0D;
		double zOffset = living.isSneaking() ? (sneakRange * -Math.sin(yaw)) : (range * -Math.sin(yaw));
		net.minecraft.client.Minecraft.getMinecraft().effectRenderer.addEffect(new Tamaized.TamModized.particles.FX.ParticleFluff(world, new Vec3d(living.posX + xOffset, living.posY + yOffset + 0.785D, living.posZ + zOffset), new Vec3d(0, 0, 0), 20 * 2, 0.05F, world.rand.nextFloat() * 0.9F + 0.1F, 0x7700FFFF));
	}

}
