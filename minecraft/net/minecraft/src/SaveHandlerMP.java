package net.minecraft.src;

import java.io.File;
import java.util.List;

public class SaveHandlerMP implements ISaveHandler
{
    public SaveHandlerMP()
    {
    }

    /**
     * Returns a freshly loaded worldInfo from the save
     */
    public WorldInfo loadWorldInfo()
    {
        return null;
    }

    public void checkSessionLock()
    {
    }

    /**
     * Returns the chunk loader with the provided world provider
     */
    public IChunkLoader getChunkLoader(WorldProvider par1WorldProvider)
    {
        return null;
    }

    public void saveWorldInfoAndPlayer(WorldInfo worldinfo, List list)
    {
    }

    /**
     * Saves the passed in world info.
     */
    public void saveWorldInfo(WorldInfo worldinfo)
    {
    }

    /**
     * Gets the file location of the given map
     */
    public File getMapFileFromName(String par1Str)
    {
        return null;
    }

    /**
     * Returns the name of the directory where world information is saved
     */
    public String getSaveDirectoryName()
    {
        return "none";
    }
}
