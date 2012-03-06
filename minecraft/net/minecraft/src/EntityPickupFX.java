package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class EntityPickupFX extends EntityFX
{
    private Entity field_675_a;
    private Entity field_679_o;
    private int age;
    private int maxAge;
    private float field_676_r;

    public EntityPickupFX(World par1World, Entity par2Entity, Entity par3Entity, float par4)
    {
        super(par1World, par2Entity.posX, par2Entity.posY, par2Entity.posZ, par2Entity.motionX, par2Entity.motionY, par2Entity.motionZ);
        age = 0;
        maxAge = 0;
        field_675_a = par2Entity;
        field_679_o = par3Entity;
        maxAge = 3;
        field_676_r = par4;
    }

    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        float f = ((float)age + par2) / (float)maxAge;
        f *= f;
        double d = field_675_a.posX;
        double d1 = field_675_a.posY;
        double d2 = field_675_a.posZ;
        double d3 = field_679_o.lastTickPosX + (field_679_o.posX - field_679_o.lastTickPosX) * (double)par2;
        double d4 = field_679_o.lastTickPosY + (field_679_o.posY - field_679_o.lastTickPosY) * (double)par2 + (double)field_676_r;
        double d5 = field_679_o.lastTickPosZ + (field_679_o.posZ - field_679_o.lastTickPosZ) * (double)par2;
        double d6 = d + (d3 - d) * (double)f;
        double d7 = d1 + (d4 - d1) * (double)f;
        double d8 = d2 + (d5 - d2) * (double)f;
        int i = MathHelper.floor_double(d6);
        int j = MathHelper.floor_double(d7 + (double)(yOffset / 2.0F));
        int k = MathHelper.floor_double(d8);
        int l = getEntityBrightnessForRender(par2);
        int i1 = l % 0x10000;
        int j1 = l / 0x10000;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapEnabled, (float)i1 / 1.0F, (float)j1 / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        d6 -= interpPosX;
        d7 -= interpPosY;
        d8 -= interpPosZ;
        RenderManager.instance.renderEntityWithPosYaw(field_675_a, (float)d6, (float)d7, (float)d8, field_675_a.rotationYaw, par2);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        age++;

        if (age == maxAge)
        {
            setEntityDead();
        }
    }

    public int getFXLayer()
    {
        return 3;
    }
}
