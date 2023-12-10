package com.girafi.bettertitlescreen;

import com.girafi.bettertitlescreen.handler.ConfigurationHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(value = Constants.MOD_ID)
public class BetterTitleScreen {

    public BetterTitleScreen() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigurationHandler.spec);
    }
}