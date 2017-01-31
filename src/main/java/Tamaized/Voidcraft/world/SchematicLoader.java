package Tamaized.Voidcraft.world;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SchematicLoader {

	public Schematic get(String schemname) {
		try {
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("assets/voidcraft/Schematics/" + schemname);
			NBTTagCompound nbtdata = CompressedStreamTools.readCompressed(is);

			short width = nbtdata.getShort("Width");
			short height = nbtdata.getShort("Height");
			short length = nbtdata.getShort("Length");

			byte[] addBlocks = nbtdata.getByteArray("AddBlocks");
			byte[] localBlocks = nbtdata.getByteArray("Blocks");
			byte[] data = nbtdata.getByteArray("Data");

			NBTTagCompound ctMapping = nbtdata.getCompoundTag("SchematicaMapping");

			boolean extra = false;
			byte extraBlocks[] = null;
			byte extraBlocksNibble[] = null;
			if (nbtdata.hasKey("AddBlocks")) {
				extra = true;
				extraBlocksNibble = nbtdata.getByteArray("AddBlocks");
				extraBlocks = new byte[extraBlocksNibble.length * 2];
				for (int i = 0; i < extraBlocksNibble.length; i++) {
					extraBlocks[i * 2 + 0] = (byte) ((extraBlocksNibble[i] >> 4) & 0xF);
					extraBlocks[i * 2 + 1] = (byte) (extraBlocksNibble[i] & 0xF);
				}
			}

			int[] blocks = new int[localBlocks.length];

			for (int i = 0; i < blocks.length; i++) {
				blocks[i] = (localBlocks[i] & 0xFF) | (extra ? ((extraBlocks[i] & 0xFF) << 8) : 0);
			}

			NBTTagList tileentities = nbtdata.getTagList("TileEntities", 10);
			is.close();

			Map<Short, Block> mappings = new HashMap<Short, Block>();

			if (ctMapping != null) {
				for (String key : ctMapping.getKeySet()) {
					Block b = Block.getBlockFromName(key);
					if (b == null) continue;
					mappings.put(ctMapping.getShort(key), b);
				}
			}

			return new Schematic(mappings, tileentities, width, height, length, addBlocks, blocks, data);
		} catch (Exception e) {
			return null;
		}
	}

	public static void buildSchematic(String schematicName, SchematicLoader loader, World world, BlockPos pos) {
		if (world == null) return;
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		Schematic spring = loader.get(schematicName);
		int i = 0;
		for (int cy = 0; cy < spring.height; cy++) {
			for (int cz = 0; cz < spring.length; cz++) {
				for (int cx = 0; cx < spring.width; cx++) {
					short id = (short) spring.blocks[i];
					int meta = spring.data[i];
					Block b = spring.getBlock(id);
					if (id != 0 && b != null) {
						// System.out.println(id+" : "+b+" : "+meta+" : "+(cx + x + 1)+":"+(cy + y)+":"+(cz + z + 1));
						world.setBlockState(new BlockPos(cx + x + 1, cy + y, cz + z + 1), b.getStateFromMeta(meta), 2);
					} else {
						world.setBlockToAir(new BlockPos(cx + x + 1, cy + y, cz + z + 1));
					}
					i++;
				}
			}
		}
	}

	public class Schematic {

		private final Map<Short, Block> blockMap = new HashMap<Short, Block>();
		public final NBTTagList tileentities;
		public final short width;
		public final short height;
		public final short length;
		public final int[] blocks;
		public final byte[] data;

		public Schematic(Map<Short, Block> mappings, NBTTagList tileentities, short width, short height, short length, byte[] addBlocks, int[] blocks, byte[] data) {
			blockMap.putAll(mappings);
			this.tileentities = tileentities;
			this.width = width;
			this.height = height;
			this.length = length;
			this.blocks = blocks;
			this.data = data;
		}

		public Block getBlock(short id) {
			return blockMap.get(id);
		}

	}

}
