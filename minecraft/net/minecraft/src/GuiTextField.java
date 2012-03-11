package net.minecraft.src;

public class GuiTextField extends Gui
{
    /**
     * Have the font renderer from GuiScreen to render the textbox text into the screen.
     */
    private final FontRenderer fontRenderer;
    private final int xPos;
    private final int yPos;
    private final int width;
    private final int height;

    /** Have the current text beign edited on the textbox. */
    private String text;
    private int maxStringLength;
    private int cursorCounter;

    /**
     * If this value is true along isEnabled, keyTyped will process the keys.
     */
    public boolean isFocused;

    /**
     * If this value is true along isFocused, keyTyped will process the keys.
     */
    public boolean isEnabled;

    /**
     * Holds the GuiScreen that the textfield is attached, used for tab purposes.
     */
    private GuiScreen parentGuiScreen;

    public GuiTextField(GuiScreen par1GuiScreen, FontRenderer par2FontRenderer, int par3, int par4, int par5, int par6, String par7Str)
    {
        isFocused = false;
        isEnabled = true;
        parentGuiScreen = par1GuiScreen;
        fontRenderer = par2FontRenderer;
        xPos = par3;
        yPos = par4;
        width = par5;
        height = par6;
        setText(par7Str);
    }

    /**
     * Sets the text of the textbox.
     */
    public void setText(String par1Str)
    {
        text = par1Str;
    }

    /**
     * Returns the text beign edited on the textbox.
     */
    public String getText()
    {
        return text;
    }

    /**
     * Increments the cursor counter
     */
    public void updateCursorCounter()
    {
        cursorCounter++;
    }

    /**
     * Call this method from you GuiScreen to process the keys into textbox.
     */
    public void textboxKeyTyped(char par1, int par2)
    {
        if (!isEnabled || !isFocused)
        {
            return;
        }

        if (par1 == '\t')
        {
            parentGuiScreen.selectNextField();
        }

        if (!(par1 != '\026'))
        {
            String s;
            int i;
            s = GuiScreen.getClipboardString();

            if (s == null)
            {
                s = "";
            }

            i = 32 - text.length();

            if (i > s.length())
            {
                i = s.length();
            }

            if (!(i <= 0))
            {
                text += s.substring(0, i);
            }
        }

        if (par2 == 14 && text.length() > 0)
        {
            text = text.substring(0, text.length() - 1);
        }

        if (!(!ChatAllowedCharacters.func_48614_a(par1) || text.length() >= maxStringLength && maxStringLength != 0))
        {
            text += par1;
        }
    }

    /**
     * Args: x, y, buttonClicked
     */
    public void mouseClicked(int par1, int par2, int par3)
    {
        boolean flag = isEnabled && par1 >= xPos && par1 < xPos + width && par2 >= yPos && par2 < yPos + height;
        setFocused(flag);
    }

    public void setFocused(boolean par1)
    {
        if (par1 && !isFocused)
        {
            cursorCounter = 0;
        }

        isFocused = par1;
    }

    /**
     * Draws the textbox
     */
    public void drawTextBox()
    {
        drawRect(xPos - 1, yPos - 1, xPos + width + 1, yPos + height + 1, 0xffa0a0a0);
        drawRect(xPos, yPos, xPos + width, yPos + height, 0xff000000);

        if (isEnabled)
        {
            boolean flag = isFocused && (cursorCounter / 6) % 2 == 0;
            drawString(fontRenderer, (new StringBuilder()).append(text).append(flag ? "_" : "").toString(), xPos + 4, yPos + (height - 8) / 2, 0xe0e0e0);
        }
        else
        {
            drawString(fontRenderer, text, xPos + 4, yPos + (height - 8) / 2, 0x707070);
        }
    }

    public void setMaxStringLength(int par1)
    {
        maxStringLength = par1;
    }
}
