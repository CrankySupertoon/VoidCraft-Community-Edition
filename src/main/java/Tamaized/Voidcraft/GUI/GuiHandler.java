package Tamaized.Voidcraft.GUI;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.GUI.client.HeimdallGUI;
import Tamaized.Voidcraft.GUI.client.RealityStabilizerGUI;
import Tamaized.Voidcraft.GUI.client.RealityTeleporterBlockGUI;
import Tamaized.Voidcraft.GUI.client.RealityTeleporterGUI;
import Tamaized.Voidcraft.GUI.client.StarForgeGUI;
import Tamaized.Voidcraft.GUI.client.VoidBoxGUI;
import Tamaized.Voidcraft.GUI.client.VoidicAlchemyGUI;
import Tamaized.Voidcraft.GUI.client.VoidicChargerGUI;
import Tamaized.Voidcraft.GUI.client.VoidicPowerGenGUI;
import Tamaized.Voidcraft.GUI.client.voidInfuserGUI;
import Tamaized.Voidcraft.GUI.client.voidMaceratorGUI;
import Tamaized.Voidcraft.GUI.server.HeimdallContainer;
import Tamaized.Voidcraft.GUI.server.RealityStabilizerContainer;
import Tamaized.Voidcraft.GUI.server.RealityTeleporterBlockContainer;
import Tamaized.Voidcraft.GUI.server.RealityTeleporterContainer;
import Tamaized.Voidcraft.GUI.server.StarForgeContainer;
import Tamaized.Voidcraft.GUI.server.VoidBoxContainer;
import Tamaized.Voidcraft.GUI.server.VoidInfuserContainer;
import Tamaized.Voidcraft.GUI.server.VoidMaceratorContainer;
import Tamaized.Voidcraft.GUI.server.VoidicAlchemyContainer;
import Tamaized.Voidcraft.GUI.server.VoidicChargerContainer;
import Tamaized.Voidcraft.GUI.server.VoidicPowerGenContainer;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityStarForge;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;
import Tamaized.Voidcraft.machina.tileentity.TileEntityRealityStabilizer;
import Tamaized.Voidcraft.machina.tileentity.TileEntityRealityTeleporter;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidInfuser;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicAlchemy;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicCharger;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicPowerGen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public static enum Type {
		NULL, Macerator, MusicBox, Infuser, Heimdall, VoidicGenerator, VoidicCharger, RealityStabilizer, RealityTeleporterBlock, VoidicAlchemy, RealityTeleporter, StarForge// , VadeMecum
	}

	public static int getTypeID(Type type) {
		return type.ordinal();
	}

	public static Type getTypeFromID(int id) {
		return id > Type.values().length ? Type.NULL : Type.values()[id];
	}

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		ItemStack main = player.getHeldItemMainhand();
		ItemStack off = player.getHeldItemOffhand();
		switch (getTypeFromID(id)) {
			// case VadeMecum:
			// return new VadeMecumContainer(player);
			case Macerator:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidMacerator) return new VoidMaceratorContainer(player.inventory, (TileEntityVoidMacerator) tileEntity);
			case MusicBox:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidBox) return new VoidBoxContainer(player.inventory, (TileEntityVoidBox) tileEntity);
			case Infuser:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidInfuser) return new VoidInfuserContainer(player.inventory, (TileEntityVoidInfuser) tileEntity);
			case Heimdall:
				if (tileEntity != null && tileEntity instanceof TileEntityHeimdall) return new HeimdallContainer(player.inventory, (TileEntityHeimdall) tileEntity);
			case VoidicGenerator:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidicPowerGen) return new VoidicPowerGenContainer(player.inventory, (TileEntityVoidicPowerGen) tileEntity);
			case VoidicCharger:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidicCharger) return new VoidicChargerContainer(player.inventory, (TileEntityVoidicCharger) tileEntity);
			case RealityStabilizer:
				if (tileEntity != null && tileEntity instanceof TileEntityRealityStabilizer) return new RealityStabilizerContainer(player.inventory, (TileEntityRealityStabilizer) tileEntity);
			case RealityTeleporterBlock:
				if (tileEntity != null && tileEntity instanceof TileEntityRealityTeleporter) return new RealityTeleporterBlockContainer(player.inventory, (TileEntityRealityTeleporter) tileEntity);
			case VoidicAlchemy:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidicAlchemy) return new VoidicAlchemyContainer(player.inventory, (TileEntityVoidicAlchemy) tileEntity);
			case RealityTeleporter:
				if (!main.isEmpty() && main.getItem() == VoidCraft.items.realityTeleporter) return new RealityTeleporterContainer(player.inventory, main);
				else if (!off.isEmpty() && off.getItem() == VoidCraft.items.realityTeleporter) return new RealityTeleporterContainer(player.inventory, off);
			case StarForge:
				if (tileEntity != null && tileEntity instanceof TileEntityStarForge) return new StarForgeContainer(player.inventory, (TileEntityStarForge) tileEntity);
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		ItemStack main = player.getHeldItemMainhand();
		ItemStack off = player.getHeldItemOffhand();
		switch (getTypeFromID(id)) {
			// case VadeMecum:
			// return new VadeMecumGUI(player);
			case Macerator:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidMacerator) return new voidMaceratorGUI(player.inventory, (TileEntityVoidMacerator) tileEntity);
			case MusicBox:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidBox) return new VoidBoxGUI(player.inventory, (TileEntityVoidBox) tileEntity);
			case Infuser:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidInfuser) return new voidInfuserGUI(player.inventory, (TileEntityVoidInfuser) tileEntity);
			case Heimdall:
				if (tileEntity != null && tileEntity instanceof TileEntityHeimdall) return new HeimdallGUI(player.inventory, (TileEntityHeimdall) tileEntity);
			case VoidicGenerator:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidicPowerGen) return new VoidicPowerGenGUI(player.inventory, (TileEntityVoidicPowerGen) tileEntity);
			case VoidicCharger:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidicCharger) return new VoidicChargerGUI(player.inventory, (TileEntityVoidicCharger) tileEntity);
			case RealityStabilizer:
				if (tileEntity != null && tileEntity instanceof TileEntityRealityStabilizer) return new RealityStabilizerGUI(player.inventory, (TileEntityRealityStabilizer) tileEntity);
			case RealityTeleporterBlock:
				if (tileEntity != null && tileEntity instanceof TileEntityRealityTeleporter) return new RealityTeleporterBlockGUI(player.inventory, (TileEntityRealityTeleporter) tileEntity);
			case VoidicAlchemy:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidicAlchemy) return new VoidicAlchemyGUI(player.inventory, (TileEntityVoidicAlchemy) tileEntity);
			case RealityTeleporter:
				if (!main.isEmpty() && main.getItem() == VoidCraft.items.realityTeleporter) return new RealityTeleporterGUI(player.inventory, main);
				else if (!off.isEmpty() && off.getItem() == VoidCraft.items.realityTeleporter) return new RealityTeleporterGUI(player.inventory, off);
			case StarForge:
				if (tileEntity != null && tileEntity instanceof TileEntityStarForge) return new StarForgeGUI(player.inventory, (TileEntityStarForge) tileEntity);
			default:
				return null;
		}
	}
}