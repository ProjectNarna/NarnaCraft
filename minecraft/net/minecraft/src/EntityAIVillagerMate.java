package net.minecraft.src;

import java.util.Random;

public class EntityAIVillagerMate extends EntityAIBase
{
    private EntityVillager field_48342_b;
    private EntityVillager field_48343_c;
    private World field_48340_d;
    private int field_48341_e;
    Village field_48344_a;

    public EntityAIVillagerMate(EntityVillager par1EntityVillager)
    {
        field_48341_e = 0;
        field_48342_b = par1EntityVillager;
        field_48340_d = par1EntityVillager.worldObj;
        func_46079_a(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (field_48342_b.func_48123_at() != 0)
        {
            return false;
        }

        if (field_48342_b.getRNG().nextInt(500) != 0)
        {
            return false;
        }

        field_48344_a = field_48340_d.field_48465_A.func_48564_a(MathHelper.floor_double(field_48342_b.posX), MathHelper.floor_double(field_48342_b.posY), MathHelper.floor_double(field_48342_b.posZ), 0);

        if (field_48344_a == null)
        {
            return false;
        }

        if (!func_48337_h())
        {
            return false;
        }

        Entity entity = field_48340_d.func_48459_a(net.minecraft.src.EntityVillager.class, field_48342_b.boundingBox.expand(8D, 3D, 8D), field_48342_b);

        if (entity == null)
        {
            return false;
        }

        field_48343_c = (EntityVillager)entity;
        return field_48343_c.func_48123_at() == 0;
    }

    public void func_46080_e()
    {
        field_48341_e = 300;
        field_48342_b.func_48128_a(true);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        field_48344_a = null;
        field_48343_c = null;
        field_48342_b.func_48128_a(false);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return field_48341_e >= 0 && func_48337_h() && field_48342_b.func_48123_at() == 0;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        field_48341_e--;
        field_48342_b.getLookHelper().setLookPositionWithEntity(field_48343_c, 10F, 30F);

        if (field_48342_b.getDistanceSqToEntity(field_48343_c) > 2.25D)
        {
            field_48342_b.func_48084_aL().func_48667_a(field_48343_c, 0.25F);
        }
        else if (field_48341_e == 0 && field_48343_c.func_48126_w_())
        {
            func_48339_i();
        }

        if (field_48342_b.getRNG().nextInt(35) == 0)
        {
            func_48338_a(field_48342_b);
        }
    }

    private boolean func_48337_h()
    {
        int i = (int)((double)(float)field_48344_a.func_48530_c() * 0.35D);
        return field_48344_a.func_48524_e() < i;
    }

    private void func_48339_i()
    {
        EntityVillager entityvillager = new EntityVillager(field_48340_d);
        field_48343_c.func_48122_d(6000);
        field_48342_b.func_48122_d(6000);
        entityvillager.func_48122_d(-24000);
        entityvillager.func_48124_d_(field_48342_b.getRNG().nextInt(5));
        entityvillager.setLocationAndAngles(field_48342_b.posX, field_48342_b.posY, field_48342_b.posZ, 0.0F, 0.0F);
        field_48340_d.spawnEntityInWorld(entityvillager);
        func_48338_a(entityvillager);
    }

    private void func_48338_a(EntityLiving par1EntityLiving)
    {
        Random random = par1EntityLiving.getRNG();

        for (int i = 0; i < 5; i++)
        {
            double d = random.nextGaussian() * 0.02D;
            double d1 = random.nextGaussian() * 0.02D;
            double d2 = random.nextGaussian() * 0.02D;
            field_48340_d.spawnParticle("heart", (par1EntityLiving.posX + (double)(random.nextFloat() * par1EntityLiving.width * 2.0F)) - (double)par1EntityLiving.width, par1EntityLiving.posY + 1.0D + (double)(random.nextFloat() * par1EntityLiving.height), (par1EntityLiving.posZ + (double)(random.nextFloat() * par1EntityLiving.width * 2.0F)) - (double)par1EntityLiving.width, d, d1, d2);
        }
    }
}
