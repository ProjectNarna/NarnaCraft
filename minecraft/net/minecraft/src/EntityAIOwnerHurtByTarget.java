package net.minecraft.src;

public class EntityAIOwnerHurtByTarget extends EntityAITarget
{
    EntityTameable field_48394_a;
    EntityLiving field_48393_b;

    public EntityAIOwnerHurtByTarget(EntityTameable par1EntityTameable)
    {
        super(par1EntityTameable, 32F, false);
        field_48394_a = par1EntityTameable;
        func_46079_a(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!field_48394_a.func_48139_F_())
        {
            return false;
        }

        EntityLiving entityliving = field_48394_a.func_48144_ah();

        if (entityliving == null)
        {
            return false;
        }
        else
        {
            field_48393_b = entityliving.getAITarget();
            return func_48376_a(field_48393_b, false);
        }
    }

    public void func_46080_e()
    {
        field_48382_c.func_48092_c(field_48393_b);
        super.func_46080_e();
    }
}
