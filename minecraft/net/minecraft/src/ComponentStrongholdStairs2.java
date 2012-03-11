package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

public class ComponentStrongholdStairs2 extends ComponentStrongholdStairs
{
    public StructureStrongholdPieceWeight field_35038_a;
    public ComponentStrongholdPortalRoom field_40009_b;
    public ArrayList field_35037_b;

    public ComponentStrongholdStairs2(int par1, Random par2Random, int par3, int par4)
    {
        super(0, par2Random, par3, par4);
        field_35037_b = new ArrayList();
    }

    public ChunkPosition getCenter()
    {
        if (field_40009_b != null)
        {
            return field_40009_b.getCenter();
        }
        else
        {
            return super.getCenter();
        }
    }
}
