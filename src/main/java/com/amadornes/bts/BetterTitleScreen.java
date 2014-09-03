package com.amadornes.bts;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import com.amadornes.bts.handler.ConfigurationHandler;
import com.amadornes.bts.reference.Reference;
import com.amadornes.bts.reflect.ReflectUtilities;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import static com.amadornes.bts.handler.ConfigurationHandler.TitleScreenText;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, guiFactory = Reference.GUI_FACTORY_CLASS, acceptableRemoteVersions = "*")
public class BetterTitleScreen {

    @Mod.Instance(Reference.MOD_ID)
    public static BetterTitleScreen instance;

    @SuppressWarnings("unchecked")
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        Field f = ReflectUtilities.getField(FMLCommonHandler.instance(), "brandings");
        f.setAccessible(true);
        try {
            FMLCommonHandler.instance().computeBranding();
            List<String> brands = new ArrayList<String>((List<String>) f.get(FMLCommonHandler.instance()));
            List<String> newBrands = new ArrayList<String>();
            newBrands.add(brands.get(0));

            Collections.addAll(newBrands, TitleScreenText);

            newBrands.add(brands.get(brands.size() - 1));
            f.set(FMLCommonHandler.instance(), newBrands);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Mod.EventHandler
    public void init (FMLPostInitializationEvent event)
    {

    }
}