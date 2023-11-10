package dev.ithundxr.blahaj.mixin;

import dev.ithundxr.blahaj.Blahaj;
import dev.ithundxr.blahaj.client.BlahajModel;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ModelManager.class)
public class MixinModelManager {
    @Shadow
    private Map<ResourceLocation, BakedModel> bakedRegistry;

    @Inject(at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/resources/model/ModelBakery;getBakedTopLevelModels()Ljava/util/Map;", shift = At.Shift.AFTER), method = "apply(Lnet/minecraft/client/resources/model/ModelManager$ReloadState;Lnet/minecraft/util/profiling/ProfilerFiller;)V")
    private void onModelBake(ModelManager.ReloadState reloadState, ProfilerFiller profilerFiller, CallbackInfo ci) {
        ModelResourceLocation key = new ModelResourceLocation(Blahaj.asResource("blue_shark"), "inventory");
        bakedRegistry.put(key, new BlahajModel(bakedRegistry.get(key)));
    }
}
