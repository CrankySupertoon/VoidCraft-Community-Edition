package Tamaized.Voidcraft.entity.ghost;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityGhostBiped extends EntityGhostPlayerBase {

	public EntityGhostBiped(World world) {
		super(world);
	}

	protected EntityGhostBiped(World world, UUID id, boolean interactable) {
		super(world, id, interactable);
	}

	protected EntityGhostBiped(World world, UUID id, boolean interactable, Entity target, int length) {
		super(world, id, interactable, target, length);
	}

}