package narnacraft.mod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class NarnaItem extends Item {

        public NarnaItem(int id) {
                super(id);
                
                // Constructor Configuration
                maxStackSize = 64;
                setCreativeTab(CreativeTabs.tabMisc);
                setIconIndex(0);
                setItemName("NarnaItem");
        }
        
        public String getTextureFile() {
                return CommonProxy.ITEMS_PNG;
        }
}