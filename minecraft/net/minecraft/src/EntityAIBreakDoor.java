package net.minecraft.src;

import java.util.Random;

public class EntityAIBreakDoor extends EntityAIDoorInteract
{
    private int field_48329_i;

    public EntityAIBreakDoor(EntityLiving par1EntityLiving)
    {
        super(par1EntityLiving);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!super.shouldExecute())
        {
            return false;
        }
        else
        {
            return !field_48322_e.func_48213_h(field_48325_a.worldObj, field_48323_b, field_48324_c, field_48321_d);
        }
    }

    public void func_46080_e()
    {
        super.func_46080_e();
        field_48329_i = 240;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        double d = field_48325_a.getDistanceSq(field_48323_b, field_48324_c, field_48321_d);
        return field_48329_i >= 0 && !field_48322_e.func_48213_h(field_48325_a.worldObj, field_48323_b, field_48324_c, field_48321_d) && d < 4D;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        super.updateTask();

        if (field_48325_a.getRNG().nextInt(20) == 0)
        {
            field_48325_a.worldObj.playAuxSFX(1010, field_48323_b, field_48324_c, field_48321_d, 0);
        }

        if (--field_48329_i == 0 && field_48325_a.worldObj.difficultySetting == 3)
        {
            field_48325_a.worldObj.setBlockWithNotify(field_48323_b, field_48324_c, field_48321_d, 0);
            field_48325_a.worldObj.playAuxSFX(1012, field_48323_b, field_48324_c, field_48321_d, 0);
            field_48325_a.worldObj.playAuxSFX(2001, field_48323_b, field_48324_c, field_48321_d, field_48322_e.blockID);
        }
    }
}
