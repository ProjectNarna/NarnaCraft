package net.minecraft.src;

public abstract class EntityAIBase
{
    private int field_46085_a;

    public EntityAIBase()
    {
        field_46085_a = 0;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public abstract boolean shouldExecute();

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return shouldExecute();
    }

    /**
     * Returns whether the task requires multiple updates or not
     */
    public boolean isContinous()
    {
        return true;
    }

    public void func_46080_e()
    {
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
    }

    public void func_46079_a(int par1)
    {
        field_46085_a = par1;
    }

    public int func_46083_c()
    {
        return field_46085_a;
    }
}
