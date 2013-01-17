package narnacraft.mod;

import net.minecraftforge.client.MinecraftForgeClient;
import narnacraft.mod.CommonProxy;

public class ClientProxy extends CommonProxy {
        
        @Override
        public void registerRenderers() {
                MinecraftForgeClient.preloadTexture(ITEMS_PNG);
                MinecraftForgeClient.preloadTexture(BLOCK_PNG);
        }
        
}