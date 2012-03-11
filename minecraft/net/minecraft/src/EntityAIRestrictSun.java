package net.minecraft.src;

public class EntityAIRestrictSun extends EntityAIBase
{
    private EntityCreature field_48235_a;

    public EntityAIRestrictSun(EntityCreature par1EntityCreature)
    {
        field_48235_a = par1EntityCreature;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return field_48235_a.worldObj.isDaytime();
    }

    public void func_46080_e()
    {
        field_48235_a.func_48084_aL().func_48680_d(true);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        field_48235_a.func_48084_aL().func_48680_d(false);
    }
}
