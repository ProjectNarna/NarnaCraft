package net.minecraft.src;

import java.io.*;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class Packet51MapChunk extends Packet
{
    public int field_48177_a;
    public int field_48175_b;
    public int field_48176_c;
    public int field_48173_d;
    public byte field_48174_e[];
    public boolean field_48171_f;
    private int field_48172_g;
    private int field_48178_h;
    private static byte field_48179_i[] = new byte[0];

    public Packet51MapChunk()
    {
        isChunkDataPacket = true;
    }

    /**
     * Abstract. Reads the raw packet data from the data stream.
     */
    public void readPacketData(DataInputStream par1DataInputStream) throws IOException
    {
        field_48177_a = par1DataInputStream.readInt();
        field_48175_b = par1DataInputStream.readInt();
        field_48171_f = par1DataInputStream.readBoolean();
        field_48176_c = par1DataInputStream.readShort();
        field_48173_d = par1DataInputStream.readShort();
        field_48172_g = par1DataInputStream.readInt();
        field_48178_h = par1DataInputStream.readInt();

        if (field_48179_i.length < field_48172_g)
        {
            field_48179_i = new byte[field_48172_g];
        }

        par1DataInputStream.readFully(field_48179_i, 0, field_48172_g);
        int i = 0;

        for (int j = 0; j < 16; j++)
        {
            i += field_48176_c >> j & 1;
        }

        int k = 12288 * i;

        if (field_48171_f)
        {
            k += 256;
        }

        field_48174_e = new byte[k];
        Inflater inflater = new Inflater();
        inflater.setInput(field_48179_i, 0, field_48172_g);

        try
        {
            inflater.inflate(field_48174_e);
        }
        catch (DataFormatException dataformatexception)
        {
            throw new IOException("Bad compressed data format");
        }
        finally
        {
            inflater.end();
        }
    }

    /**
     * Abstract. Writes the raw packet data to the data stream.
     */
    public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
    {
        par1DataOutputStream.writeInt(field_48177_a);
        par1DataOutputStream.writeInt(field_48175_b);
        par1DataOutputStream.writeBoolean(field_48171_f);
        par1DataOutputStream.writeShort((short)(field_48176_c & 0xffff));
        par1DataOutputStream.writeShort((short)(field_48173_d & 0xffff));
        par1DataOutputStream.writeInt(field_48172_g);
        par1DataOutputStream.writeInt(field_48178_h);
        par1DataOutputStream.write(field_48174_e, 0, field_48172_g);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(NetHandler par1NetHandler)
    {
        par1NetHandler.func_48487_a(this);
    }

    /**
     * Abstract. Return the size of the packet (not counting the header).
     */
    public int getPacketSize()
    {
        return 17 + field_48172_g;
    }
}
