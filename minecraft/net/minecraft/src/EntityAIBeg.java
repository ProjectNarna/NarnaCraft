package net.minecraft.src;

import java.util.Random;

public class EntityAIBeg extends EntityAIBase
{
    private EntityWolf field_48350_a;
    private EntityPlayer field_48348_b;
    private World field_48349_c;
    private float field_48346_d;
    private int field_48347_e;

    public EntityAIBeg(EntityWolf par1EntityWolf, float par2)
    {
        field_48350_a = par1EntityWolf;
        field_48349_c = par1EntityWolf.worldObj;
        field_48346_d = par2;
        func_46079_a(2);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        field_48348_b = field_48349_c.getClosestPlayerToEntity(field_48350_a, field_48346_d);

        if (field_48348_b == null)
        {
            return false;
        }
        else
        {
            return func_48345_a(field_48348_b);
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        if (!field_48348_b.isEntityAlive())
        {
            return false;
        }

        if (field_48350_a.getDistanceSqToEntity(field_48348_b) > (double)(field_48346_d * field_48346_d))
        {
            return false;
        }
        else
        {
            return field_48347_e > 0 && func_48345_a(field_48348_b);
        }
    }

    public void func_46080_e()
    {
        field_48350_a.func_48150_h(true);
        field_48347_e = 40 + field_48350_a.getRNG().nextInt(40);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        field_48350_a.func_48150_h(false);
        field_48348_b = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        field_48350_a.getLookHelper().setLookPosition(field_48348_b.posX, field_48348_b.posY + (double)field_48348_b.getEyeHeight(), field_48348_b.posZ, 10F, field_48350_a.getVerticalFaceSpeed());
        field_48347_e--;
    }

    private boolean func_48345_a(EntityPlayer par1EntityPlayer)
    {
        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

        if (itemstack == null)
        {
            return false;
        }

        if (!field_48350_a.func_48139_F_() && itemstack.itemID == Item.bone.shiftedIndex)
        {
            return true;
        }
        else
        {
            return field_48350_a.isWheat(itemstack);
        }
    }
}
