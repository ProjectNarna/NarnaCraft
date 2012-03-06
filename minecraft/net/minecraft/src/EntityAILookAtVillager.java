package net.minecraft.src;

import java.util.Random;

public class EntityAILookAtVillager extends EntityAIBase
{
    private EntityIronGolem field_48406_a;
    private EntityVillager field_48404_b;
    private int field_48405_c;

    public EntityAILookAtVillager(EntityIronGolem par1EntityIronGolem)
    {
        field_48406_a = par1EntityIronGolem;
        func_46079_a(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!field_48406_a.worldObj.isDaytime())
        {
            return false;
        }

        if (field_48406_a.getRNG().nextInt(8000) != 0)
        {
            return false;
        }
        else
        {
            field_48404_b = (EntityVillager)field_48406_a.worldObj.func_48459_a(net.minecraft.src.EntityVillager.class, field_48406_a.boundingBox.expand(6D, 2D, 6D), field_48406_a);
            return field_48404_b != null;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return field_48405_c > 0;
    }

    public void func_46080_e()
    {
        field_48405_c = 400;
        field_48406_a.func_48116_a(true);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        field_48406_a.func_48116_a(false);
        field_48404_b = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        field_48406_a.getLookHelper().setLookPositionWithEntity(field_48404_b, 30F, 30F);
        field_48405_c--;
    }
}
