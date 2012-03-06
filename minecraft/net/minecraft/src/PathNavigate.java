package net.minecraft.src;

public class PathNavigate
{
    private EntityLiving field_46076_a;
    private World field_46074_b;
    private PathEntity field_46075_c;
    private float field_46073_d;
    private float field_48683_e;
    private boolean field_48681_f;
    private int field_48682_g;
    private int field_48688_h;
    private Vec3D field_48689_i;
    private boolean field_48686_j;
    private boolean field_48687_k;
    private boolean field_48684_l;
    private boolean field_48685_m;

    public PathNavigate(EntityLiving par1EntityLiving, World par2World, float par3)
    {
        field_48681_f = false;
        field_48689_i = Vec3D.createVectorHelper(0.0D, 0.0D, 0.0D);
        field_48686_j = true;
        field_48687_k = false;
        field_48684_l = false;
        field_48685_m = false;
        field_46076_a = par1EntityLiving;
        field_46074_b = par2World;
        field_48683_e = par3;
    }

    public void func_48664_a(boolean par1)
    {
        field_48684_l = par1;
    }

    public boolean func_48658_a()
    {
        return field_48684_l;
    }

    public void func_48673_b(boolean par1)
    {
        field_48687_k = par1;
    }

    public void func_48663_c(boolean par1)
    {
        field_48686_j = par1;
    }

    public boolean func_48665_b()
    {
        return field_48687_k;
    }

    public void func_48680_d(boolean par1)
    {
        field_48681_f = par1;
    }

    public void func_48660_a(float par1)
    {
        field_46073_d = par1;
    }

    public void func_48669_e(boolean par1)
    {
        field_48685_m = par1;
    }

    public PathEntity func_48671_a(double par1, double par3, double par5)
    {
        if (!func_48659_j())
        {
            return null;
        }
        else
        {
            return field_46074_b.func_48460_a(field_46076_a, MathHelper.floor_double(par1), (int)par3, MathHelper.floor_double(par5), field_48683_e, field_48686_j, field_48687_k, field_48684_l, field_48685_m);
        }
    }

    public boolean func_48666_a(double par1, double par3, double par5, float par7)
    {
        PathEntity pathentity = func_48671_a(MathHelper.floor_double(par1), (int)par3, MathHelper.floor_double(par5));
        return func_48678_a(pathentity, par7);
    }

    public PathEntity func_48679_a(EntityLiving par1EntityLiving)
    {
        if (!func_48659_j())
        {
            return null;
        }
        else
        {
            return field_46074_b.func_48463_a(field_46076_a, par1EntityLiving, field_48683_e, field_48686_j, field_48687_k, field_48684_l, field_48685_m);
        }
    }

    public boolean func_48667_a(EntityLiving par1EntityLiving, float par2)
    {
        PathEntity pathentity = func_48679_a(par1EntityLiving);

        if (pathentity != null)
        {
            return func_48678_a(pathentity, par2);
        }
        else
        {
            return false;
        }
    }

    public boolean func_48678_a(PathEntity par1PathEntity, float par2)
    {
        if (par1PathEntity == null)
        {
            field_46075_c = null;
            return false;
        }

        if (!par1PathEntity.func_48647_a(field_46075_c))
        {
            field_46075_c = par1PathEntity;
        }

        if (field_48681_f)
        {
            func_48677_l();
        }

        if (field_46075_c.func_48644_d() == 0)
        {
            return false;
        }
        else
        {
            field_46073_d = par2;
            Vec3D vec3d = func_48661_h();
            field_48688_h = field_48682_g;
            field_48689_i.xCoord = vec3d.xCoord;
            field_48689_i.yCoord = vec3d.yCoord;
            field_48689_i.zCoord = vec3d.zCoord;
            return true;
        }
    }

    public PathEntity func_48670_c()
    {
        return field_46075_c;
    }

