package Tamaized.Voidcraft.items;

import Tamaized.TamModized.items.TamItem;
import Tamaized.Voidcraft.entity.nonliving.EntityObsidianFlask;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ObsidianFlask extends TamItem {

	private final EntityObsidianFlask.Type type;
	
	public ObsidianFlask(EntityObsidianFlask.Type t, CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
		type = t;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack s, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack stack = playerIn.getHeldItem(hand);
		if (!worldIn.isRemote) {
			EntityObsidianFlask e = new EntityObsidianFlask(type, worldIn, playerIn);
			e.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
			worldIn.spawnEntity(e);
		}
		worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!playerIn.capabilities.isCreativeMode) {
			stack.stackSize -= (1);
		}
		return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

}