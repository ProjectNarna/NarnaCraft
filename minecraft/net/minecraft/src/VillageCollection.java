package net.minecraft.src;

import java.util.*;

public class VillageCollection
{
    private World field_48570_a;
    private final List field_48568_b = new ArrayList();
    private final List field_48569_c = new ArrayList();
    private final List field_48566_d = new ArrayList();
    private int field_48567_e;

    public VillageCollection(World par1World)
    {
        field_48567_e = 0;
        field_48570_a = par1World;
    }

    public void func_48565_a(int par1, int par2, int par3)
    {
        if (field_48568_b.size() > 64)
        {
            return;
        }

        if (!func_48561_d(par1, par2, par3))
        {
            field_48568_b.add(new ChunkCoordinates(par1, par2, par3));
        }
    }

    public void func_48558_a()
    {
        field_48567_e++;
        Village village;

        for (Iterator iterator = field_48566_d.iterator(); iterator.hasNext(); village.func_48522_a(field_48567_e))
        {
            village = (Village)iterator.next();
        }

        func_48563_c();
        func_48557_d();
        func_48560_e();
    }

    private void func_48563_c()
    {
        Iterator iterator = field_48566_d.iterator();

        do
        {
            if (!iterator.hasNext())
            {
                break;
            }

            Village village = (Village)iterator.next();

            if (village.func_48529_g())
            {
                iterator.remove();
            }
        }
        while (true);
    }

    public List func_48554_b()
    {
        return field_48566_d;
    }

    public Village func_48564_a(int par1, int par2, int par3, int par4)
    {
        Village village = null;
        float f = 3.402823E+038F;
        Iterator iterator = field_48566_d.iterator();

        do
        {
            if (!iterator.hasNext())
            {
                break;
            }

            Village village1 = (Village)iterator.next();
            float f1 = village1.func_48539_a().func_48655_c(par1, par2, par3);

            if (f1 < f)
            {
                int i = par4 + village1.func_48531_b();

                if (f1 <= (float)(i * i))
                {
                    village = village1;
                    f = f1;
                }
            }
        }
        while (true);

        return village;
    }

    private void func_48557_d()
    {
        if (field_48568_b.isEmpty())
        {
            return;
        }
        else
        {
            func_48559_a((ChunkCoordinates)field_48568_b.remove(0));
            return;
        }
    }

    private void func_48560_e()
    {
        for (int i = 0; i < field_48569_c.size(); i++)
        {
            VillageDoorInfo villagedoorinfo = (VillageDoorInfo)field_48569_c.get(i);
            boolean flag = false;
            Iterator iterator = field_48566_d.iterator();

            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }

                Village village1 = (Village)iterator.next();
                int j = (int)village1.func_48539_a().getSqDistanceTo(villagedoorinfo.field_48600_a, villagedoorinfo.field_48598_b, villagedoorinfo.field_48599_c);

                if (j > 32 + village1.func_48531_b())
                {
                    continue;
                }

                village1.func_48538_a(villagedoorinfo);
                flag = true;
                break;
            }
            while (true);

            if (!flag)
            {
                Village village = new Village(field_48570_a);
                village.func_48538_a(villagedoorinfo);
                field_48566_d.add(village);
            }
        }

        field_48569_c.clear();
    }

    private void func_48559_a(ChunkCoordinates par1ChunkCoordinates)
    {
        byte byte0 = 16;
        byte byte1 = 4;
        byte byte2 = 16;

        for (int i = par1ChunkCoordinates.posX - byte0; i < par1ChunkCoordinates.posX + byte0; i++)
        {
            for (int j = par1ChunkCoordinates.posY - byte1; j < par1ChunkCoordinates.posY + byte1; j++)
            {
                for (int k = par1ChunkCoordinates.posZ - byte2; k < par1ChunkCoordinates.posZ + byte2; k++)
                {
                    if (!func_48555_e(i, j, k))
                    {
                        continue;
                    }

                    VillageDoorInfo villagedoorinfo = func_48562_b(i, j, k);

                    if (villagedoorinfo == null)
                    {
                        func_48556_c(i, j, k);
                    }
                    else
                    {
                        villagedoorinfo.field_48594_f = field_48567_e;
                    }
                }
            }
        }
    }

    private VillageDoorInfo func_48562_b(int par1, int par2, int par3)
    {
        for (Iterator iterator = field_48569_c.iterator(); iterator.hasNext();)
        {
            VillageDoorInfo villagedoorinfo = (VillageDoorInfo)iterator.next();

            if (villagedoorinfo.field_48600_a == par1 && villagedoorinfo.field_48599_c == par3 && Math.abs(villagedoorinfo.field_48598_b - par2) <= 1)
            {
                return villagedoorinfo;
            }
        }

        for (Iterator iterator1 = field_48566_d.iterator(); iterator1.hasNext();)
        {
            Village village = (Village)iterator1.next();
            VillageDoorInfo villagedoorinfo1 = village.func_48526_d(par1, par2, par3);

            if (villagedoorinfo1 != null)
            {
                return villagedoorinfo1;
            }
        }

        return null;
    }

    private void func_48556_c(int par1, int par2, int par3)
    {
        int i = ((BlockDoor)Block.doorWood).func_48214_g(field_48570_a, par1, par2, par3);

        if (i == 0 || i == 2)
        {
            int j = 0;

            for (int l = -5; l < 0; l++)
            {
                if (field_48570_a.canBlockSeeTheSky(par1 + l, par2, par3))
                {
                    j--;
                }
            }

            for (int i1 = 1; i1 <= 5; i1++)
            {
                if (field_48570_a.canBlockSeeTheSky(par1 + i1, par2, par3))
                {
                    j++;
                }
            }

            if (j != 0)
            {
                field_48569_c.add(new VillageDoorInfo(par1, par2, par3, j <= 0 ? 2 : -2, 0, field_48567_e));
            }
        }
        else
        {
            int k = 0;

            for (int j1 = -5; j1 < 0; j1++)
            {
                if (field_48570_a.canBlockSeeTheSky(par1, par2, par3 + j1))
                {
                    k--;
                }
            }

            for (int k1 = 1; k1 <= 5; k1++)
            {
                if (field_48570_a.canBlockSeeTheSky(par1, par2, par3 + k1))
                {
                    k++;
                }
            }

            if (k != 0)
            {
                field_48569_c.add(new VillageDoorInfo(par1, par2, par3, 0, k <= 0 ? 2 : -2, field_48567_e));
            }
        }
    }

    private boolean func_48561_d(int par1, int par2, int par3)
    {
        for (Iterator iterator = field_48568_b.iterator(); iterator.hasNext();)
        {
            ChunkCoordinates chunkcoordinates = (ChunkCoordinates)iterator.next();

            if (chunkcoordinates.posX == par1 && chunkcoordinates.posY == par2 && chunkcoordinates.posZ == par3)
            {
                return true;
            }
        }

        return false;
    }

    private boolean func_48555_e(int par1, int par2, int par3)
    {
        int i = field_48570_a.getBlockId(par1, par2, par3);
        return i == Block.doorWood.blockID;
    }
}
