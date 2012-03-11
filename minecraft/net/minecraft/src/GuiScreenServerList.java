package net.minecraft.src;

import java.util.List;
import org.lwjgl.input.Keyboard;

public class GuiScreenServerList extends GuiScreen
{
    /** Needed a change as a local variable was conflicting on construct */
    private GuiScreen guiScreen;
    private GuiTextField serverTextField;
    private ServerNBTStorage field_35318_c;

    public GuiScreenServerList(GuiScreen par1GuiScreen, ServerNBTStorage par2ServerNBTStorage)
    {
        guiScreen = par1GuiScreen;
        field_35318_c = par2ServerNBTStorage;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        serverTextField.updateCursorCounter();
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        Keyboard.enableRepeatEvents(true);
        controlList.clear();
        controlList.add(new GuiButton(0, width / 2 - 100, height / 4 + 96 + 12, stringtranslate.translateKey("selectServer.select")));
        controlList.add(new GuiButton(1, width / 2 - 100, height / 4 + 120 + 12, stringtranslate.translateKey("gui.cancel")));
        serverTextField = new GuiTextField(this, fontRenderer, width / 2 - 100, 116, 200, 20, field_35318_c.host);
        serverTextField.setMaxStringLength(128);
        ((GuiButton)controlList.get(0)).enabled = serverTextField.getText().length() > 0;
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (!par1GuiButton.enabled)
        {
            return;
        }

        if (par1GuiButton.id == 1)
        {
            guiScreen.deleteWorld(false, 0);
        }
        else if (par1GuiButton.id == 0)
        {
            field_35318_c.host = serverTextField.getText();
            guiScreen.deleteWorld(true, 0);
        }
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
        serverTextField.textboxKeyTyped(par1, par2);

        if (par1 == '\r')
        {
            actionPerformed((GuiButton)controlList.get(0));
        }

        ((GuiButton)controlList.get(0)).enabled = serverTextField.getText().length() > 0;
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        serverTextField.mouseClicked(par1, par2, par3);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        drawDefaultBackground();
        drawCenteredString(fontRenderer, stringtranslate.translateKey("selectServer.direct"), width / 2, (height / 4 - 60) + 20, 0xffffff);
        drawString(fontRenderer, stringtranslate.translateKey("addServer.enterIp"), width / 2 - 100, 100, 0xa0a0a0);
        serverTextField.drawTextBox();
        super.drawScreen(par1, par2, par3);
    }
}
