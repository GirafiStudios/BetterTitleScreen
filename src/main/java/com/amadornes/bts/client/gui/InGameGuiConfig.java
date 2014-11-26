package com.amadornes.bts.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.amadornes.bts.handler.ConfigurationHandler;
import com.amadornes.bts.reference.Reference;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class InGameGuiConfig extends GuiConfig
{
    public InGameGuiConfig(GuiScreen parent)
    {
        super(parent, InGameGuiConfig.getConfigElements(), Reference.MOD_ID, false, true, GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
    }

    private  static List<IConfigElement> getConfigElements() {

        List<IConfigElement> list = new ArrayList<IConfigElement>();

        List<InGameGuiConfig> listCustomText = new ConfigElement(ConfigurationHandler.configuration.getCategory(ConfigurationHandler.CATEGORY_CUSTOM_TEXT)).getChildElements();
        List<InGameGuiConfig> general = new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements();

        list.add(new DummyConfigElement.DummyCategoryElement("Custom Text", "ConfigurationHandler.CATEGORY_CUSTOM_TEXT", listCustomText));
        list.add(new DummyConfigElement.DummyCategoryElement("General", "Configuration.CATEGORY_GENERAL", general));

        return list;
    }
}