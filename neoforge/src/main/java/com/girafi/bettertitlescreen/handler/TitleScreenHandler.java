package com.girafi.bettertitlescreen.handler;

import com.girafi.bettertitlescreen.Constants;
import net.minecraft.client.gui.screens.TitleScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.internal.BrandingControl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class TitleScreenHandler {
    private static boolean hasLoaded = false;

    @SubscribeEvent
    public static void openMainMenu(final ScreenEvent.Init.Pre event) {
        if (event.getScreen() instanceof TitleScreen) {
            TitleScreenHandler.init();
        }
    }

    public static void init() {
        if (!hasLoaded) {
            try {
                BrandingControl brandingControl = new BrandingControl();

                Field f = BrandingControl.class.getDeclaredField("brandings");
                f.setAccessible(true);

                Method computeBranding = BrandingControl.class.getDeclaredMethod("computeBranding");
                computeBranding.setAccessible(true);
                computeBranding.invoke(null);

                List<String> brands = new ArrayList<>((List<String>) f.get(brandingControl));
                List<String> newBrands = new ArrayList<>();

                f.set(brandingControl, newBrands);
                newBrands.addAll(ConfigurationHandler.CUSTOM_TEXT.titleScreenText.get());

                if (ConfigurationHandler.GENERAL.titleScreenForge.get()) {
                    newBrands.add(brands.get(0));
                }
                if (ConfigurationHandler.GENERAL.titleScreenMCVersion.get()) {
                    newBrands.add(brands.get(1));
                }
                if (ConfigurationHandler.GENERAL.titleScreenMCP.get()) {
                    newBrands.add(brands.get(2));
                }
                if (ConfigurationHandler.GENERAL.titleScreenMods.get()) {
                    newBrands.add(brands.get(3));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            hasLoaded = true;
        }
    }
}