package net.minecraft.src;

public class PlayerCapabilities
{
    /** Disables player damage. */
    public boolean disableDamage;

    /** Sets/indicates whether the player is flying. */
    public boolean isFlying;

    /** whether or not to allow the player to fly when they double jump. */
    public boolean allowFlying;

    /** Used by ItemBucket to say if it should empty when used or not. */
    public boolean depleteBuckets;

    public PlayerCapabilities()
    {
        disableDamage = false;
        isFlying = false;
        allowFlying = false;
        depleteBuckets = false;
    }

    public void writeCapabilitiesToNBT(NBTTagCompound par1NBTTagCompound)
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setBoolean("invulnerable", disableDamage);
        nbttagcompound.setBoolean("flying", disableDamage);
        nbttagcompound.setBoolean("mayfly", allowFlying);
        nbttagcompound.setBoolean("instabuild", depleteBuckets);
        par1NBTTagCompound.setTag("abilities", nbttagcompound);
    }

    public void readCapabilitiesFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        if (par1NBTTagCompound.hasKey("abilities"))
        {
            NBTTagCompound nbttagcompound = par1NBTTagCompound.getCompoundTag("abilities");
            disableDamage = nbttagcompound.getBoolean("invulnerable");
            isFlying = nbttagcompound.getBoolean("flying");
            allowFlying = nbttagcompound.getBoolean("mayfly");
            depleteBuckets = nbttagcompound.getBoolean("instabuild");
        }
    }
}
