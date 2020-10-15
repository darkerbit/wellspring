package io.github.darkerbit.wellspring.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

public class WellspringCreativeInventoryScreen extends CreativeInventoryScreen {
    private static final Identifier TEXTURE = new Identifier("textures/gui/container/creative_inventory/tabs.png");
    private static final Identifier WELLSPRING_TEXTURE =
            new Identifier("wellspring", "textures/gui/creative_tab.png");

    private static final int TEXTURE_WIDTH = 32;
    private static final int TEXTURE_HEIGHT = 64;

    private static final int SIDETAB_WIDTH = 32;
    private static final int SIDETAB_HEIGHT = 20;

    private static final int SIDETAB_OFFSET = -27;

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
                renderSpecial(matrices, group, specialGroupIndex, group == itemGroup);
                specialGroupIndex++;
            } else {
                renderSideTab(matrices, group, normalGroupIndex, group == itemGroup);
                normalGroupIndex++;
            }
        }

        client.getTextureManager().bindTexture(TEXTURE);

        int scrollX = x + 175;
        int scrollY = y + 18;
        int height = scrollY + 112;

        float scrollPosition = ((CreativeInventoryHooks) this).getScrollPosition();

        if (itemGroup.hasScrollbar()) {
            drawTexture(
                    matrices,
                    scrollX, scrollY + (int)((height - scrollY - 17) * scrollPosition),
                    232, 0, 12, 15);
        }
    }

    private void renderSideTab(MatrixStack matrices, ItemGroup group, int groupIndex, boolean selected) {
        int x = this.x + SIDETAB_OFFSET;
        int y = this.y + groupIndex * (SIDETAB_HEIGHT - 1) + 4;

        client.getTextureManager().bindTexture(WELLSPRING_TEXTURE);
        drawTexture(
                matrices,
                x, y,
                0, selected ? SIDETAB_HEIGHT : 0,
                SIDETAB_WIDTH, SIDETAB_HEIGHT,
                TEXTURE_WIDTH, TEXTURE_HEIGHT);
    }

    private void renderSpecial(MatrixStack matrices, ItemGroup group, int groupIndex, boolean selected) {
        matrices.push();

        matrices.translate(0, 0, 0);

        if (!selected)
            matrices.translate(0, group.isTopRow() ? -3 : 3, 0);

        client.getTextureManager().bindTexture(TEXTURE);
        renderTabIcon(matrices, group);
        client.getTextureManager().bindTexture(WELLSPRING_TEXTURE);
        matrices.pop();
    }
}
