package net.minecraft.src;

public class VillageDoorInfo
{
    public final int field_48600_a;
    public final int field_48598_b;
    public final int field_48599_c;
    public final int field_48596_d;
    public final int field_48597_e;
    public int field_48594_f;
    public boolean field_48595_g;
    private int field_48601_h;

    public VillageDoorInfo(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        field_48595_g = false;
        field_48601_h = 0;
        field_48600_a = par1;
        field_48598_b = par2;
        field_48599_c = par3;
        field_48596_d = par4;
        field_48597_e = par5;
        field_48594_f = par6;
    }

    public int func_48588_a(int par1, int par2, int par3)
    {
        int i = par1 - field_48600_a;
        int j = par2 - field_48598_b;
        int k = par3 - field_48599_c;
        return i * i + j * j + k * k;
    }

    public int func_48593_b(int par1, int par2, int par3)
    {
        int i = par1 - field_48600_a - field_48596_d;
        int j = par2 - field_48598_b;
        int k = par3 - field_48599_c - field_48597_e;
        return i * i + j * j + k * k;
    }

    public int func_48590_a()
    {
        return field_48600_a + field_48596_d;
    }

    public int func_48592_b()
    {
        return field_48598_b;
    }

    public int func_48591_c()
    {
        return field_48599_c + field_48597_e;
    }

    public boolean func_48586_a(int par1, int par2)
    {
        int i = par1 - field_48600_a;
        int j = par2 - field_48599_c;
        return i * field_48596_d + j * field_48597_e >= 0;
    }

    public void func_48585_d()
    {
        field_48601_h = 0;
    }

    public void func_48589_e()
    {
        field_48601_h++;
    }

    public int func_48587_f()
    {
        return field_48601_h;
    }
}
