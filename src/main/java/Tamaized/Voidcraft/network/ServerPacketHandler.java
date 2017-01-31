package Tamaized.Voidcraft.network;

import java.io.IOException;
import java.util.ArrayList;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.armor.ArmorCustomElytra;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityStarForge;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.starforge.IStarForgeCapability;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.handlers.CustomElytraHandler;
import Tamaized.Voidcraft.handlers.VadeMecumPacketHandler;
import Tamaized.Voidcraft.helper.GUIListElement;
import Tamaized.Voidcraft.items.HookShot;
import Tamaized.Voidcraft.items.RealityTeleporter;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;
import Tamaized.Voidcraft.starforge.StarForgeEffectEntry;
import Tamaized.Voidcraft.starforge.StarForgeToolEntry;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ServerPacketHandler {

	public static enum PacketType {
		VOIDBOX_PLAY, VOIDBOX_STOP, VOIDBOX_LOOP, VOIDBOX_AUTO, HOOKSHOT_STOP, VADEMECUM, VADEMECUM_LASTENTRY, CUSTOM_ELYTRA, LINK_CLEAR, STARFORGE_CRAFT
	}

	public static int getPacketTypeID(PacketType type) {
		return type.ordinal();
	}

	public static PacketType getPacketTypeFromID(int id) {
		return PacketType.values()[id];
	}

	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent event) {
		EntityPlayerMP player = ((NetHandlerPlayServer) event.getHandler()).playerEntity;
		player.getServer().addScheduledTask(new Runnable() {
			public void run() {
				processPacketOnServer(event.getPacket().payload(), Side.SERVER, player);
			}
		});
	}

	public static void processPacketOnServer(ByteBuf parBB, Side parSide, EntityPlayerMP player) {
		if (parSide == Side.SERVER) {
			World world = player.world;
			ByteBufInputStream bbis = new ByteBufInputStream(parBB);
			int pktType;
			try {
				pktType = bbis.readInt();
				switch (getPacketTypeFromID(pktType)) {
					case STARFORGE_CRAFT: {
						TileEntity te = world.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
						if (te instanceof TileEntityStarForge) {
							TileEntityStarForge tile = (TileEntityStarForge) te;
							int index = bbis.readInt();
							ArrayList<GUIListElement> list = tile.buildPossibleEffectList();
							if (index >= 0 && index < list.size()) {
								GUIListElement element = list.get(index);
								if (element instanceof StarForgeToolEntry) {
									StarForgeToolEntry entry = (StarForgeToolEntry) element;
									if (tile.getStackInSlot(tile.SLOT_OUTPUT).isEmpty() && tile.getStackInSlot(tile.SLOT_INPUT_COSMICMATERIAL).getCount() >= 4 && tile.getStackInSlot(tile.SLOT_INPUT_QUORIFRAGMENT).getCount() >= 1) {
										tile.getStackInSlot(tile.SLOT_INPUT_COSMICMATERIAL).shrink(4);
										tile.getStackInSlot(tile.SLOT_INPUT_QUORIFRAGMENT).shrink(1);
										tile.setInventorySlotContents(tile.SLOT_OUTPUT, entry.getTool());
									}
								} else if (element instanceof StarForgeEffectEntry) {
									StarForgeEffectEntry entry = (StarForgeEffectEntry) element;
									if (!tile.getStackInSlot(tile.SLOT_INPUT_TOOL).isEmpty() && tile.getStackInSlot(tile.SLOT_OUTPUT).isEmpty()) {
										boolean flag = true;
										for (ItemStack checkStack : entry.getRecipe().getInputs()) {
											int slot = checkStack.getItem() == Item.getItemFromBlock(VoidCraft.blocks.cosmicMaterial) ? tile.SLOT_INPUT_COSMICMATERIAL : checkStack.getItem() == VoidCraft.items.voidicDragonScale ? tile.SLOT_INPUT_DRAGONSCALE : checkStack.getItem() == VoidCraft.items.quoriFragment ? tile.SLOT_INPUT_QUORIFRAGMENT : checkStack.getItem() == VoidCraft.items.astralEssence ? tile.SLOT_INPUT_ASTRALESSENCE : tile.SLOT_INPUT_VOIDICPHLOG;
											if (tile.getStackInSlot(slot).getCount() >= checkStack.getCount()) break;
											flag = false;
										}
										if (flag) {
											ItemStack tool = tile.getStackInSlot(tile.SLOT_INPUT_TOOL).copy();
											IStarForgeCapability cap = tool.getCapability(CapabilityList.STARFORGE, null);
											if (cap != null && cap.getEffect(entry.getRecipe().getEffect().getTier()) == null) {
												for (ItemStack checkStack : entry.getRecipe().getInputs()) {
													int slot = checkStack.getItem() == Item.getItemFromBlock(VoidCraft.blocks.cosmicMaterial) ? tile.SLOT_INPUT_COSMICMATERIAL : checkStack.getItem() == VoidCraft.items.voidicDragonScale ? tile.SLOT_INPUT_DRAGONSCALE : checkStack.getItem() == VoidCraft.items.quoriFragment ? tile.SLOT_INPUT_QUORIFRAGMENT : checkStack.getItem() == VoidCraft.items.astralEssence ? tile.SLOT_INPUT_ASTRALESSENCE : tile.SLOT_INPUT_VOIDICPHLOG;
													tile.getStackInSlot(slot).shrink(checkStack.getCount());
												}
												cap.addEffect(entry.getRecipe().getEffect());
											}
											tile.setInventorySlotContents(tile.SLOT_OUTPUT, tool);
											tile.setInventorySlotContents(tile.SLOT_INPUT_TOOL, ItemStack.EMPTY);
										}
									}
								}
							}
						}
					}
						break;
					case LINK_CLEAR: {
						int slot = bbis.readInt();
						ItemStack stack = ItemStack.EMPTY;
						if (slot >= 0 && slot < player.inventory.mainInventory.size()) stack = player.inventory.mainInventory.get(slot);
						else if (slot == -1) stack = player.inventory.offHandInventory.get(0);
						if (!stack.isEmpty() && stack.getItem() == VoidCraft.items.realityTeleporter) {
							RealityTeleporter.clearLink(stack);
						}
					}
						break;
					case CUSTOM_ELYTRA: {
						if (!player.onGround && player.motionY < 0.0D && !CustomElytraHandler.isElytraFlying(player) && !player.isInWater()) {
							ItemStack itemstack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
							if (!itemstack.isEmpty() && itemstack.getItem() instanceof ArmorCustomElytra && ArmorCustomElytra.isBroken(itemstack)) {
								CustomElytraHandler.setFlying(player, true);
							}
						} else {
							CustomElytraHandler.setFlying(player, false);
						}
					}
						break;
					case VADEMECUM: {
						VadeMecumPacketHandler.DecodeRequestServer(bbis, player);
					}
						break;
					case VADEMECUM_LASTENTRY: {
						IVadeMecumCapability cap = player.getCapability(CapabilityList.VADEMECUM, null);
						if (cap != null) cap.setLastEntry(bbis.readUTF());
					}
						break;
					case VOIDBOX_PLAY: {
						TileEntityVoidBox voidBox = (TileEntityVoidBox) player.world.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
						if (voidBox == null) {
							bbis.close();
							return;
						}
						voidBox.PlayNextSound();
					}
						break;
					case VOIDBOX_STOP: {
						TileEntityVoidBox voidBox = (TileEntityVoidBox) player.world.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
						if (voidBox == null) {
							bbis.close();
							return;
						}
						voidBox.StopTheSoundAndDeposit();
					}
						break;
					case VOIDBOX_LOOP: {
						TileEntityVoidBox voidBox = (TileEntityVoidBox) player.world.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
						if (voidBox == null) {
							bbis.close();
							return;
						}
						voidBox.setLoopState();
					}
						break;
					case VOIDBOX_AUTO: {
						TileEntityVoidBox voidBox = (TileEntityVoidBox) player.world.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
						if (voidBox == null) {
							bbis.close();
							return;
						}
						voidBox.setAutoState();
					}
						break;
					case HOOKSHOT_STOP: {
						HookShot.handler.put(player, false);
					}
						break;
					default: {

					}
						break;
				}
			} catch (Exception e) {
				try {
					bbis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
	}

}
