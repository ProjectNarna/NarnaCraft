package net.minecraft.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class GuiWinGame extends GuiScreen
{
    /** Counts the number of screen updates. */
    private int updateCounter;
    private List field_41044_b;
    private int field_41045_c[];
    private int field_41042_d;
    private float field_41043_e;

    public GuiWinGame()
    {
        updateCounter = 0;
        field_41042_d = 0;
        field_41043_e = 0.5F;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        updateCounter++;
        float f = (float)(field_41042_d + height + height + 24) / field_41043_e;

        if ((float)updateCounter > f)
        {
            func_41041_e();
        }
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
        if (par2 == 1)
        {
            func_41041_e();
        }
    }

    private void func_41041_e()
    {
        if (mc.theWorld.isRemote)
        {
            EntityClientPlayerMP entityclientplayermp = (EntityClientPlayerMP)mc.thePlayer;
            entityclientplayermp.sendInventoryChanged();
            entityclientplayermp.sendQueue.addToSendQueue(new Packet9Respawn(entityclientplayermp.dimension, (byte)mc.theWorld.difficultySetting, mc.theWorld.getWorldInfo().getTerrainType(), mc.theWorld.func_48453_b(), 0));
        }
        else
        {
            mc.displayGuiScreen(null);
            mc.respawn(mc.theWorld.isRemote, 0, true);
        }
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return true;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        if (field_41044_b != null)
        {
            return;
        }

        field_41044_b = new ArrayList();

        try
        {
            String s = "";
            String s1 = "\247f\247k\247a\247b";
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader((net.minecraft.src.GuiWinGame.class).getResourceAsStream("/title/win.txt"), Charset.forName("UTF-8")));
            Random random = new Random(0x7bf7d3L);

            while ((s = bufferedreader.readLine()) != null)
            {
                String s2;
                String s3;

                for (s = s.replaceAll("PLAYERNAME", mc.session.username); s.indexOf(s1) >= 0; s = (new StringBuilder()).append(s2).append("\247f\247k").append("XXXXXXXX".substring(0, random.nextInt(4) + 3)).append(s3).toString())
                {
                    int i = s.indexOf(s1);
                    s2 = s.substring(0, i);
                    s3 = s.substring(i + s1.length());
                }

                if (s.length() == 0)
                {
                    field_41044_b.add(s);
                }

                field_41044_b.add(s);
            }

            for (int j = 0; j < 16; j++)
            {
                field_41044_b.add("");
            }

            bufferedreader = new BufferedReader(new InputStreamReader((net.minecraft.src.GuiWinGame.class).getResourceAsStream("/title/credits.txt"), Charset.forName("UTF-8")));

            while ((s = bufferedreader.readLine()) != null)
            {
                s = s.replaceAll("PLAYERNAME", mc.session.username);
                s = s.replaceAll("\t", "    ");

                if (s.length() == 0)
                {
                    field_41044_b.add(s);
                }

                field_41044_b.add(s);
            }

            field_41045_c = new int[field_41044_b.size()];
            char c = 274;
            fontRenderer.FONT_HEIGHT = 12;

            for (int k = 0; k < field_41044_b.size(); k++)
            {
                field_41042_d += field_41045_c[k] = fontRenderer.splitStringWidth((String)field_41044_b.get(k), c);
            }

            fontRenderer.FONT_HEIGHT = 8;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton guibutton)
    {
    }

    private void func_41040_b(int par1, int par2, float par3)
    {
        Tessellator tessellator = Tessellator.instance;
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("%blur%/gui/background.png"));
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        int i = width;
        float f = 0.0F - ((float)updateCounter + par3) * 0.5F * field_41043_e;
        float f1 = (float)height - ((float)updateCounter + par3) * 0.5F * field_41043_e;
        float f2 = 0.015625F;
        float f3 = (((float)updateCounter + par3) - 0.0F) * 0.02F;
        float f4 = (float)(field_41042_d + height + height + 24) / field_41043_e;
        float f5 = (f4 - 20F - ((float)updateCounter + par3)) * 0.005F;

        if (f5 < f3)
        {
            f3 = f5;
        }

        if (f3 > 1.0F)
        {
            f3 = 1.0F;
        }

        f3 *= f3;
        f3 = (f3 * 96F) / 255F;
        tessellator.setColorOpaque_F(f3, f3, f3);
        tessellator.addVertexWithUV(0.0D, height, zLevel, 0.0D, f * f2);
        tessellator.addVertexWithUV(i, height, zLevel, (float)i * f2, f * f2);
        tessellator.addVertexWithUV(i, 0.0D, zLevel, (float)i * f2, f1 * f2);
        tessellator.addVertexWithUV(0.0D, 0.0D, zLevel, 0.0D, f1 * f2);
        tessellator.draw();
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        func_41040_b(par1, par2, par3);
        Tessellator tessellator = Tessellator.instance;
        char c = 274;
        int i = width / 2 - c / 2;
        int j = height + 50;
        float f = -((float)updateCounter + par3) * field_41043_e;
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, f, 0.0F);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/title/mclogo.png"));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(i + 0, j + 0, 0, 0, 155, 44);
        drawTexturedModalRect(i + 155, j + 0, 0, 45, 155, 44);
        tessellator.setColorOpaque_I(0xffffff);
        fontRenderer.FONT_HEIGHT = 12;
        int k = j + 200;

        for (int l = 0; l < field_41044_b.size(); l++)
        {
            int j1 = field_41045_c[l];

            if (l == field_41044_b.size() - 1)
            {
                float f1 = ((float)k + f) - (float)(height / 2 - j1 / 2);

                if (f1 < 0.0F)
                {
                    GL11.glTranslatef(0.0F, -f1, 0.0F);
                }
            }

            if ((float)k + f + (float)j1 + 8F > 0.0F && (float)k + f < (float)height)
            {
                String s = (String)field_41044_b.get(l);

                if (s.startsWith("[C]"))
                {
                    fontRenderer.drawStringWithShadow(s.substring(3), i + (c - fontRenderer.getStringWidth(s.substring(3))) / 2, k, 0xffffff);
                }
                else
                {
                    fontRenderer.fontRandom.setSeed((long)l * 0xfca99533L + (long)(updateCounter / 4));
                    fontRenderer.drawSplitString(s, i + 1, k + 1, c, 0xffffff, true);
                    fontRenderer.fontRandom.setSeed((long)l * 0xfca99533L + (long)(updateCounter / 4));
                    fontRenderer.drawSplitString(s, i, k, c, 0xffffff, false);
                }
            }

            k += j1;
        }

        fontRenderer.FONT_HEIGHT = 8;
        GL11.glPopMatrix();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("%blur%/misc/vignette.png"));
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE_MINUS_SRC_COLOR);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        int i1 = width;
        int k1 = height;
        tessellator.addVertexWithUV(0.0D, k1, zLevel, 0.0D, 1.0D);
        tessellator.addVertexWithUV(i1, k1, zLevel, 1.0D, 1.0D);
        tessellator.addVertexWithUV(i1, 0.0D, zLevel, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, zLevel, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDisable(GL11.GL_BLEND);
        super.drawScreen(par1, par2, par3);
    }
}
