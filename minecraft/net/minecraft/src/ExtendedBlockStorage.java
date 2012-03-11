package net.minecraft.src;

public class ExtendedBlockStorage
{
    private int field_48722_a;
    private int field_48720_b;
    private int field_48721_c;
    private byte field_48718_d[];
    private NibbleArray field_48719_e;
    private NibbleArray field_48716_f;
    private NibbleArray field_48717_g;
    private NibbleArray field_48723_h;

    public ExtendedBlockStorage(int par1)
    {
        field_48722_a = par1;
        field_48718_d = new byte[4096];
        field_48716_f = new NibbleArray(field_48718_d.length, 4);
        field_48723_h = new NibbleArray(field_48718_d.length, 4);
        field_48717_g = new NibbleArray(field_48718_d.length, 4);
    }

    public int func_48703_a(int par1, int par2, int par3)
    {
        int i = field_48718_d[par2 << 8 | par3 << 4 | par1] & 0xff;

        if (field_48719_e != null)
        {
            return field_48719_e.get(par1, par2, par3) << 8 | i;
        }
        else
        {
            return i;
        }
    }

    public void func_48691_a(int par1, int par2, int par3, int par4)
    {
        int i = field_48718_d[par2 << 8 | par3 << 4 | par1] & 0xff;

        if (field_48719_e != null)
        {
            i = field_48719_e.get(par1, par2, par3) << 8 | i;
        }

        if (i == 0 && par4 != 0)
        {
            field_48720_b++;

            if (Block.blocksList[par4] != null && Block.blocksList[par4].func_48203_o())
            {
                field_48721_c++;
            }
        }
        else if (i != 0 && par4 == 0)
        {
            field_48720_b--;

            if (Block.blocksList[i] != null && Block.blocksList[i].func_48203_o())
            {
                field_48721_c--;
            }
        }
        else if (Block.blocksList[i] != null && Block.blocksList[i].func_48203_o() && (Block.blocksList[par4] == null || !Block.blocksList[par4].func_48203_o()))
        {
            field_48721_c--;
        }
        else if ((Block.blocksList[i] == null || !Block.blocksList[i].func_48203_o()) && Block.blocksList[par4] != null && Block.blocksList[par4].func_48203_o())
        {
            field_48721_c++;
        }

        field_48718_d[par2 << 8 | par3 << 4 | par1] = (byte)(par4 & 0xff);

        if (par4 > 255)
        {
            if (field_48719_e == null)
            {
                field_48719_e = new NibbleArray(field_48718_d.length, 4);
            }

            field_48719_e.set(par1, par2, par3, (par4 & 0xf00) >> 8);
        }
        else if (field_48719_e != null)
        {
            field_48719_e.set(par1, par2, par3, 0);
        }
    }

    public int func_48694_b(int par1, int par2, int par3)
    {
        return field_48716_f.get(par1, par2, par3);
    }

    public void func_48690_b(int par1, int par2, int par3, int par4)
    {
        field_48716_f.set(par1, par2, par3, par4);
    }

    public boolean func_48693_a()
    {
        return field_48720_b == 0;
    }

    public boolean func_48698_b()
    {
        return field_48721_c > 0;
    }

    public int func_48707_c()
    {
        return field_48722_a;
    }

    public void func_48702_c(int par1, int par2, int par3, int par4)
    {
        field_48723_h.set(par1, par2, par3, par4);
    }

    public int func_48709_c(int par1, int par2, int par3)
    {
        return field_48723_h.get(par1, par2, par3);
    }

    public void func_48699_d(int par1, int par2, int par3, int par4)
    {
        field_48717_g.set(par1, par2, par3, par4);
    }

    public int func_48712_d(int par1, int par2, int par3)
    {
        return field_48717_g.get(par1, par2, par3);
    }

    public void func_48708_d()
    {
        field_48720_b = 0;
        field_48721_c = 0;

        for (int i = 0; i < 16; i++)
        {
            for (int j = 0; j < 16; j++)
            {
                for (int k = 0; k < 16; k++)
                {
                    int l = func_48703_a(i, j, k);

                    if (l <= 0)
                    {
                        continue;
                    }

                    if (Block.blocksList[l] == null)
                    {
                        field_48718_d[j << 8 | k << 4 | i] = 0;

                        if (field_48719_e != null)
                        {
                            field_48719_e.set(i, j, k, 0);
                        }

                        continue;
                    }

                    field_48720_b++;

                    if (Block.blocksList[l].func_48203_o())
                    {
                        field_48721_c++;
                    }
                }
            }
        }
    }

    public void func_48711_e()
    {
    }

    public int func_48700_f()
    {
        return field_48720_b;
    }

    public byte[] func_48692_g()
    {
        return field_48718_d;
    }

    public void func_48715_h()
    {
        field_48719_e = null;
    }

    public NibbleArray func_48704_i()
    {
        return field_48719_e;
    }

    public NibbleArray func_48697_j()
    {
        return field_48716_f;
    }

    public NibbleArray func_48705_k()
    {
        return field_48717_g;
    }

    public NibbleArray func_48714_l()
    {
        return field_48723_h;
    }

    public void func_48706_a(byte par1ArrayOfByte[])
    {
        field_48718_d = par1ArrayOfByte;
    }

    public void func_48710_a(NibbleArray par1NibbleArray)
    {
        field_48719_e = par1NibbleArray;
    }

    public void func_48701_b(NibbleArray par1NibbleArray)
    {
        field_48716_f = par1NibbleArray;
    }

    public void func_48695_c(NibbleArray par1NibbleArray)
    {
        field_48717_g = par1NibbleArray;
    }

    public void func_48713_d(NibbleArray par1NibbleArray)
    {
        field_48723_h = par1NibbleArray;
    }

    public NibbleArray func_48696_m()
    {
        field_48719_e = new NibbleArray(field_48718_d.length, 4);
        return field_48719_e;
    }
}
