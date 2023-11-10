package dev.ithundxr.blahaj;

import dev.ithundxr.blahaj.client.BlahajModelLoadingPlugin;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;

public class BlahajClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLoadingPlugin.register(new BlahajModelLoadingPlugin());
    }
}
