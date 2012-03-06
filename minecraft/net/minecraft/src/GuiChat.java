package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class GuiChat extends GuiScreen
{
    /** The chat message. */
    protected String message;

    /** Counts the number of screen updates. Used to make the caret flash. */
    private int updateCounter;

    public GuiChat()
    {
        message = "";
        updateCounter = 0;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        updateCounter++;
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
        if (par2 == 1)
        {
            mc.displayGuiScreen(null);
            return;
        }

        if (par2 == 28)
        {
            String s = message.trim();

            if (s.length() > 0)
            {
                String s1 = message.trim();

                if (!mc.lineIsCommand(s1))
                {
                    mc.thePlayer.sendChatMessage(s1);
                }
            }

            mc.displayGuiScreen(null);
            return;
        }

        if (par2 == 14 && message.length() > 0)
        {
            message = message.substring(0, message.length() - 1);
        }

        if (!(!ChatAllowedCharacters.func_48614_a(par1) || message.length() >= 100))
        {
            message += par1;
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        drawRect(2, height - 14, width - 2, height - 2, 0x80000000);
        drawString(fontRenderer, (new StringBuilder()).append("> ").append(message).append((updateCounter / 6) % 2 != 0 ? "" : "_").toString(), 4, height - 12, 0xe0e0e0);
        super.drawScreen(par1, par2, par3);
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int par1, int par2, int par3)
    {
        if (par3 != 0)
        {
            return;
        }

        if (mc.ingameGUI.field_933_a == null)
        {
            super.mouseClicked(par1, par2, par3);
            return;
        }

        if (!(message.length() <= 0 || message.endsWith(" ")))
        {
            message += " ";
        }

        message += mc.ingameGUI.field_933_a;
        byte byte0 = 100;

        if (message.length() > byte0)
        {
            message = message.substring(0, byte0);
        }
    }
}
