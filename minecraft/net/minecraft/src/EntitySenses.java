package net.minecraft.src;

import java.util.ArrayList;

public class EntitySenses
{
    EntityLiving field_48484_a;
    ArrayList field_48482_b;
    ArrayList field_48483_c;

    public EntitySenses(EntityLiving par1EntityLiving)
    {
        field_48482_b = new ArrayList();
        field_48483_c = new ArrayList();
        field_48484_a = par1EntityLiving;
    }

    public void func_48481_a()
    {
        field_48482_b.clear();
        field_48483_c.clear();
    }

    public boolean func_48480_a(Entity par1Entity)
    {
        if (field_48482_b.contains(par1Entity))
        {
            return true;
        }

        if (field_48483_c.contains(par1Entity))
        {
            return false;
        }

        Profiler.startSection("canSee");
        boolean flag = field_48484_a.canEntityBeSeen(par1Entity);
        Profiler.endSection();

        if (flag)
        {
            field_48482_b.add(par1Entity);
        }
        else
        {
            field_48483_c.add(par1Entity);
        }

        return flag;
    }
}
