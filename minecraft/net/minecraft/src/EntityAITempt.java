package net.minecraft.src;

public class EntityAITempt extends EntityAIBase
{
    private EntityCreature field_48277_a;
    private float field_48275_b;
    private double field_48276_c;
    private double field_48273_d;
    private double field_48274_e;
    private double field_48271_f;
    private double field_48272_g;
    private EntityPlayer field_48282_h;
    private int field_48283_i;
    private boolean field_48280_j;
    private int field_48281_k;
    private boolean field_48278_l;
    private boolean field_48279_m;

    public EntityAITempt(EntityCreature par1EntityCreature, float par2, int par3, boolean par4)
    {
        field_48283_i = 0;
        field_48277_a = par1EntityCreature;
        field_48275_b = par2;
        field_48281_k = par3;
        field_48278_l = par4;
        func_46079_a(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (field_48283_i > 0)
        {
            field_48283_i--;
            return false;
        }

        field_48282_h = field_48277_a.worldObj.getClosestPlayerToEntity(field_48277_a, 10D);

        if (field_48282_h == null)
        {
            return false;
        }

        ItemStack itemstack = field_48282_h.getCurrentEquippedItem();

        if (itemstack == null)
        {
            return false;
        }

        return itemstack.itemID == field_48281_k;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        if (field_48278_l)
        {
            if (field_48277_a.getDistanceSqToEntity(field_48282_h) < 36D)
            {
                if (field_48282_h.getDistanceSq(field_48276_c, field_48273_d, field_48274_e) > 0.01D)
                {
                    return false;
                }

                if (Math.abs((double)field_48282_h.rotationPitch - field_48271_f) > 5D || Math.abs((double)field_48282_h.rotationYaw - field_48272_g) > 5D)
                {
                    return false;
                }
            }
            else
            {
                field_48276_c = field_48282_h.posX;
                field_48273_d = field_48282_h.posY;
                field_48274_e = field_48282_h.posZ;
            }

            field_48271_f = field_48282_h.rotationPitch;
            field_48272_g = field_48282_h.rotationYaw;
        }

        return shouldExecute();
    }

    public void func_46080_e()
    {
        field_48276_c = field_48282_h.posX;
        field_48273_d = field_48282_h.posY;
        field_48274_e = field_48282_h.posZ;
        field_48280_j = true;
        field_48279_m = field_48277_a.func_48084_aL().func_48658_a();
        field_48277_a.func_48084_aL().func_48664_a(false);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        field_48282_h = null;
        field_48277_a.func_48084_aL().func_48672_f();
        field_48283_i = 100;
        field_48280_j = false;
        field_48277_a.func_48084_aL().func_48664_a(field_48279_m);
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        field_48277_a.getLookHelper().setLookPositionWithEntity(field_48282_h, 30F, field_48277_a.getVerticalFaceSpeed());

        if (field_48277_a.getDistanceSqToEntity(field_48282_h) < 6.25D)
        {
            field_48277_a.func_48084_aL().func_48672_f();
        }
        else
        {
            field_48277_a.func_48084_aL().func_48667_a(field_48282_h, field_48275_b);
        }
    }

    public boolean func_48270_h()
    {
        return field_48280_j;
    }
}
