package net.minecraft.src;

import java.util.Random;

public class EntityAuraFX extends EntityFX
{
    public EntityAuraFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        super(par1World, par2, par4, par6, par8, par10, par12);
        float f = rand.nextFloat() * 0.1F + 0.2F;
        particleRed = f;
        particleGreen = f;
        particleBlue = f;
        setParticleTextureIndex(0);
        setSize(0.02F, 0.02F);
        particleScale = particleScale * (rand.nextFloat() * 0.6F + 0.5F);
        motionX *= 0.02D;
        motionY *= 0.02D;
        motionZ *= 0.02D;
        particleMaxAge = (int)(20D / (Math.random() * 0.8D + 0.2D));
        noClip = true;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        moveEntity(motionX, motionY, motionZ);
        motionX *= 0.99D;
        motionY *= 0.99D;
        motionZ *= 0.99D;

        if (particleMaxAge-- <= 0)
        {
            setEntityDead();
        }
    }
}
