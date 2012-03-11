package net.minecraft.src;

import java.io.File;
import java.util.List;

public interface ISaveHandler
{
    /**
     * Returns a freshly loaded worldInfo from the save
     */
    public abstract WorldInfo loadWorldInfo();

    public abstract void checkSessionLock();

    /**
     * Returns the chunk loader with the provided world provider
     */
    public abstract IChunkLoader getChunkLoader(WorldProvider worldprovider);

    public abstract void saveWorldInfoAndPlayer(WorldInfo worldinfo, List list);

    /**
     * Saves the passed in world info.
     */
    public abstract void saveWorldInfo(WorldInfo worldinfo);

    /**
     * Gets the file location of the given map
     */
    public abstract File getMapFileFromName(String s);

    /**
     * Returns the name of the directory where world information is saved
     */
    public abstract String getSaveDirectoryName();
}
