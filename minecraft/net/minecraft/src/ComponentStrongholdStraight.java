package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentStrongholdStraight extends ComponentStronghold
{
    private final EnumDoor field_35050_a;
    private final boolean field_35048_b;
    private final boolean field_35049_c;

    public ComponentStrongholdStraight(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4)
    {
        super(par1);
        coordBaseMode = par4;
        field_35050_a = getRandomDoor(par2Random);
        boundingBox = par3StructureBoundingBox;
        field_35048_b = par2Random.nextInt(2) == 0;
        field_35049_c = par2Random.nextInt(2) == 0;
    }

    /**
     * 'Initiates construction of the Structure Component picked, at the current Location of StructGen'
     */
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random)
    {
        getNextComponentNormal((ComponentStrongholdStairs2)par1StructureComponent, par2List, par3Random, 1, 1);

        if (field_35048_b)
        {
            getNextComponentX((ComponentStrongholdStairs2)par1StructureComponent, par2List, par3Random, 1, 2);
        }

        if (field_35049_c)
        {
            getNextComponentZ((ComponentStrongholdStairs2)par1StructureComponent, par2List, par3Random, 1, 2);
        }
    }

    public static ComponentStrongholdStraight findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -1, -1, 0, 5, 5, 7, par5);

        if (!canStrongholdGoDeeper(structureboundingbox) || StructureComponent.findIntersecting(par0List, structureboundingbox) != null)
        {
            return null;
        }
        else
        {
            return new ComponentStrongholdStraight(par6, par1Random, structureboundingbox, par5);
        }
    }

    /**
     * 'second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...'
     */
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        if (isLiquidInStructureBoundingBox(par1World, par3StructureBoundingBox))
        {
            return false;
        }

        fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 4, 4, 6, true, par2Random, StructureStrongholdPieces.getStrongholdStones());
        placeDoor(par1World, par2Random, par3StructureBoundingBox, field_35050_a, 1, 1, 0);
        placeDoor(par1World, par2Random, par3StructureBoundingBox, EnumDoor.OPENING, 1, 1, 6);
        randomlyPlaceBlock(par1World, par3StructureBoundingBox, par2Random, 0.1F, 1, 2, 1, Block.torchWood.blockID, 0);
        randomlyPlaceBlock(par1World, par3StructureBoundingBox, par2Random, 0.1F, 3, 2, 1, Block.torchWood.blockID, 0);
        randomlyPlaceBlock(par1World, par3StructureBoundingBox, par2Random, 0.1F, 1, 2, 5, Block.torchWood.blockID, 0);
        randomlyPlaceBlock(par1World, par3StructureBoundingBox, par2Random, 0.1F, 3, 2, 5, Block.torchWood.blockID, 0);

        if (field_35048_b)
        {
            fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 2, 0, 3, 4, 0, 0, false);
        }

        if (field_35049_c)
        {
            fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 2, 4, 3, 4, 0, 0, false);
        }

        return true;
    }
}
