package com.girafi.bettertitlescreen.mixin;

import com.girafi.bettertitlescreen.handler.ConfigurationHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Mixin(TitleScreen.class)
public class MixinTitleScreen {
    private float widgetFade;

    @ModifyArg(method = "extractRenderState(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Ljava/lang/String;III)V"),
            index = 1)
    private String modifyDefaultTitle(String defaultTitle) { //Options to disable default
        //System.out.println("modifyDefaultTitle");
        String[] args = defaultTitle.split("/");
        String mc = args[0];

        return (ConfigurationHandler.GENERAL.titleScreenMCVersion.get() ? mc : "")
                + (isMCandFabric() ? "/" : "")
                + (ConfigurationHandler.GENERAL.titleScreenFabric.get() ? "Fabric" : "");
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Ljava/lang/String;III)V"))
    private void injectTitle(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo callbackInfo) { //Injects title before default vanilla drawString method
        TitleScreen titleScreen = (TitleScreen) (Object) this;
        Minecraft mc = Minecraft.getInstance();
        List<? extends String> titleList = ConfigurationHandler.CUSTOM_TEXT.titleScreenText.get();

        AtomicInteger lineNo = new AtomicInteger();
        titleList.forEach((s) -> {
            lineNo.getAndIncrement();
            guiGraphics.text(mc.font, s, 2, titleScreen.height - ((!isMCorFabric() ? 0 : 10) + (titleList.size() * 10) - (lineNo.get() * 10) + (mc.font.lineHeight + 1)), ARGB.white(this.widgetFade));
        });
    }

    @Redirect(method = "extractRenderState(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ARGB;white(F)I"))
    private int captureWidgetFade(float f) {
        this.widgetFade = f;
        return ARGB.white(f);
    }

    public boolean isMCandFabric() {
        return ConfigurationHandler.GENERAL.titleScreenMCVersion.get() && ConfigurationHandler.GENERAL.titleScreenFabric.get();
    }

    public boolean isMCorFabric() {
        return ConfigurationHandler.GENERAL.titleScreenMCVersion.get() || ConfigurationHandler.GENERAL.titleScreenFabric.get();
    }
}