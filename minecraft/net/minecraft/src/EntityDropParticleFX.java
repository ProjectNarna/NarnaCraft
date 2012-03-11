package net.minecraft.src;

public class EntityDropParticleFX extends EntityFX
{
    private Material field_40103_a;
    private int field_40104_aw;

    public EntityDropParticleFX(World par1World, double par2, double par4, double par6, Material par8Material)
    {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        motionX = motionY = motionZ = 0.0D;

        if (par8Material == Material.water)
        {
            particleRed = 0.0F;
            particleGreen = 0.0F;
            particleBlue = 1.0F;
        }
        else
        {
            particleRed = 1.0F;
            particleGreen = 0.0F;
            particleBlue = 0.0F;
        }

        setParticleTextureIndex(113);
        setSize(0.01F, 0.01F);
        particleGravity = 0.06F;
        field_40103_a = par8Material;
        field_40104_aw = 40;
        particleMaxAge = (int)(64D / (Math.random() * 0.8D + 0.2D));
        motionX = motionY = motionZ = 0.0D;
    }

    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
    }

    public int getEntityBrightnessForRender(float par1)
    {
        if (field_40103_a == Material.water)
        {
            return super.getEntityBrightnessForRender(par1);
        }
        else
        {
            return 257;
        }
    }

    /**
     * Gets how bright this entity is.
     */
    public float getEntityBrightness(float par1)
    {
        if (field_40103_a == Material.water)
        {
            return super.getEntityBrightness(par1);
        }
        else
        {
            return 1.0F;
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        if (field_40103_a == Material.water)
        {
            particleRed = 0.2F;
            particleGreen = 0.3F;
            particleBlue = 1.0F;
        }
        else
        {
            particleRed = 1.0F;
            particleGreen = 16F / (float)((40 - field_40104_aw) + 16);
            particleBlue = 4F / (float)((40 - field_40104_aw) + 8);
        }

        motionY -= particleGravity;

        if (field_40104_aw-- > 0)
        {
            motionX *= 0.02D;
            motionY *= 0.02D;
            motionZ *= 0.02D;
            setParticleTextureIndex(113);
        }
        else
        {
            setParticleTextureIndex(112);
        }

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
            if (field_40103_a == Material.water)
            {
                setEntityDead();
                worldObj.spawnParticle("splash", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
            }
            else
            {
                setParticleTextureIndex(114);
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
