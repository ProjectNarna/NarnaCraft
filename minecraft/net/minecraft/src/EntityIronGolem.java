package net.minecraft.src;

import java.util.Random;

public class EntityIronGolem extends EntityGolem
{
    private int field_48119_b;
    Village field_48121_a;
    private int field_48120_c;
    private int field_48118_d;

    public EntityIronGolem(World par1World)
    {
        super(par1World);
        field_48119_b = 0;
        field_48121_a = null;
        texture = "/mob/villager_golem.png";
        setSize(1.4F, 2.9F);
        func_48084_aL().func_48664_a(true);
        tasks.addTask(1, new EntityAIAttackOnCollide(this, 0.25F, true));
        tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.22F, 32F));
        tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.16F, true));
        tasks.addTask(4, new EntityAIMoveTwardsRestriction(this, 0.16F));
        tasks.addTask(5, new EntityAILookAtVillager(this));
        tasks.addTask(6, new EntityAIWander(this, 0.16F));
        tasks.addTask(7, new EntityAIWatchClosest(this, net.minecraft.src.EntityPlayer.class, 6F));
        tasks.addTask(8, new EntityAILookIdle(this));
        field_48105_bU.addTask(1, new EntityAIDefendVillage(this));
        field_48105_bU.addTask(2, new EntityAIHurtByTarget(this, false));
        field_48105_bU.addTask(3, new EntityAINearestAttackableTarget(this, net.minecraft.src.EntityMob.class, 16F, 0, false, true));
    }

    protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }

    protected void func_48097_s_()
    {
        if (--field_48119_b <= 0)
        {
            field_48119_b = 70 + rand.nextInt(50);
            field_48121_a = worldObj.field_48465_A.func_48564_a(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), 32);

            if (field_48121_a == null)
            {
                func_48083_aW();
            }
            else
            {
                ChunkCoordinates chunkcoordinates = field_48121_a.func_48539_a();
                func_48082_b(chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, field_48121_a.func_48531_b());
            }
        }

        super.func_48097_s_();
    }

    public int getMaxHealth()
    {
        return 100;
    }

    /**
     * Decrements the entity's air supply when underwater
     */
    protected int decreaseAirSupply(int par1)
    {
        return par1;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (field_48120_c > 0)
        {
            field_48120_c--;
        }

        if (field_48118_d > 0)
        {
            field_48118_d--;
        }

        if (motionX * motionX + motionZ * motionZ > 2.5E-007D && rand.nextInt(5) == 0)
        {
            int i = MathHelper.floor_double(posX);
            int j = MathHelper.floor_double(posY - 0.2D - (double)yOffset);
            int k = MathHelper.floor_double(posZ);
            int l = worldObj.getBlockId(i, j, k);

            if (l > 0)
            {
                worldObj.spawnParticle((new StringBuilder()).append("tilecrack_").append(l).toString(), posX + ((double)rand.nextFloat() - 0.5D) * (double)width, boundingBox.minY + 0.1D, posZ + ((double)rand.nextFloat() - 0.5D) * (double)width, 4D * ((double)rand.nextFloat() - 0.5D), 0.5D, ((double)rand.nextFloat() - 0.5D) * 4D);
            }
        }
    }

    public boolean func_48100_a(Class par1Class)
    {
        if (func_48112_E_() && (net.minecraft.src.EntityPlayer.class).isAssignableFrom(par1Class))
        {
            return false;
        }
        else
        {
            return super.func_48100_a(par1Class);
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("PlayerCreated", func_48112_E_());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        func_48115_b(par1NBTTagCompound.getBoolean("PlayerCreated"));
    }

    public boolean attackEntityAsMob(Entity par1Entity)
    {
        field_48120_c = 10;
        worldObj.setEntityState(this, (byte)4);
        boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 7 + rand.nextInt(15));

        if (flag)
        {
            par1Entity.motionY += 0.4D;
        }

        worldObj.playSoundAtEntity(this, "mob.irongolem.throw", 1.0F, 1.0F);
        return flag;
    }

    public void handleHealthUpdate(byte par1)
    {
        if (par1 == 4)
        {
            field_48120_c = 10;
            worldObj.playSoundAtEntity(this, "mob.irongolem.throw", 1.0F, 1.0F);
        }
        else if (par1 == 11)
        {
            field_48118_d = 400;
        }
        else
        {
            super.handleHealthUpdate(par1);
        }
    }

    public Village func_48113_aa()
    {
        return field_48121_a;
    }

    public int func_48114_ab()
    {
        return field_48120_c;
    }

    public void func_48116_a(boolean par1)
    {
        field_48118_d = par1 ? 400 : 0;
        worldObj.setEntityState(this, (byte)11);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "none";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.irongolem.hit";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.irongolem.death";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        worldObj.playSoundAtEntity(this, "mob.irongolem.walk", 1.0F, 1.0F);
    }

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean par1, int par2)
    {
        int i = rand.nextInt(3);

        for (int j = 0; j < i; j++)
        {
            dropItem(Block.plantRed.blockID, 1);
        }

        int k = 3 + rand.nextInt(3);

        for (int l = 0; l < k; l++)
        {
            dropItem(Item.ingotIron.shiftedIndex, 1);
        }
    }

    public int func_48117_D_()
    {
        return field_48118_d;
    }

    public boolean func_48112_E_()
    {
        return (dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void func_48115_b(boolean par1)
    {
        byte byte0 = dataWatcher.getWatchableObjectByte(16);

        if (par1)
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 | 1)));
        }
        else
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 & -2)));
        }
    }
}
