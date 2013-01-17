package narnacraft.mod;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SmashedItem extends Item {
	
	private final static Item SmashedItem = new SmashedItem(5000).setMaxStackSize(64).setCreativeTab(CreativeTabs.tabMisc).setIconIndex(0).setItemName("Smashed Obsidian");
		
	private static final Item CopperIngot = new SmashedItem(5001).setMaxStackSize(16).setCreativeTab(CreativeTabs.tabMisc).setIconIndex(1).setItemName("Copper Ingot");
    int MyItemID = CopperIngot.itemID;
       
    		public SmashedItem(int id) {
                super(id);
                LanguageRegistry.addName(SmashedItem, "Generic Item");
            	LanguageRegistry.addName(CopperIngot, "Copper Ingot");
    
        }

}