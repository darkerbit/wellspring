package io.github.darkerbit.wellspring.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

public class WellspringCreativeInventoryScreen extends CreativeInventoryScreen {
    private static final Identifier TEXTURE = new Identifier("textures/gui/container/creative_inventory/tabs.png");

    public WellspringCreativeInventoryScreen(PlayerEntity player) {
        super(player);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        ItemGroup itemGroup = ItemGroup.GROUPS[getSelectedTab()];

        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        client.getTextureManager().bindTexture(new Identifier("textures/gui/container/creative_inventory/tab_" + itemGroup.getTexture()));
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        
        int normalGroupIndex = 0;
        int specialGroupIndex = 0;

        for (ItemGroup group : ItemGroup.GROUPS) {
            if (group.isSpecial()) {
                // TODO: Special ItemGroup Rendering
                specialGroupIndex++;
            } else {
                normalGroupIndex++;
            }
        }
    }
}
