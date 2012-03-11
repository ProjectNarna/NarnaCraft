package net.minecraft.src;

public class EntityAIRestrictOpenDoor extends EntityAIBase
{
    private EntityCreature field_48365_a;
    private VillageDoorInfo field_48364_b;

    public EntityAIRestrictOpenDoor(EntityCreature par1EntityCreature)
    {
        field_48365_a = par1EntityCreature;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (field_48365_a.worldObj.isDaytime())
        {
            return false;
        }

        Village village = field_48365_a.worldObj.field_48465_A.func_48564_a(MathHelper.floor_double(field_48365_a.posX), MathHelper.floor_double(field_48365_a.posY), MathHelper.floor_double(field_48365_a.posZ), 16);

        if (village == null)
        {
            return false;
        }

        field_48364_b = village.func_48527_b(MathHelper.floor_double(field_48365_a.posX), MathHelper.floor_double(field_48365_a.posY), MathHelper.floor_double(field_48365_a.posZ));

        if (field_48364_b == null)
        {
            return false;
        }
        else
        {
            return (double)field_48364_b.func_48593_b(MathHelper.floor_double(field_48365_a.posX), MathHelper.floor_double(field_48365_a.posY), MathHelper.floor_double(field_48365_a.posZ)) < 2.25D;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        if (field_48365_a.worldObj.isDaytime())
        {
            return false;
        }
        else
        {
            return !field_48364_b.field_48595_g && field_48364_b.func_48586_a(MathHelper.floor_double(field_48365_a.posX), MathHelper.floor_double(field_48365_a.posZ));
        }
    }

    public void func_46080_e()
    {
        field_48365_a.func_48084_aL().func_48673_b(false);
        field_48365_a.func_48084_aL().func_48663_c(false);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        field_48365_a.func_48084_aL().func_48673_b(true);
        field_48365_a.func_48084_aL().func_48663_c(true);
        field_48364_b = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        field_48364_b.func_48589_e();
    }
}
