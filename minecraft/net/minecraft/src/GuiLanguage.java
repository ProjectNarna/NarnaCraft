package net.minecraft.src;

import java.util.List;
import net.minecraft.client.Minecraft;

public class GuiLanguage extends GuiScreen
{
    protected GuiScreen field_44009_a;
    private int field_44007_b;
    private GuiSlotLanguage field_44008_c;
    private final GameSettings field_44006_d;
    private GuiSmallButton field_46029_e;

    public GuiLanguage(GuiScreen par1GuiScreen, GameSettings par2GameSettings)
    {
        field_44007_b = -1;
        field_44009_a = par1GuiScreen;
        field_44006_d = par2GameSettings;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        controlList.add(field_46029_e = new GuiSmallButton(6, width / 2 - 75, height - 38, stringtranslate.translateKey("gui.done")));
        field_44008_c = new GuiSlotLanguage(this);
        field_44008_c.registerScrollButtons(controlList, 7, 8);
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

        if (par1GuiButton.id != 5)
        {
            if (par1GuiButton.id == 6)
            {
                field_44006_d.saveOptions();
                mc.displayGuiScreen(field_44009_a);
            }
            else
            {
                field_44008_c.actionPerformed(par1GuiButton);
            }
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
    }

    /**
     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
     * mouseMove, which==0 or which==1 is mouseUp
     */
    protected void mouseMovedOrUp(int par1, int par2, int par3)
    {
        super.mouseMovedOrUp(par1, par2, par3);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        field_44008_c.drawScreen(par1, par2, par3);

        if (field_44007_b <= 0)
        {
            mc.texturePackList.updateAvaliableTexturePacks();
            field_44007_b += 20;
        }

        StringTranslate stringtranslate = StringTranslate.getInstance();
        drawCenteredString(fontRenderer, stringtranslate.translateKey("options.language"), width / 2, 16, 0xffffff);
        drawCenteredString(fontRenderer, (new StringBuilder()).append("(").append(stringtranslate.translateKey("options.languageWarning")).append(")").toString(), width / 2, height - 56, 0x808080);
        super.drawScreen(par1, par2, par3);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();
        field_44007_b--;
    }

    static GameSettings func_44005_a(GuiLanguage par0GuiLanguage)
    {
        return par0GuiLanguage.field_44006_d;
    }

    static GuiSmallButton func_46028_b(GuiLanguage par0GuiLanguage)
    {
        return par0GuiLanguage.field_46029_e;
    }
}
