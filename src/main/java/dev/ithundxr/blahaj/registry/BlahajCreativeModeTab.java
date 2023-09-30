package dev.ithundxr.blahaj.registry;

import dev.ithundxr.blahaj.Blahaj;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class BlahajCreativeModeTab {
    public static final ResourceKey<CreativeModeTab> BLAHAJ_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Blahaj.asResource("blahaj_tab"));

    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, BLAHAJ_TAB, FabricItemGroup.builder()
                .title(Component.translatable("itemGroup.blahaj"))
                .icon(BlahajItems.BLUE_SHARK::getDefaultInstance)
                .build());
    }
}
