package dev.ithundxr.blahaj.mixin;

import dev.ithundxr.blahaj.item.BlahajItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Allay.class)
public class MixinAllay {
    /**
     * Prevent allay's from taking blahaj's
     */
    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    public void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (player.getItemInHand(hand).getItem() instanceof BlahajItem)
            cir.setReturnValue(InteractionResult.PASS);
    }
}
