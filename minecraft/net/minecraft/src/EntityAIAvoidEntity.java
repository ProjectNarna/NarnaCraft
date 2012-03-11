package net.minecraft.src;

import java.util.List;

public class EntityAIAvoidEntity extends EntityAIBase
{
    private EntityCreature field_48244_a;
    private float field_48242_b;
    private float field_48243_c;
    private Entity field_48240_d;
    private float field_48241_e;
    private PathEntity field_48238_f;
    private PathNavigate field_48239_g;
    private Class field_48245_h;

    public EntityAIAvoidEntity(EntityCreature par1EntityCreature, Class par2Class, float par3, float par4, float par5)
    {
        field_48244_a = par1EntityCreature;
        field_48245_h = par2Class;
        field_48241_e = par3;
        field_48242_b = par4;
        field_48243_c = par5;
        field_48239_g = par1EntityCreature.func_48084_aL();
        func_46079_a(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (field_48245_h == (net.minecraft.src.EntityPlayer.class))
        {
            if ((field_48244_a instanceof EntityTameable) && ((EntityTameable)field_48244_a).func_48139_F_())
            {
                return false;
            }

            field_48240_d = field_48244_a.worldObj.getClosestPlayerToEntity(field_48244_a, field_48241_e);

            if (field_48240_d == null)
            {
                return false;
            }
        }
        else
        {
            List list = field_48244_a.worldObj.getEntitiesWithinAABB(field_48245_h, field_48244_a.boundingBox.expand(field_48241_e, 3D, field_48241_e));

            if (list.size() == 0)
            {
                return false;
            }

            field_48240_d = (Entity)list.get(0);
        }

        if (!field_48244_a.func_48090_aM().func_48480_a(field_48240_d))
        {
            return false;
        }

        Vec3D vec3d = RandomPositionGenerator.func_48623_b(field_48244_a, 16, 7, Vec3D.createVector(field_48240_d.posX, field_48240_d.posY, field_48240_d.posZ));

        if (vec3d == null)
        {
            return false;
        }

        if (field_48240_d.getDistanceSq(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord) < field_48240_d.getDistanceSqToEntity(field_48244_a))
        {
            return false;
        }

        field_48238_f = field_48239_g.func_48671_a(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);

        if (field_48238_f == null)
        {
            return false;
        }

        return field_48238_f.func_48639_a(vec3d);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !field_48239_g.func_46072_b();
    }

    public void func_46080_e()
    {
        field_48239_g.func_48678_a(field_48238_f, field_48242_b);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        field_48240_d = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        if (field_48244_a.getDistanceSqToEntity(field_48240_d) < 49D)
        {
            field_48244_a.func_48084_aL().func_48660_a(field_48243_c);
        }
        else
        {
            field_48244_a.func_48084_aL().func_48660_a(field_48242_b);
        }
    }
}
