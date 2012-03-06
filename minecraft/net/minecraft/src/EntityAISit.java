package net.minecraft.src;

public class EntityAISit extends EntityAIBase
{
    private EntityTameable field_48409_a;
    private boolean field_48408_b;

    public EntityAISit(EntityTameable par1EntityTameable)
    {
        field_48408_b = false;
        field_48409_a = par1EntityTameable;
        func_46079_a(5);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!field_48409_a.func_48139_F_())
        {
            return false;
        }

        if (field_48409_a.isInWater())
        {
            return false;
        }

        if (!field_48409_a.onGround)
        {
            return false;
        }

        EntityLiving entityliving = field_48409_a.func_48144_ah();

        if (entityliving == null)
        {
            return true;
        }

        if (field_48409_a.getDistanceSqToEntity(entityliving) < 144D && entityliving.getAITarget() != null)
        {
            return false;
        }
        else
        {
            return field_48408_b;
        }
    }

    public void func_46080_e()
    {
        field_48409_a.func_48084_aL().func_48672_f();
        field_48409_a.func_48140_f(true);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        field_48409_a.func_48140_f(false);
    }

    public void func_48407_a(boolean par1)
    {
        field_48408_b = par1;
    }
}
