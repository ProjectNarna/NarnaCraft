package net.minecraft.src;

import org.lwjgl.opengl.*;

public class OpenGlHelper
{
    /** An OpenGL constant specifying the disabled lightmap texture */
    public static int lightmapDisabled;

    /** An OpenGL constant specifying the enabled lightmap texture */
    public static int lightmapEnabled;

    /**
     * True if the renderer supports multitextures and the OpenGL version != 1.3
     */
    private static boolean useMultitextureARB = false;

    public OpenGlHelper()
    {
    }

    /**
     * Initializes the texture constants to be used when rendering lightmap values
     */
    public static void initializeTextures()
    {
        useMultitextureARB = GLContext.getCapabilities().GL_ARB_multitexture && !GLContext.getCapabilities().OpenGL13;

        if (useMultitextureARB)
        {
            lightmapDisabled = 33984;
            lightmapEnabled = 33985;
        }
        else
        {
            lightmapDisabled = 33984;
            lightmapEnabled = 33985;
        }
    }

    /**
     * Sets the current lightmap texture to the specified OpenGL constant
     */
    public static void setActiveTexture(int par0)
    {
        if (useMultitextureARB)
        {
            ARBMultitexture.glActiveTextureARB(par0);
        }
        else
        {
            GL13.glActiveTexture(par0);
        }
    }

    /**
     * Sets the current lightmap texture to the specified OpenGL constant
     */
    public static void setClientActiveTexture(int par0)
    {
        if (useMultitextureARB)
        {
            ARBMultitexture.glClientActiveTextureARB(par0);
        }
        else
        {
            GL13.glClientActiveTexture(par0);
        }
    }

    /**
     * Sets the current coordinates of the given lightmap texture
     */
    public static void setLightmapTextureCoords(int par0, float par1, float par2)
    {
        if (useMultitextureARB)
        {
            ARBMultitexture.glMultiTexCoord2fARB(par0, par1, par2);
        }
        else
        {
            GL13.glMultiTexCoord2f(par0, par1, par2);
        }
    }
}
