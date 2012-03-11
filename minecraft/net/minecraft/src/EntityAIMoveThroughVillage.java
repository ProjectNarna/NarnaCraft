package net.minecraft.src;

import java.util.*;

public class EntityAIMoveThroughVillage extends EntityAIBase
{
    private EntityCreature field_48292_a;
    private float field_48290_b;
    private PathEntity field_48291_c;
    private VillageDoorInfo field_48288_d;
    private boolean field_48289_e;
    private List field_48287_f;

    public EntityAIMoveThroughVillage(EntityCreature par1EntityCreature, float par2, boolean par3)
    {
        field_48287_f = new ArrayList();
        field_48292_a = par1EntityCreature;
        field_48290_b = par2;
        field_48289_e = par3;
        func_46079_a(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        func_48286_h();

        if (field_48289_e && field_48292_a.worldObj.isDaytime())
        {
            return false;
        }

        Village village = field_48292_a.worldObj.field_48465_A.func_48564_a(MathHelper.floor_double(field_48292_a.posX), MathHelper.floor_double(field_48292_a.posY), MathHelper.floor_double(field_48292_a.posZ), 0);

        if (village == null)
        {
            return false;
        }

        field_48288_d = func_48284_a(village);

        if (field_48288_d == null)
        {
            return false;
        }

        boolean flag = field_48292_a.func_48084_aL().func_48665_b();
        field_48292_a.func_48084_aL().func_48673_b(false);
        field_48291_c = field_48292_a.func_48084_aL().func_48671_a(field_48288_d.field_48600_a, field_48288_d.field_48598_b, field_48288_d.field_48599_c);
        field_48292_a.func_48084_aL().func_48673_b(flag);

        if (field_48291_c != null)
        {
            return true;
        }

        Vec3D vec3d = RandomPositionGenerator.func_48620_a(field_48292_a, 10, 7, Vec3D.createVector(field_48288_d.field_48600_a, field_48288_d.field_48598_b, field_48288_d.field_48599_c));

        if (vec3d == null)
        {
            return false;
        }
        else
        {
            field_48292_a.func_48084_aL().func_48673_b(false);
            field_48291_c = field_48292_a.func_48084_aL().func_48671_a(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
            field_48292_a.func_48084_aL().func_48673_b(flag);
            return field_48291_c != null;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        if (field_48292_a.func_48084_aL().func_46072_b())
        {
            return false;
        }
        else
        {
            float f = field_48292_a.width + 4F;
            return field_48292_a.getDistanceSq(field_48288_d.field_48600_a, field_48288_d.field_48598_b, field_48288_d.field_48599_c) > (double)(f * f);
        }
    }

    public void func_46080_e()
    {
        field_48292_a.func_48084_aL().func_48678_a(field_48291_c, field_48290_b);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        if (field_48292_a.func_48084_aL().func_46072_b() || field_48292_a.getDistanceSq(field_48288_d.field_48600_a, field_48288_d.field_48598_b, field_48288_d.field_48599_c) < 16D)
        {
            field_48287_f.add(field_48288_d);
        }
    }

    private VillageDoorInfo func_48284_a(Village par1Village)
    {
        VillageDoorInfo villagedoorinfo = null;
        int i = 0x7fffffff;
        List list = par1Village.func_48521_f();
        Iterator iterator = list.iterator();

        do
        {
            if (!iterator.hasNext())
            {
                break;
            }

            VillageDoorInfo villagedoorinfo1 = (VillageDoorInfo)iterator.next();
            int j = villagedoorinfo1.func_48588_a(MathHelper.floor_double(field_48292_a.posX), MathHelper.floor_double(field_48292_a.posY), MathHelper.floor_double(field_48292_a.posZ));

            if (j < i && !func_48285_a(villagedoorinfo1))
            {
                villagedoorinfo = villagedoorinfo1;
                i = j;
            }
        }
        while (true);

        return villagedoorinfo;
    }

    private boolean func_48285_a(VillageDoorInfo par1VillageDoorInfo)
    {
        for (Iterator iterator = field_48287_f.iterator(); iterator.hasNext();)
        {
            VillageDoorInfo villagedoorinfo = (VillageDoorInfo)iterator.next();

            if (par1VillageDoorInfo.field_48600_a == villagedoorinfo.field_48600_a && par1VillageDoorInfo.field_48598_b == villagedoorinfo.field_48598_b && par1VillageDoorInfo.field_48599_c == villagedoorinfo.field_48599_c)
            {
                return true;
            }
        }

        return false;
    }

    private void func_48286_h()
    {
        if (field_48287_f.size() > 15)
        {
            field_48287_f.remove(0);
        }
    }
}
