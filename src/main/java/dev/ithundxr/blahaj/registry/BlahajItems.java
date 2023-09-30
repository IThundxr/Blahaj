package dev.ithundxr.blahaj.registry;

import dev.ithundxr.blahaj.Blahaj;
import dev.ithundxr.blahaj.item.BlahajItem;
import dev.ithundxr.blahaj.item.ContainerBlahajItem;
import dev.ithundxr.blahaj.util.ItemUtils;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

public class BlahajItems {
    public static final Item BLUE_SHARK = new BlahajItem(ItemUtils.SINGLE_STACKABLE, "item.blahaj.blue_shark.tooltip");
    public static final Item GRAY_SHARK = new BlahajItem(ItemUtils.SINGLE_STACKABLE, "item.blahaj.gray_shark.tooltip");
    public static final Item BLUE_WHALE = new ContainerBlahajItem(ItemUtils.SINGLE_STACKABLE, "item.blahaj.blue_whale.tooltip");
    public static final Item BREAD = new BlahajItem(ItemUtils.SINGLE_STACKABLE, null);


    public static void register() {
        registerItem("blue_shark", BLUE_SHARK);
        registerItem("gray_shark", GRAY_SHARK);
        registerItem("blue_whale", BLUE_WHALE);
        registerItem("bread", BREAD);
    }

    private static void registerItem(@NotNull String path, @NotNull Item item) {
        if (BuiltInRegistries.ITEM.getOptional(Blahaj.asResource(path)).isEmpty()) {
            // Register Item
            Registry.register(BuiltInRegistries.ITEM, Blahaj.asResource(path), item);

            // Add it to the creative tab
            ItemGroupEvents.modifyEntriesEvent(BlahajCreativeModeTab.BLAHAJ_TAB)
                    .register((content) -> content.accept(item));
        }
    }
}
