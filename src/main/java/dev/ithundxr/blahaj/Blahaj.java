package dev.ithundxr.blahaj;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Blahaj implements ModInitializer {
    public static String MOD_ID = "blahaj";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModSetup.register();
    }

    public static ResourceLocation asResource(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}
