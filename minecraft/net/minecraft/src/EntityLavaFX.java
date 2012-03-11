package net.minecraft.src;

import java.util.Random;

public class EntityLavaFX extends EntityFX
{
    private float lavaParticleScale;

    public EntityLavaFX(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        motionX *= 0.8D;
        motionY *= 0.8D;
        motionZ *= 0.8D;
        motionY = rand.nextFloat() * 0.4F + 0.05F;
        particleRed = particleGreen = particleBlue = 1.0F;
        particleScale *= rand.nextFloat() * 2.0F + 0.2F;
        lavaParticleScale = particleScale;
        particleMaxAge = (int)(16D / (Math.random() * 0.8D + 0.2D));
        noClip = false;
        setParticleTextureIndex(49);
    }

    public int getEntityBrightnessForRender(float par1)
    {
        float f = ((float)particleAge + par1) / (float)particleMaxAge;

        if (f < 0.0F)
        {
            f = 0.0F;
        }

        if (f > 1.0F)
        {
            f = 1.0F;
        }

        int i = super.getEntityBrightnessForRender(par1);
        char c = '\360';
        int j = i >> 16 & 0xff;
        return c | j << 16;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getEntityBrightness(float par1)
    {
        return 1.0F;
    }

    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        float f = ((float)particleAge + par2) / (float)particleMaxAge;
        particleScale = lavaParticleScale * (1.0F - f * f);
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

        float f = (float)particleAge / (float)particleMaxAge;

        if (rand.nextFloat() > f)
        {
            worldObj.spawnParticle("smoke", posX, posY, posZ, motionX, motionY, motionZ);
        }

        motionY -= 0.03D;
        moveEntity(motionX, motionY, motionZ);
        motionX *= 0.999D;
        motionY *= 0.999D;
        motionZ *= 0.999D;

        if (onGround)
        {
            motionX *= 0.7D;
            motionZ *= 0.7D;
        }
    }
}
