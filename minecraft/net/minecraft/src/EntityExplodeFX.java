package net.minecraft.src;

import java.util.Random;

public class EntityExplodeFX extends EntityFX
{
    public EntityExplodeFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        super(par1World, par2, par4, par6, par8, par10, par12);
        motionX = par8 + (double)((float)(Math.random() * 2D - 1.0D) * 0.05F);
        motionY = par10 + (double)((float)(Math.random() * 2D - 1.0D) * 0.05F);
        motionZ = par12 + (double)((float)(Math.random() * 2D - 1.0D) * 0.05F);
        particleRed = particleGreen = particleBlue = rand.nextFloat() * 0.3F + 0.7F;
        particleScale = rand.nextFloat() * rand.nextFloat() * 6F + 1.0F;
        particleMaxAge = (int)(16D / ((double)rand.nextFloat() * 0.8D + 0.2D)) + 2;
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

        if (particleAge++ >= particleMaxAge)
        {
            setEntityDead();
        }

        setParticleTextureIndex(7 - (particleAge * 8) / particleMaxAge);
        motionY += 0.004D;
        moveEntity(motionX, motionY, motionZ);
        motionX *= 0.9D;
        motionY *= 0.9D;
        motionZ *= 0.9D;

        if (onGround)
        {
            motionX *= 0.7D;
            motionZ *= 0.7D;
        }
    }
}
