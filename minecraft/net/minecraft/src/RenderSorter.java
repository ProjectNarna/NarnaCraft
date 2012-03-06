package net.minecraft.src;

import java.util.Comparator;

public class RenderSorter implements Comparator
{
    /** The entity (usually the player) that the camera is inside. */
    private EntityLiving baseEntity;

    public RenderSorter(EntityLiving par1EntityLiving)
    {
        baseEntity = par1EntityLiving;
    }

    public int doCompare(WorldRenderer par1WorldRenderer, WorldRenderer par2WorldRenderer)
    {
        boolean flag = par1WorldRenderer.isInFrustum;
        boolean flag1 = par2WorldRenderer.isInFrustum;

        if (flag && !flag1)
        {
            return 1;
        }

        if (flag1 && !flag)
        {
            return -1;
        }

        double d = par1WorldRenderer.distanceToEntitySquared(baseEntity);
        double d1 = par2WorldRenderer.distanceToEntitySquared(baseEntity);

        if (d < d1)
        {
            return 1;
        }

        if (d > d1)
        {
            return -1;
        }
        else
        {
            return par1WorldRenderer.chunkIndex >= par2WorldRenderer.chunkIndex ? -1 : 1;
        }
    }

    public int compare(Object par1Obj, Object par2Obj)
    {
        return doCompare((WorldRenderer)par1Obj, (WorldRenderer)par2Obj);
    }
}
