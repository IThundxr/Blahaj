package dev.ithundxr.blahaj.client;

import dev.ithundxr.blahaj.Blahaj;
import dev.ithundxr.blahaj.mixin.AccessorModelManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.regex.Pattern;

@Environment(EnvType.CLIENT)
public class BlahajModel implements BakedModel {
    private static final Pattern ESCAPED = Pattern.compile("[^a-z0-9/._-]");

    private final BakedModel oldModel;

    public BlahajModel(BakedModel oldModel) {
        this.oldModel = oldModel;
    }

    @Override
    public @NotNull List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction direction, @NotNull RandomSource random) {
        return oldModel.getQuads(state, direction, random);
    }

    @Override
    public boolean useAmbientOcclusion() {
        return oldModel.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return oldModel.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return oldModel.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return oldModel.isCustomRenderer();
    }

    @Override
    public @NotNull TextureAtlasSprite getParticleIcon() {
        return oldModel.getParticleIcon();
    }

    @Override
    public @NotNull ItemTransforms getTransforms() {
        return oldModel.getTransforms();
    }

    @Override
    public @NotNull ItemOverrides getOverrides() {
        return new ItemOverrides() {
            @NotNull
            @Override
            public BakedModel resolve(@NotNull BakedModel model, @NotNull ItemStack stack, @Nullable ClientLevel worldIn,
                                      @Nullable LivingEntity entityIn, int seed) {
                if (stack.hasCustomHoverName()) {
                    return getModelFromDisplayName(stack.getHoverName());
                }
                return oldModel;
            }
        };
    }

    private BakedModel getModelFromDisplayName(Component component) {
        // Model Manager & Map of baked models
        ModelManager modelManager = Minecraft.getInstance().getModelManager();
        Map<ResourceLocation, BakedModel> bakedModelMap = ((AccessorModelManager) modelManager).getBakedRegistry();

        // Missing Model
        BakedModel missingModel = modelManager.getMissingModel();

        // The Model
        BakedModel newModel = bakedModelMap.get(getLocSkin(fromComponent(component)));

        if (newModel == null) {
            return bakedModelMap.getOrDefault(getDefaultLoc(), missingModel);
        }

        return newModel;
    }

    private ResourceLocation getDefaultLoc() {
        return Blahaj.asResource("item/blue_shark");
    }

    private ResourceLocation getLocSkin(String name) {
        String fullName = ESCAPED.matcher(name).replaceAll("_");
        if (fullName.contains("_shark"))
            return Blahaj.asResource("item/blahaj_skins/" + fullName);
        return Blahaj.asResource("item/blahaj_skins/" + fullName + "_shark");
    }

    private String fromComponent(Component component) {
        String string = component.getString();
        string = ChatFormatting.stripFormatting(string);

        if (string == null) return "";

        string = string.trim().toLowerCase(Locale.ROOT);

        return string;
    }

    public static void registerModels(Set<ResourceLocation> set) {
        ResourceManager rm = Minecraft.getInstance().getResourceManager();
        Set<String> usedNames = new HashSet<>();

        Map<ResourceLocation, Resource> resources = rm.listResources("models/item/blahaj_skins", r -> r.getPath().endsWith(".json"));
        for (ResourceLocation model : resources.keySet()) {
            if (Blahaj.MOD_ID.equals(model.getNamespace())) {
                String path = model.getPath();
                if ("models/item/blue_shark".equals(path) || usedNames.contains(path))
                    continue;

                usedNames.add(path);

                path = path.substring("models/".length(), path.length() - ".json".length());
                set.add(Blahaj.asResource(path));
            }
        }
    }
}
