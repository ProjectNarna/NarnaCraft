package net.minecraft.src;

import java.util.*;

public class EntityAIFollowGolem extends EntityAIBase
{
    private EntityVillager field_48403_a;
    private EntityIronGolem field_48401_b;
    private int field_48402_c;
    private boolean field_48400_d;

    public EntityAIFollowGolem(EntityVillager par1EntityVillager)
    {
        field_48400_d = false;
        field_48403_a = par1EntityVillager;
        func_46079_a(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (field_48403_a.func_48123_at() >= 0)
        {
            return false;
        }

        if (!field_48403_a.worldObj.isDaytime())
        {
            return false;
        }

        List list = field_48403_a.worldObj.getEntitiesWithinAABB(net.minecraft.src.EntityIronGolem.class, field_48403_a.boundingBox.expand(6D, 2D, 6D));

        if (list.size() == 0)
        {
            return false;
        }

        Iterator iterator = list.iterator();

        do
        {
            if (!iterator.hasNext())
            {
                break;
            }

            Entity entity = (Entity)iterator.next();
            EntityIronGolem entityirongolem = (EntityIronGolem)entity;

            if (entityirongolem.func_48117_D_() <= 0)
            {
                continue;
            }

            field_48401_b = entityirongolem;
            break;
        }
        while (true);

        return field_48401_b != null;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return field_48401_b.func_48117_D_() > 0;
    }

    public void func_46080_e()
    {
        field_48402_c = field_48403_a.getRNG().nextInt(320);
        field_48400_d = false;
        field_48401_b.func_48084_aL().func_48672_f();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        field_48401_b = null;
        field_48403_a.func_48084_aL().func_48672_f();
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        field_48403_a.getLookHelper().setLookPositionWithEntity(field_48401_b, 30F, 30F);

        if (field_48401_b.func_48117_D_() == field_48402_c)
        {
            field_48403_a.func_48084_aL().func_48667_a(field_48401_b, 0.15F);
            field_48400_d = true;
        }

        if (field_48400_d && field_48403_a.getDistanceSqToEntity(field_48401_b) < 4D)
        {
            field_48401_b.func_48116_a(false);
            field_48403_a.func_48084_aL().func_48672_f();
        }
    }
}
