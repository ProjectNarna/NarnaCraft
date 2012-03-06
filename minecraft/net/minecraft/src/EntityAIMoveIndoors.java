package net.minecraft.src;

import java.util.Random;

public class EntityAIMoveIndoors extends EntityAIBase
{
    private EntityCreature field_48256_a;
    private VillageDoorInfo field_48254_b;
    private int field_48255_c;
    private int field_48253_d;

    public EntityAIMoveIndoors(EntityCreature par1EntityCreature)
    {
        field_48255_c = -1;
        field_48253_d = -1;
        field_48256_a = par1EntityCreature;
        func_46079_a(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (field_48256_a.worldObj.isDaytime() && !field_48256_a.worldObj.isRaining() || field_48256_a.worldObj.worldProvider.hasNoSky)
        {
            return false;
        }

        if (field_48256_a.getRNG().nextInt(50) != 0)
        {
            return false;
        }

        if (field_48255_c != -1 && field_48256_a.getDistanceSq(field_48255_c, field_48256_a.posY, field_48253_d) < 4D)
        {
            return false;
        }

        Village village = field_48256_a.worldObj.field_48465_A.func_48564_a(MathHelper.floor_double(field_48256_a.posX), MathHelper.floor_double(field_48256_a.posY), MathHelper.floor_double(field_48256_a.posZ), 14);

        if (village == null)
        {
            return false;
        }
        else
        {
            field_48254_b = village.func_48540_c(MathHelper.floor_double(field_48256_a.posX), MathHelper.floor_double(field_48256_a.posY), MathHelper.floor_double(field_48256_a.posZ));
            return field_48254_b != null;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !field_48256_a.func_48084_aL().func_46072_b();
    }

    public void func_46080_e()
    {
        field_48255_c = -1;

        if (field_48256_a.getDistanceSq(field_48254_b.func_48590_a(), field_48254_b.field_48598_b, field_48254_b.func_48591_c()) > 256D)
        {
            Vec3D vec3d = RandomPositionGenerator.func_48620_a(field_48256_a, 14, 3, Vec3D.createVector((double)field_48254_b.func_48590_a() + 0.5D, field_48254_b.func_48592_b(), (double)field_48254_b.func_48591_c() + 0.5D));

            if (vec3d != null)
            {
                field_48256_a.func_48084_aL().func_48666_a(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord, 0.3F);
            }
        }
        else
        {
            field_48256_a.func_48084_aL().func_48666_a((double)field_48254_b.func_48590_a() + 0.5D, field_48254_b.func_48592_b(), (double)field_48254_b.func_48591_c() + 0.5D, 0.3F);
        }
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        field_48255_c = field_48254_b.func_48590_a();
        field_48253_d = field_48254_b.func_48591_c();
        field_48254_b = null;
    }
}
