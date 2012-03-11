package net.minecraft.src;

public class EntityAIOpenDoor extends EntityAIDoorInteract
{
    boolean field_48328_i;
    int field_48327_j;

    public EntityAIOpenDoor(EntityLiving par1EntityLiving, boolean par2)
    {
        super(par1EntityLiving);
        field_48325_a = par1EntityLiving;
        field_48328_i = par2;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return field_48328_i && field_48327_j > 0 && super.continueExecuting();
    }

    public void func_46080_e()
    {
        field_48327_j = 20;
        field_48322_e.onPoweredBlockChange(field_48325_a.worldObj, field_48323_b, field_48324_c, field_48321_d, true);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        if (field_48328_i)
        {
            field_48322_e.onPoweredBlockChange(field_48325_a.worldObj, field_48323_b, field_48324_c, field_48321_d, false);
        }
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        field_48327_j--;
        super.updateTask();
    }
}
