package com.amadornes.bts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


import com.amadornes.bts.reference.Reference;
import com.amadornes.bts.reflect.ReflectUtilities;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, acceptableRemoteVersions = "*")
public class BetterTitleScreen {

    private List<String> text;

    @SuppressWarnings("unchecked")
    @EventHandler
    public void init(FMLInitializationEvent ev) {
    
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
            System.exit(0);
        }
    }
    
    private void readConfig() {
    
        List<String> text = readResource("BetterTitleScreen.cfg");
        
        if (text == null) {
            this.text = new ArrayList<String>();
            File file = new File("config/BetterTitleScreen.cfg");
            file.getParentFile().mkdir();
            file.getParentFile().mkdirs();
            
            if (!file.exists()) try {
                file.createNewFile();
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
        } else {
            this.text = text;
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
    
}
