package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class EntityAIHurtByTarget extends EntityAITarget
{
    boolean field_48395_a;

    public EntityAIHurtByTarget(EntityLiving par1EntityLiving, boolean par2)
    {
        super(par1EntityLiving, 16F, false);
        field_48395_a = par2;
        func_46079_a(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return func_48376_a(field_48382_c.getAITarget(), true);
    }

    public void func_46080_e()
    {
        field_48382_c.func_48092_c(field_48382_c.getAITarget());

        if (field_48395_a)
        {
            List list = field_48382_c.worldObj.getEntitiesWithinAABB(field_48382_c.getClass(), AxisAlignedBB.getBoundingBoxFromPool(field_48382_c.posX, field_48382_c.posY, field_48382_c.posZ, field_48382_c.posX + 1.0D, field_48382_c.posY + 1.0D, field_48382_c.posZ + 1.0D).expand(field_48379_d, 4D, field_48379_d));
            Iterator iterator = list.iterator();

            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }

                Entity entity = (Entity)iterator.next();
                EntityLiving entityliving = (EntityLiving)entity;

                if (field_48382_c != entityliving && entityliving.func_48094_aS() == null)
                {
                    entityliving.func_48092_c(field_48382_c.getAITarget());
                }
            }
            while (true);
        }

        super.func_46080_e();
    }
}
