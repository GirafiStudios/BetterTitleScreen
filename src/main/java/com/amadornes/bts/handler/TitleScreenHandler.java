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
                newBrands.add(brands.get(0));

                Collections.addAll(newBrands, TitleScreenText);

                f.set(FMLCommonHandler.instance(), newBrands);

                if (TitleScreenMCP == true)
                    try {
                        newBrands.add(brands.get(brands.size() - 4));
                    }
                    finally {
                        if (TitleScreenMCP == false)
                             {
                                newBrands.add(brands.get(0));
                            }
                    }

                if (TitleScreenFML == true)
                    try {
                        newBrands.add(brands.get(brands.size() - 3));
                    }
                    finally {
                        if (TitleScreenFML == false)
                            {
                               newBrands.add(brands.get(0));
                            }
                    }

                if (TitleScreenForge == true)
                    try {
                        newBrands.add(brands.get(brands.size() - 2));
                    }
                    finally {
                        if (TitleScreenForge == false)
                        {
                            newBrands.add(brands.get(0));
                        }
                    }

                if (TitleScreenMods == true)
                    try {
                        newBrands.add(brands.get(brands.size() - 1));
                    }
                    finally {
                        if (TitleScreenMods == false)
                        {
                            newBrands.add(brands.get(0));
                        }
                    }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }

}
