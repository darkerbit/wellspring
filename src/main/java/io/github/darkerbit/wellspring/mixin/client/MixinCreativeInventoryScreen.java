package io.github.darkerbit.wellspring.mixin.client;

import io.github.darkerbit.wellspring.client.CreativeInventoryHooks;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CreativeInventoryScreen.class)
public abstract class MixinCreativeInventoryScreen extends AbstractInventoryScreen<CreativeInventoryScreen.CreativeScreenHandler>
        implements CreativeInventoryHooks {
    public MixinCreativeInventoryScreen(CreativeInventoryScreen.CreativeScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }

    @Shadow
    private float scrollPosition;

    @Override
    public float getScrollPosition() {
        return scrollPosition;
    }
}
