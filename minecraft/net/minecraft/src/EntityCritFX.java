package net.minecraft.src;

public class EntityCritFX extends EntityFX
{
    private boolean field_35136_ay;
    float field_35137_a;

    public EntityCritFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        this(par1World, par2, par4, par6, par8, par10, par12, 1.0F);
    }

    public EntityCritFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float par14)
    {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        field_35136_ay = true;
        motionX *= 0.1D;
        motionY *= 0.1D;
        motionZ *= 0.1D;
        motionX += par8 * 0.4D;
        motionY += par10 * 0.4D;
        motionZ += par12 * 0.4D;
        particleRed = particleGreen = particleBlue = (float)(Math.random() * 0.3D + 0.6D);
        particleScale *= 0.75F;
        particleScale *= par14;
        field_35137_a = particleScale;
        particleMaxAge = (int)(6D / (Math.random() * 0.8D + 0.6D));
        particleMaxAge *= par14;
        noClip = false;
        setParticleTextureIndex(65);
        onUpdate();
    }

    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        if (!field_35136_ay)
        {
            return;
        }

        float f = (((float)particleAge + par2) / (float)particleMaxAge) * 32F;

        if (f < 0.0F)
        {
            f = 0.0F;
        }

        if (f > 1.0F)
        {
            f = 1.0F;
        }

        particleScale = field_35137_a * f;
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

        moveEntity(motionX, motionY, motionZ);
        particleGreen *= 0.96D;
        particleBlue *= 0.9D;
        motionX *= 0.7D;
        motionY *= 0.7D;
        motionZ *= 0.7D;
        motionY -= 0.02D;

        if (onGround)
        {
            motionX *= 0.7D;
            motionZ *= 0.7D;
        }
    }
}
