package Tamaized.Voidcraft.entity.ghost;

import Tamaized.Voidcraft.handlers.SkinHandler.PlayerNameAlias;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityGhostBiped extends EntityGhostPlayerBase {

	public EntityGhostBiped(World world) {
		super(world);
	}

	protected EntityGhostBiped(World world, PlayerNameAlias alias, boolean interactable) {
		super(world, alias, interactable);
	}

	protected EntityGhostBiped(World world, PlayerNameAlias alias, boolean interactable, Entity target, int length) {
		super(world, alias, interactable, target, length);
	}

}