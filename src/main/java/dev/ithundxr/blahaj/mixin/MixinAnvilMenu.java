package dev.ithundxr.blahaj.mixin;

import dev.ithundxr.blahaj.item.BlahajItem;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(AnvilMenu.class)
public class MixinAnvilMenu {
    /**
     * Set name and owner of merged item properly
     */
    @Inject(
            method = "createResult",
            at = {
                    @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/world/item/ItemStack;resetHoverName()V"
                    ),
                    @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/world/item/ItemStack;setHoverName(Lnet/minecraft/network/chat/Component;)Lnet/minecraft/world/item/ItemStack;"
                    )
            },
            locals = LocalCapture.CAPTURE_FAILHARD,
            expect = 2,
            require = 2
    )
    public void createResult(CallbackInfo ci, ItemStack itemStack, int i, int j, int k, ItemStack itemStack2, ItemStack itemStack3, Map<Enchantment, Integer> map) {
        if(itemStack2.getItem() instanceof BlahajItem)
            itemStack2.addTagElement(BlahajItem.OWNER_KEY, StringTag.valueOf(((AccessorItemCombinerMenu) this).getPlayer().getName().getString()));
    }
}
