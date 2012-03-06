package net.minecraft.src;

import java.util.Random;

public abstract class WorldGenerator
{
    /**
     * Sets wither or not the generator should notify blocks of blocks it changes. When the world is first generated,
     * this is false, when saplings grow, this is true.
     */
    private final boolean doBlockNotify;

    public WorldGenerator()
    {
        doBlockNotify = false;
    }

    public WorldGenerator(boolean par1)
    {
        doBlockNotify = par1;
    }

    public abstract boolean generate(World world, Random random, int i, int j, int k);

    /**
     * Rescales the generator settings, only used in WorldGenBigTree
     */
    public void setScale(double d, double d1, double d2)
    {
    }

    /**
     * Sets the block in the world, notifying neighbors if enabled.
     */
    protected void setBlockAndMetadata(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        if (doBlockNotify)
        {
            par1World.setBlockAndMetadataWithNotify(par2, par3, par4, par5, par6);
        }
        else
        {
            par1World.setBlockAndMetadata(par2, par3, par4, par5, par6);
        }
    }
}
