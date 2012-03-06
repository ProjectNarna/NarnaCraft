package net.minecraft.src;

import java.util.Random;

public class EntityAILookIdle extends EntityAIBase
{
    private EntityLiving field_46089_a;
    private double field_46087_b;
    private double field_46088_c;
    private int field_46086_d;

    public EntityAILookIdle(EntityLiving par1EntityLiving)
    {
        field_46086_d = 0;
        field_46089_a = par1EntityLiving;
        func_46079_a(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return field_46089_a.getRNG().nextFloat() < 0.02F;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return field_46086_d >= 0;
    }

    public void func_46080_e()
    {
        double d = (Math.PI * 2D) * field_46089_a.getRNG().nextDouble();
        field_46087_b = Math.cos(d);
        field_46088_c = Math.sin(d);
        field_46086_d = 20 + field_46089_a.getRNG().nextInt(20);
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        field_46086_d--;
        field_46089_a.getLookHelper().setLookPosition(field_46089_a.posX + field_46087_b, field_46089_a.posY + (double)field_46089_a.getEyeHeight(), field_46089_a.posZ + field_46088_c, 10F, field_46089_a.getVerticalFaceSpeed());
    }
}
