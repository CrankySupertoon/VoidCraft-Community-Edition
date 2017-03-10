package Tamaized.Voidcraft.structures.voidCity;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureComponentTemplate;
import net.minecraft.world.gen.structure.StructureEndCityPieces;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class StructureVoidCityPieces {
	private static final PlacementSettings OVERWRITE = (new PlacementSettings()).setIgnoreEntities(true);
	private static final PlacementSettings INSERT = (new PlacementSettings()).setIgnoreEntities(true).setReplacedBlock(Blocks.AIR);
	private static final StructureVoidCityPieces.IGenerator HOUSE_TOWER_GENERATOR = new StructureVoidCityPieces.IGenerator() {
		public void init() {
		}

		public boolean generate(TemplateManager p_191086_1_, int p_191086_2_, StructureVoidCityPieces.CityTemplate p_191086_3_, BlockPos p_191086_4_, List<StructureComponent> p_191086_5_, Random p_191086_6_) {
			if (p_191086_2_ > 8) {
				return false;
			} else {
				Rotation rotation = p_191086_3_.getPlaceSettings().getRotation();
				StructureVoidCityPieces.CityTemplate structureendcitypieces$citytemplate = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, p_191086_3_, p_191086_4_, "base_floor", rotation, true));
				int i = p_191086_6_.nextInt(3);

				if (i == 0) {
					StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(-1, 4, -1), "base_roof", rotation, true));
				} else if (i == 1) {
					structureendcitypieces$citytemplate = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(-1, 0, -1), "second_floor_2", rotation, false));
					structureendcitypieces$citytemplate = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(-1, 8, -1), "second_roof", rotation, false));
					StructureVoidCityPieces.recursiveChildren(p_191086_1_, StructureVoidCityPieces.TOWER_GENERATOR, p_191086_2_ + 1, structureendcitypieces$citytemplate, (BlockPos) null, p_191086_5_, p_191086_6_);
				} else if (i == 2) {
					structureendcitypieces$citytemplate = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(-1, 0, -1), "second_floor_2", rotation, false));
					structureendcitypieces$citytemplate = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(-1, 4, -1), "third_floor_c", rotation, false));
					structureendcitypieces$citytemplate = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(-1, 8, -1), "third_roof", rotation, true));
					StructureVoidCityPieces.recursiveChildren(p_191086_1_, StructureVoidCityPieces.TOWER_GENERATOR, p_191086_2_ + 1, structureendcitypieces$citytemplate, (BlockPos) null, p_191086_5_, p_191086_6_);
				}

				return true;
			}
		}
	};
	private static final List<Tuple<Rotation, BlockPos>> TOWER_BRIDGES = Lists.<Tuple<Rotation, BlockPos>> newArrayList(new Tuple[] { new Tuple(Rotation.NONE, new BlockPos(1, -1, 0)), new Tuple(Rotation.CLOCKWISE_90, new BlockPos(6, -1, 1)), new Tuple(Rotation.COUNTERCLOCKWISE_90, new BlockPos(0, -1, 5)), new Tuple(Rotation.CLOCKWISE_180, new BlockPos(5, -1, 6)) });
	private static final StructureVoidCityPieces.IGenerator TOWER_GENERATOR = new StructureVoidCityPieces.IGenerator() {

		@Override
		public void init() {
		}

		@Override
		public boolean generate(TemplateManager p_191086_1_, int p_191086_2_, StructureVoidCityPieces.CityTemplate p_191086_3_, BlockPos p_191086_4_, List<StructureComponent> p_191086_5_, Random p_191086_6_) {
			Rotation rotation = p_191086_3_.getPlaceSettings().getRotation();
			StructureVoidCityPieces.CityTemplate lvt_8_1_ = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, p_191086_3_, new BlockPos(3 + p_191086_6_.nextInt(2), -3, 3 + p_191086_6_.nextInt(2)), "tower_base", rotation, true));
			lvt_8_1_ = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, lvt_8_1_, new BlockPos(0, 7, 0), "tower_piece", rotation, true));
			StructureVoidCityPieces.CityTemplate structureendcitypieces$citytemplate1 = p_191086_6_.nextInt(3) == 0 ? lvt_8_1_ : null;
			int i = 1 + p_191086_6_.nextInt(3);

			for (int j = 0; j < i; ++j) {
				lvt_8_1_ = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, lvt_8_1_, new BlockPos(0, 4, 0), "tower_piece", rotation, true));

				if (j < i - 1 && p_191086_6_.nextBoolean()) {
					structureendcitypieces$citytemplate1 = lvt_8_1_;
				}
			}

			if (structureendcitypieces$citytemplate1 != null) {
				for (Tuple<Rotation, BlockPos> tuple : StructureVoidCityPieces.TOWER_BRIDGES) {
					if (p_191086_6_.nextBoolean()) {
						StructureVoidCityPieces.CityTemplate structureendcitypieces$citytemplate2 = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, structureendcitypieces$citytemplate1, (BlockPos) tuple.getSecond(), "bridge_end", rotation.add((Rotation) tuple.getFirst()), true));
						StructureVoidCityPieces.recursiveChildren(p_191086_1_, StructureVoidCityPieces.TOWER_BRIDGE_GENERATOR, p_191086_2_ + 1, structureendcitypieces$citytemplate2, (BlockPos) null, p_191086_5_, p_191086_6_);
					}
				}

				StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, lvt_8_1_, new BlockPos(-1, 4, -1), "tower_top", rotation, true));
			} else {
				if (p_191086_2_ != 7) {
					return StructureVoidCityPieces.recursiveChildren(p_191086_1_, StructureVoidCityPieces.FAT_TOWER_GENERATOR, p_191086_2_ + 1, lvt_8_1_, (BlockPos) null, p_191086_5_, p_191086_6_);
				}

				StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, lvt_8_1_, new BlockPos(-1, 4, -1), "tower_top", rotation, true));
			}

			return true;
		}
	};
	private static final StructureVoidCityPieces.IGenerator TOWER_BRIDGE_GENERATOR = new StructureVoidCityPieces.IGenerator() {
		public boolean shipCreated;

		public void init() {
			this.shipCreated = false;
		}

		public boolean generate(TemplateManager p_191086_1_, int p_191086_2_, StructureVoidCityPieces.CityTemplate p_191086_3_, BlockPos p_191086_4_, List<StructureComponent> p_191086_5_, Random p_191086_6_) {
			Rotation rotation = p_191086_3_.getPlaceSettings().getRotation();
			int i = p_191086_6_.nextInt(4) + 1;
			StructureVoidCityPieces.CityTemplate structureendcitypieces$citytemplate = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, p_191086_3_, new BlockPos(0, 0, -4), "bridge_piece", rotation, true));
			structureendcitypieces$citytemplate.setComponentType(-1);
			int j = 0;

			for (int k = 0; k < i; ++k) {
				if (p_191086_6_.nextBoolean()) {
					structureendcitypieces$citytemplate = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(0, j, -4), "bridge_piece", rotation, true));
					j = 0;
				} else {
					if (p_191086_6_.nextBoolean()) {
						structureendcitypieces$citytemplate = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(0, j, -4), "bridge_steep_stairs", rotation, true));
					} else {
						structureendcitypieces$citytemplate = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(0, j, -8), "bridge_gentle_stairs", rotation, true));
					}

					j = 4;
				}
			}

			if (!this.shipCreated && p_191086_6_.nextInt(10 - p_191086_2_) == 0) {
				StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(-8 + p_191086_6_.nextInt(8), j, -70 + p_191086_6_.nextInt(10)), "ship", rotation, true));
				this.shipCreated = true;
			} else if (!StructureVoidCityPieces.recursiveChildren(p_191086_1_, StructureVoidCityPieces.HOUSE_TOWER_GENERATOR, p_191086_2_ + 1, structureendcitypieces$citytemplate, new BlockPos(-3, j + 1, -11), p_191086_5_, p_191086_6_)) {
				return false;
			}

			structureendcitypieces$citytemplate = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(4, j, 0), "bridge_end", rotation.add(Rotation.CLOCKWISE_180), true));
			structureendcitypieces$citytemplate.setComponentType(-1);
			return true;
		}
	};
	private static final List<Tuple<Rotation, BlockPos>> FAT_TOWER_BRIDGES = Lists.<Tuple<Rotation, BlockPos>> newArrayList(new Tuple[] { new Tuple(Rotation.NONE, new BlockPos(4, -1, 0)), new Tuple(Rotation.CLOCKWISE_90, new BlockPos(12, -1, 4)), new Tuple(Rotation.COUNTERCLOCKWISE_90, new BlockPos(0, -1, 8)), new Tuple(Rotation.CLOCKWISE_180, new BlockPos(8, -1, 12)) });
	private static final StructureVoidCityPieces.IGenerator FAT_TOWER_GENERATOR = new StructureVoidCityPieces.IGenerator() {
		public void init() {
		}

		public boolean generate(TemplateManager p_191086_1_, int p_191086_2_, StructureVoidCityPieces.CityTemplate p_191086_3_, BlockPos p_191086_4_, List<StructureComponent> p_191086_5_, Random p_191086_6_) {
			Rotation rotation = p_191086_3_.getPlaceSettings().getRotation();
			StructureVoidCityPieces.CityTemplate structureendcitypieces$citytemplate = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, p_191086_3_, new BlockPos(-3, 4, -3), "fat_tower_base", rotation, true));
			structureendcitypieces$citytemplate = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(0, 4, 0), "fat_tower_middle", rotation, true));

			for (int i = 0; i < 2 && p_191086_6_.nextInt(3) != 0; ++i) {
				structureendcitypieces$citytemplate = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(0, 8, 0), "fat_tower_middle", rotation, true));

				for (Tuple<Rotation, BlockPos> tuple : StructureVoidCityPieces.FAT_TOWER_BRIDGES) {
					if (p_191086_6_.nextBoolean()) {
						StructureVoidCityPieces.CityTemplate structureendcitypieces$citytemplate1 = StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, structureendcitypieces$citytemplate, (BlockPos) tuple.getSecond(), "bridge_end", rotation.add((Rotation) tuple.getFirst()), true));
						StructureVoidCityPieces.recursiveChildren(p_191086_1_, StructureVoidCityPieces.TOWER_BRIDGE_GENERATOR, p_191086_2_ + 1, structureendcitypieces$citytemplate1, (BlockPos) null, p_191086_5_, p_191086_6_);
					}
				}
			}

			StructureVoidCityPieces.addHelper(p_191086_5_, StructureVoidCityPieces.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(-2, 8, -2), "fat_tower_top", rotation, true));
			return true;
		}
	};

	public static void registerPieces() {
		MapGenStructureIO.registerStructureComponent(StructureVoidCityPieces.CityTemplate.class, VoidCraft.modid + "VCP");
	}

	private static StructureVoidCityPieces.CityTemplate addPiece(TemplateManager p_191090_0_, StructureVoidCityPieces.CityTemplate p_191090_1_, BlockPos p_191090_2_, String p_191090_3_, Rotation p_191090_4_, boolean p_191090_5_) {
		StructureVoidCityPieces.CityTemplate structureendcitypieces$citytemplate = new StructureVoidCityPieces.CityTemplate(p_191090_0_, p_191090_3_, p_191090_1_.getTemplatePosition(), p_191090_4_, p_191090_5_);
		BlockPos blockpos = p_191090_1_.getTemplate().calculateConnectedPos(p_191090_1_.getPlaceSettings(), p_191090_2_, structureendcitypieces$citytemplate.getPlaceSettings(), BlockPos.ORIGIN);
		structureendcitypieces$citytemplate.offset(blockpos.getX(), blockpos.getY(), blockpos.getZ());
		return structureendcitypieces$citytemplate;
	}

	public static void startHouseTower(TemplateManager p_191087_0_, BlockPos p_191087_1_, Rotation p_191087_2_, List<StructureComponent> p_191087_3_, Random p_191087_4_) {
		FAT_TOWER_GENERATOR.init();
		HOUSE_TOWER_GENERATOR.init();
		TOWER_BRIDGE_GENERATOR.init();
		TOWER_GENERATOR.init();
		StructureVoidCityPieces.CityTemplate structureendcitypieces$citytemplate = addHelper(p_191087_3_, new StructureVoidCityPieces.CityTemplate(p_191087_0_, "base_floor", p_191087_1_, p_191087_2_, true));
		structureendcitypieces$citytemplate = addHelper(p_191087_3_, addPiece(p_191087_0_, structureendcitypieces$citytemplate, new BlockPos(-1, 0, -1), "second_floor", p_191087_2_, false));
		structureendcitypieces$citytemplate = addHelper(p_191087_3_, addPiece(p_191087_0_, structureendcitypieces$citytemplate, new BlockPos(-1, 4, -1), "third_floor", p_191087_2_, false));
		structureendcitypieces$citytemplate = addHelper(p_191087_3_, addPiece(p_191087_0_, structureendcitypieces$citytemplate, new BlockPos(-1, 8, -1), "third_roof", p_191087_2_, true));
		recursiveChildren(p_191087_0_, TOWER_GENERATOR, 1, structureendcitypieces$citytemplate, (BlockPos) null, p_191087_3_, p_191087_4_);
	}

	private static StructureVoidCityPieces.CityTemplate addHelper(List<StructureComponent> p_189935_0_, StructureVoidCityPieces.CityTemplate p_189935_1_) {
		p_189935_0_.add(p_189935_1_);
		return p_189935_1_;
	}

	private static boolean recursiveChildren(TemplateManager p_191088_0_, StructureVoidCityPieces.IGenerator p_191088_1_, int p_191088_2_, StructureVoidCityPieces.CityTemplate p_191088_3_, BlockPos p_191088_4_, List<StructureComponent> p_191088_5_, Random p_191088_6_) {
		if (p_191088_2_ > 8) {
			return false;
		} else {
			List<StructureComponent> list = Lists.<StructureComponent> newArrayList();

			if (p_191088_1_.generate(p_191088_0_, p_191088_2_, p_191088_3_, p_191088_4_, list, p_191088_6_)) {
				boolean flag = false;
				int i = p_191088_6_.nextInt();

				for (StructureComponent structurecomponent : list) {
					if(structurecomponent instanceof CityTemplate) ((CityTemplate) structurecomponent).setComponentType(i);
					StructureComponent structurecomponent1 = StructureComponent.findIntersecting(p_191088_5_, structurecomponent.getBoundingBox());

					if (structurecomponent1 != null && structurecomponent1.getComponentType() != p_191088_3_.getComponentType()) {
						flag = true;
						break;
					}
				}

				if (!flag) {
					p_191088_5_.addAll(list);
					return true;
				}
			}

			return false;
		}
	}

	public static class CityTemplate extends StructureComponentTemplate {
		private String pieceName;
		private Rotation rotation;
		private boolean overwrite;
		
		public PlacementSettings getPlaceSettings() {
			return placeSettings;
		}
		
		public BlockPos getTemplatePosition() {
			return templatePosition;
		}

		public Template getTemplate() {
			return template;
		}

		public void setComponentType(int i) {
			componentType = i;
		}

		public CityTemplate() {
		}

		public CityTemplate(TemplateManager p_i47214_1_, String p_i47214_2_, BlockPos p_i47214_3_, Rotation p_i47214_4_, boolean p_i47214_5_) {
			super(0);
			this.pieceName = p_i47214_2_;
			this.templatePosition = p_i47214_3_;
			this.rotation = p_i47214_4_;
			this.overwrite = p_i47214_5_;
			this.loadTemplate(p_i47214_3_);
		}

		private void loadTemplate(BlockPos p_186180_1_)
        {
            Template template = StructureEndCityPieces.field_186201_a.getTemplate((MinecraftServer)null, new ResourceLocation(VoidCraft.modid, "voidcity/" + this.pieceName.toLowerCase()));
            PlacementSettings placementsettings;

            if (this.overwrite)
            {
                placementsettings = StructureVoidCityPieces.OVERWRITE.copy().setRotation(this.rotation);
            }
            else
            {
                placementsettings = StructureVoidCityPieces.INSERT.copy().setRotation(this.rotation);
            }

            this.setup(template, p_186180_1_, placementsettings);
		}

		/**
		 * (abstract) Helper method to write subclass data to NBT
		 */
		protected void writeStructureToNBT(NBTTagCompound tagCompound) {
			super.writeStructureToNBT(tagCompound);
			tagCompound.setString("Template", this.pieceName);
			tagCompound.setString("Rot", this.rotation.name());
			tagCompound.setBoolean("OW", this.overwrite);
		}

		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		protected void readStructureFromNBT(NBTTagCompound tagCompound) {
			super.readStructureFromNBT(tagCompound);
			this.pieceName = tagCompound.getString("Template");
			this.rotation = Rotation.valueOf(tagCompound.getString("Rot"));
			this.overwrite = tagCompound.getBoolean("OW");
			this.loadTemplate(templatePosition);
		}

		protected void handleDataMarker(String p_186175_1_, BlockPos p_186175_2_, World p_186175_3_, Random p_186175_4_, StructureBoundingBox p_186175_5_) {
			if (p_186175_1_.startsWith("Chest")) {
				BlockPos blockpos = p_186175_2_.down();

				if (p_186175_5_.isVecInside(blockpos)) {
					TileEntity tileentity = p_186175_3_.getTileEntity(blockpos);

					if (tileentity instanceof TileEntityChest) {
						((TileEntityChest) tileentity).setLootTable(VoidCraft.lootTables.chest_voidCity, p_186175_4_.nextLong());
					}
				}
			} else if (p_186175_1_.startsWith("Sentry")) {
				//EntityShulker entityshulker = new EntityShulker(p_186175_3_);
				//entityshulker.setPosition((double) p_186175_2_.getX() + 0.5D, (double) p_186175_2_.getY() + 0.5D, (double) p_186175_2_.getZ() + 0.5D);
				//entityshulker.setAttachmentPos(p_186175_2_);
				//p_186175_3_.spawnEntity(entityshulker);
			} else if (p_186175_1_.startsWith("Elytra")) {
				EntityItemFrame entityitemframe = new EntityItemFrame(p_186175_3_, p_186175_2_, this.rotation.rotate(EnumFacing.SOUTH));
				entityitemframe.setDisplayedItem(new ItemStack(VoidCraft.items.astralEssence));
				p_186175_3_.spawnEntity(entityitemframe);
			}
		}
	}

	interface IGenerator {

		void init();

		boolean generate(TemplateManager p_191086_1_, int p_191086_2_, StructureVoidCityPieces.CityTemplate p_191086_3_, BlockPos p_191086_4_, List<StructureComponent> p_191086_5_, Random p_191086_6_);

	}
}