package com.girafi.bettertitlescreen;

import com.girafi.bettertitlescreen.handler.ConfigurationHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(Constants.MOD_ID)
public class BetterTitleScreen {

    public BetterTitleScreen(ModContainer modContainer, IEventBus modBus) {
        modContainer.registerConfig(ModConfig.Type.CLIENT, ConfigurationHandler.spec);
    }
}