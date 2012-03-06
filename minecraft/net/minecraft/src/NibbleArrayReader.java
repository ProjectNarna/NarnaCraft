package net.minecraft.src;

public class NibbleArrayReader
{
    public final byte field_48511_a[];
    private final int field_48509_b;
    private final int field_48510_c;

    public NibbleArrayReader(byte par1ArrayOfByte[], int par2)
    {
        field_48511_a = par1ArrayOfByte;
        field_48509_b = par2;
        field_48510_c = par2 + 4;
    }

    public int func_48508_a(int par1, int par2, int par3)
    {
        int i = par1 << field_48510_c | par3 << field_48509_b | par2;
        int j = i >> 1;
        int k = i & 1;

        if (k == 0)
        {
            return field_48511_a[j] & 0xf;
        }
        else
        {
            return field_48511_a[j] >> 4 & 0xf;
        }
    }
}
