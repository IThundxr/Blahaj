package dev.ithundxr.blahaj.mixin;

import dev.ithundxr.blahaj.item.BlahajItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerRenderer.class)
public class MixinPlayerRenderer {
    @Inject(method = "getArmPose", at = @At("TAIL"), cancellable = true)
    private static void getArmPose(AbstractClientPlayer player, InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> cir) {
        if (player.getItemInHand(hand).getItem() instanceof BlahajItem)
            cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
    }
}
