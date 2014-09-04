package com.amadornes.bts.handler;

import com.amadornes.bts.reflect.ReflectUtilities;
import cpw.mods.fml.common.FMLCommonHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.amadornes.bts.handler.ConfigurationHandler.*;

public class TitleScreenHandler{

        {

            Field f = ReflectUtilities.getField(FMLCommonHandler.instance(), "brandings");
            f.setAccessible(true);
            try {
                FMLCommonHandler.instance().computeBranding();
                List<String> brands = new ArrayList<String>((List<String>) f.get(FMLCommonHandler.instance()));
                List<String> newBrands = new ArrayList<String>();

                f.set(FMLCommonHandler.instance(), newBrands);

                if (TitleScreenMCVersion == true)
                    {
                        newBrands.add(brands.get(0));
                    }

                Collections.addAll(newBrands, TitleScreenText);

                if (TitleScreenMCP == true)
                    {
                        newBrands.add(brands.get(brands.size() - 4));
                    }

                if (TitleScreenFML == true)
                    {
                        newBrands.add(brands.get(brands.size() - 3));
                    }

                if (TitleScreenForge == true)
                    {
                        newBrands.add(brands.get(brands.size() - 2));
                    }


                if (TitleScreenMods == true)
                    {
                        newBrands.add(brands.get(brands.size() - 1));
                    }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }

}
