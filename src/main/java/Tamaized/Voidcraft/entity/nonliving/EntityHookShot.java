package Tamaized.Voidcraft.entity.nonliving;

public class EntityHookShot{/* extends EntityFishHook implements IProjectile, IEntityAdditionalSpawnData  {

	public EntityPlayer shootingEntity;
	private int lastItem;
	//public double[][] lineCoord = {{0, 0, 0}, {0, 0, 0}};

	private double speed = 0.3D;
	private float range = 0.4F;

	private boolean inGround;
	private int blockX = -1;
	private int blockY = -1;
	private int blockZ = -1;
	private Block inBlock;
	private int inBlockMeta;
	private int ticksInGround;
	private int ticksInAir;
	
	public EntityHookShot(World p_i1753_1_, EntityPlayer player){
		super(p_i1753_1_, player);
        this.setSize(0.5F, 0.5F);
    }
	
    public EntityHookShot(World world, EntityPlayer player, float p_i1756_3_){
        super(world, player);
        this.ignoreFrustumCheck = true;
        shootingEntity = player;
        lastItem = shootingEntity.inventory.currentItem;
        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(player.posX, player.posY + (double)player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, p_i1756_3_ * 1.5F, 1.0F);
        //lineCoord = new double[][]{{player.posX, player.posY + (double)player.getEyeHeight(), player.posZ}, {player.posX, player.posY + (double)player.getEyeHeight(), player.posZ}};
    }
    
    /**
     * Called by the server when constructing the spawn packet.
     * Data should be added to the provided stream.
     *
     * @param buffer The packet data stream
     *//*
    public void writeSpawnData(ByteBuf buffer){
    	if(shootingEntity == null){
    		this.setDead();
    		return;
    	}
    	buffer.writeInt(shootingEntity.getEntityId());
    	buffer.writeDouble(posX);
    	buffer.writeDouble(posY);
    	buffer.writeDouble(posZ);
    }

    /**
     * Called by the client when it receives a Entity spawn packet.
     * Data should be read out of the stream in the same way as it was written.
     *
     * @param data The packet data stream
     *//*
    public void readSpawnData(ByteBuf additionalData){
    	try{
    		Entity e = worldObj.getEntityByID(additionalData.readInt());
    		EntityPlayer player = e instanceof EntityPlayer ? (EntityPlayer) e : null;
    		this.setPosition(additionalData.readDouble(), additionalData.readDouble(), additionalData.readDouble());
    		this.ignoreFrustumCheck = true;
    		shootingEntity = player;
        	lastItem = shootingEntity == null ? null : shootingEntity.inventory.currentItem;
    	}catch(IndexOutOfBoundsException e){
    		//No Data was sent, just kill the entity
    		this.setDead();
    	}
    }
    
    public void setRangeAndSpeed(float r, double s){
    	range = r;
    	speed = s;
    }

	@Override
	protected void entityInit() {
	}

	/**
	 * Called to update the entity's position/logic.
	 *//*
	public void onUpdate() {/*
		ticksExisted++;
		
		if(shootingEntity == null){
			this.setDead();
			return;
		}
		
		if(!worldObj.isRemote){
			if(!HookShot.handler.containsKey(shootingEntity) || !HookShot.handler.get(shootingEntity)){
				this.setDead();
			}
			if(shootingEntity.inventory.currentItem != lastItem || (((ticksExisted % (20*3)) == 0) && !inGround) || (ticksExisted % (20*15)) == 0){
				HookShot.handler.put(shootingEntity, false);
				this.setDead();
			}
		}
		

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(this.motionY, (double) f) * 180.0D / Math.PI);
		}

		 Block block = this.worldObj.getBlockState(new BlockPos(this.blockX, this.blockY, this.blockZ)).getBlock();

	        if (block.getMaterial() != Material.air) //check if hit block
	        {
	        	block.setBlockBoundsBasedOnState(this.worldObj, new BlockPos(this.blockX, this.blockY, this.blockZ));
	            AxisAlignedBB axisalignedbb = block.getCollisionBoundingBox(this.worldObj, new BlockPos(this.blockX, this.blockY, this.blockZ), worldObj.getBlockState(new BlockPos(this.blockX, this.blockY, this.blockZ)));

	            if (axisalignedbb != null && axisalignedbb.isVecInside(new Vec3(this.posX, this.posY, this.posZ)))
	            {
	                this.inGround = true;
	            }
	        }

		if (this.inGround){ 
			IBlockState Bstate = this.worldObj.getBlockState(new BlockPos(this.blockX, this.blockY, this.blockZ));
        int j = Bstate.getBlock().getMetaFromState(Bstate);

			if (block == this.inBlock && j == this.inBlockMeta) {
				++this.ticksInGround;
				if (this.ticksInGround == 1200) {
					this.setDead();
				}
				
				//Do work here TODO
				if (shootingEntity != null) {
					int shootingEntityBlockX = MathHelper.floor_double(shootingEntity.posX);
					int shootingEntityBlockY = MathHelper.floor_double(shootingEntity.getEntityBoundingBox().minY)-1;
					int shootingEntityBlockZ = MathHelper.floor_double(shootingEntity.posZ);
			    
					if(shootingEntityBlockX-this.blockX > -3 && shootingEntityBlockX-this.blockX < 3){
			    		if(shootingEntityBlockY-this.blockY > -3 && shootingEntityBlockY-this.blockY < 3){
			    			if(shootingEntityBlockZ-this.blockZ > -3 && shootingEntityBlockZ-this.blockZ < 3){
			    				this.setDead();
			    				if(!worldObj.isRemote && shootingEntity instanceof EntityPlayer) HookShot.handler.put(((EntityPlayer)shootingEntity), false);
			    			}
			    		}
			    	}
			    	
			    	double preMathX = this.blockX > shootingEntityBlockX ? this.blockX-shootingEntityBlockX : this.blockX < shootingEntityBlockX ? shootingEntityBlockX-this.blockX : 0;
			    	double preMathY = this.blockY > shootingEntityBlockY ? this.blockY-shootingEntityBlockY : this.blockY < shootingEntityBlockY ? shootingEntityBlockY-this.blockY : 0;
			    	double preMathZ = this.blockZ > shootingEntityBlockZ ? this.blockZ-shootingEntityBlockZ : this.blockZ < shootingEntityBlockZ ? shootingEntityBlockZ-this.blockZ : 0;
			    	
			    	boolean gX = preMathZ > preMathX ? false : preMathY > preMathX ? false : true;
			    	boolean gY = preMathZ > preMathY ? false : preMathX > preMathY ? false : true;
			    	boolean gZ = preMathX > preMathZ ? false : preMathY > preMathZ ? false : true;
			    	
			    	double MathX = gX ? 1 : gY ? (preMathX/preMathY) : (preMathX/preMathZ);
			    	double MathY = gY ? 1 : gX ? (preMathY/preMathX) : (preMathY/preMathZ);
			    	double MathZ = gZ ? 1 : gY ? (preMathZ/preMathY) : (preMathZ/preMathX);
						
			    	shootingEntity.motionX = this.blockX > shootingEntityBlockX ? MathX : -MathX;
			    	shootingEntity.motionY = this.blockY > shootingEntityBlockY ? MathY : -MathY;
			    	shootingEntity.motionZ = this.blockZ > shootingEntityBlockZ ? MathZ : -MathZ;
			    	shootingEntity.velocityChanged = true;
				}
			} else {
				this.inGround = false;
				this.motionX *= (double) (this.rand.nextFloat() * 0.2F);
				this.motionY *= (double) (this.rand.nextFloat() * 0.2F);
				this.motionZ *= (double) (this.rand.nextFloat() * 0.2F);
				this.ticksInGround = 0;
				this.ticksInAir = 0;
			}
			
		} else { // Traveling
			++this.ticksInAir;
			Vec3 vec31 = new Vec3(this.posX, this.posY, this.posZ);
            Vec3 vec3 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec31, vec3, false, true, false);
            vec31 = new Vec3(this.posX, this.posY, this.posZ);
            vec3 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

			if (movingobjectposition != null){
                vec3 = new Vec3(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
			}

			float f1;
            float f2;
            float f4;

			if (movingobjectposition != null) {
				this.blockX = movingobjectposition.getBlockPos().getX();
				this.blockY = movingobjectposition.getBlockPos().getY();
				this.blockZ = movingobjectposition.getBlockPos().getZ();
                IBlockState bState = this.worldObj.getBlockState(new BlockPos(this.blockX, this.blockY, this.blockZ));
                this.inBlock = bState.getBlock();
				this.inBlockMeta = bState.getBlock().getMetaFromState(bState);
				this.motionX = (double) ((float) (movingobjectposition.hitVec.xCoord - this.posX));
				this.motionY = (double) ((float) (movingobjectposition.hitVec.yCoord - this.posY));
				this.motionZ = (double) ((float) (movingobjectposition.hitVec.zCoord - this.posZ));
				f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
				this.posX -= this.motionX / (double) f2 * 0.05000000074505806D;
				this.posY -= this.motionY / (double) f2 * 0.05000000074505806D;
				this.posZ -= this.motionZ / (double) f2 * 0.05000000074505806D;
				this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F)); // TODO
				this.inGround = true;
				if (this.inBlock.getMaterial() != Material.air) this.inBlock.onEntityCollidedWithBlock(this.worldObj, new BlockPos(this.blockX, this.blockY, this.blockZ), this);
			}
		

			this.posX += this.motionX * speed;
			this.posY += this.motionY * speed;
			this.posZ += this.motionZ * speed;
			f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

			for (this.rotationPitch = (float) (Math.atan2(this.motionY, (double) f2) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
				;
			}

			while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
				this.prevRotationPitch += 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
				this.prevRotationYaw -= 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
				this.prevRotationYaw += 360.0F;
			}

			this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
			this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
			float f3 = 0.99F;
			f1 = range;

			if (this.isInWater()) {
				for (int l = 0; l < 4; ++l) {
					f4 = 0.25F;
					this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * (double) f4, this.posY - this.motionY * (double) f4, this.posZ - this.motionZ * (double) f4, this.motionX, this.motionY, this.motionZ);
				}

				f3 = 0.8F;
			}

			if (this.isWet()) {
				this.extinguish();
			}

			this.motionX *= (double) f3;
			this.motionY *= (double) f3;
			this.motionZ *= (double) f3;
			this.motionY -= (double) f1;
			this.setPosition(this.posX, this.posY, this.posZ);
            this.doBlockCollisions();
		}
	*//*}
	
	/**
     * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
     * posY, posZ, yaw, pitch
     *//*
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_)
    {
        this.setPosition(p_70056_1_, p_70056_3_, p_70056_5_);
        this.setRotation(p_70056_7_, p_70056_8_);
    }

    /**
     * Sets the velocity to the args. Args: x, y, z
     *//*
    @SideOnly(Side.CLIENT)
    public void setVelocity(double p_70016_1_, double p_70016_3_, double p_70016_5_)
    {
        this.motionX = p_70016_1_;
        this.motionY = p_70016_3_;
        this.motionZ = p_70016_5_;
        
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(p_70016_1_, p_70016_5_) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(p_70016_3_, (double)f) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            this.ticksInGround = 0;
        }
    }
    
    /**
     * Called by a player entity when they collide with an entity
     *//*
    public void onCollideWithPlayer(EntityPlayer p_70100_1_)
    {
       
    }
    
    public double getDamage()
    {
        return 0;
    }

    /**
     * If returns false, the item will not inflict any damage against entities.
     *//*
    public boolean canAttackWithItem()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

	@Override
	public void readEntityFromNBT(NBTTagCompound p_70037_1_) {

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound p_70014_1_) {

	}

	/**
	 * Prevents crop trample
	 *//*
	protected boolean canTriggerWalking() {
		return false;
	}

	public void setThrowableHeading(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_)
    {
        float f2 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
        p_70186_1_ /= (double)f2;
        p_70186_3_ /= (double)f2;
        p_70186_5_ /= (double)f2;
        p_70186_1_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
        p_70186_3_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
        p_70186_5_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
        p_70186_1_ *= (double)p_70186_7_;
        p_70186_3_ *= (double)p_70186_7_;
        p_70186_5_ *= (double)p_70186_7_;
        this.motionX = p_70186_1_;
        this.motionY = p_70186_3_;
        this.motionZ = p_70186_5_;
        float f3 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(p_70186_3_, (double)f3) * 180.0D / Math.PI);
        this.ticksInGround = 0;
    }
*/
}
