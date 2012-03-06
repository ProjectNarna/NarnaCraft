package net.minecraft.src;

import java.io.*;

public class Packet132TileEntityData extends Packet
{
    public int field_48167_a;
    public int field_48165_b;
    public int field_48166_c;
    public int field_48163_d;
    public int field_48164_e;
    public int field_48161_f;
    public int field_48162_g;

    public Packet132TileEntityData()
    {
        isChunkDataPacket = true;
    }

    /**
     * Abstract. Reads the raw packet data from the data stream.
     */
    public void readPacketData(DataInputStream par1DataInputStream) throws IOException
    {
        field_48167_a = par1DataInputStream.readInt();
        field_48165_b = par1DataInputStream.readShort();
        field_48166_c = par1DataInputStream.readInt();
        field_48163_d = par1DataInputStream.readByte();
        field_48164_e = par1DataInputStream.readInt();
        field_48161_f = par1DataInputStream.readInt();
        field_48162_g = par1DataInputStream.readInt();
    }

    /**
     * Abstract. Writes the raw packet data to the data stream.
     */
    public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
    {
        par1DataOutputStream.writeInt(field_48167_a);
        par1DataOutputStream.writeShort(field_48165_b);
        par1DataOutputStream.writeInt(field_48166_c);
        par1DataOutputStream.writeByte((byte)field_48163_d);
        par1DataOutputStream.writeInt(field_48164_e);
        par1DataOutputStream.writeInt(field_48161_f);
        par1DataOutputStream.writeInt(field_48162_g);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(NetHandler par1NetHandler)
    {
        par1NetHandler.func_48489_a(this);
    }

    /**
     * Abstract. Return the size of the packet (not counting the header).
     */
    public int getPacketSize()
    {
        return 25;
    }
}
