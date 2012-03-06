package net.minecraft.src;

public class EntitySpellParticleFX extends EntityFX
{
    private int field_40111_a;

    public EntitySpellParticleFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        super(par1World, par2, par4, par6, par8, par10, par12);
        field_40111_a = 128;
        motionY *= 0.2D;

        if (par8 == 0.0D && par12 == 0.0D)
        {
            motionX *= 0.1D;
            motionZ *= 0.1D;
        }

        particleScale *= 0.75F;
        particleMaxAge = (int)(8D / (Math.random() * 0.8D + 0.2D));
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

        setParticleTextureIndex(field_40111_a + (7 - (particleAge * 8) / particleMaxAge));
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

    public void func_40110_b(int par1)
    {
        field_40111_a = par1;
    }
}
