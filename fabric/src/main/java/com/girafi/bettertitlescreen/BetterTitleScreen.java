package com.girafi.bettertitlescreen;

import com.girafi.bettertitlescreen.handler.ConfigurationHandler;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.neoforged.fml.config.ModConfig;

public class BetterTitleScreen implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        NeoForgeConfigRegistry.INSTANCE.register(Constants.MOD_ID, ModConfig.Type.CLIENT, ConfigurationHandler.spec);
    }
}