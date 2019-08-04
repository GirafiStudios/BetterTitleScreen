package com.girafi.bettertitlescreen;

import com.girafi.bettertitlescreen.handler.ConfigurationHandler;
import com.girafi.bettertitlescreen.handler.TitleScreenHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BetterTitleScreen.MOD_ID)
public class BetterTitleScreen {
    public static final String MOD_ID = "bettertitlescreen";

    public BetterTitleScreen() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::setupClient);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigurationHandler.spec);
    }

    private void setupClient(final FMLClientSetupEvent event) {
        TitleScreenHandler.registerReloadListener();
    }
}