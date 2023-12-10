package com.girafi.bettertitlescreen;

import com.girafi.bettertitlescreen.handler.ConfigurationHandler;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(Constants.MOD_ID)
public class BetterTitleScreen {

    public BetterTitleScreen() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigurationHandler.spec);
    }
}