package net.minecraft.src;

import java.util.Random;

public class EntityAIFleeSun extends EntityAIBase
{
    private EntityCreature field_48302_a;
    private double field_48300_b;
    private double field_48301_c;
    private double field_48298_d;
    private float field_48299_e;
    private World field_48297_f;

    public EntityAIFleeSun(EntityCreature par1EntityCreature, float par2)
    {
        field_48302_a = par1EntityCreature;
        field_48299_e = par2;
        field_48297_f = par1EntityCreature.worldObj;
        func_46079_a(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!field_48297_f.isDaytime())
        {
            return false;
        }

        if (!field_48302_a.isBurning())
        {
            return false;
        }

        if (!field_48297_f.canBlockSeeTheSky(MathHelper.floor_double(field_48302_a.posX), (int)field_48302_a.boundingBox.minY, MathHelper.floor_double(field_48302_a.posZ)))
        {
            return false;
        }

        Vec3D vec3d = func_48296_h();

        if (vec3d == null)
        {
            return false;
        }
        else
        {
            field_48300_b = vec3d.xCoord;
            field_48301_c = vec3d.yCoord;
            field_48298_d = vec3d.zCoord;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !field_48302_a.func_48084_aL().func_46072_b();
    }

    public void func_46080_e()
    {
        field_48302_a.func_48084_aL().func_48666_a(field_48300_b, field_48301_c, field_48298_d, field_48299_e);
    }

    private Vec3D func_48296_h()
    {
        Random random = field_48302_a.getRNG();

        for (int i = 0; i < 10; i++)
        {
            int j = MathHelper.floor_double((field_48302_a.posX + (double)random.nextInt(20)) - 10D);
            int k = MathHelper.floor_double((field_48302_a.boundingBox.minY + (double)random.nextInt(6)) - 3D);
            int l = MathHelper.floor_double((field_48302_a.posZ + (double)random.nextInt(20)) - 10D);

            if (!field_48297_f.canBlockSeeTheSky(j, k, l) && field_48302_a.getBlockPathWeight(j, k, l) < 0.0F)
            {
                return Vec3D.createVector(j, k, l);
            }
        }

        return null;
    }
}
