package net.minecraft.src;

public class EntityAIOwnerHurtTarget extends EntityAITarget
{
    EntityTameable field_48392_a;
    EntityLiving field_48391_b;

    public EntityAIOwnerHurtTarget(EntityTameable par1EntityTameable)
    {
        super(par1EntityTameable, 32F, false);
        field_48392_a = par1EntityTameable;
        func_46079_a(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!field_48392_a.func_48139_F_())
        {
            return false;
        }

        EntityLiving entityliving = field_48392_a.func_48144_ah();

        if (entityliving == null)
        {
            return false;
        }
        else
        {
            field_48391_b = entityliving.func_48088_aP();
            return func_48376_a(field_48391_b, false);
        }
    }

    public void func_46080_e()
    {
        field_48382_c.func_48092_c(field_48391_b);
        super.func_46080_e();
    }
}
