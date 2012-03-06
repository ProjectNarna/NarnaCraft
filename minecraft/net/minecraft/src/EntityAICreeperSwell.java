package net.minecraft.src;

public class EntityAICreeperSwell extends EntityAIBase
{
    EntityCreeper field_48237_a;
    EntityLiving field_48236_b;

    public EntityAICreeperSwell(EntityCreeper par1EntityCreeper)
    {
        field_48237_a = par1EntityCreeper;
        func_46079_a(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLiving entityliving = field_48237_a.func_48094_aS();
        return field_48237_a.getCreeperState() > 0 || entityliving != null && field_48237_a.getDistanceSqToEntity(entityliving) < 9D;
    }

    public void func_46080_e()
    {
        field_48237_a.func_48084_aL().func_48672_f();
        field_48236_b = field_48237_a.func_48094_aS();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        field_48236_b = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        if (field_48236_b == null)
        {
            field_48237_a.setCreeperState(-1);
            return;
        }

        if (field_48237_a.getDistanceSqToEntity(field_48236_b) > 49D)
        {
            field_48237_a.setCreeperState(-1);
            return;
        }

        if (!field_48237_a.func_48090_aM().func_48480_a(field_48236_b))
        {
            field_48237_a.setCreeperState(-1);
            return;
        }
        else
        {
            field_48237_a.setCreeperState(1);
            return;
        }
    }
}
