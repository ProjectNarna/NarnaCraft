package net.minecraft.src;

import java.util.Random;

public class EntityVillager extends EntityAgeable
{
    private int field_48131_b;
    private boolean field_48132_c;
    private boolean field_48130_d;
    Village field_48133_a;

    public EntityVillager(World par1World)
    {
        this(par1World, 0);
    }

    public EntityVillager(World par1World, int par2)
    {
        super(par1World);
        field_48131_b = 0;
        field_48132_c = false;
        field_48130_d = false;
        field_48133_a = null;
        func_48124_d_(par2);
        setTextureByProfession();
        moveSpeed = 0.5F;
        func_48084_aL().func_48673_b(true);
        func_48084_aL().func_48664_a(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIAvoidEntity(this, net.minecraft.src.EntityZombie.class, 8F, 0.3F, 0.35F));
        tasks.addTask(2, new EntityAIMoveIndoors(this));
        tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        tasks.addTask(4, new EntityAIOpenDoor(this, true));
        tasks.addTask(5, new EntityAIMoveTwardsRestriction(this, 0.3F));
        tasks.addTask(6, new EntityAIVillagerMate(this));
        tasks.addTask(7, new EntityAIFollowGolem(this));
        tasks.addTask(8, new EntityAIPlay(this, 0.32F));
        tasks.addTask(9, new EntityAIWatchClosest2(this, net.minecraft.src.EntityPlayer.class, 3F, 1.0F));
        tasks.addTask(9, new EntityAIWatchClosest2(this, net.minecraft.src.EntityVillager.class, 5F, 0.02F));
        tasks.addTask(9, new EntityAIWander(this, 0.3F));
        tasks.addTask(10, new EntityAIWatchClosest(this, net.minecraft.src.EntityLiving.class, 8F));
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
        if (--field_48131_b <= 0)
        {
            worldObj.field_48465_A.func_48565_a(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
            field_48131_b = 70 + rand.nextInt(50);
            field_48133_a = worldObj.field_48465_A.func_48564_a(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), 32);

            if (field_48133_a == null)
            {
                func_48083_aW();
            }
            else
            {
                ChunkCoordinates chunkcoordinates = field_48133_a.func_48539_a();
                func_48082_b(chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, field_48133_a.func_48531_b());
            }
        }

        super.func_48097_s_();
    }

    protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(16, Integer.valueOf(0));
    }

    public int getMaxHealth()
    {
        return 20;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Profession", func_48129_t());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        func_48124_d_(par1NBTTagCompound.getInteger("Profession"));
        setTextureByProfession();
    }

    /**
     * [This is the function which sets a Villager's skin based on its villagerProfession value]
     */
    private void setTextureByProfession()
    {
        texture = "/mob/villager/villager.png";

        if (func_48129_t() == 0)
        {
            texture = "/mob/villager/farmer.png";
        }

        if (func_48129_t() == 1)
        {
            texture = "/mob/villager/librarian.png";
        }

        if (func_48129_t() == 2)
        {
            texture = "/mob/villager/priest.png";
        }

        if (func_48129_t() == 3)
        {
            texture = "/mob/villager/smith.png";
        }

        if (func_48129_t() == 4)
        {
            texture = "/mob/villager/butcher.png";
        }
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return false;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.villager.default";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.villager.defaulthurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.villager.defaultdeath";
    }

    public void func_48124_d_(int par1)
    {
        dataWatcher.updateObject(16, Integer.valueOf(par1));
    }

    public int func_48129_t()
    {
        return dataWatcher.getWatchableObjectInt(16);
    }

    public boolean func_48126_w_()
    {
        return field_48132_c;
    }

    public void func_48128_a(boolean par1)
    {
        field_48132_c = par1;
    }

    public void func_48127_b(boolean par1)
    {
        field_48130_d = par1;
    }

    public boolean func_48125_w()
    {
        return field_48130_d;
    }

    public void func_48086_a(EntityLiving par1EntityLiving)
    {
        super.func_48086_a(par1EntityLiving);

        if (field_48133_a != null && par1EntityLiving != null)
        {
            field_48133_a.func_48534_a(par1EntityLiving);
        }
    }
}
