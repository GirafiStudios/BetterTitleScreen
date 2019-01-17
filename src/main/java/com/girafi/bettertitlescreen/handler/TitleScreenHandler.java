package com.girafi.bettertitlescreen.handler;

import net.minecraftforge.fml.BrandingControl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TitleScreenHandler {

    public static void init() {
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

            if (ConfigurationHandler.GENERAL.titleScreenMCVersion.get()) {
                newBrands.add(brands.get(0));
            }
            if (ConfigurationHandler.GENERAL.titleScreenMCP.get()) {
                newBrands.add(brands.get(1));
            }
            if (ConfigurationHandler.GENERAL.titleScreenForge.get()) {
                newBrands.add(brands.get(2));
            }
            if (ConfigurationHandler.GENERAL.titleScreenMods.get()) {
                newBrands.add(brands.get(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}