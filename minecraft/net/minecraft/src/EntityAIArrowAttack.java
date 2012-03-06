package net.minecraft.src;

import java.util.Random;

public class EntityAIArrowAttack extends EntityAIBase
{
    World field_48373_a;
    EntityLiving field_48371_b;
    EntityLiving field_48372_c;
    int field_48369_d;
    float field_48370_e;
    int field_48367_f;
    int field_48368_g;
    int field_48374_h;

    public EntityAIArrowAttack(EntityLiving par1EntityLiving, float par2, int par3, int par4)
    {
        field_48369_d = 0;
        field_48367_f = 0;
        field_48371_b = par1EntityLiving;
        field_48373_a = par1EntityLiving.worldObj;
        field_48370_e = par2;
        field_48368_g = par3;
        field_48374_h = par4;
        func_46079_a(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLiving entityliving = field_48371_b.func_48094_aS();

        if (entityliving == null)
        {
            return false;
        }
        else
        {
            field_48372_c = entityliving;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return shouldExecute() || !field_48371_b.func_48084_aL().func_46072_b();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        field_48372_c = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        double d = 100D;
        double d1 = field_48371_b.getDistanceSq(field_48372_c.posX, field_48372_c.boundingBox.minY, field_48372_c.posZ);
        boolean flag = field_48371_b.func_48090_aM().func_48480_a(field_48372_c);

        if (flag)
        {
            field_48367_f++;
        }
        else
        {
            field_48367_f = 0;
        }

        if (d1 > d || field_48367_f < 20)
        {
            field_48371_b.func_48084_aL().func_48667_a(field_48372_c, field_48370_e);
        }
        else
        {
            field_48371_b.func_48084_aL().func_48672_f();
        }

        field_48371_b.getLookHelper().setLookPositionWithEntity(field_48372_c, 30F, 30F);
        field_48369_d = Math.max(field_48369_d - 1, 0);

        if (field_48369_d > 0)
        {
            return;
        }

        if (d1 > d || !flag)
        {
            return;
        }
        else
        {
            func_48366_h();
            field_48369_d = field_48374_h;
            return;
        }
    }

    private void func_48366_h()
    {
        if (field_48368_g == 1)
        {
            EntityArrow entityarrow = new EntityArrow(field_48373_a, field_48371_b, field_48372_c, 1.6F, 12F);
            field_48373_a.playSoundAtEntity(field_48371_b, "random.bow", 1.0F, 1.0F / (field_48371_b.getRNG().nextFloat() * 0.4F + 0.8F));
            field_48373_a.spawnEntityInWorld(entityarrow);
        }
        else if (field_48368_g == 2)
        {
            EntitySnowball entitysnowball = new EntitySnowball(field_48373_a, field_48371_b);
            double d = field_48372_c.posX - field_48371_b.posX;
            double d1 = (field_48372_c.posY + (double)field_48372_c.getEyeHeight()) - 1.1D - entitysnowball.posY;
            double d2 = field_48372_c.posZ - field_48371_b.posZ;
            float f = MathHelper.sqrt_double(d * d + d2 * d2) * 0.2F;
            entitysnowball.setThrowableHeading(d, d1 + (double)f, d2, 1.6F, 12F);
            field_48373_a.playSoundAtEntity(field_48371_b, "random.bow", 1.0F, 1.0F / (field_48371_b.getRNG().nextFloat() * 0.4F + 0.8F));
            field_48373_a.spawnEntityInWorld(entitysnowball);
        }
    }
}
