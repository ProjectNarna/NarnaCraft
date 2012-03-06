package net.minecraft.src;

public class ChunkLoader
{
    public ChunkLoader()
    {
    }

    public static AnvilConverterData func_48485_a(NBTTagCompound par0NBTTagCompound)
    {
        int i = par0NBTTagCompound.getInteger("xPos");
        int j = par0NBTTagCompound.getInteger("zPos");
        AnvilConverterData anvilconverterdata = new AnvilConverterData(i, j);
        anvilconverterdata.field_48603_g = par0NBTTagCompound.getByteArray("Blocks");
        anvilconverterdata.field_48602_f = new NibbleArrayReader(par0NBTTagCompound.getByteArray("Data"), 7);
        anvilconverterdata.field_48605_e = new NibbleArrayReader(par0NBTTagCompound.getByteArray("SkyLight"), 7);
        anvilconverterdata.field_48604_d = new NibbleArrayReader(par0NBTTagCompound.getByteArray("BlockLight"), 7);
        anvilconverterdata.field_48607_c = par0NBTTagCompound.getByteArray("HeightMap");
        anvilconverterdata.field_48606_b = par0NBTTagCompound.getBoolean("TerrainPopulated");
        anvilconverterdata.field_48612_h = par0NBTTagCompound.getTagList("Entities");
        anvilconverterdata.field_48613_i = par0NBTTagCompound.getTagList("TileEntities");
        anvilconverterdata.field_48610_j = par0NBTTagCompound.getTagList("TileTicks");
        anvilconverterdata.field_48608_a = par0NBTTagCompound.getLong("LastUpdate");
        return anvilconverterdata;
    }

    public static void func_48486_a(AnvilConverterData par0AnvilConverterData, NBTTagCompound par1NBTTagCompound, WorldChunkManager par2WorldChunkManager)
    {
        par1NBTTagCompound.setInteger("xPos", par0AnvilConverterData.field_48611_k);
        par1NBTTagCompound.setInteger("zPos", par0AnvilConverterData.field_48609_l);
        par1NBTTagCompound.setLong("LastUpdate", par0AnvilConverterData.field_48608_a);
        int ai[] = new int[par0AnvilConverterData.field_48607_c.length];

        for (int i = 0; i < par0AnvilConverterData.field_48607_c.length; i++)
        {
            ai[i] = par0AnvilConverterData.field_48607_c[i];
        }

        par1NBTTagCompound.func_48183_a("HeightMap", ai);
        par1NBTTagCompound.setBoolean("TerrainPopulated", par0AnvilConverterData.field_48606_b);
        NBTTagList nbttaglist = new NBTTagList("Sections");

        for (int j = 0; j < 8; j++)
        {
            boolean flag = true;

            for (int l = 0; l < 16 && flag; l++)
            {
                label0:

                for (int j1 = 0; j1 < 16 && flag; j1++)
                {
                    int k1 = 0;

                    do
                    {
                        if (k1 >= 16)
                        {
                            continue label0;
                        }

                        int l1 = l << 11 | k1 << 7 | j1 + (j << 4);
                        byte byte0 = par0AnvilConverterData.field_48603_g[l1];

                        if (byte0 != 0)
                        {
                            flag = false;
                            continue label0;
                        }

                        k1++;
                    }
                    while (true);
                }
            }

            if (flag)
            {
                continue;
            }

            byte abyte1[] = new byte[4096];
            NibbleArray nibblearray = new NibbleArray(abyte1.length, 4);
            NibbleArray nibblearray1 = new NibbleArray(abyte1.length, 4);
            NibbleArray nibblearray2 = new NibbleArray(abyte1.length, 4);

            for (int i2 = 0; i2 < 16; i2++)
            {
                for (int j2 = 0; j2 < 16; j2++)
                {
                    for (int k2 = 0; k2 < 16; k2++)
                    {
                        int l2 = i2 << 11 | k2 << 7 | j2 + (j << 4);
                        byte byte1 = par0AnvilConverterData.field_48603_g[l2];
                        abyte1[j2 << 8 | k2 << 4 | i2] = (byte)(byte1 & 0xff);
                        nibblearray.set(i2, j2, k2, par0AnvilConverterData.field_48602_f.func_48508_a(i2, j2 + (j << 4), k2));
                        nibblearray1.set(i2, j2, k2, par0AnvilConverterData.field_48605_e.func_48508_a(i2, j2 + (j << 4), k2));
                        nibblearray2.set(i2, j2, k2, par0AnvilConverterData.field_48604_d.func_48508_a(i2, j2 + (j << 4), k2));
                    }
                }
            }

            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setByte("Y", (byte)(j & 0xff));
            nbttagcompound.setByteArray("Blocks", abyte1);
            nbttagcompound.setByteArray("Data", nibblearray.data);
            nbttagcompound.setByteArray("SkyLight", nibblearray1.data);
            nbttagcompound.setByteArray("BlockLight", nibblearray2.data);
            nbttaglist.appendTag(nbttagcompound);
        }

        par1NBTTagCompound.setTag("Sections", nbttaglist);
        byte abyte0[] = new byte[256];

        for (int k = 0; k < 16; k++)
        {
            for (int i1 = 0; i1 < 16; i1++)
            {
                abyte0[i1 << 4 | k] = (byte)(par2WorldChunkManager.getBiomeGenAt(par0AnvilConverterData.field_48611_k << 4 | k, par0AnvilConverterData.field_48609_l << 4 | i1).biomeID & 0xff);
            }
        }

        par1NBTTagCompound.setByteArray("Biomes", abyte0);
        par1NBTTagCompound.setTag("Entities", par0AnvilConverterData.field_48612_h);
        par1NBTTagCompound.setTag("TileEntities", par0AnvilConverterData.field_48613_i);

        if (par0AnvilConverterData.field_48610_j != null)
        {
            par1NBTTagCompound.setTag("TileTicks", par0AnvilConverterData.field_48610_j);
        }
    }
}
