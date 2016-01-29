package Tamaized.Voidcraft.blocks;

import java.util.Random;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class BasicVoidBlockFire extends BlockFire implements IBasicVoidBlock{

	private final String name;

	protected BasicVoidBlockFire(String n) {
		name = n;
		setUnlocalizedName(name);
		GameRegistry.registerBlock(this, n);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	protected  abstract boolean canNeighborBurn(World par1World, BlockPos pos);
	 
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand){
		if (world.getGameRules().getBoolean("doFireTick")){
			boolean flag = world.getBlockState(pos.add(0, -1, 0)).getBlock().isFireSource(world, pos.add(0, -1, 0), EnumFacing.UP);
			
			if (!this.canPlaceBlockAt(world, pos)){
				world.setBlockToAir(pos);
			}

			if (!flag && world.isRaining() && this.canDie(world, pos)){
				world.setBlockToAir(pos);
			}else{
				IBlockState bState = world.getBlockState(pos);
				int l = bState.getBlock().getMetaFromState(bState);
				
				if (l < 15){
					world.setBlockState(pos, this.getStateFromMeta(l + rand.nextInt(3) / 2), 4);
				}

				world.scheduleUpdate(pos, this, this.tickRate(world) + rand.nextInt(10));

				if (!flag && !this.canNeighborBurn(world, pos)){
					if (!World.doesBlockHaveSolidTopSurface(world, pos.add(0, -1, 0)) || l > 3){
						if(!(world.getBlockState(pos.add(0, -1, 0)).getBlock() instanceof BlockVoidcrystal)) world.setBlockToAir(pos);
					}
				}else if (!flag && !this.canCatchFire(world, pos.add(0, -1, 0), EnumFacing.UP) && l == 15 && rand.nextInt(4) == 0){
					world.setBlockToAir(pos);
				}else{
					boolean flag1 = world.isBlockinHighHumidity(pos);
					byte b0 = 0;
					
					if (flag1){
						b0 = -50;
					}
					
					this.tryCatchFire(world, pos.add(1, 0, 0), 300 + b0, rand, l, EnumFacing.WEST);
					this.tryCatchFire(world, pos.add(-1, 0, 0), 300 + b0, rand, l, EnumFacing.EAST);
					this.tryCatchFire(world, pos.add(0, -1, 0), 250 + b0, rand, l, EnumFacing.UP);
					this.tryCatchFire(world, pos.add(0, 1, 0), 250 + b0, rand, l, EnumFacing.DOWN);
					this.tryCatchFire(world, pos.add(0, 0, -1), 300 + b0, rand, l, EnumFacing.SOUTH);
					this.tryCatchFire(world, pos.add(0, 0, 1), 300 + b0, rand, l, EnumFacing.NORTH);
					
					for (int i1 = pos.getX() - 1; i1 <= pos.getX() + 1; ++i1){
						for (int j1 = pos.getZ() - 1; j1 <= pos.getZ() + 1; ++j1){
							for (int k1 = pos.getY() - 1; k1 <= pos.getY() + 4; ++k1){
								if (i1 != pos.getX() || k1 != pos.getY() || j1 != pos.getZ()){
									int l1 = 100;
									
									if (k1 > pos.getY() + 1){
										l1 += (k1 - (pos.getY() + 1)) * 100;
									}
										
									int i2 = this.getChanceOfNeighborsEncouragingFire(world, new BlockPos(i1, k1, j1));
									
									if (i2 > 0){
										int j2 = (i2 + 40 + world.getDifficulty().getDifficultyId() * 7) / (l + 30);
										
										if (flag1){
											j2 /= 2;
										}

										if (
												j2 > 0 &&
												rand.nextInt(l1) <= j2 &&
												(!world.isRaining() || this.canDie(world, pos))){
											int k2 = l + rand.nextInt(5) / 4;
											
											if (k2 > 15){
												k2 = 15;
											}
											
											world.setBlockState(new BlockPos(i1, k1, j1), this.getStateFromMeta(k2), 3);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	private void tryCatchFire(World world, BlockPos pos, int chance, Random rand, int age, EnumFacing face){
		int j1 = world.getBlockState(pos).getBlock().getFlammability(world, pos, face);
		
		if (rand.nextInt(chance) < j1){
			boolean flag = world.getBlockState(pos).getBlock() == Blocks.tnt;
			
			if (rand.nextInt(age + 10) < 5 && !this.canDie(world, pos)){
				int k1 = age + rand.nextInt(5) / 4;
				
				if (k1 > 15){
					k1 = 15;
				}
					
				world.setBlockState(pos, this.getStateFromMeta(k1),  3);
			}else{
				world.setBlockToAir(pos);
			}	

			if (flag){
				Blocks.tnt.onBlockDestroyedByPlayer(world, pos, Blocks.tnt.getStateFromMeta(1));
			}
		}
	}
	 
	 /**
	  * Gets the highest chance of a neighbor block encouraging this block to catch fire
	  */
	private int getChanceOfNeighborsEncouragingFire(World world, BlockPos pos){
		byte b0 = 0;
		
		if (!world.isAirBlock(pos)){
			return 0;
		}else{
			int l = b0;
			l = this.getFlammability(world, pos.add(1, 0, 0), EnumFacing.WEST);
			l = this.getFlammability(world, pos.add(-1, 0, 0), EnumFacing.EAST);
			l = this.getFlammability(world, pos.add(0, -1, 0), EnumFacing.UP);
			l = this.getFlammability(world, pos.add(0, 1, 0), EnumFacing.DOWN);
			l = this.getFlammability(world, pos.add(0, 0, -1), EnumFacing.SOUTH);
			l = this.getFlammability(world, pos.add(0, 0, 1), EnumFacing.NORTH);
			return l;
		}
	}

}