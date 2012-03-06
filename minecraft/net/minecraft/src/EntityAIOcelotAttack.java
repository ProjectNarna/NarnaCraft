package net.minecraft.src;

public class EntityAIOcelotAttack extends EntityAIBase
{
    World field_48363_a;
    EntityLiving field_48361_b;
    EntityLiving field_48362_c;
    int field_48360_d;

    public EntityAIOcelotAttack(EntityLiving par1EntityLiving)
    {
        field_48360_d = 0;
        field_48361_b = par1EntityLiving;
        field_48363_a = par1EntityLiving.worldObj;
        func_46079_a(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLiving entityliving = field_48361_b.func_48094_aS();

        if (entityliving == null)
        {
            return false;
        }
        else
        {
            field_48362_c = entityliving;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        if (!field_48362_c.isEntityAlive())
        {
            return false;
        }

        if (field_48361_b.getDistanceSqToEntity(field_48362_c) > 225D)
        {
            return false;
        }
        else
        {
            return !field_48361_b.func_48084_aL().func_46072_b() || shouldExecute();
        }
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        field_48362_c = null;
        field_48361_b.func_48084_aL().func_48672_f();
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        field_48361_b.getLookHelper().setLookPositionWithEntity(field_48362_c, 30F, 30F);
        double d = field_48361_b.width * 2.0F * (field_48361_b.width * 2.0F);
        double d1 = field_48361_b.getDistanceSq(field_48362_c.posX, field_48362_c.boundingBox.minY, field_48362_c.posZ);
        float f = 0.23F;

        if (d1 > d && d1 < 16D)
        {
            f = 0.4F;
        }
        else if (d1 < 225D)
        {
            f = 0.18F;
        }

        field_48361_b.func_48084_aL().func_48667_a(field_48362_c, f);
        field_48360_d = Math.max(field_48360_d - 1, 0);

        if (d1 > d)
        {
            return;
        }

        if (field_48360_d > 0)
        {
            return;
        }
        else
        {
            field_48360_d = 20;
            field_48361_b.attackEntityAsMob(field_48362_c);
            return;
        }
    }
}
