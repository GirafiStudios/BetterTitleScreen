package com.amadornes.bts.client.gui;

import com.amadornes.bts.handler.ConfigurationHandler;
import com.amadornes.bts.reference.Reference;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class InGameGuiConfig extends GuiConfig
{
    public InGameGuiConfig(GuiScreen guiScreen)
    {
        super(guiScreen,
                new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                Reference.MOD_ID,
                false, //Does it require a world restart?
                true, //Does it require a Minecraft restart?
                GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
    }
}
