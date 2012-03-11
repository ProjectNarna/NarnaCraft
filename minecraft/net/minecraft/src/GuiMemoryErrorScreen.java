package net.minecraft.src;

public class GuiMemoryErrorScreen extends GuiScreen
{
    private int field_28098_a;

    public GuiMemoryErrorScreen()
    {
        field_28098_a = 0;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        field_28098_a++;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton guibutton)
    {
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char c, int i)
    {
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, "Out of memory!", width / 2, (height / 4 - 60) + 20, 0xffffff);
        drawString(fontRenderer, "Minecraft has run out of memory.", width / 2 - 140, (height / 4 - 60) + 60 + 0, 0xa0a0a0);
        drawString(fontRenderer, "This could be caused by a bug in the game or by the", width / 2 - 140, (height / 4 - 60) + 60 + 18, 0xa0a0a0);
        drawString(fontRenderer, "Java Virtual Machine not being allocated enough", width / 2 - 140, (height / 4 - 60) + 60 + 27, 0xa0a0a0);
        drawString(fontRenderer, "memory. If you are playing in a web browser, try", width / 2 - 140, (height / 4 - 60) + 60 + 36, 0xa0a0a0);
        drawString(fontRenderer, "downloading the game and playing it offline.", width / 2 - 140, (height / 4 - 60) + 60 + 45, 0xa0a0a0);
        drawString(fontRenderer, "To prevent level corruption, the current game has quit.", width / 2 - 140, (height / 4 - 60) + 60 + 63, 0xa0a0a0);
        drawString(fontRenderer, "Please restart the game.", width / 2 - 140, (height / 4 - 60) + 60 + 81, 0xa0a0a0);
        super.drawScreen(par1, par2, par3);
    }
}
