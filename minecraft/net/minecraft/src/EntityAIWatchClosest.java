package net.minecraft.src;

import java.util.Random;

public class EntityAIWatchClosest extends EntityAIBase
{
    private EntityLiving field_46105_a;
    private Entity field_48295_b;
    private float field_46101_d;
    private int field_46102_e;
    private float field_48294_e;
    private Class field_48293_f;

    public EntityAIWatchClosest(EntityLiving par1EntityLiving, Class par2Class, float par3)
    {
        field_46105_a = par1EntityLiving;
        field_48293_f = par2Class;
        field_46101_d = par3;
        field_48294_e = 0.02F;
        func_46079_a(2);
    }

    public EntityAIWatchClosest(EntityLiving par1EntityLiving, Class par2Class, float par3, float par4)
    {
        field_46105_a = par1EntityLiving;
        field_48293_f = par2Class;
        field_46101_d = par3;
        field_48294_e = par4;
        func_46079_a(2);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (field_46105_a.getRNG().nextFloat() >= field_48294_e)
        {
            return false;
        }

        if (field_48293_f == (net.minecraft.src.EntityPlayer.class))
        {
            field_48295_b = field_46105_a.worldObj.getClosestPlayerToEntity(field_46105_a, field_46101_d);
        }
        else
        {
            field_48295_b = field_46105_a.worldObj.func_48459_a(field_48293_f, field_46105_a.boundingBox.expand(field_46101_d, 3D, field_46101_d), field_46105_a);
        }

        return field_48295_b != null;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        if (!field_48295_b.isEntityAlive())
        {
            return false;
        }

        if (field_46105_a.getDistanceSqToEntity(field_48295_b) > (double)(field_46101_d * field_46101_d))
        {
            return false;
        }
        else
        {
            return field_46102_e > 0;
        }
    }

    public void func_46080_e()
    {
        field_46102_e = 40 + field_46105_a.getRNG().nextInt(40);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        field_48295_b = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        field_46105_a.getLookHelper().setLookPosition(field_48295_b.posX, field_48295_b.posY + (double)field_48295_b.getEyeHeight(), field_48295_b.posZ, 10F, field_46105_a.getVerticalFaceSpeed());
        field_46102_e--;
    }
}
