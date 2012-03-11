package net.minecraft.src;

public class EntityAIDefendVillage extends EntityAITarget
{
    EntityIronGolem field_48385_a;
    EntityLiving field_48384_b;

    public EntityAIDefendVillage(EntityIronGolem par1EntityIronGolem)
    {
        super(par1EntityIronGolem, 16F, false, true);
        field_48385_a = par1EntityIronGolem;
        func_46079_a(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        Village village = field_48385_a.func_48113_aa();

        if (village == null)
        {
            return false;
        }
        else
        {
            field_48384_b = village.func_48537_b(field_48385_a);
            return func_48376_a(field_48384_b, false);
        }
    }

    public void func_46080_e()
    {
        field_48385_a.func_48092_c(field_48384_b);
        super.func_46080_e();
    }
}
