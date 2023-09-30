package dev.ithundxr.blahaj;

import dev.ithundxr.blahaj.registry.*;

public class ModSetup {
    public static void register() {
        Blahaj.LOGGER.info("Started Registration");
        BlahajCreativeModeTab.register();
        BlahajItems.register();
        Blahaj.LOGGER.info("Finished Registration");
    }
}
