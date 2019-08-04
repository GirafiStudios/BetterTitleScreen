package com.girafi.bettertitlescreen.handler;

import com.girafi.bettertitlescreen.BetterTitleScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.BrandingControl;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = BetterTitleScreen.MOD_ID, value = Dist.CLIENT)
public class TitleScreenHandler implements ISelectiveResourceReloadListener {
    private static boolean hasLoaded = false;

    @SubscribeEvent
    public static void openMainMenu(final GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.getGui() instanceof MainMenuScreen) {
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
            hasLoaded = true;
        }
    }

    public static void registerReloadListener() {
        ((IReloadableResourceManager) Minecraft.getInstance().getResourceManager()).addReloadListener(new TitleScreenHandler());
    }

    @Override
    public void onResourceManagerReload(@Nonnull IResourceManager resourceManager, @Nonnull Predicate<IResourceType> resourcePredicate) {
        hasLoaded = false;
        init();
    }
}