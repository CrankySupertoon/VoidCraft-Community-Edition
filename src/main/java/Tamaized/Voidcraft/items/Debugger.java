package Tamaized.Voidcraft.items;

import java.util.List;

import Tamaized.TamModized.items.TamItem;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.entity.companion.EntityCompanionFireElemental;
import Tamaized.Voidcraft.entity.nonliving.EntitySpellImplosion;
import Tamaized.Voidcraft.world.SchematicLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Debugger extends TamItem {

	public Debugger(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	SchematicLoader sut = new SchematicLoader();

	/**
	 * Called when a Block is right-clicked with this Item
	 */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			EntityCompanionFireElemental fireElemental = new EntityCompanionFireElemental(worldIn);
			fireElemental.setPositionAndUpdate(pos.getX(), pos.getY() + 1, pos.getZ());
			fireElemental.tame(playerIn);
			worldIn.spawnEntity(fireElemental);
			// worldIn.spawnEntityInWorld(new EntityLichInferno(worldIn, pos, 6));
			// EntityPig pig = new EntityPig(worldIn);
			// pig.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
			// worldIn.spawnEntity(pig);
			// EntityGhostPlayerBase entity = EntityGhostPlayerBase.newInstance(worldIn, PlayerNameAlias.Soaryn, false, playerIn, 20*30);
			// EntityHerobrineCreeper entity = new EntityHerobrineCreeper(worldIn);
			// entity.setPositionAndRotation(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 0, 0);
			// entity.rotationYawHead = entity.rotationYaw = entity.prevRotationYaw = entity.prevRotationYawHead = entity.prevRenderYawOffset = entity.renderYawOffset = 90;
			// EntityBossXia2 entity = new EntityBossXia2(worldIn, new Xia2BattleHandler());
			// entity.setPositionAndUpdate(playerIn.posX, playerIn.posY + 5, playerIn.posZ);
			// EntitySpellImplosion entity = new EntitySpellImplosion(worldIn);
			// entity.setPositionAndUpdate(pos.getX(), pos.getY() + 1, pos.getZ());
			// worldIn.spawnEntity(entity);
			// voidCraft.fluids.acidFluidBlock.place(worldIn, pos.up(), new FluidStack(voidCraft.fluids.acidFluid, Fluid.BUCKET_VOLUME), true);
		}
		return EnumActionResult.PASS;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (worldIn.isRemote) return super.onItemRightClick(worldIn, playerIn, hand);
		// EntityWitherbrine wither = new EntityWitherbrine(worldIn);
		// wither.setPositionAndUpdate(playerIn.posX, playerIn.posY, playerIn.posZ);
		// wither.ignite();
		// worldIn.spawnEntity(wither);
		// EntityDragonXia dragon = new EntityDragonXia(worldIn);
		// dragon.setPositionAndUpdate(playerIn.posX, playerIn.posY + 20, playerIn.posZ);
		// worldIn.spawnEntity(dragon);
		// EntityDolXia dol = new EntityDolXia(worldIn);
		// dol.setPositionAndUpdate(playerIn.posX, playerIn.posY + 20, playerIn.posZ);
		// worldIn.spawnEntity(dol);
		// EntityZolXia zol = new EntityZolXia(worldIn);
		// zol.setPositionAndUpdate(playerIn.posX, playerIn.posY + 20, playerIn.posZ);
		// worldIn.spawnEntity(zol);

		// IVoidicInfusionCapability cap = playerIn.getCapability(CapabilityList.VOIDICINFUSION, null);
		// if (cap != null) cap.setXiaDefeats(0);
		// if (worldIn.provider instanceof WorldProviderXia) ((WorldProviderXia) worldIn.provider).getXiaCastleHandler().start();
		// if (worldIn.provider instanceof WorldProviderXia) ((WorldProviderXia) worldIn.provider).getXiaCastleHandler().debug();

		// ItemStack newStack = new ItemStack(voidCraft.tools.starforgedPickaxe);
		// IStarForgeCapability cap = newStack.getCapability(CapabilityList.STARFORGE, null);
		// cap.addEffect(StarForgeEffectList.haste);
		// cap.addEffect(StarForgeEffectList.fortune);
		// cap.addEffect(StarForgeEffectList.threeByThree);
		// playerIn.inventory.addItemStackToInventory(newStack);

		// VoidCraft.reloadRitualList();
		// IVadeMecumCapability cap = playerIn.getCapability(CapabilityList.VADEMECUM, null);
		// if (cap != null) cap.clearCategories();

		// playerIn.clearActivePotions();
		// playerIn.addPotionEffect(new PotionEffect(voidCraft.potions.frostSheathe, 20 * 90));
		// Vec3d vec = playerIn.getLook(1.0f);
		// EntityHerobrineFireball entity = new EntityHerobrineFireball(worldIn, playerIn, vec.xCoord, vec.yCoord, vec.zCoord);
		// ProjectileDisintegration entity = new ProjectileDisintegration(worldIn, playerIn, playerIn.posX, playerIn.posY, playerIn.posZ);
		// EntityGhostPlayer entity = new EntityGhostPlayer(worldIn, PlayerNameAlias.Cpw11);
		// entity.setPositionAndUpdate(playerIn.getPosition().getX(), playerIn.getPosition().getX(), playerIn.getPosition().getX());
		// worldIn.spawnEntity(entity);
		// playerIn.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(playerIn.getMaxHealth()+1);
		// playerIn.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 20 * 20));
		// playerIn.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(playerIn.getMaxHealth()-20);
		return super.onItemRightClick(worldIn, playerIn, hand);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(TextFormatting.DARK_PURPLE + "Debug Tool");
	}

}
