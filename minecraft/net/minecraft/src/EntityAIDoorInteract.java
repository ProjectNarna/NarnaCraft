package net.minecraft.src;

public abstract class EntityAIDoorInteract extends EntityAIBase
{
    protected EntityLiving field_48325_a;
    protected int field_48323_b;
    protected int field_48324_c;
    protected int field_48321_d;
    protected BlockDoor field_48322_e;
    boolean field_48319_f;
    float field_48320_g;
    float field_48326_h;

    public EntityAIDoorInteract(EntityLiving par1EntityLiving)
    {
        field_48325_a = par1EntityLiving;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!field_48325_a.isCollidedHorizontally)
        {
            return false;
        }

        PathNavigate pathnavigate = field_48325_a.func_48084_aL();
        PathEntity pathentity = pathnavigate.func_48670_c();

        if (pathentity == null || pathentity.isFinished() || !pathnavigate.func_48665_b())
        {
            return false;
        }

        for (int i = 0; i < Math.min(pathentity.func_48643_e() + 2, pathentity.func_48644_d()); i++)
        {
            PathPoint pathpoint = pathentity.func_48648_a(i);
            field_48323_b = pathpoint.xCoord;
            field_48324_c = pathpoint.yCoord + 1;
            field_48321_d = pathpoint.zCoord;

            if (field_48325_a.getDistanceSq(field_48323_b, field_48325_a.posY, field_48321_d) > 2.25D)
            {
                continue;
            }

            field_48322_e = func_48318_a(field_48323_b, field_48324_c, field_48321_d);

            if (field_48322_e != null)
            {
                return true;
            }
        }

        field_48323_b = MathHelper.floor_double(field_48325_a.posX);
        field_48324_c = MathHelper.floor_double(field_48325_a.posY + 1.0D);
        field_48321_d = MathHelper.floor_double(field_48325_a.posZ);
        field_48322_e = func_48318_a(field_48323_b, field_48324_c, field_48321_d);
        return field_48322_e != null;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !field_48319_f;
    }

    public void func_46080_e()
    {
        field_48319_f = false;
        field_48320_g = (float)((double)((float)field_48323_b + 0.5F) - field_48325_a.posX);
        field_48326_h = (float)((double)((float)field_48321_d + 0.5F) - field_48325_a.posZ);
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        float f = (float)((double)((float)field_48323_b + 0.5F) - field_48325_a.posX);
        float f1 = (float)((double)((float)field_48321_d + 0.5F) - field_48325_a.posZ);
        float f2 = field_48320_g * f + field_48326_h * f1;

        if (f2 < 0.0F)
        {
            field_48319_f = true;
        }
    }

    private BlockDoor func_48318_a(int par1, int par2, int par3)
    {
        int i = field_48325_a.worldObj.getBlockId(par1, par2, par3);

        if (i != Block.doorWood.blockID)
        {
            return null;
        }
        else
        {
            BlockDoor blockdoor = (BlockDoor)Block.blocksList[i];
            return blockdoor;
        }
    }
}
