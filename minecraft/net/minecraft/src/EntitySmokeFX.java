package net.minecraft.src;

public class EntitySmokeFX extends EntityFX
{
    float smokeParticleScale;

    public EntitySmokeFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        this(par1World, par2, par4, par6, par8, par10, par12, 1.0F);
    }

    public EntitySmokeFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float par14)
    {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        motionX *= 0.1D;
        motionY *= 0.1D;
        motionZ *= 0.1D;
        motionX += par8;
        motionY += par10;
        motionZ += par12;
        particleRed = particleGreen = particleBlue = (float)(Math.random() * 0.3D);
        particleScale *= 0.75F;
        particleScale *= par14;
        smokeParticleScale = particleScale;
        particleMaxAge = (int)(8D / (Math.random() * 0.8D + 0.2D));
        particleMaxAge *= par14;
        noClip = false;
    }

    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        float f = (((float)particleAge + par2) / (float)particleMaxAge) * 32F;

        if (f < 0.0F)
        {
            f = 0.0F;
        }

        if (f > 1.0F)
        {
            f = 1.0F;
        }

        particleScale = smokeParticleScale * f;
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

        if (posY == prevPosY)
        {
            motionX *= 1.1D;
            motionZ *= 1.1D;
        }

        motionX *= 0.96D;
        motionY *= 0.96D;
        motionZ *= 0.96D;

        if (onGround)
        {
            motionX *= 0.7D;
            motionZ *= 0.7D;
        }
    }
}
