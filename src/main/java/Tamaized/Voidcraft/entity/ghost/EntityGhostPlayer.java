package Tamaized.Voidcraft.entity.ghost;

import Tamaized.Voidcraft.handlers.SkinHandler.PlayerNameAlias;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityGhostPlayer extends EntityGhostPlayerBase {

	public EntityGhostPlayer(World par1World) {
		super(par1World);
	}

	protected EntityGhostPlayer(World world, PlayerNameAlias alias, boolean interactable) {
		super(world, alias, interactable);
	}

	protected EntityGhostPlayer(World world, PlayerNameAlias alias, boolean interactable, Entity target, int length) {
		super(world, alias, interactable, target, length);
	}

}