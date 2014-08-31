package com.amadornes.bts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


import com.amadornes.bts.handler.ConfigurationHandler;
import com.amadornes.bts.reference.Reference;
import com.amadornes.bts.reflect.ReflectUtilities;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, acceptableRemoteVersions = "*")
public class BetterTitleScreen {

    private List<String> text;

    @SuppressWarnings("unchecked")
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        readConfig();


        Field f = ReflectUtilities.getField(FMLCommonHandler.instance(), "brandings");
        f.setAccessible(true);
        try {
            FMLCommonHandler.instance().computeBranding();
            List<String> brands = new ArrayList<String>((List<String>) f.get(FMLCommonHandler.instance()));
            List<String> newBrands = new ArrayList<String>();
            newBrands.add(brands.get(0));

            newBrands.addAll(text);

            newBrands.add(brands.get(brands.size() - 1));
            f.set(FMLCommonHandler.instance(), newBrands);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void readConfig() {

        List<String> TestText = readResource("BetterTitleScreen.cfg");

        if (text == null) {
            this.text = new ArrayList<String>();
            File file = new File("config/BetterTitleScreen.cfg");

            if (!file.exists()) try {
            } catch (Exception e) {
            }

            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                while (br.ready())
                    this.text.add(br.readLine());

                br.close();
                fr.close();
            } catch (Exception e) {
            }

        }

    }
    
    private List<String> readResource(String file) {

        List<String> text = new ArrayList<String>();

        try {
            InputStream input = getClass().getResourceAsStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(input));

            while (br.ready())
                text.add(br.readLine());

            br.close();
        } catch (Exception e1) {
            return null;
        }

        boolean empty = true;

        for (String s : text)
            if (s.trim().length() > 0) {
                empty = false;
                break;
            }

        return empty ? null : text;
    }
    @Mod.EventHandler
    public void init (FMLPostInitializationEvent event)
    {

    }
}