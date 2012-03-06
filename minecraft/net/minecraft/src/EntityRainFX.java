package net.minecraft.src;

import java.util.Random;

public class EntityRainFX extends EntityFX
{
    public EntityRainFX(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        motionX *= 0.3D;
        motionY = (float)Math.random() * 0.2F + 0.1F;
        motionZ *= 0.3D;
        particleRed = 1.0F;
        particleGreen = 1.0F;
        particleBlue = 1.0F;
        setParticleTextureIndex(19 + rand.nextInt(4));
        setSize(0.01F, 0.01F);
        particleGravity = 0.06F;
        particleMaxAge = (int)(8D / (Math.random() * 0.8D + 0.2D));
    }

    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        motionY -= particleGravity;
        moveEntity(motionX, motionY, motionZ);
        motionX *= 0.98D;
        motionY *= 0.98D;
        motionZ *= 0.98D;

        if (particleMaxAge-- <= 0)
        {
            setEntityDead();
        }

        if (onGround)
        {
            if (Math.random() < 0.5D)
            {
                setEntityDead();
            }

            motionX *= 0.7D;
            motionZ *= 0.7D;
        }

        Material material = worldObj.getBlockMaterial(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));

        if (material.isLiquid() || material.isSolid())
        {
            double d = (float)(MathHelper.floor_double(posY) + 1) - BlockFluid.getFluidHeightPercent(worldObj.getBlockMetadata(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)));

            if (posY < d)
            {
                setEntityDead();
            }
        }
    }
}
