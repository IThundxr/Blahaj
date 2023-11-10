package dev.ithundxr.blahaj.client;

import dev.ithundxr.blahaj.Blahaj;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.resources.ResourceLocation;

public class BlahajModelLoadingPlugin implements ModelLoadingPlugin {
    public static final ResourceLocation BLAHAJ_MODEL = Blahaj.asResource("item/blahaj");

    @Override
    public void onInitializeModelLoader(Context pluginContext) {
        pluginContext.resolveModel().register(context -> {
            if(context.id().equals(BLAHAJ_MODEL)) {
                return new BlahajModelNew();
            } else {
                return null;
            }
        });
    }
}
