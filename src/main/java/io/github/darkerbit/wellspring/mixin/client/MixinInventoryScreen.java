package io.github.darkerbit.wellspring.mixin.client;

import io.github.darkerbit.wellspring.client.WellspringCreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public abstract class MixinInventoryScreen extends AbstractInventoryScreen<PlayerScreenHandler> {
    public MixinInventoryScreen(PlayerScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }

    @Inject(at = @At("HEAD"), method = "init()V", cancellable = true)
    protected void init(CallbackInfo info) {
        if (this.client.interactionManager.hasCreativeInventory()) {
            this.client.openScreen(new WellspringCreativeInventoryScreen(this.client.player));
            info.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "tick()V", cancellable = true)
    public void tick(CallbackInfo info) {
        if (this.client.interactionManager.hasCreativeInventory()) {
            this.client.openScreen(new WellspringCreativeInventoryScreen(this.client.player));
            info.cancel();
        }
    }
}
