package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class EntityAIFollowParent extends EntityAIBase
{
    EntityAnimal field_48249_a;
    EntityAnimal field_48247_b;
    float field_48248_c;
    private int field_48246_d;

    public EntityAIFollowParent(EntityAnimal par1EntityAnimal, float par2)
    {
        field_48249_a = par1EntityAnimal;
        field_48248_c = par2;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (field_48249_a.func_48123_at() >= 0)
        {
            return false;
        }

        List list = field_48249_a.worldObj.getEntitiesWithinAABB(field_48249_a.getClass(), field_48249_a.boundingBox.expand(8D, 4D, 8D));
        EntityAnimal entityanimal = null;
        double d = Double.MAX_VALUE;
        Iterator iterator = list.iterator();

        do
        {
            if (!iterator.hasNext())
            {
                break;
            }

            Entity entity = (Entity)iterator.next();
            EntityAnimal entityanimal1 = (EntityAnimal)entity;

            if (entityanimal1.func_48123_at() >= 0)
            {
                double d1 = field_48249_a.getDistanceSqToEntity(entityanimal1);

                if (d1 <= d)
                {
                    d = d1;
                    entityanimal = entityanimal1;
                }
            }
        }
        while (true);

        if (entityanimal == null)
        {
            return false;
        }

        if (d < 9D)
        {
            return false;
        }
        else
        {
            field_48247_b = entityanimal;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        if (!field_48247_b.isEntityAlive())
        {
            return false;
        }

        double d = field_48249_a.getDistanceSqToEntity(field_48247_b);
        return d >= 9D && d <= 256D;
    }

    public void func_46080_e()
    {
        field_48246_d = 0;
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        field_48247_b = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        if (--field_48246_d > 0)
        {
            return;
        }
        else
        {
            field_48246_d = 10;
            field_48249_a.func_48084_aL().func_48667_a(field_48247_b, field_48248_c);
            return;
        }
    }
}
