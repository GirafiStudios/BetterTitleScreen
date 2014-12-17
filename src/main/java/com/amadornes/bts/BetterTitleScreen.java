package com.amadornes.bts;

import com.amadornes.bts.handler.ConfigurationHandler;
import com.amadornes.bts.handler.TitleScreenHandler;
import com.amadornes.bts.proxy.IProxy;
import com.amadornes.bts.reference.Reference;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUI_FACTORY_CLASS, acceptableRemoteVersions = "*")
public class BetterTitleScreen {

    @Mod.Instance(Reference.MOD_ID)
    public static BetterTitleScreen instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = ("Better Title Screen is a client-side only mod"))
    public static IProxy proxy;

    @SuppressWarnings("unchecked")
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        //if (Loader.instance().isModLoaded() == true)
            //{

            //}

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

        FMLCommonHandler.instance().bus().register(new TitleScreenHandler());

    }

    @Mod.EventHandler
    public void init (FMLPostInitializationEvent event)
    {

    }
}