    public void onUpdateNavigation()
    {
        field_48682_g++;

        if (func_46072_b())
        {
            return;
        }

        if (func_48659_j())
        {
            func_48674_g();
        }

        if (func_46072_b())
        {
            return;
        }

        Vec3D vec3d = field_46075_c.func_48640_a(field_46076_a);

        if (vec3d == null)
        {
            return;
        }
        else
        {
            field_46076_a.getMoveHelper().func_48187_a(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord, field_46073_d);
            return;
        }
    }

    private void func_48674_g()
    {
        Vec3D vec3d = func_48661_h();
        int i = field_46075_c.func_48644_d();
        int f = field_46075_c.func_48643_e();

        do
        {
            if (f >= field_46075_c.func_48644_d())
            {
                break;
            }

            if (field_46075_c.func_48648_a(f).yCoord != (int)vec3d.yCoord)
            {
                i = f;
                break;
            }

            f++;
        }
        while (true);

        float fa = field_46076_a.width * field_46076_a.width;

        for (int j = field_46075_c.func_48643_e(); j < i; j++)
        {
            if (vec3d.squareDistanceTo(field_46075_c.func_48646_a(field_46076_a, j)) < (double)fa)
            {
                field_46075_c.func_48642_c(j + 1);
            }
        }

        int k = (int)Math.ceil(field_46076_a.width);
        int l = (int)field_46076_a.height + 1;
        int i1 = k;
        int j1 = i - 1;

        do
        {
            if (j1 < field_46075_c.func_48643_e())
            {
                break;
            }

            if (func_48662_a(vec3d, field_46075_c.func_48646_a(field_46076_a, j1), k, l, i1))
            {
                field_46075_c.func_48642_c(j1);
                break;
            }

            j1--;
        }
        while (true);

        if (field_48682_g - field_48688_h > 100)
        {
            if (vec3d.squareDistanceTo(field_48689_i) < 2.25D)
            {
                func_48672_f();
            }

            field_48688_h = field_48682_g;
            field_48689_i.xCoord = vec3d.xCoord;
            field_48689_i.yCoord = vec3d.yCoord;
            field_48689_i.zCoord = vec3d.zCoord;
        }
    }

    public boolean func_46072_b()
    {
        return field_46075_c == null || field_46075_c.isFinished();
    }

    public void func_48672_f()
    {
        field_46075_c = null;
    }

    private Vec3D func_48661_h()
    {
        return Vec3D.createVector(field_46076_a.posX, func_48668_i(), field_46076_a.posZ);
    }

    private int func_48668_i()
    {
        if (!field_46076_a.isInWater() || !field_48685_m)
        {
            return (int)(field_46076_a.boundingBox.minY + 0.5D);
        }

        int i = (int)field_46076_a.boundingBox.minY;
        int j = field_46074_b.getBlockId(MathHelper.floor_double(field_46076_a.posX), i, MathHelper.floor_double(field_46076_a.posZ));
        int k = 0;

        while (j == Block.waterMoving.blockID || j == Block.waterStill.blockID)
        {
            i++;
            j = field_46074_b.getBlockId(MathHelper.floor_double(field_46076_a.posX), i, MathHelper.floor_double(field_46076_a.posZ));

            if (++k > 16)
            {
                return (int)field_46076_a.boundingBox.minY;
            }
        }

        return i;
    }

    private boolean func_48659_j()
    {
        return field_46076_a.onGround || field_48685_m && func_48657_k();
    }

    private boolean func_48657_k()
    {
        return field_46076_a.isInWater() || field_46076_a.handleLavaMovement();
    }

    private void func_48677_l()
    {
        if (field_46074_b.canBlockSeeTheSky(MathHelper.floor_double(field_46076_a.posX), (int)(field_46076_a.boundingBox.minY + 0.5D), MathHelper.floor_double(field_46076_a.posZ)))
        {
            return;
        }

        for (int i = 0; i < field_46075_c.func_48644_d(); i++)
        {
            PathPoint pathpoint = field_46075_c.func_48648_a(i);

            if (field_46074_b.canBlockSeeTheSky(pathpoint.xCoord, pathpoint.yCoord, pathpoint.zCoord))
            {
                field_46075_c.func_48641_b(i - 1);
                return;
            }
        }
    }

