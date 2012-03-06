package net.minecraft.src;

import java.util.*;

public class Village
{
    private final World field_48548_a;
    private final List field_48546_b = new ArrayList();
    private final ChunkCoordinates field_48547_c = new ChunkCoordinates(0, 0, 0);
    private final ChunkCoordinates field_48544_d = new ChunkCoordinates(0, 0, 0);
    private int field_48545_e;
    private int field_48542_f;
    private int field_48543_g;
    private int field_48550_h;
    private List field_48551_i;
    private int field_48549_j;

    public Village(World par1World)
    {
        field_48545_e = 0;
        field_48542_f = 0;
        field_48543_g = 0;
        field_48550_h = 0;
        field_48551_i = new ArrayList();
        field_48549_j = 0;
        field_48548_a = par1World;
    }

    public void func_48522_a(int par1)
    {
        field_48543_g = par1;
        func_48520_k();
        func_48528_j();

        if (par1 % 20 == 0)
        {
            func_48535_i();
        }

        if (par1 % 30 == 0)
        {
            func_48541_h();
        }

        int i = field_48550_h / 16;

        if (field_48549_j < i && field_48546_b.size() > 20 && field_48548_a.rand.nextInt(7000) == 0)
        {
            Vec3D vec3d = func_48532_a(MathHelper.floor_float(field_48544_d.posX), MathHelper.floor_float(field_48544_d.posY), MathHelper.floor_float(field_48544_d.posZ), 2, 4, 2);

            if (vec3d != null)
            {
                EntityIronGolem entityirongolem = new EntityIronGolem(field_48548_a);
                entityirongolem.setPosition(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
                field_48548_a.spawnEntityInWorld(entityirongolem);
                field_48549_j++;
            }
        }
    }

    private Vec3D func_48532_a(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        for (int i = 0; i < 10; i++)
        {
            int j = (par1 + field_48548_a.rand.nextInt(16)) - 8;
            int k = (par2 + field_48548_a.rand.nextInt(6)) - 3;
            int l = (par3 + field_48548_a.rand.nextInt(16)) - 8;

            if (func_48533_a(j, k, l) && func_48525_b(j, k, l, par4, par5, par6))
            {
                return Vec3D.createVector(j, k, l);
            }
        }

        return null;
    }

    private boolean func_48525_b(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        if (!field_48548_a.isBlockNormalCube(par1, par2 - 1, par3))
        {
            return false;
        }

        int i = par1 - par4 / 2;
        int j = par3 - par6 / 2;

        for (int k = i; k < i + par4; k++)
        {
            for (int l = par2; l < par2 + par5; l++)
            {
                for (int i1 = j; i1 < j + par6; i1++)
                {
                    if (field_48548_a.isBlockNormalCube(k, l, i1))
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private void func_48541_h()
    {
        List list = field_48548_a.getEntitiesWithinAABB(net.minecraft.src.EntityIronGolem.class, AxisAlignedBB.getBoundingBoxFromPool(field_48544_d.posX - field_48545_e, field_48544_d.posY - 4, field_48544_d.posZ - field_48545_e, field_48544_d.posX + field_48545_e, field_48544_d.posY + 4, field_48544_d.posZ + field_48545_e));
        field_48549_j = list.size();
    }

    private void func_48535_i()
    {
        List list = field_48548_a.getEntitiesWithinAABB(net.minecraft.src.EntityVillager.class, AxisAlignedBB.getBoundingBoxFromPool(field_48544_d.posX - field_48545_e, field_48544_d.posY - 4, field_48544_d.posZ - field_48545_e, field_48544_d.posX + field_48545_e, field_48544_d.posY + 4, field_48544_d.posZ + field_48545_e));
        field_48550_h = list.size();
    }

    public ChunkCoordinates func_48539_a()
    {
        return field_48544_d;
    }

    public int func_48531_b()
    {
        return field_48545_e;
    }

    public int func_48530_c()
    {
        return field_48546_b.size();
    }

    public int func_48523_d()
    {
        return field_48543_g - field_48542_f;
    }

    public int func_48524_e()
    {
        return field_48550_h;
    }

    public boolean func_48533_a(int par1, int par2, int par3)
    {
        return field_48544_d.func_48655_c(par1, par2, par3) < (float)(field_48545_e * field_48545_e);
    }

    public List func_48521_f()
    {
        return field_48546_b;
    }

    public VillageDoorInfo func_48527_b(int par1, int par2, int par3)
    {
        VillageDoorInfo villagedoorinfo = null;
        int i = 0x7fffffff;
        Iterator iterator = field_48546_b.iterator();

        do
        {
            if (!iterator.hasNext())
            {
                break;
            }

            VillageDoorInfo villagedoorinfo1 = (VillageDoorInfo)iterator.next();
            int j = villagedoorinfo1.func_48588_a(par1, par2, par3);

            if (j < i)
            {
                villagedoorinfo = villagedoorinfo1;
                i = j;
            }
        }
        while (true);

        return villagedoorinfo;
    }

    public VillageDoorInfo func_48540_c(int par1, int par2, int par3)
    {
        VillageDoorInfo villagedoorinfo = null;
        int i = 0x7fffffff;
        Iterator iterator = field_48546_b.iterator();

        do
        {
            if (!iterator.hasNext())
            {
                break;
            }

            VillageDoorInfo villagedoorinfo1 = (VillageDoorInfo)iterator.next();
            int j = villagedoorinfo1.func_48588_a(par1, par2, par3);

            if (j > 256)
            {
                j *= 1000;
            }
            else
            {
                j = villagedoorinfo1.func_48587_f();
            }

            if (j < i)
            {
                villagedoorinfo = villagedoorinfo1;
                i = j;
            }
        }
        while (true);

        return villagedoorinfo;
    }

    public VillageDoorInfo func_48526_d(int par1, int par2, int par3)
    {
        if (field_48544_d.func_48655_c(par1, par2, par3) > (float)(field_48545_e * field_48545_e))
        {
            return null;
        }

        for (Iterator iterator = field_48546_b.iterator(); iterator.hasNext();)
        {
            VillageDoorInfo villagedoorinfo = (VillageDoorInfo)iterator.next();

            if (villagedoorinfo.field_48600_a == par1 && villagedoorinfo.field_48599_c == par3 && Math.abs(villagedoorinfo.field_48598_b - par2) <= 1)
            {
                return villagedoorinfo;
            }
        }

        return null;
    }

    public void func_48538_a(VillageDoorInfo par1VillageDoorInfo)
    {
        field_48546_b.add(par1VillageDoorInfo);
        field_48547_c.posX += par1VillageDoorInfo.field_48600_a;
        field_48547_c.posY += par1VillageDoorInfo.field_48598_b;
        field_48547_c.posZ += par1VillageDoorInfo.field_48599_c;
        func_48536_l();
        field_48542_f = par1VillageDoorInfo.field_48594_f;
    }

    public boolean func_48529_g()
    {
        return field_48546_b.isEmpty();
    }

    public void func_48534_a(EntityLiving par1EntityLiving)
    {
        for (Iterator iterator = field_48551_i.iterator(); iterator.hasNext();)
        {
            VillageAgressor villageagressor = (VillageAgressor)iterator.next();

            if (villageagressor.field_48515_a == par1EntityLiving)
            {
                villageagressor.field_48513_b = field_48543_g;
                return;
            }
        }

        field_48551_i.add(new VillageAgressor(this, par1EntityLiving, field_48543_g));
    }

    public EntityLiving func_48537_b(EntityLiving par1EntityLiving)
    {
        double d = Double.MAX_VALUE;
        VillageAgressor villageagressor = null;

        for (int i = 0; i < field_48551_i.size(); i++)
        {
            VillageAgressor villageagressor1 = (VillageAgressor)field_48551_i.get(i);
            double d1 = villageagressor1.field_48515_a.getDistanceSqToEntity(par1EntityLiving);

            if (d1 <= d)
            {
                villageagressor = villageagressor1;
                d = d1;
            }
        }

        return villageagressor == null ? null : villageagressor.field_48515_a;
    }

    private void func_48528_j()
    {
        Iterator iterator = field_48551_i.iterator();

        do
        {
            if (!iterator.hasNext())
            {
                break;
            }

            VillageAgressor villageagressor = (VillageAgressor)iterator.next();

            if (!villageagressor.field_48515_a.isEntityAlive() || Math.abs(field_48543_g - villageagressor.field_48513_b) > 300)
            {
                iterator.remove();
            }
        }
        while (true);
    }

    private void func_48520_k()
    {
        boolean flag = false;
        boolean flag1 = field_48548_a.rand.nextInt(50) == 0;
        Iterator iterator = field_48546_b.iterator();

        do
        {
            if (!iterator.hasNext())
            {
                break;
            }

            VillageDoorInfo villagedoorinfo = (VillageDoorInfo)iterator.next();

            if (flag1)
            {
                villagedoorinfo.func_48585_d();
            }

            if (!func_48519_e(villagedoorinfo.field_48600_a, villagedoorinfo.field_48598_b, villagedoorinfo.field_48599_c) || Math.abs(field_48543_g - villagedoorinfo.field_48594_f) > 1200)
            {
                field_48547_c.posX -= villagedoorinfo.field_48600_a;
                field_48547_c.posY -= villagedoorinfo.field_48598_b;
                field_48547_c.posZ -= villagedoorinfo.field_48599_c;
                flag = true;
                villagedoorinfo.field_48595_g = true;
                iterator.remove();
            }
        }
        while (true);

        if (flag)
        {
            func_48536_l();
        }
    }

    private boolean func_48519_e(int par1, int par2, int par3)
    {
        int i = field_48548_a.getBlockId(par1, par2, par3);

        if (i <= 0)
        {
            return false;
        }
        else
        {
            return i == Block.doorWood.blockID;
        }
    }

    private void func_48536_l()
    {
        int i = field_48546_b.size();

        if (i == 0)
        {
            field_48544_d.func_48656_a(0, 0, 0);
            field_48545_e = 0;
            return;
        }

        field_48544_d.func_48656_a(field_48547_c.posX / i, field_48547_c.posY / i, field_48547_c.posZ / i);
        int j = 0;

        for (Iterator iterator = field_48546_b.iterator(); iterator.hasNext();)
        {
            VillageDoorInfo villagedoorinfo = (VillageDoorInfo)iterator.next();
            j = Math.max(villagedoorinfo.func_48588_a(field_48544_d.posX, field_48544_d.posY, field_48544_d.posZ), j);
        }

        field_48545_e = Math.max(32, (int)Math.sqrt(j));
    }
}
