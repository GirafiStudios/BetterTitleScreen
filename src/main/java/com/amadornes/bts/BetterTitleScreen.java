package com.amadornes.bts;

import com.amadornes.bts.handler.ConfigurationHandler;
import com.amadornes.bts.handler.TitleScreenHandler;
import com.amadornes.bts.proxy.IProxy;
import com.amadornes.bts.reference.Reference;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUI_FACTORY_CLASS, acceptableRemoteVersions = "*")

public class BetterTitleScreen {

    @Mod.Instance(Reference.MOD_ID)
    public static BetterTitleScreen instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = ("Better Title Screen is a client-side only mod"))
    public static IProxy proxy;

    @SuppressWarnings("unchecked")
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @Mod.EventHandler
    public void init(FMLPostInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(new TitleScreenHandler());
    }
}