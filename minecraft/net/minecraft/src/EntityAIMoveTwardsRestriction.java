package net.minecraft.src;

public class EntityAIMoveTwardsRestriction extends EntityAIBase
{
    private EntityCreature field_48355_a;
    private double field_48353_b;
    private double field_48354_c;
    private double field_48351_d;
    private float field_48352_e;

    public EntityAIMoveTwardsRestriction(EntityCreature par1EntityCreature, float par2)
    {
        field_48355_a = par1EntityCreature;
        field_48352_e = par2;
        func_46079_a(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (field_48355_a.func_48093_aT())
        {
            return false;
        }

        ChunkCoordinates chunkcoordinates = field_48355_a.func_48091_aU();
        Vec3D vec3d = RandomPositionGenerator.func_48620_a(field_48355_a, 16, 7, Vec3D.createVector(chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ));

        if (vec3d == null)
        {
            return false;
        }
        else
        {
            field_48353_b = vec3d.xCoord;
            field_48354_c = vec3d.yCoord;
            field_48351_d = vec3d.zCoord;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !field_48355_a.func_48084_aL().func_46072_b();
    }

    public void func_46080_e()
    {
        field_48355_a.func_48084_aL().func_48666_a(field_48353_b, field_48354_c, field_48351_d, field_48352_e);
    }
}
