package dev.ithundxr.blahaj.client;

import dev.ithundxr.blahaj.Blahaj;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Environment(EnvType.CLIENT)
public class BlahajModel implements BakedModel {
    BakedModel oldModel;

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

    private final ItemOverrides itemHandler = new ItemOverrides() {
        @NotNull
        @Override
        public BakedModel resolve(BakedModel model, ItemStack stack, @Nullable ClientLevel worldIn, @Nullable LivingEntity entityIn, int seed) {
            return Minecraft.getInstance().getModelManager().getModel(new ModelResourceLocation(Blahaj.asResource("gray_shark"), "inventory"));
        }
    };

    @Override
    public @NotNull ItemOverrides getOverrides() {
        return itemHandler;
    }
}
