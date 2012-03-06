package net.minecraft.src;

public class EntityAIFollowOwner extends EntityAIBase
{
    private EntityTameable field_48305_d;
    private EntityLiving field_48306_e;
    World field_48309_a;
    private float field_48303_f;
    private PathNavigate field_48304_g;
    private int field_48310_h;
    float field_48307_b;
    float field_48308_c;
    private boolean field_48311_i;

    public EntityAIFollowOwner(EntityTameable par1EntityTameable, float par2, float par3, float par4)
    {
        field_48305_d = par1EntityTameable;
        field_48309_a = par1EntityTameable.worldObj;
        field_48303_f = par2;
        field_48304_g = par1EntityTameable.func_48084_aL();
        field_48308_c = par3;
        field_48307_b = par4;
        func_46079_a(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLiving entityliving = field_48305_d.func_48144_ah();

        if (entityliving == null)
        {
            return false;
        }

        if (field_48305_d.func_48141_af())
        {
            return false;
        }

        if (field_48305_d.getDistanceSqToEntity(entityliving) < (double)(field_48308_c * field_48308_c))
        {
            return false;
        }
        else
        {
            field_48306_e = entityliving;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !field_48304_g.func_46072_b() && field_48305_d.getDistanceSqToEntity(field_48306_e) > (double)(field_48307_b * field_48307_b) && !field_48305_d.func_48141_af();
    }

    public void func_46080_e()
    {
        field_48310_h = 0;
        field_48311_i = field_48305_d.func_48084_aL().func_48658_a();
        field_48305_d.func_48084_aL().func_48664_a(false);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        field_48306_e = null;
        field_48304_g.func_48672_f();
        field_48305_d.func_48084_aL().func_48664_a(field_48311_i);
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        field_48305_d.getLookHelper().setLookPositionWithEntity(field_48306_e, 10F, field_48305_d.getVerticalFaceSpeed());

        if (field_48305_d.func_48141_af())
        {
            return;
        }

        if (--field_48310_h > 0)
        {
            return;
        }

        field_48310_h = 10;

        if (field_48304_g.func_48667_a(field_48306_e, field_48303_f))
        {
            return;
        }

        if (field_48305_d.getDistanceSqToEntity(field_48306_e) < 144D)
        {
            return;
        }

        int i = MathHelper.floor_double(field_48306_e.posX) - 2;
        int j = MathHelper.floor_double(field_48306_e.posZ) - 2;
        int k = MathHelper.floor_double(field_48306_e.boundingBox.minY);

        for (int l = 0; l <= 4; l++)
        {
            for (int i1 = 0; i1 <= 4; i1++)
            {
                if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && field_48309_a.isBlockNormalCube(i + l, k - 1, j + i1) && !field_48309_a.isBlockNormalCube(i + l, k, j + i1) && !field_48309_a.isBlockNormalCube(i + l, k + 1, j + i1))
                {
                    field_48305_d.setLocationAndAngles((float)(i + l) + 0.5F, k, (float)(j + i1) + 0.5F, field_48305_d.rotationYaw, field_48305_d.rotationPitch);
                    field_48304_g.func_48672_f();
                    return;
                }
            }
        }
    }
}