    private boolean func_48662_a(Vec3D par1Vec3D, Vec3D par2Vec3D, int par3, int par4, int par5)
    {
        int i = MathHelper.floor_double(par1Vec3D.xCoord);
        int j = MathHelper.floor_double(par1Vec3D.zCoord);
        double d = par2Vec3D.xCoord - par1Vec3D.xCoord;
        double d1 = par2Vec3D.zCoord - par1Vec3D.zCoord;
        double d2 = d * d + d1 * d1;

        if (d2 < 1E-008D)
        {
            return false;
        }

        double d3 = 1.0D / Math.sqrt(d2);
        d *= d3;
        d1 *= d3;
        par3 += 2;
        par5 += 2;

        if (!func_48675_a(i, (int)par1Vec3D.yCoord, j, par3, par4, par5, par1Vec3D, d, d1))
        {
            return false;
        }

        par3 -= 2;
        par5 -= 2;
        double d4 = 1.0D / Math.abs(d);
        double d5 = 1.0D / Math.abs(d1);
        double d6 = (double)(i * 1) - par1Vec3D.xCoord;
        double d7 = (double)(j * 1) - par1Vec3D.zCoord;

        if (d >= 0.0D)
        {
            d6++;
        }

        if (d1 >= 0.0D)
        {
            d7++;
        }

        d6 /= d;
        d7 /= d1;
        byte byte0 = ((byte)(d >= 0.0D ? 1 : -1));
        byte byte1 = ((byte)(d1 >= 0.0D ? 1 : -1));
        int k = MathHelper.floor_double(par2Vec3D.xCoord);
        int l = MathHelper.floor_double(par2Vec3D.zCoord);
        int i1 = k - i;

        for (int j1 = l - j; i1 * byte0 > 0 || j1 * byte1 > 0;)
        {
            if (d6 < d7)
            {
                d6 += d4;
                i += byte0;
                i1 = k - i;
            }
            else
            {
                d7 += d5;
                j += byte1;
                j1 = l - j;
            }

            if (!func_48675_a(i, (int)par1Vec3D.yCoord, j, par3, par4, par5, par1Vec3D, d, d1))
            {
                return false;
            }
        }

        return true;
    }

    private boolean func_48675_a(int par1, int par2, int par3, int par4, int par5, int par6, Vec3D par7Vec3D, double par8, double par10)
    {
        int i = par1 - par4 / 2;
        int j = par3 - par6 / 2;

        if (!func_48676_b(i, par2, j, par4, par5, par6, par7Vec3D, par8, par10))
        {
            return false;
        }

        for (int k = i; k < i + par4; k++)
        {
            for (int l = j; l < j + par6; l++)
            {
                double d = ((double)k + 0.5D) - par7Vec3D.xCoord;
                double d1 = ((double)l + 0.5D) - par7Vec3D.zCoord;

                if (d * par8 + d1 * par10 < 0.0D)
                {
                    continue;
                }

                int i1 = field_46074_b.getBlockId(k, par2 - 1, l);

                if (i1 <= 0)
                {
                    return false;
                }

                Material material = Block.blocksList[i1].blockMaterial;

                if (material == Material.water && !field_46076_a.isInWater())
                {
                    return false;
                }

                if (material == Material.lava)
                {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean func_48676_b(int par1, int par2, int par3, int par4, int par5, int par6, Vec3D par7Vec3D, double par8, double par10)
    {
        for (int i = par1; i < par1 + par4; i++)
        {
            for (int j = par2; j < par2 + par5; j++)
            {
                for (int k = par3; k < par3 + par6; k++)
                {
                    double d = ((double)i + 0.5D) - par7Vec3D.xCoord;
                    double d1 = ((double)k + 0.5D) - par7Vec3D.zCoord;

                    if (d * par8 + d1 * par10 < 0.0D)
                    {
                        continue;
                    }

                    int l = field_46074_b.getBlockId(i, j, k);

                    if (l > 0 && !Block.blocksList[l].func_48204_b(field_46074_b, i, j, k))
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
