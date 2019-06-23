package com.girafi.bettertitlescreen;

import com.girafi.bettertitlescreen.handler.ConfigurationHandler;
import com.girafi.bettertitlescreen.handler.TitleScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BetterTitleScreen.MOD_ID)
public class BetterTitleScreen {
    public static final String MOD_ID = "bettertitlescreen";

    public BetterTitleScreen() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigurationHandler.spec);
    }

    private void loadComplete(final FMLLoadCompleteEvent event) {
        System.out.println("LOAD COMPLETE START");
        TitleScreenHandler.init();
        System.out.println("LOAD COMPLETE");
    }
}