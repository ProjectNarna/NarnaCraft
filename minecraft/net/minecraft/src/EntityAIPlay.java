package net.minecraft.src;

import java.util.*;

public class EntityAIPlay extends EntityAIBase
{
    private EntityVillager field_48359_a;
    private EntityLiving field_48357_b;
    private float field_48358_c;
    private int field_48356_d;

    public EntityAIPlay(EntityVillager par1EntityVillager, float par2)
    {
        field_48359_a = par1EntityVillager;
        field_48358_c = par2;
        func_46079_a(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (field_48359_a.func_48123_at() >= 0)
        {
            return false;
        }

        if (field_48359_a.getRNG().nextInt(400) != 0)
        {
            return false;
        }

        List list = field_48359_a.worldObj.getEntitiesWithinAABB(net.minecraft.src.EntityVillager.class, field_48359_a.boundingBox.expand(6D, 3D, 6D));
        double d = Double.MAX_VALUE;
        Iterator iterator = list.iterator();

        do
        {
            if (!iterator.hasNext())
            {
                break;
            }

            Entity entity = (Entity)iterator.next();

            if (entity != field_48359_a)
            {
                EntityVillager entityvillager = (EntityVillager)entity;

                if (!entityvillager.func_48125_w() && entityvillager.func_48123_at() < 0)
                {
                    double d1 = entityvillager.getDistanceSqToEntity(field_48359_a);

                    if (d1 <= d)
                    {
                        d = d1;
                        field_48357_b = entityvillager;
                    }
                }
            }
        }
        while (true);

        if (field_48357_b == null)
        {
            Vec3D vec3d = RandomPositionGenerator.func_48622_a(field_48359_a, 16, 3);

            if (vec3d == null)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return field_48356_d > 0;
    }

    public void func_46080_e()
    {
        if (field_48357_b != null)
        {
            field_48359_a.func_48127_b(true);
        }

        field_48356_d = 1000;
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        field_48359_a.func_48127_b(false);
        field_48357_b = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        field_48356_d--;

        if (field_48357_b != null)
        {
            if (field_48359_a.getDistanceSqToEntity(field_48357_b) > 4D)
            {
                field_48359_a.func_48084_aL().func_48667_a(field_48357_b, field_48358_c);
            }
        }
        else if (field_48359_a.func_48084_aL().func_46072_b())
        {
            Vec3D vec3d = RandomPositionGenerator.func_48622_a(field_48359_a, 16, 3);

            if (vec3d == null)
            {
                return;
            }

            field_48359_a.func_48084_aL().func_48666_a(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord, field_48358_c);
        }
    }
}
