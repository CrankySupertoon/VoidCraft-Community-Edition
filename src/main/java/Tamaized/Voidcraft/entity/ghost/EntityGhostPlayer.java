package Tamaized.Voidcraft.entity.ghost;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityGhostPlayer extends EntityGhostPlayerBase {

	public EntityGhostPlayer(World par1World) {
		super(par1World);
	}

	protected EntityGhostPlayer(World world, UUID id, boolean interactable) {
		super(world, id, interactable);
	}

	protected EntityGhostPlayer(World world, UUID id, boolean interactable, Entity target, int length) {
		super(world, id, interactable, target, length);
	}

}