package narnacraft.mod;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="NarnaCraft", name="NarnaCraft", version="0.0.4")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class NarnaBase {
        
        
        @Instance("NarnaCraft")
        public static NarnaBase instance;
//Normal items
        private final static Item SmashedItem = new NarnaItem(5000);
        private final static Item CopperIngot = new NarnaItem(5001);
        private final static Item TinIngot = new NarnaItem(5002);
        private final static Item BronzeIngot = new NarnaItem(5003);
        //blocks
                private final static Block CopperBlock = new NarnaBlock(5005);
                private final static Block TinBlock = new NarnaBlock(5006);
                private final static Block BronzeBlock = new NarnaBlock(5007);
           //special items     
        public final static Item TinIngot = new NarnaItem(5004)
                .setMaxStackSize(64).setIconIndex(0).setItemName("TinIngot");

        @SidedProxy(clientSide="narnacraft.mod.ClientProxy",
                        serverSide="narnacraft.mod.CommonProxy")
        public static CommonProxy proxy;
        
        @PreInit
        public void preInit(FMLPreInitializationEvent event) {
                // Stub Method
        }
        
        @Init
        public void load(FMLInitializationEvent event) {
                LanguageRegistry.addName(SmashedItem, "Smashed Obsidian");
                LanguageRegistry.addName(TinIngot, "Tin Ingot");
        }
        
        @PostInit
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
}
