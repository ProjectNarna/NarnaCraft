package net.minecraft.src;

import java.util.Random;

public class EntityAISwimming extends EntityAIBase
{
    private EntityLiving field_46106_a;

    public EntityAISwimming(EntityLiving par1EntityLiving)
    {
        field_46106_a = par1EntityLiving;
        func_46079_a(4);
        par1EntityLiving.func_48084_aL().func_48669_e(true);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return field_46106_a.isInWater() || field_46106_a.handleLavaMovement();
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        if (field_46106_a.getRNG().nextFloat() < 0.8F)
        {
            field_46106_a.getJumpHelper().setJumping();
        }
    }
}
