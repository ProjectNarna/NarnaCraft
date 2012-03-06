package net.minecraft.src;

import java.util.*;

public class EntityAINearestAttackableTarget extends EntityAITarget
{
    EntityLiving field_48389_a;
    Class field_48388_b;
    int field_48386_f;
    private EntityAINearestAttackableTargetSorter field_48387_g;

    public EntityAINearestAttackableTarget(EntityLiving par1EntityLiving, Class par2Class, float par3, int par4, boolean par5)
    {
        this(par1EntityLiving, par2Class, par3, par4, par5, false);
    }

    public EntityAINearestAttackableTarget(EntityLiving par1EntityLiving, Class par2Class, float par3, int par4, boolean par5, boolean par6)
    {
        super(par1EntityLiving, par3, par5, par6);
        field_48388_b = par2Class;
        field_48379_d = par3;
        field_48386_f = par4;
        field_48387_g = new EntityAINearestAttackableTargetSorter(this, par1EntityLiving);
        func_46079_a(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        label0:
        {
            if (field_48386_f > 0 && field_48382_c.getRNG().nextInt(field_48386_f) != 0)
            {
                return false;
            }

            if (field_48388_b == (net.minecraft.src.EntityPlayer.class))
            {
                EntityPlayer entityplayer = field_48382_c.worldObj.getClosestVulnerablePlayerToEntity(field_48382_c, field_48379_d);

                if (func_48376_a(entityplayer, false))
                {
                    field_48389_a = entityplayer;
                    return true;
                }

                break label0;
            }

            List list = field_48382_c.worldObj.getEntitiesWithinAABB(field_48388_b, field_48382_c.boundingBox.expand(field_48379_d, 4D, field_48379_d));
            Collections.sort(list, field_48387_g);
            Iterator iterator = list.iterator();
            EntityLiving entityliving;

            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }

                Entity entity = (Entity)iterator.next();
                entityliving = (EntityLiving)entity;
            }
            while (!func_48376_a(entityliving, false));

            field_48389_a = entityliving;
            return true;
        }
        return false;
    }

    public void func_46080_e()
    {
        field_48382_c.func_48092_c(field_48389_a);
        super.func_46080_e();
    }
}
