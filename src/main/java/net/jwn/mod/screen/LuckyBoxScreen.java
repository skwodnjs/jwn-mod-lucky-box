package net.jwn.mod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jwn.mod.Main;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class LuckyBoxScreen extends AbstractContainerScreen<LuckyBoxMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MOD_ID, "textures/gui/lucky_box_gui.png");
    private int x, y;
    private boolean isHover;

    public LuckyBoxScreen(LuckyBoxMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        x = (width - imageWidth) / 2;
        y = (height - imageHeight) / 2;
        isHover = false;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);

        isHover = x + 125 <= mouseX && mouseX <= x + 125 + 20 && y + 34 <= mouseY && mouseY <= y + 34 + 18;

        ImageButton button;
        button = new ImageButton(x + 125, y + 34, 20, 18, 0, isHover ? 187 : 168, 0,
                TEXTURE, 256, 256, pButton -> {
            this.menu.buttonPressed();
        });
        addRenderableWidget(button);
    }
}
