package net.minecraft.src;

import java.util.Random;

public class EntityAILeapAtTarget extends EntityAIBase
{
    EntityLiving field_48252_a;
    EntityLiving field_48250_b;
    float field_48251_c;

    public EntityAILeapAtTarget(EntityLiving par1EntityLiving, float par2)
    {
        field_48252_a = par1EntityLiving;
        field_48251_c = par2;
        func_46079_a(5);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        field_48250_b = field_48252_a.func_48094_aS();

        if (field_48250_b == null)
        {
            return false;
        }

        double d = field_48252_a.getDistanceSqToEntity(field_48250_b);

        if (d < 4D || d > 16D)
        {
            return false;
        }

        if (!field_48252_a.onGround)
        {
            return false;
        }

        return field_48252_a.getRNG().nextInt(5) == 0;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !field_48252_a.onGround;
    }

    public void func_46080_e()
    {
        double d = field_48250_b.posX - field_48252_a.posX;
        double d1 = field_48250_b.posZ - field_48252_a.posZ;
        float f = MathHelper.sqrt_double(d * d + d1 * d1);
        field_48252_a.motionX += (d / (double)f) * 0.5D * 0.8D + field_48252_a.motionX * 0.2D;
        field_48252_a.motionZ += (d1 / (double)f) * 0.5D * 0.8D + field_48252_a.motionZ * 0.2D;
        field_48252_a.motionY = field_48251_c;
    }
}
