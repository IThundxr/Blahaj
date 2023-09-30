package dev.ithundxr.blahaj.mixin;

import dev.ithundxr.blahaj.item.BlahajItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public class MixinHumanoidModel<T extends LivingEntity> {
    @Final @Shadow public ModelPart rightArm;
    @Final @Shadow public ModelPart leftArm;

    /**
     * Pose the arms for players properly
     */
    @Inject(
            method = {"poseRightArm", "poseLeftArm"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/model/AnimationUtils;animateCrossbowHold(Lnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/client/model/geom/ModelPart;Z)V",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    public void poseArms(T livingEntity, CallbackInfo ci) {
        if(livingEntity.getMainHandItem().getItem() instanceof BlahajItem || livingEntity.getOffhandItem().getItem() instanceof BlahajItem) {
            this.rightArm.xRot = -0.95F;
            this.rightArm.yRot = (float) (-Math.PI / 8);
            this.leftArm.xRot = -0.90F;
            this.leftArm.yRot = (float) (Math.PI / 8);
            ci.cancel();
        }
    }
}
