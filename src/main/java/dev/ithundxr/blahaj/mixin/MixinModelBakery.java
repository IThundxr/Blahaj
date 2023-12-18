package dev.ithundxr.blahaj.mixin;

import com.google.common.collect.Sets;
import dev.ithundxr.blahaj.client.BlahajModel;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mixin(ModelBakery.class)
public abstract class MixinModelBakery {
    @Shadow @Final private Map<ResourceLocation, UnbakedModel> unbakedCache;

    @Shadow @Final private Map<ResourceLocation, UnbakedModel> topLevelModels;

    @Shadow public abstract UnbakedModel getModel(ResourceLocation modelLocation);

    @Inject(method = "<init>", at = @At(target = "Ljava/util/Map;values()Ljava/util/Collection;", value = "INVOKE"))
    private void blahaj$injectModels(BlockColors blockColors, ProfilerFiller profilerFiller, Map<ResourceLocation, BlockModel> modelResources,
    Map<ResourceLocation, List<ModelBakery.LoadedJson>> blockStateResources, CallbackInfo ci) {
        Set<ResourceLocation> additionalModels = Sets.newHashSet();
        BlahajModel.registerModels(additionalModels);
        for (ResourceLocation rl : additionalModels) {
            UnbakedModel unbakedmodel = getModel(rl); // loadTopLevel(...), but w/o ModelResourceLocation limitation
            unbakedCache.put(rl, unbakedmodel);
            topLevelModels.put(rl, unbakedmodel);
        }
    }
}
