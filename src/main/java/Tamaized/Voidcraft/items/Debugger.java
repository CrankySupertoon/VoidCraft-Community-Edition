package Tamaized.Voidcraft.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.TamModized.items.TamItem;
import Tamaized.Voidcraft.projectiles.HerobrineFireball;
import Tamaized.Voidcraft.world.SchematicLoader;

public class Debugger extends TamItem {

	public Debugger(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	SchematicLoader sut = new SchematicLoader();

	/**
	 * Called when a Block is right-clicked with this Item
	 */
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		// VoidChain entityarrow = new VoidChain(worldIn, playerIn, playerIn, 1);
		// worldIn.spawnEntityInWorld(entityarrow);
		return EnumActionResult.PASS;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		//Vec3d vec = playerIn.getLook(1.0f);
		//HerobrineFireball fireball = new HerobrineFireball(worldIn, playerIn, vec.xCoord, vec.yCoord, vec.zCoord);
		//worldIn.spawnEntityInWorld(fireball);
		//playerIn.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(playerIn.getMaxHealth()+1);
		playerIn.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 20 * 20));
		//playerIn.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(playerIn.getMaxHealth()-20);
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(TextFormatting.DARK_PURPLE + "Debug Tool");
	}

}
