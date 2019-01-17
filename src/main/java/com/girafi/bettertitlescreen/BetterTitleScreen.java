package com.girafi.bettertitlescreen;

import com.girafi.bettertitlescreen.handler.ConfigurationHandler;
import com.girafi.bettertitlescreen.handler.TitleScreenHandler;
import com.girafi.bettertitlescreen.reference.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(Reference.MOD_ID)
public class BetterTitleScreen {

    public BetterTitleScreen() {
        FMLModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLModLoadingContext.get().getModEventBus().addListener(this::loadComplete);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ConfigurationHandler.loadFrom(FMLPaths.CONFIGDIR.get());
    }

    private void loadComplete(final FMLLoadCompleteEvent event) {
        TitleScreenHandler.init();
    }
}