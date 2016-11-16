package com.girafi.bettertitlescreen.handler;

import com.girafi.bettertitlescreen.reflect.ReflectUtilities;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.girafi.bettertitlescreen.handler.ConfigurationHandler.*;

public class TitleScreenHandler {
    static {
        Field f = ReflectUtilities.getField(FMLCommonHandler.instance(), "brandings");
        f.setAccessible(true);
        try {
            FMLCommonHandler.instance().computeBranding();
            List<String> brands = new ArrayList<String>((List<String>) f.get(FMLCommonHandler.instance()));
            List<String> newBrands = new ArrayList<String>();

            f.set(FMLCommonHandler.instance(), newBrands);

            if (TitleScreenMCVersion) {
                newBrands.add(brands.get(0));
            }
            Collections.addAll(newBrands, TitleScreenText);

            if (TitleScreenMCP) {
                newBrands.add(brands.get(brands.size() - 4));
            }
            if (TitleScreenFML) {
                newBrands.add(brands.get(brands.size() - 3));
            }
            if (TitleScreenForge) {
                newBrands.add(brands.get(brands.size() - 2));
            }
            if (TitleScreenMods) {
                newBrands.add(brands.get(brands.size() - 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}